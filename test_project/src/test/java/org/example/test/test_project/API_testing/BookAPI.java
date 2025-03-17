package org.example.test.test_project.API_testing;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.example.test.test_project.LogConfig.ExtendReport;
import org.junit.jupiter.api.*;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class BookAPI {
    private static final String BASE_URL = "http://localhost:8080/api/books";
    private static ExtentReports extent;
    private ExtentTest test;

    @BeforeAll
    static void setup() {
        RestAssured.baseURI = BASE_URL;
        extent = ExtendReport.getInstance();
    }
    @BeforeEach
    public void setup(TestInfo testInfo) {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        test = extent.createTest(testInfo.getDisplayName() + " - " + timestamp);
    }

    @Test
    public void testGetAllBooksWithPagination() {
        try {
            Response response = given()
                    .contentType(ContentType.JSON)
                    .queryParam("page", 0)
                    .queryParam("size", 6)
                    .when()
                    .get(BASE_URL)
                    .then()
                    .statusCode(200)
                    .contentType(ContentType.JSON)
                    .body("content.size()", greaterThanOrEqualTo(0))
                    .extract()
                    .response();
            test.pass("Get all books API call successful!");

            if (response.jsonPath().getList("content").size() > 0) {
                given()
                        .when()
                        .get(BASE_URL)
                        .then()
                        .body("content[0].id", notNullValue())
                        .body("content[0].name", not(emptyString()))
                        .body("content[0].author", not(emptyString()))
                        .body("content[0].price", greaterThan(0F))
                        .body("totalElements", greaterThan(0))
                        .body("pageable.pageSize", equalTo(6));
                test.pass("First Book's format true!");
            }
        }catch (Exception e) {
            test.fail(e.getMessage());
        }
    }
    @Test
    public void testGetBookById_Success() {
        try {
            int bookId = 2;

            given()
                    .contentType(ContentType.JSON)
                    .when()
                    .get(BASE_URL + "/" + bookId)
                    .then()
                    .statusCode(200)
                    .contentType(ContentType.JSON)
                    .body("id", equalTo(bookId))
                    .body("title", not(emptyString()))
                    .body("author", not(emptyString()))
                    .body("category", not(emptyString()))
                    .body("price", greaterThan(0F));
            test.pass("Get book with id "+bookId+" sucessfully!");

        }catch (Exception e) {
            test.fail(e.getMessage());
        }

    }

    @Test
    public void testGetBookById_NotFound() {
        try {
            int nonExistingBookId = 10;

            given()
                    .contentType(ContentType.JSON)
                    .when()
                    .get(BASE_URL + "/" + nonExistingBookId)
                    .then()
                    .statusCode(200)
                    .body(is(emptyOrNullString()));
            test.pass("Get book with non exitsing id "+nonExistingBookId+"unsucessfully!");
        }catch (Exception e) {
            test.fail(e.getMessage());
        }

    }

    @Test
    public void testCreateBook_Success() {
        try {
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
                    .post(BASE_URL)
                    .then()
                    .statusCode(200);
            test.pass("Add a book successfully!");
        }catch (Exception e) {
            test.fail(e.getMessage());
        }

    }
    @Test
    public void testGetCategories(){
        try {
            given().contentType(ContentType.JSON)
                    .when()
                    .get(BASE_URL + "/categories")
                    .then()
                    .statusCode(200);
            test.pass("Get categories successfully!");
        }catch (Exception e) {
            test.fail(e.getMessage());
        }
    }
    @Test
    public void testCreateBook_LackofImage() {
        try {
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
                    //.multiPart("image", testImage)
                    .contentType(ContentType.MULTIPART)
                    .when()
                    .post(BASE_URL)
                    .then()
                    .statusCode(403);
            test.pass("Add a book without image field unsuccessfully!");
        }catch (Exception e) {
            test.fail(e.getMessage());
        }

    }
    @Test
    public void testUpdateBook() {
    try {
        int bookId = 6;

        String updatedBookJson = """
        {
            "rating": 4.8,
            "author": "Mario Puzo (Updated)"
        }
        """;

        given()
                .contentType(ContentType.JSON)
                .body(updatedBookJson)
                .when()
                .put(BASE_URL + "/" + bookId)
                .then()
                .statusCode(200);
        test.pass("Update a book successfully!");
    }catch (Exception e) {
        test.fail(e.getMessage());
    }

    }

    @Test
    public void testUpdateBookFail() {
        try {
            int bookId = 10;

            String updatedBookJson = """
        {
            "rating": 4.8,
            "author": "Mario Puzo (Updated)"
        }
        """;
            given()
                    .contentType(ContentType.JSON)
                    .body(updatedBookJson)
                    .when()
                    .put(BASE_URL + "/" + bookId)
                    .then()
                    .statusCode(404);
            test.pass("Update a book with non existing id unsuccessfully!");
        }catch (Exception e) {
            test.fail(e.getMessage());
        }

    }
    @Test
    public void testDeleteBook() {
        try {
            int bookId = 7; // ID sách cần xóa, đảm bảo sách này tồn tại trong DB

            given()
                    .when()
                    .delete(BASE_URL + "/" + bookId)
                    .then()
                    .statusCode(200);
            test.pass("Delete a book successfully!");
        }catch (Exception e) {
            test.fail(e.getMessage());
        }

    }
    @Test
    public void testDeleteBookFail() {
        try {
            int bookId = 10; // ID sách cần xóa, đảm bảo sách này tồn tại trong DB

            given()
                    .when()
                    .delete(BASE_URL + "/" + bookId)
                    .then()
                    .statusCode(404);
            test.pass("Delete a book with non existing id unsuccessfully!");
        }catch (Exception e) {
            test.fail(e.getMessage());
        }

    }

    @AfterAll
    public static void closeTest(){
        ExtendReport.closeReport();
    }

}
