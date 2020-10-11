package trello.ui.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import trello.ui.core.DriverManager;

@RunWith(Cucumber.class)
@CucumberOptions(
//        plugin = {
//                "pretty",
//                "html:reports/html/index.html",
//                "de.monochromata.cucumber.report.PrettyReports:reports/cucumber",
//                "json:reports/cucumber-report/cucumber.json"
//        },
        features = "src/test/resources/features-ui",
        glue = "trello.ui",
        tags = "@board"
)
public class RunCucumberTests {
        @AfterClass
        public static void close(){
                DriverManager.getInstance().getDriver().quit();
        }
}
