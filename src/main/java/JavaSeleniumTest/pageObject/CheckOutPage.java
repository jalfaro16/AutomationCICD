package JavaSeleniumTest.pageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import AbstractComponents.Utilities;

public class CheckOutPage extends Utilities {

	WebDriver driver;
	
	public CheckOutPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	////Page Factory ////
	@FindBy(css="[placeholder='Select Country']")
	WebElement country;
	@FindBy(css=".action__submit")
	WebElement submit;
	@FindBy(xpath="(//button[contains(@class,'ta-item')])[2]")
	WebElement selectedCountry ;
	
	By results = By.cssSelector(".ta-results");
	
	////Methods of the Class.////
    
    public void selectCountry(String countryName)
    {
    	Actions a = new Actions(driver);
    	a.sendKeys(country, countryName).build().perform();
    	waitForElementToAppear(results);
    	selectedCountry.click();
    }
    
    public ConfirmationPage submitOrder()
    {
    	submit.click();
    	return new ConfirmationPage(driver);
    }

	

	
	
	
}
