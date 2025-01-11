package Resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterTNG {
	
	public static ExtentReports getReportObject()
	{
	String path = System.getProperty("user.dir")+"\\reports\\index.html";
	ExtentSparkReporter reporter = new ExtentSparkReporter(path);
	reporter.config().setReportName("Jason Report Results");
	reporter.config().setDocumentTitle("Jason Title Results");
	
	ExtentReports spark = new ExtentReports();
	spark.attachReporter(reporter);
	spark.setSystemInfo("Tester","Jason Alfaro");
	//spark.createTest(path);
	
	return spark;
	}
}
