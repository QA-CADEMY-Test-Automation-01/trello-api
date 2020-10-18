package trello.ui.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.AfterClass;
import org.junit.runner.RunWith;
import trello.ui.core.DriverManager;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {
                "pretty",
//                "html:reports/html/index.html",
//                "de.monochromata.cucumber.report.PrettyReports:reports/cucumber",
                "json:reports/cucumber-reports/cucumber-retry.json",
        },

        features = "@reports/rerun/rerun.txt",
        glue = "trello.ui"
)
public class ReRunCucumberTests {
        @AfterClass
        public static void close(){
                DriverManager.getInstance().getDriver().quit();
        }

}
