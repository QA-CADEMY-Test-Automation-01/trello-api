package trello.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Board extends AbstractPageObject {

    private String LIST_XPATH = "//textarea[text()='%s']";

    private String CARD_XPATH = "//span[text()='%s']";

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

    public void moveCard(String cardSource, String listTarget) {
        action.dragAndDrop(By.xpath(String.format(CARD_XPATH, cardSource)), By.xpath(String.format(LIST_XPATH, listTarget)));
    }
}
