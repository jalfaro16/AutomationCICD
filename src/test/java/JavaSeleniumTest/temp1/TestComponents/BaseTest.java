package JavaSeleniumTest.temp1.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import JavaSeleniumTest.pageObject.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	
	public WebDriver driver;
	public LandingPage landingPage;
	private String urlName;
	private String product1;
	private String confMsg; 
	
	//expectedConfMsg="THANKYOU FOR THE ORDER.";
	//String productName = "IPHONE 13 PRO";	
	
	@BeforeMethod(alwaysRun=true)
	public LandingPage launchApp() throws IOException
	{
		driver = initializerDriver();
		landingPage = new LandingPage(driver);
		landingPage.goTo(getUrlName());
		return landingPage;
	}
	
	public WebDriver initializerDriver() throws IOException
	{
		// properties class
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+ "//src//main//java//Resources/GlobalData.properties");
		prop.load(fis);			
		String browserName = System.getProperty("browser")!=null ? System.getProperty("browser"):prop.getProperty("browser"); 
		urlName = System.getProperty("url")!=null ? System.getProperty("url"):prop.getProperty("url");
		product1 = System.getProperty("productName")!=null ? System.getProperty("productName"):prop.getProperty("productName");
		confMsg = System.getProperty("expectedConfMsg")!=null ? System.getProperty("expectedConfMsg"):prop.getProperty("expectedConfMsg");
		
		if(browserName.contains("chrome"))
		{
		ChromeOptions options=new ChromeOptions();	
		WebDriverManager.chromedriver().setup();
		if(browserName.contains("headless"))
		{
			options.addArguments("headless");
		}
		driver = new ChromeDriver(options);	
		driver.manage().window().setSize(new Dimension(1440,900));
		}
		else if (browserName.equalsIgnoreCase("firefox"))
		{
		WebDriverManager.firefoxdriver().setup();
		driver = new FirefoxDriver();	
		}
		else if (browserName.equalsIgnoreCase("edge"))
		{
		WebDriverManager.edgedriver().setup();
		driver = new EdgeDriver();	
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		driver.manage().window().maximize();	
		return driver;
	}
	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException
	{
		//read json to string
		String jsonContent = FileUtils.readFileToString(new File(filePath),StandardCharsets.UTF_8);
		//string to HashMap
		ObjectMapper mapper = new ObjectMapper(); 
		List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>(){});
		return data;
	}
	
	 public String getproductName()
	 {
	        try {
				return product1;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	       return product1 ;
	 }
	 public String getconfMsg()
	 {
		 return confMsg;
	 }
	
     public String getUrlName()
     {
    	return urlName;
     }
    
	@AfterMethod(alwaysRun=true)
	public void tearDown()
	{
		driver.close();
	}
	
	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException
	{
		TakesScreenshot ts = (TakesScreenshot)driver; 
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir")+
				"\\reports\\" + testCaseName + ".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir")+
				"\\reports\\" + testCaseName + ".png";
	}
	
	
}
