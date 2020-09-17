package trello.api;


import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

import static io.restassured.RestAssured.given;

public final class RequestManager {

    private static RequestSpecification requestSpecification = new Authentication().getRequestSpecification();

    public static Response get(String endpoint) {
        return given()
                .spec(requestSpecification)
                .log().all()
                .when()
                .get(endpoint);
    }

    public static Response post(String endpoint, String body) {
        return given()
                .spec(requestSpecification)
                .log().all()
                .body(body)
                .when()
                .post(endpoint);
    }

    public static Response post(String endpoint, Object body) {
        return given()
                .spec(requestSpecification)
                .log().all()
                .body(body)
                .when()
                .post(endpoint);
    }

    public static Response post(String endpoint, Map<String, Object> body) {
        return given()
                .spec(requestSpecification)
                .log().all()
                .body(body)
                .when()
                .post(endpoint);
    }

    public static Response put(String endpoint, String body) {
        return given()
                .spec(requestSpecification)
                .log().all()
                .body(body)
                .when()
                .put(endpoint);
    }

    public static Response put(String endpoint, Object body) {
        return given()
                .spec(requestSpecification)
                .log().all()
                .body(body)
                .when()
                .put(endpoint);
    }

    public static Response put(String endpoint, Map<String, Object> body) {
        return given()
                .spec(requestSpecification)
                .log().all()
                .body(body)
                .when()
                .put(endpoint);
    }

    public static Response delete(String endpoint) {
        return given()
                .spec(requestSpecification)
                .log().all()
                .when()
                .delete(endpoint);
    }
}
