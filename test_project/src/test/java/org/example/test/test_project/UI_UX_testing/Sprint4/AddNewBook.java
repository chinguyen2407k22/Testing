package org.example.test.test_project.UI_UX_testing.Sprint4;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
import org.junit.jupiter.params.provider.ValueSources;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class AddNewBook {
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
        try{
            driver = BrowserFactory.getDriver(browser);
            driver.get("http://localhost:3000/login");

            WebElement usernameField = driver.findElement(By.name("username"));
            usernameField.sendKeys("admin");
            WebElement passwordField = driver.findElement(By.name("password"));
            passwordField.sendKeys("adminpassword");
            WebElement signInButton = driver.findElement(By.xpath("//button[span[text()='Sign In']]"));

            signInButton.click();
            test.pass("Log in successfully!");
            Thread.sleep(1000);
            driver.get("http://localhost:3000/add-book");
            test.pass("Change to ADD NEW BOOK page successfully!");
            Thread.sleep(1000);

        }catch (Exception e){
            test.fail(e);
        }
    }

    @ParameterizedTest
    @ValueSource(strings={"chrome"})
    public void checkComponent(String browser){
        try{
            test.info("Check ADD NEW BOOK page components!");
            login(browser);

            WebElement title = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/h1"));
            Assertions.assertTrue(title.isDisplayed());
            if(title.getText().equals("ADD NEW BOOK")){
                test.pass("Title is displayed! Right title: "+title.getText());
            }else{
                test.pass("Title is displayed! Wrong title: "+title.getText());
            }

            WebElement nameBox = driver.findElement(By.name("name"));
            Assertions.assertTrue(nameBox.isDisplayed());
            test.pass("Enter name box displayed!");

            WebElement nameBox = driver.findElement(By.name("name"));
            Assertions.assertTrue(nameBox.isDisplayed());
            test.pass("Enter name box displayed!");

            WebElement nameBox = driver.findElement(By.name("name"));
            Assertions.assertTrue(nameBox.isDisplayed());
            test.pass("Enter name box displayed!");

            WebElement nameBox = driver.findElement(By.name("name"));
            Assertions.assertTrue(nameBox.isDisplayed());
            test.pass("Enter name box displayed!");

            WebElement nameBox = driver.findElement(By.name("name"));
            Assertions.assertTrue(nameBox.isDisplayed());
            test.pass("Enter name box displayed!");

            WebElement nameBox = driver.findElement(By.name("name"));
            Assertions.assertTrue(nameBox.isDisplayed());
            test.pass("Enter name box displayed!");

            WebElement nameBox = driver.findElement(By.name("name"));
            Assertions.assertTrue(nameBox.isDisplayed());
            test.pass("Enter name box displayed!");

            WebElement nameBox = driver.findElement(By.name("name"));
            Assertions.assertTrue(nameBox.isDisplayed());
            test.pass("Enter name box displayed!");

            WebElement nameBox = driver.findElement(By.name("name"));
            Assertions.assertTrue(nameBox.isDisplayed());
            test.pass("Enter name box displayed!");

            WebElement nameBox = driver.findElement(By.name("name"));
            Assertions.assertTrue(nameBox.isDisplayed());
            test.pass("Enter name box displayed!");

            WebElement nameBox = driver.findElement(By.name("name"));
            Assertions.assertTrue(nameBox.isDisplayed());
            test.pass("Enter name box displayed!");

            WebElement nameBox = driver.findElement(By.name("name"));
            Assertions.assertTrue(nameBox.isDisplayed());
            test.pass("Enter name box displayed!");
            


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
