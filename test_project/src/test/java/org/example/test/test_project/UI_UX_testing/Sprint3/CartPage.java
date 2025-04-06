package org.example.test.test_project.UI_UX_testing.Sprint3;

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
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static io.restassured.RestAssured.given;

public class CartPage {
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

            WebElement icon2 = driver.findElement(By.xpath("//*[@id=\"root\"]/header/div[2]/div[2]"));
            Assertions.assertTrue(icon2.isDisplayed(),"Shopping cart icon did not display");

            icon2.click();
            Thread.sleep(1000);

            currentUrl = driver.getCurrentUrl();
            expectedUrl = "http://localhost:3000/cart";

            if (currentUrl.equals(expectedUrl)) {
                test.pass("Page changed to cart page successfully!");
            } else {
                test.fail("Page unchanged!");
            }
        }catch (Exception e){
            test.fail(e);
        }
    }
    @ParameterizedTest
    @ValueSource(strings = {"firefox"})
    public void checkComponent(String browser){
        try {
            test.info("Check Cart Page component");
            login(browser);
            WebElement cartHeader = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/h1"));
            Assertions.assertTrue(cartHeader.isDisplayed(),"Cart Header did not display");
            test.pass("Cart Header displayed!");

            WebElement bookImage = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[1]/table/tbody/tr[1]/td[1]/img"));
            Assertions.assertTrue(bookImage.isDisplayed(),"Book Image did not display");
            test.pass("Book Image displayed!");

            WebElement productTitle = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[1]/table/tbody/tr[1]/td[2]/p[1]"));
            Assertions.assertTrue(productTitle.isDisplayed(),"Product Title did not display");
            test.pass("Product Title displayed! Product Title: "+productTitle.getText());

            WebElement productAuthor = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[1]/table/tbody/tr[2]/td[2]/p[2]"));
            Assertions.assertTrue(productAuthor.isDisplayed(),"Product Author did not display");
            test.pass("Product Author displayed! Product Author: "+productAuthor.getText());

            WebElement productPrice = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[1]/table/tbody/tr[1]/td[3]"));
            Assertions.assertTrue(productPrice.isDisplayed(),"Product Price did not display");
            test.pass("Product price displayed! Product Price: "+productPrice.getText());

            WebElement productQuantity = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[1]/table/tbody/tr[1]/td[4]/div/input"));
            Assertions.assertTrue(productQuantity.isDisplayed(),"Product Quantity did not display");
            test.pass("Product Quantity displayed! Product Quantity: "+productQuantity.getText());

            WebElement removeButton = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[1]/table/tbody/tr[1]/td[4]/button"));
            Assertions.assertTrue(removeButton.isDisplayed(),"Remove Button did not display");
            test.pass("Remove Button displayed!");

            WebElement addButton = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[1]/table/tbody/tr[1]/td[4]/div/button[2]"));
            Assertions.assertTrue(addButton.isDisplayed(),"Add Button Price did not display");
            test.pass("Product price displayed!");

            WebElement minusButton = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[1]/table/tbody/tr[1]/td[4]/div/button[1]"));
            Assertions.assertTrue(minusButton.isDisplayed(),"Minus Button did not display");
            test.pass("Minus Button  displayed!");

            WebElement productTotalPrice = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[1]/table/tbody/tr[1]/td[5]"));
            Assertions.assertTrue(productTotalPrice.isDisplayed(),"Product Total Price did not display");
            test.pass("Product Total Price displayed! Total Price: "+productPrice.getText());

            WebElement subTotal = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[2]/div"));
            Assertions.assertTrue(subTotal.isDisplayed(),"Sub Total price did not display");
            test.pass("Sub Total price displayed!");

            WebElement totalPrice = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[3]/div[2]/div/span[2]"));
            Assertions.assertTrue(totalPrice.isDisplayed(),"Total Price did not display");
            test.pass("Total Price  displayed!");

            WebElement addCodeBox = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[3]/div[1]/div/input"));
            Assertions.assertTrue(addCodeBox.isDisplayed(),"Add Voucher Code Box did not display");
            test.pass("Add Voucher Code Box displayed!");

            WebElement addVoucherButton = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[3]/div[1]/div/button"));
            Assertions.assertTrue(addVoucherButton.isDisplayed(),"Add Voucher Button did not display");
            test.pass("Add Voucher Button displayed!");

            WebElement checkOutButton = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[4]/button"));
            Assertions.assertTrue(checkOutButton.isDisplayed(),"Check Out Button did not display");
            test.pass("Check Out Button displayed!");

        }catch (Exception e){
            test.fail(e.getMessage());
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"chrome","edge","firefox"})
    public void checkCheckOutButton(String browser){
        try {
            login(browser);
            test.info("Check Check Out button");
            Thread.sleep(1000);
            WebElement checkOutButton = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[4]/button"));
            Assertions.assertTrue(checkOutButton.isDisplayed(),"Check Out Button did not display");
            test.pass("Check Out Button displayed!");

            checkOutButton.click();
            test.pass("Click check out button successfully!");


            String currentUrl = driver.getCurrentUrl();
            String expectedUrl = "http://localhost:3000/confirmaddress";

            if (currentUrl.equals(expectedUrl)) {
                test.pass("Page changed to confirm address page successfully!");
            } else {
                test.fail("Page unchanged!");
            }

        }catch (Exception e){
            test.fail(e.getMessage());
        }
    }


    @ParameterizedTest
    @ValueSource(strings = {"chrome","edge","firefox"})
    public void checkPlusProductTotalPrice(String browser){
        try {
            login(browser);
            double epsilon = 1e-9;
            test.info("Check Plus Button with product total price");

            WebElement addButton = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[1]/table/tbody/tr[1]/td[4]/div/button[2]"));
            Assertions.assertTrue(addButton.isDisplayed(),"Add Button Price did not display");
            test.pass("Add button displayed!");

            WebElement productQuantity = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[1]/table/tbody/tr[1]/td[4]/div/input"));
            Assertions.assertTrue(productQuantity.isDisplayed(),"Product Quantity did not display");
            test.pass("Product Quantity displayed! Product Quantity: "+productQuantity.getText());
            String numBook = productQuantity.getAttribute("value");
            int old_number = Integer.parseInt(numBook);

            WebElement productPrice = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[1]/table/tbody/tr[1]/td[3]"));
            Assertions.assertTrue(productPrice.isDisplayed(),"Product Price did not display");
            String ppriceText = productPrice.getText().replace("$", "");
            double pprice = Double.parseDouble(ppriceText);

            WebElement productTotalPrice = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[1]/table/tbody/tr[1]/td[5]"));
            Assertions.assertTrue(productTotalPrice.isDisplayed(),"Product Total Price did not display");
            test.pass("Product Total Price displayed!");
            String priceText = productTotalPrice.getText().replace("$", "");
            double old_price = Double.parseDouble(priceText);

            WebElement subTotal = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[2]/div/span[2]"));
            Assertions.assertTrue(subTotal.isDisplayed(),"Sub Total price did not display");
            test.pass("Sub Total price displayed!");
            String subpriceText = subTotal.getText().replace("$", "");
            double subold_price = Double.parseDouble(subpriceText);

            WebElement totalPrice = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[3]/div[2]/div/span[2]"));
            Assertions.assertTrue(totalPrice.isDisplayed(),"Total Price did not display");
            test.pass("Total Price  displayed!");
            String tolpriceText = subTotal.getText().replace("$", "");
            double tolold_price = Double.parseDouble(tolpriceText);

            addButton.click();
            test.pass("Click add button successfylly!");
            Thread.sleep(1000);

            numBook=productQuantity.getAttribute("value");
            int new_number = Integer.parseInt(numBook);
            if(new_number == old_number + 1){
                test.pass("Number of books increased!");
            }else {
                test.fail("Number of books unchanged!");
            }

            priceText = productTotalPrice.getText().replace("$", "");
            double new_price = Double.parseDouble(priceText);
            if ((new_price - (old_price + pprice))<epsilon){
                test.pass("product total price was updated!");
            }else test.fail("product total price wasn't updated!");

            subpriceText = subTotal.getText().replace("$", "");
            double subnew_price = Double.parseDouble(subpriceText);
            if ((subnew_price - (subold_price + pprice))<epsilon){
                test.pass("sub total price was updated!");
            }else test.fail("sub total price wasn't updated!");

            tolpriceText = totalPrice.getText().replace("$", "");
            double tonew_price = Double.parseDouble(tolpriceText);
            if ((tonew_price - (tolold_price + pprice))<epsilon){
                test.pass("Total price was updated!");
            }else test.fail("Total price wasn't updated!");

        }catch (Exception e){
            test.fail(e.getMessage());
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"chrome","edge","firefox"})
    public void checkMinusButton(String browser){
        try {
            login(browser);
            double epsilon = 1e-9;
            test.info("Check Minus Button with num of book is greater than 1");
            WebElement addButton = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[1]/table/tbody/tr[1]/td[4]/div/button[2]"));
            Assertions.assertTrue(addButton.isDisplayed(),"Add Button Price did not display");
            addButton.click();

            WebElement minusButton = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[1]/table/tbody/tr[1]/td[4]/div/button[1]"));
            Assertions.assertTrue(minusButton.isDisplayed(),"Minus Button did not display");
            test.pass("Minus Button  displayed!");

            WebElement productQuantity = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[1]/table/tbody/tr[1]/td[4]/div/input"));
            Assertions.assertTrue(productQuantity.isDisplayed(),"Product Quantity did not display");
            test.pass("Product Quantity displayed! Product Quantity: "+productQuantity.getText());
            String numBook = productQuantity.getAttribute("value");
            int old_number = Integer.parseInt(numBook);

            WebElement productPrice = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[1]/table/tbody/tr[1]/td[3]"));
            Assertions.assertTrue(productPrice.isDisplayed(),"Product Price did not display");
            String ppriceText = productPrice.getText().replace("$", "");
            double pprice = Double.parseDouble(ppriceText);

            WebElement productTotalPrice = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[1]/table/tbody/tr[1]/td[5]"));
            Assertions.assertTrue(productTotalPrice.isDisplayed(),"Product Total Price did not display");
            test.pass("Product Total Price displayed!");
            String priceText = productTotalPrice.getText().replace("$", "");
            double old_price = Double.parseDouble(priceText);

            minusButton.click();
            test.pass("Click minus button successfully!");
            Thread.sleep(1000);
            numBook=productQuantity.getAttribute("value");
            int new_number = Integer.parseInt(numBook);
            if(new_number == old_number -1){
                test.pass("Number of books decreased!");
            }else {
                test.fail("Number of books unchanged!");
            }

            priceText = productTotalPrice.getText().replace("$", "");
            double new_price = Double.parseDouble(priceText);
            if (new_price - (old_price - pprice) < epsilon) {
                test.pass("product total price was updated!");
            } else test.fail("product total price wasn't updated!");

        }catch (Exception e){
            test.fail(e.getMessage());
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"chrome","edge","firefox"})
    public void checkEnterBox(String browser){
        try {
            login(browser);
            test.info("Check Enter add voucher box");

            WebElement addCodeBox = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[3]/div[1]/div/input"));
            Assertions.assertTrue(addCodeBox.isDisplayed(),"Add Voucher Code Box did not display");
            test.pass("Add Voucher Code Box displayed!");

            addCodeBox.sendKeys("MT22KH01");
            String text = addCodeBox.getAttribute("value");
            if (text.equals("MT22KH01")){
                test.pass("Enter code in box successfully!");
            }else {
                test.fail("Enter code in box unsuccessfully!");
            }

            for(int i=0; i<100;i++){
                addCodeBox.sendKeys("MT22KH01");
                addCodeBox.clear();
            }

            WebElement addVoucherButton = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[3]/div[1]/div/button"));
            Assertions.assertTrue(addVoucherButton.isDisplayed(),"Add Voucher Button did not display");
            test.pass("Add Voucher Button displayed!");

            addVoucherButton.click();
            test.pass("Click add voucher button successfully!");

        }catch (Exception e){
            test.fail(e.getMessage());
        }
    }



    /*@Test
    public void checkAddANewBook(){
        try {
            driver.get("http://localhost:3000/product");
            Thread.sleep(1000);
            WebElement hoverElement = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[2]/div[2]/div[2]/div"));

            Actions actions = new Actions(driver);
            actions.moveToElement(hoverElement).perform();

            WebElement icon2 = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[2]/div[2]/div[2]/div/div[1]/button"));
            test.pass("Add to cart button displayed!");

            icon2.click();
            test.pass("Clicking ADD TO CART BUTTON successfully!");

            WebElement addsuccessfullNofi = driver.findElement(By.xpath("//*[@id=\"root\"]/section"));
            if (addsuccessfullNofi.getText().equals("Added to cart successfully!")){
                test.pass("Add a book successfully");
            }else {
                test.fail("Add a book unsuccessfully");
            }
            icon2.click();
            test.pass("Clicking ADD TO CART BUTTON successfully!");

            addsuccessfullNofi = driver.findElement(By.xpath("//*[@id=\"root\"]/section"));
            if (addsuccessfullNofi.getText().equals("Added to cart successfully!")){
                test.pass("Add a book successfully");
            }else {
                test.fail("Add a book unsuccessfully");
            }
        }catch (Exception e){
            test.fail(e.getMessage());
        }
    }
    @Test
    public void checkRemoveABook(){
        try {
            test.info("Check Remove a the last book");

            WebElement removeButton = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[2]/div[4]/button"));
            Assertions.assertTrue(removeButton.isDisplayed(),"Remove Button did not display");
            test.pass("Remove Button displayed!");

            WebElement cartRow = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[2]"));
            Assertions.assertTrue(cartRow.isDisplayed(), "Cart Row did not display");
            test.pass("Cart Row displayed!");

            removeButton.click();

            WebElement cartRowNull = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[2]"));
            Assertions.assertTrue(cartRowNull.isDisplayed(), "Cart Row did not display");

            if(cartRowNull.getText().equals("There are no products here.")){
                test.pass("Delete the last book: "+cartRowNull.getText());
            }else {
                test.pass("Delete the last book: none!");
            }

        }catch (Exception e){
            test.fail(e.getMessage());
        }
    }
    @ParameterizedTest
    @ValueSource(strings = {"chrome","edge","firefox"})
    public void checkMinusButtonFrom(String browser){
        try {
            login(browser);
            test.info("Check Minus Button with num of book is 1");

            WebElement minusButton = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[1]/table/tbody/tr[2]/td[4]/div/button[1]"));
            Assertions.assertTrue(minusButton.isDisplayed(),"Minus Button did not display");
            test.pass("Minus Button  displayed!");

            WebElement productQuantity = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[1]/table/tbody/tr[1]/td[4]/div/input"));
            Assertions.assertTrue(productQuantity.isDisplayed(),"Product Quantity did not display");
            test.pass("Product Quantity displayed! Product Quantity: "+productQuantity.getText());
            String numBook = productQuantity.getAttribute("value");
            int old_number = Integer.parseInt(numBook);

            minusButton.click();
            test.pass("Click minus button successfully!");
            numBook=productQuantity.getAttribute("value");
            int new_number = Integer.parseInt(numBook);
            if(new_number == old_number){
                test.pass("Number of books unchanged!");
            }else {
                test.fail("Number of books changed!");
            }

        }catch (Exception e){
            test.fail(e.getMessage());
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
