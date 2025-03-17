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

public class ProductPage {
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
    public void testProductFragment(String browser) {
        try{
            driver = BrowserFactory.getDriver(browser);
            driver.get("http://localhost:3000/product");

            WebElement logo = driver.findElement(By.xpath("//*[@id=\"root\"]/header/div[1]/img"));
            Assertions.assertTrue(logo.isDisplayed(),"Logo did not display");
            test.pass("Logo displayed!");

            WebElement searchBar = driver.findElement(By.xpath("//*[@id=\"root\"]/header/div[2]/input"));
            Assertions.assertTrue(searchBar.isDisplayed(),"Search Bar did not display");
            test.pass("Search Bar displayed!");

            WebElement icon1 = driver.findElement(By.xpath("//*[@id=\"root\"]/header/div[3]/a[1]"));
            Assertions.assertTrue(icon1.isDisplayed(),"Login icon did not display");
            test.pass("Login icon displayed!");

            WebElement icon2 = driver.findElement(By.xpath("//*[@id=\"root\"]/header/div[3]/a[2]"));
            Assertions.assertTrue(icon2.isDisplayed(),"Shopping cart icon did not display");
            test.pass("Shopping cart icon displayed!");

            WebElement icon3 = driver.findElement(By.xpath("//*[@id=\"root\"]/header/div[3]/a[3]"));
            Assertions.assertTrue(icon3.isDisplayed(),"Favorite icon did not display");
            test.pass("Favorite icon displayed!");

            WebElement home = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/a"));
            Assertions.assertTrue(home.isDisplayed(),"Home link did not display");
            test.pass("Home link icon displayed!");

            WebElement bookdetail = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[2]/div[2]/div[1]"));
            Assertions.assertTrue(bookdetail.isDisplayed(),"Book detail page link did not display");
            test.pass("Book detail page icon displayed!");

        }catch (Exception e) {
            test.fail(e.getMessage());
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"chrome","edge","firefox"})
    public void testHomeLink(String browser) {
        try{
            driver = BrowserFactory.getDriver(browser);
            driver.get("http://localhost:3000/product");
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
    public void testBookDetailLink(String browser) {
        try{
            driver = BrowserFactory.getDriver(browser);
            driver.get("http://localhost:3000/product");
            WebElement bookdetail = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[2]/div[2]/div[1]"));
            Assertions.assertTrue(bookdetail.isDisplayed(),"Book detail page link did not display");
            bookdetail.click();
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
    public void testInputSearchBar(String browser) {
        try{
            driver = BrowserFactory.getDriver(browser);
            driver.get("http://localhost:3000/product");

            WebElement searchBar = driver.findElement(By.xpath("//*[@id=\"root\"]/header/div[2]/input"));
            searchBar.sendKeys("Times");
            String testStr = searchBar.getAttribute("value");
            Assertions.assertTrue(testStr.equals("Times"),"Input value incorrect!");
            test.pass("Enter search keyword successfully!");

        }catch (Exception e) {
            test.fail(e.getMessage());
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"chrome","edge","firefox"})
    public void findOkKey(String browser) {
        try{
            driver = BrowserFactory.getDriver(browser);
            driver.get("http://localhost:3000/product");

            WebElement searchBar = driver.findElement(By.xpath("//*[@id=\"root\"]/header/div[2]/input"));
            searchBar.sendKeys("Times");
            String testStr = searchBar.getAttribute("value");
            Assertions.assertTrue(testStr.equals("Times"),"Input value incorrect!");
            test.pass("Enter search keyword successfully!");

            searchBar.sendKeys(Keys.ENTER);

            if(driver.getCurrentUrl().contains("search?q=Selenium+Testing")){
                test.pass("Finding sucessfully!");
            }
            else test.fail("Finding unsucessfully!");

        }catch (Exception e) {
            test.fail(e.getMessage());
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"chrome","edge","firefox"})
    public void testInputSearchBarTimes100(String browser) {
        try{
            driver = BrowserFactory.getDriver(browser);
            driver.get("http://localhost:3000/product");

            WebElement searchBar = driver.findElement(By.xpath("//*[@id=\"root\"]/header/div[2]/input"));
            for (int i = 0;i<100; i++){
                searchBar.sendKeys("Times");
                searchBar.clear();
            }
            test.pass("Insert input search bar 100 times successfully!");
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
