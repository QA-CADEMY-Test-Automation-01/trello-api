package trello.ui.hooks;

import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import org.junit.AfterClass;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import trello.ui.core.DriverManager;

public class Hooks {
    //    @After
//    public void close(){
//        DriverManager.getInstance().getDriver().quit();
//    }
    @After
    public static void takeScreenshot(Scenario scenario) {
        if (scenario.isFailed()) {
            WebDriver driver = DriverManager.getInstance().getDriver();
            final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "screenshot");
        }
    }


    @AfterClass
    public static void close(){
        DriverManager.getInstance().getDriver().quit();
    }
}
