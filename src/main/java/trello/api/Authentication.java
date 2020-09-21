package trello.api;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class Authentication {

    private static final String BASE_URL = "https://api.trello.com";
    private static final String KEY = "84efc26915c199f93ea4e172ed5b3653";
    private static final String TOKEN = "caa3104197da9230f06a6408ee25f058771e111ff473ef9438f40f651f709241";

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
