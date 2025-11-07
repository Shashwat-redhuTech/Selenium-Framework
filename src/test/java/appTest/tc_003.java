package appTest;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageobjectclasses.OrderHistoryPage;
import pageobjectclasses.productCat;
import testComponents.BaseTest;

public class tc_003 extends BaseTest {
    
	@Test
	public void emptyOrderHistoryValidation() {
	    productCat productCatalogue = landingPage.loginApplication(prop.getProperty("negative_username"),prop.getProperty("negative_password"));
	    OrderHistoryPage orderHistoryPage = productCatalogue.goToOrderHistory();
	    
	    // Yahan se "\r" hata diya gaya hai
	    String expectedMessage = "You have No Orders to show at this time.\nPlease Visit Back Us";
	    
	    Assert.assertEquals(orderHistoryPage.youHaveNoOrders().trim(), expectedMessage);
	}
   
	
}
