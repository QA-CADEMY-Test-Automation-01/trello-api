package trello.ui.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import trello.ui.pages.*;

public class BoardsSteps {
    private Header header;
    private CreateMenu createMenu;
    private BoardForm boardForm;
    private Board board;

    public BoardsSteps(Header header){
        this.header = header;
    }

    @Given("I open board creation form")
    public void iOpenBoardCreationForm(){
        this.createMenu = this.header.openCreateMenu();
    }

    @When("I create a board with title {string}")
    public void iCreateABoardWithTitle(String boardTitle) {
        this.boardForm = this.createMenu.openCreateBoardForm();
        this.board = this.boardForm.createBoard(boardTitle);
    }

    @Then("I should see board page loaded with {string}")
    public void iShouldSeeBoardPageLoaded(String expectedTitle) {
        String actualTitle = this.board.getBoardName();
        Assert.assertEquals(expectedTitle, actualTitle);
    }
}
