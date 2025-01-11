package JavaSeleniumTest.temp1.TestComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentReporter;

import Resources.ExtentReporterTNG;

public class Listeners extends BaseTest implements ITestListener {
	ExtentTest test;
	ExtentReports spark = ExtentReporterTNG.getReportObject();
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();
	
	@Override
	public void onTestStart(ITestResult result)
	{
		test = spark.createTest(result.getMethod().getMethodName());
		extentTest.set(test);
		//System.out.println("---- onTestStart ********");
		//System.out.println("Test Started: " + result.getName());
	}
	
	public void onTestSuccess(ITestResult result)
	{
		extentTest.get().log(Status.PASS, "Test Passed");
		//test.log(Status.PASS, "Test Passed");
		//System.out.println("---- onTestSuccess check from Listners******");
	}
	
	public void onTestFailure(ITestResult result)
	{
		extentTest.get().fail(result.getThrowable());
		
		//test.log(Status.FAIL, "Test Failed");
		String filePath=null;
		try {
			driver = (WebDriver) result.getTestClass().getRealClass()
					.getField("driver").get(result.getInstance());
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		try {
			filePath = getScreenshot(result.getMethod().getMethodName(),driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		extentTest.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
		
		//System.out.println("---- onTestFailure *******");
	}
	
	public void onFinish(ITestContext context)
	{
		spark.flush();
	}
	
	public void onTestSkipped(ITestResult result)
	{
		System.out.println("---- onTestSkipped ----");
	}

}
