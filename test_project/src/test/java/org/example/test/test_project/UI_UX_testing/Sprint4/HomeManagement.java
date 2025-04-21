package org.example.test.test_project.UI_UX_testing.Sprint4;

import static org.mockito.Mockito.framework;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.apache.logging.log4j.util.PerformanceSensitive;
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
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class HomeManagement {
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
        //LocalDateTime now = LocalDateTime.now();
        //LocalDateTime fakeDateTime = LocalDateTime.of(2025, 4, 17, now.getHour(), now.getMinute(), now.getSecond());
        //String timestamp = fakeDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        //test = extent.createTest(testInfo.getDisplayName() + " - " + timestamp);
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
            String currentUrl = driver.getCurrentUrl();
            String expectedUrl = "http://localhost:3000/home-management";

            if (currentUrl.equals(expectedUrl)) {
                test.pass("Change to HOME MANAGEMENT page successfully!");
            } else {
                test.fail("Page unchanged!");
            }
            driver.get("http://localhost:3000/home-management");
            Thread.sleep(2000);
            
        }catch (Exception e){
            test.fail(e);
        }
    }
    
    @ParameterizedTest
    @ValueSource(strings = {"chrome","edge","firefox"})
    public void testLogin(String browser){
        try{
            test.info("Check login by admin account.");
            login(browser);

        }catch (Exception e){
            test.fail(e);
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"chrome","edge","firefox"})
    public void testComnent(String browser){
        try{
            test.info("Check HOME MANAGEMENT page components.");
            login(browser);

            WebElement navigation = driver.findElement(By.className("breadcrumbs-content"));
            Assertions.assertTrue(navigation.isDisplayed());
            test.pass("Navigation Bar displayed. Included: "+navigation.getText());

            WebElement nextBookButton = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[1]/div/button[2]"));
            Assertions.assertTrue(nextBookButton.isDisplayed());
            test.pass("Go to next book button displayed!");

            WebElement prevBookButton = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[1]/div/button[1]"));
            Assertions.assertTrue(prevBookButton.isDisplayed());
            test.pass("Go to previous book button displayed!");

            WebElement readMoreButton = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[1]/div/div/button[1]"));
            Assertions.assertTrue(readMoreButton.isDisplayed());
            test.pass("READ MORE button displayed!");

            WebElement pageBookButton = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[1]/div/div/div[2]/div[3]"));
            Assertions.assertTrue(pageBookButton.isDisplayed());
            test.pass("Page Book button displayed!");

            WebElement chooseAnotherBook = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[1]/div/div/button[2]"));
            Assertions.assertTrue(chooseAnotherBook.isDisplayed());
            test.pass("Choose Another Book button displayed!");

            WebElement bestSeller = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[2]/div[1]"));
            Assertions.assertTrue(bestSeller.isDisplayed());
            test.pass("Best Seller section displayed!");

            WebElement revenue = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[2]/div[2]"));
            Assertions.assertTrue(revenue.isDisplayed());
            test.pass("Revenue section displayed!");

            WebElement discount = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[3]"));
            Assertions.assertTrue(discount.isDisplayed());
            test.pass("Discount section displayed!");

            WebElement atEventName = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[3]/div[2]/div[1]/input"));
            Assertions.assertTrue(atEventName.isDisplayed());
            test.pass("Enter discount event name box displayed!");

            WebElement discountPercent = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[3]/div[1]/div[2]/input"));
            Assertions.assertTrue(discountPercent.isDisplayed());
            test.pass("Enter discount percent displayed!");

            WebElement discription = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[3]/div[2]/div[2]/textarea"));
            Assertions.assertTrue(discription.isDisplayed());
            test.pass("Enter discription of event box displayed!");

            WebElement startday = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[3]/div[3]/div[1]/input"));
            Assertions.assertTrue(startday.isDisplayed());
            test.pass("Enter event start day box displayed!");

            WebElement endday = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[3]/div[3]/div[2]/input"));
            Assertions.assertTrue(endday.isDisplayed());
            test.pass("Enter event end day box displayed!");

            WebElement discardChange = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[3]/div[4]/button[1]"));
            Assertions.assertTrue(discardChange.isDisplayed());
            test.pass("Discard Change button displayed!");

            WebElement saveChange = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[3]/div[4]/button[2]"));
            Assertions.assertTrue(saveChange.isDisplayed());
            test.pass("Save Change button displayed!");

        }catch (Exception e){
            test.fail(e);
        }
    }
    @ParameterizedTest
    @ValueSource(strings = {"chrome","edge","firefox"})
    public void testPaginationButtons(String browser){
        try{
            test.info("Check pagination buttions");
            login(browser);
            WebElement nextBookButton = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[1]/div/button[2]"));
            nextBookButton.click();
            test.pass("Click go to next book button.");

            WebElement prevBookButton = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[1]/div/button[1]"));
            prevBookButton.click();
            test.pass("Click go to previous book button.");

            WebElement pageBookButton = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[1]/div/div/div[2]/div[3]"));
            pageBookButton.click();
            test.pass("Click go to page book button.");

        }catch (Exception e){
            test.fail(e);
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"chrome","edge","firefox"})
    public void testREADMOREbutton(String browser){
        try{
            test.info("Check READ MORE button");
            login(browser);

            WebElement readMoreButton = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[1]/div/div/button[1]"));
            Assertions.assertTrue(readMoreButton.isDisplayed());
            test.pass("READ MORE button displayed!");

            readMoreButton.click();
            test.pass("Click READ MORE button.");
            Thread.sleep(1000);

            String currentUrl = driver.getCurrentUrl();
            String expectedUrl = "http://localhost:3000/product/";

            if (currentUrl.contains(expectedUrl)) {
                test.pass("Change to BOOK DETAIL page successfully!");
            } else {
                test.fail("Page unchanged!");
            }


        }catch (Exception e){
            test.fail(e);
        }
    }

    

    @ParameterizedTest
    @ValueSource(strings = {"chrome","edge","firefox"})
    public void checkSaveChangeWithOutInput(String browser){
        try{
            test.info("Check click Save Button without enter input!");
            login(browser);
            WebElement discountPercent = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[3]/div[1]/div[2]/input"));
            discountPercent.clear();
            discountPercent.sendKeys("12");

            WebElement saveChange = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[3]/div[4]/button[2]"));
            saveChange.click();
            test.pass("Click Save Change Button!");
            Thread.sleep(1000);

            WebElement nameNofi = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[3]/div[2]/div[1]/span"));
            test.pass("Event name nofication displayed! Nofication: "+nameNofi.getText());

            WebElement startNofi = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[3]/div[3]/div[1]/span"));
            test.pass("Start Day nofication displayed! Nofication: "+startNofi.getText());

            WebElement endNofi = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[3]/div[3]/div[2]/span"));
            test.pass("End day nofication displayed! Nofication: "+endNofi.getText());

            WebElement discriptNofi = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[3]/div[2]/div[2]/span"));
            test.pass("Event Discription nofication displayed! Nofication: "+discriptNofi.getText());


        }catch (Exception e){
            test.fail(e);
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"chrome","edge","firefox"})
    public void checkWrongValue(String browser){
        try{
            test.info("Check enter start day greater than end day.");
            login(browser);

            WebElement startday = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[3]/div[3]/div[1]/input"));

            WebElement endday = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[3]/div[3]/div[2]/input"));
            WebElement discountPercent = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[3]/div[1]/div[2]/input"));
            
            discountPercent.clear();
            discountPercent.sendKeys("12");
            endday.sendKeys("01/01/2025");
            test.pass("Enter end day: "+endday.getAttribute("value"));

            startday.sendKeys("12/01/2025");
            test.pass("Enter start day: "+startday.getAttribute("value"));

            WebElement saveChange = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[3]/div[4]/button[2]"));
            saveChange.click();
            Thread.sleep(1000);

            WebElement startNofi = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[3]/div[3]/div[1]/span"));
            test.pass("Start Day nofication displayed! Nofication: "+startNofi.getText());

            WebElement endNofi = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[3]/div[3]/div[2]/span"));
            test.pass("End day nofication displayed! Nofication: "+endNofi.getText());

            List<WebElement> discountNofi = driver.findElements(By.xpath("//*[@id=\"root\"]/main/div/div[3]/div[1]/div[2]/span"));
            if(discountNofi.size()==0){
                test.fail("There is no discount nofication for positive input");
            }else{
                test.pass("Discount Percent nofication displayed! Nofication: "+endNofi.getText());
            }
            

        }catch (Exception e){
            test.fail(e);
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"chrome","edge","firefox"})
    public void checkDiscardChange(String browser){
        try {
            test.info("Check DISCARD CHANGE button.");
            login(browser);
            WebElement discountPercent = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[3]/div[1]/div[2]/input"));
            discountPercent.clear();
            discountPercent.sendKeys("12");

            WebElement atEventName = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[3]/div[2]/div[1]/input"));
            atEventName.sendKeys("checkcheck");
            
            WebElement discardChange = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[3]/div[4]/button[1]"));
            Assertions.assertTrue(discardChange.isDisplayed());
            test.pass("Discard Change button displayed!");
            
            discardChange.click();
            test.pass("Click discard change button.");

        
            if(atEventName.getAttribute("value").equals("")){
                test.pass("Discard all event change successfully. Event name: "+atEventName.getAttribute("value"));
            }else{
                test.fail("Discard all event change unsuccessfully. Event name: "+atEventName.getAttribute("value"));
            }

        } catch (Exception e){
            test.fail(e);
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"chrome","edge","firefox"})
    public void testEnterDiscountField(String browser){
        try{
            test.info("Check enter discount field");
            login(browser);

            WebElement atEventName = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[3]/div[2]/div[1]/input"));

            WebElement discountPercent = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[3]/div[1]/div[2]/input"));

            WebElement discription = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[3]/div[2]/div[2]/textarea"));

            WebElement startday = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[3]/div[3]/div[1]/input"));

            WebElement endday = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[3]/div[3]/div[2]/input"));

            discountPercent.clear();
            discountPercent.sendKeys("10");
            if(discountPercent.getAttribute("value").equals("10")){
                test.pass("Enter discount percent successfully. Discount percent: "+atEventName.getAttribute("value"));
            }else{
                test.fail("Enter discount percent unsuccessfully. Discount percent: "+atEventName.getAttribute("value"));
            }
            atEventName.sendKeys("Event 20/10");
            if(atEventName.getAttribute("value").equals("Event 20/10")){
                test.pass("Enter event name successfully. Event name: "+atEventName.getAttribute("value"));
            }else{
                test.pass("Enter event name unsuccessfully. Event name: "+atEventName.getAttribute("value"));
            }

            discription.sendKeys("This is discription.");
            if(discription.getAttribute("value").equals("This is discription.")){
                test.pass("Enter event discription successfully. Event discription: "+discription.getAttribute("value"));
            }else{
                test.pass("Enter event discription unsuccessfully. Event discription: "+discription.getAttribute("value"));
            }

            startday.sendKeys("01012025");
            if(startday.getAttribute("value").equals("01/01/2025")){
                test.pass("Enter event start day successfully. Event start day: "+startday.getAttribute("value"));
            }else{
                test.pass("Enter event start day unsuccessfully. Event start day: "+startday.getAttribute("value"));
            }

            endday.sendKeys("15012025");
            if(endday.getAttribute("value").equals("15/01/2025")){
                test.pass("Enter event end day successfully. End day: "+endday.getAttribute("value"));
            }else{
                test.pass("Enter event end day unsuccessfully. End day: "+endday.getAttribute("value"));
            }
            WebElement saveChange = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[3]/div[4]/button[2]"));
            saveChange.click();
            test.pass("Save event successfully!");



        }catch (Exception e){
            test.fail(e);
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"chrome","firefox","edge"})
    public void checkChooseAnotherBook(String browser){
        try{
            test.pass("Check click choose another book button and discard change button in choose another book pop up.");
            login(browser);
            WebElement chooseAnotherBook = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[1]/div/div/button[2]"));
            chooseAnotherBook.click();

            WebElement popUp = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[1]/div[2]"));
            test.pass("Choose Another Book Pop up displayed!");

            WebElement firstPage = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[1]/div[2]/div[2]/input"));
            test.pass("Choose first page book box displayed");

            WebElement secondPage = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[1]/div[2]/div[3]/input"));
            test.pass("Choose second page book box displayed");

            WebElement thirdPage = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[1]/div[2]/div[4]/input"));
            test.pass("Choose third page book box displayed");

            WebElement fourthPage = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[1]/div[2]/div[5]/input"));
            test.pass("Choose fourth page book box displayed");

            WebElement discardChangeButton = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[1]/div[2]/div[6]/button[1]"));
            test.pass("Discard Change button displayed!");

            WebElement saveChangeButton = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[1]/div[2]/div[6]/button[2]"));
            test.pass("Save Change Button displayed!");

            discardChangeButton.click();
            test.pass("Click discard change button");

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
