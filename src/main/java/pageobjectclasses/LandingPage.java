package pageobjectclasses;





import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Components.AbstractComponents;

public class LandingPage extends AbstractComponents {
	  

	
	
		WebDriver driver;
	
	 public LandingPage(WebDriver driver) {
	        // initialization
		     super(driver);
	        this.driver = driver;
	        PageFactory.initElements(driver, this);
	    }


	 
    
	 
	 
	    // login
        @FindBy(id="userEmail")
	  private  WebElement userEmail;

	    @FindBy(id="userPassword")
	  private  WebElement password;

	    @FindBy(xpath="//input[@id='login']")
	  private  WebElement submit;
	    
	    @FindBy(xpath=" //div[@id='toast-container']")
	    WebElement errorMessage;	
	    
	    public productCat loginApplication(String email, String pwd) {
	    	userEmail.sendKeys(email);
	    	password.sendKeys(pwd);
	    	submit.click();
	    	productCat productCatalogue = new productCat(driver);
	    	return productCatalogue;
	    }
	   
	    public void goTo() {
	    	driver.get("https://rahulshettyacademy.com/client");
	       }
	    
	    public String getErrorMessage() {
	    	waitForWebElementToAppear(errorMessage);
	    	return errorMessage.getText();
	    }
	    
	
}


