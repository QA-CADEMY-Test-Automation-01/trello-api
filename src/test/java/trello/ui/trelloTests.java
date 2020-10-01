package trello.ui;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class trelloTests {
    public WebDriver driver;

    @Before
    public void setUp(){
        //Configure web driver manager
//        WebDriverManager.chromedriver().setup();
        //Headless Mode
//        ChromeOptions chromeOptions = new ChromeOptions();
//        chromeOptions.addArguments("--headless");
//        chromeOptions.setHeadless(true);
//        driver = new ChromeDriver(chromeOptions);
//        driver.manage().window().setSize(new Dimension(1920, 1080));

        //Create driver adn open browser
//        driver = new ChromeDriver();

        //Practice from day 3
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.setHeadless(true);
        driver = new FirefoxDriver(firefoxOptions);
        driver.manage().window().setSize(new Dimension(1920, 1080));
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
            sleep(3);
            //Click login button
            WebElement loginHomeButton = driver.findElement(By.cssSelector("[href*='login']"));
            loginHomeButton.click();
            sleep(3);
            //Set username
            WebElement nameField = driver.findElement(By.cssSelector("[autocomplete='username']"));
            nameField.sendKeys("joseccb1948@yahoo.com");
            sleep(3);
            //Click login with Atlasian
            WebElement loginAtlasianButton = driver.findElement(By.cssSelector("#login"));
            loginAtlasianButton.click();
            sleep(3);
            //Set password
            WebElement passwordField = driver.findElement(By.cssSelector("#password"));
            passwordField.sendKeys("Control*1234");
            //Click login
            WebElement loginButton = driver.findElement(By.cssSelector("#login-submit"));
            loginButton.click();
            sleep(15);
            //Click profile button
            WebElement profileButton = driver.findElement(By.cssSelector("[data-test-id='header-member-menu-button']"));
            profileButton.click();

            sleep(2);
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
        WebElement boardsButton = driver.findElement(By.cssSelector("[href*='boards']"));
        boardsButton.click();
        WebElement newBoardButton = driver.findElement(By.cssSelector(".mod-add"));
        newBoardButton.click();
        //Set board name
        WebElement titleBox = driver.findElement(By.cssSelector("[data-test-id='create-board-title-input']"));
        titleBox.sendKeys("My board");
        //Select public option
        WebElement privateButton = driver.findElement(By.cssSelector(".icon-private"));
        privateButton.click();
        WebElement publicOption = driver.findElement(By.cssSelector(".icon-public"));
        publicOption.click();
        WebElement confirmButton = driver.findElement(By.cssSelector(".js-confirm"));
        confirmButton.click();
        //Press create board
        WebElement createButton = driver.findElement(By.cssSelector("[data-test-id='create-board-submit-button']"));
        createButton.click();
        sleep(5);
        //Validate board name
        WebElement boardLabel = driver.findElement(By.cssSelector(".mod-board-name"));
        String boardName = boardLabel.getText();
        String expectedName = "My board";

        Assert.assertEquals(expectedName, boardName);
    }

    @Test
    public void testAddDueDate(){
        login();
        //Load card
        driver.get("https://trello.com/c/Pd3nLwWm/2-implement-login");
        sleep(3);
        //Expand Due Date
        WebElement dueDateButton = driver.findElement(By.cssSelector(".js-add-due-date"));
        dueDateButton.click();
        sleep(6);
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

    @Test
    public void testCreateList() {
        createBoard();
        //Refresh to make sure Add list button is visible
        driver.navigate().refresh();
        //Fill list data
        WebElement addListButton = driver.findElement(By.cssSelector(".open-add-list"));
        addListButton.click();
        WebElement listNameField = driver.findElement(By.cssSelector(".list-name-input"));
        listNameField.sendKeys("My new list");
        //Press add button
        WebElement modalAddButton = driver.findElement(By.cssSelector(".mod-list-add-button"));
        modalAddButton.click();
        //Validate the name of created list
        WebElement listLabel = driver.findElement(By.xpath("//textarea[text()='My new list']"));
        String listName = listLabel.getText();
        String expectedName = "My new list";
        Assert.assertEquals(expectedName, listName);

    }
    //Practice from Day 3
    @Test
    public void testCreateCard(){
        createList();
        sleep(3);
        //Open card creation form
        WebElement addCardButton = driver.findElement(By.xpath("//textarea[text()='My new list']/parent::div/following-sibling::div/a"));
        addCardButton.click();
        //Fill card name
        WebElement cardTextArea = driver.findElement(By.cssSelector(".list-card-composer-textarea"));
        cardTextArea.sendKeys("My new card");
        //Press Add card button
        WebElement addButton = driver.findElement(By.cssSelector(".js-add-card"));
        addButton.click();
        //Validate card name
        sleep(3);
        WebElement cardCreated = driver.findElement(By.xpath("//span[text()='My new card']"));
        String cardName = cardCreated.getText();
        String expectedCardName = "My new card";
        Assert.assertEquals(expectedCardName, cardName);
    }
    //Practice from Day 3
    @Test
    public void testCreateBoardFromPlusButton(){
        login();
        sleep(6);
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
        sleep(5);
        //Validate board name
        WebElement boardLabel = driver.findElement(By.cssSelector(".mod-board-name"));
        String boardName = boardLabel.getText();
        String expectedName = "My board";

        Assert.assertEquals(expectedName, boardName);

    }

    public void sleep(int timeInSeconds){
        try{
            Thread.sleep(timeInSeconds*1000);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void createList(){
        createBoard();
        driver.navigate().refresh();
        sleep(10);
//        WebElement addListButton = driver.findElement(By.cssSelector(".open-add-list"));
        //Practice from Day 3: Firefox driver needs another selector to click on Add list button
        WebElement addListButton = driver.findElement(By.cssSelector(".js-add-list"));
        addListButton.click();
        WebElement listNameField = driver.findElement(By.cssSelector(".list-name-input"));
        listNameField.sendKeys("My new list");
        WebElement modalAddButton = driver.findElement(By.cssSelector(".mod-list-add-button"));
        modalAddButton.click();
    }

    public void createBoard(){
        login();
        //Open create board form
        WebElement boardsButton = driver.findElement(By.cssSelector("[href*='boards']"));
        boardsButton.click();
        WebElement newBoardButton = driver.findElement(By.cssSelector(".mod-add"));
        newBoardButton.click();
        //Set board name
        WebElement titleBox = driver.findElement(By.cssSelector("[data-test-id='create-board-title-input']"));
        titleBox.sendKeys("My board");
        //Select public option
        WebElement privateButton = driver.findElement(By.cssSelector(".icon-private"));
        privateButton.click();
        WebElement publicOption = driver.findElement(By.cssSelector(".icon-public"));
        publicOption.click();
        WebElement confirmButton = driver.findElement(By.cssSelector(".js-confirm"));
        confirmButton.click();
        //Press create board
        WebElement createButton = driver.findElement(By.cssSelector("[data-test-id='create-board-submit-button']"));
        createButton.click();
        sleep(5);
    }
    public void login(){
        driver.get("https://trello.com/");
        sleep(3);
        //Click login button
        WebElement loginHomeButton = driver.findElement(By.cssSelector("[href='/login']"));
        loginHomeButton.click();
        sleep(6);
        //Set username
        WebElement nameField = driver.findElement(By.cssSelector("[autocomplete='username']"));
        nameField.sendKeys("joseccb1948@yahoo.com");
        sleep(3);
        //Click login with Atlasian
        WebElement loginAtlasianButton = driver.findElement(By.cssSelector("#login"));
        loginAtlasianButton.click();
        sleep(5);
        //Set password
        WebElement passwordField = driver.findElement(By.cssSelector("#password"));
        passwordField.sendKeys("Control*1234");
        //Click login
        WebElement loginButton = driver.findElement(By.cssSelector("#login-submit"));
        loginButton.click();
        sleep(20);
    }
}
