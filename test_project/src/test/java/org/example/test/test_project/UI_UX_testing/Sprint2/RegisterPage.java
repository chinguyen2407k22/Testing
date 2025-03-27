package org.example.test.test_project.UI_UX_testing.Sprint2;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.example.test.test_project.LogConfig.ExtendReport;
import org.example.test.test_project.WebBrowser.BrowserFactory;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class RegisterPage {
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

    /*@ParameterizedTest
    @ValueSource(strings = {"chrome","edge","firefox"})
    public void testRegisterElement(String browser) {
        driver = BrowserFactory.getDriver(browser);
        driver.get("http://localhost:3000/register");

        try {
            WebElement username = driver.findElement(By.name("username"));
            WebElement lastname = driver.findElement(By.name("lastname"));
            WebElement firstname = driver.findElement(By.name("firstname"));
            WebElement phonenum = driver.findElement(By.name("phone"));
            WebElement email = driver.findElement(By.name("email"));
            WebElement password = driver.findElement(By.name("password"));
            WebElement confirmPassword =driver.findElement(By.name("confirmPassword"));
            WebElement signUp = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/div/button"));

            Assertions.assertTrue(username.isDisplayed(),"Username box did not display!");
            test.pass("Username box displayed!");
            Assertions.assertTrue(lastname.isDisplayed(),"Lastname Box did not display!");
            test.pass("Lastname box displayed!");
            Assertions.assertTrue(firstname.isDisplayed(),"Firstname Box did not display!");
            test.pass("Firstname box displayed!");
            Assertions.assertTrue(phonenum.isDisplayed(),"Phone Number Box did not display!");
            test.pass("Phone number box displayed!");
            Assertions.assertTrue(email.isDisplayed(),"Email Box did not display!");
            test.pass("Email box displayed!");
            Assertions.assertTrue(password.isDisplayed(),"Password Box did not display!");
            test.pass("Password box displayed!");
            Assertions.assertTrue(confirmPassword.isDisplayed(),"Confirm PassWord Box did not display!");
            test.pass("Confirm password box displayed!");
            Assertions.assertTrue(signUp.isDisplayed(),"Sign Up button did not display");
            test.pass("Sign Up box displayed!");

        }catch (Exception e) {
            test.fail(e.getMessage());
        }
    }
    @ParameterizedTest
    @ValueSource(strings = {"chrome","edge","firefox"})
    public void testLinkElement(String browser){
        driver = BrowserFactory.getDriver(browser);
        driver.get("http://localhost:3000/register");
        try {
            WebElement facebookLink = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[1]/a[1]"));
            WebElement instaLink = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[1]/a[2]"));
            WebElement linkIn = driver.findElement(By.xpath("//*[@id='root']/div/div[1]/a[3]"));
            WebElement twitterLink = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[1]/a[4]"));

            Assertions.assertTrue(facebookLink.isDisplayed(),"Facebook link did not display!");
            test.pass("Facebook link box displayed!");

            Assertions.assertTrue(linkIn.isDisplayed(),"LinkIn link did not display!");
            test.pass("LinkIn link box displayed!");

            Assertions.assertTrue(twitterLink.isDisplayed(),"Twitter Link did not display!");
            test.pass("Twitter link box displayed!");

            Assertions.assertTrue(instaLink.isDisplayed(),"Instagram Link did not display!");
            test.pass("Instagram link box displayed!");

        }catch (Exception e) {
            test.fail(e.getMessage());
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"chrome","edge","firefox"})
    public void testBarElement(String browser) {
        driver = BrowserFactory.getDriver(browser);
        driver.get("http://localhost:3000/register");
        try {
            WebElement homeSpan = driver.findElement(By.xpath("//span[text()='HOME']"));
            WebElement aboutUsSpan = driver.findElement(By.xpath("//span[text()='ABOUT US']"));
            WebElement productsSpan = driver.findElement(By.xpath("//span[text()='PRODUCTS']"));
            WebElement newReleaseSpan = driver.findElement(By.xpath("//span[text()='NEW RELEASE']"));
            WebElement contactUsSpan = driver.findElement(By.xpath("//span[text()='CONTACT US']"));

            Assertions.assertTrue(homeSpan.isDisplayed(),"HOME page link did not display!");
            test.pass("HOME displayed!");

            Assertions.assertTrue(aboutUsSpan.isDisplayed(),"ABOUT US page link did not display!");
            test.pass("ABOUT displayed!");

            Assertions.assertTrue(newReleaseSpan.isDisplayed(),"NEW RELEASE page link did not display!");
            test.pass("NEW RELEASE displayed!");

            Assertions.assertTrue(productsSpan.isDisplayed(),"PRODUCTS page link did not display!");
            test.pass("PRODUCTS displayed!");

            Assertions.assertTrue(contactUsSpan.isDisplayed(),"CONTACT US page link did not display!");
            test.pass("CONTACT displayed!");
        }catch (Exception e) {
            test.fail(e.getMessage());
        }

    }

    @ParameterizedTest
    @ValueSource(strings = {"chrome","edge","firefox"})
    public void testOtherLinkElement(String browser){
        driver = BrowserFactory.getDriver(browser);
        driver.get("http://localhost:3000/register");
        try {
            WebElement signIpLink= driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/div/div[2]/a"));
            WebElement facebookLogin = driver.findElement(By.xpath("//div[@class='button-cluster']/button[2]"));
            WebElement googleLogin = driver.findElement(By.xpath("//div[@class='button-cluster']/button[1]"));
            Assertions.assertTrue(signIpLink.isDisplayed(),"Sign In link did not display!");
            test.pass("Sign In link displayed!");
            Assertions.assertTrue(facebookLogin.isDisplayed(),"FaceBook Login did not display!");
            test.pass("Facebook login displayed!");
            Assertions.assertTrue(googleLogin.isDisplayed(),"Google Login did not display!");
            test.pass("Google login displayed!");
        }catch (Exception e) {
            test.fail(e.getMessage());
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"chrome","edge","firefox"})
    public void testInput(String browser){
        driver = BrowserFactory.getDriver(browser);
        driver.get("http://localhost:3000/register");
        try {
            WebElement username = driver.findElement(By.name("username"));
            username.sendKeys("username1");
            String testStr = username.getAttribute("value");
            Assertions.assertTrue(testStr.equals("username1"),"Input value incorrect!");

            WebElement lastname = driver.findElement(By.name("lastname"));
            lastname.sendKeys("lastname1");
            testStr = lastname.getAttribute("value");
            Assertions.assertTrue(testStr.equals("lastname1"),"Input value incorrect!");
            test.pass("Enter username input successfully!");

            WebElement firstname = driver.findElement(By.name("firstname"));
            firstname.sendKeys("firstname1");
            testStr = firstname.getAttribute("value");
            Assertions.assertTrue(testStr.equals("firstname1"),"Input value incorrect!");
            test.pass("Enter firstname input successfully!");

            WebElement phonenum = driver.findElement(By.name("phone"));
            phonenum.sendKeys("phonenumber1");
            testStr = phonenum.getAttribute("value");
            Assertions.assertTrue(testStr.equals("phonenumber1"),"Input value incorrect!");
            test.pass("Enter phone number input successfully!");

            WebElement email = driver.findElement(By.name("email"));
            email.sendKeys("email1");
            testStr = email.getAttribute("value");
            Assertions.assertTrue(testStr.equals("email1"),"Input value incorrect!");
            test.pass("Enter email input successfully!");

            WebElement password = driver.findElement(By.name("password"));
            password.sendKeys("password1");
            testStr = password.getAttribute("value");
            Assertions.assertTrue(testStr.equals("password1"),"Input value incorrect!");
            test.pass("Enter password input successfully!");

            WebElement confirmPassword =driver.findElement(By.name("confirmPassword"));
            confirmPassword.sendKeys("confirmpassword1");
            testStr = confirmPassword.getAttribute("value");
            Assertions.assertTrue(testStr.equals("confirmpassword1"),"Input value incorrect!");
            test.pass("Enter confirm input successfully!");
        }
        catch (Exception e) {
            test.fail(e.getMessage());
        }

    }



    @ParameterizedTest
    @ValueSource(strings = {"chrome","edge","firefox"})
    public void testGoogleLogin(String browser) {
        driver = BrowserFactory.getDriver(browser);
        driver.get("http://localhost:3000/register");
        try {
            WebElement googleLogin = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/div/div[11]/button[1]"));
            googleLogin.click();
            String mainWindow = driver.getWindowHandle();
            Set<String> windowHandles = driver.getWindowHandles();

            for (String window : windowHandles) {
                if (!window.equals(mainWindow)) {
                    driver.switchTo().window(window);
                    break;
                }
            }
            test.pass("Click Google Login link successfully!");
        }catch (Exception e) {
            test.fail(e.getMessage());
        }


    }


    @ParameterizedTest
    @ValueSource(strings = {"chrome","edge","firefox"})
    public void testLogInLink(String browser) {
        driver = BrowserFactory.getDriver(browser);
        driver.get("http://localhost:3000/register");
        try {
            WebElement signIpLink= driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/div/div[2]/a"));
            signIpLink.click();
            test.pass("Click Sign In link successfully!");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            String currentUrl = driver.getCurrentUrl();
            String expectedUrl = "http://localhost:3000/login";

            if (currentUrl.equals(expectedUrl)) {
                test.pass("Page changed successfully!");
            } else {
                test.fail("Page changed unsucessfully!");
            }
        }catch (Exception e) {
            test.fail(e.getMessage());
        }

    }

    @ParameterizedTest
    @ValueSource(strings = {"chrome","edge","firefox"})
    public void testSignUpUnCompletedAll(String browser) {
        driver = BrowserFactory.getDriver(browser);
        driver.get("http://localhost:3000/register");
        try {
            WebElement signUp = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/div/button"));
            signUp.click();
            test.pass("Test register with no required information testcase successfully!");
            String currentUrl = driver.getCurrentUrl();
            String expectedUrl = "http://localhost:3000/register";

            if (currentUrl.equals(expectedUrl)) {
                test.pass("Page unchanged successfully!");
            } else {
                test.fail("Error!");
            }
        }catch (Exception e) {
            test.fail(e.getMessage());
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"chrome","edge","firefox"})
    public void testSignUpUnCompletedEmail(String browser) {
        driver = BrowserFactory.getDriver(browser);
        driver.get("http://localhost:3000/register");
        try {
            WebElement username = driver.findElement(By.name("username"));
            username.sendKeys("username1");

            WebElement lastname = driver.findElement(By.name("lastname"));
            lastname.sendKeys("lastname1");


            WebElement password = driver.findElement(By.name("password"));
            password.sendKeys("password1");

            WebElement confirmPassword =driver.findElement(By.name("confirmPassword"));
            confirmPassword.sendKeys("password1");
            WebElement signUp = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/div/button"));
            signUp.click();
            test.pass("Test register with lack of email testcase successfully!");
            String currentUrl = driver.getCurrentUrl();
            String expectedUrl = "http://localhost:3000/register";

            if (currentUrl.equals(expectedUrl)) {
                test.pass("Page unchanged successfully!");
            } else {
                test.fail("Error!");
            }
        }catch (Exception e) {
            test.fail(e.getMessage());
        }
    }
    @ParameterizedTest
    @ValueSource(strings = {"chrome","edge","firefox"})
    public void testSignUpUnCompletedConfirmPassWord(String browser) {
        driver = BrowserFactory.getDriver(browser);
        driver.get("http://localhost:3000/register");
        try {
            WebElement username = driver.findElement(By.name("username"));
            username.sendKeys("username1");

            WebElement lastname = driver.findElement(By.name("lastname"));
            lastname.sendKeys("lastname1");

            WebElement email = driver.findElement(By.name("email"));
            email.sendKeys("example@gmail.com");


            WebElement password = driver.findElement(By.name("password"));
            password.sendKeys("password1");


            WebElement signUp = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/div/button"));
            signUp.click();
            test.pass("Test register with lack of confirm password testcase successfully!");

            String currentUrl = driver.getCurrentUrl();
            String expectedUrl = "http://localhost:3000/register";

            if (currentUrl.equals(expectedUrl)) {
                test.pass("Page unchanged successfully!");
            } else {
                test.fail("Error!");
            }
        }catch (Exception e) {
            test.fail(e.getMessage());
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"chrome","edge","firefox"})
    public void testSignUpWrongEmailFormat(String browser) {
        driver = BrowserFactory.getDriver(browser);
        driver.get("http://localhost:3000/register");
        try {
            WebElement username = driver.findElement(By.name("username"));
            username.sendKeys("username1");

            WebElement lastname = driver.findElement(By.name("lastname"));
            lastname.sendKeys("lastname1");

            WebElement email = driver.findElement(By.name("email"));
            email.sendKeys("email1");

            WebElement password = driver.findElement(By.name("password"));
            password.sendKeys("password1");

            WebElement confirmPassword = driver.findElement(By.name("confirmPassword"));
            confirmPassword.sendKeys("password1");

            WebElement signUp = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/div/button"));
            signUp.click();
            test.pass("Test register with wrong email format testcase successfully!");

            String currentUrl = driver.getCurrentUrl();
            String expectedUrl = "http://localhost:3000/register";

            if (currentUrl.equals(expectedUrl)) {
                test.pass("Page unchanged successfully!");
            } else {
                test.fail("Error!");
            }

        }catch (Exception e) {
            test.fail(e.getMessage());
        }
    }
    @ParameterizedTest
    @ValueSource(strings = {"chrome","edge","firefox"})
    public void testSignUpWrongConfirmPassWord(String browser) {
        driver = BrowserFactory.getDriver(browser);
        driver.get("http://localhost:3000/register");
        try{
            WebElement username = driver.findElement(By.name("username"));
            username.sendKeys("username1");

            WebElement lastname = driver.findElement(By.name("lastname"));
            lastname.sendKeys("lastname1");

            WebElement email = driver.findElement(By.name("email"));
            email.sendKeys("example@gmail.com");

            WebElement password = driver.findElement(By.name("password"));
            password.sendKeys("password1");

            WebElement confirmPassword = driver.findElement(By.name("confirmPassword"));
            confirmPassword.sendKeys("confirmpassword1");

            WebElement signUp = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/div/button"));
            signUp.click();
            test.pass("Test register with wrong confirm password testcase successfully!");

            String currentUrl = driver.getCurrentUrl();
            String expectedUrl = "http://localhost:3000/register";

            if (currentUrl.equals(expectedUrl)) {
                test.pass("Page unchanged successfully!");
            } else {
                test.fail("Error!");
            }
        }catch (Exception e) {
            test.fail(e.getMessage());
        }

    }

    @ParameterizedTest
    @ValueSource(strings = {"chrome","edge","firefox"})
    public void test100GoogleLogin(String browser) {
        driver = BrowserFactory.getDriver(browser);
        driver.get("http://localhost:3000/register");
        try {
            WebElement googleLogin = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/div/div[11]/button[1]"));
            for (int i = 0; i <100;i++){
                googleLogin.click();
            }
            test.pass("Click Google Login link 100 times successfully!");
        }catch (Exception e) {
            test.fail(e.getMessage());
        }

    }
    @ParameterizedTest
    @ValueSource(strings = {"chrome","edge","firefox"})
    public void test100input(String browser) {
        driver = BrowserFactory.getDriver(browser);
        driver.get("http://localhost:3000/register");
        try {
            WebElement username = driver.findElement(By.name("username"));
            WebElement phonenumber = driver.findElement(By.name("phone"));
            for (int i = 0; i <99;i++){
                username.sendKeys("username1");
                phonenumber.sendKeys("0123456789");
                username.clear();
                phonenumber.clear();
            }
            username.sendKeys("username1");
            phonenumber.sendKeys("0123456789");
            test.pass("Test Enter username and phone number 100 times successfully!");
        }catch (Exception e) {
            test.fail(e.getMessage());
        }

    }

    @ParameterizedTest
    @ValueSource(strings = {"chrome","edge","firefox"})
    public void test100FacebookLogin(String browser) {
        driver = BrowserFactory.getDriver(browser);
        driver.get("http://localhost:3000/register");
        try {
            WebElement facebookLogin = driver.findElement(By.xpath("//div[@class='button-cluster']/button[2]"));
            for (int i=0; i<100;i++){
                facebookLogin.click();
            }
            test.pass("Click FaceBook Login link 100 times successfully!");
        }catch (Exception e) {
            test.fail(e.getMessage());
        }


    }
    @ParameterizedTest
    @ValueSource(strings = {"chrome","edge","firefox"})
    public void testFacebookLogin(String browser) {
        driver = BrowserFactory.getDriver(browser);
        driver.get("http://localhost:3000/register");
        try {
            WebElement facebookLogin = driver.findElement(By.xpath("//div[@class='button-cluster']/button[2]"));
            facebookLogin.click();
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            String mainWindow = driver.getWindowHandle();
            Set<String> windowHandles = driver.getWindowHandles();

            for (String window : windowHandles) {
                if (!window.equals(mainWindow)) {
                    driver.switchTo().window(window);
                    break;
                }
            }
        }catch (Exception e) {
            test.fail(e.getMessage());
        }

    }*/
    @ParameterizedTest
    @ValueSource(strings = {"chrome","edge","firefox"})
    public void testRegisterUsedEmail(String browser) {
        driver = BrowserFactory.getDriver(browser);
        driver.get("http://localhost:3000/register");
        try{
            WebElement username = driver.findElement(By.name("username"));
            if(browser.equals("chrome")){
                username.sendKeys("usernamegg3");
            }
            else if(browser.equals("edge")){
                username.sendKeys("usernameie3");
            } else if (browser.equals("firefox")) {
                username.sendKeys("usernameff3");
            }

            WebElement phonenum = driver.findElement(By.name("phone"));
            phonenum.sendKeys("0123456789");

            WebElement firstname = driver.findElement(By.name("firstname"));
            firstname.sendKeys("firstname");

            WebElement lastname = driver.findElement(By.name("lastname"));
            lastname.sendKeys("lastname5");

            WebElement email = driver.findElement(By.name("email"));
            if(browser.equals("chrome")){
                email.sendKeys("example@gmail.com");
            }
            else if(browser.equals("edge")){
                email.sendKeys("example@gmail.com");
            } else if (browser.equals("firefox")) {
                email.sendKeys("example@gmail.com");
            }
            WebElement password = driver.findElement(By.name("password"));
            password.sendKeys("password5");

            WebElement confirm = driver.findElement(By.name("confirmPassword"));
            confirm.sendKeys("password5");

            WebElement signUp = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/div/button"));
            signUp.click();
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            test.pass("Test Register Successfully!");
            String currentUrl = driver.getCurrentUrl();
            String expectedUrl = "http://localhost:3000/register";

            if (currentUrl.equals(expectedUrl)) {
                test.pass("Page unchanged successfully!");
            } else {
                test.fail("Page changed!");
            }
        }catch (Exception e) {
            test.fail(e.getMessage());
        }

    }
    @ParameterizedTest
    @ValueSource(strings = {"chrome","edge","firefox"})
    public void testRegisterSuccessfully(String browser) {
        driver = BrowserFactory.getDriver(browser);
        driver.get("http://localhost:3000/register");
        try{
            WebElement username = driver.findElement(By.name("username"));
            if(browser.equals("chrome")){
                username.sendKeys("usernamegg2");
            }
            else if(browser.equals("edge")){
                username.sendKeys("usernameie2");
            } else if (browser.equals("firefox")) {
                username.sendKeys("usernameff2");
            }

            WebElement phonenum = driver.findElement(By.name("phone"));
            phonenum.sendKeys("0123456789");

            WebElement firstname = driver.findElement(By.name("firstname"));
            firstname.sendKeys("firstname");

            WebElement lastname = driver.findElement(By.name("lastname"));
            lastname.sendKeys("lastname5");

            WebElement email = driver.findElement(By.name("email"));
            if(browser.equals("chrome")){
                email.sendKeys("examplegg2@gmail.com");
            }
            else if(browser.equals("edge")){
                email.sendKeys("exampleie2@gmail.com");
            } else if (browser.equals("firefox")) {
                email.sendKeys("exampleff2@gmail.com");
            }
            WebElement password = driver.findElement(By.name("password"));
            password.sendKeys("password5");

            WebElement confirm = driver.findElement(By.name("confirmPassword"));
            confirm.sendKeys("password5");

            WebElement signUp = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/div/button"));
            signUp.click();
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            test.pass("Test Register Successfully!");
            String currentUrl = driver.getCurrentUrl();
            String expectedUrl = "http://localhost:3000/products";

            if (currentUrl.equals(expectedUrl)) {
                test.pass("Page changed successfully!");
            } else {
                test.fail("Page changed unsuccessfully!");
            }
        }catch (Exception e) {
            test.fail(e.getMessage());
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
