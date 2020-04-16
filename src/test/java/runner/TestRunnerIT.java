package runner;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        features = {"src/test/resources/features"},
        glue = {"stepDefinitions"},
        monochrome = true,
        strict = true,
        //plugin = {"json:target/cucumber-report/cucumber.json"},
        plugin = {"pretty", "com.epam.reportportal.cucumber.StepReporter"},
        dryRun = false,
        tags = {""}
)
public class TestRunnerIT extends AbstractTestNGCucumberTests {


    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
