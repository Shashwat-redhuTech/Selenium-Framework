package appTest;

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
	
	  List<String> itemsToAdd = Arrays.asList("ZARA COAT 3", "ADIDAS ORIGINAL");
  @Test
  public void endToEndTest() throws InterruptedException {
	  productCat productCatalogue = landingPage.loginApplication(prop.getProperty("username"),prop.getProperty("password"));
	
	  productCatalogue.addItemsToCart(itemsToAdd);
	  CartPage cartPage = productCatalogue.goToCart();
	
	  Assert.assertTrue(cartPage.verifyItemsInCart(itemsToAdd));
	   placeorderPage placeorderPage = cartPage.goToCheckout();
	   placeorderPage.selectCountry("India");
	   ConfirmationPage confirmationPage = placeorderPage.submitOrder();
	   String ConfirmationMessage = confirmationPage.getConfirmationMessage();
	   Assert.assertTrue(ConfirmationMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
  }
  
  @Test(dependsOnMethods = {"endToEndTest"})
  public void orderHistoryTest() {
	  productCat productCatalogue = landingPage.loginApplication(prop.getProperty("username"),prop.getProperty("password"));
       OrderHistoryPage OrderHistoryPage = productCatalogue.goToOrderHistory();
       Assert.assertTrue(OrderHistoryPage.verifyOrderDisplay(itemsToAdd));
  }
  
   
  
  
}
