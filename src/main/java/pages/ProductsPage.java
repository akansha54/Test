package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utils.WaitUtils;

public class ProductsPage {

    private WebDriver driver;
    private WaitUtils wait;

    // Locators
    private By backpack =
            By.id("add-to-cart-sauce-labs-backpack");

    private By removeBackpack =
            By.id("remove-sauce-labs-backpack");

    private By cart =
            By.className("shopping_cart_link");

    public ProductsPage(WebDriver driver) {

        this.driver = driver;
        this.wait = new WaitUtils(driver);
    }

    public void addBackpackToCart() {

        wait.waitForClick(backpack);
    }

    public void removeBackpackFromCart() {

        wait.waitForClick(removeBackpack);
    }

    public void clickCart() {

        wait.waitForClick(cart);
    }
}