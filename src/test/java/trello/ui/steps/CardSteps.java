package trello.ui.steps;

import io.cucumber.java.DataTableType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import trello.ui.pages.Card;
import trello.ui.pages.CardForm;
import trello.ui.pages.ListCreated;
import trello.ui.utils.CardObject;

import java.util.List;
import java.util.Map;

public class CardSteps {

    private ListCreated listCreated;
    private CardForm cardForm;
    private Card card;

    public CardSteps(ListCreated listCreated) {
        this.listCreated = listCreated;
    }

    @DataTableType
    public CardObject getCardDetails(Map<String, String> cardTitle){
        return new CardObject(cardTitle.get("Title"));
    }

    @Given("I create the following cards")
    public void iCreateTheFollowingCards(List<CardObject> cards) {
        for (CardObject cardObject: cards) {
            this.cardForm = listCreated.openCardForm();
            this.card = cardForm.createCard(cardObject);
        }

    }

    @Then("I see the following cards")
    public void iSeeTheFollowingCards(List<CardObject> cards) {
        for (CardObject cardObject: cards) {
            String actualName = this.card.getCardByTitle(cardObject);
            String expectedName = cardObject.getTitle();
            Assert.assertEquals(actualName, expectedName);
        }

    }
}
