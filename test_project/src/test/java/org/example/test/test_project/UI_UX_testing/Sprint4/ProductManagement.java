package org.example.test.test_project.UI_UX_testing.Sprint4;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.checkerframework.checker.units.qual.t;
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

import io.restassured.internal.common.assertion.Assertion;

public class ProductManagement {
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
            String currentUrl = driver.getCurrentUrl();
            String expectedUrl = "http://localhost:3000/home-management";

            if (currentUrl.equals(expectedUrl)) {
                test.pass("Page changed to HOME MANAGEMENT page successfully!");
            } else {
                test.fail("Page unchanged!");
            }

        }catch (Exception e){
            test.fail(e);
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"chrome","firefox","edge"})
    public void testLoginByAdminAccountt(String browser){
        try{
            test.info("Check login by admin account!");
            login(browser);

        }catch (Exception e){
            test.fail(e);
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"chrome","firefox","edge"})
    public void testGotoProductManagementPage(String browser){
        try{
            test.info("test get to PRODUCT MANAGEMENT page!");
            login(browser);
            driver.get("http://localhost:3000/home-management");
            WebElement productmanagementlink = driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div/a[2]"));
            Assertions.assertTrue(productmanagementlink.isDisplayed());
            test.pass("Go to PRODUCT MANAGEMNET page link displayed!");

            productmanagementlink.click();
            test.pass("Click go to PRODUCT MANAGEMENT page link!");

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
    public void checkComponent(String browser){
        try{
            test.info("Check PRODUCT MANAGEMENT page components!");
            login(browser);
            
            driver.get("http://localhost:3000/product-management");
            test.pass("Page change to PRODUCT MANAGEMENT page successfully!");
            Thread.sleep(2000);

            WebElement addNewBookButton = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[2]/div[2]/div[1]"));
            Assertions.assertTrue(addNewBookButton.isDisplayed());
            test.pass("Add new book button displayed!");

            List<WebElement> products = driver.findElements(By.className("product-wrapper"));
            if(products.size() <=0){
                test.fail("Number of book: " + products.size());
            }else{
                test.pass("Number of book: " + products.size());
            }

            List<WebElement> productinfors = driver.findElements(By.className("product-info"));
            if(productinfors.size() <=0){
                test.fail("Number of book info: " + productinfors.size());
            }else{
                test.pass("Number of book info: " + productinfors.size());
            }

            List<WebElement> editButton = driver.findElements(By.className("edit-btn"));
            if(editButton.size() <=0){
                test.fail("Number of edit button: " + editButton.size());
            }else{
                test.pass("Number of edit button: " + editButton.size());
            }

            List<WebElement> deleteButton = driver.findElements(By.className("delete-btn"));
            if(deleteButton.size() <=0){
                test.fail("Number of delete button: " + deleteButton.size());
            }else{
                test.pass("Number of delete button: " + deleteButton.size());
            }
            //Chưa có title

            WebElement sortByButton = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[2]/div[1]/div[1]"));
            Assertions.assertTrue(sortByButton.isDisplayed());
            test.pass("Sort By button displayed!");

            WebElement numBookButton = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[2]/div[1]/div[3]"));
            Assertions.assertTrue(numBookButton.isDisplayed());
            test.pass("Choose number of book to show button displayed!");

            WebElement paginationButton = driver.findElement(By.className("pagination-container"));
            Assertions.assertTrue(paginationButton.isDisplayed());
            test.pass("Pagination buttons displayed!");

            WebElement inputMinPriceBox = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[1]/div[1]/div[2]/div[1]/div[1]/input"));
            Assertions.assertTrue(inputMinPriceBox.isDisplayed());
            test.pass("Input min price box displayed!");

            WebElement inputMaxPriceBox = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[1]/div[1]/div[2]/div[1]/div[3]/input"));
            Assertions.assertTrue(inputMaxPriceBox.isDisplayed());
            test.pass("Input max price box displayed!");

            WebElement filterByPriceButton = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[1]/div[1]/div[2]/div[2]/button"));
            Assertions.assertTrue(filterByPriceButton.isDisplayed());
            test.pass("Filter By Price button displayed!");

            WebElement filterByCategorySection = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[1]/div[2]"));
            Assertions.assertTrue(filterByCategorySection.isDisplayed());
            test.pass("Filter by category section displayed! Included: "+filterByCategorySection.getText());

            WebElement filterByPubliserSection = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[1]/div[3]"));
            Assertions.assertTrue(filterByPubliserSection.isDisplayed());
            test.pass("Filter by publisher section displayed! Included: "+filterByPubliserSection.getText());

            WebElement filterByCoverType = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[1]/div[3]"));
            Assertions.assertTrue(filterByCoverType.isDisplayed());
            test.pass("Filter by cover type section displayed! Included: "+filterByCoverType.getText());
            
        }catch (Exception e){
            test.fail(e);
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"chrome","edge","firefox"})
    public void testSortByButton(String browser){
        try{
            test.info("Check SORT BY button!");
            login(browser);
            
            driver.get("http://localhost:3000/product-management");
            test.pass("Page change to PRODUCT MANAGEMENT page successfully!");
            Thread.sleep(2000);

            WebElement sortByButton = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[2]/div[1]/div[1]/select"));
            Assertions.assertTrue(sortByButton.isDisplayed(),"Sort By Button did not displayed!");
            test.pass("Sort By Button displayed!");


            if (!sortByButton.isEnabled()) {
                test.fail("Sort By button is disabled!");
            } else {
                test.pass("Sort By button is clickable!");
                sortByButton.click();
                WebElement za = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[2]/div[1]/div[1]/select/option[2]"));
                Assertions.assertTrue(za.isDisplayed(),"Sort By Z-A did not displayed!");
                test.pass("Sort By Z-A displayed!");

                List<WebElement> items = driver.findElements(By.className("product-title"));
                test.pass("Total products found: " + items.size());
                if (items.size() == 1){
                    return;
                } else {
                    String beforeSort = items.get(0).getText();

                    za.click();
                    Thread.sleep(1000);

                    List<WebElement> newItems = driver.findElements(By.className("product-title"));
                    String afterSort = newItems.get(1).getText();

                    if (!beforeSort.equals(afterSort)) {
                        test.pass("Sorting works correctly!");
                    } else {
                        test.fail("Sorting did not change the list!");
                    }
                }
            }

        }catch (Exception e){
            test.fail(e);
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"chrome","edge","firefox"})
    public void checkUpdateNumOfBookButton(String browser){
        try{
            test.info("Check choose number of book a page button!");
            login(browser);
            
            driver.get("http://localhost:3000/product-management");
            test.pass("Page change to PRODUCT MANAGEMENT page successfully!");
            Thread.sleep(2000);

            WebElement numBookButton = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[2]/div[1]/div[3]/select"));
            Assertions.assertTrue(numBookButton.isDisplayed());
            test.pass("Choose number of book to show button displayed!");

            numBookButton.click();
            test.pass("Click Choose number of book to show button!");

            WebElement newBookButton = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[2]/div[1]/div[3]/select/option[2]"));
            Assertions.assertTrue(newBookButton.isDisplayed());
            test.pass("Choose new number of book to show button displayed!");

            List<WebElement> products = driver.findElements(By.className("product-wrapper"));
            int oldsize = products.size();
            if(products.size() <=0){
                test.fail("Old number of book: " + products.size());
            }else{
                test.pass("Old number of book: " + products.size());
            }

            newBookButton.click();
            Thread.sleep(1000);
            test.pass("Click Choose new number of book to show button!");

            products = driver.findElements(By.className("product-wrapper"));
            int newsize = products.size();
            if(products.size() <=0){
                test.fail("New size of book: " + products.size());
            }else{
                test.pass("New size of book: " + products.size());
            }

            if(oldsize != newsize){
                test.pass("Updated number of book a page!");
            }else{
                test.pass("Updated number of book a page failed!");
            }

            if(newsize == Integer.valueOf(newBookButton.getText())){
                test.pass("New number of book is equal with expected num!");
            }else{
                test.fail("New number of book is unequal with expected num!");
            }

        }catch (Exception e){
            test.fail(e);
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"chrome","edge","firefox"})
    public void checkPagButton(String browser){
        try{
            test.info("Check pagination buttons!");
            login(browser);
            
            driver.get("http://localhost:3000/product-management");
            test.pass("Page change to PRODUCT MANAGEMENT page successfully!");
            Thread.sleep(2000);

            WebElement nextButton = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[2]/div[3]/button[5]"));
            Assertions.assertTrue(nextButton.isDisplayed());
            nextButton.click();
            test.pass("Click Next Button!");

            WebElement prevButton = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[2]/div[3]/button[1]"));
            Assertions.assertTrue(prevButton.isDisplayed());
            prevButton.click();
            test.pass("Click Previous Button!");

            WebElement numButton = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[2]/div[3]/button[3]"));
            Assertions.assertTrue(numButton.isDisplayed());
            numButton.click();
            test.pass("Click Page number Button!");

        }catch (Exception e){
            test.fail(e);
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"chrome","edge","firefox"})
    public void checkEditBookButton(String browser){
        try{
            test.info("Check EDIT BOOK buttons!");
            login(browser);
            
            driver.get("http://localhost:3000/product-management");
            test.pass("Page change to PRODUCT MANAGEMENT page successfully!");
            Thread.sleep(2000);

            List<WebElement> editButton = driver.findElements(By.className("edit-btn"));
            if(editButton.size() <=0){
                test.fail("Number of edit button: " + editButton.size());
            }else{
                test.pass("Number of edit button: " + editButton.size());
            }

            editButton.get(0).click();
            Thread.sleep(1000);
            test.pass("Click edit button of the 1st book.");

            String currentUrl = driver.getCurrentUrl();
            String expectedUrl = "http://localhost:3000/edit-book";

            if (currentUrl.equals(expectedUrl)) {
                test.pass("Go to EDIT BOOK page successfully!");
            } else {
                test.fail("Go to EDIT BOOK page unsuccessfully!");
            }

        }catch (Exception e){
            test.fail(e);
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"chrome","edge","firefox"})
    public void checkDeleteBookButton(String browser){
        try{
            test.info("Check DELETE BOOK buttons!");
            login(browser);
            
            driver.get("http://localhost:3000/product-management");
            test.pass("Page change to PRODUCT MANAGEMENT page successfully!");
            Thread.sleep(2000);

            WebElement numBookButton = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[2]/div[1]/div[3]/select"));
            numBookButton.click();
            test.pass("Click Choose number of book to show button!");

            WebElement newBookButton = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[2]/div[1]/div[3]/select/option[3]"));
            newBookButton.click();

            List<WebElement> products = driver.findElements(By.className("product-wrapper"));
            int oldsize = products.size();
            if(products.size() <=0){
                test.fail("Old number of book: " + products.size());
            }else{
                test.pass("Old number of book: " + products.size());
            }

            List<WebElement> deleteButton = driver.findElements(By.className("delete-btn"));
            if(deleteButton.size() <=0){
                test.fail("Number of delete button: " + deleteButton.size());
            }else{
                test.pass("Number of delete button: " + deleteButton.size());
            }

            deleteButton.get(0).click();
            products = driver.findElements(By.className("product-wrapper"));
            int newsize = products.size();
            if(products.size() <=0){
                test.fail("New number of book: " + products.size());
            }else{
                test.pass("New number of book: " + products.size());
            }

            if(newsize - oldsize ==1){
                test.pass("Delete a book successfully!");
            }else{
                test.fail("Delete a book unsuccessfully!");
            }

        }catch (Exception e){
            test.fail(e);
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"chrome","edge","firefox"})
    public void checkAddNewBookButton(String browser){
        try{
            test.info("Check ADD NEW BOOK button!");
            login(browser);
            
            driver.get("http://localhost:3000/product-management");
            test.pass("Page change to PRODUCT MANAGEMENT page successfully!");
            Thread.sleep(2000);
            WebElement addNewBookButton = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[2]/div[2]/div[1]"));
            Assertions.assertTrue(addNewBookButton.isDisplayed());
            test.pass("Add new book button displayed!");
            
            addNewBookButton.click();
            test.pass("Click add new book button");
            
            Thread.sleep(1000);
            test.pass("Click edit button of the 1st book.");

            String currentUrl = driver.getCurrentUrl();
            String expectedUrl = "http://localhost:3000/add-book";

            if (currentUrl.equals(expectedUrl)) {
                test.pass("Go to ADD NEW BOOK page successfully!");
            } else {
                test.fail("Go to ADD NEW BOOK page unsuccessfully!");
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
