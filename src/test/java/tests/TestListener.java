// package tests;

// import com.aventstack.extentreports.ExtentReports;
// import com.aventstack.extentreports.ExtentTest;
// import com.aventstack.extentreports.Status;

// import org.openqa.selenium.WebDriver;

// import org.testng.ITestListener;
// import org.testng.ITestResult;

// import utils.ExtentReportManager;
// import utils.ScreenshotUtils;

// public class TestListener
//         implements ITestListener {


//     private ExtentReports extent =
//             ExtentReportManager
//                     .getReportInstance();


//     private ThreadLocal<ExtentTest> test =
//             new ThreadLocal<>();


// @Override
// public void onTestStart(ITestResult result) {

//     Object[] parameters =
//             result.getParameters();

//     String testCaseId =
//             "Unknown Test Case";

//     if (parameters.length > 0) {

//         testCaseId =
//                 String.valueOf(parameters[0]);
//     }

//     ExtentTest extentTest =
//             extent.createTest(testCaseId);

//     extentTest.info(
//             "Test Method: " +
//             result.getMethod().getMethodName()
//     );

//     test.set(extentTest);
// }

//     @Override
//     public void onTestSuccess(
//             ITestResult result) {

//         test.get().log(
//                 Status.PASS,
//                 "Test passed"
//         );
//     }


//     @Override
//     public void onTestFailure(
//             ITestResult result) {

//         test.get().log(
//                 Status.FAIL,
//                 "Test failed"
//         );


//         test.get().log(
//                 Status.FAIL,
//                 result.getThrowable()
//         );


//         Object instance =
//                 result.getInstance();


//         if (instance instanceof base.BaseTest) {

//             WebDriver driver =
//                     ((base.BaseTest) instance)
//                             .getDriver();


//             if (driver != null) {

//                 String screenshotPath =
//                         ScreenshotUtils
//                                 .takeScreenshot(

//                                         driver,

//                                         result.getMethod()
//                                                 .getMethodName()
//                                 );


//                 if (screenshotPath != null) {

//                     test.get()
//                             .addScreenCaptureFromPath(
//                                     screenshotPath
//                             );
//                 }
//             }
//         }
//     }


//     @Override
//     public void onTestSkipped(
//             ITestResult result) {

//         test.get().log(
//                 Status.SKIP,
//                 "Test skipped"
//         );
//     }


//     @Override
//     public void onFinish(
//             org.testng.ITestContext context) {

//         extent.flush();

//         test.remove();
//     }
// }


package tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import org.openqa.selenium.WebDriver;

import org.testng.ITestListener;
import org.testng.ITestResult;

import utils.ExtentReportManager;
import utils.ScreenshotUtils;

public class TestListener implements ITestListener {

    // Extent Report instance
    private ExtentReports extent =
            ExtentReportManager.getReportInstance();

    // Keeps ExtentTest for current test
    private ThreadLocal<ExtentTest> test =
            new ThreadLocal<>();


    // =====================================================
    // 1. WHEN TEST STARTS
    // =====================================================

    @Override
    public void onTestStart(ITestResult result) {

        String testCaseId = "Unknown Test Case";

        Object[] parameters =
                result.getParameters();

        // If test has parameters
        if (parameters.length > 0) {

            testCaseId =
                    String.valueOf(parameters[0]);
        }

        // Create test in Extent Report
        ExtentTest extentTest =
                extent.createTest(testCaseId);

        // Add test method name
        extentTest.info(
                "Test Method: "
                + result.getMethod().getMethodName()
        );

        // Store current test
        test.set(extentTest);
    }


    // =====================================================
    // 2. WHEN TEST PASSES
    // =====================================================

    @Override
    public void onTestSuccess(
            ITestResult result) {

        test.get().log(
                Status.PASS,
                "Test passed"
        );
    }


    // =====================================================
    // 3. WHEN TEST FAILS
    // =====================================================

    @Override
    public void onTestFailure(
            ITestResult result) {

        // Log failure
        test.get().log(
                Status.FAIL,
                "Test failed"
        );

        // Log actual exception
        test.get().log(
                Status.FAIL,
                result.getThrowable()
        );


        // Get test class object
        Object instance =
                result.getInstance();


        // Check whether test extends BaseTest
        if (instance instanceof base.BaseTest) {

            WebDriver driver =
                    ((base.BaseTest) instance)
                            .getDriver();


            // Take screenshot
            if (driver != null) {

                String screenshotPath =
                        ScreenshotUtils.takeScreenshot(
                                driver,
                                result.getMethod()
                                        .getMethodName()
                        );


                // Attach screenshot
                if (screenshotPath != null) {

                    test.get()
                            .addScreenCaptureFromPath(
                                    screenshotPath
                            );
                }
            }
        }
    }


    // =====================================================
    // 4. WHEN TEST IS SKIPPED
    // =====================================================

    @Override
    public void onTestSkipped(
            ITestResult result) {

        test.get().log(
                Status.SKIP,
                "Test skipped"
        );
    }


    // =====================================================
    // 5. WHEN ALL TESTS FINISH
    // =====================================================

    @Override
    public void onFinish(
            org.testng.ITestContext context) {

        // Save Extent Report
        extent.flush();

        // Remove ThreadLocal
        test.remove();
    }
}