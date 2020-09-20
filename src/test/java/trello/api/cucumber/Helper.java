package trello.api.cucumber;

import io.restassured.response.Response;
import trello.api.Board;
import trello.api.List;

import java.util.Map;

public class Helper {
    public Board board;
    Response response;
    String endpoint;
    String rawBody;
    Map<String, String> bodyMap;
    BodyType bodyType;
    public List list;
}
