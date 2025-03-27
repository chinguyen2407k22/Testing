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

public class UpdateHomePage {
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

    @ValueSource(strings = {"chrome"})
    @ParameterizedTest
    @ValueSource(strings = {"chrome","edge","firefox"})
    public void testEnterEmail100times(String browser){
        try{
            driver = BrowserFactory.getDriver(browser);
            driver.get("http://localhost:3000/");
            test.info("Test enter email into email box!");
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            WebElement popup = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"root\"]/main/div/div[1]/div")));

            WebElement continueBrowsing = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[1]/div/div/button[2]"));
            Assertions.assertTrue(continueBrowsing.isDisplayed());
            test.pass("Continue Browsing displayed!");
            continueBrowsing.click();

            WebElement sucbribeEmailBox = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[5]/div/div/input"));
            Assertions.assertTrue(sucbribeEmailBox.isDisplayed(),"Subcribe Email Box did not display");
            for(int i = 0; i<100; i++){
                sucbribeEmailBox.sendKeys("example@gmail.com");
                sucbribeEmailBox.clear();
            }


        }catch (Exception e) {
            test.fail(e.getMessage());
        }
    }
    /*
    @ParameterizedTest
    @ValueSource(strings = {"chrome","edge","firefox"})
    public void testEnterEmail(String browser){
        try{
            driver = BrowserFactory.getDriver(browser);
            driver.get("http://localhost:3000/");
            test.info("Test enter email into email box!");
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            WebElement popup = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"root\"]/main/div/div[1]/div")));

            WebElement continueBrowsing = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[1]/div/div/button[2]"));
            Assertions.assertTrue(continueBrowsing.isDisplayed());
            test.pass("Continue Browsing displayed!");
            continueBrowsing.click();

            WebElement sucbribeEmailBox = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[5]/div/div/input"));
            Assertions.assertTrue(sucbribeEmailBox.isDisplayed(),"Subcribe Email Box did not display");
            sucbribeEmailBox.sendKeys("example@gmail.com");

            String result = sucbribeEmailBox.getAttribute("value");
            if(result.equals("example@gmail.com")){
                test.pass("Enter email successfully!");
            }else {
                test.fail("Enter email unsuccessfully");
            }


        }catch (Exception e) {
            test.fail(e.getMessage());
        }
    }
    @ParameterizedTest
    @ValueSource(strings = {"chrome","edge","firefox"})
    public void testEnterEmail100times(String browser){
        try{
            driver = BrowserFactory.getDriver(browser);
            driver.get("http://localhost:3000/");
            test.info("Test enter email into email box!");
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            WebElement popup = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"root\"]/main/div/div[1]/div")));

            WebElement continueBrowsing = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[1]/div/div/button[2]"));
            Assertions.assertTrue(continueBrowsing.isDisplayed());
            test.pass("Continue Browsing displayed!");
            continueBrowsing.click();

            WebElement sucbribeEmailBox = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[5]/div/div/input"));
            Assertions.assertTrue(sucbribeEmailBox.isDisplayed(),"Subcribe Email Box did not display");
            for(int i = 0; i<100; i++){
                sucbribeEmailBox.sendKeys("example@gmail.com");
                sucbribeEmailBox.clear();
            }


        }catch (Exception e) {
            test.fail(e.getMessage());
        }
    }
     */

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @AfterAll
    public static void closeTest(){
        ExtendReport.closeReport();
    }
}
