package trello.api;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;
import io.restassured.http.ContentType;

public class ApiRestAssuredTest {

    private RequestSpecification requestSpecification;
    private ResponseSpecification responseSpecification;

    public ApiRestAssuredTest() {
        requestSpecification = new RequestSpecBuilder().
                setBaseUri("https://gorest.co.in").
                addHeader("Authorization", "Bearer 253e4e72a1e5510b6899aa17cd8acdf0899a85faa4cf8967746f60bc5cdddf7d").
                addHeader("Content-Type", "application/json").
                build();

        responseSpecification = new ResponseSpecBuilder().
                expectStatusCode(200).
                expectContentType(ContentType.JSON).
                build();
    }

    @Test
    public void testGoRestInGetMethod() {
        given().
            spec(requestSpecification).
            log().all().
        when().
            get("/public-api/users").
        then().
            log().all().
            spec(responseSpecification);
    }

    @Test
    public void testGoRestInPostMethod() {
        given().
            spec(requestSpecification).
            body("{\"name\":\"Tenali Ramakrishna\", \"gender\":\"Male\", \"email\":\"tenali.ramakrishna@email1valid2.com\", \"status\":\"Active\"}").
            log().all().
        when().
            post("/public-api/users").
        then().
            log().all().
            spec(responseSpecification).
            assertThat().
            body("code", equalTo(201));

    }

    @Test
    public void testGoRestInPostMethodWithForParams() {
        Response response = given().
            spec(requestSpecification).
            formParam("name", "Example2").
            formParam("gender", "Male").
            formParam("email", "val1d@emailed.com").
            formParam("status", "Active").
        when().
            post("/public-api/users").
        then().
            spec(responseSpecification).
        extract().response();

        assertEquals("Example2", response.path("data.name"));
    }

    @Test
    public void testGoRestInPutMethod() {
        given().
            spec(requestSpecification).
            body("{\"name\":\"Tenali Ramakrishna\", \"gender\":\"Male\", \"email\":\"tenali.ramakrishna@15ce.com\", \"status\":\"Active\"}").
            log().all().
        when().
            put("/public-api/users/10").
        then().
            log().all().
            spec(responseSpecification);
    }

    @Test
    public void testGoRestInDeleteMethod() {
        given().
            spec(requestSpecification).
            log().all().
        when().
            delete("/public-api/users/1").
        then().
            log().all().
            spec(responseSpecification);
    }
}
