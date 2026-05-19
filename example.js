const { chromium } = require('playwright');

(async () => {
  // launch browser
  const browser = await chromium.launch({ headless: false });

  // create new context
  const context = await browser.newContext();

  // open new page
  const page = await context.newPage();

  // go to Playwright website
  await page.goto('https://playwright.dev');

  // click "Get started" and wait for navigation
  await Promise.all([
    page.waitForNavigation(),
    page.click('text=Get started')
  ]);

  // wait for a visible element on next page
  await page.waitForSelector('h1');

 await page.waitForSelector('h2');
  // take screenshot
  await page.screenshot({ path: 'playwright.png' });

  // wait so you can see result
  await page.waitForTimeout(3000);

  // close browser
  await browser.close();
})();