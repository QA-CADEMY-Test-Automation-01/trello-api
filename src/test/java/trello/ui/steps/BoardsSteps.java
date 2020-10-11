package trello.ui.steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import trello.ui.pages.*;

import java.util.List;
import java.util.Map;

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

    //DataTable using list of lists
//    @When("I create a board with following data")
//    public void iCreateABoardWithFollowingData(List<List<String>> boardData) {
//        String boardTitle = boardData.get(0).get(1);
//        String boardTheme = boardData.get(1).get(1);
//
//        this.boardForm = this.createMenu.openCreateBoardForm();
//        this.board = this.boardForm.createBoard(boardTitle, boardTheme);
//
//    }

    //DataTable using list of Maps
//    @When("I create a board with following data")
//    public void iCreateABoardWithFollowingData(List<Map<String, String>> boardData) {
//        String boardTitle = boardData.get(0).get("Title");
//        String boardTheme = boardData.get(0).get("Theme");
//
//        this.boardForm = this.createMenu.openCreateBoardForm();
//        this.board = this.boardForm.createBoard(boardTitle, boardTheme);
//
//    }
//
    //DataTable using DataTable object
    @When("I create a board with following data")
    public void iCreateABoardWithFollowingData(DataTable boardData) {
        Map<String, String> boardFields = boardData.asMap(String.class, String.class);
        String boardTitle = boardFields.get("Title");
        String boardTheme = boardFields.get("Theme");

        this.boardForm = this.createMenu.openCreateBoardForm();
        this.board = this.boardForm.createBoard(boardTitle, boardTheme);

    }
}
