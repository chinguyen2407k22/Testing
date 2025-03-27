package org.example.test.test_project.UI_UX_testing.Sprint2;

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

public class Header {
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



    //@ValueSource(strings = {"chrome"})
    @ParameterizedTest
    @ValueSource(strings = {"chrome","edge","firefox"})
    public void testProductFragment(String browser) {
        try{
            driver = BrowserFactory.getDriver(browser);
            driver.get("http://localhost:3000/");

            WebElement logo = driver.findElement(By.xpath("//*[@id=\"root\"]/header/a/img"));
            Assertions.assertTrue(logo.isDisplayed(),"Logo did not display");
            test.pass("Logo displayed!");

            WebElement searchBar = driver.findElement(By.xpath("//*[@id=\"root\"]/header/div[1]/input"));
            Assertions.assertTrue(searchBar.isDisplayed(),"Search Bar did not display");
            test.pass("Search Bar displayed!");

            WebElement icon1 = driver.findElement(By.xpath("//*[@id=\"root\"]/header/div[2]/div/div"));
            Assertions.assertTrue(icon1.isDisplayed(),"Login icon did not display");
            test.pass("Login icon displayed!");

            WebElement icon2 = driver.findElement(By.xpath("//*[@id=\"root\"]/header/div[2]/a[1]"));
            Assertions.assertTrue(icon2.isDisplayed(),"Shopping cart icon did not display");
            test.pass("Shopping cart icon displayed!");

            WebElement icon3 = driver.findElement(By.xpath("//*[@id=\"root\"]/header/div[2]/a[2]"));
            Assertions.assertTrue(icon3.isDisplayed(),"Favorite icon did not display");
            test.pass("Favorite icon displayed!");

            WebElement home = driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div/a[1]"));
            Assertions.assertTrue(home.isDisplayed(),"Home link did not display");
            test.pass("Home link icon displayed!");

            WebElement product = driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div/a[3]"));
            Assertions.assertTrue(product.isDisplayed(),"Product link did not display");
            test.pass("Product link icon displayed!");


        }catch (Exception e) {
            test.fail(e.getMessage());
        }
    }
    //@ValueSource(strings = {"chrome"})
    @ParameterizedTest
    @ValueSource(strings = {"chrome","edge","firefox"})
    public void testInputSearchBar(String browser) {
        try{
            driver = BrowserFactory.getDriver(browser);
            driver.get("http://localhost:3000/");

            WebElement searchBar = driver.findElement(By.xpath("//*[@id=\"root\"]/header/div[1]/input"));
            searchBar.sendKeys("Times");
            String testStr = searchBar.getAttribute("value");
            Assertions.assertTrue(testStr.equals("Times"),"Input value incorrect!");
            test.pass("Enter search keyword successfully!");

        }catch (Exception e) {
            test.fail(e.getMessage());
        }
    }

    //@ValueSource(strings = {"chrome"})
    @ParameterizedTest
    @ValueSource(strings = {"chrome","edge","firefox"})
    public void findOkKey(String browser) {
        try{
            driver = BrowserFactory.getDriver(browser);
            driver.get("http://localhost:3000/");

            WebElement searchBar = driver.findElement(By.xpath("//*[@id=\"root\"]/header/div[1]/input"));
            searchBar.sendKeys("Times");
            String testStr = searchBar.getAttribute("value");
            Assertions.assertTrue(testStr.equals("Times"),"Input value incorrect!");
            test.pass("Enter search keyword successfully!");

            searchBar.sendKeys(Keys.ENTER);
            if(driver.getCurrentUrl().contains("http://localhost:3000/product")){
                test.pass("Page changed successfully!");
            }
            else test.fail("Page unchanged");

            if(driver.getCurrentUrl().contains("?search=")){
                test.pass("Finding sucessfully!");
            }
            else test.fail("Finding unsucessfully!");

        }catch (Exception e) {
            test.fail(e.getMessage());
        }
    }

