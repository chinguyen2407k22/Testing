package org.example.test.test_project.API_testing;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.example.test.test_project.LogConfig.ExtendReport;
import org.junit.jupiter.api.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class CommentAndRatingAPI {
    private static final String BASE_URL = "http://localhost:8080/api/ratings";
    private static ExtentReports extent;
    private ExtentTest test;
    private String token;

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
    void testGetAllRatings_ShouldReturnListOrEmpty() {
        try {
            Response response =
                    given()
                            .contentType(ContentType.JSON)
                            .when()
                            .get(BASE_URL)
                            .then()
                            .statusCode(200)
                            .contentType(ContentType.JSON)
                            .extract().response();
            test.pass("Get list of commend and rating successfully!");

            if (response.jsonPath().getList("$").isEmpty()) {
                test.pass("List is null!");
            } else {
                test.pass("List has at least one element!");
                response.then()
                        .body("$", not(empty()))
                        .body("[0].id", notNullValue())
                        .body("[0].bookId", notNullValue())
                        .body("[0].userId", notNullValue());
                test.pass("Check first element successfully!");

            }
        }catch (Exception e) {
            test.fail(e.getMessage());
        }

    }
    @Test
    void testAddRating_Success() {
        try {
            String requestBody = """
            {
                "username": "usernamegg3",
                "password": "password5"
            }
            """;

            Response response = given()
                    .contentType(ContentType.JSON)
                    .body(requestBody)
                    .when()
                    .post("http://localhost:8080/api/login")
                    .then()
                    .statusCode(200)
                    .body("token", notNullValue()) // Kiểm tra token tồn tại
                    .extract()
                    .response();

            token = response.jsonPath().getString("token");
            //System.out.println("Token: " + token);
            //System.out.println(token);
            requestBody = """
            {
                "bookId": 6,
                "userId": 15,
                "rating": 4.1,
                "comment": "Sách rất hay!"
            }
            """;
            test.pass("Add new comment successfully!");

            given()
                    .contentType(ContentType.JSON)
                    .header("Authorization", "Bearer " + token)
                    .body(requestBody)
                    .when()
                    .post(BASE_URL+"/add")
                    .then()
                    .statusCode(200)
                    .body("id", greaterThan(0));
            test.pass("Add new rating and comment successfully");
        }catch (Exception e) {
            test.fail(e.getMessage());
        }

    }


    @Test
    void testAddRating_MissingField() {
        try {
            String requestBody = """
            {
                "username": "username1",
                "password": "password1"
            }
            """;

            Response response = given()
                    .contentType(ContentType.JSON)
                    .body(requestBody)
                    .when()
                    .post("http://localhost:8080/api/login")
                    .then()
                    .statusCode(200)
                    .body("token", notNullValue()) // Kiểm tra token tồn tại
                    .extract()
                    .response();

            token = response.jsonPath().getString("token");
            System.out.println("Token: " + token);
            //System.out.println(token);
            requestBody = """
            {
                "bookId": 8,
                "rating": 4.5,
                "comment": "Sách rất hay!"
            }
            """;

            given()
                    .contentType(ContentType.JSON)
                    .header("Authorization", "Bearer " + token)
                    .body(requestBody)
                    .when()
                    .post(BASE_URL+"/add")
                    .then()
                    .statusCode(403);
            test.pass("Add new rating and comment unsuccessfully because of lack of userId");
        }catch (Exception e) {
            test.fail(e.getMessage());
        }

    }

    @Test
    void testAddRating_MissingToken() {
        try {
            String requestBody = """
            {
                "bookId": 8,
                "userId":15,
                "rating": 4.5,
                "comment": "Sách rất hay!"
            }
            """;

            given()
                    .contentType(ContentType.JSON)
                    .header("Authorization", "Bearer " + token)
                    .body(requestBody)
                    .when()
                    .post(BASE_URL+"/add")
                    .then()
                    .statusCode(403);
            test.pass("Add new rating and comment unsuccessfully becase of lack of Token");
        }catch (Exception e) {
            test.fail(e.getMessage());
        }
        }

    @Test
    void testAddRating_Repeat() {
        try {
            String requestBody = """
            {
                "username": "username1",
                "password": "password1"
            }
            """;

            Response response = given()
                    .contentType(ContentType.JSON)
                    .body(requestBody)
                    .when()
                    .post("http://localhost:8080/api/login")
                    .then()
                    .statusCode(200)
                    .body("token", notNullValue()) // Kiểm tra token tồn tại
                    .extract()
                    .response();

            token = response.jsonPath().getString("token");
            System.out.println("Token: " + token);
            //System.out.println(token);
            requestBody = """
            {
                "bookId": 2,
                "userId": 2,
                "rating": 4.5,
                "comment": "Sách rất hay!"
            }
            """;
            test.pass("Add new comment successfully!");

            given()
                    .contentType(ContentType.JSON)
                    .header("Authorization", "Bearer " + token)
                    .body(requestBody)
                    .when()
                    .post(BASE_URL+"/add")
                    .then()
                    .statusCode(403);
            test.pass("Add new rating and comment unsuccessfully because user has already rated that book!");
        }catch (Exception e) {
            test.fail(e.getMessage());
        }

    }
    @Test
    void testGetAverageRating_successfully() {
        try {
            long validBookId = 2;

            double averageRating = given()
                    .contentType(ContentType.JSON)
                    .when()
                    .get(BASE_URL+"/average/"+validBookId)
                    .then()
                    .statusCode(200)
                    .extract().as(Double.class);

            Assertions.assertTrue(averageRating > 0.0, "Average rating should be greater than 0.0");
            test.pass("Avarage rating of book with id exists > 0.0");
        }catch (Exception e) {
            test.fail(e.getMessage());
        }

    }
    @Test
    void testGetAverageRating_zero() {
        try {
            long validBookId = 99999;

            double averageRating = given()
                    .contentType(ContentType.JSON)
                    .when()
                    .get(BASE_URL+"/average/"+validBookId)
                    .then()
                    .statusCode(200)
                    .extract().as(Double.class);

            Assertions.assertEquals(0.0, averageRating);
            test.pass("Avarage rating of book with id not exist = 0.0");
        }catch (Exception e) {
            test.fail(e.getMessage());
        }

    }
    @Test
    void testUpdateRating_Success() {
        try {
            int id =5;
            String requestBody = """
            {
                "username": "usernamegg3",
                "password": "password5"
            }
            """;

            Response response = given()
                    .contentType(ContentType.JSON)
                    .body(requestBody)
                    .when()
                    .post("http://localhost:8080/api/login")
                    .then()
                    .statusCode(200)
                    .body("token", notNullValue()) // Kiểm tra token tồn tại
                    .extract()
                    .response();

            token = response.jsonPath().getString("token");
            System.out.println("Token: " + token);
            //System.out.println(token);
            requestBody = """
            {
                "bookId": 2,
                "userId": 15,
                "rating": 4.0,
                "comment": "Sách rất hay!"
            }
            """;
            test.pass("Update comment successfully!");

            given()
                    .contentType(ContentType.JSON)
                    .header("Authorization", "Bearer " + token)
                    .body(requestBody)
                    .when()
                    .put(BASE_URL+"/update/" + id)
                    .then()
                    .statusCode(200)
                    .body("id", greaterThan(0));
            test.pass("Update new rating and comment successfully for rating id 1");
        }catch (Exception e) {
            test.fail(e.getMessage());
        }

    }
    @Test
    void testUpdateRating_BookNotExist() {
        try {
            int id = 2;
            String requestBody = """
            {
                "username": "username1",
                "password": "password1"
            }
            """;

            Response response = given()
                    .contentType(ContentType.JSON)
                    .body(requestBody)
                    .when()
                    .post("http://localhost:8080/api/login")
                    .then()
                    .statusCode(200)
                    .body("token", notNullValue()) // Kiểm tra token tồn tại
                    .extract()
                    .response();

            token = response.jsonPath().getString("token");
            System.out.println("Token: " + token);
            //System.out.println(token);
            requestBody = """
            {
                "rating": 4.0,
                "comment": "Sách rất hay!"
            }
            """;
            test.pass("Add new comment successfully!");

            given()
                    .contentType(ContentType.JSON)
                    .header("Authorization", "Bearer " + token)
                    .body(requestBody)
                    .when()
                    .put(BASE_URL+"/update/" + id)
                    .then()
                    .statusCode(404);
            test.pass("Update new rating and comment unsuccessfully for rating with id not exist!");
        }catch (Exception e) {
            test.fail(e.getMessage());
        }

    }
    @Test
    void testUpdateRating_WrongToken() {
        try {
            int id = 5;
            String requestBody = """
            {
                "username": "username1",
                "password": "password1"
            }
            """;

            Response response = given()
                    .contentType(ContentType.JSON)
                    .body(requestBody)
                    .when()
                    .post("http://localhost:8080/api/login")
                    .then()
                    .statusCode(200)
                    .body("token", notNullValue()) // Kiểm tra token tồn tại
                    .extract()
                    .response();

            token = response.jsonPath().getString("token");
            System.out.println("Token: " + token);
            //System.out.println(token);
            requestBody = """
            {
                "rating": 4.0,
                "comment": "Sách rất hay!"
            }
            """;
            test.pass("Add new comment successfully!");

            given()
                    .contentType(ContentType.JSON)
                    .header("Authorization", "Bearer " + token)
                    .body(requestBody)
                    .when()
                    .put(BASE_URL+"/update/" + id)
                    .then()
                    .statusCode(403);
            test.pass("Update new rating and comment unsuccessfully because of lack of token!");
        }catch (Exception e) {
            test.fail(e.getMessage());
        }

    }
    @Test
    void testDeleteRating_Success() {
        try {
            int id = 9;
            String requestBody = """
            {
                "username": "username1",
                "password": "password1"
            }
            """;

            Response response = given()
                    .contentType(ContentType.JSON)
                    .body(requestBody)
                    .when()
                    .post("http://localhost:8080/api/login")
                    .then()
                    .statusCode(200)
                    .body("token", notNullValue()) // Kiểm tra token tồn tại
                    .extract()
                    .response();

            token = response.jsonPath().getString("token");
            System.out.println("Token: " + token);
            //System.out.println(token);

            given()
                    .contentType(ContentType.JSON)
                    .header("Authorization", "Bearer " + token)
                    .when()
                    .delete(BASE_URL+"/"+id)
                    .then()
                    .statusCode(200)
                    .body("id", greaterThan(0));
            test.pass("Delete rating and comment successfully");
        }catch (Exception e) {
            test.fail(e.getMessage());
        }

    }
    @Test
    void testDeleteRating_Idnotexist() {
        try {
            int id = 2;
            String requestBody = """
            {
                "username": "username1",
                "password": "password1"
            }
            """;

            Response response = given()
                    .contentType(ContentType.JSON)
                    .body(requestBody)
                    .when()
                    .post("http://localhost:8080/api/login")
                    .then()
                    .statusCode(200)
                    .body("token", notNullValue()) // Kiểm tra token tồn tại
                    .extract()
                    .response();

            token = response.jsonPath().getString("token");
            System.out.println("Token: " + token);
            //System.out.println(token);

            given()
                    .contentType(ContentType.JSON)
                    .header("Authorization", "Bearer " + token)
                    .when()
                    .delete(BASE_URL+"/"+id)
                    .then()
                    .statusCode(404);
            test.pass("Delete rating and comment unsuccessfully because rating with id do not exist.");
        }catch (Exception e) {
            test.fail(e.getMessage());
        }

    }
    @Test
    void testDeleteRating_Wrongtoken() {
        try {
            int id = 9;
            String requestBody = """
            {
                "username": "usernamegg3",
                "password": "password5"
            }
            """;

            Response response = given()
                    .contentType(ContentType.JSON)
                    .body(requestBody)
                    .when()
                    .post("http://localhost:8080/api/login")
                    .then()
                    .statusCode(200)
                    .body("token", notNullValue()) // Kiểm tra token tồn tại
                    .extract()
                    .response();

            token = response.jsonPath().getString("token");
            System.out.println("Token: " + token);
            //System.out.println(token);

            given()
                    .contentType(ContentType.JSON)
                    .header("Authorization", "Bearer " + token)
                    .when()
                    .delete(BASE_URL+"/"+id)
                    .then()
                    .statusCode(403);
            test.pass("Delete rating and comment unsuccessfully because wrong token.");
        }catch (Exception e) {
            test.fail(e.getMessage());
        }

    }
    @Test
    void testGetTopRatings_Success() {
        try {
            int validBookId = 2;
            given()
                    .contentType(ContentType.JSON)
                    .when()
                    .get(BASE_URL + "/" + validBookId + "/top/3")
                    .then()
                    .statusCode(200)
                    .body("size()", lessThanOrEqualTo(3))
                    .body("[0].rating", greaterThanOrEqualTo(0))
                    .body("[0].bookId", equalTo(validBookId));
            test.pass("Get top 3 rating of book successfully!");

        }catch (Exception e) {
            test.fail(e.getMessage());
        }

    }
    @Test
    void testGetTopRatings_unSuccess() {
        try {
            int validBookId = 1;
            given()
                    .contentType(ContentType.JSON)
                    .when()
                    .get(BASE_URL + "/" + validBookId + "/top/3")
                    .then()
                    .statusCode(200)
                    .body("size()", lessThanOrEqualTo(0));

            test.pass("Get top 3 rating of book unsuccessfully");

        }catch (Exception e) {
            test.fail(e.getMessage());
        }

    }



    @AfterAll
    public static void closeTest(){
        ExtendReport.closeReport();
    }
}
