package trello.ui.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ListForm extends AbstractPageObject {
    @FindBy(css = ".mod-list-add-button")
    WebElement createButton;

    @FindBy(css = ".list-name-input")
    WebElement listName;


    public ListCreated createList(String listName) {
        action.sendText(this.listName, listName);
        action.click(createButton);
        return new ListCreated();
    }
}
