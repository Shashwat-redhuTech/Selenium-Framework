package testComponents;


import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class Listeners implements ITestListener {
    
    // Initialize the report by calling your ExtentReport utility
    ExtentReports extent = ExtentReport.getConfig();
    ExtentTest test;

    // Use ThreadLocal to make test objects thread-safe for parallel execution
    private ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();

    @Override
    public void onTestStart(ITestResult result) {
        // This method is called when any @Test method starts
        test = extent.createTest(result.getMethod().getMethodName());
        extentTest.set(test); // Assign a unique thread-safe test object
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        // This method is called when a test passes
        extentTest.get().log(Status.PASS, "Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        // This method is called when a test fails
        extentTest.get().fail(result.getThrowable()); // Log the error
        
        // You can add code here to take a screenshot and attach it to the report
    }

    @Override
    public void onFinish(ITestContext context) {
        // This method is called when all tests in the suite are finished
        extent.flush(); // This command generates the final report
    }

    // We are not using these other ITestListener methods for now
    @Override
    public void onTestSkipped(ITestResult result) {}

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {}

    @Override
    public void onStart(ITestContext context) {}
}