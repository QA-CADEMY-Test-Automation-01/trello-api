package trello.ui.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AttachMenu extends AbstractPageObject {
    @FindBy(css = ".js-attach-file")
    WebElement computerButton;

    public CardPopOver attachFileByComputer(String path) {
        action.setText(computerButton, path);
        return new CardPopOver();
    }
}
