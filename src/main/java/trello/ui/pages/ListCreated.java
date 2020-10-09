package trello.ui.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ListCreated extends AbstractPageObject {

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
}
