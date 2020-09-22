package trello.api.cucumber;

import io.cucumber.java.en.Then;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;

public class TrelloApiVerificationSteps {

    private Helper helper;
    private static final Logger LOGGER = LogManager.getLogger();

    public TrelloApiVerificationSteps(Helper helper) {
        this.helper = helper;
    }

    @Then("status code {int}")
    public void status_code(int statusCode) {
        System.out.println(statusCode);
        LOGGER.info(statusCode);
        assertEquals(statusCode, this.helper.response.getStatusCode());
    }

    @Then("response body contains {string}")
    public void responseBodyContains(String string) {
        LOGGER.info(string);
        this.helper.response.then().assertThat().body(containsString(string));
    }

    @Then("response headers contains:")
    public void responseMapHeadersContains(Map<String, String> headersMap) {
        for(Map.Entry<String, String> entry: headersMap.entrySet()) {
            LOGGER.info(entry.getKey(), entry.getValue());
            assertEquals(entry.getValue(), this.helper.response.getHeader(entry.getKey()));
        }
    }

    @Then("response body contains:")
    public void responseMapBodyContains(Map<String, String> bodyMap) {
        for(Map.Entry<String, String> entry: bodyMap.entrySet()) {
            LOGGER.info(entry.getKey(), entry.getValue());
            assertEquals(entry.getValue(), this.helper.response.path(entry.getKey()));
        }
    }

    @Then("JSON schema matches {string}")
    public void verifyJsonSchema(String schemaPath) {
        this.helper.response.then()
                .body(matchesJsonSchemaInClasspath(schemaPath));
    }
}
