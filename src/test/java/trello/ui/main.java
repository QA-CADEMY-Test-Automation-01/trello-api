package trello.ui;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.List;

public class main {
    public static void main(String[] args){
        //Configure driver manually
//        System.setProperty("webdriver.chrome.driver", "d:\\otros\\webUI\\chromedriver_win32\\chromedriver.exe");
        //Configure web driver manager
        WebDriverManager.chromedriver().setup();

        //Create web driver
        //Open browser
        WebDriver driver = new ChromeDriver();
        //Navigate
//        driver.get("https://www.ebay.com/");
        driver.navigate().to("https://www.ebay.com/");
        //Close browser
//        driver.close();
        //Find element
        WebElement searchBox = driver.findElement(By.cssSelector("#gh-ac"));
        searchBox.sendKeys("Cellphones");
        searchBox.clear();
        searchBox.sendKeys("Laptops");
        //Click search
        WebElement searchButton = driver.findElement(By.cssSelector("#gh-btn"));
        searchButton.click();
        //Close browser and kill process

        //Get list of elements
        List<WebElement> linksList = driver.findElements(By.cssSelector("#gh-topl>li"));
        for (WebElement link: linksList) {
            System.out.println(link.getText());
        }

        driver.quit();
    }
}
