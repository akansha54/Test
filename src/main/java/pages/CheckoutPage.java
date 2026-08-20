package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utils.WaitUtils;

public class CheckoutPage {

    private WebDriver driver;
    private WaitUtils wait;

    // =========================
    // Locators
    // =========================

    // First Name
    private By firstNameField = By.id("first-name");

    // Last Name
    private By lastNameField = By.id("last-name");

    // Postal Code
    private By postalCodeField = By.id("postal-code");

    // Continue button
    private By continueButton = By.id("continue");

    // Finish button
    private By finishButton = By.id("finish");

    // Success message
    private By successMessage = By.className("complete-header");

    // =========================
    // Constructor
    // =========================

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WaitUtils(driver);
    }

    // =========================
    // Methods
    // =========================

    // Enter customer details
    public void enterCustomerDetails(
            String firstName,
            String lastName,
            String postalCode) {

        wait.waitForElement(firstNameField)
                .sendKeys(firstName);

        wait.waitForElement(lastNameField)
                .sendKeys(lastName);

        wait.waitForElement(postalCodeField)
                .sendKeys(postalCode);
    }

    // Click Continue
    public void clickContinue() {

        wait.waitForClick(continueButton).click();
    }

    // Click Finish
    public void clickFinish() {

        wait.waitForClick(finishButton).click();
    }

    // Get success message
    public String getSuccessMessage() {

        return wait.waitForElement(successMessage)
                .getText();
    }
}