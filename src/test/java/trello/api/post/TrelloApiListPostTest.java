package trello.api.post;

import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import trello.api.RequestManager;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;

public class TrelloApiListPostTest {
    private String boardId;

    @Before
    public void init(){
        Response response = RequestManager.post("/1/boards/", "{\"name\":\"My Board\"}");
        boardId = response.path("id");
    }

    @After
    public void finalizer(){
        RequestManager.delete("/1/boards/"+boardId);
    }

    @Test
    public void testListPostWithAssured(){
        Response response = RequestManager.post("/1/lists/", String.format("{\"name\":\"Validation\", \"idBoard\":\"%s\"}", boardId));
        response.then().log().all();
        response.then().statusCode(200);
    }

    @Test
    public void testListPostWithAssuredAndHashMap(){
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("name", "Check");
        jsonAsMap.put("idBoard", boardId);
        Response response = RequestManager.post("/1/lists/", jsonAsMap);
        response.then().log().all();
        response.then().statusCode(200).
                header("Content-Type", "application/json; charset=utf-8").
                body("name", equalTo("Check"));
    }

    @Test
    public void testListPostJsonSchemaWithRestAssured(){
        Response response = RequestManager.post("/1/lists/", String.format("{\"name\":\"Research\", \"idBoard\":\"%s\"}", boardId));
        response.then().statusCode(200).
        body(matchesJsonSchemaInClasspath("listSchema.json")).
        log().all();
    }
}
