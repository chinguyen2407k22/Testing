package org.example.test.test_project.UI_UX_testing.Sprint3;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import org.example.test.test_project.LogConfig.ExtendReport;
import org.example.test.test_project.WebBrowser.BrowserFactory;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;
import java.time.Duration;

public class OrderDetail {
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

            WebElement loginButton = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[1]/div/div/button[1]"));
            Assertions.assertTrue(loginButton.isDisplayed());

            loginButton.click();

            Thread.sleep(1000);

            WebElement usernameField = driver.findElement(By.name("username"));
            usernameField.sendKeys("username2");
            WebElement passwordField = driver.findElement(By.name("password"));
            passwordField.sendKeys("321zyxcba");
            WebElement signInButton = driver.findElement(By.xpath("//button[span[text()='Sign In']]"));

            signInButton.click();
            Thread.sleep(1000);

            driver.get("http://localhost:3000/order/9");

        }catch (Exception e){
            test.fail(e);
        }
    }

    @ParameterizedTest
    @ValueSource(strings ={"chrome","edge","firefox"})
    public void checkComponent(String browser) {
        try {
            test.info("Test order detail page component!");
            login(browser);
            Thread.sleep(2000);
            WebElement title = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[1]/span"));
            Assertions.assertTrue(title.isDisplayed());
            test.pass("Title displayed!");

            WebElement orderHistoryButton = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[1]/span"));
            Assertions.assertTrue(orderHistoryButton.isDisplayed());
            test.pass("Back to order history button dispalyed!");

            WebElement leaveARatingButton = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[1]/div"));
            Assertions.assertTrue(leaveARatingButton.isDisplayed());
            test.pass("Leave a rating button displayed!");

            WebElement paymentInfo = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[2]"));
            Assertions.assertTrue(paymentInfo.isDisplayed());
            test.pass("Payment info displayed!" + "\nPaymentInfo: " + paymentInfo.getText());

            WebElement estimatedDelTime = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[3]/p"));
            Assertions.assertTrue(estimatedDelTime.isDisplayed());
            test.pass("Estimanted delivery time displayed! \n Estimated delivery time: " + estimatedDelTime.getText());

            WebElement orderStatus = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[3]/div[1]/div[1]/p"));
            String res = orderStatus.getText();

            orderStatus = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[3]/div[1]/div[2]/p"));
            res = res + " " + orderStatus.getText();

            orderStatus = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[3]/div[1]/div[3]/p"));
            res = res + " " + orderStatus.getText();

            orderStatus = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[3]/div[1]/div[4]/p"));
            res = res + " " + orderStatus.getText();

            test.pass("Order status displayed! \nOrder Status includes: "+res);

            WebElement orderAcctivityTable = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[3]/div[2]"));
            Assertions.assertTrue(orderAcctivityTable.isDisplayed());
            test.pass("Order Activity Table displayed!");

            WebElement productTable = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[3]/div[3]/div[1]"));
            Assertions.assertTrue(productTable.isDisplayed());
            test.pass("Order product table displayed! Order product table includes: "+productTable.getText());

            WebElement billingAdderss = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[3]/div[4]/div[1]"));
            Assertions.assertTrue(billingAdderss.isDisplayed());
            test.pass("Billing Address displayed! \n Billing Address: " +billingAdderss.getText());

            WebElement shippingAddress = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[3]/div[4]/div[3]"));
            Assertions.assertTrue(shippingAddress.isDisplayed());
            test.pass("Shipping address displayed!\nShipping address: "+ shippingAddress.getText());

            WebElement note = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[3]/div[4]/div[5]"));
            Assertions.assertTrue(note.isDisplayed());
            test.pass("Order note displayed!\n Order note: "+note.getText());
        }catch (Exception e){
            test.fail(e);
        }
    }
    @ParameterizedTest
    @ValueSource(strings ={"chrome","edge","firefox"})
    public void checkClickbackButton(String browser) {
        try {
            test.info("Test click back to order history button!");
            login(browser);
            Thread.sleep(2000);

            WebElement orderHistoryButton = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[1]/span"));
            Assertions.assertTrue(orderHistoryButton.isDisplayed());
            test.pass("Back to order history button dispalyed!");

            orderHistoryButton.click();
            test.pass("Click Back to order history button!");
            Thread.sleep(1000);

            String currentUrl = driver.getCurrentUrl();
            String expectedUrl = "http://localhost:3000/view";

            if (currentUrl.equals(expectedUrl)) {
                test.pass("Change to order history page successfully!");
            } else {
                test.fail("Page unchanged!");
            }
        }catch (Exception e){
            test.fail(e);
        }

    }
    @ParameterizedTest
    @ValueSource(strings ={"chrome","edge","firefox"})
    public void checkClickLeaveARatingButton(String browser) {
        try {
            test.info("Test click to leave rating button!");
            login(browser);
            Thread.sleep(2000);

            WebElement leaveARatingButton = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[1]/div[1]"));
            Assertions.assertTrue(leaveARatingButton.isDisplayed());
            test.pass("Leave a rating button displayed!");

            leaveARatingButton.click();
            test.pass("Click Leave a rating button!");

            leaveARatingButton.click();
            test.pass("Click Leave a rating button again!");

            Boolean leaveAratingWindow = driver.findElements(By.xpath("//*[@id=\"root\"]/main/div/div[1]/div[2]")).isEmpty();
            if(leaveAratingWindow){
                test.pass("Leave a rating window disappear!");
            }else {
                test.fail("Leave a rating window still appear!");
            }


        }catch (Exception e){
            test.fail(e);
        }

    }
    @ParameterizedTest
    @ValueSource(strings ={"firefox","chrome","edge"})
    public void checkLARComponent(String browser) {
        try {
            test.info("Test leave a rating component!");
            login(browser);
            Thread.sleep(2000);

            WebElement leaveARatingButton = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[1]/div[1]"));
            Assertions.assertTrue(leaveARatingButton.isDisplayed());
            test.pass("Leave a rating button displayed!");

            leaveARatingButton.click();
            test.pass("Click Leave a rating button!");

            WebElement titleLAR = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[1]/div[2]/div[1]/h2"));
            test.pass("Leave a rating window title displayed!\n Title: "+titleLAR.getText());

            WebElement name = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[1]/div[2]/div[2]/div/label[1]"));
            test.pass("name of product displayed! "+"Name: "+name.getText());

            WebElement rating = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[1]/div[2]/div[2]/div/select"));
            test.pass("select rating button displayed!");

            rating.click();
            test.pass("Click select rating button to choose rating.");
            WebElement newRating = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[1]/div[2]/div[2]/div/select/option[2]"));
            newRating.click();
            test.pass("Click select rating button to choose new rating.");

            WebElement feedback = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[1]/div[2]/div[2]/div/textarea"));
            test.pass("feed back box displayed!");

            feedback.sendKeys("Good!");
            if(feedback.getAttribute("value").equals("Good!")){
                test.pass("enter value in feedback book successfully!");
            }else {
                test.fail("enter value in feedback book unsuccessfully!");
            }

            WebElement publishReview = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[1]/div[2]/div[2]/div/div/button"));
            test.pass("PUBLISH FEEDBACK displayed!");
            publishReview.click();
            test.pass("click PUBLISH FEEDBACK button!");
            Thread.sleep(1000);

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
