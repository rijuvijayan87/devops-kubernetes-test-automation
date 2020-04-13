package features.stepDefinitions;

import io.github.bonigarcia.wdm.WebDriverManager;
import libs.PropertyFiles;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.UnreachableBrowserException;
import pageObject.LandingPageObject;
import persistence.ScenarioContext;

import java.net.MalformedURLException;
import java.net.URL;

public class CommonContext {
    protected WebDriver driver;
    protected ScenarioContext persistentData;
    private PropertyFiles propertyFiles = new PropertyFiles();
    private LandingPageObject landingPageObject;

    protected WebDriver createWebDriver() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        if (propertyFiles.getPropertyValue("EXECUTIONONSERVER").equalsIgnoreCase("true")) {

            if (propertyFiles.getPropertyValue("BROWSER").equalsIgnoreCase("chrome")) {
                capabilities = DesiredCapabilities.chrome();
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--remote-debugging-port=9222");
                options.setExperimentalOption("useAutomationExtension", false);
                if (propertyFiles.getPropertyValue("HEADLESS").equalsIgnoreCase("true")) {
                    options.addArguments("HEADLESS");
                }
                capabilities.setCapability(ChromeOptions.CAPABILITY, options);
                capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
            } else if (propertyFiles.getPropertyValue("BROWSER").equalsIgnoreCase("firefox")) {
                capabilities = DesiredCapabilities.firefox();
            } else if (propertyFiles.getPropertyValue("BROWSER").equalsIgnoreCase("ie")) {
                capabilities = DesiredCapabilities.internetExplorer();
            }
            capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
            try {
                this.driver = new RemoteWebDriver(new URL(propertyFiles.getPropertyValue("selenium_local_server")), capabilities);
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

    public void setLandingPageObject(LandingPageObject landingPageObject){
        this.landingPageObject = landingPageObject;
    }

    public LandingPageObject getLandingPageObject(){
        return landingPageObject;
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
