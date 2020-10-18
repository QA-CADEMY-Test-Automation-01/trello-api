package trello.ui.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import trello.ui.core.Environment;
import trello.ui.pages.Header;
import trello.ui.pages.Login;
import trello.ui.pages.Profile;
import trello.ui.pages.Welcome;

public class LoginSteps {
    private Welcome welcome;
    private Login login;
    private Header header;
    private Profile profile;

    public LoginSteps(Welcome welcome) {
        this.welcome = welcome;
    }

    @Given("I am on login page")
    public void iAmOnLoginPage() {
        this.login = this.welcome.login();
    }

    @When("I login with username {string} and password {string}")
    public void iLoginWithUsernameAndPassword(String user, String password){
        this.header = this.login.login(user, password);

    }

    @Then("I should see {string} in profile menu")
    public void iShouldSeeInProfileMenu(String userEmail) {
        this.profile = this.header.goToProfileMenu();
        String actualEmail = this.profile.getEmailAccount();

        Assert.assertEquals(userEmail, actualEmail);
    }

    @And("I login in single page with username {string} and password {string}")
    public void iLoginInSinglePageWithUsernameAndPassword(String user, String password) {
        this.login.loginInSinglePage(user, password);
    }

    @Then("I should see the error message {string}")
    public void iShouldSeeTheErrorMessageIncorrectPassword(String expectedError) {
        String actualErrorMessage = this.login.getErrorMessage();
        Assert.assertEquals(expectedError, actualErrorMessage);
    }

    @When("I login with username {string}")
    public void iLoginWithUsername(String userName) {
        String user = Environment.getInstance().getValue(String.format("$['users']['%s']['user']", userName));
        String password = Environment.getInstance().getValue(String.format("$['users']['%s']['password']", userName));
        this.header = this.login.login(user, password);
    }
}
