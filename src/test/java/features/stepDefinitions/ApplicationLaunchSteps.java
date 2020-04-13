package features.stepDefinitions;

import cucumber.api.java.en.Given;
import pageObject.ApplicationPageLaunchObject;
import pageObject.LandingPageObject;

public class ApplicationLaunchSteps {

    private CommonContext commonContext;

    public ApplicationLaunchSteps(CommonContext commonContext){
        this.commonContext = commonContext;
    }

    @Given("the user wants to use train schedule application")
    public void the_user_wants_to_use_train_schedule_application() {
        ApplicationPageLaunchObject applicationPageLaunchObject = new ApplicationPageLaunchObject(commonContext.driver);
        LandingPageObject landingPageObject = applicationPageLaunchObject.launchApplication();
        commonContext.setLandingPageObject(landingPageObject);
    }
}
