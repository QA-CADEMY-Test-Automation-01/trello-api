package trello.ui.pages;

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

    @FindBy(css = "#error")
    private WebElement errorTextArea;


    public Header login(String userName, String password) {
        action.sendText(this.userName, userName);
        action.attributeContains(loginButton, "value", "Atlassian");
        action.click(loginButton);
        action.sendText(this.password, password);
        action.click(loginButtonSubmit);
        return new Header();
    }

    public void loginInSinglePage(String userName, String password) {
        action.sendText(this.userName, userName);
        action.sendText(this.password, password);
        action.click(loginButton);
    }

    public String getErrorMessage(){
        return action.getText(this.errorTextArea);
    }

}
