package trello.ui.pages;

import org.checkerframework.checker.units.qual.C;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CardForm extends AbstractPageObject {

    @FindBy(css = ".js-card-title")
    private WebElement titleCardField;

    @FindBy(css = ".js-add-card")
    private WebElement addButton;

    public CardForm(WebDriver driver) {
        super(driver);
    }

    public Card createCard(String cardName) {
        action.sendText(this.titleCardField, cardName);
        action.click(this.addButton);
        return new Card(this.driver);
    }
}
