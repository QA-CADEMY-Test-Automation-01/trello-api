package trello.api.post;

import io.restassured.response.Response;
import org.junit.After;
import org.junit.Test;
import trello.api.RequestManager;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;

public class TrelloApiBoardPostTest {

    private String id;

    @After
    public void finalizer() {
        RequestManager.delete("/1/boards/" + id);
    }

    @Test
    public void testBoardPostWithJUnit() {
        Response response = RequestManager.post("/1/boards/", "{\"name\":\"Example Board\"}");
        id = response.path("id");
        assertEquals(200, response.getStatusCode());
    }

    @Test
    public void testBoardPostWithRestAssuredWithHashMap() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("name", "Example Board");
        jsonAsMap.put("desc", "Description");
        id = RequestManager.post("/1/boards/", jsonAsMap)
                .then().statusCode(200)
                .extract().path("id");
    }

    @Test
    public void testBoardPostWithJUnitWithHashMap() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("name", "Example Board");
        jsonAsMap.put("desc", "Description");
        Response response = RequestManager.post("/1/boards/", jsonAsMap);
        id = response.path("id");
        assertEquals(200, response.getStatusCode());
    }

    @Test
    public void testBoardPostWithRestAssured() {
        id = RequestManager.post("/1/boards/", "{\"name\":\"Example Board\"}")
                .then().statusCode(200)
                .extract().path("id");
    }

    @Test
    public void testBoardPostResponseHeadersWithJUnit() {
        Response response = RequestManager.post("/1/boards/", "{\"name\":\"Example Board\"}");
        id = response.path("id");
        assertEquals(200, response.getStatusCode());
        assertEquals("application/json; charset=utf-8", response.getHeader("Content-Type"));
    }

    @Test
    public void testBoardPostResponseHeadersWithRestAssured() {
        id = RequestManager.post("/1/boards/", "{\"name\":\"Example Board\"}")
                .then().statusCode(200)
                .header("Content-Type", "application/json; charset=utf-8")
                .extract().path("id");
    }

    @Test
    public void testBoardPostResponseBodyWithJUnit() {
        Response response = RequestManager.post("/1/boards/", "{\"name\":\"Example Board\"}");
        id = response.path("id");
        assertEquals(200, response.getStatusCode());
        assertEquals("application/json; charset=utf-8", response.getHeader("Content-Type"));
        assertEquals("Example Board", response.path("name"));
    }

    @Test
    public void testBoardPostResponseBodyWithRestAssured() {
        id = RequestManager.post("/1/boards/", "{\"name\":\"Example Board\"}")
                .then().statusCode(200)
                .header("Content-Type", "application/json; charset=utf-8")
                .body("name", equalTo("Example Board"))
                .extract().path("id");
    }

    @Test
    public void testBoardPostResponseJsonSchemaWithRestAssured() {
        id = RequestManager.post("/1/boards/", "{\"name\":\"Example Board\"}")
                .then().statusCode(200)
                .header("Content-Type", "application/json; charset=utf-8")
                .body("name", equalTo("Example Board"))
                .body(matchesJsonSchemaInClasspath("boardSchema.json"))
                .extract().path("id");

    }
}
