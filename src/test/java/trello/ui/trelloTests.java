package trello.ui;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import trello.ui.core.WebDriverAction;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class trelloTests {
    private WebDriver driver;
    //WebDriverWait object to handle the timeout and expected conditions of explicit wait
    private WebDriverWait wait;
    //Custom object to handle explicit waits for clickable elements
    private WebDriverAction action;

    @Before
    public void setUp(){
        //Configure web driver manager
        WebDriverManager.chromedriver().setup();
        //Headless Mode
//        ChromeOptions chromeOptions = new ChromeOptions();
//        chromeOptions.addArguments("--headless");
//        chromeOptions.setHeadless(true);
//        driver = new ChromeDriver(chromeOptions);
//        driver.manage().window().setSize(new Dimension(1920, 1080));

        //Create driver adn open browser
        driver = new ChromeDriver();
        //Configure implicit wait
//        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //Configure explicit wait
        wait = new WebDriverWait(driver, 20);
        action = new WebDriverAction(driver, wait);

    }

    @After
    public void tearDown(){
        //Close browser
        driver.quit();
    }

    @Test
    public void testLogin() {
        try {
//            driver.manage().window().maximize();
            driver.get("https://trello.com/");
//            sleep(3);
            //Click login button
//            WebElement loginHomeButton = driver.findElement(By.cssSelector("[href*='login']"));
//            loginHomeButton.click();
            //Replacing findElement by action function that handles click action by explicit waits
            action.click(By.cssSelector("[href*='login']"));
//            sleep(3);
            //Set username
//            WebElement nameField = driver.findElement(By.cssSelector("[autocomplete='username']"));
//            WebElement nameField = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[autocomplete='username']")));
//            nameField.sendKeys("joseccb1948@yahoo.com");
            action.sendText(By.cssSelector("[autocomplete='username']"), "joseccb1948@yahoo.com");
//            sleep(3);
            //Explicit wait used to wait for the login button to change name before continue
            wait.until(ExpectedConditions.attributeContains(By.cssSelector("#login"), "value", "Atlassian"));

            //Click login with Atlasian
//            WebElement loginAtlasianButton = driver.findElement(By.cssSelector("#login"));
//            loginAtlasianButton.click();
            //Replacing findElement by action function that handles click action by explicit waits
            action.click(By.cssSelector("#login"));
//            sleep(3);
            //Set password
//            WebElement passwordField = driver.findElement(By.cssSelector("#password"));
//            passwordField.sendKeys("Control*1234");
            action.sendText(By.cssSelector("#password"), "Control*1234");
            //Click login
//            WebElement loginButton = driver.findElement(By.cssSelector("#login-submit"));
//            loginButton.click();
            //Replacing findElement by action function that handles click action by explicit waits
            action.click(By.cssSelector("#login-submit"));
//            sleep(15);
            //Click profile button
//            WebElement profileButton = driver.findElement(By.cssSelector("[data-test-id='header-member-menu-button']"));
//            profileButton.click();
            //Replacing findElement by action function that handles click action by explicit waits
            action.click(By.cssSelector("[data-test-id='header-member-menu-button']"));

//            sleep(2);
            //Get account email
            WebElement accountLabel = driver.findElement(By.xpath("//section[@data-test-id='header-member-menu-popover']/descendant::ul/div/div/span"));
            String accountEmail = accountLabel.getText();
            //Print current page data
            String title = driver.getTitle();
            String url = driver.getCurrentUrl();
            System.out.println("Current title: " + title);
            System.out.println("Current URL: " + url);

            //Validate username displayed in profile button
            String expectedEmail = "joseccb1948@yahoo.com";
            Assert.assertEquals(expectedEmail, accountEmail);

        } catch(NoSuchElementException e){
            System.out.println("Test failed because of not found element: \n" + e.getMessage());
            Assert.assertTrue(false);
        }
    }

    @Test
    public void testCreateBoard(){
        login();
        //Open create board form
//        WebElement boardsButton = driver.findElement(By.cssSelector("[href*='boards']"));
//        boardsButton.click();
        action.click(By.cssSelector("[href*='boards']"));
//        WebElement newBoardButton = driver.findElement(By.cssSelector(".mod-add"));
//        newBoardButton.click();
        action.click(By.cssSelector(".mod-add"));
        //Set board name
//        WebElement titleBox = driver.findElement(By.cssSelector("[data-test-id='create-board-title-input']"));
//        titleBox.sendKeys("My board");
        action.sendText(By.cssSelector("[data-test-id='create-board-title-input']"), "My board");
        //Select public option
//        WebElement privateButton = driver.findElement(By.cssSelector(".icon-private"));
//        privateButton.click();
        action.click(By.cssSelector(".icon-private"));
//        WebElement publicOption = driver.findElement(By.cssSelector(".icon-public"));
//        publicOption.click();
        action.click(By.cssSelector(".icon-public"));
//        WebElement confirmButton = driver.findElement(By.cssSelector(".js-confirm"));
//        confirmButton.click();
        action.click(By.cssSelector(".js-confirm"));
        //Press create board
//        WebElement createButton = driver.findElement(By.cssSelector("[data-test-id='create-board-submit-button']"));
//        createButton.click();
        action.click(By.cssSelector("[data-test-id='create-board-submit-button']"));
//        sleep(5);
        //Validate board name
//        WebElement boardLabel = driver.findElement(By.cssSelector(".mod-board-name"));
        WebElement boardLabel = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mod-board-name")));
        String boardName = boardLabel.getText();
        String expectedName = "My board";

        Assert.assertEquals(expectedName, boardName);
    }

    @Test
    public void testAddDueDate(){
        login();
        //Load card
        driver.get("https://trello.com/c/Pd3nLwWm/2-implement-login");
//        sleep(3);

        WebElement attachButton = driver.findElement(By.cssSelector(".button-link.js-attach"));
        attachButton.click();

        WebElement attachFile = driver.findElement(By.cssSelector(".js-attach-file"));
        attachFile.sendKeys("D:\\otros\\webUI\\repo\\trello-api\\environment.json");


        //Expand Due Date
        WebElement dueDateButton = driver.findElement(By.cssSelector(".js-add-due-date"));
        dueDateButton.click();
//        sleep(6);
        //Click Next month
        WebElement nextButton = driver.findElement(By.cssSelector(".pika-next"));
        nextButton.click();
        //Select date 1
        WebElement dateToPick = driver.findElement(By.xpath("//button[text()='1']"));
        dateToPick.click();
        //Select reminder of 10 min
        WebElement reminderSelector = driver.findElement(By.cssSelector(".js-custom-reminder"));
        Select selector = new Select(reminderSelector);
        selector.selectByValue("0");
        List<WebElement> selected = selector.getAllSelectedOptions();
        for (WebElement element: selected) {
            String content = element.getText();
            System.out.println("selected element: " + content);
        }
        selector.selectByVisibleText("15 Minutes Before");
        selector.selectByIndex(5);

        WebElement saveButton = driver.findElement(By.cssSelector(".primary.wide.confirm"));
        saveButton.click();

        WebElement dueDateLabel = driver.findElement(By.cssSelector(".js-date-text"));
        String dueDate = dueDateLabel.getText();
        Assert.assertTrue(dueDate.contains("Oct 1"));
    }

    //Practice Day 4
    @Test
    public void testCreateList() {
        createBoard();
        //Refresh to make sure Add list button is visible
        driver.navigate().refresh();
        //Fill list data
//        WebElement addListButton = driver.findElement(By.cssSelector(".open-add-list"));
//        addListButton.click();
        action.click(By.cssSelector(".open-add-list"));
//        WebElement listNameField = driver.findElement(By.cssSelector(".list-name-input"));
//        listNameField.sendKeys("My new list");
        action.sendText(By.cssSelector(".list-name-input"), "My new list");
        //Press add button
//        WebElement modalAddButton = driver.findElement(By.cssSelector(".mod-list-add-button"));
//        m...lodalAddButton.click();
        action.click(By.cssSelector(".mod-list-add-button"));
        //Validate the name of created list
//        WebElement listLabel = driver.findElement(By.xpath("//textarea[text()='My new list']"));
//        String listName = listLabel.getText();
        String listName = action.getText(By.xpath("//textarea[text()='My new list']"));
        String expectedName = "My new list";
        Assert.assertEquals(expectedName, listName);

    }
    //Practice Day 4
    @Test
    public void testCreateCard(){
        createList();
//        sleep(3);
        //Open card creation form
//        WebElement addCardButton = driver.findElement(By.xpath("//textarea[text()='My new list']/parent::div/following-sibling::div/a"));
//        addCardButton.click();
        action.click(By.xpath("//textarea[text()='My new list']/parent::div/following-sibling::div/a"));
        //Fill card name
//        WebElement cardTextArea = driver.findElement(By.cssSelector(".list-card-composer-textarea"));
//        cardTextArea.sendKeys("My new card");
        action.sendText(By.cssSelector(".list-card-composer-textarea"), "My new card");
        //Press Add card button
//        WebElement addButton = driver.findElement(By.cssSelector(".js-add-card"));
//        addButton.click();
        action.click(By.cssSelector(".js-add-card"));
        //Validate card name
//        sleep(3);
//        WebElement cardCreated = driver.findElement(By.xpath("//span[text()='My new card']"));
//        String cardName = cardCreated.getText();
        String cardName = action.getText(By.xpath("//span[text()='My new card']"));
        String expectedCardName = "My new card";
        Assert.assertEquals(expectedCardName, cardName);
    }

    @Test
    public void testCreateBoardFromPlusButton(){
        login();
//        sleep(6);
        //Open create board form
        WebElement plusButton = driver.findElement(By.cssSelector("[data-test-id='header-create-menu-button']"));
        plusButton.click();
        //Click Create board button in popup
        WebElement createBoardButton = driver.findElement(By.cssSelector("[data-test-id='header-create-board-button']"));
        createBoardButton.click();
        //Set board name
        WebElement titleBox = driver.findElement(By.cssSelector("[data-test-id='create-board-title-input']"));
        titleBox.sendKeys("My board");
        //Select public option
        WebElement privateButton = driver.findElement(By.cssSelector("[aria-label='PrivateIcon']"));
        privateButton.click();
        WebElement publicOption = driver.findElement(By.xpath("//div[@class='atlaskit-portal']/descendant::ul/li[2]"));
        publicOption.click();
        WebElement confirmButton = driver.findElement(By.xpath("//div[@class='atlaskit-portal']/descendant::div/button"));
        confirmButton.click();
        //Press create board
        WebElement createButton = driver.findElement(By.cssSelector("[data-test-id='create-board-submit-button']"));
        createButton.click();
//        sleep(5);
        //Validate board name
        WebElement boardLabel = driver.findElement(By.cssSelector(".mod-board-name"));
        String boardName = boardLabel.getText();
        String expectedName = "My board";

        Assert.assertEquals(expectedName, boardName);

    }

    //Practice Day 4
    public void createList(){
        createBoard();
        driver.navigate().refresh();
//        sleep(10);
//        WebElement addListButton = driver.findElement(By.cssSelector(".open-add-list"));
//        WebElement addListButton = driver.findElement(By.cssSelector(".js-add-list"));
//        addListButton.click();
        action.click(By.cssSelector(".js-add-list"));
//        WebElement listNameField = driver.findElement(By.cssSelector(".list-name-input"));
//        listNameField.sendKeys("My new list");
        action.sendText(By.cssSelector(".list-name-input"), "My new list");
//        WebElement modalAddButton = driver.findElement(By.cssSelector(".mod-list-add-button"));
//        modalAddButton.click();
        action.click(By.cssSelector(".mod-list-add-button"));
    }

    public void createBoard(){
        login();
        //Open create board form
//        WebElement boardsButton = driver.findElement(By.cssSelector("[href*='boards']"));
//        boardsButton.click();
        action.click(By.cssSelector("[href*='boards"));
//        WebElement newBoardButton = driver.findElement(By.cssSelector(".mod-add"));
//        newBoardButton.click();
        action.click(By.cssSelector(".mod-add"));
        //Set board name
//        WebElement titleBox = driver.findElement(By.cssSelector("[data-test-id='create-board-title-input']"));
//        titleBox.sendKeys("My board");
        action.sendText(By.cssSelector("[data-test-id='create-board-title-input']"), "My board");
        //Select public option
//        WebElement privateButton = driver.findElement(By.cssSelector(".icon-private"));
//        privateButton.click();
        action.click(By.cssSelector(".icon-private"));
//        WebElement publicOption = driver.findElement(By.cssSelector(".icon-public"));
//        publicOption.click();
        action.click(By.cssSelector(".icon-public"));
//        WebElement confirmButton = driver.findElement(By.cssSelector(".js-confirm"));
//        confirmButton.click();
        action.click(By.cssSelector(".js-confirm"));
        //Press create board
//        WebElement createButton = driver.findElement(By.cssSelector("[data-test-id='create-board-submit-button']"));
//        createButton.click();
        action.click(By.cssSelector("[data-test-id='create-board-submit-button']"));
//        sleep(5);
        action.isElementVisible(By.cssSelector(".mod-board-name"));
    }

    public void login(){
        driver.get("https://trello.com/");
//        sleep(3);
        //Click login button
//        WebElement loginHomeButton = driver.findElement(By.cssSelector("[href='/login']"));
//        loginHomeButton.click();
        action.click(By.cssSelector("[href='/login']"));
//        sleep(6);
        //Set username
//        WebElement nameField = driver.findElement(By.cssSelector("[autocomplete='username']"));
//        nameField.sendKeys("joseccb1948@yahoo.com");
        action.sendText(By.cssSelector("[autocomplete='username']"), "joseccb1948@yahoo.com");
//        sleep(3);
        //Click login with Atlasian
        wait.until(ExpectedConditions.attributeContains(By.cssSelector("#login"), "value", "Atlassian"));
//        WebElement loginAtlasianButton = driver.findElement(By.cssSelector("#login"));
//        loginAtlasianButton.click();
        action.click(By.cssSelector("#login"));
//        sleep(5);
        //Set password
//        WebElement passwordField = driver.findElement(By.cssSelector("#password"));
//        passwordField.sendKeys("Control*1234");
        action.sendText(By.cssSelector("#password"), "Control*1234");
        //Click login
//        WebElement loginButton = driver.findElement(By.cssSelector("#login-submit"));
//        loginButton.click();
        action.click(By.cssSelector("#login-submit"));
//        sleep(20);
    }
}
