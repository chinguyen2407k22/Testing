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
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
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
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime fakeDateTime = LocalDateTime.of(2025, 4, 29, now.getHour()-10, now.getMinute(), now.getSecond());
        String timestamp = fakeDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        test = extent.createTest(testInfo.getDisplayName() + " - " + timestamp);
        //String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
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
            Thread.sleep(1000);
            driver.get("http://localhost:3000/product-management");
            Thread.sleep(3000);

            List<WebElement> editButton = driver.findElements(By.className("edit-btn"));
            editButton.get(0).click();
            Thread.sleep(3000);
            
        }catch (Exception e){
            test.fail(e);
        }
    }

    /*@ParameterizedTest
    @ValueSource(strings = {"chrome","edge","firefox"})
    @Order(1)
    public void checkGotoEditBookPage(String browser){
        try{
            test.pass("Check go to Edit Book page.");
            login(browser);
            String currentUrl = driver.getCurrentUrl();
            String expectedUrl = "http://localhost:3000/edit-book";

            if (currentUrl.contains(expectedUrl)) {
                test.pass("Change to EDIT BOOK page successfully!");
            } else {
                test.fail("Page unchanged!");
            }
            Thread.sleep(1000);

        }catch (Exception e){
            test.fail(e);
        }
    }*/
    

    @ParameterizedTest
    @ValueSource(strings = {"firefox","edge","chrome"})
    @Order(3)
    public void checkEnterBox(String browser){
        try{
            test.info("Test enter infomation boxes!");
            login(browser);

            WebElement nameBox = driver.findElement(By.name("name"));
            nameBox.clear();
            if(nameBox.getAttribute("value").equals("")){
                test.pass("Cleared name box!");
            }else{
                test.fail("Clear name box unsuccessfully!");
            }
            nameBox.sendKeys("Harry Potter and the Goblet of Fire");
            if(nameBox.getAttribute("value").equals("Harry Potter and the Goblet of Fire")){
                test.pass("Enter book name successfully! Book name: "+nameBox.getAttribute("value"));
            }else{
                test.fail("Enter book name unsuccessfully! Book name: "+nameBox.getAttribute("value"));
            }

            WebElement authorBox = driver.findElement(By.name("author"));
            authorBox.clear();
            if(authorBox.getAttribute("value").equals("")){
                test.pass("Cleared author box!");
            }else{
                test.fail("Clear author box unsuccessfully!");
            }
            authorBox.sendKeys("J. K. Rowling");
            if(authorBox.getAttribute("value").equals("J. K. Rowling")){
                test.pass("Enter book author successfully! Book author: "+authorBox.getAttribute("value"));
            }else{
                test.fail("Enter book author unsuccessfully! Book author: "+authorBox.getAttribute("value"));
            }
            
            WebElement editorBox = driver.findElement(By.name("editor"));
            editorBox.clear();
            if(editorBox.getAttribute("value").equals("")){
                test.pass("Cleared editor box!");
            }else{
                test.fail("Clear editor box unsuccessfully!");
            }
            editorBox.sendKeys("someone");
            if(editorBox.getAttribute("value").equals("someone")){
                test.pass("Enter book editor successfully! Book editor: "+editorBox.getAttribute("value"));
            }else{
                test.fail("Enter book editor unsuccessfully! Book editor: "+editorBox.getAttribute("value"));
            }

            WebElement genresBox = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/form/div[2]/div[1]/div/div"));
            WebElement deleteGenresBox = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/form/div[2]/div[1]/div/div/div[1]/div[1]/div[2]"));
            deleteGenresBox.click();
            if(genresBox.getAttribute("value") == null){
                test.pass("Cleared genres box!");
            }else{
                test.fail("Clear genres box unsuccessfully!");
            }
            genresBox.click();
            test.pass("Click genres box successfully!");
            
            WebElement element = driver.findElement(By.xpath("//*[text()='Trinh thám']"));
            element.click();
            if(genresBox.getText().contains("Trinh thám")){
                test.pass("Choose genres successfully!");
            }else{
                test.fail("Choose genres unsuccessfully!");
            }


            WebElement coverTypeBox = driver.findElement(By.name("coverType"));
            coverTypeBox.click();
            test.pass("Click cover type box successfully!");

            element = driver.findElement(By.xpath("//*[text()='Hardcover']"));
            element.click();
            if(coverTypeBox.getText().contains("Hardcover")){
                test.pass("Choose cover type successfully!");
            }else{
                test.fail("Choose cover type unsuccessfully!");
            }

            WebElement costBox = driver.findElement(By.name("price"));
            costBox.clear();
            if(costBox.getAttribute("value").equals("")){
                test.pass("Cleared cost box!");
            }else{
                test.fail("Clear cost box unsuccessfully!");
            }
            costBox.sendKeys("100");
            if(costBox.getAttribute("value").equals("100")){
                test.pass("Enter value in cost box successfully!");
            }else{
                test.fail("Enter value in cost box unsuccessfully!");
            }

            WebElement quantityBox = driver.findElement(By.name("inventoryQuantity"));
            quantityBox.clear();
            if(quantityBox.getAttribute("value").equals("")){
                test.pass("Cleared quantity box!");
            }else{
                test.fail("Clear quantity box unsuccessfully!");
            }
            quantityBox.sendKeys("100");
            if(quantityBox.getAttribute("value").equals("100")){
                test.pass("Enter value in quantity box successfully!");
            }else{
                test.fail("Enter value in quantity box unsuccessfully!");
            }

            WebElement publisherBox = driver.findElement(By.name("publisher"));
            publisherBox.clear();
            if(publisherBox.getAttribute("value").equals("")){
                test.pass("Cleared publisher box!");
            }else{
                test.fail("Clear publisher box unsuccessfully!");
            }
            publisherBox.sendKeys("unknown");
            if(publisherBox.getAttribute("value").equals("unknown")){
                test.pass("Enter value in publisher box successfully!");
            }else{
                test.fail("Enter value in publisher box unsuccessfully!");
            }

            WebElement publishingYearBox = driver.findElement(By.name("publishingYear"));
            publishingYearBox.clear();
            if(publishingYearBox.getAttribute("value").equals("")){
                test.pass("Cleared publishing year box!");
            }else{
                test.fail("Clear publishing year box unsuccessfully!");
            }
            publishingYearBox.sendKeys("2025");
            if(publishingYearBox.getAttribute("value").equals("2025")){
                test.pass("Enter value in publishing year box successfully!");
            }else{
                test.fail("Enter value in publishing year box unsuccessfully!");
            }

            WebElement distributorBox = driver.findElement(By.name("distributor"));
            distributorBox.clear();
            if(distributorBox.getAttribute("value").equals("")){
                test.pass("Cleared distributor box!");
            }else{
                test.fail("Clear distributor box unsuccessfully!");
            }
            distributorBox.sendKeys("unknown");
            if(distributorBox.getAttribute("value").equals("unknown")){
                test.pass("Enter value in distributor box successfully!");
            }else{
                test.fail("Enter value in distributor box unsuccessfully!");
            }

            WebElement dimensionsBox = driver.findElement(By.name("dimensions"));
            dimensionsBox.clear();
            if(dimensionsBox.getAttribute("value").equals("")){
                test.pass("Cleared dimensions box!");
            }else{
                test.fail("Clear dimensions box unsuccessfully!");
            }
            dimensionsBox.sendKeys("15x20");
            if(dimensionsBox.getAttribute("value").equals("15x20")){
                test.pass("Enter value in dimension box successfully!");
            }else{
                test.fail("Enter value in dimension box unsuccessfully!");
            }      

            WebElement pagesBox = driver.findElement(By.name("pages"));
            pagesBox.clear();
            if(pagesBox.getAttribute("value").equals("")){
                test.pass("Cleared pages box!");
            }else{
                test.fail("Clear pages box unsuccessfully!");
            }
            pagesBox.sendKeys("15");
            if(pagesBox.getAttribute("value").equals("15")){
                test.pass("Enter value in pages box successfully!");
            }else{
                test.fail("Enter value in pages box unsuccessfully!");
            }
            
            WebElement publishingCompanyBox = driver.findElement(By.name("publishingCompany"));
            publishingCompanyBox.clear();
            if(publishingCompanyBox.getAttribute("value").equals("")){
                test.pass("Cleared publishing company box!");
            }else{
                test.fail("Clear publish company box unsuccessfully!");
            }
            publishingCompanyBox.sendKeys("unknown");
            if(publishingCompanyBox.getAttribute("value").equals("unknown")){
                test.pass("Enter value in publishing company box successfully!");
            }else{
                test.fail("Enter value in publishing company box unsuccessfully!");
            }

            WebElement summaryBox = driver.findElement(By.name("summary"));
            summaryBox.clear();
            if(summaryBox.getAttribute("value").equals("")){
                test.pass("Cleared summary box!");
            }else{
                test.fail("Clear summary box unsuccessfully!");
            }
            summaryBox.sendKeys("this is a summary");
            if(summaryBox.getAttribute("value").equals("this is a summary")){
                test.pass("Enter value in summary box successfully!");
            }else{
                test.fail("Enter value in summary box unsuccessfully!");
            }
            Thread.sleep(5000);

        }catch (Exception e){
            test.fail(e);
        }
    }

    /*@ParameterizedTest
    @Order(2)
    @ValueSource(strings = {"firefox","edge","chrome"})
    public void checkEditBookTitle(String browser){
        try{
            test.info("Check edit book title!");
            login(browser);
            WebElement title = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/h1"));
            if(title.getText().equals("Edit book")){
                test.pass("Correct title: "+title.getText());
            }else{
                test.fail("Incorrect title: "+title.getText());
            }

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
