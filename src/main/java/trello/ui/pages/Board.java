package trello.ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Board extends AbstractPageObject {
    @FindBy(css = ".mod-board-name")
    private WebElement boardName;

    @FindBy(css = ".open-add-list")
    private WebElement addListButton;

    @FindBy(css = ".mod-board-name")
    private WebElement boardNameLabel;

    public Board(WebDriver driver){
        super(driver);
    }

    public String getBoardName(){
        return action.getText(boardName);
    }

    public ListForm openListForm(){
        action.isElementVisible(boardNameLabel);
        driver.navigate().refresh();
        action.click(addListButton);
        return new ListForm(this.driver);
    }
}
