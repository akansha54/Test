package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utils.WaitUtils;

public class LoginPage {

    private WebDriver driver;
    private WaitUtils wait;

    // Locators
    private By usernameField = By.id("user-name");
    private By passwordField = By.id("password");
    private By loginButton = By.id("login-button");
    private By errorMessage = By.cssSelector("[data-test='error']");

    // Constructor
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WaitUtils(driver);
    }

    // Enter username
    public void enterUsername(String username) {
        wait.waitForElement(usernameField).sendKeys(username);
    }

    // Enter password
    public void enterPassword(String password) {
        wait.waitForElement(passwordField).sendKeys(password);
    }

    // Click Login
    public void clickLogin() {
        wait.waitForClick(loginButton).click();
    }

    // Complete login
    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLogin();
    }

    // Get error message
    public String getErrorMessage() {
        return wait.waitForElement(errorMessage).getText();
    }
}