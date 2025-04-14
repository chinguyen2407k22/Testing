package org.example.test.test_project.UI_UX_testing.Sprint4;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

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

public class EditBook {
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
            driver.get("http://localhost:3000/product-management");
            test.pass("Change to PRODUCT MANAGEMENT page successfully!");
            Thread.sleep(1000);

            List<WebElement> editButton = driver.findElements(By.className("edit-btn"));
            if(editButton.size() <=0){
                test.fail("Number of edit button: " + editButton.size());
            }else{
                test.pass("Number of edit button: " + editButton.size());
            }
            editButton.get(1).click();
            Thread.sleep(1000);
            String currentUrl = driver.getCurrentUrl();
            String expectedUrl = "http://localhost:3000/edit-book";

            if (currentUrl.equals(expectedUrl)) {
                test.pass("Change to EDIT BOOK page successfully!");
            } else {
                test.fail("Page unchanged!");
            }
            Thread.sleep(1000);
            
        }catch (Exception e){
            test.fail(e);
        }
    }
     
    @ParameterizedTest
    @ValueSource(strings = {"chrome","edge","firefox"})
    public void checkComponent(String browser){
        try{
            test.info("Check EDIT BOOK page components.");
            login(browser);
            WebElement title = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/h1"));
            Assertions.assertTrue(title.isDisplayed());
            if(title.getText().equals("EDIT BOOK")){
                test.pass("Title is displayed! Right title: "+title.getText());
            }else{
                test.pass("Title is displayed! Wrong title: "+title.getText());
            }

            WebElement nameBox = driver.findElement(By.name("name"));
            Assertions.assertTrue(nameBox.isDisplayed());
            test.pass("Enter name box displayed!");

            WebElement authorBox = driver.findElement(By.name("author"));
            Assertions.assertTrue(authorBox.isDisplayed());
            test.pass("Enter author box displayed!");

            WebElement editorBox = driver.findElement(By.name("editor"));
            Assertions.assertTrue(editorBox.isDisplayed());
            test.pass("Enter editor box displayed!");

            WebElement genresBox = driver.findElement(By.name("genres"));
            Assertions.assertTrue(genresBox.isDisplayed());
            test.pass("Choose genres box displayed!");

            test.fail("Add new genres button didn't displayed!");

            WebElement costBox = driver.findElement(By.name("cost"));
            Assertions.assertTrue(costBox.isDisplayed());
            test.pass("Enter cost box displayed!");

            WebElement quantityBox = driver.findElement(By.name("quantity"));
            Assertions.assertTrue(quantityBox.isDisplayed());
            test.pass("Enter quantity box displayed!");

            WebElement bookImageBox = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/form/div[4]/div"));
            Assertions.assertTrue(bookImageBox.isDisplayed());
            test.pass("Select book image box displayed!");

            WebElement detailsBox = driver.findElement(By.name("details"));
            Assertions.assertTrue(detailsBox.isDisplayed());
            test.pass("Enter details box displayed!");

            WebElement overviewBox = driver.findElement(By.name("overview"));
            Assertions.assertTrue(overviewBox.isDisplayed());
            test.pass("Enter overview box displayed!");

            WebElement discardChangeButton = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/form/div[6]/button[1]"));
            Assertions.assertTrue(discardChangeButton.isDisplayed());
            test.pass("Discard Change Button displayed!");

            WebElement saveChangeButton = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/form/div[6]/button[2]"));
            Assertions.assertTrue(saveChangeButton.isDisplayed());
            test.pass("Save change button displayed!");


        }catch (Exception e){
            test.fail(e);
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"chrome","edge","firefox"})
    public void checkEnterBox(String browser){
        try{
            test.info("Test enter infomation boxes!");
            login(browser);

            WebElement nameBox = driver.findElement(By.name("name"));
            nameBox.clear();
            nameBox.sendKeys("Harry Potter and the Goblet of Fire");
            if(nameBox.getAttribute("value").equals("Harry Potter and the Goblet of Fire")){
                test.pass("Enter book name successfully! Book name: "+nameBox.getAttribute("value"));
            }else{
                test.fail("Enter book name unsuccessfully! Book name: "+nameBox.getAttribute("value"));
            }

            WebElement authorBox = driver.findElement(By.name("author"));
            authorBox.clear();
            authorBox.sendKeys("J. K. Rowling");
            if(authorBox.getAttribute("value").equals("J. K. Rowling")){
                test.pass("Enter book author successfully! Book author: "+authorBox.getAttribute("value"));
            }else{
                test.fail("Enter book author unsuccessfully! Book author: "+authorBox.getAttribute("value"));
            }
            
            WebElement editorBox = driver.findElement(By.name("editor"));
            editorBox.clear();
            editorBox.sendKeys("someone");
            if(editorBox.getAttribute("value").equals("someone")){
                test.pass("Enter book editor successfully! Book editor: "+editorBox.getAttribute("value"));
            }else{
                test.fail("Enter book editor unsuccessfully! Book editor: "+editorBox.getAttribute("value"));
            }

            WebElement genresBox = driver.findElement(By.name("genres"));
            test.fail("Choose Genres Box wasn't clickable!");

            test.fail("Add more genres button didn't display.");

            WebElement costBox = driver.findElement(By.name("cost"));
            costBox.clear();
            costBox.sendKeys("1.12");
            if(costBox.getAttribute("value").equals("1.12")){
                test.pass("Enter book price successfully! Price: "+costBox.getAttribute("value"));
            }else{
                test.fail("Enter book price unsuccessfully! Price: "+costBox.getAttribute("value"));
            }

            WebElement quantityBox = driver.findElement(By.name("quantity"));
            quantityBox.clear();
            quantityBox.sendKeys("10");
            if(quantityBox.getAttribute("value").equals("10")){
                test.pass("Enter book quantity successfully! Quantity: "+quantityBox.getAttribute("value"));
            }else{
                test.fail("Enter book quantity unsuccessfully! Quantity: "+quantityBox.getAttribute("value"));
            }

            WebElement detailsBox = driver.findElement(By.name("details"));
            detailsBox.clear();
            detailsBox.sendKeys("Detail infor");
            if(detailsBox.getAttribute("value").equals("Detail infor")){
                test.pass("Enter book details successfully! Details: "+detailsBox.getAttribute("value"));
            }else{
                test.fail("Enter book details unsuccessfully! Details: "+detailsBox.getAttribute("value"));
            }
           
            WebElement overviewBox = driver.findElement(By.name("overview"));
            overviewBox.clear();
            overviewBox.sendKeys("overview");
            if(overviewBox.getAttribute("value").equals("overview")){
                test.pass("Enter overview successfully! Overview: "+overviewBox.getAttribute("value"));
            }else{
                test.fail("Enter overview unsuccessfully! Overview: "+overviewBox.getAttribute("value"));
            }


        }catch (Exception e){
            test.fail(e);
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"chrome","edge","firefox"})
    public void checkWrongInput(String browser){
        try{
            test.info("Test enter infomation boxes with wrong format!");
            login(browser);
            WebElement nameBox = driver.findElement(By.name("name"));
            String term = "";
            for(int i=0;i<120;i++){
                term = term + "a";
            }
            nameBox.sendKeys(term);
            test.pass("Enter name: "+term);
            test.fail("The system did not raise errors.");

            WebElement authorBox = driver.findElement(By.name("author"));
            authorBox.sendKeys(term);
            test.pass("Enter author: "+term);
            test.fail("The system did not raise errors.");
            
            WebElement editorBox = driver.findElement(By.name("editor"));
            editorBox.sendKeys(term);
            test.pass("Enter editor: "+term);
            test.fail("The system did not raise errors.");

            WebElement costBox = driver.findElement(By.name("cost"));
            costBox.sendKeys("100000");
            test.pass("Enter cost: 100000");
            test.fail("The system did not raise errors.");

            costBox.clear();
            costBox.sendKeys("-1.12");
            if(costBox.getAttribute("value").equals("0")){
                test.pass("Enter book negative price unsuccessfully! Price: "+costBox.getAttribute("value"));
            }else{
                test.fail("Enter book negative price successfully! Price: "+costBox.getAttribute("value"));
            }

            WebElement quantityBox = driver.findElement(By.name("quantity"));
            quantityBox.sendKeys("100000");
            test.pass("Enter quantity: 100000");
            test.fail("The system did not raise errors.");

            quantityBox.clear();
            quantityBox.sendKeys("-10");
            if(quantityBox.getAttribute("value").equals("0")){
                test.pass("Enter book negative quantity unsuccessfully! Quantity: "+quantityBox.getAttribute("value"));
            }else{
                test.fail("Enter book negative quantity successfully! Quantity: "+quantityBox.getAttribute("value"));
            }
            for(int i=0;i<400;i++){
                term = term + "a";
            }

            WebElement detailsBox = driver.findElement(By.name("details"));
            detailsBox.sendKeys(term);
            test.pass("Enter details: "+term);
            test.fail("The system did not raise errors.");
            
           
            WebElement overviewBox = driver.findElement(By.name("overview"));
            overviewBox.sendKeys(term);
            test.pass("Enter overview: "+term);
            test.fail("The system did not raise errors.");

        }catch (Exception e){
            test.fail(e);
        }
    }
    @ParameterizedTest
    @ValueSource(strings = {"chrome","firefox","edge"})
    public void checkDiscardChange(String browser){
        try{
            test.info("Test discard change button.");
            login(browser);

            WebElement discardChangeButton = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/form/div[6]/button[1]"));
            Assertions.assertTrue(discardChangeButton.isDisplayed());
            test.pass("Discard Change Button displayed!");
            
            discardChangeButton.click();
            Thread.sleep(1000);
            String currentUrl = driver.getCurrentUrl();
            String expectedUrl = "http://localhost:3000/product-management";

            if (currentUrl.equals(expectedUrl)) {
                test.pass("Page changed to PRODUCT MANAGEMENT page successfully!");
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
