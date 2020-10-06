package trello.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import trello.ui.core.WebDriverAction;

public class CreateMenu {
    private WebDriver driver;
    private WebDriverWait wait;
    private WebDriverAction action;
    private By createBoardOption = By.cssSelector("[data-test-id='header-create-board-button']");

    public CreateMenu(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(this.driver, 20);
        this.action = new WebDriverAction(driver, wait);
    }

    public BoardForm openCreateBoardForm(){
        action.click(createBoardOption);
        return new BoardForm(this.driver);
    }
}
