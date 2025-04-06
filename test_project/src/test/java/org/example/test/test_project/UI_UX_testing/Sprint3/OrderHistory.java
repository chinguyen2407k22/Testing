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
import org.openqa.selenium.devtools.v114.browser.Browser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class OrderHistory {
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

            String currentUrl = driver.getCurrentUrl();
            String expectedUrl = "http://localhost:3000/login";

            if (currentUrl.equals(expectedUrl)) {
                test.pass("Change to login page successfully!");
            } else {
                test.fail("Page unchanged!");
            }
            

            WebElement usernameField = driver.findElement(By.name("username"));
            usernameField.sendKeys("username2");
            WebElement passwordField = driver.findElement(By.name("password"));
            passwordField.sendKeys("abcxyz123");
            WebElement signInButton = driver.findElement(By.xpath("//button[span[text()='Sign In']]"));

            signInButton.click();
            test.pass("Log in successfully!");

            Thread.sleep(3000);

            WebElement icon1 = driver.findElement(By.xpath("//*[@id=\"root\"]/header/div[2]/div/div"));
            Assertions.assertTrue(icon1.isDisplayed(),"Login icon did not display");
            test.pass("Login icon displayed!");

            icon1.click();

            WebElement link = driver.findElement(By.xpath("//a[contains(text(), 'View')]"));

            link.click();
            Thread.sleep(1000);
            currentUrl = driver.getCurrentUrl();
            expectedUrl = "http://localhost:3000/view";

            if (currentUrl.equals(expectedUrl)) {
                test.pass("Page changed to view page successfully!");
            } else {
                test.fail("Page unchanged!");
            }


        }catch (Exception e){
            test.fail(e);
        }
    }

    @ValueSource(strings ={"chrome","firefox","edge"})
    @ParameterizedTest
    public void checkComponent(String browser){
        try {
            test.info("Check Order history page component");
            login(browser);
            WebElement pageTitle = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/h1"));
            Assertions.assertTrue(pageTitle.isDisplayed());
            if(pageTitle.getText().equals("VIEW ORDER HISTORY")){
                test.pass("Page Title: "+pageTitle.getText());
            }else {
                test.fail("Page Title: "+pageTitle.getText());
            }
            WebElement orderBar = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[1]"));
            Assertions.assertTrue(orderBar.isDisplayed());
            test.pass("Order Bar displayed!");
            test.pass("Order Bar included: " + orderBar.getText());

            WebElement orderRow = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[2]"));
            Assertions.assertTrue(orderRow.isDisplayed());
            test.pass("Order Row displayed!");
            test.pass("Order Row data is: "+orderRow.getText());

            WebElement allbutton = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[16]"));
            Assertions.assertTrue(allbutton.isDisplayed());
            test.pass("All button is: " + allbutton.getText());

            WebElement number2button = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[16]/button[3]"));
            Assertions.assertTrue(number2button.isDisplayed());
            test.pass("The button with number: "+number2button.getText() +" is displayed!");

            WebElement nextButton = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[16]/button[5]"));
            Assertions.assertTrue(nextButton.isDisplayed());
            test.pass("Next button is display!");

            WebElement prevButton = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[16]/button[1]"));
            Assertions.assertTrue(prevButton.isDisplayed());
            test.pass("Previous button is display!");

            WebElement viewDetailButton = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[2]/span[5]"));
            Assertions.assertTrue(viewDetailButton.isDisplayed());
            test.pass("View Detail Button displayed!");

        }catch (Exception e){
            test.fail(e);
        }
    }
    @ValueSource(strings ={"chrome","firefox","edge"})
    @ParameterizedTest
    public void checkButton(String browser){
        try {
            test.info("Check click button!");
            login(browser);

            WebElement number2button = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[16]/button[3]"));
            Assertions.assertTrue(number2button.isDisplayed());
            test.pass("The button with number: "+number2button.getText() +" is displayed!");

            number2button.click();
            test.pass("Click button with number: "+number2button.getText());

            WebElement number1button = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[16]/button[2]"));
            Assertions.assertTrue(number1button.isDisplayed());
            test.pass("The button with number: "+number1button.getText() +" is displayed!");

            number1button.click();
            test.pass("Click button with number: "+number1button.getText());

            WebElement nextButton = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[16]/button[5]"));
            Assertions.assertTrue(nextButton.isDisplayed());
            test.pass("Next button is display!");

            nextButton.click();
            test.pass("Click next button!");

            WebElement prevButton = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[16]/button[1]"));
            Assertions.assertTrue(prevButton.isDisplayed());
            test.pass("Previous button is display!");

            prevButton.click();
            test.pass("Click previous button!");


        }catch (Exception e){
            test.fail(e);
        }
    }
    @ValueSource(strings ={"chrome","firefox","edge"})
    @ParameterizedTest
    public void checkViewDetailButton(String browser){
        try {
            test.info("Check click button!");
            login(browser);

            WebElement viewDetailButton = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[2]/span[5]"));
            Assertions.assertTrue(viewDetailButton.isDisplayed());
            test.pass("View Detail Button displayed!");

            viewDetailButton.click();

            String currentUrl = driver.getCurrentUrl();
            String expectedUrl = "http://localhost:3000/order";

            if (currentUrl.contains(expectedUrl)) {
                test.pass("Change to orrder detail page successfully!");
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
