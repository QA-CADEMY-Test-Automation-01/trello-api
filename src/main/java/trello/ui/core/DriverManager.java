package trello.ui.core;

import org.openqa.selenium.WebDriver;

public class DriverManager {

    private WebDriver driver;
    private static DriverManager driverManager;

    private DriverManager(){
        String browser = Environment.getInstance().getValue("$['browser']");
        this.driver = DriverFactory.createDriver(browser);
    }

    public static DriverManager getInstance(){
        if(driverManager == null){
            driverManager = new DriverManager();
        }
        return driverManager;
    }

    public WebDriver getDriver(){
        return this.driver;
    }
}
