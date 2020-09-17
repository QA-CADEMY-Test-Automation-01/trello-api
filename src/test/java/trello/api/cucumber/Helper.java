package trello.api.cucumber;

import io.restassured.response.Response;
import trello.api.Board;

import java.util.Map;

public class Helper {
    Board board;
    Response response;
    String endpoint;
    String rawBody;
    Map<String, String> bodyMap;
    BodyType bodyType;
}
