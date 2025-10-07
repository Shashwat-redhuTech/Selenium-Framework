package testComponents;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReport {

    /**
     * This static method configures and returns an ExtentReports object.
     * It sets up the report's location, look and feel, and basic information.
     * @return A configured ExtentReports object.
     */
    public static ExtentReports getConfig() {
        
        // 1. Set the path where the report will be saved
        String path = System.getProperty("user.dir") + "\\reports\\index.html";
        
        // 2. Create the ExtentSparkReporter object (this configures the HTML report)
        ExtentSparkReporter reporter = new ExtentSparkReporter(path);
        reporter.config().setReportName("Web Automation Results");
        reporter.config().setDocumentTitle("Test Results");
        
        // 3. Create the main ExtentReports object and attach the reporter
        ExtentReports extent = new ExtentReports();
        extent.attachReporter(reporter);
        
        // 4. Add system information to the report
        extent.setSystemInfo("Tester", "Shashank"); // You can change this to your name
        
        // 5. Return the configured object
        return extent;
    }
}