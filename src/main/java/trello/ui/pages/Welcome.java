package trello.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Welcome extends AbstractPageObject {
    @FindBy(css = "[href*='login']")
    private WebElement loginButton;

    public Welcome(WebDriver driver){
        super(driver);
        this.driver.get("https://trello.com/");
    }

    public Login login(){
        action.click(loginButton);
        return new Login(this.driver);
    }
}
