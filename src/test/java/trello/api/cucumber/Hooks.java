package trello.api.cucumber;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import trello.api.Board;
import trello.api.RequestManager;

public class Hooks {

    private Board board;

    @Before("@CreateBoard")
    public void init() {
        Board newBoard = new Board();
        newBoard.setName("Example");
        newBoard.setDesc("Sample Description");
        board = RequestManager.post("/1/boards", newBoard).as(Board.class);
    }

    @After("@CreateBoard")
    public void finalizer() {
        RequestManager.delete("/1/boards/"+board.getId());
    }
}
