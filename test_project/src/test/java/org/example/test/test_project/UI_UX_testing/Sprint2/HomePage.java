package org.example.test.test_project.UI_UX_testing.Sprint2;

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

public class HomePage {
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
    public void testRecommendFactor(String browser) {
        try{
            driver = BrowserFactory.getDriver(browser);
            driver.get("http://localhost:3000/");

            WebElement recommendField = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[1]"));
            Assertions.assertTrue(recommendField.isDisplayed(),"Recommend field did not display");
            test.pass("Recommend field link displayed!");

            WebElement recommendNext = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[1]/button[2]"));
            Assertions.assertTrue(recommendNext.isDisplayed(),"Recommend Next Button did not display");
            test.pass("Recommend Next Button displayed!");

            WebElement recommendPrevious = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[1]/button[1]"));
            Assertions.assertTrue(recommendPrevious.isDisplayed(),"Recommend Previous Button did not display");
            test.pass("Recommend Previous Button displayed!");

            WebElement recommendIndex = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[1]/div/div"));
            Assertions.assertTrue(recommendIndex.isDisplayed(),"Recommend Index Button did not display");
            test.pass("Recommend Index Button displayed!");

            WebElement recommendBookDetail = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[1]/div"));
            Assertions.assertTrue(recommendBookDetail.isDisplayed(),"Recommend Book Detail did not display");
            test.pass("Recommend Book Detail Button displayed!");

            WebElement recommendReadMore = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[1]/div/button"));
            Assertions.assertTrue(recommendReadMore.isDisplayed(),"Recommend Read More Button did not display");
            test.pass("Recommend Read More Button displayed!");

        }catch (Exception e) {
            test.fail(e.getMessage());
        }
    }
    @ParameterizedTest
    @ValueSource(strings = {"chrome","edge","firefox"})
    public void testRecommendNext(String browser) {
        try{
            driver = BrowserFactory.getDriver(browser);
            driver.get("http://localhost:3000/");

            WebElement recommendNext = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[1]/button[2]"));
            Assertions.assertTrue(recommendNext.isDisplayed(),"Recommend Next Button did not display");

            recommendNext.click();

            WebElement recommend = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[1]/div/h1"));
            if(recommend.getText().equals("Harry Potter")){
                test.pass("Changed to next recommended book!");
            } else  test.fail("Unchanged to next recommended book!");

        }catch (Exception e) {
            test.fail(e.getMessage());
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"chrome","edge","firefox"})
    public void testRecommendNextByIndex(String browser) {
        try{
            driver = BrowserFactory.getDriver(browser);
            driver.get("http://localhost:3000/");

            //WebElement recommendNext = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[1]/button[2]"));
            //Assertions.assertTrue(recommendNext.isDisplayed(),"Recommend Next Button did not display");

            //recommendNext.click();

            WebElement recommendIndex = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[1]/div/div/button[2]"));
            recommendIndex.click();

            WebElement recommend = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[1]/div/h1"));
            if(recommend.getText().equals("Harry Potter")){
                test.pass("Changed to recommended book!");
            } else  test.fail("Unchanged to recommended book!");

        }catch (Exception e) {
            test.fail(e.getMessage());
        }
    }
    @ParameterizedTest
    @ValueSource(strings = {"chrome","edge","firefox"})
    public void testRecommendPrev(String browser) {
        try{
            driver = BrowserFactory.getDriver(browser);
            driver.get("http://localhost:3000/");

            WebElement recommendNext = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[1]/button[2]"));
            Assertions.assertTrue(recommendNext.isDisplayed(),"Recommend Next Button did not display");

            recommendNext.click();

            WebElement recommendPrevious = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[1]/button[1]"));
            Assertions.assertTrue(recommendPrevious.isDisplayed(),"Recommend Previous Button did not display");
            recommendPrevious.click();

            WebElement recommend = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[1]/div/h1"));
            if(recommend.getText().equals("Nhà Giả Kim")){
                test.pass("Changed to previous recommended book!");
            } else  test.fail("Unchanged to previous recommended book!");

        }catch (Exception e) {
            test.fail(e.getMessage());
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"chrome","edge","firefox"})
    public void testRecommendReadMore(String browser) {
        try{
            driver = BrowserFactory.getDriver(browser);
            driver.get("http://localhost:3000/");

            WebElement recommendReadMore = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[1]/div/button"));
            Assertions.assertTrue(recommendReadMore.isDisplayed(),"Recommend Read More Button did not display");
            recommendReadMore.click();

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            String currentUrl = driver.getCurrentUrl();
            String expectedUrl = "http://localhost:3000/Product/HomeDetail";

            if (currentUrl.equals(expectedUrl)) {
                test.pass("Change to book detail page successfully!");
            } else {
                test.fail("Change to book detail page unsuccessfully!");
            }
        }catch (Exception e) {
            test.fail(e.getMessage());
        }
    }
    @ParameterizedTest
    @ValueSource(strings = {"chrome","edge","firefox"})
    public void testNewReleaseField(String browser) {
        try{
            driver = BrowserFactory.getDriver(browser);
            driver.get("http://localhost:3000/");

            WebElement newReleaseField = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[2]"));
            Assertions.assertTrue(newReleaseField.isDisplayed(),"New Release field did not display");
            test.pass("New Release field link displayed!");

            WebElement newReleaseIndex = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[2]/div/div[2]"));
            Assertions.assertTrue(newReleaseIndex.isDisplayed(),"New Release Index Button did not display");
            test.pass("New Release Index Button  displayed!");

            WebElement newReleaseBookDetail = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[2]/div/div[1]"));
            Assertions.assertTrue(newReleaseBookDetail.isDisplayed(),"New Release Detail did not display");
            test.pass("New Release Book Detail Button displayed!");

            WebElement newReleaseViewAll = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[2]/div/button"));
            Assertions.assertTrue(newReleaseViewAll.isDisplayed(),"New Release View All Product Button did not display");
            test.pass("New Release View All Product Button  displayed!");
        }catch (Exception e) {
            test.fail(e.getMessage());
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"chrome","edge","firefox"})
    public void testNewReleaseNext(String browser) {
        try{
            driver = BrowserFactory.getDriver(browser);
            driver.get("http://localhost:3000/");

            WebElement newReleaseIndex = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[2]/div/div[2]/button[4]"));
            Assertions.assertTrue(newReleaseIndex.isDisplayed(),"New Release Index Button did not display");
            newReleaseIndex.click();

            WebElement booktitle = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[2]/div/div[1]/div/h3"));
            if(booktitle.getText().equals("Once upon a time")){
                test.pass("Changed to next new release book!");
            } else  test.fail("Unchanged to next new release book!");
        }catch (Exception e) {
        test.fail(e.getMessage());
        }
        }

