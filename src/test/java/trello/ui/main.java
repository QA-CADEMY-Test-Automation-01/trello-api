package trello.ui;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.List;

public class main {
        WebDriverManager.chromedriver().setup();

        WebDriver driver = new ChromeDriver();

        driver.navigate().to("https://www.trello.com/");

        WebElement searchText = driver.findElement(By.cssSelector(".col-lg-6>h1"));
        System.out.println(searchText.getText());

        WebElement searchButton = driver.findElement(By.cssSelector("[href=\"/login\"]"));
        searchButton.click();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        WebElement searchBox = driver.findElement(By.cssSelector("[inputmode=\"email\"]"));
        searchBox.sendKeys("Juan");

        WebElement searchBox1 = driver.findElement(By.cssSelector("[name=\"password\"]"));
        searchBox1.sendKeys("123");


        WebElement searchButton1 = driver.findElement(By.cssSelector("[id=\"login\"]"));
        searchButton1.click();

        driver.quit();
        
    }
}
