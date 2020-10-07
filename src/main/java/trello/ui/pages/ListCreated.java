package trello.ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ListCreated extends AbstractPageObject {
    @FindBy(css=".list-header")
    WebElement listNameLabel;

    public ListCreated(WebDriver driver){
        super(driver);
    }

    public String getListName(){
        return action.getText(listNameLabel);
    }
}
