package trello.ui.core;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverAction {
    WebDriver driver;
    WebDriverWait wait;
    public WebDriverAction(WebDriver driver, WebDriverWait wait){
        this.driver = driver;
        this.wait = wait;
    }
    //Clicks element given a locator
    //previously makes sure that the element is clickable through explicit wait
    public void click(By locator){
        wait.until(ExpectedConditions.elementToBeClickable(locator));
        driver.findElement(locator).click();
    }
    //Sends text to element given a locator
    //previously makes sure that the element is clickable through explicit wait
    public void sendText(By locator, String text){
        wait.until(ExpectedConditions.elementToBeClickable(locator));
        driver.findElement(locator).sendKeys(text);
    }
    //Gets text from a given locator
    //previously verifies if element is visible
    public String getText(By locator){
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return driver.findElement(locator).getText();
    }
    //Waits for element to be visible
    public boolean isElementVisible(By locator){
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            return true;
        } catch(NoSuchElementException e){
            return false;
        }
    }
}
