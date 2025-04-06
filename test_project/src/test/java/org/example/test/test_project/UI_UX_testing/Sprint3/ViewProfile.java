package org.example.test.test_project.UI_UX_testing.Sprint3;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.example.test.test_project.LogConfig.ExtendReport;
import org.example.test.test_project.WebBrowser.BrowserFactory;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ViewProfile {
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
        try {
            driver = BrowserFactory.getDriver(browser);
            driver.get("http://localhost:3000/");
            Thread.sleep(3000);

            WebElement loginButton = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[1]/div/div/button[1]"));
            Assertions.assertTrue(loginButton.isDisplayed());

            loginButton.click();

            String currentUrl = driver.getCurrentUrl();
            String expectedUrl = "http://localhost:3000/login";

            if (currentUrl.equals(expectedUrl)) {
                test.pass("Change to login page successfully!");
            } else {
                test.fail("Page unchanged!");
            }
            Thread.sleep(1000);

            WebElement usernameField = driver.findElement(By.name("username"));
            usernameField.sendKeys("username2");
            WebElement passwordField = driver.findElement(By.name("password"));
            passwordField.sendKeys("abcxyz123");
            WebElement signInButton = driver.findElement(By.xpath("//button[span[text()='Sign In']]"));

            signInButton.click();
            test.pass("Log in successfully!");

            Thread.sleep(3000);

            WebElement icon1 = driver.findElement(By.xpath("//*[@id=\"root\"]/header/div[2]/div/div"));
            Assertions.assertTrue(icon1.isDisplayed(),"Login icon did not display");
            test.pass("Login icon displayed!");

            icon1.click();

            Thread.sleep(1000);

            WebElement link = driver.findElement(By.xpath("//a[contains(text(), 'Profile')]"));

            link.click();
            currentUrl = driver.getCurrentUrl();
            expectedUrl = "http://localhost:3000/profile";

            if (currentUrl.equals(expectedUrl)) {
                test.pass("Page changed to profile page successfully!");
            } else {
                test.fail("Page unchanged!");
            }


        }catch (Exception e){
            test.fail(e);
        }
    }

    @ParameterizedTest
    @ValueSource(strings ={"chrome","firefox","edge"})
    @Order(1)
    public void checkComponent(String browser){
        try {
            test.info("check profile page component!");
            login(browser);
            Thread.sleep(2000);

            WebElement title = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[1]/div[1]"));
            Assertions.assertTrue(title.isDisplayed());
            test.pass("Title displayed! Title: "+title.getText());

            WebElement avatar = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[1]/div[2]"));
            Assertions.assertTrue(avatar.isDisplayed());
            test.pass("Avatar displayed!");

            WebElement name = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[1]/p[1]"));
            Assertions.assertTrue(name.isDisplayed());
            test.pass("User's fullname displayed!");

            WebElement member = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[1]/p[2]"));
            Assertions.assertTrue(member.isDisplayed());
            test.pass("Member Level displayed!");

            WebElement editProfileButton = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[1]/button[1]"));
            Assertions.assertTrue(editProfileButton.isDisplayed());
            test.pass("Edit Profile Button displayed!");

            WebElement changePasswordButton = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[1]/button[2]"));
            Assertions.assertTrue(changePasswordButton.isDisplayed());
            test.pass("Change password button is displayed!");

            WebElement fullnameBox = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[3]/div/div[1]"));
            Assertions.assertTrue(fullnameBox.isDisplayed());
            test.pass("Fullname Box displayed!");

            WebElement birthdayBox = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[3]/div/div[2]"));
            Assertions.assertTrue(birthdayBox.isDisplayed());
            test.pass("Birthday Box displayed!");

            WebElement emailBox = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[3]/div/div[3]"));
            Assertions.assertTrue(emailBox.isDisplayed());
            test.pass("Email Box displayed!");

            WebElement addressBox = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[3]/div/div[4]"));
            Assertions.assertTrue(addressBox.isDisplayed());
            test.pass("Address Box displayed!");

            WebElement genderBox = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[3]/div/div[5]"));
            Assertions.assertTrue(genderBox.isDisplayed());
            test.pass("Gender Box displayed!");

            WebElement phoneNumBox = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[3]/div/div[6]"));
            Assertions.assertTrue(phoneNumBox.isDisplayed());
            test.pass("Phone Number Box displayed!");

        }catch (Exception e){
            test.fail(e.getMessage());
        }
    }
    @ParameterizedTest
    @Order(2)
    @ValueSource(strings = {"chrome","edge","firefox"})
    public void checkClickEditProfileButton(String browser){
        try {
            login(browser);
            Thread.sleep(2000);
            test.info("Check click edit profile button!");
            WebElement editProfileButton = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[1]/button[1]"));
            Assertions.assertTrue(editProfileButton.isDisplayed());
            test.pass("Edit Profile Button displayed!");

            editProfileButton.click();
            test.pass("Click edit profile button successfully!");

            if (editProfileButton.getText().equals("Save")){
                test.pass("Click successfully! Edit Profile button changed to Save Button");
            }else {
                test.fail("Click unsuccessfully! Edit Profile button unchanged to Save Button");
            }

            WebElement fullnameBox = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[3]/div/input[1]"));
            Assertions.assertTrue(fullnameBox.isDisplayed());
            fullnameBox.clear();
            fullnameBox.sendKeys("Tran Van B");

            if(fullnameBox.getAttribute("value").equals("Tran Van B")){
                test.pass("Enter fullname box successfully!");
            }else {
                test.fail("Enter fullname box unsuccessfully!");
            }

            WebElement birthdayBox = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[3]/div/input[2]"));
            Assertions.assertTrue(birthdayBox.isDisplayed());
            birthdayBox.clear();
            birthdayBox.sendKeys("12102002");
            
            if(birthdayBox.getAttribute("value").equals("2002-12-10")){
                test.pass("Enter birthday name box successfully!");
            }else {
                test.fail("Enter birthday box unsuccessfully!");
            }

            WebElement emailBox = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[3]/div/input[3]"));
            Assertions.assertTrue(emailBox.isDisplayed());
            emailBox.clear();
            emailBox.sendKeys("example@gmail.com");

            if(emailBox.getAttribute("value").equals("example@gmail.com")){
                test.pass("Enter email box successfully!");
            }else {
                test.fail("Enter email box unsuccessfully!");
            }

            WebElement addressBox = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[3]/div/input[4]"));
            Assertions.assertTrue(addressBox.isDisplayed());
            addressBox.clear();
            addressBox.sendKeys("address");
            if(addressBox.getAttribute("value").equals("address")){
                test.pass("Enter address box successfully!");
            }else {
                test.fail("Enter address box unsuccessfully!");
            }

            WebElement genderBox = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[3]/div/input[5]"));
            Assertions.assertTrue(genderBox.isDisplayed());
            genderBox.clear();
            genderBox.sendKeys("nam");
            if(genderBox.getAttribute("value").equals("nam")){
                test.pass("Enter gender box successfully!");
            }else {
                test.fail("Enter gender box unsuccessfully!");
            }

            WebElement phoneNumBox = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[3]/div/input[6]"));
            Assertions.assertTrue(phoneNumBox.isDisplayed());
            phoneNumBox.clear();
            phoneNumBox.sendKeys("0123456789");
            if(phoneNumBox.getAttribute("value").equals("0123456789")){
                test.pass("Enter phone number box successfully!");
            }else {
                test.fail("Enter phone number box unsuccessfully!");
            }

            editProfileButton.click();
            Thread.sleep(1000);
            test.pass("Click save button successfully!");

            fullnameBox = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[3]/div/div[1]"));
            Assertions.assertTrue(fullnameBox.isDisplayed());
            if (fullnameBox.getText().equals("Tran Van B")){
                test.pass("Update fullname successfully!");
            }else {
                test.fail("Update fullname unsuccessfully!");
            }

            birthdayBox = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[3]/div/div[2]"));
            Assertions.assertTrue(birthdayBox.isDisplayed());
            if (birthdayBox.getText().equals("10/12/2002")){
                test.pass("Update birthday successfully!");
            }else {
                test.fail("Update birthday unsuccessfully!");
            }

            emailBox = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[3]/div/div[3]"));
            Assertions.assertTrue(emailBox.isDisplayed());
            if (emailBox.getText().equals("example@gmail.com")){
                test.pass("Update email successfully!");
            }else {
                test.fail("Update email unsuccessfully!");
            }

            addressBox = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[3]/div/div[4]"));
            Assertions.assertTrue(addressBox.isDisplayed());
            if (addressBox.getText().equals("address")){
                test.pass("Update Address Box successfully!");
            }else {
                test.fail("Update Address Box unsuccessfully!");
            }

            genderBox = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[3]/div/div[5]"));
            Assertions.assertTrue(genderBox.isDisplayed());
            if (genderBox.getText().equals("nam")){
                test.pass("Update Gender Box successfully!");
            }else {
                test.fail("Update Gender Box unsuccessfully!");
            }

            phoneNumBox = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[3]/div/div[6]"));
            Assertions.assertTrue(phoneNumBox.isDisplayed());
            if (phoneNumBox.getText().equals("0123456789")){
                test.pass("Update Phonenumber Box successfully!");
            }else {
                test.fail("Update Phonenumber Box unsuccessfully!");
            }

        }catch (Exception e){
            test.fail(e.getMessage());
        }
    }

    @ParameterizedTest
    @Order(3)
    @ValueSource(strings = {"chrome","edge","firefox"})
    public void checkChangePassWordComponent(String browser){
        try {
            login(browser);
            test.info("Check change password button and component!");
            Thread.sleep(2000);
            WebElement changePasswordButton = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[1]/button[2]"));
            Assertions.assertTrue(changePasswordButton.isDisplayed());
            test.pass("Change password button is displayed!");

            changePasswordButton.click();
            test.pass("Clinking change password button!");

            WebElement oldPasswordBox = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[3]/div[1]/input"));
            Assertions.assertTrue(oldPasswordBox.isDisplayed());
            test.pass("Old password box displayed!");

            WebElement  newPassword = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[3]/div[2]/input"));
            Assertions.assertTrue(newPassword.isDisplayed());
            test.pass("New password box displayed!");

            WebElement confirmPassword = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[3]/div[3]/input"));
            Assertions.assertTrue(confirmPassword.isDisplayed());
            test.pass("Confirm password displayed!");

            if(changePasswordButton.getText().equals("Save")){
                test.pass("Change Password button changed to Save button");
            }else{
                test.fail("Change Password button didn't change to Save button");
            }
            
            oldPasswordBox.sendKeys("adasdkakjdaksadksd");
            if(oldPasswordBox.getAttribute("value").equals("adasdkakjdaksadksd")){
                test.pass("Enter old password successfully!");
            }else {
                test.fail("Enter old password unsucessfully!");
            }

            newPassword.sendKeys("password11");
            if(newPassword.getAttribute("value").equals("password11")){
                test.pass("Enter new password successfully!");
            }else {
                test.fail("Enter new password unsucessfully!");
            }

            confirmPassword.sendKeys("password11");
            if ((confirmPassword.getAttribute("value").equals("password11"))){
                test.pass("Enter confirm password successfully!");
            }else {
                test.fail("Enter confirm password unsucessfully!");
            }

            changePasswordButton.click();
            
            Thread.sleep(1000);
            Alert alert = driver.switchTo().alert();
            if(alert.getText().equals("Old password didn't match")){
                test.pass("Sytem displayed: "+alert.getText());
            }else {
                test.fail("System displayed: "+alert.getText());
            }

            
            alert.accept();
            Thread.sleep(1000);
            changePasswordButton = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[1]/button[2]"));
            Assertions.assertTrue(changePasswordButton.isDisplayed());

            changePasswordButton.click();
            oldPasswordBox = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[3]/div[1]/input"));
            Assertions.assertTrue(oldPasswordBox.isDisplayed());
            
            newPassword = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[3]/div[2]/input"));
            Assertions.assertTrue(newPassword.isDisplayed());

            confirmPassword = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[3]/div[3]/input"));
            Assertions.assertTrue(confirmPassword.isDisplayed());

            oldPasswordBox.sendKeys("abcxyz123");
            if(oldPasswordBox.getAttribute("value").equals("abcxyz123")){
                test.pass("Enter old password successfully!");
            }else {
                test.fail("Enter old password unsucessfully!");
            }

            newPassword.sendKeys("321zyxcba");
            if(newPassword.getAttribute("value").equals("321zyxcba")){
                test.pass("Enter new password successfully!");
            }else {
                test.fail("Enter new password unsucessfully!");
            }

            confirmPassword.sendKeys("password11");
            if ((confirmPassword.getAttribute("value").equals("password11"))){
                test.pass("Enter confirm password successfully!");
            }else {
                test.fail("Enter confirm password unsucessfully!");
            }

            changePasswordButton.click();
            Thread.sleep(2000);

            alert = driver.switchTo().alert();
            if(alert.getText().equals("Mật khẩu mới không khớp!")){
                test.pass("Sytem displayed: "+alert.getText());
            }else {
                test.fail("System displayed: "+alert.getText());
            }

            alert.accept();

            

        }catch (Exception e){
            test.fail(e);
        }

    }
    

    @ParameterizedTest
    @ValueSource(strings={"chrome","edge","firefox"})
    @Order(99) //the last
    public void checkEnterNewPassword(String browser){
        try {
            driver = BrowserFactory.getDriver(browser);
            driver.get("http://localhost:3000/login");
            Thread.sleep(1000);
            String oldpass = "";
            if(browser.equals("chrome")){
                oldpass = "abcxyz123";
            }else if (browser.equals("edge")){
                oldpass = "321zyxcba";
            } else{
                oldpass = "abcxyz123";
            }

            String newpass = "";
            if(browser.equals("chrome")){
                newpass = "321zyxcba";
            }else if (browser.equals("edge")){
                newpass = "abcxyz123";
            } else{
                newpass= "321zyxcba";
            }            

            WebElement usernameField = driver.findElement(By.name("username"));
            usernameField.sendKeys("username2");
            WebElement passwordField = driver.findElement(By.name("password"));
            passwordField.sendKeys(oldpass);
            System.out.println(oldpass);
            WebElement signInButton = driver.findElement(By.xpath("//button[span[text()='Sign In']]"));
            signInButton.click();
            test.pass("Log in successfully!");
            Thread.sleep(3000);

            WebElement icon1 = driver.findElement(By.xpath("//*[@id=\"root\"]/header/div[2]/div/div"));
            Assertions.assertTrue(icon1.isDisplayed(),"Login icon did not display");
            test.pass("Login icon displayed!");

            icon1.click();

            Thread.sleep(2000);

            WebElement link = driver.findElement(By.xpath("//a[contains(text(), 'Profile')]"));

            link.click();
            test.info("Enter change password boxes!");
            Thread.sleep(2000);

            WebElement changePasswordButton = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[1]/button[2]"));
            Assertions.assertTrue(changePasswordButton.isDisplayed());
            test.pass("Change password button is displayed!");

            changePasswordButton.click();
            test.pass("Clinking change password button!");

            WebElement oldPasswordBox = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[3]/div[1]/input"));
            oldPasswordBox.sendKeys(oldpass);

            WebElement  newPassword = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[3]/div[2]/input"));
            newPassword.sendKeys(newpass);

            WebElement confirmPassword = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[3]/div[3]/input"));
            confirmPassword.sendKeys(newpass);

            changePasswordButton.click();
            Thread.sleep(1000);
            Alert alert = driver.switchTo().alert();
            alert.accept();
            Thread.sleep(2000);

            driver.get("http://localhost:3000/login");

            usernameField = driver.findElement(By.name("username"));
            usernameField.sendKeys("username2");
            passwordField = driver.findElement(By.name("password"));
            passwordField.sendKeys(newpass);
            signInButton = driver.findElement(By.xpath("//button[span[text()='Sign In']]"));

            signInButton.click();
            test.pass("Log in successfully!");

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
