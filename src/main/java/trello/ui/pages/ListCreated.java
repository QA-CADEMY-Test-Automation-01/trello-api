package trello.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ListCreated extends AbstractPageObject {

    private String ADD_CARD_XPATH = "//textarea[text()='%s']/../following-sibling::div//a[contains(@class,'open-card-composer')]";

    private String CARD_XPATH = "//textarea[text()='%s']/../following-sibling::div//span[@class='list-card-title js-card-name']";

    private String CARD_BY_NAME_XPATH = "//div/span[@class='list-card-title js-card-name' and text() = '%s']";

    @FindBy(css = ".list-header")
    private WebElement listNameLabel;

    @FindBy(css = ".open-card-composer")
    private WebElement addCardButton;

    public String getListName() {
        return action.getText(this.listNameLabel);
    }

    public CardForm openCardForm() {
        action.click(this.addCardButton);
        return new CardForm();
    }

    public CardForm openCardForm(String listName) {
        action.click(By.xpath(String.format(this.ADD_CARD_XPATH, listName)));
        return new CardForm();
    }

    public CardPopOver openCard(String listName) {
        action.click(By.xpath(String.format(this.CARD_BY_NAME_XPATH, listName)));
        return new CardPopOver();
    }

    public String getCard(String listName) {
        return action.getText(By.xpath(String.format(CARD_XPATH, listName)));
    }
}
