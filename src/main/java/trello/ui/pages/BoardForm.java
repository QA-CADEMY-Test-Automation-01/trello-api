package trello.ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BoardForm extends AbstractPageObject{
    @FindBy(css = "[data-test-id='create-board-title-input']")
    private WebElement boardName;

    @FindBy(css = "[data-test-id='create-board-submit-button']")
    private WebElement createButton;

    public BoardForm(WebDriver driver){
        super(driver);
    }

    public Board createBoard(String boardName){
        action.sendText(this.boardName, boardName);
        action.click(createButton);
        return new Board(this.driver);
    }

}