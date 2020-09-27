package trello.ui;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.junit.Assert;

import java.util.List;

public class main {

    private static void waitForPageLoaded(WebDriver driver, long sleep) {
        ExpectedCondition<Boolean> expectation = new
                ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver driver) {
                        return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete");
                    }
                };
        try {
//            Thread.sleep(1000);
            Thread.sleep(sleep);
            WebDriverWait wait = new WebDriverWait(driver, 30);
            wait.until(expectation);
        } catch (Throwable error) {
            Assert.fail("Timeout waiting for Page Load Request to complete.");
        }
    }

    public static void main(String[] args){

        //Configure web driver manager
        WebDriverManager.chromedriver().setup();

        //Create web driver. Open browser
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--lang=en");
        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();

        //1.- Navigate to Trello home page
        driver.navigate().to("https://trello.com/");

        //2.- Print in Cosole Main message
        WebElement welcomeMesssage = driver.findElement(By.cssSelector(".col-lg-6>h1"));
        System.out.println(welcomeMesssage.getText());

        //3.- Click Log in button
        WebElement loginHomeButton = driver.findElement(By.cssSelector("[href*='login']"));
        loginHomeButton.click();
        waitForPageLoaded(driver, 1000);

        //4.- Fill username
//        WebElement userInput = driver.findElement(By.cssSelector("#user[name='user']"));
        WebElement userInput = driver.findElement(By.cssSelector("#user"));
        userInput.sendKeys("art_ag@hotmail.com");
        //Click on Log in with Atlassian
//        WebElement loginAtlassian = driver.findElement(By.cssSelector(".button#login"));
        WebElement loginAtlassian = driver.findElement(By.cssSelector("#login"));
        loginAtlassian.click();
        waitForPageLoaded(driver, 1000);

        //5.- Fill password
        WebElement passwordInput = driver.findElement(By.cssSelector("#password"));
        passwordInput.sendKeys("12345678");

        //6.- Click on Login button
        WebElement loginButton = driver.findElement(By.cssSelector("#login-submit"));
        loginButton.click();
        waitForPageLoaded(driver, 5000);

        //7.- Get all element is sidebar
//        WebElement sibeBar = driver.findElement(By.cssSelector("._27QKbGhxDkdNHq>ul li"));
        List<WebElement> sibeBarList = driver.findElements(By.cssSelector("._27QKbGhxDkdNHq>ul li"));
        for (WebElement option : sibeBarList){
            System.out.println(option.getText());
        }

        //Close browser and kill process
        driver.quit();
    }
}
