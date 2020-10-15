package trello.ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import trello.ui.core.DriverManager;
import trello.ui.core.WebDriverAction;

import java.util.concurrent.TimeUnit;

public abstract class AbstractPageObject {

    protected WebDriver driver;
    protected WebDriverWait wait;
    protected WebDriverAction action;

    public AbstractPageObject() {
        this.driver = DriverManager.getInstance().getDriver();
        this.wait = new WebDriverWait(this.driver, 20);
        this.action = new WebDriverAction(this.driver, wait);
        AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(this.driver, 20);
        PageFactory.initElements(factory, this);
    }
}
