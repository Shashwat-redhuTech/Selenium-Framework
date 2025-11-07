package testComponents;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import io.github.bonigarcia.wdm.WebDriverManager;
import pageobjectclasses.LandingPage;

public class BaseTest {
    
    // Suggestion 1: Instance variables declare karein
    public WebDriver driver;
    public LandingPage landingPage;
    public Properties prop;

    @BeforeMethod(alwaysRun = true)
    public LandingPage launchApplication() throws IOException {
        
        // Initialize Properties object
        prop = new Properties();
        
        // Suggestion 3: Universal file path use karein
        String dynamicPath = System.getProperty("user.dir") + "/src/test/resources/GlobalData.properties";
        
        // Suggestion 2: File ko sirf ek baar load karein
        try (FileInputStream fis = new FileInputStream(dynamicPath)) {
            prop.load(fis);
        }

        String browserName = prop.getProperty("browser");

        if (browserName.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if (browserName.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        } else if (browserName.equalsIgnoreCase("edge")) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        }
        
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();

        landingPage = new LandingPage(driver);
        driver.get(prop.getProperty("url")); // 'url' ko bhi prop file se lein
        
        return landingPage;
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}