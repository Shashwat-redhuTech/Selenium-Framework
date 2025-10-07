package pageobjectclasses;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Components.AbstractComponents;

public class placeorderPage extends AbstractComponents {
	   WebDriver driver;

	    // Constructor to initialize driver and PageFactory
	    public placeorderPage(WebDriver driver) {
	    	super(driver);
	        this.driver = driver;
	        PageFactory.initElements(driver, this);
	    }
	    
	    @FindBy(xpath=" //input[@placeholder='Select Country']")
	    WebElement selectCountry;
	    
	    @FindBy(xpath="//a[normalize-space()='Place Order']")
	    WebElement placeOrder;
	    
	    
	    public void selectCountry(String country) throws InterruptedException {
	        // 1) wait for input and type a short prefix to trigger suggestions
//	        WebElement input = wait.until(ExpectedConditions.elementToBeClickable(selectCountryInput));
//	        input.clear();
	    	waitForElementtoBeClickable(selectCountry);
	    	selectCountry.click();
	    	

	        // type a short prefix (3 chars) to trigger suggestions but not too specific
	        String prefix = country.length() > 3 ? country.substring(0, 3) : country;
	        selectCountry.sendKeys(prefix);

	        // small pause to let UI populate suggestions (explicit waits follow)
	        Thread.sleep(300);

	        // 2) build a tolerant xpath for the suggestion that exactly matches country (case-insensitive)
	        String suggestionXpath = String.format(
	            "//section[contains(@class,'ta-results') or contains(@class,'typeahead')]" +
	            "//*[normalize-space(translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz')) = '%s']",
	            country.toLowerCase()
	        );

//	        By suggestionBy = By.xpath(suggestionXpath);
	        WebElement suggestion = driver.findElement(By.xpath(suggestionXpath));
            waitForElementtoBeClickable(suggestion);

	        try {
	            // normal click
	            suggestion.click();
	            return;
	        } catch (Exception e1) {
	            // Actions fallback
	            try {
	                Actions actions = new Actions(driver);
	                actions.moveToElement(suggestion).pause(Duration.ofMillis(100)).click().perform();
	                return;
	            } catch (Exception e2) {
	                // JS fallback
	                try {
	                    JavascriptExecutor js = (JavascriptExecutor) driver;
	                    js.executeScript("arguments[0].click();", suggestion);
	                    return;
	                } catch (Exception e3) {
	                    throw new RuntimeException("Failed to select country '" + country + "' via suggestion clicks.", e3);
	                }
	            }
	        }
	    }

	    public ConfirmationPage submitOrder() {
	    	waitForElementtoBeClickable(placeOrder);
	    	placeOrder.click();
	    	return new ConfirmationPage(driver);
	    }

 
}
