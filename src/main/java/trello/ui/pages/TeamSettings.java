package trello.ui.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TeamSettings extends AbstractPageObject{
    private JavascriptExecutor javascriptExecutor = (JavascriptExecutor) this.driver;

    @FindBy(css = "#content")
    private WebElement containerScrollable;

    @FindBy(css = ".quiet-button")
    private WebElement deleteLink;

    @FindBy(css = ".js-confirm")
    private WebElement confirmButton;

    public Home deleteTeam(){
        action.click(deleteLink);
        action.click(confirmButton);
        return new Home();
    }

    public void scrollToBottom() {
        javascriptExecutor.executeScript("arguments[0].scrollTop = arguments[0].scrollHeight", containerScrollable);
    }
}
