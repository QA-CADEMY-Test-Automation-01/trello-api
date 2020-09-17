package trello.api.cucumber;

import io.cucumber.java.en.Then;

import java.util.Map;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;

public class TrelloApiVerificationSteps {

    private Helper helper;

    public TrelloApiVerificationSteps(Helper helper) {
        this.helper = helper;
    }

    @Then("status code {int}")
    public void status_code(int statusCode) {
        System.out.println(statusCode);
        assertEquals(statusCode, this.helper.response.getStatusCode());
    }

    @Then("response body contains {string}")
    public void responseBodyContains(String string) {
        this.helper.response.then()
                .statusCode(200)
                .header("Content-Type", "application/json; charset=utf-8")
                .body("[0].name", equalTo("Example"));
    }

    @Then("response headers contains:")
    public void responseMapHeadersContains(Map<String, String> headersMap) {
        for(Map.Entry<String, String> entry: headersMap.entrySet()) {
            assertEquals(entry.getValue(), this.helper.response.getHeader(entry.getKey()));
        }
    }

    @Then("response body contains:")
    public void responseMapBodyContains(Map<String, String> bodyMap) {
        for(Map.Entry<String, String> entry: bodyMap.entrySet()) {
            assertEquals(entry.getValue(), this.helper.response.path(entry.getKey()));
        }
    }

    @Then("JSON schema matches {string}")
    public void verifyJsonSchema(String schemaPath) {
        this.helper.response.then()
                .body(matchesJsonSchemaInClasspath(schemaPath));
    }
}
