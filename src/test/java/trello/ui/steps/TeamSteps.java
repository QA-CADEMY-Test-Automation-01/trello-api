package trello.ui.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import trello.ui.pages.*;

import java.util.Map;

public class TeamSteps {

    private CreateMenu createMenu;
    private TeamForm teamForm;
    private Team team;
    private TeamSettings teamSettings ;
    private Home home ;

    public TeamSteps(CreateMenu createMenu) {
        this.createMenu = createMenu;
    }

    @When("I create a team with following data")
    public void iCreateATeamWithFollowingData(Map<String, String> table){
        String teamName = table.get("Name");
        String type = table.get("Type");
        String description = table.get("Description");

        this.teamForm = this.createMenu.openCreateTeamForm();
        this.team = this.teamForm.createTeam(teamName, type, description);
    }

    @And("I go to team settings")
    public void iGoToTeamSettings() {
        this.teamSettings = this.team.goSettings();
    }

    @And("I scroll down to bottom")
    public void iScrollDownToBottom() {
        this.teamSettings.scrollToBottom();
    }

    @When("I delete the team")
    public void iDeleteTheTeam() {
        this.home = this.teamSettings.deleteTeam();
    }

    @Then("I should not see the team {string} into the homepage")
    public void iShouldNotSeeTheTeamIntoTheHomepage(String teamName) {
        Assert.assertFalse(this.home.isTeamInMenuList(teamName));
    }
}
