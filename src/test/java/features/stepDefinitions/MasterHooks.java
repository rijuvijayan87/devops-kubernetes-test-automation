package features.stepDefinitions;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import libs.DriverFactory;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

public class MasterHooks {

    private WebDriver driver;
    private CommonContext commonContext;
    private DriverFactory driverFactory;

    public MasterHooks(CommonContext commonContext, DriverFactory driverFactory){
        this.commonContext = commonContext;
        this.driverFactory = driverFactory;
    }

    @Before
    public void setup(Scenario cukeScenarioObj) throws IOException {
        this.driver = driverFactory.createWebDriver();
        commonContext.setDriver(driver);
    }

    @After
    public void tearDownAndScreenShotOnFailure(Scenario cukeScenario) {
        try {
            if (driver != null && cukeScenario.isFailed()) {

            }
            if (driver != null) {
                driver.manage().deleteAllCookies();
                driver.quit();
            }
        } catch (Exception e) {
            System.out.println("Methods failed: tearDownAndScreenShotOnFailure, Exception: " + e.getMessage());
        }
    }


}
