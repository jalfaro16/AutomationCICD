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
import org.testng.annotations.BeforeMethod;
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

public class Test4 extends BaseTest   {
	
    String productName;
    String expectedConfMsg;
    
    @BeforeMethod(alwaysRun = true)
    public void setup() {
        productName = getproductName(); // Use inherited method
        expectedConfMsg = getconfMsg();
    }
    
	///Test #1 of Test4 Class - submitOrder
	@Test(dataProvider="getData",groups= {"Purchase"})
	public void submitOrder(HashMap<String,String>input) throws IOException
	{	
	ProductCatalog prodCat = landingPage.loginApp(input.get("username"),input.get("password"));
	//List<WebElement> products = prodCat.getProductList();	
	
	//Verify products List
	//for (WebElement product : products) {
	  //  System.out.println(product.getText());
	//}
    
	//Search and select desire product//** Add product to Cart
	prodCat.addProducToCart(input.get("productname"));
	
	//Go to CartPage
	CartPage cartPage = prodCat.goToCartPage();
	 
	//***CartPage***
	//Verify Elements presents in the screen list//
	Boolean match = cartPage.VerifyProductDisplay(input.get("productname"));
	Assert.assertTrue(match);
 

	CheckOutPage checkoutPage =  cartPage.goToCheckout();
	
	checkoutPage.selectCountry(input.get("country"));
	ConfirmationPage confirmationPage = checkoutPage.submitOrder();
	
	//Verify confirmation message///
	String confirmationmessage = confirmationPage.getConfirmationMsg();
	Assert.assertTrue(confirmationmessage.equalsIgnoreCase(expectedConfMsg));
	System.out.println(confirmationmessage);
	//driver.close();
    }
	
	///Test #2 of Test4 Class - OrderHistory
	//@Test(dependsOnMethods={"submitOrder"})
	@Test
	public void OrderHistory()
	{
		ProductCatalog prodCat = landingPage.loginApp("shetty@gmail.com","Iamking@000");
		OrderPage ordersPage = prodCat.goToOrderPage();
		Assert.assertTrue(ordersPage.VerifyOrderDisplay(productName));
	}
	
	@DataProvider
	public Object[][] getData() throws IOException
	{
		List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty
				("user.dir")+
				"//src//test//java//JavaSeleniumTest//data//PurshaseOrder.json");
		return new Object[][] {{data.get(0)},{data.get(1)} };
	}
	
}
