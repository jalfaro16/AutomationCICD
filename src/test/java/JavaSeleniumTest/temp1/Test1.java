package JavaSeleniumTest.temp1;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Test1 {
	
	public static void main(String[]args) throws InterruptedException
{
    String productName = "IPHONE 13 PRO";		
	WebDriverManager.chromedriver().setup();
	WebDriver driver = new ChromeDriver();	
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
	//driver.manage().window().maximize();
	driver.get("https://rahulshettyacademy.com/client");
	
	
	///Login
	driver.findElement(By.id("userEmail")).sendKeys("anshika@gmail.com");
	driver.findElement(By.id("userPassword")).sendKeys("Iamking@000");
	driver.findElement(By.id("login")).click();
	
	//Check initial list
	List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
	
	//Search and select desire product
	WebElement prod = products.stream().filter(product->
	product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
	//Thread.sleep(3000);
	prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
	//driver.findElement(By.xpath("//h5[b[normalize-space()='IPHONE 13 PRO']]/ancestor::div[contains(@class, 'card')]//button[contains(., 'Add To Cart')]")).click();
	
	WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));	
	wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animation")));	
	
	//Go to cart screen
	driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
	
	//Verify Elements presents in the screen list
	List <WebElement> cartProducts =  driver.findElements(By.cssSelector(".cartSection h3"));
	Boolean match = cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));
	Assert.assertTrue(match);
	
	driver.findElement(By.cssSelector(".totalRow button")).click();	
	
	//Enter Country
	Actions a = new Actions(driver);
	a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "india").build().perform();
	
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));	
	
	driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();
	driver.findElement(By.cssSelector(".action__submit")).click();
	
	//Verify confirmation message
	String confirmationmessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
	Assert.assertTrue(confirmationmessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	System.out.println(confirmationmessage);
	driver.close();
}

}
