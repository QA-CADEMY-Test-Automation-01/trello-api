package trello.api.put;

import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import trello.api.RequestManager;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;

public class TrelloApiBoardPutTest {
    private String id;

    @Before
    public void init() {
        id = RequestManager.post("/1/boards", "{\"name\":\"Example Board\"}").path("id");
    }

    @After
    public void finalizer() {
        RequestManager.delete("/1/boards/" + id);
    }

    @Test
    public void testBoardPutWithJUnit() {
        Response response = RequestManager.put("/1/boards/" + id, "{\"name\":\"Example Board Updated\"}");
        assertEquals(200, response.getStatusCode());
    }

    @Test
    public void testBoardPutWithRestAssured() {
        RequestManager.put("/1/boards/" + id, "{\"name\":\"Example Board Updated\"}")
                .then().statusCode(200);
    }

    @Test
    public void testBoardPutResponseHeadersWithJUnit() {
        Response response = RequestManager.put("/1/boards/" + id, "{\"name\":\"Example Board Updated\"}");
        assertEquals(200, response.getStatusCode());
        assertEquals("application/json; charset=utf-8", response.getHeader("Content-Type"));
    }

    @Test
    public void testBoardPutResponseHeadersWithRestAssured() {
        RequestManager.put("/1/boards/" + id, "{\"name\":\"Example Board Updated\"}")
                .then().statusCode(200)
                .header("Content-Type", "application/json; charset=utf-8");
    }

    @Test
    public void testBoardPutResponseBodyWithJUnit() {
        Response response = RequestManager.put("/1/boards/" + id, "{\"name\":\"Example Board Updated\"}");
        assertEquals(200, response.getStatusCode());
        assertEquals("application/json; charset=utf-8", response.getHeader("Content-Type"));
        assertEquals("Example Board Updated", response.path("name"));
    }

    @Test
    public void testBoardPutResponseBodyWithRestAssured() {
        RequestManager.put("/1/boards/" + id, "{\"name\":\"Example Board Updated\"}")
                .then().statusCode(200)
                .header("Content-Type", "application/json; charset=utf-8")
                .body("name", equalTo("Example Board Updated"));
    }

    @Test
    public void testBoardPutResponseJsonSchemaWithRestAssured() {
        RequestManager.put("/1/boards/" + id, "{\"name\":\"Example Board Updated\"}")
                .then().statusCode(200)
                .header("Content-Type", "application/json; charset=utf-8")
                .body("name", equalTo("Example Board Updated"))
                .body(matchesJsonSchemaInClasspath("boardSchema.json"));

    }
}
