package trello.ui.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import trello.ui.utils.CardObject;

public class CardForm extends AbstractPageObject {

    @FindBy(css = ".js-cancel")
    private WebElement cancelButton;

    @FindBy(css = ".js-card-title")
    private WebElement titleCardField;

    @FindBy(css = ".js-add-card")
    private WebElement addButton;

    public Card createCard(String cardName) {
        action.sendText(this.titleCardField, cardName);
        action.click(this.addButton);
        return new Card();
    }
    public Card createCard(CardObject cardObject) {
        action.sendText(this.titleCardField, cardObject.getTitle());
        action.click(this.addButton);
        action.click(cancelButton);
        return new Card();
    }
}
