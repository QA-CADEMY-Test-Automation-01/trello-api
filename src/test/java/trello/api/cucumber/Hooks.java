package trello.api.cucumber;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import trello.api.Board;
import trello.api.RequestManager;

public class Hooks {

    private Helper helper;

    public Hooks(Helper helper) {
        this.helper = helper;
    }

    @Before("@CreateDeleteBoard")
    public void createBoard() {
        Board newBoard = new Board();
        newBoard.setName("Example");
        newBoard.setDesc("Sample Description");
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
}
