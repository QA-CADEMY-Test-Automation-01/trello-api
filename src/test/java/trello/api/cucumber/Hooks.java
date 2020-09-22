package trello.api.cucumber;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import trello.api.Board;
import trello.api.List;
import trello.api.RequestManager;

public class Hooks {

    private Helper helper;

    public Hooks(Helper helper) {
        this.helper = helper;
    }

    @Before(value = "@CreateDeleteBoard", order = 10)
    public void createBoard() {
        Board newBoard = new Board();
        newBoard.setName("Example");
        newBoard.setDesc("Sample Description");
        newBoard.setDefaultLabels(false);
        newBoard.setDefaultLists(false);
        this.helper.board = RequestManager.post("/1/boards", newBoard).as(Board.class);
    }

    @After("@CreateDeleteBoard")
    public void deleteBoard() {
        RequestManager.delete("/1/boards/" + this.helper.board.getId());
    }

    @After("@DeleteBoard")
    public void deleteBoardFromResponse() {
        RequestManager.delete("/1/boards/" + this.helper.response.path("id"));
    }

    @Before(value = "@CreateList", order = 20)
    public void createList() {
        List list = new List();
        list.setName("List Example");
        list.setIdBoard(this.helper.board.getId());
        this.helper.list = RequestManager.post("/1/lists", list).as(List.class);
    }
}
