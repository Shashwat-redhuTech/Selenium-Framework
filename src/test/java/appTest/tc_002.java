package appTest;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageobjectclasses.CartPage;
import pageobjectclasses.ConfirmationPage;
import pageobjectclasses.OrderHistoryPage;
import pageobjectclasses.placeorderPage;
import pageobjectclasses.productCat;
import testComponents.BaseTest;

public class tc_002 extends BaseTest {
  
	@Test
	 public void endToEndTest() throws IOException, InterruptedException {
        productCat productCatalogue = landingPage.loginApplication("codexgigas90@gmail.com", "Rasx4@fetlife");
        
        // Jin items ko add karna hai, unki list banayein
        List<String> itemsToAdd = Arrays.asList("ZARA COAT 3", "ADIDAS ORIGINAL");
        
        // Sirf ek method call karke saare items add karein
        productCatalogue.addItemsToCart(itemsToAdd);
        
        CartPage cartPage = productCatalogue.goToCart();
        
        Assert.assertTrue(cartPage.verifyItemsInCart(itemsToAdd));
        
        placeorderPage placeOrderPage = cartPage.goToCheckout();
        
        placeOrderPage.selectCountry("India");
        
        ConfirmationPage confirmationPage = placeOrderPage.submitOrder();
        
        String confirmMessage = confirmationPage.getConfirmationMessage();
        Assert.assertEquals(confirmMessage, "THANKYOU FOR THE ORDER.");
        
        
      }
	
	@Test(dependsOnMethods = {"endToEndTest"} )
	public void orderHistoryTest() throws InterruptedException {
	     productCat productCatalogue = landingPage.loginApplication("codexgigas90@gmail.com", "Rasx4@fetlife");
	        
	        // Jin items ko add karna hai, unki list banayein
	        List<String> itemsToAdd = Arrays.asList("ZARA COAT 3", "ADIDAS ORIGINAL");
	        
	        // Sirf ek method call karke saare items add karein
	        
	        OrderHistoryPage orderHistoryPage = productCatalogue.goToOrderHistory();
	        
	       Assert.assertTrue(orderHistoryPage.verifyOrderDisplay(itemsToAdd));
	     
	    
	}
	
	
}
