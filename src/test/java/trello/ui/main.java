package trello.ui;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class main {
    public static void main(String[] args){
        //Configure driver manually
//        System.setProperty("webdriver.chrome.driver", "d:\\otros\\webUI\\chromedriver_win32\\chromedriver.exe");
        //Configure web driver manager
        WebDriverManager.firefoxdriver().setup();

        //Create web driver
        //Open browser
        WebDriver driver = new FirefoxDriver();
        driver.get("https://www.ebay.com/");

        //Close browser
//        driver.close();
        //Close browser and kill process
        driver.quit();
    }
}
