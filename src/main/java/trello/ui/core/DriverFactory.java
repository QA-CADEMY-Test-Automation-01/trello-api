package trello.ui.core;

import org.openqa.selenium.WebDriver;

import java.util.HashMap;
import java.util.Map;

public class DriverFactory {

    private static Map<String, AbstractDriver> DRIVERS = new HashMap<>();
    static {
        DRIVERS.put("chrome", new Chrome());
        DRIVERS.put("firefox", new Firefox());
        DRIVERS.put("headless", new ChromeHeadless());
        DRIVERS.put("docker", new Docker());
        DRIVERS.put("browserstack", new BrowserStack());
    }

    public static WebDriver createDriver(String browser){
        return DRIVERS.get(browser).initDriver();
    }
}
