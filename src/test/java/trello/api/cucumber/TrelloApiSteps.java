package trello.api.cucumber;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import trello.api.RequestManager;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;

public class TrelloApiSteps {

    private String endpoint;
    private Response response;

    @Given("endpoint {string}")
    public void endpoint(String endpoint) {
        System.out.println(endpoint);
        this.endpoint = endpoint;
    }

    @When("method {word}")
    public void method_get(String httpMethod) {
        System.out.println(httpMethod);
        this.response = RequestManager.get(endpoint);
    }

    @Then("status code {int}")
    public void status_code(int statusCode) {
        System.out.println(statusCode);
        assertEquals(statusCode, response.getStatusCode());
    }

    @Then("response body contains {string}")
    public void responseBodyContains(String string) {
        response.then()
                .statusCode(200)
                .header("Content-Type", "application/json; charset=utf-8")
                .body("[0].name", equalTo("Example"));
    }
}
