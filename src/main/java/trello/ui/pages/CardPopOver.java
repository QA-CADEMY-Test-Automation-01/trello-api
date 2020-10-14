package trello.ui.pages;

import io.cucumber.java.bs.A;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CardPopOver extends AbstractPageObject {

    @FindBy(css = ".button-link.js-attach")
    private WebElement attachButton;

    @FindBy(css = ".attachment-thumbnail-name")
    private WebElement attachmentLabel;


    public AttachMenu openAttachMenu() {
        action.click(attachButton);
        return new AttachMenu();
    }

    public String getAttachmentName(){
        return action.getText(attachmentLabel);
    }
}
