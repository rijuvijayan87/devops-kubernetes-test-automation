package features.stepDefinitions;

import cucumber.api.java.en.Given;
import features.Pages;
import org.testng.Assert;

public class LandingPageSteps extends Pages {

    @Given("the user is navigate successfully to the application")
    public void the_user_is_navigate_successfully_to_the_application() {
        //Assert.assertTrue(landingPageObject.checkIfPageIsLoadedSuccessfully(), "Page has not been loaded");
    }
}
