package trello.ui.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Board extends AbstractPageObject {

    @FindBy(css = ".mod-board-name")
    private WebElement boardName;

    @FindBy(css = ".open-add-list")
    private WebElement addListButton;

    @FindBy(css = ".mod-board-name")
    private WebElement boardNameLabel;

    public String getBoardName() {
        return action.getText(this.boardName);
    }

    public ListForm openListForm() {
        action.isElementVisible(this.boardNameLabel);
        driver.navigate().refresh();
        action.click(this.addListButton);
        return new ListForm();
    }
}
