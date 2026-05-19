const { test, expect } = require('@playwright/test');

test('Fabindia Login Test', async ({ page }) => {

    // Open application
    await page.goto('https://fabindiaqa.garudavigil.com/');

    // Wait for page load
    await page.waitForLoadState('networkidle');

    // Username
    await page.locator('#txtUsername').fill('admin.ITTS');

    // Password
    await page.locator('#txtPassword').fill('admin.ITTS');
    await page.locator('#txtPassword').fill('admin.ITTS');
    await page.locator('#txtCaptcha').fill('1234');
    // Captcha handling


    // Login button
    await page.locator('#btnLogin').click();

    // Wait after login
    await page.waitForTimeout(5000);

    // Validation
    await expect(page).not.toHaveURL(/login/i);

});