package JavaSeleniumTest.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.Utilities;

public class LandingPage extends Utilities {

	WebDriver driver;
	// Define locators using By
    private By usernameField = By.id("userEmail");
    private By passwordField = By.id("userPassword");
    private By loginButton = By.id("login");
    private By errorMsg = By.cssSelector("[class*='flyInOut']");
	
	public LandingPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
	}
	
	public ProductCatalog loginApp(String email, String password)
	{
		driver.findElement(usernameField).sendKeys(email);
		driver.findElement(passwordField).sendKeys(password);
		driver.findElement(loginButton).click();
		ProductCatalog prodCat = new ProductCatalog(driver);
		return prodCat;
	}
	
	public void goTo(String url)
	{
		driver.get(url);
	}
	
	public String getErrorMsg()
	{
		WebElement errorElement = waitForWebElementToAppear(errorMsg);
	    return errorElement.getText();
	}
}
