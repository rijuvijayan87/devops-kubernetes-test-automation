package pageObject;

import libs.BasePage;
import org.openqa.selenium.WebDriver;

public class ApplicationPageLaunchObject extends BasePage{
    private WebDriver driver;

    public ApplicationPageLaunchObject(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public LandingPageObject launchApplication() {
        loadUrl(getEnvironment().getAppURL());
        return new LandingPageObject(this.driver);
    }
}
