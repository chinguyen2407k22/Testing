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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LoginPopUp {
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
    public void checkPopUpComponent(String browser){
        try {
            double epsilon = 1e-9;
            driver = BrowserFactory.getDriver(browser);
            driver.get("http://localhost:3000/");
            test.info("Check Login pop up component!");

            WebElement popUp = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[1]/div"));
            Assertions.assertTrue(popUp.isDisplayed());
            test.pass("Login Pop up window displayed!");

            WebElement loginButton = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[1]/div/div/button[1]"));
            Assertions.assertTrue(loginButton.isDisplayed());
            test.pass("Login button displayed!");

            WebElement continueBrowsing = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[1]/div/div/button[2]"));
            Assertions.assertTrue(continueBrowsing.isDisplayed());
            test.pass("Continue Browsing displayed!");

        }catch (Exception e){
            test.fail(e.getMessage());
        }
    }
    @ParameterizedTest
    @ValueSource(strings = {"chrome","edge","firefox"})
    public void checkLoginButton(String browser) {
        try {
            double epsilon = 1e-9;
            driver = BrowserFactory.getDriver(browser);
            driver.get("http://localhost:3000/");
            test.info("Check Login Button!");

            WebElement loginButton = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[1]/div/div/button[1]"));
            Assertions.assertTrue(loginButton.isDisplayed());
            test.pass("Login button displayed!");

            loginButton.click();
            String currentUrl = driver.getCurrentUrl();
            String expectedUrl = "http://localhost:3000/login";

            if (currentUrl.equals(expectedUrl)) {
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
    public void checkContinueBrowsingButton(String browser) {
        try {
            double epsilon = 1e-9;
            driver = BrowserFactory.getDriver(browser);
            driver.get("http://localhost:3000/");
            test.info("Check Continue Browsing Button!");

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            WebElement popup = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"root\"]/main/div/div[1]/div")));

            WebElement continueBrowsing = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[1]/div/div/button[2]"));
            Assertions.assertTrue(continueBrowsing.isDisplayed());
            test.pass("Continue Browsing displayed!");
            continueBrowsing.click();

            boolean isPopupClosed = wait.until(ExpectedConditions.invisibilityOf(popup));

            if (isPopupClosed) {
                test.pass("Popup was closed!");
            } else {
                test.fail("Popup wasn't closed!");
            }

        }catch (Exception e){
            test.fail(e.getMessage());
        }
    }
    @ParameterizedTest
    @ValueSource(strings = {"chrome","edge","firefox"})
    public void checkvisitProductwithoutlogin(String browser) {
        try {
            driver = BrowserFactory.getDriver(browser);
            driver.get("http://localhost:3000/");
            test.info("Check visit product page without login!");

            WebElement continueBrowsing = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[1]/div/div/button[2]"));
            Assertions.assertTrue(continueBrowsing.isDisplayed());
            test.pass("Continue Browsing displayed!");

            continueBrowsing.click();
            test.pass("Clicking Continue Browsing Button successfully!");

            WebElement product = driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div/a[3]"));
            Assertions.assertTrue(product.isDisplayed(),"Product link did not display");
            test.pass("Product link displayed!");

            product.click();
            test.pass("Clicking Product Link successfully!");

            String currentUrl = driver.getCurrentUrl();
            String expectedUrl = "http://localhost:3000/product";

            if (currentUrl.equals(expectedUrl)) {
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
    public void checkvisitWishListwithoutlogin(String browser) {
        try {
            driver = BrowserFactory.getDriver(browser);
            driver.get("http://localhost:3000/");
            test.info("Check visit WISHLIST page without login!");

            WebElement continueBrowsing = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[1]/div/div/button[2]"));
            Assertions.assertTrue(continueBrowsing.isDisplayed());
            test.pass("Continue Browsing displayed!");

            continueBrowsing.click();
            test.pass("Clicking Continue Browsing Button successfully!");

            WebElement icon3 = driver.findElement(By.xpath("//*[@id=\"root\"]/header/div[2]/div[3]"));
            Assertions.assertTrue(icon3.isDisplayed(),"WISHLIST icon did not display");
            test.pass("WISHLIST icon displayed!");

            icon3.click();
            test.pass("Clicking WISHLIST BUTTON successfully!");

            String currentUrl = driver.getCurrentUrl();
            String expectedUrl = "http://localhost:3000/login";

            if (currentUrl.equals(expectedUrl)) {
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
    public void checkvisitCartwithoutlogin(String browser) {
        try {
            driver = BrowserFactory.getDriver(browser);
            driver.get("http://localhost:3000/");
            test.info("Check visit CART page without login!");

            WebElement continueBrowsing = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[1]/div/div/button[2]"));
            Assertions.assertTrue(continueBrowsing.isDisplayed());
            test.pass("Continue Browsing displayed!");

            continueBrowsing.click();
            test.pass("Clicking Continue Browsing Button successfully!");

            WebElement icon2 = driver.findElement(By.xpath("//*[@id=\"root\"]/header/div[2]/div[2]"));
            Assertions.assertTrue(icon2.isDisplayed(),"Shopping cart icon did not display");
            test.pass("Shopping cart icon displayed!");

            icon2.click();
            test.pass("Clicking CART BUTTON successfully!");

            String currentUrl = driver.getCurrentUrl();
            String expectedUrl = "http://localhost:3000/login";

            if (currentUrl.equals(expectedUrl)) {
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
    public void checkSearchingwithoutlogin(String browser) {
        try {
            driver = BrowserFactory.getDriver(browser);
            driver.get("http://localhost:3000/");
            test.info("Check searching without login!");

            WebElement continueBrowsing = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[1]/div/div/button[2]"));
            Assertions.assertTrue(continueBrowsing.isDisplayed());
            test.pass("Continue Browsing displayed!");

            continueBrowsing.click();
            test.pass("Clicking Continue Browsing Button successfully!");

            WebElement searchBar = driver.findElement(By.xpath("//*[@id=\"root\"]/header/div[1]/input"));
            Assertions.assertTrue(searchBar.isDisplayed(),"Search Bar did not display");
            test.pass("Search Bar displayed!");

            searchBar.sendKeys("programmer");

            searchBar.sendKeys(Keys.ENTER);
            test.pass("Searching successfully!");

            String currentUrl = driver.getCurrentUrl();
            String expectedUrl = "http://localhost:3000/displaysearch?search=";

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
    public void checkSearchingwithlogin(String browser) {
        try {
            driver = BrowserFactory.getDriver(browser);
            driver.get("http://localhost:3000/");
            test.info("Check searching with login!");

            WebElement loginButton = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[1]/div/div/button[1]"));
            Assertions.assertTrue(loginButton.isDisplayed());
            test.pass("Login button displayed!");

            loginButton.click();
            test.pass("Clicking login button to change to login page successfully!");

            String currentUrl = driver.getCurrentUrl();
            String expectedUrl = "http://localhost:3000/login";

            if (currentUrl.equals(expectedUrl)) {
                test.pass("Change to login page successfully!");
            } else {
                test.fail("Page unchanged!");
            }
            Thread.sleep(1000);

            WebElement usernameField = driver.findElement(By.name("username"));
            usernameField.sendKeys("username1");
            WebElement passwordField = driver.findElement(By.name("password"));
            passwordField.sendKeys("password1");
            WebElement signInButton = driver.findElement(By.xpath("//button[span[text()='Sign In']]"));

            signInButton.click();
            test.pass("Log in successfully!");
            Thread.sleep(1000);

            WebElement searchBar = driver.findElement(By.xpath("//*[@id=\"root\"]/header/div[1]/input"));
            Assertions.assertTrue(searchBar.isDisplayed(),"Search Bar did not display");
            test.pass("Search Bar displayed!");

            searchBar.sendKeys("programmer");

            searchBar.sendKeys(Keys.ENTER);
            test.pass("Searching successfully!");

            currentUrl = driver.getCurrentUrl();
            expectedUrl = "http://localhost:3000/displaysearch?search=";

            if (currentUrl.contains(expectedUrl)) {
                test.pass("Page changed to research result page successfully!");
            } else {
                test.fail("Page unchanged!");
            }

        }catch (Exception e){
            test.fail(e.getMessage());
        }
    }
    @ParameterizedTest
    @ValueSource(strings = {"chrome","edge","firefox"})
    public void checkvisitProductwithlogin(String browser) {
        try {
            driver = BrowserFactory.getDriver(browser);
            driver.get("http://localhost:3000/");
            test.info("Check visit product page with login!");

            WebElement loginButton = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[1]/div/div/button[1]"));
            Assertions.assertTrue(loginButton.isDisplayed());
            test.pass("Login button displayed!");

            loginButton.click();
            test.pass("Clicking login button to change to login page successfully!");

            String currentUrl = driver.getCurrentUrl();
            String expectedUrl = "http://localhost:3000/login";

            if (currentUrl.equals(expectedUrl)) {
                test.pass("Change to login page successfully!");
            } else {
                test.fail("Page unchanged!");
            }
            Thread.sleep(1000);

            WebElement usernameField = driver.findElement(By.name("username"));
            usernameField.sendKeys("username1");
            WebElement passwordField = driver.findElement(By.name("password"));
            passwordField.sendKeys("password1");
            WebElement signInButton = driver.findElement(By.xpath("//button[span[text()='Sign In']]"));

            signInButton.click();
            test.pass("Log in successfully!");

            Thread.sleep(1000);

            WebElement product = driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div/a[3]"));
            Assertions.assertTrue(product.isDisplayed(),"Product link did not display");
            test.pass("Product link displayed!");

            product.click();
            test.pass("Clicking Product Link successfully!");

            currentUrl = driver.getCurrentUrl();
            expectedUrl = "http://localhost:3000/product";

            if (currentUrl.equals(expectedUrl)) {
                test.pass("Page changed to product page successfully!");
            } else {
                test.fail("Page unchanged!");
            }

        }catch (Exception e){
            test.fail(e.getMessage());
        }
    }
    @ParameterizedTest
    @ValueSource(strings = {"chrome","edge","firefox"})
    public void checkvisitCartwithlogin(String browser) {
        try {
            driver = BrowserFactory.getDriver(browser);
            driver.get("http://localhost:3000/");
            test.info("Check visit cart page with login!");

            WebElement loginButton = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[1]/div/div/button[1]"));
            Assertions.assertTrue(loginButton.isDisplayed());
            test.pass("Login button displayed!");

            loginButton.click();
            test.pass("Clicking login button to change to login page successfully!");

            String currentUrl = driver.getCurrentUrl();
            String expectedUrl = "http://localhost:3000/login";

            if (currentUrl.equals(expectedUrl)) {
                test.pass("Change to login page successfully!");
            } else {
                test.fail("Page unchanged!");
            }
            Thread.sleep(1000);

            WebElement usernameField = driver.findElement(By.name("username"));
            usernameField.sendKeys("username1");
            WebElement passwordField = driver.findElement(By.name("password"));
            passwordField.sendKeys("password1");
            WebElement signInButton = driver.findElement(By.xpath("//button[span[text()='Sign In']]"));

            signInButton.click();
            test.pass("Log in successfully!");

            Thread.sleep(1000);

            WebElement icon2 = driver.findElement(By.xpath("//*[@id=\"root\"]/header/div[2]/div[2]"));
            Assertions.assertTrue(icon2.isDisplayed(),"Shopping cart icon did not display");
            test.pass("Shopping cart icon displayed!");

            icon2.click();
            test.pass("Clicking CART BUTTON successfully!");

            currentUrl = driver.getCurrentUrl();
            expectedUrl = "http://localhost:3000/cart";

            if (currentUrl.equals(expectedUrl)) {
                test.pass("Page changed to cart page successfully!");
            } else {
                test.fail("Page unchanged!");
            }

        }catch (Exception e){
            test.fail(e.getMessage());
        }
    }
    @ParameterizedTest
    @ValueSource(strings = {"chrome","edge","firefox"})
    public void checkvisitWLwithlogin(String browser) {
        try {
            driver = BrowserFactory.getDriver(browser);
            driver.get("http://localhost:3000/");
            test.info("Check visit WISHLIST page with login!");

            WebElement loginButton = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[1]/div/div/button[1]"));
            Assertions.assertTrue(loginButton.isDisplayed());
            test.pass("Login button displayed!");

            loginButton.click();
            test.pass("Clicking login button to change to login page successfully!");

            String currentUrl = driver.getCurrentUrl();
            String expectedUrl = "http://localhost:3000/login";

            if (currentUrl.equals(expectedUrl)) {
                test.pass("Change to login page successfully!");
            } else {
                test.fail("Page unchanged!");
            }
            Thread.sleep(1000);

            WebElement usernameField = driver.findElement(By.name("username"));
            usernameField.sendKeys("username1");
            WebElement passwordField = driver.findElement(By.name("password"));
            passwordField.sendKeys("password1");
            WebElement signInButton = driver.findElement(By.xpath("//button[span[text()='Sign In']]"));

            signInButton.click();
            test.pass("Log in successfully!");

            Thread.sleep(1000);

            WebElement icon3 = driver.findElement(By.xpath("//*[@id=\"root\"]/header/div[2]/div[3]"));
            Assertions.assertTrue(icon3.isDisplayed(),"WISHLIST icon did not display");
            test.pass("WISHLIST icon displayed!");

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
