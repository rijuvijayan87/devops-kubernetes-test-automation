package features;

import libs.DriverFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import pageObject.ApplicationPageLaunchObject;
import pageObject.LandingPageObject;

public abstract class Pages extends DriverFactory {
    protected EventFiringWebDriver driver;
    protected ApplicationPageLaunchObject applicationLaunchObject;
    protected LandingPageObject landingPageObject;

    protected void setWebDriver(EventFiringWebDriver driver) {
        this.driver = driver;
    }

}
