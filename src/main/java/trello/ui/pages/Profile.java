package trello.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import trello.ui.core.WebDriverAction;

public class Profile {
    private WebDriver driver;
    private WebDriverWait wait;
    private WebDriverAction action;
    private By emailLabel = By.xpath("//section[@data-test-id='header-member-menu-popover']/descendant::ul/div/div/span");


    public Profile(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(this.driver, 20);
        this.action = new WebDriverAction(driver, wait);
    }

    public String getEmailAccount(){
        return action.getText(emailLabel);
    }
}
