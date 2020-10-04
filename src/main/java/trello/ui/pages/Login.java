package trello.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import trello.ui.core.WebDriverAction;

public class Login {
    private WebDriver driver;
    private WebDriverWait wait;
    private WebDriverAction action;
    private By userName = By.cssSelector("[autocomplete='username']");
    private By password = By.cssSelector("#password");
    private By loginButton = By.cssSelector("#login");
    private By loginButtonSubmit = By.cssSelector("#login-submit");


    public Login(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(this.driver, 20);
        this.action = new WebDriverAction(driver, wait);
    }

    public Header login(String userName, String password){
        action.sendText(this.userName, userName);
        action.attributeContains(loginButton, "value", "Atlassian");
        action.click(loginButton);
        action.sendText(this.password, password);
        action.click(loginButtonSubmit);
        return new Header(this.driver);
    }

}
