package org.example.test.test_project.UI_UX_testing;

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

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ProductPage {
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
    public void testHomeLink(String browser) {
        try{
            driver = BrowserFactory.getDriver(browser);
            driver.get("http://localhost:3000/product");
            WebElement home = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/a"));
            Assertions.assertTrue(home.isDisplayed(),"Home link did not display");
            home.click();
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            test.pass("Click Home link successfully!");
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
    public void testBookDetailLink(String browser) {
        try{
            driver = BrowserFactory.getDriver(browser);
            driver.get("http://localhost:3000/product");
            WebElement bookdetail = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[2]/div[2]/div[1]"));
            Assertions.assertTrue(bookdetail.isDisplayed(),"Book detail page link did not display");
            bookdetail.click();
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            test.pass("Book detail page link successfully!");
            String currentUrl = driver.getCurrentUrl();
            String expectedUrl = "http://localhost:3000/product/1";

            if (currentUrl.equals(expectedUrl)) {
                test.pass("Page changed successfully!");
            } else {
                test.fail("Page unchanged!");
            }

        }catch (Exception e) {
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
