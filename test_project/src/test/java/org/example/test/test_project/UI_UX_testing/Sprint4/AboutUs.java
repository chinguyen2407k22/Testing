package org.example.test.test_project.UI_UX_testing.Sprint4;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.example.test.test_project.LogConfig.ExtendReport;
import org.example.test.test_project.WebBrowser.BrowserFactory;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class AboutUs {
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
    @ValueSource(strings = {"chrome","firefox","edge"})
    public void checkClickAboutUsLink(String browser){
        try{
            test.info("Test click ABOUT US page link.");
            driver = BrowserFactory.getDriver(browser);
            driver.get("http://localhost:3000/");
            Thread.sleep(3000);
            WebElement continueBrowsingButton = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[1]/div/div/button[2]"));
            continueBrowsingButton.click();
            WebElement aboutUsLink = driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div/a[2]"));
            Assertions.assertTrue(aboutUsLink.isDisplayed());
            test.pass("ABOUT US page link displayed!");
            aboutUsLink.click();
            String currentUrl = driver.getCurrentUrl();
            String expectedUrl = "http://localhost:3000/about-us";

            if (currentUrl.equals(expectedUrl)) {
                test.pass("Page changed to ABOUT US page successfully!");
            } else {
                test.fail("Page unchanged!");
            }
        }catch (Exception e){
            test.fail(e);
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"chrome","firefox","edge"})
    public void checkComponent(String browser){
        try{
            test.info("Test ABOUT US page component.");
            driver = BrowserFactory.getDriver(browser);
            driver.get("http://localhost:3000/about-us");
            Thread.sleep(3000);

            WebElement title = driver.findElement(By.className("about-title"));
            Assertions.assertTrue(title.isDisplayed());
            if (title.getText().equals("ABOUT US")){
                test.pass("Title displayed: "+title.getText());
            }else{
                test.fail("Title displayed: "+title.getText());
            }

            WebElement content = driver.findElement(By.className("about-section"));
            Assertions.assertTrue(content.isDisplayed());
            test.pass("Content displayed!");

            WebElement visitor = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div[2]/div[1]"));
            Assertions.assertTrue(visitor.isDisplayed());
            test.pass("Number of visitor displayed: "+visitor.getText());

            WebElement soldbook = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div[2]/div[2]"));
            Assertions.assertTrue(soldbook.isDisplayed());
            test.pass("Number of sold book displayed: "+soldbook.getText());

            WebElement rating = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div[2]/div[3]"));
            Assertions.assertTrue(rating.isDisplayed());
            test.pass("Number of good rating displayed: "+rating.getText());

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
