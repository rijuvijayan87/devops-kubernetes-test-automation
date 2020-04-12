package pageObject;

import libs.BasePage;
import org.openqa.selenium.WebDriver;

public class ApplicationPageLaunchObject extends BasePage {

    public ApplicationPageLaunchObject(WebDriver driver) {
        super(driver);
    }

    public LandingPageObject launchApplication() {
        loadUrl(getEnvironment().getAppURL());
        return new LandingPageObject(getDriver());
    }
}
