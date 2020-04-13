package features.stepDefinitions;

import cucumber.api.java.en.Given;
import pageObject.ApplicationPageLaunchObject;
import pageObject.LandingPageObject;

public class ApplicationLaunchSteps {

    private DriverFactory driverFactory;

    public ApplicationLaunchSteps(DriverFactory driverFactory){
        this.driverFactory = driverFactory;
    }

    @Given("the user wants to use train schedule application")
    public void the_user_wants_to_use_train_schedule_application() {
        ApplicationPageLaunchObject applicationPageLaunchObject = new ApplicationPageLaunchObject(driverFactory.driver);
        LandingPageObject landingPageObject = applicationPageLaunchObject.launchApplication();
    }
}
