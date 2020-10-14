package trello.ui.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreateMenu extends AbstractPageObject {

    @FindBy(css = "[data-test-id='header-create-board-button']")
    private WebElement createBoardOption;

    @FindBy(css = "[data-test-id='header-create-team-button']")
    private WebElement createTeamOption;

    public BoardForm openCreateBoardForm() {
        action.click(this.createBoardOption);
        return new BoardForm();
    }

    public TeamForm openCreateTeamForm(){
        action.click(createTeamOption);
        return new TeamForm();
    }
}
