package trello.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Home extends AbstractPageObject {
    private String TEAM_NAME_XPATH = "//span[@data-test-id='home-team-tab-name' and text()='%s']";
    private WebElement teamItem;

    public boolean isTeamInMenuList(String teamName){
        return action.isElementPresent(By.xpath(String.format(TEAM_NAME_XPATH, teamName)));
    }
}
