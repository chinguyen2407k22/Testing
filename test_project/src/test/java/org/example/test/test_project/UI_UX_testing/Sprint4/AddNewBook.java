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
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.params.provider.ValueSources;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
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

    /*@ParameterizedTest
    @ValueSource(strings={"chrome","edge","firefox"})
    @Order(1)
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

            WebElement authorBox = driver.findElement(By.name("author"));
            Assertions.assertTrue(authorBox.isDisplayed());
            test.pass("Enter author box displayed!");

            WebElement editorBox = driver.findElement(By.name("editor"));
            Assertions.assertTrue(editorBox.isDisplayed());
            test.pass("Enter editor box displayed!");

            WebElement genresBox = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/form/div[2]/div[1]/div/div"));
            Assertions.assertTrue(genresBox.isDisplayed());
            test.pass("Choose genres box displayed!");

            WebElement brandBox = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/form/div[2]/div[2]/div/div"));
            Assertions.assertTrue(brandBox.isDisplayed());
            test.pass("Choose brand box displayed!");

            WebElement coverTypeBox = driver.findElement(By.name("coverType"));
            Assertions.assertTrue(coverTypeBox.isDisplayed());
            test.pass("Choose cover type box displayed!");

            WebElement costBox = driver.findElement(By.name("price"));
            Assertions.assertTrue(costBox.isDisplayed());
            test.pass("Enter price box displayed!");

            WebElement quantityBox = driver.findElement(By.name("inventoryQuantity"));
            Assertions.assertTrue(quantityBox.isDisplayed());
            test.pass("Enter inventory quantity box displayed!");

            WebElement publisherBox = driver.findElement(By.name("publisher"));
            Assertions.assertTrue(publisherBox.isDisplayed());
            test.pass("Enter publisher box displayed!");

            WebElement publishingYearBox = driver.findElement(By.name("publishingYear"));
            Assertions.assertTrue(publishingYearBox.isDisplayed());
            test.pass("Enter publishing year box displayed!");

            WebElement distributorBox = driver.findElement(By.name("distributor"));
            Assertions.assertTrue(distributorBox.isDisplayed());
            test.pass("Enter distributor box displayed!");

            WebElement dimensionsBox = driver.findElement(By.name("dimensions"));
            Assertions.assertTrue(dimensionsBox.isDisplayed());
            test.pass("Enter dimensions box displayed!");

            WebElement pagesBox = driver.findElement(By.name("pages"));
            Assertions.assertTrue(pagesBox.isDisplayed());
            test.pass("Enter number of pages box displayed!");

            WebElement publishingCompanyBox = driver.findElement(By.name("publishingCompany"));
            Assertions.assertTrue(publishingCompanyBox.isDisplayed());
            test.pass("Enter publishing company box displayed!");

            WebElement bookImageBox = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/form/div[4]/div"));
            Assertions.assertTrue(bookImageBox.isDisplayed());
            test.pass("Select book image box displayed!");

            WebElement summaryBox = driver.findElement(By.name("summary"));
            Assertions.assertTrue(summaryBox.isDisplayed());
            test.pass("Enter summary box displayed!");

            WebElement discardChangeButton = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/form/div[8]/button[1]"));
            Assertions.assertTrue(discardChangeButton.isDisplayed());
            test.pass("Discard Change Button displayed!");

            WebElement saveChangeButton = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/form/div[8]/button[2]"));
            Assertions.assertTrue(saveChangeButton.isDisplayed());
            test.pass("Save change button displayed!");

        }catch (Exception e){
            test.fail(e);
        }
    }*/

    @ParameterizedTest
    @ValueSource(strings = {"chrome"})
    @Order(2)
    public void checkEnterBox(String browser){
        try{
            test.info("Test enter infomation boxes!");
            login(browser);

            WebElement nameBox = driver.findElement(By.name("name"));
            nameBox.sendKeys("Harry Potter and the Goblet of Fire");
            if(nameBox.getAttribute("value").equals("Harry Potter and the Goblet of Fire")){
                test.pass("Enter book name successfully! Book name: "+nameBox.getAttribute("value"));
            }else{
                test.fail("Enter book name unsuccessfully! Book name: "+nameBox.getAttribute("value"));
            }

            WebElement authorBox = driver.findElement(By.name("author"));
            authorBox.sendKeys("J. K. Rowling");
            if(authorBox.getAttribute("value").equals("J. K. Rowling")){
                test.pass("Enter book author successfully! Book author: "+authorBox.getAttribute("value"));
            }else{
                test.fail("Enter book author unsuccessfully! Book author: "+authorBox.getAttribute("value"));
            }
            
            WebElement editorBox = driver.findElement(By.name("editor"));
            editorBox.sendKeys("someone");
            if(editorBox.getAttribute("value").equals("someone")){
                test.pass("Enter book editor successfully! Book editor: "+editorBox.getAttribute("value"));
            }else{
                test.fail("Enter book editor unsuccessfully! Book editor: "+editorBox.getAttribute("value"));
            }

            WebElement genresBox = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/form/div[2]/div[1]/div/div"));
            genresBox.click();
            test.pass("Click genres box successfully!");
            
            WebElement element = driver.findElement(By.xpath("//*[text()='Trinh thám']"));
            element.click();
            if(genresBox.getText().contains("Trinh thám")){
                test.pass("Choose genres successfully!");
            }else{
                test.fail("Choose genres unsuccessfully!");
            }

            WebElement brandBox = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/form/div[2]/div[2]/div/div"));
            brandBox.click();
            test.pass("Click brand box sucessfully!");
            
            element = driver.findElement(By.xpath("//*[text()='Trinh thám pháp y']"));
            element.click();
            if(brandBox.getText().contains("Trinh thám pháp y")){
                test.pass("Choose brand successfully!");
            }else{
                test.fail("Choose brand unsuccessfully!");
            }

            WebElement coverTypeBox = driver.findElement(By.name("coverType"));
            coverTypeBox.click();
            test.pass("Click cover type box successfully!");

            element = driver.findElement(By.xpath("//*[text()='Hardcover']"));
            element.click();
            if(brandBox.getText().contains("Hardcover")){
                test.pass("Choose cover type successfully!");
            }else{
                test.fail("Choose cover type unsuccessfully!");
            }

            WebElement costBox = driver.findElement(By.name("price"));
            costBox.sendKeys("100");
            if(costBox.getAttribute("value").equals("100")){
                test.pass("Enter value in cost box successfully!");
            }else{
                test.fail("Enter value in cost box unsuccessfully!");
            }

            WebElement quantityBox = driver.findElement(By.name("inventoryQuantity"));
            Assertions.assertTrue(quantityBox.isDisplayed());
            test.pass("Enter inventory quantity box displayed!");

            WebElement publisherBox = driver.findElement(By.name("publisher"));
            Assertions.assertTrue(publisherBox.isDisplayed());
            test.pass("Enter publisher box displayed!");

            WebElement publishingYearBox = driver.findElement(By.name("publishingYear"));
            Assertions.assertTrue(publishingYearBox.isDisplayed());
            test.pass("Enter publishing year box displayed!");

            WebElement distributorBox = driver.findElement(By.name("distributor"));
            Assertions.assertTrue(distributorBox.isDisplayed());
            test.pass("Enter distributor box displayed!");

            WebElement dimensionsBox = driver.findElement(By.name("dimensions"));
            Assertions.assertTrue(dimensionsBox.isDisplayed());
            test.pass("Enter dimensions box displayed!");

            WebElement pagesBox = driver.findElement(By.name("pages"));
            Assertions.assertTrue(pagesBox.isDisplayed());
            test.pass("Enter number of pages box displayed!");

            WebElement publishingCompanyBox = driver.findElement(By.name("publishingCompany"));
            Assertions.assertTrue(publishingCompanyBox.isDisplayed());
            test.pass("Enter publishing company box displayed!");

            WebElement bookImageBox = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/form/div[4]/div"));
            Assertions.assertTrue(bookImageBox.isDisplayed());
            test.pass("Select book image box displayed!");

            WebElement summaryBox = driver.findElement(By.name("summary"));
            Assertions.assertTrue(summaryBox.isDisplayed());
            test.pass("Enter summary box displayed!");

        }catch (Exception e){
            test.fail(e);
        }
    }

    /*@ParameterizedTest
    @ValueSource(strings = {"chrome","firefox","edge"})
    public void checkSaveChangeAnDiscardChange(String browser){
        try{
            test.info("Test save change button and discard change button.");
            login(browser);

            WebElement saveChangeButton = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/form/div[6]/button[2]"));
            if(!saveChangeButton.isEnabled()){
                test.pass("Save change button disabled if users didn't complete all field.");
            }else{
                test.fail("Save change button enabled if users didn't complete all field.");
            }

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
    }*/
    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @AfterAll
    public static void closeTest(){
        ExtendReport.closeReport();
    }


}
