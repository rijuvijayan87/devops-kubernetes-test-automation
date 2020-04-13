package pageObject;

import libs.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LandingPageObject extends BasePage{
    private WebDriver driver;

    private By headerFindYourTrain = By.cssSelector("body > h1");

    protected LandingPageObject(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public void checkIfPageIsLoadedSuccessfully() {
        //return isElementVisibleAndClickable(headerFindYourTrain, 10);
    }

}
