package trello.api;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import trello.util.Config;

public class Authentication {

    private static final String BASE_URL = Config.getInstance().getBaseUrl();
    private static final String KEY = Config.getInstance().getKey();
    private static final String TOKEN = Config.getInstance().getToken();
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
