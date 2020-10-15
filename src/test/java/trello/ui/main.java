package trello.ui;

import io.cucumber.java.bs.A;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import trello.ui.core.DriverManager;
import trello.ui.core.WebDriverAction;
import trello.ui.pages.Header;
import trello.ui.pages.Login;
import trello.ui.pages.Welcome;

import java.util.List;

public class main {
    public static void main(String[] args){
        //Configure driver manually
//        System.setProperty("webdriver.chrome.driver", "d:\\otros\\webUI\\chromedriver_win32\\chromedriver.exe");
        //Configure web driver manager
//        WebDriverManager.chromedriver().setup();

        //Create web driver
        //Open browser
        WebDriver driver = DriverManager.getInstance().getDriver();
        //Navigate
//        driver.get("https://www.ebay.com/");


        WebDriverWait wait = new WebDriverWait(driver, 20);
        WebDriverAction action = new WebDriverAction(driver, wait);
//        driver.navigate().to("https://trello.com/");
        //Close browser
//        driver.close();
        //Find element
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
        Welcome welcome = new Welcome();
        Login login = welcome.login();
        Header header = login.login("joseccb1948@yahoo.com", "Control*1234");

        WebElement profile = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-test-id='header-member-menu-button']")));
        driver.navigate().to("https://trello.com/b/LMdSVV6i/welcome-to-trello");

        WebElement boardsButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-test-id='board-views-switcher-button']")));
        WebElement searchBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-test-id='header-search-input']")));
        WebElement cardTemplates = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//textarea[text()='My new list']/parent::div/following-sibling::div/*[contains(@class,'js-card-templates-button')]/descendant::span")));
        WebElement listTarget = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='board']/div[3]")));
        WebElement cardSource = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='test']")));
//        WebElement card = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Implement login']")));
        WebElement boardMenuButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-test-id='header-boards-menu-button']")));


//        WebElement boardMenuButton = (WebElement) javascriptExecutor.executeScript("document.getElementsByClassName('MEu8ZECLGMLeab')[0]");
        javascriptExecutor.executeScript("arguments[0].click();", boardMenuButton);
        WebElement boardMenu = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-test-id='header-boards-menu-popover']")));

        WebElement overflowNotSet = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("._1k52pNENP7tpiK")));
        javascriptExecutor.executeScript("arguments[0].scrollTop = arguments[0].scrollHeight", overflowNotSet);

        WebElement overflowHidden = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".khihAkM9Ux6EHW")));
        javascriptExecutor.executeScript("arguments[0].scrollTop = arguments[0].scrollHeight", overflowHidden);

        WebElement overflowSet = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-test-id='header-boards-menu-popover']")));
        javascriptExecutor.executeScript("arguments[0].scrollTop = arguments[0].scrollHeight", overflowSet);
//        javascriptExecutor.executeScript("arguments[0].scrollTop = arguments[0].scrollHeight - arguments[0].clientHeight", boardsScrollable);
        javascriptExecutor.executeScript("arguments[0].scrollTop = 0", overflowSet);
//        javascriptExecutor.executeScript("$(\"#content\").animate({scrollTop:\"300px\"})");
//        javascriptExecutor.executeScript("window.scrollBy(0,600)","");

//        Actions actions = new Actions(driver);
//
//        actions.dragAndDrop(cardSource, listTarget).perform();
//
//        actions.contextClick(card).perform();
//
//        actions.moveToElement(boardsButton);
////        actions.moveToElement(boardsButton).build().perform();
//        actions.moveToElement(boardsButton).perform();

//        Action actionSeries = actions.build();
//        actionSeries.perform();
//
//        actionSeries.perform();


//        actions.sendKeys(searchBox, "tes two sequence ...").moveToElement(cardTemplates).perform();
//        actionSeries.perform();

//        actions.moveToElement(cardTemplates).perform();

//        actionSeries = actions.build();
//        actionSeries.perform();

//        String tooltipText  = action.getText(By.cssSelector(".Tooltip"));


//        System.out.println(tooltipText);



//        WebElement searchBox = driver.findElement(By.cssSelector("#gh-ac"));
//        searchBox.sendKeys("Cellphones");
//        searchBox.clear();
//        searchBox.sendKeys("Laptops");
//        //Click search
//        WebElement searchButton = driver.findElement(By.cssSelector("#gh-btn"));
//        searchButton.click();
//        //Close browser and kill process
//
//        //Get list of elements
//        List<WebElement> linksList = driver.findElements(By.cssSelector("#gh-topl>li"));
//        for (WebElement link: linksList) {
//            System.out.println(link.getText());
//        }
        driver.quit();
    }
}
