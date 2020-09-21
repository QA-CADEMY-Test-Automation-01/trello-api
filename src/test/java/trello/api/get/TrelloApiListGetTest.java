package trello.api.get;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import trello.api.List;
import trello.api.RequestManager;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;

public class TrelloApiListGetTest {
    private String boardId;
//    private String listId;
    private List list;

    @Before
    public void init(){
        boardId = RequestManager.post("/1/boards/", "{\"name\":\"My Board\"}").path("id");

//        listId = RequestManager.post("/1/lists/", String.format("{\"name\":\"Research\", \"idBoard\":\"%s\"}", boardId)).path("id");
        List newList = new List();
        newList.setName("Research");
        newList.setIdBoard(boardId);
        list = RequestManager.post("/1/lists/", newList).as(List.class);
    }

    @After
    public void finalizer(){
        RequestManager.delete("/1/boards/"+boardId);
    }

    @Test
    public void testListGetWithAssured(){
        RequestManager.get("/1/lists/"+list.getId()).
                then().
                statusCode(200).
                log().all();
    }

    @Test
    public void testListGetBodyWithAssured(){
        RequestManager.get("/1/lists/"+list.getId()).
                then().
                statusCode(200).
                header("Content-Type", "application/json; charset=utf-8").
                body("name", equalTo("Validation"));
    }

    @Test
    public void testListGetJsonSchemaWithAssured(){
        RequestManager.get("/1/lists/"+list.getId()).
                then().
                statusCode(200).
                body(matchesJsonSchemaInClasspath("listSchema.json"));
    }
}
