package trello.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import trello.ui.core.WebDriverAction;

public class Welcome {
    private WebDriver driver;
    private WebDriverWait wait;
    private WebDriverAction action;
    private By loginButton = By.cssSelector("[href*='login']");

    public Welcome(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(this.driver, 20);
        this.action = new WebDriverAction(driver, wait);
        driver.get("https://trello.com/");
    }

    public Login login(){
        action.click(loginButton);
        return new Login(this.driver);
    }
}
