package trello.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Card extends AbstractPageObject {

    private String CARD_NAME_XPATH = "//span[text()='%s']";

    public Card(WebDriver driver) {
        super(driver);
    }

    public String getCardTitle(String cardName) {
        return action.getText(By.xpath(String.format(this.CARD_NAME_XPATH, cardName)));
    }
}
