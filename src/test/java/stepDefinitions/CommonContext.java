package stepDefinitions;

import org.openqa.selenium.WebDriver;
import pageObject.LandingPageObject;

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
