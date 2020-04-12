package libs;

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
    private WebDriver wd;
    protected EventFiringWebDriver driver;
    protected EventHandler handler;
    protected ScenarioContext persistentData;


    protected EventFiringWebDriver createWebDriver() throws MalformedURLException {
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
                wd = new RemoteWebDriver(new URL(getPropertyValue("selenium_local_server")), capabilities);
            } catch (UnreachableBrowserException exception) {
                System.out.println("WebDriver creation failed. Possibly Remote webdriver endpoint is not configured properly. Check <IP>:4444/wd/hub endpoint");
                System.out.println(exception.getMessage());
                System.exit(1);
            }
        } else {
            System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\developer\\Chrome_Driver\\chromedriver.exe");
            ChromeOptions options = new ChromeOptions();
            options.setExperimentalOption("useAutomationExtension", false);
            options.addArguments("--remote-debugging-port=10000");
            wd = new ChromeDriver(options);
        }
        driver = new EventFiringWebDriver(wd);
        handler = new EventHandler();
        driver.register(handler);

        wd.manage().window().maximize();

        initializeObjects();
        return driver;
    }

    /**
     * Initialize PageObjects here.
     * Any new Page object that gets created should be initialised here.
     */
    private void initializeObjects() {
        if (wd != null) {
            persistentData = new ScenarioContext();
        }
    }
}
