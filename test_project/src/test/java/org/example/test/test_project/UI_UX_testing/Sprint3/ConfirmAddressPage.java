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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ConfirmAddressPage {
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

        try {
            driver = BrowserFactory.getDriver("edge");
            driver.get("http://localhost:3000/");

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
            passwordField.sendKeys("password2");
            WebElement signInButton = driver.findElement(By.xpath("//button[span[text()='Sign In']]"));

            signInButton.click();
            test.pass("Log in successfully!");

            Thread.sleep(3000);

            WebElement icon2 = driver.findElement(By.xpath("//*[@id=\"root\"]/header/div[2]/div[2]"));
            Assertions.assertTrue(icon2.isDisplayed(),"Shopping cart icon did not display");

            icon2.click();
            Thread.sleep(1000);

            currentUrl = driver.getCurrentUrl();
            expectedUrl = "http://localhost:3000/cart";

            if (currentUrl.equals(expectedUrl)) {
                test.pass("Page changed to cart page successfully!");
            } else {
                test.fail("Page unchanged!");
            }
            WebElement checkOutButton = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[7]/button"));
            Assertions.assertTrue(checkOutButton.isDisplayed(),"Check Out Button did not display");
            test.pass("Check Out Button displayed!");

            checkOutButton.click();
            test.pass("Click check out button successfully!");


            currentUrl = driver.getCurrentUrl();
            expectedUrl = "http://localhost:3000/confirmaddress";

            if (currentUrl.equals(expectedUrl)) {
                test.pass("Page changed to confirm address page successfully!");
            } else {
                test.fail("Page unchanged!");
            }
        }catch (Exception e){
            test.fail(e);
        }
    }


    @Test
    public void checkVNPayButton() {
        try {
            test.info("Check click VNPay Button!");

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

            List<WebElement> paymentButtons = driver.findElements(By.className("payment-button"));

            WebElement cashButton = paymentButtons.get(0);
            test.pass("VNPay Button displayed");
            cashButton.click();

            wait.until(ExpectedConditions.attributeContains(cashButton, "class", "active"));

            boolean isTickVisible = cashButton.findElement(By.className("checkmark")).isDisplayed();

            if (isTickVisible) {
                test.pass("VNPay payment method selected successfully!");
            } else {
                test.fail("Error: Checkmark not found!");
            }
        }catch (Exception e){
            test.fail(e.getMessage());
        }
    }
    @Test
    public void checkConfirmAddressComponent(){
        try {
            test.info("Check confirm address page components!");

            WebElement header = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/h2"));
            Assertions.assertTrue(header.isDisplayed(),"Page Title did not display");
            test.pass("Page Title  Book displayed!");

            if (header.getText().equals("CONFIRM ADDRESS")){
                test.pass("Correct page title!");
            }else{
                test.fail("Incorrect page title");
            }

            WebElement usernameBox = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[1]/input"));
            Assertions.assertTrue(usernameBox.isDisplayed(),"Username Box did not display");
            test.pass("Book Title Book displayed!");

            WebElement addressBox = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[2]/input"));
            Assertions.assertTrue(addressBox.isDisplayed(),"Address Box did not displayed!");
            test.pass("Address Box displayed!");

            WebElement phoneNumberBox = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[3]/input"));
            Assertions.assertTrue(phoneNumberBox.isDisplayed(),"Phone Number Box did not displayed!");
            test.pass("Phone Number Box displayed!");

            WebElement cashButton = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[4]/button[2]"));
            Assertions.assertTrue(cashButton.isDisplayed(),"Pay By Cash Button did not displayed!");
            test.pass("Pay By Cash Button displayed!");

            WebElement vnpayButton = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[4]/button[1]"));
            Assertions.assertTrue(vnpayButton.isDisplayed(),"Pay by VNPAY Button did not displayed!");
            test.pass("Pay by VNPAY Button displayed!");

            WebElement continueButton = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/button"));
            Assertions.assertTrue(continueButton.isDisplayed(),"Continue Button did not displayed!");
            test.pass("Continue Button displayed!");

        }catch (Exception e){
            test.fail(e.getMessage());
        }
    }


    @Test
    public void checkEnterInput(){
        try {
            test.info("Check Enter into username, address, phone number box!");

            WebElement usernameBox = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[1]/input"));
            Assertions.assertTrue(usernameBox.isDisplayed(),"Username Box did not display");
            test.pass("Book Title Book displayed!");

            usernameBox.sendKeys("username2");
            String res = usernameBox.getAttribute("value");
            if (res.equals("username2")){
                test.pass("Enter username successfully!");
            }else {
                test.fail("Enter username unsuccessfully!");
            }
            WebElement addressBox = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[2]/input"));
            Assertions.assertTrue(addressBox.isDisplayed(),"Address Box did not displayed!");
            test.pass("Address Box displayed!");

            addressBox.sendKeys("address1");
            res = addressBox.getAttribute("value");
            if (res.equals("address1")){
                test.pass("Enter address successfully!");
            }else {
                test.fail("Enter address unsuccessfully!");
            }

            WebElement phoneNumberBox = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[3]/input"));
            Assertions.assertTrue(phoneNumberBox.isDisplayed(),"Phone Number Box did not displayed!");
            test.pass("Phone Number Box displayed!");

            phoneNumberBox.sendKeys("0123456789");
            res = phoneNumberBox.getAttribute("value");
            if (res.equals("0123456789")){
                test.pass("Enter phone number successfully!");
            }else {
                test.fail("Enter phone number unsuccessfully!");
            }


        }catch (Exception e){
            test.fail(e.getMessage());
        }
    }
    @Test
    public void checkEnterInput100times(){
        try {
            test.info("Check Enter into username, address, phone number box 100 times!");


            WebElement usernameBox = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[1]/input"));
            Assertions.assertTrue(usernameBox.isDisplayed(),"Username Box did not display");
            test.pass("Book Title Book displayed!");

            WebElement addressBox = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[2]/input"));
            Assertions.assertTrue(addressBox.isDisplayed(),"Address Box did not displayed!");
            test.pass("Address Box displayed!");

            WebElement phoneNumberBox = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[3]/input"));
            Assertions.assertTrue(phoneNumberBox.isDisplayed(),"Phone Number Box did not displayed!");
            test.pass("Phone Number Box displayed!");

            for(int i=0; i<100;i++){
                usernameBox.sendKeys("username1");
                addressBox.sendKeys("address1");
                phoneNumberBox.sendKeys("0123456789");
                usernameBox.clear();
                addressBox.clear();
                phoneNumberBox.clear();
            }
            test.pass("Enter box 100 times successfully!");


        }catch (Exception e){
            test.fail(e.getMessage());
        }
    }

    @Test
    public void checkCashButton() {
        try {
            test.info("Check click Cash Button!");

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

            List<WebElement> paymentButtons = driver.findElements(By.className("payment-button"));

            WebElement cashButton = paymentButtons.get(1);
            test.pass("Cash Button displayed");
            cashButton.click();

            wait.until(ExpectedConditions.attributeContains(cashButton, "class", "active"));

            boolean isTickVisible = cashButton.findElement(By.className("checkmark")).isDisplayed();

            if (isTickVisible) {
                test.pass("Cash payment method selected successfully!");
            } else {
                test.fail("Error: Checkmark not found!");
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
