package org.example.test.test_project.UI_UX_testing;


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

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BookDetailPage {
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


    @ParameterizedTest
    @ValueSource(strings = {"chrome","edge","firefox"})
    public void testHomeLink(String browser) {
        try{
            driver = BrowserFactory.getDriver(browser);
            driver.get("http://localhost:3000/product/1");
            WebElement home = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/a"));
            Assertions.assertTrue(home.isDisplayed(),"Home link did not display");
            home.click();
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            test.pass("Click Home link successfully!");
            String currentUrl = driver.getCurrentUrl();
            String expectedUrl = "http://localhost:3000/";

            if (currentUrl.equals(expectedUrl)) {
                test.pass("Page changed successfully!");
            } else {
                test.fail("Page unchanged!");
            }

        }catch (Exception e) {
            test.fail(e.getMessage());
        }
    }


    @ParameterizedTest
    @ValueSource(strings = {"chrome","edge","firefox"})
    public void testProductLink(String browser) {
        try{
            driver = BrowserFactory.getDriver(browser);
            driver.get("http://localhost:3000/product/1");

            WebElement product = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/a[2]"));
            Assertions.assertTrue(product.isDisplayed(),"Product link did not display");
            test.pass("Product link icon displayed!");

            product.click();
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            String currentUrl = driver.getCurrentUrl();
            String expectedUrl = "http://localhost:3000/product";

            if (currentUrl.equals(expectedUrl)) {
                test.pass("Page changed successfully!");
            } else {
                test.fail("Page unchanged!");
            }

        }catch (Exception e) {
            test.fail(e.getMessage());
        }
    }
    @ParameterizedTest
    @ValueSource(strings = {"chrome","edge","firefox"})
    public void testBookDetailElement(String browser) {
        try{
            driver = BrowserFactory.getDriver(browser);
            driver.get("http://localhost:3000/product/2");

            WebElement bookimage = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[1]/img"));
            Assertions.assertTrue(bookimage.isDisplayed(),"Book Image did not display");
            test.pass("Book Image displayed!");

            WebElement bookName = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[1]/div/div[1]"));
            Assertions.assertTrue(bookName.isDisplayed(),"Book Name did not display");
            test.pass("Book Name displayed!");

            WebElement bookAuthor = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[1]/div/div[2]"));
            Assertions.assertTrue(bookAuthor.isDisplayed(),"Book Author did not display");
            test.pass("Book Author displayed!");

            WebElement bookPrice = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[1]/div/div[3]"));
            Assertions.assertTrue(bookPrice.isDisplayed(),"BookPrice Name did not display");
            test.pass("BookPrice Name displayed!");

            WebElement bookBrand = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[2]/div[1]/div[1]/p[2]"));
            Assertions.assertTrue(bookBrand.isDisplayed(),"Book Brand did not display");
            test.pass("Book Brand displayed!");

            WebElement bookMaterial = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[2]/div[1]/div[1]/p[3]"));
            Assertions.assertTrue(bookMaterial.isDisplayed(),"Book Material did not display");
            test.pass("Book Material displayed!");

            WebElement bookCategories = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[2]/div[1]/div[1]/p[1]"));
            Assertions.assertTrue(bookCategories.isDisplayed(),"Book Categories did not display");
            test.pass("Book Categories displayed!");

            WebElement bookOverview = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[2]/div[2]"));
            Assertions.assertTrue(bookOverview.isDisplayed(),"Book Overview did not display");
            test.pass("Book Overview displayed!");

            WebElement AddToCartButton = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[2]/div[2]"));
            Assertions.assertTrue(AddToCartButton.isDisplayed(),"ADD TO CART button did not display");
            test.pass("ADD TO CART button displayed!");

            WebElement AddToWishListButton = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[2]/div[2]"));
            Assertions.assertTrue(AddToWishListButton.isDisplayed(),"ADD TO WISHLIST button did not display");
            test.pass("ADD TO WISHLIST button displayed!");

            WebElement Rating = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[2]/div[1]/div[2]"));
            Assertions.assertTrue(Rating.isDisplayed(),"Rating did not display");
            test.pass("Rating displayed!");



        }catch (Exception e) {
            test.fail(e.getMessage());
        }
    }
    @ParameterizedTest
    @ValueSource(strings = {"chrome","edge","firefox"})
    public void testBookDetailElement2(String browser) {
        try{
            driver = BrowserFactory.getDriver(browser);
            driver.get("http://localhost:3000/product/2");

            WebElement shareOpinion = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[3]/div/textarea"));
            Assertions.assertTrue(shareOpinion.isDisplayed(),"SHARE YOUR OPINION Box did not display");
            test.pass("SHARE YOUR OPINION Box displayed!");

            WebElement sendButton = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[3]/div/button"));
            Assertions.assertTrue(sendButton.isDisplayed(),"SEND button did not display");
            test.pass("SEND button displayed!");

            WebElement alsoLike = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[4]"));
            Assertions.assertTrue(alsoLike.isDisplayed(),"YOU MAY ALSO LIKE part did not display");
            test.pass("YOU MAY ALSO LIKE PART displayed!");

            WebElement alsoLikeBook = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[4]/div/a[1]"));
            Assertions.assertTrue(alsoLikeBook.isDisplayed(),"Books in YOU MAY ALSO LIKE part did not display");
            test.pass("Books in YOU MAY ALSO LIKE part displayed!");

        }catch (Exception e) {
            test.fail(e.getMessage());
        }
    }
    @ParameterizedTest
    @ValueSource(strings = {"chrome","edge","firefox"})
    public void testEnterComment(String browser) {
        try{
            driver = BrowserFactory.getDriver(browser);
            driver.get("http://localhost:3000/product/2");

            WebElement shareOpinion = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[3]/div/textarea"));
            Assertions.assertTrue(shareOpinion.isDisplayed(),"SHARE YOUR OPINION Box did not display");
            shareOpinion.sendKeys("This book is very hay :)");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            String curCmt = shareOpinion.getAttribute("value");
            if(curCmt.equals("This book is very hay :)")){
                test.pass("Enter comment in SHARE YOUR OPINION BOX successfully!");
            }
            else test.fail("Enter comment in SHARE YOUR OPINION BOX unsuccessfully!");

        }catch (Exception e) {
            test.fail(e.getMessage());
        }
    }
    @ParameterizedTest
    @ValueSource(strings = {"chrome","edge","firefox"})
    public void testEnterComment100times(String browser) {
        try{
            driver = BrowserFactory.getDriver(browser);
            driver.get("http://localhost:3000/product/2");

            WebElement shareOpinion = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[3]/div/textarea"));
            Assertions.assertTrue(shareOpinion.isDisplayed(),"SHARE YOUR OPINION Box did not display");
            for(int i=0;i<100;i++){
                shareOpinion.sendKeys("This book is very hay :)");
                shareOpinion.clear();
            }
            test.pass("Enter comment in SHARE YOUR OPINION BOX 100 times successfully!");
        }catch (Exception e) {
            test.fail(e.getMessage());
        }
    }
    @ParameterizedTest
    @ValueSource(strings = {"chrome","edge","firefox"})
    public void checkGoToOtherBookByYMAL(String browser) {
        try{
            driver = BrowserFactory.getDriver(browser);
            driver.get("http://localhost:3000/product/2");

            WebElement alsoLikeBook = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[4]/div/a[1]"));
            Assertions.assertTrue(alsoLikeBook.isDisplayed(),"Books in YOU MAY ALSO LIKE part did not display");
            test.pass("Books in YOU MAY ALSO LIKE part displayed!");
            alsoLikeBook.click();
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            test.pass("Book detail page link successfully!");
            String currentUrl = driver.getCurrentUrl();
            String expectedUrl = "http://localhost:3000/product/1";

            if (currentUrl.equals(expectedUrl)) {
                test.pass("Page changed successfully!");
            } else {
                test.fail("Page unchanged!");
            }

        }catch (Exception e) {
            test.fail(e.getMessage());
        }
    }
    @ParameterizedTest
    @ValueSource(strings = {"chrome","edge","firefox"})
    public void clickSEND(String browser) {
        try{
            driver = BrowserFactory.getDriver(browser);
            driver.get("http://localhost:3000/product/2");

            WebElement sendButton = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[3]/div/button"));
            Assertions.assertTrue(sendButton.isDisplayed(),"SEND button did not display");
            sendButton.click();
            test.pass("Click SEND button successfully!");

        }catch (Exception e) {
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
