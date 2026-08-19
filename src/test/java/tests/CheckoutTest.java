package tests;

import base.BaseTest;
import pages.LoginPage;
import pages.ProductsPage;
import pages.CartPage;
import pages.CheckoutPage;

import org.testng.Assert;
import org.testng.annotations.Test;

public class CheckoutTest extends BaseTest {

    @Test
    public void completeCheckoutTest() {

        // 1. Login
        LoginPage loginPage = new LoginPage(driver);

        loginPage.login(
                "standard_user",
                "secret_sauce"
        );

        // 2. Add product
        ProductsPage productsPage = new ProductsPage(driver);

        productsPage.addBackpackToCart();

        productsPage.clickCart();

        // 3. Verify product in cart
        CartPage cartPage = new CartPage(driver);

        String productName = cartPage.getProductName();

        Assert.assertEquals(
                productName,
                "Sauce Labs Backpack",
                "Incorrect product in cart"
        );

        // 4. Go to checkout
        cartPage.clickCheckout();

        // 5. Enter customer details
        CheckoutPage checkoutPage = new CheckoutPage(driver);

        checkoutPage.enterCustomerDetails(
                "Akansha",
                "Kamble",
                "411001"
        );

        // 6. Continue
        checkoutPage.clickContinue();

        // 7. Finish order
        checkoutPage.clickFinish();

        // 8. Verify successful order
        String successMessage =
                checkoutPage.getSuccessMessage();

        Assert.assertEquals(
                successMessage,
                "Thank you for your order!",
                "Order was not completed successfully"
        );

        System.out.println(
                "Order completed successfully"
        );
    }
}