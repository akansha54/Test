package tests;

import base.BaseTest;
import pages.CartPage;
import pages.LoginPage;
import pages.ProductsPage;

import org.testng.Assert;
import org.testng.annotations.Test;

public class CartPageTest extends BaseTest {

    @Test
    public void verifyProductInCartTest() {

        // 1. Login
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");

        // 2. Add product to cart
        ProductsPage productsPage = new ProductsPage(driver);
        productsPage.addBackpackToCart();

        // 3. Open cart
        CartPage cartPage = new CartPage(driver);
        cartPage.openCart();

        // 4. Verify product is displayed in cart
        Assert.assertTrue(
                cartPage.isProductDisplayed("Sauce Labs Backpack"),
                "Sauce Labs Backpack is not displayed in the cart"
        );
    }
}