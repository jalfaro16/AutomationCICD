package JavaSeleniumTest.pageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import AbstractComponents.Utilities;

public class CartPage extends Utilities {

	WebDriver driver;
	
	public CartPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	////Page Factory ////
	@FindBy(css=".totalRow button")
	WebElement checkoutEle;
	
	@FindBy(css=".cartSection h3")
	private List<WebElement> productTitles;
	
	////Methods of the Class.////
    public Boolean VerifyProductDisplay(String productName)
    {
    	Boolean match = productTitles.stream().anyMatch(product -> product
    			.getText().equalsIgnoreCase(productName));	
    	return match;
    }
    
    public CheckOutPage goToCheckout()
    {
    	checkoutEle.click();
    	//CheckOutPage checkoutPage = new CheckOutPage(driver);
    	//return checkoutPage;
    	return new CheckOutPage(driver);
    	
    	
    	
    }

	

	
	
	
}
