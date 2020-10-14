package trello.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TeamForm extends AbstractPageObject {
//    private String TYPE_OPTION_XPATH = "//div[@data-test-id='header-create-team-type-input']/preceding-sibling::span/*[text()='%s']";
    private String TYPE_OPTION_XPATH = "//*[text()='%s']";

    @FindBy(css = "[data-test-id='header-create-team-type-input-operations']")
    private WebElement operationsOption;

    @FindBy(css = "[data-test-id='show-later-button']")
    private WebElement doLaterButton;

    @FindBy(css = "[data-test-id='header-create-team-name-input']")
    private WebElement nameTextField;

    @FindBy(xpath = "//textarea[contains(@id,'create-team-org-description')]")
    private WebElement descriptionTextArea;

    @FindBy(css = "[data-test-id='header-create-team-submit-button']")
    private WebElement continueButton;

    @FindBy(css = "[data-test-id*='team-type-input']")
    private WebElement typeSelection;

    public Team createTeam(String teamName, String type, String description){
        action.sendText(this.nameTextField, teamName);
        action.click(this.typeSelection);
//        action.click(By.xpath(String.format(TYPE_OPTION_XPATH, type)));
        action.click(this.operationsOption);
        action.sendText(this.descriptionTextArea, description);
        action.click(continueButton);
        action.click(doLaterButton);
        return new Team();
    }

}
