package trello.api.cucumber;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import trello.api.RequestManager;
import trello.api.RequestType;

import java.util.Map;


public class TrelloApiSteps {

    private Helper helper;

    public TrelloApiSteps(Helper helper) {
        this.helper = helper;
    }

    @Given("endpoint {string}")
    public void endpoint(String endpoint) {
        System.out.println(endpoint);
        this.helper.endpoint = endpoint;
    }

    @Given("raw body:")
    public void rawBody(String rawBody) {
        System.out.println(rawBody);
        this.helper.rawBody = rawBody;
        this.helper.bodyType = BodyType.RAW;
    }

    @Given("body data:")
    public void bodyData(Map<String, String> bodyMap) {
        this.helper.bodyMap = bodyMap;
        this.helper.bodyType = BodyType.MAP;
    }

    @When("^method (GET|POST|PUT|DELETE)$")
    public void setHttpMethod(RequestType httpMethod) {
        System.out.println(httpMethod);
        if (RequestType.GET.equals(httpMethod)) {
            this.helper.response = RequestManager.get(this.helper.endpoint);
        }
        if (RequestType.POST.equals(httpMethod)) {
            if (BodyType.RAW.equals(this.helper.bodyType)) {
                this.helper.response = RequestManager.post(this.helper.endpoint, this.helper.rawBody);
            }
            if (BodyType.MAP.equals(this.helper.bodyType)) {
                this.helper.response = RequestManager.post(this.helper.endpoint, this.helper.bodyMap);
            }
        }
        if (RequestType.PUT.equals(httpMethod)) {
            String endpoint = this.helper.endpoint.replace("[board.id]", this.helper.board.getId());
            System.out.println(endpoint);
            this.helper.response = RequestManager.put(endpoint, this.helper.rawBody);
        }
    }
}
