package Components;

import java.time.Duration;


import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AbstractComponents {
    WebDriver driver;

    public AbstractComponents(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Yeh method ek 'By' locator ke element ke visible hone ka wait karega.
     * Ise use karein jab aapke paas locator ho.
     * Example: waitForElementToAppear(By.id("myElement"));
     */
  
    public void gotoCartfast() {
		WebElement cartHeader = driver.findElement(org.openqa.selenium.By.cssSelector("[routerlink*='cart']"));
		cartHeader.click();
	}
    
    public void waitForElementtoBeClickable(WebElement element) {
       WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); 
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    /**
     * Yeh method ek 'WebElement' ke visible hone ka wait karega.
     * Ise tab use karein jab aapke paas @FindBy se mila element ho.
     * Example: waitForWebElementToAppear(myWebElement);
     */
    public void waitForWebElementToAppear(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(element));
    }
    
    /**
     * Yeh method ek element ke gayab hone ka wait karega.
     * Example: waitForElementToDisappear(toastMessageElement);
     */
    public void waitForElementToDisappear(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.invisibilityOf(element));
    }
     
    /**
     * Yeh scroll method theek hai, ismein koi badlaav ki zaroorat nahi.
     */
    public static void scrollToBottom(WebDriver driver) throws InterruptedException {

        JavascriptExecutor js = (JavascriptExecutor) driver;
        long lastHeight = ((Number) js.executeScript("return document.body.scrollHeight")).longValue();
        int tries = 0;
        int maxTries = 25;          
        while (true) {
            js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
            // small wait to allow lazy content to load (adjust if needed)
            Thread.sleep(1000);

            long newHeight = ((Number) js.executeScript("return document.body.scrollHeight")).longValue();
            if (newHeight == lastHeight) {
                // reached the end
                break;
            }
            lastHeight = newHeight;

            if (++tries >= maxTries) {
                System.out.println("Reached max scroll tries (" + maxTries + "). Stopping scroll.");
                break;
            }
        }
    }
    
  
    
   
}

