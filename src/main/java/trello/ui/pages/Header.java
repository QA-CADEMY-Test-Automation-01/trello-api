package trello.ui.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Header extends AbstractPageObject {

    @FindBy(css = "[data-test-id='header-member-menu-button']")
    private WebElement profileButton;

    @FindBy(css = "[data-test-id='header-create-menu-button']")
    private WebElement addButton;

    public Profile goToProfileMenu() {
        action.click(profileButton);
        return new Profile();
    }

    public CreateMenu openCreateMenu() {
        action.click(addButton);
        return new CreateMenu();
    }
}
