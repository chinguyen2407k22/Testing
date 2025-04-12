package org.example.test.test_project.UI_UX_testing.Sprint4;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import io.restassured.internal.common.assertion.Assertion;

import java.security.Key;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.example.test.test_project.LogConfig.ExtendReport;
import org.example.test.test_project.WebBrowser.BrowserFactory;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ContactUs {
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
            test.info("Test click CONTACT US page link.");
            driver = BrowserFactory.getDriver(browser);
            driver.get("http://localhost:3000/");
            Thread.sleep(3000);
            WebElement continueBrowsingButton = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[1]/div/div/button[2]"));
            continueBrowsingButton.click();
            WebElement aboutUsLink = driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div/a[5]"));
            Assertions.assertTrue(aboutUsLink.isDisplayed());
            test.pass("CONTACT US page link displayed!");
            aboutUsLink.click();
            String currentUrl = driver.getCurrentUrl();
            String expectedUrl = "http://localhost:3000/contact-us";

            if (currentUrl.equals(expectedUrl)) {
                test.pass("Page changed to CONTACT US page successfully!");
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
            test.info("Test CONTACT US page component!");
            driver = BrowserFactory.getDriver(browser);
            driver.get("http://localhost:3000/contact-us");
            Thread.sleep(3000);

            WebElement title = driver.findElement(By.className("contact-title"));
            Assertions.assertTrue(title.isDisplayed());
            if (title.getText().equals("CONTACT US")){
                test.pass("Title displayed: "+title.getText());
            }else{
                test.fail("Title displayed: "+title.getText());
            }

            WebElement namebox = driver.findElement(By.name("name"));
            Assertions.assertTrue(namebox.isDisplayed());
            test.pass("Enter name box displayed!");

            WebElement emailbox = driver.findElement(By.name("email"));
            Assertions.assertTrue(emailbox.isDisplayed());
            test.pass("Enter email box displayed!");

            WebElement phonebox = driver.findElement(By.name("phone"));
            Assertions.assertTrue(phonebox.isDisplayed());
            test.pass("Enter phone number box displayed!");

            WebElement titlebox = driver.findElement(By.name("title"));
            Assertions.assertTrue(titlebox.isDisplayed());
            test.pass("Enter book title box displayed!");

            WebElement contentbox = driver.findElement(By.name("content"));
            Assertions.assertTrue(contentbox.isDisplayed());
            test.pass("Enter content box displayed!");

            WebElement photobox = driver.findElement(By.className("upload-box"));
            Assertions.assertTrue(photobox.isDisplayed());
            test.pass("Enter book image box displayed!");

            WebElement contactByEmailButton = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/form/div[6]/div[2]/div/label[1]/input"));
            Assertions.assertTrue(contactByEmailButton.isDisplayed());
            test.pass("Choose to contact by email button displayed!");

            WebElement contactByPhoneNum = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/form/div[6]/div[2]/div/label[2]/input"));
            Assertions.assertTrue(contactByPhoneNum.isDisplayed());
            test.pass("Choose to contact by phone number button displayed!");

            WebElement sendButton = driver.findElement(By.className("submit-btn"));
            Assertions.assertTrue(sendButton.isDisplayed());
            test.pass("SEND button displayed!");


        }catch (Exception e){
            test.fail(e);
        }
    }
    
    @ParameterizedTest
    @ValueSource(strings = {"chrome","edge","firefox"})
    public void checkInput(String browser){
        try{
            test.info("Check enter into input box!");
            driver = BrowserFactory.getDriver(browser);
            driver.get("http://localhost:3000/contact-us");
            Thread.sleep(3000);
            WebElement namebox = driver.findElement(By.name("name"));
            namebox.sendKeys("Vincente Nguyen");
            if (namebox.getAttribute("value").equals("Vincente Nguyen")){
                test.pass("Enter name box successfully! Enter: Vincente Nguyen, receive: "+namebox.getAttribute("value"));
            }else{
                test.fail("Enter name box unsuccessfully! Enter: Vincente Nguyen, receive: "+namebox.getAttribute("value"));
            }
            
            WebElement emailbox = driver.findElement(By.name("email"));
            emailbox.sendKeys("example@gmail.com");
            if (emailbox.getAttribute("value").equals("example@gmail.com")){
                test.pass("Enter email box successfully! Enter: example@gmail.com, receive: "+emailbox.getAttribute("value"));
            }else{
                test.fail("Enter email box unsuccessfully! Enter: example@gmail.com, receive: "+emailbox.getAttribute("value"));
            }

            WebElement phonebox = driver.findElement(By.name("phone"));
            phonebox.sendKeys("0123456789");
            if (phonebox.getAttribute("value").equals("0123456789")){
                test.pass("Enter phone number box successfully! Enter: 0123456789, receive: "+phonebox.getAttribute("value"));
            }else{
                test.fail("Enter phone number box unsuccessfully! Enter: 0123456789, receive: "+phonebox.getAttribute("value"));
            }

            WebElement titlebox = driver.findElement(By.name("title"));
            titlebox.sendKeys("Title");
            if(titlebox.getAttribute("value").equals("Title")){
                test.pass("Enter title box successfully! Enter: Title, receive: "+titlebox.getAttribute("value"));
            }else{
                test.fail("Enter title box successfully! Enter: Title, receive: "+titlebox.getAttribute("value"));
            }

            WebElement contentbox = driver.findElement(By.name("content"));
            contentbox.sendKeys("Content");
            if(contentbox.getAttribute("value").equals("Content")){
                test.pass("Enter content box successfully! Enter: Content, receive: "+contentbox.getAttribute("value"));
            }else{
                test.fail("Enter content box successfully! Enter: Content, receive: "+contentbox.getAttribute("value"));
            }

        }catch (Exception e){
            test.fail(e);
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"chrome","edge","firefox"})
    public void checkSendButton(String browser){
        try{
            test.info("Check send button disable if user didn't fill all field of form!");
            driver = BrowserFactory.getDriver(browser);
            driver.get("http://localhost:3000/contact-us");
            Thread.sleep(3000);
            WebElement namebox = driver.findElement(By.name("name"));
            namebox.sendKeys("Vincente Nguyen");

            WebElement titlebox = driver.findElement(By.name("title"));
            titlebox.sendKeys("Title");

            WebElement sendButton = driver.findElement(By.className("submit-btn"));
            if(sendButton.isEnabled()){
                test.fail("Button enable!");
            }else{
                test.pass("Button disabled!");
            }
        }catch (Exception e){
            test.fail(e);
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"chrome","edge","firefox"})
    public void checkNofication(String browser){
        try{
            test.info("Check the message that appears when clicking on the input boxes!");
            driver = BrowserFactory.getDriver(browser);
            driver.get("http://localhost:3000/contact-us");
            Thread.sleep(3000);

            WebElement namebox = driver.findElement(By.name("name"));
            namebox.click();
            test.pass("Ckick enter name box!");

            WebElement emailbox = driver.findElement(By.name("email"));
            emailbox.click();
            test.pass("Click enter email box!");

            WebElement errormessage = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/form/div[1]/div"));
            Assertions.assertTrue(errormessage.isDisplayed());
            test.pass("Message appeared: "+errormessage.getText());

            WebElement phonebox = driver.findElement(By.name("phone"));
            phonebox.click();
            test.pass("Click enter phone number box!");
            
            errormessage = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/form/div[2]/div"));
            Assertions.assertTrue(errormessage.isDisplayed());
            test.pass("Message appeared: "+errormessage.getText());

            WebElement titlebox = driver.findElement(By.name("title"));
            titlebox.click();
            test.pass("Click enter title box!");

            errormessage = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/form/div[3]/div"));
            Assertions.assertTrue(errormessage.isDisplayed());
            test.pass("Message appeared: "+errormessage.getText());

            WebElement contentbox = driver.findElement(By.name("content"));
            contentbox.click();
            test.pass("Click enter content box!");

            errormessage = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/form/div[4]/div"));
            Assertions.assertTrue(errormessage.isDisplayed());
            test.pass("Message appeared: "+errormessage.getText());

            namebox.click();
            test.pass("Ckick enter name box!");
            
            errormessage = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/form/div[2]/div"));
            Assertions.assertTrue(errormessage.isDisplayed());
            test.pass("Message appeared: "+errormessage.getText());

        }catch (Exception e){
            test.fail(e);
        }
    }
    
    @ParameterizedTest
    @ValueSource(strings = {"chrome"})
    public void checkInput100times(String browser){
        try{
            test.info("Check the message that appears when clicking on the input boxes!");
            driver = BrowserFactory.getDriver(browser);
            driver.get("http://localhost:3000/contact-us");
            Thread.sleep(3000);

            WebElement namebox = driver.findElement(By.name("name"));

            WebElement emailbox = driver.findElement(By.name("email"));

            WebElement phonebox = driver.findElement(By.name("phone"));

            WebElement titlebox = driver.findElement(By.name("title"));

            WebElement contentbox = driver.findElement(By.name("content"));

            for(int i=0;i<100;i++){
                namebox.sendKeys("NguyenVanA");
                emailbox.sendKeys("example@gmail.com");
                phonebox.sendKeys("0123456789");
                titlebox.sendKeys("title");
                contentbox.sendKeys("content");
            }
            test.pass("Enter boxes 100 times successfully!");

        }catch (Exception e){
            test.fail(e);
        }
    }
    
    @ParameterizedTest
    @ValueSource(strings = {"chrome","firefox","edge"})
    public void checkInvalidInput(String browser){
        try{
            test.info("Check enter invalid input.");
            driver = BrowserFactory.getDriver(browser);
            driver.get("http://localhost:3000/contact-us");

            WebElement namebox = driver.findElement(By.name("name"));
            namebox.click();
            namebox.sendKeys("0123");
            test.pass("Enter invalid name: "+namebox.getAttribute("value"));

            WebElement emailbox = driver.findElement(By.name("email"));
            emailbox.click();
            

            WebElement errormessage = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/form/div[1]/div"));
            Assertions.assertTrue(errormessage.isDisplayed());
            test.pass("Message appeared: "+errormessage.getText());

            emailbox = driver.findElement(By.name("email"));
            emailbox.sendKeys("example1");
            test.pass("Enter invalid email: "+emailbox.getAttribute("value"));

            WebElement phonebox = driver.findElement(By.name("phone"));
            phonebox.click();

            errormessage = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/form/div[2]/div"));
            Assertions.assertTrue(errormessage.isDisplayed());
            test.pass("Message appeared: "+errormessage.getText());

            phonebox = driver.findElement(By.name("phone"));
            phonebox.sendKeys("0123456789123456789");
            test.pass("enter phone number more than 15 characters: "+phonebox.getAttribute("value"));

            namebox.click();
            phonebox.click();

            errormessage = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/form/div[3]/div"));
            Assertions.assertTrue(errormessage.isDisplayed());
            test.pass("Message appeared: "+errormessage.getText());

            phonebox.clear();

            phonebox.sendKeys("+843289052aa");
            test.pass("Enter invalid phone number: "+phonebox.getAttribute("value"));
            errormessage = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/form/div[3]/div"));
            Assertions.assertTrue(errormessage.isDisplayed());
            test.pass("Message appeared: "+errormessage.getText());
            

        }catch (Exception e){
            test.fail(e);
        }
    }
    
    @ParameterizedTest
    @ValueSource(strings = {"chrome","edge","firefox"})
    public void checkInsertPhoto(String browser){
        try{
            test.info("Check choose contact method and upload image");
            driver = BrowserFactory.getDriver(browser);
            driver.get("http://localhost:3000/contact-us");

            WebElement contactByPhoneNum = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/form/div[6]/div[2]/div/label[2]/input"));
            contactByPhoneNum.click();
            test.pass("Click contact by phone number button successfully!");

            WebElement contactByEmailButton = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/form/div[6]/div[2]/div/label[1]/input"));
            contactByEmailButton.click();
            test.pass("Click contact by email button successfully!");

            WebElement photobox = driver.findElement(By.className("upload-box"));
            photobox.click();
            Thread.sleep(5000);
            test.pass("Open os box.");

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
