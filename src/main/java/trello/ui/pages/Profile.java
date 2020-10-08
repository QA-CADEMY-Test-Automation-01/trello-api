package trello.ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Profile extends AbstractPageObject {
    @FindBy(xpath = "//section[@data-test-id='header-member-menu-popover']/descendant::ul/div/div/span")
    private WebElement emailLabel;

    public Profile(WebDriver driver) {
        super(driver);
    }

    public String getEmailAccount() {
        return action.getText(emailLabel);
    }
}
