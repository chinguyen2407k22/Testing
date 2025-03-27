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

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @AfterAll
    public static void closeTest(){
        ExtendReport.closeReport();
    }

    @ValueSource(strings = {"chrome"})
    @ParameterizedTest
    @ValueSource(strings = {"chrome","edge","firefox"})
    public void checkCheckOutButton(String browser){
        try {
            double epsilon = 1e-9;
            driver = BrowserFactory.getDriver(browser);
            driver.get("http://localhost:3000/cart");
            test.info("Check Check Out button");

            WebElement checkOutButton = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[7]/button"));
            Assertions.assertTrue(checkOutButton.isDisplayed(),"Check Out Button did not display");
            test.pass("Check Out Button displayed!");

            checkOutButton.click();
            test.pass("Click check out button successfully!");

            String currentUrl = driver.getCurrentUrl();
            String expectedUrl = "http://localhost:3000/confirmaddress";

            if (currentUrl.equals(expectedUrl)) {
                test.pass("Page changed successfully!");
            } else {
                test.fail("Page unchanged!");
            }

        }catch (Exception e){
            test.fail(e.getMessage());
        }
    }
    /*
    @ParameterizedTest
    @ValueSource(strings = {"chrome","edge","firefox"})
    public void checkComponent(String browser){
        try {
            driver = BrowserFactory.getDriver(browser);
            driver.get("http://localhost:3000/cart");
            test.info("Check Cart Page components");

            WebElement cartHeader = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[1]"));
            Assertions.assertTrue(cartHeader.isDisplayed(),"Cart Header did not display");
            test.pass("Cart Header displayed!");

            WebElement cartRow = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[2]"));
            Assertions.assertTrue(cartRow.isDisplayed(),"Cart Row did not display");
            test.pass("Cart Row displayed!");

            WebElement bookImage = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[2]/div[1]/img"));
            Assertions.assertTrue(bookImage.isDisplayed(),"Book Image did not display");
            test.pass("Book Image displayed!");

            WebElement productTitle = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[2]/div[2]/p[1]"));
            Assertions.assertTrue(productTitle.isDisplayed(),"Product Title did not display");
            test.pass("Product Title displayed!");

            WebElement productAuthor = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[2]/div[2]/p[2]"));
            Assertions.assertTrue(productAuthor.isDisplayed(),"Product Author did not display");
            test.pass("Product Author displayed!");

            WebElement productPrice = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[2]/div[3]"));
            Assertions.assertTrue(productPrice.isDisplayed(),"Product Price did not display");
            test.pass("Product price displayed!");

            WebElement productQuantity = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[2]/div[4]/div/input"));
            Assertions.assertTrue(productQuantity.isDisplayed(),"Product Quantity did not display");
            test.pass("Product Quantity displayed!");

            WebElement removeButton = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[2]/div[4]/button"));
            Assertions.assertTrue(removeButton.isDisplayed(),"Remove Button did not display");
            test.pass("Remove Button displayed!");

            WebElement addButton = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[2]/div[4]/div/button[2]"));
            Assertions.assertTrue(addButton.isDisplayed(),"Add Button Price did not display");
            test.pass("Product price displayed!");

            WebElement minusButton = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[2]/div[4]/div/button[1]"));
            Assertions.assertTrue(minusButton.isDisplayed(),"Minus Button did not display");
            test.pass("Minus Button  displayed!");

            WebElement productTotalPrice = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[2]/div[5]"));
            Assertions.assertTrue(productTotalPrice.isDisplayed(),"Product Total Price did not display");
            test.pass("Product Total Price displayed!");

            WebElement subTotal = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[5]/div[5]"));
            Assertions.assertTrue(subTotal.isDisplayed(),"Sub Total price did not display");
            test.pass("Sub Total price displayed!");

            WebElement totalPrice = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[6]/div/div[2]/div/span[2]"));
            Assertions.assertTrue(totalPrice.isDisplayed(),"Total Price did not display");
            test.pass("Total Price  displayed!");

            WebElement addCodeBox = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[6]/div/div[1]/div/input"));
            Assertions.assertTrue(addCodeBox.isDisplayed(),"Add Voucher Code Box did not display");
            test.pass("Add Voucher Code Box displayed!");

            WebElement addVoucherButton = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[6]/div/div[1]/div/button"));
            Assertions.assertTrue(addVoucherButton.isDisplayed(),"Add Voucher Button did not display");
            test.pass("Add Voucher Button displayed!");

            WebElement checkOutButton = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[7]/button"));
            Assertions.assertTrue(checkOutButton.isDisplayed(),"Check Out Button did not display");
            test.pass("Check Out Button displayed!");
        }catch (Exception e){
            test.fail(e.getMessage());
        }
    }
    @ValueSource(strings = {"chrome"})
    @ParameterizedTest
    //@ValueSource(strings = {"chrome","edge","firefox"})
    public void checkRemoveABook(String browser){
        try {
            driver = BrowserFactory.getDriver(browser);
            driver.get("http://localhost:3000/cart");
            test.info("Check remove a book");

            WebElement removeButton = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[2]/div[4]/button"));
            Assertions.assertTrue(removeButton.isDisplayed(),"Remove Button did not display");
            test.pass("Remove Button displayed!");

            WebElement productTitle = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[2]/div[2]/p[1]"));
            Assertions.assertTrue(productTitle.isDisplayed(), "Product Title did not display");
            test.pass("Product Title displayed!");

            removeButton.click();

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            boolean isProductRemoved = wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id=\"root\"]/main/div/div[2]/div[2]/p[1]")));

            if (isProductRemoved) {
                test.pass("A book was removed from the cart successfully!");
            } else {
                test.fail("Removed a book unsuccessfully!");
            }
            Thread.sleep(5000);
            File testImage = new File("./src/test/java/org/example/test/test_project/Image/TTHVTCX.jpg"); // Đảm bảo file ảnh tồn tại
            String bookJson = "{"
                    + "\"name\":\"Bố Già\","
                    + "\"rating\":4.9,"
                    + "\"author\":\"Mario Puzo\","
                    + "\"publisher\":\"NXB Văn Học\","
                    + "\"publishingCompany\":\"Nhà Nam\","
                    + "\"publishingYear\":2019,"
                    + "\"weight\":600,"
                    + "\"dimensions\":\"16x24 cm\","
                    + "\"pages\":700,"
                    + "\"coverType\":\"Bia_cung\","
                    + "\"translator\":\"Lê Đình Chi\","
                    + "\"summary\":\"Tác phẩm kinh điển về thế giới mafia và những cuộc đấu tranh quyền lực.\","
                    + "\"price\":220000.0,"
                    + "\"comments\":\"Một tuyệt phẩm về tội phạm và quyền lực.\""
                    + "}";

            given()
                    .multiPart("book", bookJson, "application/json")
                    .multiPart("image", testImage)
                    .contentType(ContentType.MULTIPART)
                    .when()
                    .post("http://localhost:8080/api/books")
                    .then()
                    .statusCode(200);
            test.pass("Add a book successfully!");

        }catch (Exception e){
            test.fail(e.getMessage());
        }
    }
    @ParameterizedTest
    @ValueSource(strings = {"chrome","edge","firefox"})
    public void checkPlusProductTotalPrice(String browser){
        try {
            driver = BrowserFactory.getDriver(browser);
            driver.get("http://localhost:3000/cart");
            test.info("Check Plus Button with product total price");

            WebElement addButton = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[2]/div[4]/div/button[2]"));
            Assertions.assertTrue(addButton.isDisplayed(),"Add Button did not display");
            test.pass("Add Button displayed!");

            WebElement productPrice = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[2]/div[3]"));
            Assertions.assertTrue(productPrice.isDisplayed(),"Product Price did not display");
            String ppriceText = productPrice.getText().replace("$", "");
            double pprice = Double.parseDouble(ppriceText);

            WebElement productTotalPrice = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[2]/div[5]"));
            Assertions.assertTrue(productTotalPrice.isDisplayed(),"Product Total Price did not display");
            test.pass("Product Total Price displayed!");
            String priceText = productTotalPrice.getText().replace("$", "");
            double old_price = Double.parseDouble(priceText);

            addButton.click();
            test.pass("Click add button successfylly!");

            priceText = productTotalPrice.getText().replace("$", "");
            double new_price = Double.parseDouble(priceText);
            if ((new_price - (old_price + pprice))<epsilon){
                test.pass("product total price was updated!");
            }else test.fail("product total price wasn't updated!");

        }catch (Exception e){
            test.fail(e.getMessage());
        }
    }
    @ParameterizedTest
    @ValueSource(strings = {"chrome","edge","firefox"})
    public void checkPlusSubTotalPrice(String browser){
        try {
            driver = BrowserFactory.getDriver(browser);
            driver.get("http://localhost:3000/cart");
            test.info("Check Plus Button with sub total price");

            WebElement addButton = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[2]/div[4]/div/button[2]"));
            Assertions.assertTrue(addButton.isDisplayed(),"Add Button did not display");
            test.pass("Add Button displayed!");

            WebElement productPrice = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[2]/div[3]"));
            Assertions.assertTrue(productPrice.isDisplayed(),"Product Price did not display");
            String ppriceText = productPrice.getText().replace("$", "");
            double pprice = Double.parseDouble(ppriceText);

            WebElement subTotal = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[5]/div[5]"));
            Assertions.assertTrue(subTotal.isDisplayed(),"Sub Total price did not display");
            test.pass("Sub Total price displayed!");
            String priceText = subTotal.getText().replace("$", "");
            double old_price = Double.parseDouble(priceText);

            addButton.click();
            test.pass("Click add button successfylly!");

            priceText = subTotal.getText().replace("$", "");
            double new_price = Double.parseDouble(priceText);
            if ((new_price - (old_price + pprice))<epsilon){
                test.pass("sub total price was updated!");
            }else test.fail("sub total price wasn't updated!");

        }catch (Exception e){
            test.fail(e.getMessage());
        }
    }
    @ParameterizedTest
    @ValueSource(strings = {"chrome","edge","firefox"})
    public void checkPlusTotalPrice(String browser){
        try {
            driver = BrowserFactory.getDriver(browser);
            driver.get("http://localhost:3000/cart");
            test.info("Check Plus Button with total price");
            double epsilon = 1e-9;

            WebElement addButton = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[2]/div[4]/div/button[2]"));
            Assertions.assertTrue(addButton.isDisplayed(),"Add Button did not display");
            test.pass("Add Button displayed!");

            WebElement productPrice = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[2]/div[3]"));
            Assertions.assertTrue(productPrice.isDisplayed(),"Product Price did not display");
            String ppriceText = productPrice.getText().replace("$", "");
            double pprice = Double.parseDouble(ppriceText);

            WebElement totalPrice = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[6]/div/div[2]/div/span[2]"));
            Assertions.assertTrue(totalPrice.isDisplayed(),"Total Price did not display");
            test.pass("Total Price  displayed!");
            String priceText = totalPrice.getText().replace("$", "");
            double old_price = Double.parseDouble(priceText);

            addButton.click();
            test.pass("Click add button successfully!");

            priceText = totalPrice.getText().replace("$", "");
            double new_price = Double.parseDouble(priceText);
            if ((new_price - (old_price + pprice))<epsilon){
                test.pass("Total price was updated!");
            }else test.fail("Total price wasn't updated!");

        }catch (Exception e){
            test.fail(e.getMessage());
        }
    }
    @ParameterizedTest
    @ValueSource(strings = {"chrome","edge","firefox"})
    public void checkPlusButton(String browser){
        try {
            driver = BrowserFactory.getDriver(browser);
            driver.get("http://localhost:3000/cart");
            test.info("Check Plus Button");

            WebElement addButton = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[2]/div[4]/div/button[2]"));
            Assertions.assertTrue(addButton.isDisplayed(),"Add Button did not display");
            test.pass("Add Button displayed!");

            WebElement num = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[2]/div[4]/div/input"));
            Assertions.assertTrue(num.isDisplayed(),"Number of books did not display!");
            test.pass("Number of books displayed!");
            String numBook = num.getAttribute("value");
            int old_number = Integer.parseInt(numBook);


            addButton.click();
            test.pass("Click add button successfully!");
            numBook=num.getAttribute("value");
            int new_number = Integer.parseInt(numBook);
            if(new_number == old_number + 1){
                test.pass("Number of books increased!");
            }else {
                test.fail("Number of books unchanged!");
            }


        }catch (Exception e){
            test.fail(e.getMessage());
        }
    }
    @ParameterizedTest
    @ValueSource(strings = {"chrome","edge","firefox"})
    public void checkMinusButtonFrom1(String browser){
        try {
            driver = BrowserFactory.getDriver(browser);
            driver.get("http://localhost:3000/cart");
            test.info("Check Minus Button with num of book is 1");

            WebElement minusButton = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[2]/div[4]/div/button[1]"));
            Assertions.assertTrue(minusButton.isDisplayed(),"Minus Button did not display");
            test.pass("Minus Button  displayed!");


            WebElement num = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[2]/div[4]/div/input"));
            Assertions.assertTrue(num.isDisplayed(),"Number of books did not display!");
            test.pass("Number of books displayed!");
            String numBook = num.getAttribute("value");
            int old_number = Integer.parseInt(numBook);


            minusButton.click();
            test.pass("Click minus button successfully!");
            numBook=num.getAttribute("value");
            int new_number = Integer.parseInt(numBook);
            if(new_number == old_number){
                test.pass("Number of books unchanged!");
            }else {
                test.fail("Number of books changed!");
            }

        }catch (Exception e){
            test.fail(e.getMessage());
        }
    }
    @ParameterizedTest
    @ValueSource(strings = {"chrome","edge","firefox"})
    public void checkMinusButton(String browser){
        try {
            driver = BrowserFactory.getDriver(browser);
            driver.get("http://localhost:3000/cart");
            test.info("Check Minus Button with num of book is greater than 1");
            WebElement addButton = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[2]/div[4]/div/button[2]"));
            Assertions.assertTrue(addButton.isDisplayed(),"Add Button did not display");
            addButton.click();
            addButton.click();

            WebElement minusButton = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[2]/div[4]/div/button[1]"));
            Assertions.assertTrue(minusButton.isDisplayed(),"Minus Button did not display");
            test.pass("Minus Button  displayed!");


            WebElement num = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[2]/div[4]/div/input"));
            Assertions.assertTrue(num.isDisplayed(),"Number of books did not display!");
            test.pass("Number of books displayed!");
            String numBook = num.getAttribute("value");
            int old_number = Integer.parseInt(numBook);


            minusButton.click();
            test.pass("Click minus button successfully!");
            numBook=num.getAttribute("value");
            int new_number = Integer.parseInt(numBook);
            if(new_number == old_number -1){
                test.pass("Number of books decreased!");
            }else {
                test.fail("Number of books unchanged!");
            }

        }catch (Exception e){
            test.fail(e.getMessage());
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"chrome","edge","firefox"})
    public void checkMinusButtonPrice(String browser){
        try {
            double epsilon = 1e-9;
            driver = BrowserFactory.getDriver(browser);
            driver.get("http://localhost:3000/cart");
            test.info("Check Minus Button with product total price");

            WebElement addButton = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[2]/div[4]/div/button[2]"));
            Assertions.assertTrue(addButton.isDisplayed(),"Add Button did not display");
            addButton.click();
            addButton.click();

            WebElement minusButton = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[2]/div[4]/div/button[1]"));
            Assertions.assertTrue(minusButton.isDisplayed(),"Minus Button did not display");
            test.pass("Minus Button  displayed!");

            WebElement productPrice = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[2]/div[3]"));
            Assertions.assertTrue(productPrice.isDisplayed(),"Product Price did not display");
            String ppriceText = productPrice.getText().replace("$", "");
            double pprice = Double.parseDouble(ppriceText);

            WebElement productTotalPrice = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[2]/div[5]"));
            Assertions.assertTrue(productTotalPrice.isDisplayed(),"Product Total Price did not display");
            test.pass("Product Total Price displayed!");
            String priceText = productTotalPrice.getText().replace("$", "");
            double old_price = Double.parseDouble(priceText);

            minusButton.click();
            test.pass("Click minus button successfylly!");

            priceText = productTotalPrice.getText().replace("$", "");
            double new_price = Double.parseDouble(priceText);
            System.out.println(new_price);
            System.out.println(old_price);
            System.out.println(new_price-old_price);
            System.out.println(pprice);
            if (new_price - (old_price - pprice)<epsilon){
                test.pass("product total price was updated!");
            }else test.fail("product total price wasn't updated!");

        }catch (Exception e){
            test.fail(e.getMessage());
        }
    }
    @ParameterizedTest
    @ValueSource(strings = {"chrome","edge","firefox"})
    public void checkEnterBox(String browser){
        try {
            double epsilon = 1e-9;
            driver = BrowserFactory.getDriver(browser);
            driver.get("http://localhost:3000/cart");
            test.info("Check Enter add voucher box");

            WebElement addCodeBox = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[6]/div/div[1]/div/input"));
            Assertions.assertTrue(addCodeBox.isDisplayed(),"Add Voucher Code Box did not display");
            test.pass("Add Voucher Code Box displayed!");

            addCodeBox.sendKeys("MT22KH01");
            String text = addCodeBox.getAttribute("value");
            if (text.equals("MT22KH01")){
                test.pass("Enter code in box successfully!");
            }else {
                test.fail("Enter code in box unsuccessfully!");
            }

        }catch (Exception e){
            test.fail(e.getMessage());
        }
    }
    @ParameterizedTest
    @ValueSource(strings = {"chrome","edge","firefox"})
    public void checkEnterBox100times(String browser){
        try {
            double epsilon = 1e-9;
            driver = BrowserFactory.getDriver(browser);
            driver.get("http://localhost:3000/cart");
            test.info("Check Enter add voucher box 100 times");

            WebElement addCodeBox = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[6]/div/div[1]/div/input"));
            Assertions.assertTrue(addCodeBox.isDisplayed(),"Add Voucher Code Box did not display");
            test.pass("Add Voucher Code Box displayed!");

            for(int i=0; i<100;i++){
                addCodeBox.sendKeys("MT22KH01");
                addCodeBox.clear();
            }

        }catch (Exception e){
            test.fail(e.getMessage());
        }
    }
    @ParameterizedTest
    @ValueSource(strings = {"chrome","edge","firefox"})
    public void checkOKButton(String browser){
        try {
            double epsilon = 1e-9;
            driver = BrowserFactory.getDriver(browser);
            driver.get("http://localhost:3000/cart");
            test.info("Check click add voucher button");

            WebElement addVoucherButton = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[6]/div/div[1]/div/button"));
            Assertions.assertTrue(addVoucherButton.isDisplayed(),"Add Voucher Button did not display");
            test.pass("Add Voucher Button displayed!");

            addVoucherButton.click();
            test.pass("Click add voucher button successfully!");

        }catch (Exception e){
            test.fail(e.getMessage());
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"chrome","edge","firefox"})
    public void checkCheckOutButton(String browser){
        try {
            double epsilon = 1e-9;
            driver = BrowserFactory.getDriver(browser);
            driver.get("http://localhost:3000/cart");
            test.info("Check Check Out button");

            WebElement checkOutButton = driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/div[7]/button"));
            Assertions.assertTrue(checkOutButton.isDisplayed(),"Check Out Button did not display");
            test.pass("Check Out Button displayed!");

            checkOutButton.click();
            test.pass("Click check out button successfully!");

            String currentUrl = driver.getCurrentUrl();
            String expectedUrl = "http://localhost:3000/confirmaddress";

            if (currentUrl.equals(expectedUrl)) {
                test.pass("Page changed successfully!");
            } else {
                test.fail("Page unchanged!");
            }

        }catch (Exception e){
            test.fail(e.getMessage());
        }
    }


     */

}
