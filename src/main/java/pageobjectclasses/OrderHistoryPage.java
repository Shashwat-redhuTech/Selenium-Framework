package pageobjectclasses;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Components.AbstractComponents;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OrderHistoryPage extends AbstractComponents {
    WebDriver driver;
    private WebDriverWait wait;

    // Constructor to initialize driver and PageFactory
    public OrderHistoryPage(WebDriver driver) {
        super(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        PageFactory.initElements(driver, this);
    }

    // locator for the Name column cells (adjust index if your Name column is different)
    @FindBy(xpath = "//table//tbody//tr/td[2]")
    private List<WebElement> orderNames;

    /**
     * Returns the visible order names from the table as trimmed strings.
     */
    public List<String> getOrderNamesText() {
        // wait until at least one row is visible to reduce flakiness
        try {
            wait.until(ExpectedConditions.visibilityOfAllElements(orderNames));
        } catch (Exception ignored) {
            // proceed — will return empty list if nothing found
        }

        return orderNames.stream()
                .map(e -> e.getText().trim())
                .filter(s -> !s.isEmpty())
                .collect(Collectors.toList());
    }

    /**
     * Verify that all expected items are present in the Orders table.
     * Comparison is case-insensitive and trims whitespace.
     *
     * @param itemsToAdd list of product names expected to be present (e.g. "ZARA COAT 3")
     * @return true if ALL expected items are found, false otherwise
     */
    public boolean verifyOrderDisplay(List<String> itemsToAdd) {
        List<String> present = getOrderNamesText();

        for (String expected : itemsToAdd) {
            String exp = expected == null ? "" : expected.trim();
            boolean found = present.stream().anyMatch(actual -> actual.equalsIgnoreCase(exp));
            if (!found) {
                // early return on first missing item
                System.out.println("Order not found in history: " + expected);
                return false;
            }
        }
        return true;
    }
}



