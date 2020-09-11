package trello.api;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class Authentication {

    private static final String KEY = "";
    private static final String BASE_URL = "";
    private static final String TOKEN = "";
    private RequestSpecification requestSpecification;

    public Authentication() {
        requestSpecification = new RequestSpecBuilder().
                setBaseUri(BASE_URL).
                addHeader("Content-Type", "application/json").
                addQueryParam("key", KEY).
                addQueryParam("token", TOKEN).
                build();
    }

    public RequestSpecification getRequestSpecification() {
        return requestSpecification;
    }
}
