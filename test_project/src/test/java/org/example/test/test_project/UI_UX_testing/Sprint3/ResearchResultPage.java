package org.example.test.test_project.UI_UX_testing.Sprint3;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.example.test.test_project.LogConfig.ExtendReport;
import org.example.test.test_project.WebBrowser.BrowserFactory;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.WebDriver;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

    @ValueSource(strings = {"firefox"})
    @ParameterizedTest
    //@ValueSource(strings = {"chrome","edge","firefox"})
    public void check(String browser){
        try {
            driver = BrowserFactory.getDriver(browser);
            driver.get("http://localhost:3000/displaysearch");
            test.info("Check Search book by name");


        }catch (Exception e){
            test.fail(e.getMessage());
        }
    }

    /*
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

            searchBar.sendKeys("programmer");

            searchBar.sendKeys(Keys.ENTER);

            String currentUrl = driver.getCurrentUrl();
            String expectedUrl = "http://localhost:3000/displaysearch?search=programmer";

            if (currentUrl.equals(expectedUrl)) {
                test.pass("Search successfully!");
            } else {
                test.fail("Search unsuccessfully!");
            }
            Thread.sleep(3000);

            WebElement book = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div/div[2]/div/div"));
            Assertions.assertTrue(book.isDisplayed(),"Researched Book did not display");
            test.pass("Researched Book displayed!");

            WebElement bookTitle = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div/div[2]/div/div/div/h3"));
            if(bookTitle.getText().equals("The Pragmatic Programmer")){
                test.pass("The Title Pragmatic Programmer was found!");
            }else {
                test.pass("The Title Pragmatic Programmer wasn't found!");
            };


            WebElement bookImage = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div/div[2]/div/div/img"));
            Assertions.assertTrue(bookImage.isDisplayed(),"Book Image did not displayed!");
            test.pass("Book Image displayed!");

            WebElement bookAuthor = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div/div[2]/div/div/div/p[1]"));
            Assertions.assertTrue(bookAuthor.isDisplayed(),"Book Author did not displayed!");
            test.pass("Book Author displayed!");

            WebElement bookPrice = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div/div[2]/div/div/div/p[2]"));
            Assertions.assertTrue(bookPrice.isDisplayed(),"Book Price did not displayed!");
            test.pass("Book Price displayed!");

            WebElement addToCart = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div/div[2]/div/div/button"));
            Assertions.assertTrue(addToCart.isDisplayed(),"Add To Cart Button did not displayed!");
            test.pass("Add To Cart Button displayed!");

            WebElement sortByButton = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div/div[1]/div[1]/select"));
            Assertions.assertTrue(addToCart.isDisplayed(),"Sort By Button did not displayed!");
            test.pass("Sort By Button displayed!");


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

            searchBar.sendKeys("null");

            searchBar.sendKeys(Keys.ENTER);

            String currentUrl = driver.getCurrentUrl();
            String expectedUrl = "http://localhost:3000/displaysearch?search=null";

            if (currentUrl.equals(expectedUrl)) {
                test.pass("Search successfully!");
            } else {
                test.fail("Search unsuccessfully!");
            }
            Thread.sleep(3000);


            WebElement noProduct = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div/div[2]/div"));
            Assertions.assertTrue(noProduct.isDisplayed(),"No Product Session did not displayed!");
            test.pass("No Product Session displayed!");

            if(noProduct.getText().contains("No products found matching")){
                test.pass("No product was found!");
            }else {
                test.fail("Fail");
            }


        }catch (Exception e){
            test.fail(e.getMessage());
        }
    }
    @ParameterizedTest
    @ValueSource(strings = {"chrome","edge","firefox"})
    public void checkSearchAuthor(String browser){
        try {
            driver = BrowserFactory.getDriver(browser);
            driver.get("http://localhost:3000/displaysearch");
            test.info("Check Search book by author's name");

            WebElement searchBar = driver.findElement(By.xpath("//*[@id=\"root\"]/header/div[1]/input"));
            Assertions.assertTrue(searchBar.isDisplayed(),"Search Bar did not display");
            test.pass("Search Bar displayed!");

            searchBar.sendKeys("Hunt");

            searchBar.sendKeys(Keys.ENTER);

            String currentUrl = driver.getCurrentUrl();
            String expectedUrl = "http://localhost:3000/displaysearch?search=Hunt";

            if (currentUrl.equals(expectedUrl)) {
                test.pass("Search successfully!");
            } else {
                test.fail("Search unsuccessfully!");
            }
            Thread.sleep(3000);

            WebElement book = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div/div[2]/div/div"));
            Assertions.assertTrue(book.isDisplayed(),"Researched Book did not display");
            test.pass("Researched Book displayed!");

            WebElement bookTitle = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div/div[2]/div/div/div/h3"));
            if(bookTitle.getText().equals("The Pragmatic Programmer")){
                test.pass("The Title Pragmatic Programmer was found!");
            }else {
                test.pass("The Title Pragmatic Programmer wasn't found!");
            };

            WebElement bookImage = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div/div[2]/div/div/img"));
            Assertions.assertTrue(bookImage.isDisplayed(),"Book Image did not displayed!");
            test.pass("Book Image displayed!");

            WebElement bookAuthor = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div/div[2]/div/div/div/p[1]"));
            Assertions.assertTrue(bookAuthor.isDisplayed(),"Book Author did not displayed!");
            test.pass("Book Author displayed!");
            if(bookAuthor.getText().equals("Andrew Hunt")){
                test.pass("The Author Andrew Hunt was found!");
            }else {
                test.pass("The Author Andrew Hunt wasn't found!");
            };

            WebElement bookPrice = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div/div[2]/div/div/div/p[2]"));
            Assertions.assertTrue(bookPrice.isDisplayed(),"Book Price did not displayed!");
            test.pass("Book Price displayed!");

            WebElement addToCart = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div/div[2]/div/div/button"));
            Assertions.assertTrue(addToCart.isDisplayed(),"Add To Cart Button did not displayed!");
            test.pass("Add To Cart Button displayed!");

            WebElement sortByButton = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div/div[1]/div[1]/select"));
            Assertions.assertTrue(sortByButton.isDisplayed(),"Sort By Button did not displayed!");
            test.pass("Sort By Button displayed!");

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
            test.info("Check Search book by name");

            WebElement searchBar = driver.findElement(By.xpath("//*[@id=\"root\"]/header/div[1]/input"));
            Assertions.assertTrue(searchBar.isDisplayed(),"Search Bar did not display");
            test.pass("Search Bar displayed!");

            searchBar.sendKeys("Hunt");

            searchBar.sendKeys(Keys.ENTER);

            String currentUrl = driver.getCurrentUrl();
            String expectedUrl = "http://localhost:3000/displaysearch?search=Hunt";

            if (currentUrl.equals(expectedUrl)) {
                test.pass("Search successfully!");
            } else {
                test.fail("Search unsuccessfully!");
            }
            Thread.sleep(3000);

            WebElement book = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div/div[2]/div/div"));
            Assertions.assertTrue(book.isDisplayed(),"Researched Book did not display");
            test.pass("Researched Book displayed!");

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

                List<WebElement> items = driver.findElements(By.className("product-card"));
                test.pass("Total products found: " + items.size());
                if (items.size() == 1){
                    return;
                } else {
                    String beforeSort = items.get(0).getText();

                    za.click();

                    List<WebElement> newItems = driver.findElements(By.className("product-card"));
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

     */

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @AfterAll
    public static void closeTest(){
        ExtendReport.closeReport();
    }
}
