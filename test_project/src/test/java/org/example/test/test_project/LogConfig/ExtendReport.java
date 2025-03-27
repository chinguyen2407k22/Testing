package org.example.test.test_project.LogConfig;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ExtendReport {
    private static ExtentReports extent;

    public static ExtentReports getInstance() {
        if (extent == null) {
            // Format ngày tháng
            String timestamp = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            String logPath = "./src/test/java/org/example/test/test_project/Logs/Sprint3/TestReport_" + timestamp + ".html";

            ExtentSparkReporter reporter = new ExtentSparkReporter(logPath);
            reporter.config().setReportName("Automation Test Report - " + timestamp);

            extent = new ExtentReports();
            extent.attachReporter(reporter);
        }
        return extent;
    }

    public static void closeReport() {
        if (extent != null) {
            extent.flush();
        }
    }
}
