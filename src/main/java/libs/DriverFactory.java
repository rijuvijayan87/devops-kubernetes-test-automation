package libs;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.UnreachableBrowserException;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import persistence.ScenarioContext;

import java.net.MalformedURLException;
import java.net.URL;

public class DriverFactory extends PropertyFiles {
    protected static WebDriver driver;
    protected ScenarioContext persistentData;


    protected WebDriver createWebDriver() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        if (getPropertyValue("EXECUTIONONSERVER").equalsIgnoreCase("true")) {

            if (getPropertyValue("BROWSER").equalsIgnoreCase("chrome")) {
                capabilities = DesiredCapabilities.chrome();
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--remote-debugging-port=9222");
                options.setExperimentalOption("useAutomationExtension", false);
                if (getPropertyValue("HEADLESS").equalsIgnoreCase("true")) {
                    options.addArguments("HEADLESS");
                }
                capabilities.setCapability(ChromeOptions.CAPABILITY, options);
                capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
            } else if (getPropertyValue("BROWSER").equalsIgnoreCase("firefox")) {
                capabilities = DesiredCapabilities.firefox();
            } else if (getPropertyValue("BROWSER").equalsIgnoreCase("ie")) {
                capabilities = DesiredCapabilities.internetExplorer();
            }
            capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
            try {
                this.driver = new RemoteWebDriver(new URL(getPropertyValue("selenium_local_server")), capabilities);
            } catch (UnreachableBrowserException exception) {
                System.out.println("Selenium server not setup. Creating driver using webdriver manager");
                WebDriverManager.chromedriver().setup();
                this.driver = new ChromeDriver();
            }
        }
        driver.manage().window().maximize();

        initializeObjects();
        return driver;
    }

    /**
     * Initialize PageObjects here.
     * Any new Page object that gets created should be initialised here.
     */
    private void initializeObjects() {
        if (driver != null) {
            persistentData = new ScenarioContext();
        }
    }
}
