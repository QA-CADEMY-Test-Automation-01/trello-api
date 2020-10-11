package trello.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BoardForm extends AbstractPageObject {

    private String THEME_XPATH = "//button[contains(@title,'%s')]";

    @FindBy(css = "[data-test-id='create-board-title-input']")
    private WebElement boardName;

    @FindBy(css = "[data-test-id='create-board-submit-button']")
    private WebElement createButton;


    public Board createBoard(String boardName) {
        action.sendText(this.boardName, boardName);
        action.click(this.createButton);
        return new Board();
    }

    public Board createBoard(String boardName, String theme) {
        action.sendText(this.boardName, boardName);
        action.click(By.xpath(String.format(THEME_XPATH, theme)));
        action.click(this.createButton);
        return new Board();
    }

}