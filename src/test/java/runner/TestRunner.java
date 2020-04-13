package runner;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        features = {"src/test/java/features/train-schedule.feature"},
        glue = {"features.stepDefinitions"},
        monochrome = true,
        strict = true,
        plugin = {"json:target/cucumber-report/cucumber.json"},
        dryRun = false,
        tags = {""}
)
public class TestRunner extends AbstractTestNGCucumberTests {


    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
