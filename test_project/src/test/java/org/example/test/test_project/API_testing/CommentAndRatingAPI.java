package org.example.test.test_project.API_testing;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import io.restassured.RestAssured;
import org.example.test.test_project.LogConfig.ExtendReport;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CommentAndRatingAPI {
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

    @AfterAll
    public static void closeTest(){
        ExtendReport.closeReport();
    }
}
