package trello.ui.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CardForm extends AbstractPageObject {

    @FindBy(css = ".js-card-title")
    private WebElement titleCardField;

    @FindBy(css = ".js-add-card")
    private WebElement addButton;

    public Card createCard(String cardName) {
        action.sendText(this.titleCardField, cardName);
        action.click(this.addButton);
        return new Card();
    }
}
