package trello.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import trello.ui.core.WebDriverAction;

public class Board {
    private WebDriver driver;
    private WebDriverWait wait;
    private WebDriverAction action;
    private By boardName = By.cssSelector(".mod-board-name");

    public Board(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(this.driver, 20);
        this.action = new WebDriverAction(driver, wait);
    }

    public String getBoardName(){
        return action.getText(boardName);
    }
}
