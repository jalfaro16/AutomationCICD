package JavaSeleniumTest.pageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import AbstractComponents.Utilities;

public class OrderPage extends Utilities {

	WebDriver driver;
	
	public OrderPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	////Page Factory ////
	
	@FindBy(css="tr td:nth-child(3)")
	private List<WebElement> productNames;
	
	////Methods of the Class.////
    public Boolean VerifyOrderDisplay(String productName)
    {
    	Boolean match = productNames.stream().anyMatch(product -> product
    			.getText().equalsIgnoreCase(productName));	
    	return match;
    }
	
}
