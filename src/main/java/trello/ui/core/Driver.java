package trello.ui.core;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Driver {
    public WebDriver initDriver(){
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver();
    }
}
