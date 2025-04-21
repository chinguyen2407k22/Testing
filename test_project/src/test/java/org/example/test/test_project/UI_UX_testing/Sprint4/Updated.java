package org.example.test.test_project.UI_UX_testing.Sprint4;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.checkerframework.checker.units.qual.t;
import org.example.test.test_project.LogConfig.ExtendReport;
import org.example.test.test_project.WebBrowser.BrowserFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class Updated {
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
    public void checkEnterEmail(String browser){
        try{
            test.info("Check enter email.");
            driver = BrowserFactory.getDriver(browser);
            driver.get("http://localhost:3000/");
            Thread.sleep(2000);
            WebElement continueBrowsing = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[1]/div/div/button[2]"));
            continueBrowsing.click();
            Thread.sleep(1000);

            WebElement subcribeBox = driver.findElement(By.name("email"));
            test.pass("Enter email box displayed!");

            subcribeBox.sendKeys("example@gmail.com");
            if(subcribeBox.getAttribute("value").equals("example@gmail.com")){
                test.pass("Enter email successfully! Email: "+subcribeBox.getAttribute("value"));
            }else{
                test.fail("Enter email unsuccessfully! Email: "+subcribeBox.getAttribute("value"));
            }

        }catch (Exception e){
            test.fail(e);
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"chrome","edge","firefox"})
    public void checkEnterEmail100times(String browser){
        try{
            test.info("Check enter email 100 times");
            driver = BrowserFactory.getDriver(browser);
            driver.get("http://localhost:3000/");
            Thread.sleep(2000);
            WebElement continueBrowsing = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[1]/div/div/button[2]"));
            continueBrowsing.click();
            Thread.sleep(1000);
            WebElement subcribeBox = driver.findElement(By.name("email"));
            test.pass("Enter email box displayed!");
            for (int i = 0; i < 100; i++){
                subcribeBox.sendKeys("example@gmail.com");
                subcribeBox.clear();
            }

        }catch (Exception e){
            test.fail(e);
        }

    }

    @ParameterizedTest
    @ValueSource(strings = {"chrome","edge","firefox"})
    public void checkSubcribeButton(String browser){
        try{
            test.info("Check Subcribe Button");
            driver = BrowserFactory.getDriver(browser);
            driver.get("http://localhost:3000/");
            Thread.sleep(2000);
            WebElement continueBrowsing = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[1]/div/div/button[2]"));
            continueBrowsing.click();
            Thread.sleep(1000);
            WebElement subcribeBox = driver.findElement(By.name("email"));
            subcribeBox.sendKeys("example@gmail.com");
            if(subcribeBox.getAttribute("value").equals("example@gmail.com")){
                test.pass("Enter email successfully! Email: "+subcribeBox.getAttribute("value"));
            }else{
                test.fail("Enter email unsuccessfully! Email: "+subcribeBox.getAttribute("value"));
            }

            WebElement subcribeButton = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[4]/div[2]/button"));
            subcribeButton.click();
            test.pass("Click Subcribe Button successfully!");
            if(subcribeBox.getAttribute("value").equals("")){
                test.pass("Subcibed email successfully!");
            }else{
                test.fail("Subcibed email unsuccessfully!");
            }

        }catch (Exception e){
            test.fail(e);
        }
        
    }

    public void login(String browser){
        try{
            
            driver.get("http://localhost:3000/login");

            WebElement usernameField = driver.findElement(By.name("username"));
            usernameField.sendKeys("username2");
            WebElement passwordField = driver.findElement(By.name("password"));
            passwordField.sendKeys("abcxyz123");
            WebElement signInButton = driver.findElement(By.xpath("//button[span[text()='Sign In']]"));

            signInButton.click();
            test.pass("Log in successfully!");

        }catch (Exception e){
            test.fail(e);
        }
    }
    public void logout(String browser){
        try{
            WebElement icon1 = driver.findElement(By.xpath("//*[@id=\"root\"]/header/div[2]/div/div"));
            icon1.click();

            Thread.sleep(2000);

            WebElement link = driver.findElement(By.xpath("//a[contains(text(), 'Logout')]"));
            link.click();
            Thread.sleep(2000);

        }catch (Exception e){
            test.fail(e);
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"chrome","firefox","edge"})
    public void checkConfirmAddressTitle(String browser){
        try{
            driver = BrowserFactory.getDriver(browser);
            login(browser);
            test.info("Check CONFIRM ADDRESS page title!");
            Thread.sleep(2000);
            driver.get("http://localhost:3000/cart");
            Thread.sleep(2000);
            WebElement checkOutButton = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[4]/button"));
            checkOutButton.click();
            String currentUrl = driver.getCurrentUrl();
            String expectedUrl = "http://localhost:3000/confirmaddress";

            if (currentUrl.equals(expectedUrl)) {
                test.pass("Change to CONFIRM ADDRESS page successfully!");
            } else {
                test.fail("Page unchanged!");
            }
            
            WebElement header = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/h2"));
            Assertions.assertTrue(header.isDisplayed(),"Page Title did not display");
            test.pass("Page Title  Book displayed!");

            if (header.getText().equals("CONFIRM ADDRESS")){
                test.pass("Correct page title!");
            }else{
                test.fail("Incorrect page title");
            }
            
        }catch (Exception e){
            test.fail(e);
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"chrome","firefox","edge"})
    public void checkLogout(String browser){
        try{
            test.info("Check log out form WISHLIST page, CART page, CONFIRM ADDRESS page, ORDER HISTORY, ORDER DETAILS, PROFILE");
            driver = BrowserFactory.getDriver(browser);
            login(browser);
            Thread.sleep(2000);
            driver.get("http://localhost:3000/cart");
            test.pass("Go to CART page successfully!");
            Thread.sleep(2000);
            logout(browser);
            String currentUrl = driver.getCurrentUrl();
            String expectedUrl = "http://localhost:3000/login";

            if (currentUrl.equals(expectedUrl)) {
                test.pass("Log out successfully. Change to LOGIN page successfully!");
            } else {
                test.fail("Page unchanged!");
            }
            login(browser);
            Thread.sleep(2000);
            driver.get("http://localhost:3000/wishlist");
            test.pass("Go to WISHLIST page successfully!");
            Thread.sleep(2000);
            logout(browser);
            Thread.sleep(2000);
            currentUrl = driver.getCurrentUrl();

            if (currentUrl.equals(expectedUrl)) {
                test.pass("Log out successfully. Change to LOGIN page successfully!");
            } else {
                test.fail("Page unchanged!");
            }

            Thread.sleep(2000);
            login(browser);
            Thread.sleep(2000);
            driver.get("http://localhost:3000/confirmaddress");
            test.pass("Go to CONFIRM ADDRESS page successfully!");
            Thread.sleep(2000);
            logout(browser);
            currentUrl = driver.getCurrentUrl();

            if (currentUrl.equals(expectedUrl)) {
                test.pass("Log out successfully. Change to LOGIN page successfully!");
            } else {
                test.fail("Page unchanged!");
            }

            Thread.sleep(2000);
            login(browser);
            Thread.sleep(2000);
            driver.get("http://localhost:3000/profile");
            test.pass("Go to PROFILE page successfully!");
            Thread.sleep(2000);
            logout(browser);
            currentUrl = driver.getCurrentUrl();

            if (currentUrl.equals(expectedUrl)) {
                test.pass("Log out successfully. Change to LOGIN page successfully!");
            } else {
                test.fail("Page unchanged!");
            }

            Thread.sleep(2000);
            login(browser);
            Thread.sleep(2000);
            driver.get("http://localhost:3000/view");
            test.pass("Go to ORDER HISTORY page successfully!");
            Thread.sleep(2000);
            logout(browser);
            currentUrl = driver.getCurrentUrl();

            if (currentUrl.equals(expectedUrl)) {
                test.pass("Log out successfully. Change to LOGIN page successfully!");
            } else {
                test.fail("Page unchanged!");
            }

        }catch (Exception e){
            test.fail(e);
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
