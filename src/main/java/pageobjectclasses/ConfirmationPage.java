package pageobjectclasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Components.AbstractComponents;

public class ConfirmationPage extends AbstractComponents {
    WebDriver driver;

    // Constructor to initialize driver and PageFactory
    public ConfirmationPage(WebDriver driver) {
    	super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    
    @FindBy(xpath = "//h1[normalize-space()='Thankyou for the order.']")
    WebElement confirmMessage;
    
    @FindBy(xpath = " //button[@routerlink='/dashboard/myorders']")
    WebElement orderButton;
    
    public String getConfirmationMessage() {
    	waitForWebElementToAppear(confirmMessage);
		return confirmMessage.getText();
	}
    
    public OrderHistoryPage goToOrderHistory() {
    	waitForElementtoBeClickable(orderButton);
		orderButton.click();
		OrderHistoryPage orderHistoryPage = new OrderHistoryPage(driver);
		return orderHistoryPage;
	}
    
    
}



