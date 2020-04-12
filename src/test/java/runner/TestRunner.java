package runner;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(
        features = {"src/test/java/features/train-schedule.feature"},
        glue = {"src/test/java/features/stepDefinitions"},
        monochrome = true,
        strict = true,
        plugin = {"json:target/cucumber-report-feature-composite.json"},
        dryRun = false,
        tags = {""}
)
public class TestRunner extends AbstractTestNGCucumberTests {

}
