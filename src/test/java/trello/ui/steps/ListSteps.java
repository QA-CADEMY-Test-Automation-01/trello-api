package trello.ui.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import trello.ui.pages.Board;
import trello.ui.pages.ListCreated;
import trello.ui.pages.ListForm;

public class ListSteps {
    private Board board;
    private ListForm listForm;
    private ListCreated listCreated;

    public ListSteps(Board board) {
        this.board = board;
    }

    @Given("I open list creation form")
    public void iOpenListCreationForm() {
        this.listForm = board.openListForm();
    }

    @And("I create the list with name {string}")
    public void iCreateTheListWithName(String listTitle) {
        this.listCreated = listForm.createList(listTitle);
    }

    @Then("I should see the title {string} in list")
    public void iShouldSeeTheTitleInList(String expectedListTitle) {
        String actualListTitle = this.listCreated.getListName();
        Assert.assertEquals(expectedListTitle, actualListTitle);
    }
}
