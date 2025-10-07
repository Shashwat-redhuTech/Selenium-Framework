package pageobjectclasses;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Components.AbstractComponents;

public class productCat extends AbstractComponents {
    WebDriver driver;

    // Constructor to initialize driver and PageFactory
    public productCat(WebDriver driver) {
    	super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // PageFactory ka istemaal karke products ki list ko find karna
    @FindBy(css = ".card-body")
     List<WebElement> products;

    @FindBy(xpath="//button[@routerlink='/dashboard/cart']")
     WebElement cartButton;
    
    @FindBy(css=".ngx-spinner-overlay")
    WebElement spinner;
    
    @FindBy(css=".card-body")
    WebElement toast;
    
    @FindBy(xpath = " //button[@routerlink='/dashboard/myorders']")
    WebElement orderButton;
    
    // Is page ke liye zaroori locators
  
    private By toastMessage = By.cssSelector(".toast-container");
  
    /**
     * Yeh method products ke load hone ka intezaar karega.
     */
  

    /**
     * Yeh method diye gaye items ko cart mein add karega.
     * @param itemsNeeded - Jin items ko add karna hai unki List.
     */
    public void addItemsToCart(List<String> itemsNeeded) {
        // Sabse pehle products ke load hone ka wait karein
        waitForWebElementToAppear(toast);

        // Java Stream ka istemaal karke zaroori products dhoondhna aur unhe add karna
        products.stream()
            // Step 1: Sirf woh products filter karo jinka naam hamari list mein hai
            .filter(product -> itemsNeeded.contains(product.findElement(By.cssSelector("b")).getText()))
            // Step 2: Har filtered product ke liye 'Add To Cart' button par click karo
            .forEach(product -> {
                product.findElement(By.cssSelector(".card-body button:last-of-type")).click();
                System.out.println("Added to cart: " + product.findElement(By.cssSelector("b")).getText());
                
                // Product add hone ke baad aane waale toast message ke gayab hone ka intezaar
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
                wait.until(ExpectedConditions.invisibilityOfElementLocated(toastMessage));
            });
    }
    
    /**
     * Yeh method Cart button par click karega
     * @return 
     */
    public CartPage goToCart() {
    waitForElementToDisappear(spinner);
      waitForElementtoBeClickable(cartButton);
      cartButton.click();
      CartPage cartPage = new CartPage(driver);
       return cartPage;
    }
    
    public OrderHistoryPage goToOrderHistory() {
		waitForElementtoBeClickable(orderButton);
	    orderButton.click();
		OrderHistoryPage orderHistoryPage = new OrderHistoryPage(driver);
		return orderHistoryPage;
	}
    
    
}