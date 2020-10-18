package trello.ui.core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.URL;

public class BrowserStack extends AbstractDriver {
    private static final String AUTOMATE_USERNAME = "josec7";
    private static final String AUTOMATE_ACCESS_KEY = "g5p6nJVqm48saYZ7UAte";
    private static final String URL = "https://" + AUTOMATE_USERNAME + ":" + AUTOMATE_ACCESS_KEY + "@hub-cloud.browserstack.com/wd/hub";

    @Override
    WebDriver initDriver() {
        DesiredCapabilities caps = new DesiredCapabilities();

        caps.setCapability("os", "Windows");
        caps.setCapability("os_version", "10");
        caps.setCapability("browser", "Chrome");
        caps.setCapability("browser_version", "80");
        caps.setCapability("name", "Demo's First Test");
        try{
            return new RemoteWebDriver(new URL(URL), caps);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }

        return null;
    }
}
