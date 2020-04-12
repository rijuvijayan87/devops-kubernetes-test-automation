package features.stepDefinitions;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import features.Pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.io.IOException;

public class MasterHooks extends Pages {
    private EventFiringWebDriver driver;

    @Before
    public void setup(Scenario cukeScenarioObj) throws IOException {
        driver = createWebDriver();
        setWebDriver(driver);
        persistentData.setContext("testCase", cukeScenarioObj.getName());
    }

    @After
    public void tearDownAndScreenShotOnFailure(Scenario cukeScenario) {
        try {
            if (driver != null && cukeScenario.isFailed()) {

            }
            if (driver != null) {
                driver.unregister(handler);
                driver.manage().deleteAllCookies();
                driver.quit();
            }
        } catch (Exception e) {
            System.out.println("Methods failed: tearDownAndScreenShotOnFailure, Exception: " + e.getMessage());
        }
    }


}
