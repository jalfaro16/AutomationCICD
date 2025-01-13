
package JavaSeleniumTest.temp1;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import JavaSeleniumTest.pageObject.CartPage;
import JavaSeleniumTest.pageObject.CheckOutPage;
import JavaSeleniumTest.pageObject.ConfirmationPage;
import JavaSeleniumTest.pageObject.LandingPage;
import JavaSeleniumTest.pageObject.OrderPage;
import JavaSeleniumTest.pageObject.ProductCatalog;
import JavaSeleniumTest.temp1.TestComponents.BaseTest;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Test4 extends BaseTest {
	String productName = "ADIDAS ORIGINAL";	
	
	@Test(dataProvider="getData",groups= {"Purshase"})
	public void submitOrder(HashMap<String,String>input) throws IOException
	{	
    //LandingPage landingPage=launchApp();
	ProductCatalog prodCat = landingPage
			.loginApp(input.get("username"),input.get("password"));
	List<WebElement> products = prodCat.getProductList();	
	//Search and select desire product//** Add product to Cart
	prodCat.addProducToCart(input.get("productname"));
	
	//Go to cart screen//
	CartPage cartPage = prodCat.goToCartPage();
	
	//Verify Elements presents in the screen list//
	Boolean match = cartPage.VerifyProductDisplay(input.get("productname"));
	Assert.assertTrue(match);

	CheckOutPage checkoutPage =  cartPage.goToCheckout();
	checkoutPage.selectCountry(input.get("country"));
	ConfirmationPage confirmationPage = checkoutPage.submitOrder();
	
	//Verify confirmation message///
	String confirmationmessage = confirmationPage.getConfirmationMsg();
	Assert.assertTrue(confirmationmessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	System.out.println(confirmationmessage);
	//driver.close();
    }
	
	@Test(dependsOnMethods={"submitOrder"})
	public void OrderHistory()
	{
		ProductCatalog prodCat = landingPage
				.loginApp("shetty@gmail.com","Iamking@000");
		OrderPage ordersPage = prodCat.goToOrderPage();
		Assert.assertTrue(ordersPage.VerifyOrderDisplay(productName));
	    //It was just some water after the rain
}
	
	@DataProvider
	public Object[][] getData() throws IOException
	{
		/**HashMap<String,String> map = new HashMap<String,String>();
		map.put("password","Iamking@000");
		map.put("username","anshika@gmail.com");
		map.put("productname","IPHONE 13 PRO");
		map.put("country","India");

		HashMap<String,String> map2 = new HashMap<String,String>();
		map2.put("password","Iamking@000");
		map2.put("username","shetty@gmail.com");
		map2.put("productname","ADIDAS ORIGINAL");
		map2.put("country","India");
		return new Object[][] {{map},{map2} };
		//return new Object[][] {{"anshika@gmail.com","Iamking@000","IPHONE 13 PRO","India"}
		//,{"anshika@gmail.com","Iamking@000","ADIDAS ORIGINAL","India"}};
		*/
		List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty
				("user.dir")+
				"//src//test//java//JavaSeleniumTest//data//PurshaseOrder.json");
		return new Object[][] {{data.get(0)},{data.get(1)} };
	}
	
}
