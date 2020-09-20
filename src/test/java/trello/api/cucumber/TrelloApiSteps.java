package trello.api.cucumber;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import trello.api.RequestManager;
import trello.api.RequestType;
import trello.api.cucumber.util.Replacer;

import java.util.Map;


public class TrelloApiSteps {

    private static final Logger LOGGER = LogManager.getLogger();
    private Helper helper;
    private Replacer replacer;

    public TrelloApiSteps(Helper helper) {
        this.helper = helper;
        this.replacer = new Replacer(this.helper);
    }

    @Given("endpoint {string}")
    public void setEndpoint(String endpoint) {
        this.helper.endpoint = this.replacer.replaceParams(endpoint);
        LOGGER.info(this.helper.endpoint);
    }

    @Given("raw body:")
    public void setRawBody(String rawBody) {
        this.helper.rawBody = this.replacer.replaceParams(rawBody);
        LOGGER.info(this.helper.rawBody);
        this.helper.bodyType = BodyType.RAW;
    }

    @Given("body data:")
    public void setBodyData(Map<String, String> bodyMap) {
        this.helper.bodyMap = this.replacer.replaceInMap(bodyMap);
        this.helper.bodyType = BodyType.MAP;
    }

    @When("^method (GET|POST|PUT|DELETE)$")
    public void setHttpMethod(RequestType httpMethod) {
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
            if (BodyType.RAW.equals(this.helper.bodyType)) {
                this.helper.response = RequestManager.put(this.helper.endpoint, this.helper.rawBody);
            }
            if (BodyType.MAP.equals(this.helper.bodyType)) {
                this.helper.response = RequestManager.put(this.helper.endpoint, this.helper.bodyMap);
            }
        }
        if (RequestType.DELETE.equals(httpMethod)) {
            this.helper.response = RequestManager.delete(this.helper.endpoint);
        }
        LOGGER.info(this.helper.response.prettyPrint());
    }
}
