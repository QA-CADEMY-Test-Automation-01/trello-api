package trello.ui.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Welcome extends AbstractPageObject {
    @FindBy(css = "[href*='login']")
    private WebElement loginButton;

    public Welcome() {
        this.driver.get("https://trello.com/");
    }

    public Login login() {
        action.click(loginButton);
        return new Login();
    }
}
