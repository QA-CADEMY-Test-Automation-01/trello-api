package trello.api;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;
import io.restassured.http.ContentType;

public class TrelloApiTest {

    private RequestSpecification requestSpecification;
    private ResponseSpecification responseSpecification;

    public TrelloApiTest() {
        requestSpecification = new RequestSpecBuilder().
                setBaseUri("https://api.trello.com").
                addHeader("Content-Type", "application/json").
                addQueryParam("key", "").
                addQueryParam("token", "").
                build();

        responseSpecification = new ResponseSpecBuilder().
                expectStatusCode(200).
                expectContentType(ContentType.JSON).
                build();
    }

    @Test
    public void testTrelloBoardsGetMethod() {
        given().
                spec(requestSpecification).
                log().all().
                when().
                get("/1/members/me/boards").
                then().
                log().all().
                spec(responseSpecification);
    }

    @Test
    public void testTrelloBoardsPostMethod() {
        given().
                spec(requestSpecification).
                body("{\"name\":\"Example Board\"}").
                log().all().
                when().
                post("/1/boards").
                then().
                log().all().
                spec(responseSpecification).
                assertThat().
                body("name", equalTo("Example Board"));

    }

    @Test
    public void testTrelloBoardsPutMethod() {
        given().
                spec(requestSpecification).
                body("{\"name\":\"Renamed Example\"}").
                log().all().
                when().
                put("/1/boards/{id}","5f5426601f240e861780f51d").
                then().
                log().all().
                spec(responseSpecification);
    }

    @Test
    public void testTrelloBoardsDeleteMethod() {
        given().
                spec(requestSpecification).
                log().all().
                when().
                delete("/1/boards/{id}","5f5426601f240e861780f51d").
                then().
                log().all().
                spec(responseSpecification);
    }
}
