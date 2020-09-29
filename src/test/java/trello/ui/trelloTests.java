package trello.ui;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class trelloTests {
    public WebDriver driver;

    @Before
    public void setUp(){
        //Configure web driver manager
        WebDriverManager.chromedriver().setup();
        //Headless Mode
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless");
//        chromeOptions.setHeadless(true);
        driver = new ChromeDriver(chromeOptions);
        driver.manage().window().setSize(new Dimension(1920, 1080));

        //Create driver adn open browser
//        driver = new ChromeDriver();
    }

    @After
    public void tearDown(){
        //Close browser
        driver.quit();
    }

    @Test
    public void testLogin() {
        try {
//            driver.manage().window().maximize();
            driver.get("https://trello.com/");
            sleep(3);
            //Click login button
            WebElement loginHomeButton = driver.findElement(By.cssSelector("[href*='login']"));
            loginHomeButton.click();
            //Set username
            WebElement nameField = driver.findElement(By.cssSelector("[autocomplete='username']"));
            nameField.sendKeys("joseccb1948@yahoo.com");
            //Click login with Atlasian
            WebElement loginAtlasianButton = driver.findElement(By.cssSelector("#login"));
            loginAtlasianButton.click();
        sleep(3);
            //Set password
            WebElement passwordField = driver.findElement(By.cssSelector("#password"));
            passwordField.sendKeys("Control*1234");
            //Click login
            WebElement loginButton = driver.findElement(By.cssSelector("#login-submit"));
            loginButton.click();
        sleep(15);
            //Click profile button
            WebElement profileButton = driver.findElement(By.cssSelector("[data-test-id='header-member-menu-button']"));
            profileButton.click();

        sleep(2);
            //Get account email
            WebElement accountLabel = driver.findElement(By.xpath("//section[@data-test-id='header-member-menu-popover']/descendant::ul/div/div/span"));
            String accountEmail = accountLabel.getText();
            //Print current page data
            String title = driver.getTitle();
            String url = driver.getCurrentUrl();
            System.out.println("Current title: " + title);
            System.out.println("Current URL: " + url);

            //Validate username displayed in profile button
            String expectedEmail = "joseccb1948@yahoo.com";
            Assert.assertEquals(expectedEmail, accountEmail);
        } catch(NoSuchElementException e){
            System.out.println("Test failed because of not found element: \n" + e.getMessage());
            Assert.assertTrue(false);
        }
    }

    public void sleep(int timeInSeconds){
        try{
            Thread.sleep(timeInSeconds*1000);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void login(){
        driver.get("https://trello.com/");
        sleep(3);
        //Click login button
        WebElement loginHomeButton = driver.findElement(By.cssSelector("[href='/login']"));
        loginHomeButton.click();
        //Set username
        WebElement nameField = driver.findElement(By.cssSelector("[autocomplete='username']"));
        nameField.sendKeys("joseccb1948@yahoo.com");
        //Click login with Atlasian
        WebElement loginAtlasianButton = driver.findElement(By.cssSelector("#login"));
        loginAtlasianButton.click();
        sleep(3);
        //Set password
        WebElement passwordField = driver.findElement(By.cssSelector("#password"));
        passwordField.sendKeys("Control*1234");
        //Click login
        WebElement loginButton = driver.findElement(By.cssSelector("#login-submit"));
        loginButton.click();
        sleep(20);
    }
}
