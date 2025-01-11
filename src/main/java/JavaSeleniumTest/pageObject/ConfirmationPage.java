package JavaSeleniumTest.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.Utilities;

public class ConfirmationPage extends Utilities {
	
WebDriver driver;
	
	public ConfirmationPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	////Page Factory ////

	@FindBy (css= ".hero-primary")
	WebElement confirmationMsg;
	
	public String getConfirmationMsg()
	{
		return confirmationMsg.getText();
	}
	
	
}
