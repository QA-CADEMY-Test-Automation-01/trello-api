package trello.ui.core;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.nio.file.WatchEvent;

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
        WebElement clickable = wait.until(ExpectedConditions.elementToBeClickable(locator));
        clickable.click();
    }
    public void click(WebElement element){
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }
    //Sends text to element given a locator
    //previously makes sure that the element is clickable through explicit wait
    public void sendText(By locator, String text){
        wait.until(ExpectedConditions.elementToBeClickable(locator));
        driver.findElement(locator).sendKeys(text);
    }
    public void sendText(WebElement element, String text){
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.sendKeys(text);
    }
    //Gets text from a given locator
    //previously verifies if element is visible
    public String getText(By locator){
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return driver.findElement(locator).getText();
    }
    public String getText(WebElement element){
        wait.until(ExpectedConditions.visibilityOf(element));
        return element.getText();
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
    public boolean isElementVisible(WebElement element){
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
            return true;
        } catch(NoSuchElementException e){
            return false;
        }
    }

    public boolean attributeContains(By locator, String attribute, String value){
        try {
            wait.until(ExpectedConditions.attributeContains(locator, attribute, value));
            return true;
        } catch(NoSuchElementException e){
            return false;
        }
    }
    public boolean attributeContains(WebElement element, String attribute, String value){
        try {
            wait.until(ExpectedConditions.attributeContains(element, attribute, value));
            return true;
        } catch(NoSuchElementException e){
            return false;
        }
    }
}
