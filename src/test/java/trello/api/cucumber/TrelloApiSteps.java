package trello.api.cucumber;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import trello.api.RequestManager;
import trello.api.RequestType;

import java.util.Map;


public class TrelloApiSteps {

    private Helper helper;
    private static final Logger LOGGER = LogManager.getLogger();

    public TrelloApiSteps(Helper helper) {
        this.helper = helper;
    }

    @Given("endpoint {string}")
    public void endpoint(String endpoint) {
        System.out.println(endpoint);
        LOGGER.info(endpoint);
        this.helper.endpoint = endpoint.replace("[board.id]", this.helper.board.getId());
    }

    @Given("raw body:")
    public void rawBody(String rawBody) {
        System.out.println(rawBody);
        this.helper.rawBody = rawBody.replace("[board.id]", this.helper.board.getId());
        LOGGER.info(rawBody);
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
        LOGGER.info(httpMethod);
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
            this.helper.response = RequestManager.put(this.helper.endpoint, this.helper.rawBody);
        }
    }
}
