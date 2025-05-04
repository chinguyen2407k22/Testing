package org.example.test.test_project.UI_UX_testing.Sprint5;

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
public class OrderManagement {
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
            WebElement orderlink = driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div/a[3]"));
            orderlink.click();
            Thread.sleep(5000);

        }catch (Exception e){
            test.fail(e);
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"chrome","edge","firefox"})
    @Order(1)
    public void testGoToOrderManagementPage(String browser){
        try{
            test.info("check go to ORDER MANAGEMENT page link.");
            login(browser);
            String currentUrl = driver.getCurrentUrl();
            String expectedUrl = "http://localhost:3000/order-management";

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
    @Order(2)
    @ValueSource(strings = {"chrome","edge","firefox"})
    public void checkTitle(String browser){
        try{
            test.info("Check title");
            login(browser);
            Thread.sleep(1000);
            WebElement title = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/h1"));
            test.pass("ORDER MANAGEMENT page's title displayed!");
            if (title.getText().equals("ORDER MANAGEMENT")){
                test.pass("Correct title: "+title.getText());
            }else{
                test.fail("Incorrect Title: "+title.getText());
            }

        }catch (Exception e){
            test.fail(e);
        }
    }


    @ParameterizedTest
    @Order(3)
    @ValueSource(strings = {"chrome","edge","firefox"})
    public void checkPendingOrderSectionComponent(String browser){
        try{
            test.info("check pending order section components.");
            login(browser);

            WebElement pendingsection = driver.findElement(By.cssSelector(".section-title.pending"));
            Assertions.assertTrue(pendingsection.isDisplayed());
            test.pass("Pending Order section displayed! Title: "+pendingsection.getText());

            List<WebElement> pendingorders = driver.findElements(By.cssSelector(".order-card.pending"));
            if(pendingorders.size() > 0){
                test.pass("Number of Pending Orders: "+pendingorders.size());
            }else{
                test.fail("Number of Pending Orders: "+pendingorders.size());
            }

            List<WebElement> pendingOrderId = driver.findElements(By.cssSelector(".order-header.pending"));
            if (pendingOrderId.size() == pendingorders.size()){
                test.pass("The number of detailed information entries matched the number of orders.");
            }else{
                test.fail("Number of View details button: "+pendingOrderId.size());
            }

            List<WebElement> confirmButtons = driver.findElements(By.className("btn-confirm"));
            if(confirmButtons.size() == pendingorders.size()){
                test.pass("The number of Confirm button matched the number of orders.");
            }else{
                test.fail("Number of Confirm Button: "+confirmButtons.size());
            }

            List<WebElement> cancelButtons = driver.findElements(By.cssSelector(".order-header.pending"));
            if(cancelButtons.size() == pendingorders.size()){
                test.pass("The number of Cancel button matched the number of orders.");
            }else{
                test.fail("Number of Cancel Button: "+cancelButtons.size());
            }

            
        }catch (Exception e){
            test.fail(e);
        }
    }

    @ParameterizedTest
    @Order(4)
    @ValueSource(strings = {"chrome","edge","firefox"})
    public void checkPickUpOrderSectionComponent(String browser){
        try{
            test.info("check wait to picked up order section components.");
            login(browser);

            WebElement pickupsection = driver.findElement(By.cssSelector(".section-title.pickup"));
            Assertions.assertTrue(pickupsection.isDisplayed());
            test.pass("Waiting to be picked up order section displayed! Title: "+pickupsection.getText());

            List<WebElement> pickuporders = driver.findElements(By.cssSelector(".order-card.pickup"));
            if(pickuporders.size() > 0){
                test.pass("Number of waiting to be picked up orders: "+pickuporders.size());
            }else{
                test.fail("Number of waiting to be picked up Orders: "+pickuporders.size());
            }

            List<WebElement> pickupOrderId = driver.findElements(By.cssSelector(".order-header.pickup"));
            if (pickupOrderId.size() == pickuporders.size()){
                test.pass("The number of detailed information entries matched the number of orders.");
            }else{
                test.fail("Number of View details button: "+pickupOrderId.size());
            }

            List<WebElement> viewOrderDetails = driver.findElements(By.cssSelector(".view-details.pickup"));
            if(viewOrderDetails.size() == pickuporders.size()){
                test.pass("The number of view details buttons matched the number of orders.");
            }else{
                test.fail("Number of View Details buttons: "+viewOrderDetails.size());
            }

            List<WebElement> deliveryButtons = driver.findElements(By.className("btn-delivery"));
            if(deliveryButtons.size() == pickuporders.size()){
                test.pass("The number of delivery buttons matched the number of orders.");
            }else{
                test.fail("Number of delivery buttons: "+deliveryButtons.size());
            }

        }catch (Exception e){
            test.fail(e);
        }
    }

    @ParameterizedTest
    @Order(5)
    @ValueSource(strings = {"chrome","edge","firefox"})
    public void checkDeliveringOrderSectionComponent(String browser){
        try{
            test.info("check delivering order section components.");
            login(browser);

            WebElement deliveringsection = driver.findElement(By.cssSelector(".section-title.delivering"));
            Assertions.assertTrue(deliveringsection.isDisplayed());
            test.pass("Delivering Order section displayed! Title: "+deliveringsection.getText());

            List<WebElement> deliveringorders = driver.findElements(By.cssSelector(".order-card.delivering"));
            if(deliveringorders.size() > 0){
                test.pass("Number of Delivering Orders: "+deliveringorders.size());
            }else{
                test.fail("Number of Delivering Orders: "+deliveringorders.size());
            }

            List<WebElement> deliveringOrderId = driver.findElements(By.cssSelector(".order-header.delivering"));
            if (deliveringOrderId.size() == deliveringorders.size()){
                test.pass("The number of detailed information entries matched the number of orders.");
            }else{
                test.fail("Number of View details buttons: "+deliveringOrderId.size());
            }
            
            List<WebElement> compeleteButtons = driver.findElements(By.className("btn-complete"));
            if(compeleteButtons.size() == deliveringorders.size()){
                test.pass("The number of complete buttons matched the number of orders.");
            }else{
                test.fail("Number of Complete buttons: "+compeleteButtons.size());
            }

        }catch (Exception e){
            test.fail(e);
        }
    }

    @ParameterizedTest
    @Order(6)
    @ValueSource(strings = {"chrome","edge","firefox"})
    public void checkViewRatingOrderSectionComponent(String browser){
        try{
            test.info("check view rating section components.");
            login(browser);

            WebElement ratingsection = driver.findElement(By.cssSelector(".section-title.rating"));
            Assertions.assertTrue(ratingsection.isDisplayed());
            test.pass("Rating Order section displayed! Title: "+ratingsection.getText());

            List<WebElement> ratingorders = driver.findElements(By.cssSelector(".order-card.rating"));
            if(ratingorders.size() > 0){
                test.pass("Number of Rating Orders: "+ratingorders.size());
            }else{
                test.fail("Number of Rating Orders: "+ratingorders.size());
            }

            List<WebElement> ratingOrderId = driver.findElements(By.cssSelector(".order-header.rating"));
            if (ratingOrderId.size() == ratingorders.size()){
                test.pass("The number of detailed information entries matched the number of orders.");
            }else{
                test.fail("Number of View details button: "+ratingOrderId.size());
            }

            

        }catch (Exception e){
            test.fail(e);
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"chrome","edge","firefox"})
    @Order(6)
    public void checkPaginationButtons(String browser){
        try{
            test.info("Check PaginationButtons in all sections");
            login(browser);
            List<WebElement> arrowButtons = driver.findElements(By.cssSelector(".arrow-button.pending"));
            test.pass("Arrow buttons displayed in pending order section");
            List<WebElement> paginationButtons = driver.findElements(By.cssSelector(".pagination-button.pending"));
            test.pass("Pagination buttons displayed in pending order section");

            arrowButtons = driver.findElements(By.cssSelector(".arrow-button.pickup"));
            test.pass("Arrow buttons displayed in wait to be picked up order section");
            paginationButtons = driver.findElements(By.cssSelector(".pagination-button.pickup"));
            test.pass("Pagination buttons displayed in wait to be picked up order section");

            arrowButtons = driver.findElements(By.cssSelector(".arrow-button.delivering"));
            test.pass("Arrow buttons displayed in delivering order section");
            paginationButtons = driver.findElements(By.cssSelector(".pagination-button.delivering"));
            test.pass("Pagination buttons displayed in delivering order section");

            arrowButtons = driver.findElements(By.cssSelector(".arrow-button.rating"));
            test.pass("Arrow buttons displayed in view rating order section");
            paginationButtons = driver.findElements(By.cssSelector(".pagination-button.rating"));
            test.pass("Pagination buttons displayed in view rating order section");


        }catch (Exception e){
            test.fail(e);
        }

    }

    @ParameterizedTest
    @ValueSource(strings = {"chrome","edge","firefox"})
    @Order(7)
    public void checkViewDetailButtons(String browser){
        try {
            test.info("Check view Details Buttons");
            login(browser);

            List<WebElement> pendingorders = driver.findElements(By.cssSelector(".order-card.pending"));
            List<WebElement> viewOrderDetails = driver.findElements(By.cssSelector(".view-details.pending"));
            if(viewOrderDetails.size() == pendingorders.size()){
                test.pass("The number of view details button matched the number of orders.");
            }else{
                test.fail("Number of View Details button: "+viewOrderDetails.size());
            }

            List<WebElement> pickuporders = driver.findElements(By.cssSelector(".order-card.pickup"));
            viewOrderDetails = driver.findElements(By.cssSelector(".view-details.pickup"));
            if(viewOrderDetails.size() == pickuporders.size()){
                test.pass("The number of view details buttons matched the number of orders.");
            }else{
                test.fail("Number of View Details buttons: "+viewOrderDetails.size());
            }

            List<WebElement> deliveringorders = driver.findElements(By.cssSelector(".order-card.delivering"));
            viewOrderDetails = driver.findElements(By.cssSelector(".view-details.delivering"));
            if(viewOrderDetails.size() == deliveringorders.size()){
                test.pass("The number of view details buttons matched the number of orders.");
            }else{
                test.fail("Number of View Details buttons: "+viewOrderDetails.size());
            }

            List<WebElement> ratingorders = driver.findElements(By.cssSelector(".order-card.rating"));
            viewOrderDetails = driver.findElements(By.cssSelector(".view-details.rating"));
            if(viewOrderDetails.size() == ratingorders.size()){
                test.pass("The number of view details button matched the number of orders.");
            }else{
                test.fail("Number of View Details button: "+viewOrderDetails.size());
            }

        } catch (Exception e){
            test.fail(e);
        }
    }

    @ParameterizedTest
    @Order(8)
    @ValueSource(strings = {"Chrome","edge","firefox"})
    public void ClickViewDetailButton(String browser){
        try{
            test.info("Check click view details button in order of all sections");
            login(browser);
        
            List<WebElement> viewOrderDetails = driver.findElements(By.cssSelector(".view-details.pending"));
            viewOrderDetails.get(0).click();
            test.pass("Click Order Details button in pending section successfully! ");
            String currentUrl = driver.getCurrentUrl();
            String expectedUrl = "http://localhost:3000/order-details";

            if (currentUrl.contains(expectedUrl)) {
                test.pass("Page changed to ORDER DETAILS page successfully!");
            } else {
                test.fail("Page unchanged!");
            }

            driver.get("http://localhost:3000/order-management");   
            Thread.sleep(2000);
            viewOrderDetails = driver.findElements(By.cssSelector(".view-details.pickup"));
            viewOrderDetails.get(0).click();
            test.pass("Click Order Details button in pick up section successfully! ");
            if (currentUrl.contains(expectedUrl)) {
                test.pass("Page changed to ORDER DETAILS page successfully!");
            } else {
            test.fail("Page unchanged!");
            }

            driver.get("http://localhost:3000/order-management"); 
            Thread.sleep(2000);
            viewOrderDetails = driver.findElements(By.cssSelector(".view-details.delivering"));
            viewOrderDetails.get(0).click();
            test.pass("Click Order Details button in delivering section successfully! ");
            if (currentUrl.contains(expectedUrl)) {
                test.pass("Page changed to ORDER DETAILS page successfully!");
            } else {
                test.fail("Page unchanged!");
            }

            driver.get("http://localhost:3000/order-management"); 
            Thread.sleep(2000);
            viewOrderDetails = driver.findElements(By.cssSelector(".view-details.rating"));
            test.pass("Click Order Details button in rating section successfully! ");
            viewOrderDetails.get(0).click();
            if (currentUrl.contains(expectedUrl)) {
                test.pass("Page changed to ORDER DETAILS page successfully!");
            } else {
                test.fail("Page unchanged!");
            }

        }catch (Exception e){
            test.fail(e);
        }
        
    }
    
    @ParameterizedTest
    @ValueSource(strings = {"chrome","edge","firefox"})
    @Order(9)
    public void testHiddenButton(String browser){
        try{
            test.pass("Check + - button to hidden or display orders in each section");
            login(browser);
            WebElement pendingsection = driver.findElement(By.cssSelector(".section-title.pending"));
            pendingsection.click();
            List<WebElement> pendingorders = driver.findElements(By.cssSelector(".order-card.pending"));
            if(pendingorders.size() == 0){
                test.pass("Hidden orders in pending section successfully!");
            }else{
                test.fail("Hidden orders in pending section unsuccessfully!");
            }
            pendingsection.click();
            pendingorders = driver.findElements(By.cssSelector(".order-card.pending"));
            if(pendingorders.size() > 0){
                test.pass("Display orders in pending section successfully!");
            }else{
                test.fail("Display orders in pending section unsuccessfully!");
            }

            WebElement pickupsection = driver.findElement(By.cssSelector(".section-title.pickup"));
            pickupsection.click();
            List<WebElement> pickuporders = driver.findElements(By.cssSelector(".order-card.pickup"));
            if(pickuporders.size()==0){
                test.pass("Hidden orders in waiting to be picked up section successfully!");
            }else{
                test.fail("Hidden orders in waiting to be picked up section unsuccessfully!");
            }
            pickupsection.click();
            pickuporders = driver.findElements(By.cssSelector(".order-card.pickup"));
            if(pickuporders.size()>0){
                test.pass("Display orders in waiting to be picked up section successfully!");
            }else{
                test.fail("Display orders in waiting to be picked up section unsuccessfully!");
            }

            WebElement deliveringsection = driver.findElement(By.cssSelector(".section-title.delivering"));
            deliveringsection.click();
            List<WebElement> deliveringorders = driver.findElements(By.cssSelector(".order-card.delivering"));
            if(deliveringorders.size()==0){
                test.pass("Hidden orders in delivery section successfully!");
            }else{
                test.fail("Hidden orders in delivery section unsuccessfully!");
            }
            deliveringsection.click();
            deliveringorders = driver.findElements(By.cssSelector(".order-card.delivering"));
            if(deliveringorders.size()>0){
                test.pass("Display orders in delivery section successfully!");
            }else{
                test.fail("Display orders in delivery section unsuccessfully!");
            }
            
            WebElement viewRatingsection = driver.findElement(By.cssSelector(".section-title.rating"));
            viewRatingsection.click();
            List<WebElement> ratingorders = driver.findElements(By.cssSelector(".order-card.rating"));
            if(ratingorders.size()==0){
                test.pass("Hidden orders in view rating section successfully!");
            }else{
                test.fail("Hidden orders in view rating section unsuccessfully!");
            }
            viewRatingsection.click();
            ratingorders = driver.findElements(By.cssSelector(".order-card.rating"));
            if(ratingorders.size()>0){
                test.pass("Display orders in view rating section successfully!");
            }else{
                test.fail("Display orders in view rating section unsuccessfully!");
            }
            
        }catch (Exception e){
            test.fail(e);
        }
    }
    @ParameterizedTest
    @ValueSource(strings = {"chrome","edge","firefox"})
    @Order(10)
    public void checkPaginationButton(String browser){
        try{
            test.info("Check click pagination button");
            login(browser);
            List<WebElement> arrowButtons = driver.findElements(By.cssSelector(".arrow-button.pending"));
            arrowButtons.get(0).click();
            test.pass("Click previous button in pending section.");
            arrowButtons.get(1).click();
            test.pass("Click next button in pending section.");
            List<WebElement> paginationButtons = driver.findElements(By.cssSelector(".pagination-button.pending"));
            paginationButtons.get(0);
            test.pass("Click a pagination button in pending section.");
            Thread.sleep(3000);

            arrowButtons = driver.findElements(By.cssSelector(".arrow-button.pickup"));
            arrowButtons.get(0).click();
            test.pass("Click previous button in waiting to be picked up section.");
            arrowButtons.get(1).click();
            test.pass("Click next button in waiting to be picked up section.");
            paginationButtons = driver.findElements(By.cssSelector(".pagination-button.pickup"));
            paginationButtons.get(0);
            test.pass("Click a pagination button in waiting to be picked up section.");

            arrowButtons = driver.findElements(By.cssSelector(".arrow-button.delivering"));
            arrowButtons.get(0).click();
            test.pass("Click previous button in delivering section.");
            arrowButtons.get(1).click();
            test.pass("Click next button in delivering section.");
            paginationButtons = driver.findElements(By.cssSelector(".pagination-button.delivering"));
            paginationButtons.get(0);
            test.pass("Click a pagination button in delivering section.");

            arrowButtons = driver.findElements(By.cssSelector(".arrow-button.rating"));
            arrowButtons.get(0).click();
            test.pass("Click previous button in view rating section.");
            arrowButtons.get(1).click();
            test.pass("Click next button in view rating section.");
            paginationButtons = driver.findElements(By.cssSelector(".pagination-button.rating"));
            paginationButtons.get(0);
            test.pass("Click a pagination button in view rating section.");


        }catch (Exception e){
            test.fail(e);
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"chrome","edge","firefox"})
    @Order(11)
    public void checkConfirmButton(String browser){
        try{
            test.info("Check confirm button");
            login(browser);
            List<WebElement> pendingorders = driver.findElements(By.cssSelector(".order-card.pending"));
            List<WebElement> pickuporders = driver.findElements(By.cssSelector(".order-card.pickup"));
            int pendingOldSize = pendingorders.size();
            int pickupOldSize = pickuporders.size();

            List<WebElement> confirmButtons = driver.findElements(By.className("btn-confirm"));
            confirmButtons.get(0).click();
            test.pass("Click confirm button successfully!");
            driver.get("http://localhost:3000/order-management");
            Thread.sleep(3000);

            pendingorders = driver.findElements(By.cssSelector(".order-card.pending"));
            pickuporders = driver.findElements(By.cssSelector(".order-card.pickup"));
            int pendingNewSize = pendingorders.size();
            int pickupNewSize = pickuporders.size();

            if(pendingNewSize == pendingOldSize -1 && pickupNewSize == pickupOldSize +1){
                test.pass("Change order status from pending to wating to be picked up successfully!");
            }else{
                test.fail("Order status unchanged");
            }
        }catch (Exception e){
            test.fail(e);
        }

    }

    @ParameterizedTest
    @Order(12)
    @ValueSource(strings = {"chrome","edge","firefox"})
    public void checkCancelButton(String browser){
        try{
            test.info("Check cancel button");
            login(browser);
            List<WebElement> pendingorders = driver.findElements(By.cssSelector(".order-card.pending"));
            int pendingOldSize = pendingorders.size();
            
            List<WebElement> cancelButtons = driver.findElements(By.className("btn-cancel"));
            cancelButtons.get(0).click();
            test.pass("Click cancel button successfully!");
            driver.get("http://localhost:3000/order-management");
            Thread.sleep(3000);

            pendingorders = driver.findElements(By.cssSelector(".order-card.pending"));
            int pendingNewSize = pendingorders.size();

            if(pendingNewSize == pendingOldSize -1 ){
                test.pass("Delete an order from pending section");
            }else{
                test.fail("Orders in pending section unchanged");
            }
        }catch (Exception e){
            test.fail(e);
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"chrome","edge","firefox"})
    @Order(13)
    public void checkDeliveryButton(String browser){
        try{
            test.pass("Check deliver buttons");
            login(browser);
            List<WebElement> pickuporders = driver.findElements(By.cssSelector(".order-card.pickup"));
            List<WebElement> deliveringorders = driver.findElements(By.cssSelector(".order-card.delivering"));
            int pickupOldSize = pickuporders.size();
            int deliveringOldSize = deliveringorders.size();

            List<WebElement> deliveryButtons = driver.findElements(By.className("btn-delivery"));
            deliveryButtons.get(0).click();
            driver.get("http://localhost:3000/order-management");
            test.pass("Click deliver button successfully!");
            Thread.sleep(3000);

            pickuporders = driver.findElements(By.cssSelector(".order-card.pickup"));
            deliveringorders = driver.findElements(By.cssSelector(".order-card.delivering"));
            
            int pickupNewSize = pickuporders.size();
            int deliveringNewSize = deliveringorders.size();

            if(pickupNewSize == pickupOldSize -1 && deliveringNewSize == deliveringOldSize +1){
                test.pass("Change order status from wating to be picked up to delivering successfully!");
            }else{
                test.fail("Order status unchanged");
                test.fail(""+pickupNewSize);
                test.fail(""+deliveringNewSize);
                test.fail(""+pickupOldSize);
                test.fail(""+deliveringOldSize);
            }

        }catch (Exception e){
            test.fail(e);
        }

    }

    @ParameterizedTest
    @ValueSource(strings = {"chrome","edge","firefox"})
    @Order(14)
    public void checkCompleteButton(String browser){
        try{
            test.pass("Check complete buttons");
            login(browser);
            
            List<WebElement> deliveringorders = driver.findElements(By.cssSelector(".order-card.delivering"));
            List<WebElement> ratingorders = driver.findElements(By.cssSelector(".order-card.rating"));
            int deliveringOldSize = deliveringorders.size();
            int ratingOldSize = ratingorders.size();

            List<WebElement> compeleteButtons = driver.findElements(By.className("btn-complete"));
            compeleteButtons.get(0).click();
            test.pass("Click complete button successfully!");
            driver.get("http://localhost:3000/order-management");
            Thread.sleep(3000);

            deliveringorders = driver.findElements(By.cssSelector(".order-card.delivering"));
            ratingorders = driver.findElements(By.cssSelector(".order-card.rating"));
            int deliveringNewSize = deliveringorders.size();
            int ratingNewSize = ratingorders.size();

            if(ratingNewSize == ratingOldSize + 1 && deliveringNewSize == deliveringOldSize - 1){
                test.pass("Change order status from delivering to view rating successfully!");
            }else{
                test.fail("Order status unchanged");
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
