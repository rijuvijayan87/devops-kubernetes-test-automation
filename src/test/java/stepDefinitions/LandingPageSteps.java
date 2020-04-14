package stepDefinitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import pageObject.LandingPageObject;

public class LandingPageSteps  {

    private LandingPageObject landingPageObject;

    public LandingPageSteps(CommonContext commonContext){
        this.landingPageObject = commonContext.getLandingPageObject();
    }

    @Given("the user is navigate successfully to the application")
    public void the_user_is_navigate_successfully_to_the_application() {
        //Assert.assertTrue(landingPageObject.checkIfPageIsLoadedSuccessfully(), "Page has not been loaded");
    }

    @And("search for {string}")
    public void searchFor(String arg0) {
        System.out.println("searching for " + arg0);
        landingPageObject.searchForText(arg0);
    }
}
