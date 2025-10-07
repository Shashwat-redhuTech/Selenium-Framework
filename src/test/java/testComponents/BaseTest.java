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
    
    public WebDriver driver;
    public LandingPage landingPage;

    // We will put all setup logic in the @BeforeMethod
    @BeforeMethod(alwaysRun = true)
    public LandingPage launchApplication() throws IOException {
        
        // Step 1: Read the properties file ONCE
        Properties prop = new Properties();
     // BaseTest.java
        String dynamicPath = System.getProperty("user.dir") + "\\src\\test\\resources\\GlobalData.properties";
        try (FileInputStream fis = new FileInputStream(dynamicPath)) {
            prop.load(fis);
        }

        try (FileInputStream fis = new FileInputStream(dynamicPath)) {
            prop.load(fis);
        }

        // Step 2: Initialize the browser based on the property
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

        // Step 3: Launch the URL and create the LandingPage object
        landingPage = new LandingPage(driver);
        String url = prop.getProperty("url");
        driver.get(url);
        
        return landingPage;
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}