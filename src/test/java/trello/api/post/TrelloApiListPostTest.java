package trello.api.post;

import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import trello.api.Board;
import trello.api.RequestManager;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class TrelloApiListPostTest {
    private String id;
    private String idboard;

    //nota podriamos utilizar la clase Board
    @Before
    public void init() {
        idboard = RequestManager.post("/1/boards/", "{\"name\":\"Boardnuevo\"}")
                .then().log().all().extract().response().path("id");

    }
    @After
    public void finish(){
        RequestManager.delete("/1/boards/" + idboard );
    }

    @Test
    public void testListPostWithJunit() {
        Response response = RequestManager
                .post("/1/lists", "{\"idBoard\":\"" + idboard + "\",\"name\":\"lista-1\"}");

        id = response.path("id");
        assertEquals(200, response.getStatusCode());
        assertEquals("application/json; charset=utf-8",response.header("Content-Type"));

    }
    @Test
    public void testListPostWithAssured() {
        id = RequestManager.post("/1/lists","{\"idBoard\":\"" + idboard + "\",\"name\":\"lista-2\"}")
           .then().statusCode(200).contentType("application/json; charset=utf-8").toString();//path("id");

    }
    @Test
    public void testListPostWithAssuredWithHasMap(){
        Map<String,Object>jsonListMap = new HashMap<>();
        jsonListMap.put("name","otro listaMapa");
        jsonListMap.put("idBoard",idboard);

        id= RequestManager.post("/1/lists",jsonListMap).then().statusCode(200)
        .extract().path("id");


    }


}
