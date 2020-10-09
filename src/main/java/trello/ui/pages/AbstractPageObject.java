package trello.ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import trello.ui.core.DriverManager;
import trello.ui.core.WebDriverAction;

public abstract class AbstractPageObject {

    protected WebDriver driver;
    protected WebDriverWait wait;
    protected WebDriverAction action;

    public AbstractPageObject() {
        this.driver = DriverManager.getInstance().getDriver();
        this.wait = new WebDriverWait(this.driver, 20);
        this.action = new WebDriverAction(this.driver, wait);
        PageFactory.initElements(this.driver, this);
    }
}
