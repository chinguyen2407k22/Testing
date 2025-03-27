package org.example.test.test_project.UI_UX_testing.Sprint3;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.example.test.test_project.LogConfig.ExtendReport;
import org.example.test.test_project.WebBrowser.BrowserFactory;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class UpdateLoginSigninPage {
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
    public void testLoginWithWrongPasswordUsername(String browser){
        try{
            driver = BrowserFactory.getDriver(browser);
            driver.get("http://localhost:3000/login");
            test.info("Test login with wrong username");
            WebElement usernameField = driver.findElement(By.name("username"));
            usernameField.sendKeys("username1");
            WebElement passwordField = driver.findElement(By.name("password"));
            passwordField.sendKeys("password");
            WebElement signInButton = driver.findElement(By.xpath("//button[span[text()='Sign In']]"));
            signInButton.click();

            WebElement notification = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/div/p"));
            Assertions.assertTrue(notification.isDisplayed());
            if(notification.getText().equals("Your username or password is wrong")){
                test.pass("Nofication: "+notification.getText());
            }else {
                test.fail("Nofication: None");
            }

            test.pass("Test Sign In button by signing by email!");


        }catch (Exception e) {
            test.fail(e.getMessage());
        }
    }


    /*
    @ParameterizedTest
    @ValueSource(strings = {"chrome","edge","firefox"})
    public void testRegisterUsedEmail(String browser) {
        driver = BrowserFactory.getDriver(browser);
        driver.get("http://localhost:3000/register");
        try{
            WebElement username = driver.findElement(By.name("username"));
            if(browser.equals("chrome")){
                username.sendKeys("username3");
            }
            else if(browser.equals("edge")){
                username.sendKeys("usernameie3");
            } else if (browser.equals("firefox")) {
                username.sendKeys("usernameff3");
            }

            WebElement phonenum = driver.findElement(By.name("phone"));
            phonenum.sendKeys("0123456789");

            WebElement firstname = driver.findElement(By.name("firstname"));
            firstname.sendKeys("firstname");

            WebElement lastname = driver.findElement(By.name("lastname"));
            lastname.sendKeys("lastname5");

            WebElement email = driver.findElement(By.name("email"));
            if(browser.equals("chrome")){
                email.sendKeys("example1@gmail.com");
            }
            else if(browser.equals("edge")){
                email.sendKeys("example@gmail.com");
            } else if (browser.equals("firefox")) {
                email.sendKeys("example@gmail.com");
            }
            WebElement password = driver.findElement(By.name("password"));
            password.sendKeys("password1");

            WebElement confirm = driver.findElement(By.name("confirmPassword"));
            confirm.sendKeys("password1");

            WebElement signUp = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/div/button"));
            signUp.click();
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            test.pass("Test Register Successfully!");
            String currentUrl = driver.getCurrentUrl();
            String expectedUrl = "http://localhost:3000/register";

            if (currentUrl.equals(expectedUrl)) {
                test.pass("Page unchanged successfully!");
            } else {
                test.fail("Page changed!");
            }
        }catch (Exception e) {
            test.fail(e.getMessage());
        }

    }
    @ParameterizedTest
    @ValueSource(strings = {"chrome","edge","firefox"})
    public void testLoginByEmail(String browser){
        try{
            driver = BrowserFactory.getDriver(browser);
            driver.get("http://localhost:3000/login");
                WebElement usernameField = driver.findElement(By.name("username"));
                usernameField.sendKeys("example1@gmail.com");
                WebElement passwordField = driver.findElement(By.name("password"));
                passwordField.sendKeys("password1");
                WebElement signInButton = driver.findElement(By.xpath("//button[span[text()='Sign In']]"));
                signInButton.click();
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
    public void testLoginWithWrongPassword(String browser){
        try{
            driver = BrowserFactory.getDriver(browser);
            driver.get("http://localhost:3000/login");
            test.info("Test login with right email and wrong password");
            WebElement usernameField = driver.findElement(By.name("username"));
            usernameField.sendKeys("username1@gmail.com");
            WebElement passwordField = driver.findElement(By.name("password"));
            passwordField.sendKeys("password");
            WebElement signInButton = driver.findElement(By.xpath("//button[span[text()='Sign In']]"));
            signInButton.click();

            WebElement notification = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/div/p"));
            Assertions.assertTrue(notification.isDisplayed());
            if(notification.getText().equals("Your username or password is wrong")){
                test.pass("Nofication: "+notification.getText());
            }else {
                test.fail("Nofication: None");
            }


        }catch (Exception e) {
            test.fail(e.getMessage());
        }
    }
    @ParameterizedTest
    @ValueSource(strings = {"chrome","edge","firefox"})
    public void testLoginByEmail(String browser){
        try{
            driver = BrowserFactory.getDriver(browser);
            driver.get("http://localhost:3000/login");
            test.info("Test login by email successfull");
            WebElement usernameField = driver.findElement(By.name("username"));
            usernameField.sendKeys("example1@gmail.com");
            WebElement passwordField = driver.findElement(By.name("password"));
            passwordField.sendKeys("password1");
            WebElement signInButton = driver.findElement(By.xpath("//button[span[text()='Sign In']]"));
            signInButton.click();
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
    public void testLoginWithWrongEmail(String browser){
        try{
            driver = BrowserFactory.getDriver(browser);
            driver.get("http://localhost:3000/login");
            test.info("Test login with wrong email");
            WebElement usernameField = driver.findElement(By.name("username"));
            usernameField.sendKeys("example@gmail.com");
            WebElement passwordField = driver.findElement(By.name("password"));
            passwordField.sendKeys("password1");
            WebElement signInButton = driver.findElement(By.xpath("//button[span[text()='Sign In']]"));
            signInButton.click();

            WebElement notification = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/div/p"));
            Assertions.assertTrue(notification.isDisplayed());
            if(notification.getText().equals("Your username or password is wrong")){
                test.pass("Nofication: "+notification.getText());
            }else {
                test.fail("Nofication: None");
            }


        }catch (Exception e) {
            test.fail(e.getMessage());
        }
    }
    @ParameterizedTest
    @ValueSource(strings = {"chrome","edge","firefox"})
    public void testLoginWithWrongUsername(String browser){
        try{
            driver = BrowserFactory.getDriver(browser);
            driver.get("http://localhost:3000/login");
            test.info("Test login with wrong username");
            WebElement usernameField = driver.findElement(By.name("username"));
            usernameField.sendKeys("username1");
            WebElement passwordField = driver.findElement(By.name("password1"));
            passwordField.sendKeys("password");
            WebElement signInButton = driver.findElement(By.xpath("//button[span[text()='Sign In']]"));
            signInButton.click();

            WebElement notification = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/div/p"));
            Assertions.assertTrue(notification.isDisplayed());
            if(notification.getText().equals("Your username or password is wrong")){
                test.pass("Nofication: "+notification.getText());
            }else {
                test.fail("Nofication: None");
            }


        }catch (Exception e) {
            test.fail(e.getMessage());
        }
    }
    @ValueSource(strings = {"chrome","edge","firefox"})
    public void testLoginWithWrongPasswordUsername(String browser){
        try{
            driver = BrowserFactory.getDriver(browser);
            driver.get("http://localhost:3000/login");
            test.info("Test login with wrong username");
            WebElement usernameField = driver.findElement(By.name("username"));
            usernameField.sendKeys("username1");
            WebElement passwordField = driver.findElement(By.name("password"));
            passwordField.sendKeys("password");
            WebElement signInButton = driver.findElement(By.xpath("//button[span[text()='Sign In']]"));
            signInButton.click();

            WebElement notification = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/div/p"));
            Assertions.assertTrue(notification.isDisplayed());
            if(notification.getText().equals("Your username or password is wrong")){
                test.pass("Nofication: "+notification.getText());
            }else {
                test.fail("Nofication: None");
            }

            test.pass("Test Sign In button by signing by email!");


        }catch (Exception e) {
            test.fail(e.getMessage());
        }
    }*/
    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @AfterAll
    public static void closeTest(){
        ExtendReport.closeReport();
    }
}
