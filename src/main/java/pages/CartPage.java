package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utils.WaitUtils;

public class CartPage {

    private WebDriver driver;
    private WaitUtils wait;

    // =========================
    // Locators
    // =========================

    // Shopping cart icon
    private By cartIcon = By.className("shopping_cart_link");

    // Product name in cart
    private By productName = By.className("inventory_item_name");

    // Checkout button
    private By checkoutButton = By.id("checkout");

    // =========================
    // Constructor
    // =========================

    public CartPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WaitUtils(driver);
    }

    // =========================
    // Methods
    // =========================

    // Open Shopping Cart
    public void openCart() {
        wait.waitForElementClickable(cartIcon).click();
    }

    // Click Checkout
    public void clickCheckout() {
        wait.waitForElementClickable(checkoutButton).click();
    }

    // Get product name from cart
    public String getProductName() {
        return wait.waitForElement(productName).getText();
    }

    // Verify product is displayed in cart
    public boolean isProductDisplayed(String expectedProductName) {

        String actualProductName = getProductName();

        return actualProductName.equals(expectedProductName);
    }
}