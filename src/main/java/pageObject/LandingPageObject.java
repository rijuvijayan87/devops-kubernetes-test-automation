package pageObject;

import libs.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LandingPageObject extends BasePage{
    private WebDriver driver;

    private By headerFindYourTrain = By.cssSelector("body > h1");
    private By searchText = By.name("q");
    private By searchButton = By.name("btnK");


    protected LandingPageObject(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public void searchForText(String value){
        sendTextIntoElement(searchText, value);
        clickOnElement(searchButton);
    }

}
