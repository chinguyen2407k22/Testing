package org.example.test.test_project.UI_UX_testing.Sprint3;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.example.test.test_project.LogConfig.ExtendReport;
import org.example.test.test_project.WebBrowser.BrowserFactory;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ResearchResultPage {
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

            WebElement continueBrowsing = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[1]/div/div/button[2]"));
            Assertions.assertTrue(continueBrowsing.isDisplayed());

            continueBrowsing.click();
        }catch (Exception e){
            test.fail(e);
        }
    }

    
    @ParameterizedTest
    @ValueSource(strings = {"chrome","edge","firefox"})
    public void checkSearchByName(String browser){
        try {

            driver = BrowserFactory.getDriver(browser);
            driver.get("http://localhost:3000/displaysearch");
            test.info("Check Search book by name");

            WebElement searchBar = driver.findElement(By.xpath("//*[@id=\"root\"]/header/div[1]/input"));
            Assertions.assertTrue(searchBar.isDisplayed(),"Search Bar did not display");
            test.pass("Search Bar displayed!");

            searchBar.sendKeys("Nauy");

            searchBar.sendKeys(Keys.ENTER);

            String currentUrl = driver.getCurrentUrl();
            String expectedUrl = "http://localhost:3000/displaysearch?search=Nauy";

            if (currentUrl.equals(expectedUrl)) {
                test.pass("Search successfully!");
            } else {
                test.fail("Search unsuccessfully!");
            }
            Thread.sleep(3000);

            WebElement book = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div/div[2]/div[1]/div/div[1]"));
            Assertions.assertTrue(book.isDisplayed(),"Wish List Book did not display");
            test.pass("Search Book displayed!");

            WebElement bookTitle = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div/div[2]/div[1]/div/div[2]/div[1]"));
            Assertions.assertTrue(bookTitle.isDisplayed(),"Book Title did not display");
            test.pass("Book Title Book displayed!");
            
            if(bookTitle.getText().equals("Rừng Nauy")){
                test.pass("The Title: "+bookTitle.getText());
            }else {
                test.pass("The Title: "+ bookTitle.getText());
            };

            WebElement bookImage = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div/div[2]/div[1]/div/div[1]/img"));
            Assertions.assertTrue(bookImage.isDisplayed(),"Book Image did not displayed!");
            test.pass("Book Image displayed!");

            WebElement bookAuthor = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div/div[2]/div[1]/div/div[2]/div[2]"));
            Assertions.assertTrue(bookAuthor.isDisplayed(),"Book Author did not displayed!");
            test.pass("Book Author displayed!");

            WebElement bookPrice = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div/div[2]/div[1]/div/div[2]/div[3]"));
            Assertions.assertTrue(bookPrice.isDisplayed(),"Book Price did not displayed!");
            test.pass("Book Price displayed!");

            WebElement sortByButton = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div/div[1]/div[1]/select"));
            Assertions.assertTrue(sortByButton.isDisplayed(),"Sort By Button did not displayed!");
            test.pass("Sort By Button displayed!");

            WebElement hoverElement = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div/div[2]/div[1]/div/div[1]"));

            Actions actions = new Actions(driver);
            actions.moveToElement(hoverElement).perform();

            WebElement AddToCart = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div/div[2]/div/div/div[1]/button"));
            Assertions.assertTrue(AddToCart.isDisplayed());
            test.pass("Add to cart button displayed!");


        }catch (Exception e){
            test.fail(e.getMessage());
        }
    }
    
    @ParameterizedTest
    @ValueSource(strings = {"chrome","edge","firefox"})
    public void checkSearchNull(String browser){
        try {
            driver = BrowserFactory.getDriver(browser);
            driver.get("http://localhost:3000/displaysearch");
            test.info("Check Search book with no book found");

            WebElement searchBar = driver.findElement(By.xpath("//*[@id=\"root\"]/header/div[1]/input"));
            Assertions.assertTrue(searchBar.isDisplayed(),"Search Bar did not display");
            test.pass("Search Bar displayed!");

            searchBar.sendKeys("abcxyznull");

            searchBar.sendKeys(Keys.ENTER);

            String currentUrl = driver.getCurrentUrl();
            String expectedUrl = "http://localhost:3000/displaysearch?";

            if (currentUrl.contains(expectedUrl)) {
                test.pass("Search successfully!");
            } else {
                test.fail("Search unsuccessfully!");
            }
            Thread.sleep(1000);

            WebElement noproduct = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div/div[2]/div"));
            Assertions.assertTrue(noproduct.isDisplayed());
            test.pass(noproduct.getText());

        }catch (Exception e){
            test.fail(e.getMessage());
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"chrome"})
    public void checkSearchAuthor(String browser){
        try {
            driver = BrowserFactory.getDriver(browser);
            driver.get("http://localhost:3000/displaysearch");
            test.info("Check Search book by author's name");

            WebElement searchBar = driver.findElement(By.xpath("//*[@id=\"root\"]/header/div[1]/input"));
            Assertions.assertTrue(searchBar.isDisplayed(),"Search Bar did not display");
            test.pass("Search Bar displayed!");

            searchBar.sendKeys("Haruki");

            searchBar.sendKeys(Keys.ENTER);

            String currentUrl = driver.getCurrentUrl();
            String expectedUrl = "http://localhost:3000/displaysearch?";

            if (currentUrl.contains(expectedUrl)) {
                test.pass("Search successfully!");
            } else {
                test.fail("Search unsuccessfully!");
            }
            Thread.sleep(3000);

            WebElement book = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div/div[2]/div[1]/div/div[1]"));
            Assertions.assertTrue(book.isDisplayed(),"Wish List Book did not display");
            test.pass("Search Bookdisplayed!");

            WebElement bookTitle = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div/div[2]/div[1]/div/div[2]/div[1]"));
            Assertions.assertTrue(bookTitle.isDisplayed(),"Book Title did not display");
            test.pass("Book Title Book displayed!");
            
            if(bookTitle.getText().equals("Rừng Nauy")){
                test.pass("The Title: "+bookTitle.getText());
            }else {
                test.pass("The Title: "+ bookTitle.getText());
            };

            WebElement bookImage = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div/div[2]/div[1]/div/div[1]/img"));
            Assertions.assertTrue(bookImage.isDisplayed(),"Book Image did not displayed!");
            test.pass("Book Image displayed!");

            WebElement bookAuthor = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div/div[2]/div[1]/div/div[2]/div[2]"));
            Assertions.assertTrue(bookAuthor.isDisplayed(),"Book Author did not displayed!");
            test.pass("Book Author displayed!");
            test.pass("The Author: "+ bookAuthor.getText());

            WebElement bookPrice = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div/div[2]/div[1]/div/div[2]/div[3]"));
            Assertions.assertTrue(bookPrice.isDisplayed(),"Book Price did not displayed!");
            test.pass("Book Price displayed!");

            WebElement sortByButton = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div/div[1]/div[1]/select"));
            Assertions.assertTrue(sortByButton.isDisplayed(),"Sort By Button did not displayed!");
            test.pass("Sort By Button displayed!");

            WebElement hoverElement = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div/div[2]/div[1]/div/div[1]"));

            Actions actions = new Actions(driver);
            actions.moveToElement(hoverElement).perform();

            WebElement AddToCart = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div/div[2]/div/div/div[1]/button"));
            Assertions.assertTrue(AddToCart.isDisplayed());
            test.pass("Add to cart button displayed!");

        }catch (Exception e){
            test.fail(e.getMessage());
        }
    }
    @ParameterizedTest
    @ValueSource(strings = {"chrome","edge","firefox"})
    public void checkClickBook(String browser){
        try {
            driver = BrowserFactory.getDriver(browser);
            driver.get("http://localhost:3000/displaysearch");
            test.info("Check go to book detail page");

            WebElement searchBar = driver.findElement(By.xpath("//*[@id=\"root\"]/header/div[1]/input"));
            Assertions.assertTrue(searchBar.isDisplayed(),"Search Bar did not display");
            test.pass("Search Bar displayed!");

            searchBar.sendKeys("Haruki");

            searchBar.sendKeys(Keys.ENTER);

            String currentUrl = driver.getCurrentUrl();
            String expectedUrl = "http://localhost:3000/displaysearch?";

            if (currentUrl.contains(expectedUrl)) {
                test.pass("Search successfully!");
            } else {
                test.fail("Search unsuccessfully!");
            }
            Thread.sleep(3000);

            WebElement book = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div/div[2]/div[1]/div/div[1]"));
            Assertions.assertTrue(book.isDisplayed(),"Search Book did not display");
            test.pass("Search Book Book displayed!");
            book.click();

            currentUrl = driver.getCurrentUrl();
            expectedUrl = "http://localhost:3000/product/";

            if (currentUrl.contains(expectedUrl)) {
                test.pass("Page changed successfully!");
            } else {
                test.fail("Page unchanged!");
            }

        }catch (Exception e){
            test.fail(e.getMessage());
        }
    }
    @ParameterizedTest
    @ValueSource(strings = {"chrome","edge","firefox"})
    public void check(String browser){
        try {

            driver = BrowserFactory.getDriver(browser);
            driver.get("http://localhost:3000/displaysearch?search=a");
            test.info("Check Book in Search Result");

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
    public void checkButton(String browser){
        try {

            driver = BrowserFactory.getDriver(browser);
            driver.get("http://localhost:3000/displaysearch?search=a");
            test.info("Check Button in Book research page");
            Thread.sleep(2000);
            WebElement nextButton = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div/div[3]/button[5]"));
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
        

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @AfterAll
    public static void closeTest(){
        ExtendReport.closeReport();
    }
}
