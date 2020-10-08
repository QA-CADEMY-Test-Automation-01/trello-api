package trello.ui.steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import org.openqa.selenium.WebDriver;
import trello.ui.core.Driver;
import trello.ui.pages.Login;
import trello.ui.pages.Welcome;

import java.util.List;
import java.util.Map;

public class LoginStep {

    private Welcome welcome;
    private Login login;

    public LoginStep(){
//        this.welcome = welcome;
//        this.login = login;
    }
    @Given("I am on login page")
    public void iAmOnLoginPage(DataTable table) {
//        this.welcome.login();
        List<Map<String, String>> data = table.asMaps(String.class, String.class);
        System.out.println("1,1: " + data.get(0).get("header1"));
        System.out.println("1,2: " + data.get(0).get("header2"));
        System.out.println("2,1: " + data.get(1).get("header1"));
        System.out.println("2,1: " + data.get(1).get("header2"));
    }
}
