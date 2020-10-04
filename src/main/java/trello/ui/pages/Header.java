package trello.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import trello.ui.core.WebDriverAction;

public class Header {
    private WebDriver driver;
    private WebDriverWait wait;
    private WebDriverAction action;
    private By profileButton = By.cssSelector("[data-test-id='header-member-menu-button']");


    public Header(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(this.driver, 20);
        this.action = new WebDriverAction(driver, wait);
    }

    public Profile goToProfileMenu(){
        action.click(profileButton);
        return new Profile(this.driver);
    }
}
