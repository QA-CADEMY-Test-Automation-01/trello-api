package trello.api.cucumber;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {
                "pretty",
                "html:reports/html/index.html",
                "de.monochromata.cucumber.report.PrettyReports:reports/cucumber",
                "json:reports/cucumber-report/cucumber.json"
        },
        features = "src/test/resources/features",
        glue = "trello.api.cucumber",
        tags = "@Test"
)
public class RunnerTest {
}
