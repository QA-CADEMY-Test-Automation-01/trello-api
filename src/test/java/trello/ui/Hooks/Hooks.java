package trello.ui.Hooks;

import io.cucumber.java.After;
import trello.ui.core.DriverManager;

public class Hooks {
    @After
    public void close(){
        DriverManager.getInstance().getDriver().quit();
    }
}
