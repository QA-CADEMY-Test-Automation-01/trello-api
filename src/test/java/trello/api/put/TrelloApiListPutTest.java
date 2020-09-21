package trello.api.put;

import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import trello.api.List;
import trello.api.RequestManager;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;

public class TrelloApiListPutTest {
    private String boardId;
    private String listId;

    @Before
    public void init(){
        boardId = RequestManager.post("/1/boards/", "{\"name\":\"My Board\"}").path("id");
        listId = RequestManager.post("/1/lists/", String.format("{\"name\":\"Research\", \"idBoard\":\"%s\"}", boardId)).path("id");
    }

    @After
    public void finalizer(){
        RequestManager.delete("/1/boards/"+boardId);
    }

    @Test
    public void testListPutWithAssured(){
        Response response = RequestManager.put("/1/lists/"+listId, "{\"name\":\"Improve\"}");
        response.then().log().all();
        response.then().statusCode(200);
    }

    @Test
    public void testListPutWithAssuredAndHashMap(){
        Response response = RequestManager.put("/1/lists/"+listId, "{\"name\":\"New Research\"}");
        response.then().log().all();
        response.then().statusCode(200).
                header("Content-Type", "application/json; charset=utf-8").
                body("name", equalTo("New Research"));
    }

    @Test
    public void testListPutJsonSchemaWithRestAssured(){
        Response response = RequestManager.put("/1/lists/"+listId, "{\"name\":\"New Improve\"}");
        response.then().statusCode(200).
                body(matchesJsonSchemaInClasspath("listSchema.json")).
                log().all();
    }
}
