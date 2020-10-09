package trello.ui.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreateMenu extends AbstractPageObject {

    @FindBy(css = "[data-test-id='header-create-board-button']")
    private WebElement createBoardOption;

    public BoardForm openCreateBoardForm() {
        action.click(this.createBoardOption);
        return new BoardForm();
    }
}
