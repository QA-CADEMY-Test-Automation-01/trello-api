package trello.api.put;

import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import trello.api.Board;
import trello.api.RequestManager;

import static org.junit.Assert.assertEquals;

public class TrelloApiListPutTest {
private Board board;
private String idBoard ;
private String idlista;


    @Before
    public void init(){
        Board newBoard = new Board();
        newBoard.setName("Board-2");
        board= RequestManager.post("/1/boards/",newBoard).as(Board.class);

        Response response = RequestManager
                .post("/1/lists","{\"idBoard\":\""+board.getId()+"\",\"name\":\"nueva lista-board2\"}");

        idlista= response.path("id");
    }
    @After
    public void finish(){
        RequestManager.delete("/1/boards/" + board.getId());
    }

    @Test
    public void testListPutWithJUnit(){
        Response response=RequestManager.put("1/list/"+ idlista,"{\"name\":\"modificando board-3\"}");
        assertEquals(200,response.getStatusCode());
        assertEquals("application/json; charset=utf-8", response.getHeader("Content-Type"));
    }
    @Test
    public void testListPutWithAssured(){
        RequestManager.put("1/list/"+ idlista,"{\"name\":\"modificando board-3\"}").then().statusCode(200);

    }

}
