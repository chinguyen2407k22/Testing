package org.example.test.test_project.UI_UX_testing.Sprint5;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ContactAndVoucherManagement {
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

            Thread.sleep(1000);
            driver.get("http://localhost:3000/home-management");
            Thread.sleep(1000);
            WebElement otherlink = driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div/a[4]"));
            otherlink.click();
            Thread.sleep(1000);

        }catch (Exception e){
            test.fail(e);
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"chrome","edge","firefox"})
    @Order(1)
    public void checkOtherLink(String browser){
        try{
            test.info("Check go to CONTACT AND VOUCHER MANAGEMENT page.");
            login(browser);
            String currentUrl = driver.getCurrentUrl();
            String expectedUrl = "http://localhost:3000/contact-and-voucher";

            if (currentUrl.equals(expectedUrl)) {
                test.pass("Page changed to CONTACT AND VOUCHER MANAGEMENT page successfully!");
            } else {
                test.fail("Page unchanged!");
            }

        }catch (Exception e){
            test.fail(e);
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"chrome","edge","firefox"})
    @Order(2)
    public void checkContactManagementTitle(String browser){
        try{
            test.info("Check Contact Management section title.");
            login(browser);
            WebElement title = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/h1[1]"));
            test.pass("Check Contact Management section displayed!");
            if(title.getText().equals("VIEW CONTACT")){
                test.pass("Correct title: "+title.getText());
            }else{
                test.fail("Incorrect title: "+title.getText());
            }

        }catch (Exception e){
            test.fail(e);
        }
    }

    @ParameterizedTest
    @Order(3)
    @ValueSource(strings = {"chrome","firefox","edge"})
    public void checkViewContactSectionComponent(String browser){
        try{
            test.info("Check View Contact Section's component.");
            login(browser);
            List<WebElement> contactTitleList = new ArrayList<>();
            List<WebElement> contactTitles = driver.findElements(By.tagName("p"));

            for (WebElement p : contactTitles) {
                if (p.getText().startsWith("NOM")) {
                    contactTitleList.add(p);
                }
            }
            if(contactTitleList.size() > 0){
                test.pass("Contact titles displayed! Num of contacts: "+contactTitleList.size());
            }else{
                test.fail("Contact titles didn't display! Num of contacts: "+contactTitleList.size());
            }
            
            int contactCount = contactTitleList.size();
            int nameCount = 0;
            int phoneCount = 0;
            int emailCount = 0;
            int contactTypeCount = 0;
            int contentCount = 0;

            for (WebElement p : contactTitles) {
                String text = p.getText();
                if (text.startsWith("Name:")) {
                    nameCount++;
                } else if (text.startsWith("Phonenumber:")) {
                    phoneCount++;
                } else if (text.startsWith("Email:")) {
                    emailCount++;
                } else if (text.startsWith("Contact-type:")) {
                    contactTypeCount++;
                } else if (text.startsWith("Content: M")) {
                    contentCount++;
                }
            }
            boolean valid = (contactCount == nameCount) &&
                            (contactCount == phoneCount) &&
                            (contactCount == emailCount) &&
                            (contactCount == contactTypeCount) &&
                            (contactCount == contentCount);

            if (valid) {
                test.pass("All contact information fields match the number of CONTACT titles.");
            } else {
                test.fail("Mismatch between CONTACT titles and information fields. Please check the data.");
            }
            
            WebElement previousButton = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[2]/div[1]"));
            test.pass("Previous Button displayed!");
            WebElement nextButton = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[2]/div[3]"));
            test.pass("Next Button displayed!");
            WebElement paginationButton = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[2]/div[2]"));
            test.pass("Paginations Button displayed!");

        }catch (Exception e){
            test.fail(e);
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"chrome","firefox","edge"})
    @Order(4)
    public void checkFileLink(String browser){
        try{
            test.pass("Check open pdf file.");
            login(browser);
            WebElement fileLink = driver.findElement(By.xpath("//a[contains(@href, '.pdf')]"));
            fileLink.click();
            String originalTab = driver.getWindowHandle();
            Set<String> allTabs = driver.getWindowHandles();
            for (String tab : allTabs) {
                if (!tab.equals(originalTab)) {
                    driver.switchTo().window(tab);
                    break;
                }
            }

            String currentUrl = driver.getCurrentUrl();
            if (!currentUrl.equals(originalTab)) {
                test.pass("Successfully opened the file in a new tab. URL: " + currentUrl);
            } else {
                test.fail("Failed to open the correct file. URL: " + currentUrl);
            }
        }catch (Exception e){
            test.fail(e);
        }
    }

    @ParameterizedTest
    @Order(5)
    @ValueSource(strings = {"chrome","firefox","edge"})
    public void checkPaginationButtonInContactManagementSection(String browser){
        try{
            test.info("Check pagination buttons in CONTACT MANAGEMENT SECTION");
            login(browser);
            WebElement previousButton = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[2]/div[1]"));
            WebElement nextButton = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[2]/div[3]"));
            WebElement paginationButton = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[2]/div[2]"));

            nextButton.click();
            test.pass("Click Next Button successfully!");
            
            previousButton.click();
            test.pass("Click Previous Button successfully!");

            paginationButton.click();
            test.pass("Click Pagination Button successfully!");

        }catch (Exception e){
            test.fail(e);
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"chrome","firefox","edge"})
    @Order(6)
    public void checkVoucherManagementTitle(String browser){
        try{
            test.info("Check Voucher Management section title.");
            login(browser);
            WebElement title = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/h1[2]"));
            test.pass("Check Voucher Management section displayed!");
            if(title.getText().equals("VIEW VOUCHER")){
                test.pass("Correct title: "+title.getText());
            }else{
                test.fail("Incorrect title: "+title.getText());
            }

        }catch (Exception e){
            test.fail(e);
        }
    }

    @ParameterizedTest
    @Order(7)
    @ValueSource(strings = {"chrome","firefox","edge"})
    public void checkVoucherComponents(String browser){
        try{
            test.info("Check voucher section title and details information.");
            login(browser);
            List<WebElement> voucherParagraphs = driver.findElements(By.tagName("p"));
            List<WebElement> voucherTitleList = new ArrayList<>();

            for (WebElement p : voucherParagraphs) {
                if (p.getText().startsWith("VOUCHER")) {
                    voucherTitleList.add(p);
                }
            }
            if(voucherTitleList.size() > 0){
                test.pass("Contact titles displayed! Num of contacts: "+voucherTitleList.size());
            }else{
                test.fail("Contact titles didn't display! Num of contacts: "+voucherTitleList.size());
            }
            
            int voucherContentCount = 0;    
            int voucherQuantityCount = 0;
            int voucherStartDateCount = 0;
            int voucherEndDateCount = 0;
            int voucherNoteCount = 0;

            for (WebElement p : voucherParagraphs) {
                String text = p.getText();
                if (text.startsWith("Content: t")) {
                    voucherContentCount++;
                } else if (text.startsWith("Số lượng voucher:")) {
                    voucherQuantityCount++;
                } else if (text.startsWith("Ngày bắt đầu:")) {
                    voucherStartDateCount++;
                } else if (text.startsWith("Ngày kết thúc:")) {
                    voucherEndDateCount++;
                } else if (text.startsWith("Ghi chú:")) {
                    voucherNoteCount++;
                }
            }

            boolean voucherValid = (voucherTitleList.size() == voucherContentCount) &&
                       (voucherTitleList.size()  == voucherQuantityCount) &&
                       (voucherTitleList.size()  == voucherStartDateCount) &&
                       (voucherTitleList.size()  == voucherEndDateCount) &&
                       (voucherTitleList.size()  == voucherNoteCount);

            if (voucherValid) {
                test.pass("All voucher information fields match the number of VOUCHER titles.");
            } else {
                test.fail("Mismatch between VOUCHER titles and information fields. Please check the data.");
            }

            WebElement previousButton = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[4]/div[1]"));
            test.pass("Previous Button displayed!");
            WebElement nextButton = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[4]/div[3]"));
            test.pass("Next Button displayed!");
            WebElement paginationButton = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[4]/div[2]"));
            test.pass("Paginations Button displayed!");

            WebElement addNewVoucherButton = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[3]/div[2]"));
            test.pass("Add New Voucher button displayed!");

            WebElement logOutButton = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[5]/button"));
            test.pass("Log Out button displayed!");


        }catch (Exception e){
            test.fail(e);
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"chrome","edge","firefox"})
    @Order(8)
    public void checkClickPaginationButtonsInVoucherSection(String browser){
        try{
            test.info("Check pagination buttons in VOUCHER MANAGEMENT section.");
            login(browser);
            WebElement previousButton = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[4]/div[1]"));
            WebElement nextButton = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[4]/div[3]"));
            WebElement paginationButton = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[3]/div[2]"));

            nextButton.click();
            test.pass("Click Next Button successfully!");
            
            previousButton.click();
            test.pass("Click Previous Button successfully!");

            paginationButton.click();
            test.pass("Click Pagination Button successfully!");

        }catch (Exception e){
            test.fail(e);
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"chrome","edge","firefox"})
    @Order(9)
    public void checkAddNewVoucherButton(String browser){
        try{
            test.pass("Click Add New Voucher Button!");
            login(browser);
            WebElement addNewVoucherButton = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[3]/div[2]"));
            addNewVoucherButton.click();
            test.pass("Click Add new voucher section successfully!");
            WebElement addPopUp = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[5]/div"));
            if(addPopUp.isDisplayed()){
                test.pass("Add new Voucher pop up window displayed!");
            }else{
                test.fail("Add new Voucher pop up window didn't display!");
            }

        }catch (Exception e){
            test.fail(e);
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"chrome","edge"})
    @Order(10)
    public void checkAddNewBookPopUpComponent(String browser){
        try{
            test.info("Check Add new book Pop Up components");
            login(browser);
            WebElement addNewVoucherButton = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[3]/div[2]"));
            addNewVoucherButton.click();
            WebElement title = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[5]/div/h2"));
            if(title.isDisplayed()){
                test.pass("Title displayed: "+title.getText());
            }else{
                test.fail("Title didn't displayed: "+title.getText());
            }

            WebElement voucherName = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[5]/div/input[1]"));
            if(voucherName.isDisplayed()){
                test.pass("Enter Voucher Name box displayed!");
            }else{
                test.fail("Enter Voucher Name box didn't display!");
            }

            WebElement discription = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[5]/div/input[2]"));
            if(discription.isDisplayed()){
                test.pass("Enter Voucher Discription box displayed!");
            }else{
                test.fail("Enter Voucher Discription box didn't display!");
            }

            WebElement amount = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[5]/div/input[3]"));
            if(amount.isDisplayed()){
                test.pass("Enter Voucher Discount Amount box displayed!");
            }else{
                test.fail("Enter Discount Amount box didn't display!");
            }

            WebElement quantity = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[5]/div/input[4]"));
            if(quantity.isDisplayed()){
                test.pass("Enter Voucher Quantity box displayed!");
            }else{
                test.fail("Enter Voucher Quantity box didn't display!");
            }

            WebElement startday = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[5]/div/input[5]"));
            if(startday.isDisplayed()){
                test.pass("Enter Voucher Start Date box displayed!");
            }else{
                test.fail("Enter Voucher Start Date box didn't display!");
            }

            WebElement endDay = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[5]/div/input[6]"));
            if(endDay.isDisplayed()){
                test.pass("Enter Voucher End Date box displayed!");
            }else{
                test.fail("Enter Voucher End Date box didn't display!");
            }

            WebElement note = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[5]/div/textarea"));
            if(note.isDisplayed()){
                test.pass("Enter Voucher Note box displayed!");
            }else{
                test.fail("Enter Voucher Note box didn't display!");
            }

            WebElement createVoucher = driver.findElement(By.xpath("/html/body/div[1]/main/div/div[5]/div/div/button[1]"));
            if(createVoucher.isDisplayed()){
                test.pass("Create Voucher Button displayed!");
            }else{
                test.fail("Create Voucher didn't displayed!");
            }

            WebElement cancel = driver.findElement(By.xpath("/html/body/div[1]/main/div/div[5]/div/div/button[2]"));
            if(cancel.isDisplayed()){
                test.pass("Cancel create Voucher Button displayed!");
            }else{
                test.fail("Cancel create Voucher didn't displayed!");
            }

        }catch (Exception e){
            test.fail(e);
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"chrome","edge","firefox"})
    @Order(11)
    public void checkEnterValue(String browser){
        try{
            test.info("Check enter values in create new voucher pop up window");
            login(browser);
            WebElement addNewVoucherButton = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[3]/div[2]"));
            addNewVoucherButton.click();
            WebElement voucherName = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[5]/div/input[1]"));
            voucherName.sendKeys("ABC123");
            if(voucherName.getAttribute("value").equals("ABC123")){
                test.pass("Enter value in Enter Voucher Name box successfully!");
            }else{
                test.fail("Enter value in Enter Voucher Name box unsuccessfully!");
            }

            WebElement discription = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[5]/div/input[2]"));
            discription.sendKeys("this is a discription");
            if(discription.getAttribute("value").equals("this is a discription")){
                test.pass("Enter value in Enter Voucher Discription box successfully!");
            }else{
                test.fail("Enter value in Enter Voucher Discription box unsuccessfully!");
            }

            WebElement amount = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[5]/div/input[3]"));
            amount.sendKeys("20");
            if(amount.getAttribute("value").equals("20")){
                test.pass("Enter value in Enter Voucher Discount Amount box successfully!");
            }else{
                test.fail("Enter value in Enter Voucher Discount Amount box unsuccessfully!");
            }

            WebElement quantity = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[5]/div/input[4]"));
            quantity.sendKeys("100");
            if(quantity.getAttribute("value").equals("100")){
                test.pass("Enter value in Enter Voucher Quantity box successfully!");
            }else{
                test.fail("Enter value in Enter Voucher Quantity box unsuccessfully!");
            }

            WebElement startday = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[5]/div/input[5]"));
            startday.sendKeys("01042025");
            if(startday.getAttribute("value").equals("2025-01-04")){
                test.pass("Enter value in Enter Voucher Start Date box successfully!");
            }else{
                test.fail("Enter value in Enter Voucher Start Date box unsuccessfully!");
            }

            WebElement endDay = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[5]/div/input[6]"));
            endDay.clear();
            endDay.sendKeys("02042025");
            if(endDay.getAttribute("value").equals("2025-02-04")){
                test.pass("Enter value in Enter Voucher End Date box successfully!");
            }else{
                test.fail("Enter value in Enter Voucher End Date box unsuccessfully! ");
            }

            WebElement note = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[5]/div/textarea"));
            note.sendKeys("this is note");
            if(note.getAttribute("value").equals("this is note")){
                test.pass("Enter value in Enter Voucher Note box successfully!");
            }else{
                test.fail("Enter value in Enter Voucher Note box unsuccessfully!");
            }
            Thread.sleep(5000);


        }catch (Exception e){
            test.fail(e);
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"chrome","edge","firefox"})
    @Order(12)
    public void checkClickWithoutConpleteField(String browser){
        try{
            test.info("check add new voucher without fill all neccessary field.");
            login(browser);
            WebElement addNewVoucherButton = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[3]/div[2]"));
            addNewVoucherButton.click();
            WebElement createVoucher = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[5]/div/div/button[1]"));
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView(true);", createVoucher);
            Thread.sleep(500); 
            js.executeScript("arguments[0].click();", createVoucher);
            test.pass("Click create voucher button.");
            List<WebElement> nofi = driver.findElements(By.xpath("//*[@id=\"root\"]/main/div/div[5]/div/div"));
            if(nofi.size()>0){
                for (WebElement x: nofi){
                    test.pass("Nofication: "+x.getText());
                }
            }else{
                test.fail("System didn't raise error.");
            }

        }catch (Exception e){
            test.fail(e);
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"chrome","edge","firefox"})
    @Order(13)
    public void checkCancelButton(String browser){
        try{
            test.info("Check click cancel button to cancel add a new voucher.");
            login(browser);
            WebElement addNewVoucherButton = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[3]/div[2]"));
            addNewVoucherButton.click();
            WebElement cancelVoucher = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[5]/div/div/button[2]"));
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView(true);", cancelVoucher);
            Thread.sleep(500); 
            js.executeScript("arguments[0].click();", cancelVoucher);
            test.pass("Click cancel button");
            List<WebElement> pop_up = driver.findElements(By.xpath("//*[@id=\"root\"]/main/div/div[5]/div"));
            if(pop_up.size() == 0){
                test.pass("Close add new voucher pop up.");
            }else{
                test.fail("Add new voucher pop up still open");
            }

        }catch (Exception e){
            test.fail(e);
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"chrome","edge"})
    @Order(14)
    public void checkEnterWrongValue(String browser){
        try{
            test.info("Test enter invalid input.");
            login(browser);
            WebElement addNewVoucherButton = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[3]/div[2]"));
            addNewVoucherButton.click();

            WebElement amount = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[5]/div/input[3]"));
            amount.sendKeys("101");

            WebElement quantity = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[5]/div/input[4]"));
            quantity.sendKeys("-100");

            WebElement startday = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[5]/div/input[5]"));
            startday.sendKeys("02042025");

            WebElement endDay = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[5]/div/input[6]"));
            endDay.sendKeys("01042025");

            WebElement createVoucher = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[5]/div/div/button[1]"));
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView(true);", createVoucher);
            Thread.sleep(500); 
            js.executeScript("arguments[0].click();", createVoucher);

            List<WebElement> pop_up = driver.findElements(By.xpath("//*[@id=\"root\"]/main/div/div[5]/div"));
            if(pop_up.size() == 0){
                test.fail("Close add new voucher pop up.");
                return;
            }else{
                test.pass("Add new voucher pop up still open");
            }

            if(amount.getAttribute("value").equals("100")){
                test.pass("Greater than 100 number change to 100");
            }else{
                test.fail("Greater than 100 number still is Greater than 100 number");
            }

            if(quantity.getAttribute("value").equals("0")){
                test.pass("Negative number change to 0");
            }else{
                test.fail("Negative number still is negative number");
            }

            WebElement nofi = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[5]/div/div[4]"));
            if(nofi.isDisplayed()){
                test.pass("Nofication: "+nofi.getText());
            }else{
                test.fail("System didn't raise nofication.");
            }

        }catch (Exception e){
            test.fail(e);
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"chrome","edge"})
    @Order(15)
    public void checkAddNewVoucher(String browser){
        try{
            test.info("Check add new voucher");
            login(browser);
            WebElement addNewVoucherButton = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[3]/div[2]"));
            addNewVoucherButton.click();
            WebElement voucherName = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[5]/div/input[1]"));
            String name = "ABC123";
            voucherName.sendKeys(name);
            if(voucherName.getAttribute("value").equals(name)){
                test.pass("Enter value in Enter Voucher Name box successfully!");
            }else{
                test.fail("Enter value in Enter Voucher Name box unsuccessfully!");
            }

            WebElement discription = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[5]/div/input[2]"));
            discription.sendKeys("this is a discription");
            if(discription.getAttribute("value").equals("this is a discription")){
                test.pass("Enter value in Enter Voucher Discription box successfully!");
            }else{
                test.fail("Enter value in Enter Voucher Discription box unsuccessfully!");
            }

            WebElement amount = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[5]/div/input[3]"));
            amount.sendKeys("20");
            if(amount.getAttribute("value").equals("20")){
                test.pass("Enter value in Enter Voucher Discount Amount box successfully!");
            }else{
                test.fail("Enter value in Enter Voucher Discount Amount box unsuccessfully!");
            }

            WebElement quantity = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[5]/div/input[4]"));
            quantity.sendKeys("100");
            if(quantity.getAttribute("value").equals("100")){
                test.pass("Enter value in Enter Voucher Quantity box successfully!");
            }else{
                test.fail("Enter value in Enter Voucher Quantity box unsuccessfully!");
            }

            WebElement startday = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[5]/div/input[5]"));
            startday.click();
            startday.sendKeys("01042025");
            if(startday.getAttribute("value").equals("2025-01-04")){
                test.pass("Enter value in Enter Voucher Start Date box successfully!");
            }else{
                test.fail("Enter value in Enter Voucher Start Date box unsuccessfully!");
            }

            WebElement endDay = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[5]/div/input[6]"));
            endDay.click();
            endDay.sendKeys("02042025");
  
            if(endDay.getAttribute("value").equals("2025-02-04")){
                test.pass("Enter value in Enter Voucher End Date box successfully!");
            }else{
                test.fail("Enter value in Enter Voucher End Date box unsuccessfully!");
            }

            WebElement note = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[5]/div/textarea"));
            note.sendKeys("this is note");
            if(note.getAttribute("value").equals("this is note")){
                test.pass("Enter value in Enter Voucher Note box successfully!");
            }else{
                test.fail("Enter value in Enter Voucher Note box unsuccessfully!");
            }

            WebElement createVoucher = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[5]/div/div/button[1]"));
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView(true);", createVoucher);
            Thread.sleep(500); 
            js.executeScript("arguments[0].click();", createVoucher);
            test.pass("Click create voucher button.");
            Thread.sleep(3000);

            WebElement title = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[3]/div[2]/p[1]"));
            if(title.isDisplayed()){
                test.pass("Add new voucher successfully!");
            }else{
                test.fail("Add new voucher unsuccessfully!");
            }

            List<WebElement>titles = driver.findElements(By.xpath("//*[@id=\"root\"]/main/div/div[3]/div"));
            int num = titles.size();
            WebElement deleteButton = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[3]/div[2]/button"));
            deleteButton.click();
            Alert alert = driver.switchTo().alert();
            alert.accept();
            Thread.sleep(2000);
            titles = driver.findElements(By.xpath("//*[@id=\"root\"]/main/div/div[3]/div"));
            if(titles.size() - num == -1){
                test.pass("Delete a voucher successfully!");
            }else{
                test.fail("Delete a voucher unsuccessfully! ");
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
