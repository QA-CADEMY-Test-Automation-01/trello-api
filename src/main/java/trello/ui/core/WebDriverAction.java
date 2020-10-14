package trello.ui.core;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

import java.nio.file.WatchEvent;

public class WebDriverAction {

    private WebDriver driver;
    private WebDriverWait wait;
    private Actions actions;

    public WebDriverAction(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        this.actions =  new Actions(this.driver);
    }

    //Clicks element given a locator
    //previously makes sure that the element is clickable through explicit wait
    public void click(By locator) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        element.click();
    }

    public void click(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

    //Sends text to element given a locator
    //previously makes sure that the element is clickable through explicit wait
    public void sendText(By locator, String text) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        element.sendKeys(text);
    }

    public void sendText(WebElement element, String text) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.sendKeys(text);
    }

    public void setText(WebElement element, String text) {
//        wait.until(ExpectedConditions.visibilityOf(element));
        element.sendKeys(text);
    }

    //Gets text from a given locator
    //previously verifies if element is visible
    public String getText(By locator) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return element.getText();
    }

    public String getText(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
        return element.getText();
    }

    //Waits for element to be visible
    public boolean isElementVisible(By locator) {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean isElementVisible(WebElement element) {
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean isElementPresent(By locator) {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(locator));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
//        }catch (NoSuchElementException e) {
//            return false;
//        }
    }

    public boolean attributeContains(By locator, String attribute, String value) {
        try {
            wait.until(ExpectedConditions.attributeContains(locator, attribute, value));
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean attributeContains(WebElement element, String attribute, String value) {
        try {
            wait.until(ExpectedConditions.attributeContains(element, attribute, value));
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void dragAndDrop(By source, By target){
        WebElement sourceElement = wait.until(ExpectedConditions.elementToBeClickable(source));
        WebElement targetElement = wait.until(ExpectedConditions.elementToBeClickable(target));
        this.actions.dragAndDrop(sourceElement, targetElement);
        Action action = actions.build();
        action.perform();
    }
}
