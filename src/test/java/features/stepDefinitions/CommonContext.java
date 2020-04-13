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
    private WebDriver driver;
    private LandingPageObject landingPageObject;

    public WebDriver getDriver() {
        return driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    public void setLandingPageObject(LandingPageObject landingPageObject){
        this.landingPageObject = landingPageObject;
    }

    public LandingPageObject getLandingPageObject(){
        return landingPageObject;
    }



}
