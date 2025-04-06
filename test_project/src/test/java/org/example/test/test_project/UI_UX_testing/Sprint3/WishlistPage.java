package org.example.test.test_project.UI_UX_testing.Sprint3;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.example.test.test_project.LogConfig.ExtendReport;
import org.openqa.selenium.WebDriver;

import org.example.test.test_project.WebBrowser.BrowserFactory;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class WishlistPage {
    private WebDriver driver;
    private static ExtentReports extent;
    private ExtentTest test;

    @BeforeAll
    public static void setupReport() {
        extent = ExtendReport.getInstance();
    }

    @BeforeEach
    public void setup(TestInfo testInfo) {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        test = extent.createTest(testInfo.getDisplayName() + " - " + timestamp);

    }

    public void login(String browser){
        try {
            driver = BrowserFactory.getDriver(browser);
            driver.get("http://localhost:3000/");
            Thread.sleep(2000);

            WebElement loginButton = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[1]/div/div/button[1]"));
            Assertions.assertTrue(loginButton.isDisplayed());

            loginButton.click();

            String currentUrl = driver.getCurrentUrl();
            String expectedUrl = "http://localhost:3000/login";

            if (currentUrl.equals(expectedUrl)) {
                test.pass("Change to login page successfully!");
            } else {
                test.fail("Page unchanged!");
            }
            Thread.sleep(1000);

            WebElement usernameField = driver.findElement(By.name("username"));
            usernameField.sendKeys("username2");
            WebElement passwordField = driver.findElement(By.name("password"));
            passwordField.sendKeys("abcxyz123");
            WebElement signInButton = driver.findElement(By.xpath("//button[span[text()='Sign In']]"));

            signInButton.click();
            test.pass("Log in successfully!");

            Thread.sleep(3000);

            WebElement icon3 = driver.findElement(By.xpath("//*[@id=\"root\"]/header/div[2]/div[3]"));
            Assertions.assertTrue(icon3.isDisplayed(),"WISHLIST icon did not display");

            icon3.click();
            test.pass("Clicking WISHLIST BUTTON successfully!");

            currentUrl = driver.getCurrentUrl();
            expectedUrl = "http://localhost:3000/wishlist";

            if (currentUrl.equals(expectedUrl)) {
                test.pass("Page changed to WISHLIST page successfully!");
            } else {
                test.fail("Page unchanged!");
            }
        }catch (Exception e){
            test.fail(e);
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"chrome","edge","firefox"})
    @Order(1)
    public void checkWishListComponent(String browser){
        try {
            test.info("Check Book in Wish List");
            login(browser);
            Thread.sleep(2000);

            WebElement book = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div/div[2]/div[1]/div[2]/div/div[1]"));
            Assertions.assertTrue(book.isDisplayed(),"Wish List Book did not display");
            test.pass("Wish List Book displayed!");

            WebElement bookTitle = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div/div[2]/div[1]/div[2]/div/div[2]/div[1]"));
            Assertions.assertTrue(bookTitle.isDisplayed(),"Book Title did not display");
            test.pass("Book Title Book displayed!");

            WebElement bookImage = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div/div[2]/div[1]/div[2]/div/div[1]/img"));
            Assertions.assertTrue(bookImage.isDisplayed(),"Book Image did not displayed!");
            test.pass("Book Image displayed!");

            WebElement bookAuthor = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div/div[2]/div[1]/div[2]/div/div[2]/div[3]"));
            Assertions.assertTrue(bookAuthor.isDisplayed(),"Book Author did not displayed!");
            test.pass("Book Author displayed!");

            WebElement bookPrice = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div/div[2]/div[1]/div[2]/div/div[2]/div[3]"));
            Assertions.assertTrue(bookPrice.isDisplayed(),"Book Price did not displayed!");
            test.pass("Book Price displayed!");

            WebElement sortByButton = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div/div[1]/div[1]/select"));
            Assertions.assertTrue(sortByButton.isDisplayed(),"Sort By Button did not displayed!");
            test.pass("Sort By Button displayed!");

            WebElement nextButton = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div/div[3]/button[4]"));
            Assertions.assertTrue(nextButton.isDisplayed());
            test.pass("Next Button displayed!");

            WebElement prevButton = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div/div[3]/button[1]"));
            Assertions.assertTrue(prevButton.isDisplayed());
            test.pass("Previous Button displayed!");

            WebElement numButton = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div/div[3]/button[3]"));
            Assertions.assertTrue(numButton.isDisplayed());
            test.pass("Page number Button displayed!");

            WebElement hoverElement = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div/div[2]/div[1]/div[2]/div/div[1]"));

            Actions actions = new Actions(driver);
            actions.moveToElement(hoverElement).perform();

            WebElement AddToCart = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div/div[2]/div[1]/div[2]/div/div[1]/button"));
            Assertions.assertTrue(AddToCart.isDisplayed());
            test.pass("Add to cart button displayed!");

            WebElement removeButt = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div/div[2]/div[1]/div[1]"));
            Assertions.assertTrue(removeButt.isDisplayed());
            test.pass("Remove Button displayed!");

        }catch (Exception e){
            test.fail(e.getMessage());
        }
    }
    
    @ParameterizedTest
    @ValueSource(strings = {"chrome","edge","firefox"})
    @Order(2)
    public void checkSortBookInWishList(String browser){
        try {

            login(browser);
            test.info("Check Book in Wish List");


            WebElement sortByButton = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div/div[1]/div[1]/select"));
            Assertions.assertTrue(sortByButton.isDisplayed(),"Sort By Button did not displayed!");
            test.pass("Sort By Button displayed!");

            if (!sortByButton.isEnabled()) {
                test.fail("Sort By button is disabled!");
            } else {
                test.pass("Sort By button is clickable!");
                sortByButton.click();
                WebElement za = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div/div[1]/div[1]/select/option[2]"));
                Assertions.assertTrue(za.isDisplayed(),"Sort By Z-A did not displayed!");
                test.pass("Sort By Z-A displayed!");

                List<WebElement> items = driver.findElements(By.className("product-title"));
                test.pass("Total products found: " + items.size());
                if (items.size() == 1){
                    return;
                } else {
                    String beforeSort = items.get(0).getText();

                    za.click();
                    Thread.sleep(1000);

                    List<WebElement> newItems = driver.findElements(By.className("product-title"));
                    String afterSort = newItems.get(0).getText();

                    if (!beforeSort.equals(afterSort)) {
                        test.pass("Sorting works correctly!");
                    } else {
                        test.fail("Sorting did not change the list!");
                    }
                }
            }


        }catch (Exception e){
            test.fail(e.getMessage());
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"chrome","edge","firefox"})
    @Order(3)
    public void checkButton(String browser){
        try {

            login(browser);
            Thread.sleep(2000);
            test.info("Check Button");
            WebElement nextButton = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div/div[3]/button[4]"));
            Assertions.assertTrue(nextButton.isDisplayed());
            nextButton.click();
            test.pass("Click Next Button!");

            WebElement prevButton = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div/div[3]/button[1]"));
            Assertions.assertTrue(prevButton.isDisplayed());
            prevButton.click();
            test.pass("Click Previous Button!");

            WebElement numButton = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div/div[3]/button[3]"));
            Assertions.assertTrue(numButton.isDisplayed());
            numButton.click();
            test.pass("Click Page number Button!");

        }catch (Exception e){
            test.fail(e.getMessage());
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"chrome","edge","firefox"})
    @Order(4)
    public void checkGotoBookDetail(String browser){
        try {

            login(browser);
            Thread.sleep(2000);
            test.info("Click book icon to go to book detail page");
            WebElement book = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div/div[2]/div[1]/div[2]/div/div[1]"));
            Assertions.assertTrue(book.isDisplayed(),"Wish List Book did not display");
            book.click();
            Thread.sleep(1000);
            test.pass("Click book");

            String currentUrl = driver.getCurrentUrl();
            String expectedUrl = "http://localhost:3000/product";

            if (currentUrl.contains(expectedUrl)) {
                test.pass("Page changed to book detail page successfully!");
            } else {
                test.fail("Page unchanged!");
            }
        
        }catch (Exception e){
            test.fail(e.getMessage());
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"chrome","edge","firefox"})
    @Order(5)
    public void checkRemoveBook(String browser){
        try {

            login(browser);
            Thread.sleep(2000);
            test.info("Click add to cart button and Remove a book at wishlist page.");
            
            WebElement hoverElement = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div/div[2]/div[1]/div[2]/div/div[1]"));

            Actions actions = new Actions(driver);
            actions.moveToElement(hoverElement).perform();

            WebElement AddToCart = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div/div[2]/div[1]/div[2]/div/div[1]/button"));
            Assertions.assertTrue(AddToCart.isDisplayed());
            test.pass("Add to cart button displayed!");

            AddToCart.click();
            test.pass("Click Add To Cart button");

            WebElement removeButt = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div/div[2]/div[1]/div[1]"));
            Assertions.assertTrue(removeButt.isDisplayed());
            test.pass("Remove Button displayed!");

            removeButt.click();
            test.pass("Remove a book");
        
        }catch (Exception e){
            test.fail(e.getMessage());
        }
    }



    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @AfterAll
    public static void closeTest(){
        ExtendReport.closeReport();
    }
}
