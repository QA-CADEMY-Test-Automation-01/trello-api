package trello.api.delete;

import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import trello.api.RequestManager;

import static org.junit.Assert.assertEquals;

public class TrelloApiBoardDeleteTest {

    private String id;

    @Before
    public void init() {
        id = RequestManager.post("/1/boards", "{\"name\":\"Example Board\"}").path("id");
    }

    @Test
    public void testBoardDeleteWithJUnit() {
        Response response = RequestManager.delete("/1/boards/" + id);
        assertEquals(200, response.getStatusCode());
    }

    @Test
    public void testBoardDeleteWithRestAssured() {
        RequestManager.delete("/1/boards/" + id)
                .then().statusCode(200);
    }
}
