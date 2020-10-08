package trello.ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Login extends AbstractPageObject {
    @FindBy(css = "[autocomplete='username']")
    private WebElement userName;

    @FindBy(css = "#password")
    private WebElement password;

    @FindBy(css = "#login")
    private WebElement loginButton;

    @FindBy(css = "#login-submit")
    private WebElement loginButtonSubmit;

    public Login(WebDriver driver) {
        super(driver);
    }

    public Header login(String userName, String password) {
        action.sendText(this.userName, userName);
        action.attributeContains(loginButton, "value", "Atlassian");
        action.click(loginButton);
        action.sendText(this.password, password);
        action.click(loginButtonSubmit);
        return new Header(this.driver);
    }

}
