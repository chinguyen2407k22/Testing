package org.example.test.test_project.API_testing;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItems;

public class BookDetail {
    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "http://localhost:8080";
    }

}