    //@ValueSource(strings = {"chrome"})
    @ParameterizedTest
    @ValueSource(strings = {"chrome","edge","firefox"})
    public void testInputSearchBarTimes100(String browser) {
        try{
            driver = BrowserFactory.getDriver(browser);
            driver.get("http://localhost:3000/product");

            WebElement searchBar = driver.findElement(By.xpath("//*[@id=\"root\"]/header/div[1]/input"));
            for (int i = 0;i<100; i++){
                searchBar.sendKeys("Times");
                searchBar.clear();
            }
            test.pass("Insert input search bar 100 times successfully!");
        }catch (Exception e) {
            test.fail(e.getMessage());
        }
    }
    //@ValueSource(strings = {"chrome"})
    @ParameterizedTest
    @ValueSource(strings = {"chrome","edge","firefox"})
    public void testLinkElement(String browser){
        driver = BrowserFactory.getDriver(browser);
        driver.get("http://localhost:3000/product/2");
        try {
            WebElement facebookLink = driver.findElement(By.xpath("//*[@id=\"root\"]/div[1]/a[1]"));
            WebElement instaLink = driver.findElement(By.xpath("//*[@id=\"root\"]/div[1]/a[2]"));
            WebElement linkIn = driver.findElement(By.xpath("//*[@id=\"root\"]/div[1]/a[3]"));
            WebElement twitterLink = driver.findElement(By.xpath("//*[@id=\"root\"]/div[1]/a[4]"));

            Assertions.assertTrue(facebookLink.isDisplayed(),"Facebook link did not display!");
            test.pass("Facebook link box displayed!");

            Assertions.assertTrue(linkIn.isDisplayed(),"LinkIn link did not display!");
            test.pass("LinkIn link box displayed!");

            Assertions.assertTrue(twitterLink.isDisplayed(),"Twitter Link did not display!");
            test.pass("Twitter link box displayed!");

            Assertions.assertTrue(instaLink.isDisplayed(),"Instagram Link did not display!");
            test.pass("Instagram link box displayed!");

        }catch (Exception e) {
            test.fail(e.getMessage());
        }
    }
    @ParameterizedTest
    @ValueSource(strings = {"chrome","edge","firefox"})
    public void testHomeProductLink(String browser) {
        try{
            driver = BrowserFactory.getDriver(browser);
            driver.get("http://localhost:3000/");

            WebElement product = driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div/a[3]"));
            Assertions.assertTrue(product.isDisplayed(),"Product link did not display");
            product.click();

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            String currentUrl = driver.getCurrentUrl();
            String expectedUrl = "http://localhost:3000/product";

            if (currentUrl.equals(expectedUrl)) {
                test.pass("Home page changed to product page successfully!");
            } else {
                test.fail("Home page unchanged to product page !");
            }

            WebElement home = driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div/a[1]"));
            Assertions.assertTrue(home.isDisplayed(),"Home link did not display");
            home.click();
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            currentUrl = driver.getCurrentUrl();
            expectedUrl = "http://localhost:3000/";

            if (currentUrl.equals(expectedUrl)) {
                test.pass("Product page changed to home page successfully!");
            } else {
                test.fail("Product page unchanged to home page!");
            }

        }catch (Exception e) {
            test.fail(e.getMessage());
        }
    }
    @ParameterizedTest
    @ValueSource(strings = {"chrome","edge","firefox"})
    public void testLoginLink(String browser) {
        try{
            driver = BrowserFactory.getDriver(browser);
            driver.get("http://localhost:3000/");

            WebElement icon = driver.findElement(By.xpath("//*[@id=\"root\"]/header/div[2]/div/div"));
            icon.click();

            Thread.sleep(1000);

            WebElement link = driver.findElement(By.xpath("//a[contains(text(), 'Login')]"));

            link.click();

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            String currentUrl = driver.getCurrentUrl();
            String expectedUrl = "http://localhost:3000/login";

            if (currentUrl.equals(expectedUrl)) {
                test.pass("Home page changed to login page successfully!");
            } else {
                test.fail("Home page unchanged to login page!");
            }

        }catch (Exception e) {
            test.fail(e.getMessage());
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"chrome","edge","firefox"})
    public void testRegisterLink(String browser) {
        try{
            driver = BrowserFactory.getDriver(browser);
            driver.get("http://localhost:3000/");

            WebElement icon = driver.findElement(By.xpath("//*[@id=\"root\"]/header/div[2]/div/div"));
            icon.click();

            Thread.sleep(1000);

            WebElement link = driver.findElement(By.xpath("//a[contains(text(), 'Register')]"));

            link.click();

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            String currentUrl = driver.getCurrentUrl();
            String expectedUrl = "http://localhost:3000/register";

            if (currentUrl.equals(expectedUrl)) {
                test.pass("Home page changed to login page successfully!");
            } else {
                test.fail("Home page unchanged to login page!");
            }

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
