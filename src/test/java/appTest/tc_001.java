package appTest;


import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


import testComponents.BaseTest;

public class tc_001 extends BaseTest {
    
	
	
	@Test
	public void loginValidation() throws IOException {
		landingPage.loginApplication("codexgigas90@gmail.com", "Rasx4@fetlife");
	}
	
	@Test(groups = {"ErrorHandling"},dataProvider = "getData")
	public void loginErrorValidation(String email,String password) throws IOException {
landingPage.loginApplication(email,password);
		String errorMessage = landingPage.getErrorMessage();
	  Assert.assertEquals("Incorrect email or password.", errorMessage);   
	}
	

	
	 @DataProvider
	    public Object[][] getData() 
	    {
	        return new Object[][] {
	        	{"codexgigas0@gmail.com","Rasx4@fetlife"},{"codexgigas90@gmail.com","1234"}
	        };
	    }
	
}