    @ParameterizedTest
    @ValueSource(strings = {"chrome","edge","firefox"})
    public void testNewReleaseViewAll(String browser) {
        try{
            driver = BrowserFactory.getDriver(browser);
            driver.get("http://localhost:3000/");

            WebElement newReleaseViewAll = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[2]/div/button"));
            Assertions.assertTrue(newReleaseViewAll.isDisplayed(),"New Release View All Product Button did not display");
            newReleaseViewAll.click();
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            String currentUrl = driver.getCurrentUrl();
            String expectedUrl = "http://localhost:3000/Product";

            if (currentUrl.equals(expectedUrl)) {
                test.pass("Change to product page successfully!");
            } else {
                test.fail("Change to product page unsuccessfully!");
            }

        }catch (Exception e) {
            test.fail(e.getMessage());
        }
    }
    @ParameterizedTest
    @ValueSource(strings = {"chrome","edge","firefox"})
    public void testHotField(String browser) {
        try{
            driver = BrowserFactory.getDriver(browser);
            driver.get("http://localhost:3000/");

            WebElement hotField = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[3]"));
            Assertions.assertTrue(hotField.isDisplayed(),"Hot Book field did not display");
            test.pass("Hot Book field displayed!");

            WebElement hotNext = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[3]/button[1]"));
            Assertions.assertTrue(hotNext.isDisplayed(),"Hot Book Next Button did not display");
            test.pass("Hot Book Next Button displayed!");

            WebElement hotPrevious = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[3]/button[2]"));
            Assertions.assertTrue(hotPrevious.isDisplayed(),"Hot Book Previous Button did not display");
            test.pass("Hot Book Previous Button displayed!");

            WebElement hotIndex = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[3]/div/div"));
            Assertions.assertTrue(hotIndex.isDisplayed(),"Hot Book Index Button did not display");
            test.pass("Hot Book Index Buttondisplayed!");

            WebElement hotBookDetail = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[3]/div"));
            Assertions.assertTrue(hotBookDetail.isDisplayed(),"Hot Book Book Detail did not display");
            test.pass("Hot Book Book Detail Button displayed!");

            WebElement hotReadMore = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[3]/div/button"));
            Assertions.assertTrue(hotReadMore.isDisplayed(),"Hot Book Read More Button did not display");
            test.pass("Hot Book Read More Button displayed!");

        }catch (Exception e) {
            test.fail(e.getMessage());
        }
    }
    @ParameterizedTest
    @ValueSource(strings = {"chrome","edge","firefox"})
    public void testHotNext(String browser) {
        try{
            driver = BrowserFactory.getDriver(browser);
            driver.get("http://localhost:3000/");

            WebElement hotNext = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[3]/button[1]"));
            Assertions.assertTrue(hotNext.isDisplayed(),"Hot Book Next Button did not display");
            hotNext.click();

            WebElement hot = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[3]/div/h3"));
            if(hot.getText().equals("By Jane Doe")){
                test.pass("Changed to next book!");
            } else  test.fail("Unchanged to next book!");

        }catch (Exception e) {
            test.fail(e.getMessage());
        }
    }
    @ParameterizedTest
    @ValueSource(strings = {"chrome","edge","firefox"})
    public void testHotPrev(String browser) {
        try{
            driver = BrowserFactory.getDriver(browser);
            driver.get("http://localhost:3000/");

            WebElement hotNext = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[3]/button[1]"));
            Assertions.assertTrue(hotNext.isDisplayed(),"Hot Book Next Button did not display");
            hotNext.click();
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            WebElement hotPrevious = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[3]/button[2]"));
            Assertions.assertTrue(hotPrevious.isDisplayed(),"Hot Book Previous Button did not display");
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", hotPrevious);
            hotPrevious.click();
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


            WebElement hot = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[3]/div/h3"));
            if(hot.getText().equals("By Timbur Hood")){
                test.pass("Changed to next book!");
            } else  test.fail("Unchanged to next book!");

        }catch (Exception e) {
            test.fail(e.getMessage());
        }
    }
    @ParameterizedTest
    @ValueSource(strings = {"chrome","edge","firefox"})
    public void testHotIndex(String browser) {
        try{
            driver = BrowserFactory.getDriver(browser);
            driver.get("http://localhost:3000/");

            WebElement hotIndex = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[3]/div/div/button[2]"));
            Assertions.assertTrue(hotIndex.isDisplayed(),"Hot Book Index Button did not display");
            hotIndex.click();
            WebElement hot = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[3]/div/h3"));
            if(hot.getText().equals("By Jane Doe")){
                test.pass("Changed to next book!");
            } else  test.fail("Unchanged to next book!");

        }catch (Exception e) {
            test.fail(e.getMessage());
        }
    }
    @ParameterizedTest
    @ValueSource(strings = {"chrome","edge","firefox"})
    public void testHotViewMore(String browser) {
        try{
            driver = BrowserFactory.getDriver(browser);
            driver.get("http://localhost:3000/");

            WebElement hotReadMore = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[3]/div/button"));
            Assertions.assertTrue(hotReadMore.isDisplayed(),"Hot Book Read More Button did not display");
            hotReadMore.click();
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            String currentUrl = driver.getCurrentUrl();
            String expectedUrl = "http://localhost:3000/Product/HomeDetail";

            if (currentUrl.equals(expectedUrl)) {
                test.pass("Change to product page successfully!");
            } else {
                test.fail("Change to product page unsuccessfully!");
            }
        }catch (Exception e) {
            test.fail(e.getMessage());
        }
    }
    @ParameterizedTest
    @ValueSource(strings = {"chrome","edge","firefox"})
    public void testDiscountField(String browser) {
        try{
            driver = BrowserFactory.getDriver(browser);
            driver.get("http://localhost:3000/");

            WebElement discountField = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[4]"));
            Assertions.assertTrue(discountField.isDisplayed(),"Discount field did not display");
            test.pass("Discount field displayed!");

            WebElement discounIndex = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[4]/div[1]/div"));
            Assertions.assertTrue(discounIndex.isDisplayed(),"Discount Index Button did not display");
            test.pass("Discount Index Buttondisplayed!");

            WebElement discounBookDetail = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[4]/div[1]"));
            Assertions.assertTrue(discounBookDetail.isDisplayed(),"Discount Book Detail did not display");
            test.pass("Discount Book Detail Button displayed!");

        }catch (Exception e) {
            test.fail(e.getMessage());
        }
    }
    @ParameterizedTest
    @ValueSource(strings = {"chrome","edge","firefox"})
    public void testDiscountIndex(String browser) {
        try{
            driver = BrowserFactory.getDriver(browser);
            driver.get("http://localhost:3000/");

            WebElement discounIndex = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[4]/div[1]/div/button[2]"));
            Assertions.assertTrue(discounIndex.isDisplayed(),"Discount Index Button did not display");

            discounIndex.click();
            WebElement disCount = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[4]/div[1]/p[1]"));
            if(disCount.getText().equals("Get ready for summer with unbeatable discounts!")){
                test.pass("Changed to next discount!");
            } else  test.fail("Unchanged to next discount!");


        }catch (Exception e) {
            test.fail(e.getMessage());
        }
    }
    @ParameterizedTest
    @ValueSource(strings = {"chrome","edge","firefox"})
    public void testSubcribeField(String browser) {
        try{
            driver = BrowserFactory.getDriver(browser);
            driver.get("http://localhost:3000/");

            WebElement sucbribeField = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[5]"));
            Assertions.assertTrue(sucbribeField.isDisplayed(),"Subcribe Field did not display");
            test.pass("Subcribe field displayed!");

            WebElement sucbribeButton = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[5]/div/button"));
            Assertions.assertTrue(sucbribeButton.isDisplayed(),"Subcribe Button did not display");
            test.pass("Subcribe fielddisplayed!");

            WebElement sucbribeEmailBox = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[5]/div/div"));
            Assertions.assertTrue(sucbribeEmailBox.isDisplayed(),"Subcribe Email Box did not display");
            test.pass("Subcribe Email Box  displayed!");


        }catch (Exception e) {
            test.fail(e.getMessage());
        }
    }
    @ParameterizedTest
    @ValueSource(strings = {"chrome","edge","firefox"})
    public void testSubcribeBox(String browser) {
        try{
            driver = BrowserFactory.getDriver(browser);
            driver.get("http://localhost:3000/");

            WebElement sucbribeEmailBox = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[5]/div/div"));
            Assertions.assertTrue(sucbribeEmailBox.isDisplayed(),"Subcribe Email Box did not display");
            sucbribeEmailBox.sendKeys("example@gmail.com");
            String str = sucbribeEmailBox.getAttribute("value");
            if(str.equals("example@gmail.com")){
                test.pass("Enter email successfully!");
            }test.fail("Enter email unsuccessfully!");


        }catch (Exception e) {
            test.fail(e.getMessage());
        }
    }
    @ParameterizedTest
    @ValueSource(strings = {"chrome","edge","firefox"})
    public void testSubcribeBox100times(String browser) {
        try{
            driver = BrowserFactory.getDriver(browser);
            driver.get("http://localhost:3000/");

            WebElement sucbribeEmailBox = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[5]/div/div"));
            Assertions.assertTrue(sucbribeEmailBox.isDisplayed(),"Subcribe Email Box did not display");

            for (int i=0; i<100;i++){
                sucbribeEmailBox.sendKeys("example@gmail.com");
            }


        }catch (Exception e) {
            test.fail(e.getMessage());
        }
    }
    //@ValueSource(strings = {"chrome"})
    @ParameterizedTest
    @ValueSource(strings = {"chrome","edge","firefox"})
    public void testSubcribeButton(String browser) {
        try{
            driver = BrowserFactory.getDriver(browser);
            driver.get("http://localhost:3000/");
            WebElement sucbribeEmailBox = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[5]/div/div"));
            Assertions.assertTrue(sucbribeEmailBox.isDisplayed(),"Subcribe Email Box did not display");
            sucbribeEmailBox.sendKeys("example@gmail.com");

            WebElement sucbribeButton = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[5]/div/button"));
            Assertions.assertTrue(sucbribeButton.isDisplayed(),"Subcribe Button did not display");
            sucbribeButton.click();


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
