import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Listener implements ITestListener{

    private static final ExtentReports extent = ExtentManager.createInstance("report.html");

    private static ThreadLocal<ExtentTest> methodTest = new ThreadLocal<>();

    private ExtentTest getTest(ITestResult result){
        return methodTest.get();
    }

    @Override
    public synchronized void onTestStart(ITestResult result) {
        String methodName = result.getMethod().getMethodName();
        ExtentTest test = extent.createTest(methodName);
        methodTest.set(test);
    }

    @Override
    public synchronized void onTestFailure(ITestResult result) {
        DateFormat dateFormat = new SimpleDateFormat("MMM/dd/yyyy/hh:mm aaa");
        Date date = new Date();
        String name = dateFormat.format(date);
        File file = ((TakesScreenshot) Base.driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(file, new File("src/test/java/screenshots/failedTests" + name +".png"));
        } catch (IOException e) {

        }
//       getTest(result).fail(result.getThrowable());
        getTest(result).fail("Test failed due to: " + result.getThrowable().getMessage());
        extent.flush();
    }

    @Override
    public synchronized void onTestSuccess(ITestResult result) {
        getTest(result).pass("Test passed");
        extent.flush();
    }


}
