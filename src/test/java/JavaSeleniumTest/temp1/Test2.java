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

import JavaSeleniumTest.pageObject.CartPage;
import JavaSeleniumTest.pageObject.CheckOutPage;
import JavaSeleniumTest.pageObject.ConfirmationPage;
import JavaSeleniumTest.pageObject.LandingPage;
import JavaSeleniumTest.pageObject.ProductCatalog;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Test2 {
	
	public static void main(String[]args) throws InterruptedException
{
    String productName = "IPHONE 13 PRO";		
	WebDriverManager.chromedriver().setup();
	WebDriver driver = new ChromeDriver();	
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
	//driver.manage().window().maximize();
	
	
	///Login//**
	LandingPage landingPage = new LandingPage(driver);
	landingPage.goTo();
	//landingPage.loginApp("anshika@gmail.com","Iamking@000");
	 
	//Check initial list//**
	//List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
	
	//ProductCatalog prodCat = new ProductCatalog(driver);
	
	ProductCatalog prodCat = landingPage.loginApp("anshika@gmail.com","Iamking@000");
	
	List<WebElement> products = prodCat.getProductList();
	
	//Search and select desire product//** Add product to Cart
	prodCat.addProducToCart(productName);

	//Go to cart screen//
	//driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
	CartPage cartPage = prodCat.goToCartPage();
	
	//Verify Elements presents in the screen list//
	//CartPage cartPage = new CartPage(driver);
	Boolean match = cartPage.VerifyProductDisplay(productName);
	Assert.assertTrue(match);
	//List <WebElement> cartProducts =  driver.findElements(By.cssSelector(".cartSection h3"));
	//Boolean match = cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));
	//Assert.assertTrue(match);
	
	CheckOutPage checkoutPage =  cartPage.goToCheckout();
	checkoutPage.selectCountry("India");
	//driver.findElement(By.cssSelector(".totalRow button")).click();	
	
	//Enter Country///
	//Actions a = new Actions(driver);
	//a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "india").build().perform();
	
	//WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
	//wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));	
	
	//driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();
	
	//driver.findElement(By.cssSelector(".action__submit")).click();
	ConfirmationPage confirmationPage = checkoutPage.submitOrder();
	
	//Verify confirmation message///
	String confirmationmessage = confirmationPage.getConfirmationMsg();
	//String confirmationmessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
	Assert.assertTrue(confirmationmessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	System.out.println(confirmationmessage);
	driver.close();
}

}
