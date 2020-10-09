package trello.ui;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import trello.ui.core.DriverManager;
import trello.ui.pages.*;

public class trelloTestsPOM {
    private WebDriver driver;

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
//        driver = new ChromeDriver();//Driver().initDriver();
//        login = new Login(driver);
//        header = new Header(driver);

        //Configure implicit wait
//        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //Configure explicit wait
//        wait = new WebDriverWait(driver, 20);
//        action = new WebDriverAction(driver, wait);

    }

    @After
    public void tearDown(){
        //Close browser
//        driver.quit();
        DriverManager.getInstance().getDriver().quit();
    }

    @Test
    public void testLogin() {
        Welcome welcome = new Welcome();
        Login login = welcome.login();
        Header header = login.login("joseccb1948@yahoo.com", "Control*1234");
        Profile profile = header.goToProfileMenu();
        String actualEmail = profile.getEmailAccount();
        String expectedEmail = "joseccb1948@yahoo.com";
        Assert.assertEquals(expectedEmail, actualEmail);
    }

    @Test
    public void testCreateBoard(){
        Welcome welcome = new Welcome();
        Login login = welcome.login();
        Header header = login.login("joseccb1948@yahoo.com", "Control*1234");

        CreateMenu createMenu = header.openCreateMenu();
        BoardForm boardForm = createMenu.openCreateBoardForm();
        Board board = boardForm.createBoard("My board");
        String actualName = board.getBoardName();
        String expectedName = "My board";
        Assert.assertEquals(expectedName, actualName);
    }

    @Test
    public void testCreateList(){
        Welcome welcome = new Welcome();
        Login login = welcome.login();
        Header header = login.login("joseccb1948@yahoo.com", "Control*1234");
        CreateMenu createMenu = header.openCreateMenu();
        BoardForm boardForm = createMenu.openCreateBoardForm();
        Board board = boardForm.createBoard("My board");

        ListForm listForm = board.openListForm();
        ListCreated listCreated = listForm.createList("My list");
        String actualName = listCreated.getListName();
        String expectedName = "My list";
        Assert.assertEquals(actualName, expectedName);

    }

    @Test
    public void testCreateCard(){
        Welcome welcome = new Welcome();
        Login login = welcome.login();
        Header header = login.login("joseccb1948@yahoo.com", "Control*1234");
        CreateMenu createMenu = header.openCreateMenu();
        BoardForm boardForm = createMenu.openCreateBoardForm();
        Board board = boardForm.createBoard("My board");
        ListForm listForm = board.openListForm();
        ListCreated listCreated = listForm.createList("My list");

        CardForm cardForm = listCreated.openCardForm();
        Card card = cardForm.createCard("My card");
        String actualName = card.getCardByTitle("My card");
        String expectedName = "My card";
        Assert.assertEquals(actualName, expectedName);

    }

//    @Test
//    public void testAddDueDate(){
//        login();
//        //Load card
//        driver.get("https://trello.com/c/Pd3nLwWm/2-implement-login");
////        sleep(3);
//        //Expand Due Date
//        WebElement dueDateButton = driver.findElement(By.cssSelector(".js-add-due-date"));
//        dueDateButton.click();
////        sleep(6);
//        //Click Next month
//        WebElement nextButton = driver.findElement(By.cssSelector(".pika-next"));
//        nextButton.click();
//        //Select date 1
//        WebElement dateToPick = driver.findElement(By.xpath("//button[text()='1']"));
//        dateToPick.click();
//        //Select reminder of 10 min
//        WebElement reminderSelector = driver.findElement(By.cssSelector(".js-custom-reminder"));
//        Select selector = new Select(reminderSelector);
//        selector.selectByValue("0");
//        List<WebElement> selected = selector.getAllSelectedOptions();
//        for (WebElement element: selected) {
//            String content = element.getText();
//            System.out.println("selected element: " + content);
//        }
//        selector.selectByVisibleText("15 Minutes Before");
//        selector.selectByIndex(5);
//
//        WebElement saveButton = driver.findElement(By.cssSelector(".primary.wide.confirm"));
//        saveButton.click();
//
//        WebElement dueDateLabel = driver.findElement(By.cssSelector(".js-date-text"));
//        String dueDate = dueDateLabel.getText();
//        Assert.assertTrue(dueDate.contains("Oct 1"));
//    }
//
//    //Practice Day 4
//    @Test
//    public void testCreateList() {
//        createBoard();
//        //Refresh to make sure Add list button is visible
//        WebElement addListButton = driver.findElement(By.cssSelector(".open-add-list"));
//        driver.navigate().refresh();
//        addListButton.click();
        //Fill list data
