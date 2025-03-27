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
import java.util.Set;

public class LoginPage {
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
    @ValueSource(strings = {"chrome","edge","firefox"})
    public void testLoginFuctionElement(String browser) {
        driver = BrowserFactory.getDriver(browser);
        driver.get("http://localhost:3000/login");
        try {
            WebElement usernameField = driver.findElement(By.name("username"));
            WebElement passwordField = driver.findElement(By.name("password"));
            WebElement signInButton = driver.findElement(By.xpath("//button[span[text()='Sign In']]"));

            Assertions.assertTrue(usernameField.isDisplayed(), "Username did not display!");
            test.pass("Username box displayed!");
            Assertions.assertTrue(passwordField.isDisplayed(), "Password did not display!");
            test.pass("Password box displayed!");
            Assertions.assertTrue(signInButton.isDisplayed(), "Sign In button does not display!");
        }catch (Exception e) {
            test.fail(e.getMessage());
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"chrome","edge","firefox"})
    public void testLinkElement(String browser){
        driver = BrowserFactory.getDriver(browser);
        driver.get("http://localhost:3000/login");
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
    public void testOtherLinkElement(String browser){
        driver = BrowserFactory.getDriver(browser);
        driver.get("http://localhost:3000/login");
        try {
            WebElement signUpLink = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/div/div[2]/a"));
            WebElement forgetPasswordLink = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/div/a"));
            WebElement facebookLogin = driver.findElement(By.xpath("//div[@class='button-cluster']/button[2]"));
            WebElement googleLogin = driver.findElement(By.xpath("//div[@class='button-cluster']/button[1]"));

            Assertions.assertTrue(signUpLink.isDisplayed(),"Sign Up link does not display!");
            test.pass("Sign Up link displayed!");
            Assertions.assertTrue(forgetPasswordLink.isDisplayed(),"Forget Password Link does not display!");
            test.pass("Forget PassWord Link displayed!");
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
    public void testBarElement(String browser) {
        driver = BrowserFactory.getDriver(browser);
        driver.get("http://localhost:3000/login");
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

    @ValueSource(strings = {"chrome","edge","firefox"})
    public void testLoginSuccessfully(String browser){
        driver = BrowserFactory.getDriver(browser);
        driver.get("http://localhost:3000/login");
        try {
            WebElement usernameField = driver.findElement(By.name("username"));
            usernameField.sendKeys("username1");
            WebElement passwordField = driver.findElement(By.name("password"));
            passwordField.sendKeys("password1");
            WebElement signInButton = driver.findElement(By.xpath("//button[span[text()='Sign In']]"));
            signInButton.click();
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            String currentUrl = driver.getCurrentUrl();
            String expectedUrl = "http://localhost:3000/products";

            if (currentUrl.equals(expectedUrl)) {
                test.pass("Page changed successfully!");
            } else {
                test.fail("Page changed unsuccessfully!");
            }
            test.pass("Test Login successfully!");
        }catch (Exception e) {
            test.fail(e.getMessage());
        }

    }
    @ParameterizedTest
    @ValueSource(strings = {"chrome","edge","firefox"})
    public void testLoginSuccessfullywithEmail(String browser){
        driver = BrowserFactory.getDriver(browser);
        driver.get("http://localhost:3000/login");
        try {
            WebElement usernameField = driver.findElement(By.name("username"));
            usernameField.sendKeys("example@gmail.com");
            WebElement passwordField = driver.findElement(By.name("password"));
            passwordField.sendKeys("password1");
            WebElement signInButton = driver.findElement(By.xpath("//button[span[text()='Sign In']]"));
            signInButton.click();
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            String currentUrl = driver.getCurrentUrl();
            String expectedUrl = "http://localhost:3000/products";

            if (currentUrl.equals(expectedUrl)) {
                test.pass("Page changed successfully!");
            } else {
                test.fail("Page unchaged!");
            }
            test.pass("Test Login successfully!");
        }catch (Exception e) {
            test.fail(e.getMessage());
        }

    }



    @ParameterizedTest
    @ValueSource(strings = {"chrome","edge","firefox"})
    public void testInput(String browser) {
        driver = BrowserFactory.getDriver(browser);
        driver.get("http://localhost:3000/login");
        try{
            WebElement username = driver.findElement(By.name("username"));
            username.sendKeys("username1");
            String testStr = username.getAttribute("value");
            Assertions.assertTrue(testStr.equals("username1"),"Input value incorrect!");
            test.pass("Enter username input successfully!");

            WebElement password = driver.findElement(By.name("password"));
            password.sendKeys("password1");
            testStr = password.getAttribute("value");
            Assertions.assertTrue(testStr.equals("password1"),"Input value incorrect!");
            test.pass("Enter password input successfully!");

        }catch (Exception e) {
            test.fail(e.getMessage());
        }

    }

    @ParameterizedTest
    @ValueSource(strings = {"chrome","edge","firefox"})
    public void testFacebookLogin(String browser) {
        driver = BrowserFactory.getDriver(browser);
        driver.get("http://localhost:3000/login");

        try {
            WebElement facebookLogin = driver.findElement(By.xpath("//div[@class='button-cluster']/button[2]"));
            facebookLogin.click();
            test.pass("Test FaceBook login link successfully!");
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
    }
    @ParameterizedTest
    @ValueSource(strings = {"chrome","edge","firefox"})
    public void testGoogleLogin(String browser) {
        driver = BrowserFactory.getDriver(browser);
        driver.get("http://localhost:3000/login");
        try {
            WebElement googleLogin = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/div/div[6]/button[1]"));
            googleLogin.click();
            test.pass("Test Google login link successfully!");

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


    }
    @ParameterizedTest
    @ValueSource(strings = {"chrome","edge","firefox"})
    public void test100input(String browser) {
        driver = BrowserFactory.getDriver(browser);
        driver.get("http://localhost:3000/login");
        try {
            WebElement username = driver.findElement(By.name("username"));
            WebElement password = driver.findElement(By.name("password"));
            for (int i = 0; i <99;i++){
                username.sendKeys("username1");
                password.sendKeys("password1");
                username.clear();
                password.clear();
            }
            username.sendKeys("username1");
            password.sendKeys("password1");
            test.pass("Test Enter username and password 100 times successfully!");
        }catch (Exception e) {
            test.fail(e.getMessage());
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"chrome","edge","firefox"})
    public void test100FacebookLogin(String browser) {
        driver = BrowserFactory.getDriver(browser);
        driver.get("http://localhost:3000/login");
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
    public void test100GoogleLogin(String browser) {
        driver = BrowserFactory.getDriver(browser);
        driver.get("http://localhost:3000/login");
        try {
            WebElement googleLogin = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/div/div[6]/button[1]"));
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
    public void testSignUpLink(String browser){
        driver = BrowserFactory.getDriver(browser);
        driver.get("http://localhost:3000/login");
        try {
            WebElement signUpLink = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/div/div[2]/a"));
            signUpLink.click();
            String currentUrl = driver.getCurrentUrl();
            String expectedUrl = "http://localhost:3000/register";

            if (currentUrl.equals(expectedUrl)) {
                test.pass("Page changed successfully!");
            } else {
                test.fail("Page unchanged!");
            }
            test.pass("Test link to Register Page successfully!");
        }catch (Exception e) {
            test.fail(e.getMessage());
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"chrome","edge","firefox"})
    public void testSignInButton(String browser){
        driver = BrowserFactory.getDriver(browser);
        driver.get("http://localhost:3000/login");
        try {
            WebElement signInButton = driver.findElement(By.xpath("//button[span[text()='Sign In']]"));
            signInButton.click();
            String currentUrl = driver.getCurrentUrl();
            String expectedUrl = "http://localhost:3000/login";

            if (currentUrl.equals(expectedUrl)) {
                test.pass("Page unchanged successfully!");
            } else {
                test.fail("Page changed!");
            }
            test.pass("Test Sign In button with no information successfully!");
        }catch (Exception e) {
            test.fail(e.getMessage());
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"chrome","edge","firefox"})
    public void testIncompleteUsername(String browser){
        driver = BrowserFactory.getDriver(browser);
        driver.get("http://localhost:3000/login");
        try {
            WebElement usernameField = driver.findElement(By.name("username"));
            usernameField.sendKeys("username1");
            WebElement signInButton = driver.findElement(By.xpath("//button[span[text()='Sign In']]"));
            signInButton.click();
            String currentUrl = driver.getCurrentUrl();
            String expectedUrl = "http://localhost:3000/login";

            if (currentUrl.equals(expectedUrl)) {
                test.pass("Page unchanged successfully!");
            } else {
                test.fail("Page changed!");
            }
            test.pass("Test Sign In button with no password successfully!");
        }catch (Exception e) {
            test.fail(e.getMessage());
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"chrome","edge","firefox"})
    public void testIncompletePassWord(String browser){
        driver = BrowserFactory.getDriver(browser);
        driver.get("http://localhost:3000/login");
        try {
            WebElement passwordField = driver.findElement(By.name("password"));
            passwordField.sendKeys("password1");
            WebElement signInButton = driver.findElement(By.xpath("//button[span[text()='Sign In']]"));
            signInButton.click();
            String currentUrl = driver.getCurrentUrl();
            String expectedUrl = "http://localhost:3000/login";

            if (currentUrl.equals(expectedUrl)) {
                test.pass("Page unchanged successfully!");
            } else {
                test.fail("Page changed!");
            }
            test.pass("Run Sign In button with no username testcase successfully!");
        }catch (Exception e) {
            test.fail(e.getMessage());
        }
        //WebElement usernameField = driver.findElement(By.name("username"));
        //usernameField.sendKeys("username1");
    }

    @ParameterizedTest
    @ValueSource(strings = {"chrome","edge","firefox"})
    public void testWrongPassWord(String browser){
        driver = BrowserFactory.getDriver(browser);
        driver.get("http://localhost:3000/login");
        try {

            WebElement usernameField = driver.findElement(By.name("username"));
            usernameField.sendKeys("username1");
            WebElement passwordField = driver.findElement(By.name("password"));
            passwordField.sendKeys("password2222");
            WebElement signInButton = driver.findElement(By.xpath("//button[span[text()='Sign In']]"));
            signInButton.click();
            String currentUrl = driver.getCurrentUrl();
            String expectedUrl = "http://localhost:3000/login";

            if (currentUrl.equals(expectedUrl)) {
                test.pass("Page unchanged successfully!");
            } else {
                test.fail("Page changed!");
            }
            test.pass("Run Sign In button with username and wrongpassword testcase!");
        }catch (Exception e) {
            test.fail(e.getMessage());
        }
    }

        @ParameterizedTest
        @ValueSource(strings = {"chrome","edge","firefox"})
        public void testWrongPassWordEmail(String browser){
            driver = BrowserFactory.getDriver(browser);
            driver.get("http://localhost:3000/login");
            try {

                WebElement usernameField = driver.findElement(By.name("username"));
                usernameField.sendKeys("username1");
                WebElement passwordField = driver.findElement(By.name("password"));
                passwordField.sendKeys("password2222");
                WebElement signInButton = driver.findElement(By.xpath("//button[span[text()='Sign In']]"));
                signInButton.click();
                String currentUrl = driver.getCurrentUrl();
                String expectedUrl = "http://localhost:3000/login";

                if (currentUrl.equals(expectedUrl)) {
                    test.pass("Page unchanged successfully!");
                } else {
                    test.fail("Page changed!");
                }
                test.pass("Run Sign In button with email and wrong password!");
            }catch (Exception e) {
                test.fail(e.getMessage());
            }
        }

    @ParameterizedTest
    @ValueSource(strings = {"chrome","edge","firefox"})
    public void testWrongUsername(String browser){
        driver = BrowserFactory.getDriver(browser);
        driver.get("http://localhost:3000/login");
        try {

            WebElement usernameField = driver.findElement(By.name("username"));
            usernameField.sendKeys("username134");
            WebElement passwordField = driver.findElement(By.name("password"));
            passwordField.sendKeys("password1");
            WebElement signInButton = driver.findElement(By.xpath("//button[span[text()='Sign In']]"));
            signInButton.click();
            String currentUrl = driver.getCurrentUrl();
            String expectedUrl = "http://localhost:3000/login";

            if (currentUrl.equals(expectedUrl)) {
                test.pass("Page unchanged successfully!");
            } else {
                test.fail("Page changed!");
            }
            test.pass("Run Sign In button wrong username!");
        }catch (Exception e) {
            test.fail(e.getMessage());
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"chrome","edge","firefox"})
    public void testWrongEmail(String browser){
        driver = BrowserFactory.getDriver(browser);
        driver.get("http://localhost:3000/login");
        try {

            WebElement usernameField = driver.findElement(By.name("username"));
            usernameField.sendKeys("example123@gmail.com");
            WebElement passwordField = driver.findElement(By.name("password"));
            passwordField.sendKeys("password1");
            WebElement signInButton = driver.findElement(By.xpath("//button[span[text()='Sign In']]"));
            signInButton.click();
            String currentUrl = driver.getCurrentUrl();
            String expectedUrl = "http://localhost:3000/login";

            if (currentUrl.equals(expectedUrl)) {
                test.pass("Page unchanged successfully!");
            } else {
                test.fail("Page changed!");
            }
            test.pass("Test Sign In button wrong email!");
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
