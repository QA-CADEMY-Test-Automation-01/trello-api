package trello.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import trello.ui.core.WebDriverAction;

public class BoardForm {
    private WebDriver driver;
    private WebDriverWait wait;
    private WebDriverAction action;
    private By boardName = By.cssSelector("[data-test-id='create-board-title-input']");
    private By createButton = By.cssSelector("[data-test-id='create-board-submit-button']");

    public BoardForm(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(this.driver, 20);
        this.action = new WebDriverAction(driver, wait);
    }

    public Board createBoard(String boardName){
        action.sendText(this.boardName, boardName);
        action.click(createButton);
        return new Board(this.driver);
    }

}
