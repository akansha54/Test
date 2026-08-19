package base;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import pages.LoginPage;

public class BaseTest {

    protected WebDriver driver;

    @BeforeMethod
    public void setUp() {

        ChromeOptions options = new ChromeOptions();

        Map<String, Object> prefs = new HashMap<>();

        // Disable password manager
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);

        // Disable password leak detection
        prefs.put("profile.password_manager_leak_detection", false);

        options.setExperimentalOption("prefs", prefs);

        // Disable browser notifications
        options.addArguments("--disable-notifications");

        driver = new ChromeDriver(options);

        driver.manage().window().maximize();

        driver.get("https://www.saucedemo.com/");
    }

    protected void login() {

        LoginPage loginPage = new LoginPage(driver);

        loginPage.login(
                "standard_user",
                "secret_sauce"
        );
    }

    public WebDriver getDriver() {
        return driver;
    }

    @AfterMethod
    public void tearDown() {

        if (driver != null) {
            driver.quit();
        }
    }
}