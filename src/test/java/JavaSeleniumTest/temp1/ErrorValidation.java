package JavaSeleniumTest.temp1;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import JavaSeleniumTest.pageObject.CartPage;
import JavaSeleniumTest.pageObject.CheckOutPage;
import JavaSeleniumTest.pageObject.ConfirmationPage;
import JavaSeleniumTest.pageObject.ProductCatalog;
import JavaSeleniumTest.temp1.TestComponents.BaseTest;
import JavaSeleniumTest.temp1.TestComponents.Retry;

public class ErrorValidation extends BaseTest{
	
	@Test(groups= {"Purshase"},retryAnalyzer=Retry.class)
	public void LoginErrorValidation()
	{
		String Expected_msg = "Incorrect email or password.";
		//String productName = "IPHONE 13 PR";
		landingPage.loginApp("anshika@gmail.com","Iamking@0");
		
		Assert.assertEquals(Expected_msg, landingPage.getErrorMsg());
	}
	
	@Test(groups= {"ErrorVerification"})
	public void ProductErrorValidation()
	{
		String productName = "IPHONE 13 PRO";	
		ProductCatalog prodCat = landingPage
				.loginApp("anshika@gmail.com","Iamking@000");
		List<WebElement> products = prodCat.getProductList();	
		
		prodCat.addProducToCart(productName);
		
		CartPage cartPage = prodCat.goToCartPage();
		
		Boolean match = cartPage.VerifyProductDisplay(productName);
		Assert.assertTrue(match);

		
	}
	
}
