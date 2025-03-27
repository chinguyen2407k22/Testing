package org.example.test.test_project.UI_UX_testing.Sprint3;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.example.test.test_project.LogConfig.ExtendReport;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.openqa.selenium.WebDriver;

import org.example.test.test_project.WebBrowser.BrowserFactory;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
    @ValueSource(strings = {"firefox"})
    @ParameterizedTest
    @ValueSource(strings = {"chrome","edge","firefox"})
    public void checkWishListComponent(String browser){
        try {

            driver = BrowserFactory.getDriver(browser);
            driver.get("http://localhost:3000/wishlist");
            test.info("Check Book in Wish List");

            WebElement book = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div/div[2]/div/div"));
            Assertions.assertTrue(book.isDisplayed(),"Wish List Book did not display");
            test.pass("Wish List Book displayed!");

            WebElement bookTitle = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div/div[2]/div/div/div/h3"));
            Assertions.assertTrue(bookTitle.isDisplayed(),"Book Title did not display");
            test.pass("Book Title Book displayed!");

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
            Assertions.assertTrue(sortByButton.isDisplayed(),"Sort By Button did not displayed!");
            test.pass("Sort By Button displayed!");


        }catch (Exception e){
            test.fail(e.getMessage());
        }
    }
    /*
    @ParameterizedTest
    @ValueSource(strings = {"chrome","edge","firefox"})
    public void checkBookInWishList(String browser){
        try {

            driver = BrowserFactory.getDriver(browser);
            driver.get("http://localhost:3000/wishlist");
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
    @ParameterizedTest
    @ValueSource(strings = {"chrome","edge","firefox"})
    public void checkWishListComponent(String browser){
        try {

            driver = BrowserFactory.getDriver(browser);
            driver.get("http://localhost:3000/wishlist");
            test.info("Check Book in Wish List");

            WebElement book = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div/div[2]/div/div"));
            Assertions.assertTrue(book.isDisplayed(),"Wish List Book did not display");
            test.pass("Wish List Book displayed!");

            WebElement bookTitle = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div/div[2]/div/div/div/h3"));
            Assertions.assertTrue(bookTitle.isDisplayed(),"Book Title did not display");
            test.pass("Book Title Book displayed!");

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
            Assertions.assertTrue(sortByButton.isDisplayed(),"Sort By Button did not displayed!");
            test.pass("Sort By Button displayed!");


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