////        addListButton.click();
//        action.click(By.cssSelector(".open-add-list"));
////        WebElement listNameField = driver.findElement(By.cssSelector(".list-name-input"));
////        listNameField.sendKeys("My new list");
//        action.sendText(By.cssSelector(".list-name-input"), "My new list");
//        //Press add button
////        WebElement modalAddButton = driver.findElement(By.cssSelector(".mod-list-add-button"));
////        modalAddButton.click();
//        action.click(By.cssSelector(".mod-list-add-button"));
//        //Validate the name of created list
////        WebElement listLabel = driver.findElement(By.xpath("//textarea[text()='My new list']"));
////        String listName = listLabel.getText();
//        String listName = action.getText(By.xpath("//textarea[text()='My new list']"));
//        String expectedName = "My new list";
//        Assert.assertEquals(expectedName, listName);
//
//    }
//    //Practice Day 4
//    @Test
//    public void testCreateCard(){
//        createList();
////        sleep(3);
//        //Open card creation form
////        WebElement addCardButton = driver.findElement(By.xpath("//textarea[text()='My new list']/parent::div/following-sibling::div/a"));
////        addCardButton.click();
//        action.click(By.xpath("//textarea[text()='My new list']/parent::div/following-sibling::div/a"));
//        //Fill card name
////        WebElement cardTextArea = driver.findElement(By.cssSelector(".list-card-composer-textarea"));
////        cardTextArea.sendKeys("My new card");
//        action.sendText(By.cssSelector(".list-card-composer-textarea"), "My new card");
//        //Press Add card button
////        WebElement addButton = driver.findElement(By.cssSelector(".js-add-card"));
////        addButton.click();
//        action.click(By.cssSelector(".js-add-card"));
//        //Validate card name
////        sleep(3);
////        WebElement cardCreated = driver.findElement(By.xpath("//span[text()='My new card']"));
////        String cardName = cardCreated.getText();
//        String cardName = action.getText(By.xpath("//span[text()='My new card']"));
//        String expectedCardName = "My new card";
//        Assert.assertEquals(expectedCardName, cardName);
//    }
//
//    @Test
//    public void testCreateBoardFromPlusButton(){
//        login();
////        sleep(6);
//        //Open create board form
//        WebElement plusButton = driver.findElement(By.cssSelector("[data-test-id='header-create-menu-button']"));
//        plusButton.click();
//        //Click Create board button in popup
//        WebElement createBoardButton = driver.findElement(By.cssSelector("[data-test-id='header-create-board-button']"));
//        createBoardButton.click();
//        //Set board name
//        WebElement titleBox = driver.findElement(By.cssSelector("[data-test-id='create-board-title-input']"));
//        titleBox.sendKeys("My board");
//        //Select public option
//        WebElement privateButton = driver.findElement(By.cssSelector("[aria-label='PrivateIcon']"));
//        privateButton.click();
//        WebElement publicOption = driver.findElement(By.xpath("//div[@class='atlaskit-portal']/descendant::ul/li[2]"));
//        publicOption.click();
//        WebElement confirmButton = driver.findElement(By.xpath("//div[@class='atlaskit-portal']/descendant::div/button"));
//        confirmButton.click();
//        //Press create board
//        WebElement createButton = driver.findElement(By.cssSelector("[data-test-id='create-board-submit-button']"));
//        createButton.click();
////        sleep(5);
//        //Validate board name
//        WebElement boardLabel = driver.findElement(By.cssSelector(".mod-board-name"));
//        String boardName = boardLabel.getText();
//        String expectedName = "My board";
//
//        Assert.assertEquals(expectedName, boardName);
//
//    }
//
//    //Practice Day 4
//    public void createList(){
//        createBoard();
//        driver.navigate().refresh();
////        sleep(10);
////        WebElement addListButton = driver.findElement(By.cssSelector(".open-add-list"));
////        WebElement addListButton = driver.findElement(By.cssSelector(".js-add-list"));
////        addListButton.click();
//        action.click(By.cssSelector(".js-add-list"));
////        WebElement listNameField = driver.findElement(By.cssSelector(".list-name-input"));
////        listNameField.sendKeys("My new list");
//        action.sendText(By.cssSelector(".list-name-input"), "My new list");
////        WebElement modalAddButton = driver.findElement(By.cssSelector(".mod-list-add-button"));
////        modalAddButton.click();
//        action.click(By.cssSelector(".mod-list-add-button"));
//    }
//
//    public void createBoard(){
//        login();
//        //Open create board form
////        WebElement boardsButton = driver.findElement(By.cssSelector("[href*='boards']"));
////        boardsButton.click();
//        action.click(By.cssSelector("[href*='boards"));
////        WebElement newBoardButton = driver.findElement(By.cssSelector(".mod-add"));
////        newBoardButton.click();
//        action.click(By.cssSelector(".mod-add"));
//        //Set board name
////        WebElement titleBox = driver.findElement(By.cssSelector("[data-test-id='create-board-title-input']"));
////        titleBox.sendKeys("My board");
//        action.sendText(By.cssSelector("[data-test-id='create-board-title-input']"), "My board");
//        //Select public option
////        WebElement privateButton = driver.findElement(By.cssSelector(".icon-private"));
////        privateButton.click();
//        action.click(By.cssSelector(".icon-private"));
////        WebElement publicOption = driver.findElement(By.cssSelector(".icon-public"));
////        publicOption.click();
//        action.click(By.cssSelector(".icon-public"));
////        WebElement confirmButton = driver.findElement(By.cssSelector(".js-confirm"));
////        confirmButton.click();
//        action.click(By.cssSelector(".js-confirm"));
//        //Press create board
////        WebElement createButton = driver.findElement(By.cssSelector("[data-test-id='create-board-submit-button']"));
////        createButton.click();
//        action.click(By.cssSelector("[data-test-id='create-board-submit-button']"));
////        sleep(5);
//        action.isElementVisible(By.cssSelector(".mod-board-name"));
//    }
//
//    public void login(){
//        driver.get("https://trello.com/");
////        sleep(3);
//        //Click login button
////        WebElement loginHomeButton = driver.findElement(By.cssSelector("[href='/login']"));
////        loginHomeButton.click();
//        action.click(By.cssSelector("[href='/login']"));
////        sleep(6);
//        //Set username
////        WebElement nameField = driver.findElement(By.cssSelector("[autocomplete='username']"));
////        nameField.sendKeys("joseccb1948@yahoo.com");
//        action.sendText(By.cssSelector("[autocomplete='username']"), "joseccb1948@yahoo.com");
////        sleep(3);
//        //Click login with Atlasian
//        wait.until(ExpectedConditions.attributeContains(By.cssSelector("#login"), "value", "Atlassian"));
////        WebElement loginAtlasianButton = driver.findElement(By.cssSelector("#login"));
////        loginAtlasianButton.click();
//        action.click(By.cssSelector("#login"));
////        sleep(5);
//        //Set password
////        WebElement passwordField = driver.findElement(By.cssSelector("#password"));
////        passwordField.sendKeys("Control*1234");
//        action.sendText(By.cssSelector("#password"), "Control*1234");
//        //Click login
////        WebElement loginButton = driver.findElement(By.cssSelector("#login-submit"));
////        loginButton.click();
//        action.click(By.cssSelector("#login-submit"));
////        sleep(20);
//    }
}
