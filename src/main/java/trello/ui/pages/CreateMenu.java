package trello.ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreateMenu extends AbstractPageObject {

    @FindBy(css = "[data-test-id='header-create-board-button']")
    private WebElement createBoardOption;

    public CreateMenu(WebDriver driver) {
        super(driver);
    }

    public BoardForm openCreateBoardForm() {
        action.click(this.createBoardOption);
        return new BoardForm(this.driver);
    }
}
