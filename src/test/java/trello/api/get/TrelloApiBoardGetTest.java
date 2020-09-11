package trello.api.get;

import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import trello.api.Board;
import trello.api.RequestManager;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;

public class TrelloApiBoardGetTest {

    private Board board;

    @Before
    public void init() {
        Board newBoard = new Board();
        newBoard.setName("Example Board");
        board = RequestManager.post("/1/boards", newBoard).as(Board.class);
    }

    @After
    public void finalizer() {
        RequestManager.delete("/1/boards/" + board.getId());
    }

    @Test
    public void testBoardGetWithJUnit() {
        Response response = RequestManager.get("/1/boards/" + board.getId());
        assertEquals(200, response.getStatusCode());
    }

    @Test
    public void testBoardGetWithRestAssured() {
        RequestManager.get("/1/boards/" + board.getId())
                .then().statusCode(200);
    }

    @Test
    public void testBoardGetResponseHeadersWithJUnit() {
        Response response = RequestManager.get("/1/boards/" + board.getId());
        assertEquals(200, response.getStatusCode());
        assertEquals("application/json; charset=utf-8", response.getHeader("Content-Type"));
    }

    @Test
    public void testBoardGetResponseHeadersWithRestAssured() {
        RequestManager.get("/1/boards/" + board.getId())
                .then()
                .statusCode(200)
                .header("Content-Type", "application/json; charset=utf-8");
    }

    @Test
    public void testBoardGetResponseBodyWithJUnit() {
        Response response = RequestManager.get("/1/boards/" + board.getId());
        assertEquals(200, response.getStatusCode());
        assertEquals("application/json; charset=utf-8", response.getHeader("Content-Type"));
        assertEquals("Example Board", response.path("name"));
    }

    @Test
    public void testBoardGetResponseBodyWithRestAssured() {
        RequestManager.get("/1/boards/" + board.getId())
                .then()
                .statusCode(200)
                .header("Content-Type", "application/json; charset=utf-8")
                .body("name", equalTo("Example Board"));
    }

    @Test
    public void testBoardGetResponseJsonSchemaWithRestAssured() {
        RequestManager.get("/1/boards/" + board.getId())
                .then()
                .statusCode(200)
                .header("Content-Type", "application/json; charset=utf-8")
                .body("name", equalTo("Example Board"))
                .body(matchesJsonSchemaInClasspath("boardSchema.json"));
    }

    @Test
    public void testBoardGetResponseSerialization() {
        Board currentBoard = RequestManager.get("/1/boards/" + board.getId()).as(Board.class);
        assertEquals("Example Board", currentBoard.getName());
    }
}
