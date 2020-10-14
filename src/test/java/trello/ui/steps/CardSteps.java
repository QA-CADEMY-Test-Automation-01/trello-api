package trello.ui.steps;

import io.cucumber.java.DataTableType;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import trello.ui.core.Environment;
import trello.ui.pages.*;
import trello.ui.utils.CardObject;

import java.util.List;
import java.util.Map;

public class CardSteps {

    private ListCreated listCreated;
    private CardForm cardForm;
    private Card card;
    private Board board;
    private CardPopOver cardPopOver;
    private AttachMenu attachMenu;

    public CardSteps(ListCreated listCreated, Board board) {
        this.listCreated = listCreated;
        this.board = board;
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

    @Given("I create a card named {string} in the list {string}")
    public void iCreateACardNamedInTheList(String cardName, String listName) {
        this.cardForm = this.listCreated.openCardForm(listName);
        this.card = this.cardForm.createCard(cardName);
    }

    @When("I move the card {string} to list {string}")
    public void iMoveTheCardToList(String cardName, String listName) {
        this.board.moveCard(cardName, listName);
    }

    @Then("I should see the card {string} into the list {string}")
    public void iShouldSeeTheCardIntoTheList(String cardExpected, String listName) {
        String actualCard = this.listCreated.getCard(listName);
    }

    @And("I open the card {string}")
    public void iOpenTheCard(String cardName) {
        this.cardPopOver = this.listCreated.openCard(cardName);
        this.attachMenu = this.cardPopOver.openAttachMenu();
    }

    @When("I attach a file")
    public void iAttachAFile() {
        this.cardPopOver = this.attachMenu.attachFileByComputer(Environment.getInstance().getValue("$['attachment_path']"));
    }

    @Then("I should see the file name in attachments section")
    public void iShouldSeeTheFileNameInAttachmentsSection() {
        String actualAttachment = this.cardPopOver.getAttachmentName();
        String expectedAttachment = Environment.getInstance().getValue("$['attachment_name']");

        Assert.assertEquals(expectedAttachment, actualAttachment);
    }
}
