package features.stepDefinitions;

import cucumber.api.java.en.Given;
import features.Pages;
import org.testng.Assert;
import pageObject.ApplicationPageLaunchObject;

public class ApplicationLaunchSteps extends Pages {

    @Given("the user wants to use train schedule application")
    public void the_user_wants_to_use_train_schedule_application() {
        applicationLaunchObject = new ApplicationPageLaunchObject(driver);
        landingPageObject = applicationLaunchObject.launchApplication();
    }
}
