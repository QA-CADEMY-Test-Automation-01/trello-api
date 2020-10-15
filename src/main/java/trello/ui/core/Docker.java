package trello.ui.core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;

public class Docker extends AbstractDriver {
    @Override
    WebDriver initDriver() {
        WebDriver driver = null;
        try{
            ChromeOptions chromeOptions = new ChromeOptions();
            driver = new RemoteWebDriver(new URL("http://192.168.31.183:4444/wd/hub"), chromeOptions);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return driver;
    }
}
