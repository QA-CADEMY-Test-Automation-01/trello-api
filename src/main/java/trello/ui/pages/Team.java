package trello.ui.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Team extends AbstractPageObject {
    @FindBy(css = ".js-org-account")
    private WebElement settingsButton;

    public TeamSettings goSettings(){
        action.click(settingsButton);
        return new TeamSettings();
    }
}
