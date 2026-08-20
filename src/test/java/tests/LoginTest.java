package tests;

import base.BaseTest;
import pages.LoginPage;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @DataProvider(name = "loginData")
    public Object[][] loginData() {

        return new Object[][] {

            {
                "TC_LOGIN_001",
                "standard_user",
                "secret_sauce",
                "success"
            },

            // {
            //     "TC_LOGIN_002",
            //     "wrong_user",
            //     "wrong_password",
            //     "failure"
            // },

            // {
            //     "TC_LOGIN_003",
            //     "standard_user",
            //     "wrong_password",
            //     "failure"
            // },

            // {
            //     "TC_LOGIN_004",
            //     "wrong_user",
            //     "secret_sauce",
            //     "failure"
            // }
        };
    }


    @Test(dataProvider = "loginData")
    public void loginTest(
            String testCaseId,
            String username,
            String password,
            String expectedResult) {

        System.out.println(
                "======================================"
        );

        System.out.println(
                "Executing Test Case: " + testCaseId
        );

        System.out.println(
                "Username: " + username
        );

        System.out.println(
                "Expected Result: " + expectedResult
        );

        System.out.println(
                "======================================"
        );


        LoginPage loginPage =
                new LoginPage(driver);

        loginPage.login(
                username,
                password
        );


        if (expectedResult.equals("success")) {

            String currentUrl =
                    driver.getCurrentUrl();

            Assert.assertTrue(
                    currentUrl.contains(
                            "inventory.html"
                    ),
                    "Login should be successful"
            );

        } else {

            String errorMessage =
                    loginPage.getErrorMessage();

            Assert.assertTrue(
                    errorMessage.contains(
                            "Username and password do not match"
                    ),
                    "Expected login error was not displayed"
            );
        }
    }
}