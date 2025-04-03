package org.example.test.test_project.UI_UX_testing.Sprint3;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.example.test.test_project.LogConfig.ExtendReport;
import org.example.test.test_project.WebBrowser.BrowserFactory;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;

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
            Thread.sleep(1000);

            WebElement loginButton = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[1]/div/div/button[1]"));
            Assertions.assertTrue(loginButton.isDisplayed());

            loginButton.click();

            Thread.sleep(1000);

            WebElement usernameField = driver.findElement(By.name("username"));
            usernameField.sendKeys("username1");
            WebElement passwordField = driver.findElement(By.name("password"));
            passwordField.sendKeys("password1");
            WebElement signInButton = driver.findElement(By.xpath("//button[span[text()='Sign In']]"));

            signInButton.click();
            Thread.sleep(3000);

            WebElement icon1 = driver.findElement(By.xpath("//*[@id=\"root\"]/header/div[2]/div/div"));
            Assertions.assertTrue(icon1.isDisplayed(),"Login icon did not display");

            icon1.click();

            Thread.sleep(1000);

            WebElement link = driver.findElement(By.xpath("//a[contains(text(), 'View')]"));

            link.click();

            WebElement viewDetailButton = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[2]/span[5]"));
            Assertions.assertTrue(viewDetailButton.isDisplayed());

            viewDetailButton.click();

        }catch (Exception e){
            test.fail(e);
        }
    }

    @ParameterizedTest
    @ValueSource(strings ={"chrome","firefox","edge"})
    public void checkComponent(String browser) {
        try {
            test.info("Test order detail page component!");
            login(browser);
            WebElement title = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[1]"));
            Assertions.assertTrue(title.isDisplayed());
            test.pass("Title displayed!");

            WebElement orderHistoryButton = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[1]/span"));
            Assertions.assertTrue(orderHistoryButton.isDisplayed());
            test.pass("Back to order history button dispalyed!");

            WebElement leaveARatingButton = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[1]/div[1]"));
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
    @ValueSource(strings ={"chrome","firefox","edge"})
    public void checkClickbackButton(String browser) {
        try {
            test.info("Test click back to order history button!");
            login(browser);

            WebElement orderHistoryButton = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[1]/span"));
            Assertions.assertTrue(orderHistoryButton.isDisplayed());
            test.pass("Back to order history button dispalyed!");

            orderHistoryButton.click();
            test.pass("Click Back to order history button!");

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
    @ValueSource(strings ={"chrome","firefox","edge"})
    public void checkClickLeaveARatingButton(String browser) {
        try {
            test.info("Test click to leave rating button!");
            login(browser);

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
    @ValueSource(strings ={"chrome","firefox","edge"})
    public void checkLARComponent(String browser) {
        try {
            test.info("Test leave a rating component!");
            login(browser);

            WebElement leaveARatingButton = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[1]/div[1]"));
            Assertions.assertTrue(leaveARatingButton.isDisplayed());
            test.pass("Leave a rating button displayed!");

            leaveARatingButton.click();
            test.pass("Click Leave a rating button!");

            WebElement titleLAR = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[1]/div[2]/div[1]/h2"));
            test.pass("Leave a rating window title displayed!\n Title: "+titleLAR.getText());

            WebElement name = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[1]/div[2]/div[2]/input[1]"));
            test.pass("name of product displayed! "+"Name: "+name.getText());

            WebElement rating = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[1]/div[2]/div[2]/select[1]"));
            test.pass("select rating button displayed!");

            WebElement feedback = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[1]/div[2]/div[2]/textarea[1]"));
            test.pass("feed back box displayed!");

            WebElement publishReview = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[1]/div[2]/div[2]/div"));
            test.pass("PUBLISH FEEDBACK displayed!");

        }catch (Exception e){
            test.fail(e);
        }

    }

    @ParameterizedTest
    @ValueSource(strings ={"chrome","firefox","edge"})
    public void checkEnterFeedbackAndSelectRating(String browser) {
        try {
            test.info("Test enter feedback and select rating then click PUBLISH FEEDBACK button!");
            login(browser);

            WebElement leaveARatingButton = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[1]/div[1]"));
            Assertions.assertTrue(leaveARatingButton.isDisplayed());
            test.pass("Leave a rating button displayed!");

            leaveARatingButton.click();
            test.pass("Click Leave a rating button!");

            WebElement rating = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[1]/div[2]/div[2]/select[1]"));
            test.pass("select rating button displayed!");
            rating.click();
            test.pass("Click select rating button to choose rating.");
            WebElement newRating = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[1]/div[2]/div[2]/select[1]/option[2]"));
            newRating.click();
            test.pass("Click select rating button to choose new rating.");

            WebElement feedback = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[1]/div[2]/div[2]/textarea[1]"));
            test.pass("feed back box displayed!");
            feedback.sendKeys("Good!");
            if(feedback.getAttribute("value").equals("Good!")){
                test.pass("enter value in feedback book successfully!");
            }else {
                test.fail("enter value in feedback book unsuccessfully!");
            }
            Thread.sleep(2000);
            WebElement button = driver.findElement(By.xpath("//button[text()='PUBLISH REVIEW']"));
            button.click();
            test.pass("click PUBLISH FEEDBACK button!");

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

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @AfterAll
    public static void closeTest(){
        ExtendReport.closeReport();
    }

}
