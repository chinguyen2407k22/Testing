package org.example.test.test_project.UI_UX_testing.Sprint3;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.example.test.test_project.LogConfig.ExtendReport;
import org.example.test.test_project.WebBrowser.BrowserFactory;
import org.junit.jupiter.api.*;
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

        try {
            driver = BrowserFactory.getDriver("firefox");
            driver.get("http://localhost:3000/");
            Thread.sleep(1000);

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
            usernameField.sendKeys("username1");
            WebElement passwordField = driver.findElement(By.name("password"));
            passwordField.sendKeys("password1");
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

    @Test
    @Order(1)
    public void checkComponent(){
        try {
            test.info("check profile page component!");
            WebElement avatar = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[1]/div"));
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

            WebElement fullnameBox = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[3]/div[1]/div"));
            Assertions.assertTrue(fullnameBox.isDisplayed());
            test.pass("Fullname Box displayed!");

            WebElement birthdayBox = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[3]/div[2]/div"));
            Assertions.assertTrue(birthdayBox.isDisplayed());
            test.pass("Birthday Box displayed!");

            WebElement emailBox = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[3]/div[3]/div"));
            Assertions.assertTrue(emailBox.isDisplayed());
            test.pass("Email Box displayed!");

            WebElement addressBox = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[3]/div[4]/div"));
            Assertions.assertTrue(addressBox.isDisplayed());
            test.pass("Address Box displayed!");

            WebElement genderBox = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[3]/div[5]/div"));
            Assertions.assertTrue(genderBox.isDisplayed());
            test.pass("Gender Box displayed!");

            WebElement phoneNumBox = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[3]/div[6]/div"));
            Assertions.assertTrue(phoneNumBox.isDisplayed());
            test.pass("Phone Number Box displayed!");

        }catch (Exception e){
            test.fail(e.getMessage());
        }
    }
    @Test
    @Order(2)
    public void checkClickEditProfileButton(){
        try {
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

        }catch (Exception e){
            test.fail(e.getMessage());
        }
    }
    @Test
    @Order(3)
    public void checkEnterData(){
        try {
            test.info("Check click edit profile button and enter information!");
            WebElement editProfileButton = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[1]/button[1]"));
            Assertions.assertTrue(editProfileButton.isDisplayed());
            test.pass("Edit Profile Button displayed!");

            editProfileButton.click();
            test.pass("Click edit profile button successfully!");
            Thread.sleep(2000);

            WebElement fullnameBox = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[3]/div[1]/input"));
            Assertions.assertTrue(fullnameBox.isDisplayed());
            fullnameBox.clear();
            fullnameBox.sendKeys("Tran Van B");

            if(fullnameBox.getAttribute("value").equals("Tran Van B")){
                test.pass("Enter fullname box successfully!");
            }else {
                test.fail("Enter fullname box unsuccessfully!");
            }

            WebElement birthdayBox = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[3]/div[2]/input"));
            Assertions.assertTrue(birthdayBox.isDisplayed());
            birthdayBox.clear();
            birthdayBox.sendKeys("12/10/2002");

            if(birthdayBox.getAttribute("value").equals("12/10/2002")){
                test.pass("Enter birthday name box successfully!");
            }else {
                test.fail("Enter birthday box unsuccessfully!");
            }

            WebElement emailBox = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[3]/div[3]/input"));
            Assertions.assertTrue(emailBox.isDisplayed());
            emailBox.clear();
            emailBox.sendKeys("example@gmail.com");

            if(birthdayBox.getAttribute("value").equals("12/10/2002")){
                test.pass("Enter birthday box successfully!");
            }else {
                test.fail("Enter birthday box unsuccessfully!");
            }

            WebElement addressBox = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[3]/div[4]/input"));
            Assertions.assertTrue(addressBox.isDisplayed());
            addressBox.clear();
            addressBox.sendKeys("address");
            if(addressBox.getAttribute("value").equals("address")){
                test.pass("Enter address box successfully!");
            }else {
                test.fail("Enter address box unsuccessfully!");
            }

            WebElement genderBox = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[3]/div[5]/input"));
            Assertions.assertTrue(genderBox.isDisplayed());
            genderBox.clear();
            genderBox.sendKeys("nam");
            if(genderBox.getAttribute("value").equals("nam")){
                test.pass("Enter gender box successfully!");
            }else {
                test.fail("Enter gender box unsuccessfully!");
            }

            WebElement phoneNumBox = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[3]/div[6]/input"));
            Assertions.assertTrue(phoneNumBox.isDisplayed());
            phoneNumBox.clear();
            phoneNumBox.sendKeys("0123456789");
            if(phoneNumBox.getAttribute("value").equals("0123456789")){
                test.pass("Enter phone number box successfully!");
            }else {
                test.fail("Enter phone number box unsuccessfully!");
            }

        }catch (Exception e){
            test.fail(e.getMessage());
        }
    }
    @Test
    @Order(4)
    public void checkSave(){
        try {
            test.info("Check click edit profile button and enter information!");
            WebElement editProfileButton = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[1]/button[1]"));
            Assertions.assertTrue(editProfileButton.isDisplayed());
            test.pass("Edit Profile Button displayed!");

            editProfileButton.click();
            test.pass("Click edit profile button successfully!");

            WebElement fullnameBox = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[3]/div[1]/input"));
            Assertions.assertTrue(fullnameBox.isDisplayed());
            fullnameBox.clear();
            fullnameBox.sendKeys("Tran Van B");

            if(fullnameBox.getAttribute("value").equals("Tran Van B")){
                test.pass("Enter fullname box successfully!");
            }else {
                test.fail("Enter fullname box unsuccessfully!");
            }

            WebElement birthdayBox = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[3]/div[2]/input"));
            Assertions.assertTrue(birthdayBox.isDisplayed());
            birthdayBox.clear();
            birthdayBox.sendKeys("12/10/2002");

            WebElement emailBox = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[3]/div[3]/input"));
            Assertions.assertTrue(emailBox.isDisplayed());
            emailBox.clear();
            emailBox.sendKeys("example@gmail.com");

            WebElement addressBox = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[3]/div[4]/input"));
            Assertions.assertTrue(addressBox.isDisplayed());
            addressBox.clear();
            addressBox.sendKeys("address");

            WebElement genderBox = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[3]/div[5]/input"));
            Assertions.assertTrue(genderBox.isDisplayed());
            genderBox.clear();
            genderBox.sendKeys("nam");

            WebElement phoneNumBox = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[3]/div[6]/input"));
            Assertions.assertTrue(phoneNumBox.isDisplayed());
            phoneNumBox.clear();
            phoneNumBox.sendKeys("0123456789");

            editProfileButton.click();
            test.pass("Click save button successfully!");

            fullnameBox = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[3]/div[1]/div"));
            Assertions.assertTrue(fullnameBox.isDisplayed());
            if (fullnameBox.getText().equals("Tran Van B")){
                test.pass("Update fullname successfully!");
            }else {
                test.fail("Update fullname unsuccessfully!");
            }

            birthdayBox = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[3]/div[2]/div"));
            Assertions.assertTrue(birthdayBox.isDisplayed());
            if (birthdayBox.getText().equals("12/10/2002")){
                test.pass("Update birthday successfully!");
            }else {
                test.fail("Update birthday unsuccessfully!");
            }

            emailBox = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[3]/div[3]/div"));
            Assertions.assertTrue(emailBox.isDisplayed());
            if (emailBox.getText().equals("example@gmail.com")){
                test.pass("Update email successfully!");
            }else {
                test.fail("Update email unsuccessfully!");
            }

            addressBox = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[3]/div[4]/div"));
            Assertions.assertTrue(addressBox.isDisplayed());
            if (addressBox.getText().equals("address")){
                test.pass("Update Address Box successfully!");
            }else {
                test.fail("Update Address Box unsuccessfully!");
            }

            genderBox = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[3]/div[5]/div"));
            Assertions.assertTrue(genderBox.isDisplayed());
            if (genderBox.getText().equals("nam")){
                test.pass("Update Gender Box successfully!");
            }else {
                test.fail("Update Gender Box unsuccessfully!");
            }

            phoneNumBox = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[3]/div[6]/div"));
            Assertions.assertTrue(phoneNumBox.isDisplayed());
            if (phoneNumBox.getText().equals("0123456789")){
                test.pass("Update Phonenumber Box successfully!");
            }else {
                test.fail("Update Phonenumber Box unsuccessfully!");
            }
            Thread.sleep(3000);

        }catch (Exception e){
            test.fail(e.getMessage());
        }
    }


    @Test
    @Order(5)
    public void checkChangePassWordComponent(){
        try {
            test.info("Check change password button and component!");
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

        }catch (Exception e){
            test.fail(e);
        }

    }
    @Test
    @Order(6)
    public void checkWrongOldUsername(){
        try {
            test.info("Enter change password boxes with wrong old username!");
            WebElement changePasswordButton = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[1]/button[2]"));
            Assertions.assertTrue(changePasswordButton.isDisplayed());
            test.pass("Change password button is displayed!");

            changePasswordButton.click();
            test.pass("Clinking change password button!");

            WebElement oldPasswordBox = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[3]/div[1]/input"));
            Assertions.assertTrue(oldPasswordBox.isDisplayed());
            oldPasswordBox.sendKeys("password1");
            if(oldPasswordBox.getAttribute("value").equals("password1")){
                test.pass("Enter old password successfully!");
            }else {
                test.fail("Enter old password unsucessfully!");
            }

            WebElement  newPassword = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[3]/div[2]/input"));
            Assertions.assertTrue(newPassword.isDisplayed());
            newPassword.sendKeys("password11");
            if(newPassword.getAttribute("value").equals("password11")){
                test.pass("Enter new password successfully!");
            }else {
                test.fail("Enter new password unsucessfully!");
            }

            WebElement confirmPassword = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[3]/div[3]/input"));
            Assertions.assertTrue(confirmPassword.isDisplayed());
            confirmPassword.sendKeys("password11");
            if ((confirmPassword.getAttribute("value").equals("password11"))){
                test.pass("Enter confirm password successfully!");
            }else {
                test.fail("Enter confirm password unsucessfully!");
            }
            changePasswordButton.click();
            Alert alert = driver.switchTo().alert();
            if(alert.getText().equals("Mật khẩu cũ không đúng!")){
                test.pass("Sytem displayed: "+alert.getText());
            }else {
                test.fail("System displayed: "+alert.getText());
            }

            alert.accept();

        }catch (Exception e){
            test.fail(e);
        }
    }
    @Test
    @Order(7)
    public void checkWrongConfirmPassword(){
        try {
            test.info("Enter change password boxes with wrong old username!");
            WebElement changePasswordButton = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[1]/button[2]"));
            Assertions.assertTrue(changePasswordButton.isDisplayed());
            test.pass("Change password button is displayed!");

            changePasswordButton.click();
            test.pass("Clinking change password button!");

            WebElement oldPasswordBox = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[3]/div[1]/input"));
            Assertions.assertTrue(oldPasswordBox.isDisplayed());
            oldPasswordBox.sendKeys("password1");
            if(oldPasswordBox.getAttribute("value").equals("password1")){
                test.pass("Enter old password successfully!");
            }else {
                test.fail("Enter old password unsucessfully!");
            }

            WebElement  newPassword = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[3]/div[2]/input"));
            Assertions.assertTrue(newPassword.isDisplayed());
            newPassword.sendKeys("password11");
            if(newPassword.getAttribute("value").equals("password11")){
                test.pass("Enter new password successfully!");
            }else {
                test.fail("Enter new password unsucessfully!");
            }

            WebElement confirmPassword = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[3]/div[3]/input"));
            Assertions.assertTrue(confirmPassword.isDisplayed());
            confirmPassword.sendKeys("password12");
            if ((confirmPassword.getAttribute("value").equals("password12"))){
                test.pass("Enter confirm password successfully!");
            }else {
                test.fail("Enter confirm password unsucessfully!");
            }
            changePasswordButton.click();
            Alert alert = driver.switchTo().alert();
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

    @Test
    @Order(99) //the last
    public void checkEnterNewPassword(){
        try {
            test.info("Enter change password boxes!");
            WebElement changePasswordButton = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[1]/button[2]"));
            Assertions.assertTrue(changePasswordButton.isDisplayed());
            test.pass("Change password button is displayed!");

            changePasswordButton.click();
            test.pass("Clinking change password button!");

            WebElement oldPasswordBox = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[3]/div[1]/input"));
            Assertions.assertTrue(oldPasswordBox.isDisplayed());
            oldPasswordBox.sendKeys("password1");
            if(oldPasswordBox.getAttribute("value").equals("password1")){
                test.pass("Enter old password successfully!");
            }else {
                test.fail("Enter old password unsucessfully!");
            }

            WebElement  newPassword = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[3]/div[2]/input"));
            Assertions.assertTrue(newPassword.isDisplayed());
            newPassword.sendKeys("password11");
            if(newPassword.getAttribute("value").equals("password11")){
                test.pass("Enter new password successfully!");
            }else {
                test.fail("Enter new password unsucessfully!");
            }

            WebElement confirmPassword = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[3]/div[3]/input"));
            Assertions.assertTrue(confirmPassword.isDisplayed());
            confirmPassword.sendKeys("password11");
            if ((confirmPassword.getAttribute("value").equals("password11"))){
                test.pass("Enter confirm password successfully!");
            }else {
                test.fail("Enter confirm password unsucessfully!");
            }

            changePasswordButton.click();
            Alert alert = driver.switchTo().alert();
            if(alert.getText().equals("Mật khẩu đã được đổi thành công!")){
                test.pass("Sytem displayed: "+alert.getText());
            }else {
                test.fail("System displayed: "+alert.getText());
            }

            alert.accept();
            WebElement icon1 = driver.findElement(By.xpath("//*[@id=\"root\"]/header/div[2]/div/div"));
            Assertions.assertTrue(icon1.isDisplayed(),"Login icon did not display");
            test.pass("Login icon displayed!");

            icon1.click();

            Thread.sleep(1000);

            WebElement link = driver.findElement(By.xpath("//a[contains(text(), 'Logout')]"));
            test.pass("Logout button displayed!");

            link.click();

            driver.get("http://localhost:3000/login");
            test.pass("Changed to login page!");

            WebElement usernameField = driver.findElement(By.name("username"));
            usernameField.sendKeys("username1");
            WebElement passwordField = driver.findElement(By.name("password"));
            passwordField.sendKeys("password11");
            WebElement signInButton = driver.findElement(By.xpath("//button[span[text()='Sign In']]"));

            signInButton.click();
            String currentUrl = driver.getCurrentUrl();
            String expectedUrl = "http://localhost:3000/";

            if (currentUrl.equals(expectedUrl)) {
                test.pass("Login successfully!");
            } else {
                test.fail("Login unsuccessfully!");
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
