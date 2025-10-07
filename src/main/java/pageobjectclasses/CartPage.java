package pageobjectclasses;


import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.FindBy;
import Components.AbstractComponents;

public class CartPage extends AbstractComponents {
  WebDriver driver;
  public CartPage(WebDriver driver) {
	  super(driver);
	  this.driver = driver;
	  PageFactory.initElements(driver, this);
  }
  
  @FindBy(xpath="//div[@class='cart']")
  WebElement allItems;
  
  @FindBy(xpath="//ul[contains(@class,'cartWrap')]//li//h3")
  List<WebElement> cartItems;
  
  @FindBy(xpath="//button[normalize-space()='Checkout']")
  WebElement checkoutBtn;
  
  @FindBy(xpath = "//div[contains(@class,'prodTotal cartSection')]/p")
  List<WebElement> cartPriceList;
  
  @FindBy(xpath = "//li[contains(@class,'totalRow')]//span[@class='label' and normalize-space()='Total']/following-sibling::span[@class='value']")
  WebElement finalTotal;

  
  public boolean verifyItemsInCart(List<String> items) {
	  boolean match = items.stream().allMatch(item -> 
	  cartItems.stream().anyMatch(cartItem -> cartItem.getText().equalsIgnoreCase(item)));
	  return match;
  }
  
  
  
  public boolean verifyFinalTotal() {
	    double sum = 0.0;
        
	    // iterate all cart prices and sum them
	    for (WebElement priceElement : cartPriceList) {
	        String priceText = priceElement.getText().trim();
	        priceText = priceText.replace("$", "").replace(",", "").trim(); 
	        sum += Double.parseDouble(priceText);  
	    }

	    
	    waitForWebElementToAppear(finalTotal);
	    // fetch the final total displayed on UI
	    String totalText = finalTotal.getText().trim();   
	    totalText = totalText.replace("$", "").replace(",", "").trim();
	    double displayedTotal = Double.parseDouble(totalText);

	   System.out.println("Calculated Sum: " + sum);
	   System.out.println("Displayed Total: " + displayedTotal);

	    // compare with tolerance to avoid floating-point precision issues
	    return sum==displayedTotal;
	 }
  
  
  
  
  
  public placeorderPage goToCheckout() {
	  try {
		scrollToBottom(driver);
	  } catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	  }
	  waitForElementtoBeClickable(checkoutBtn);
	  checkoutBtn.click();
	  return new placeorderPage(driver);
  }
  



	
}
  
  
  
  

