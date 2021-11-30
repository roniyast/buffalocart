package com.buffalocart.testscripts;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.buffalocart.automationcore.Base;
import com.buffalocart.listener.TestListener;
import com.buffalocart.pages.LoginPage;
import com.buffalocart.pages.HomePage;
import com.buffalocart.pages.SignOutPage;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;

public class LoginTest extends Base {
    LoginPage login;
    HomePage home;
    SignOutPage signOut;
    ThreadLocal<ExtentTest> extentTest = TestListener.getTestInstance();

    @Test(priority = 1, enabled = true, description = "TC_001_VerifyLoginPageTitle", groups = {"Regression"})
    public void verifyLoginPageTitle() throws IOException {

        extentTest.get().assignCategory("Regression");

        login = new LoginPage(driver);
        String actualTitle = login.getActualLoginPageTitle();
        extentTest.get().log(Status.PASS, "Actual Login page title generated");
        String expectedTitle = login.getExpectedLoginPageTitle();
        extentTest.get().log(Status.PASS, "Expected Login page title generated");
        Assert.assertEquals(actualTitle, expectedTitle, "ERROR : Invalid Login Page Title Found");
        extentTest.get().log(Status.PASS, "verify Login Page title test case passed");
    }

    @Test(priority = 2, enabled = true, description = "TC_002_VerifyUserLoginWithValidUserCredentials", groups = {"Smoke", "Regression"})
    public void verifyUserLoginWithValidUserCredentials() throws IOException, InterruptedException {

        extentTest.get().assignCategory("Regression");
        extentTest.get().assignCategory("Smoke");

        login = new LoginPage(driver);
        login.enterUserNameLogin(login.getUsernameLogin());
        extentTest.get().log(Status.PASS, "Expected Login page Username Captured");
        login.enterPasswordLogin(login.getPasswordLogin());
        extentTest.get().log(Status.PASS, "Expected Login page Password Captured");
        login.RememberMeLoginCheck(login.getExpectedRememberMeCheckBoxStatus());
        home =login.loginButtonClick();
        home.clickOnEndTour();
        String actualUserAccountName = home.getActualUserAccountName();
        extentTest.get().log(Status.PASS, "Actual Username Captured");
        String expectedUserAccountName = home.getExpectedUserAccountName();
        extentTest.get().log(Status.PASS, "Expected Username Captured");
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(actualUserAccountName, expectedUserAccountName, "ERROR : Unsuccessful Login");
        softAssert.assertAll();
        extentTest.get().log(Status.PASS, "Successfully completed soft assertion");
        signOut=home.clickOnUserName();
        signOut.userAccountSignOut();
        extentTest.get().log(Status.PASS, "verify Successful login test case passed");
    }

    @Test(priority = 3, enabled = true, description = "TC_003_VerifyTheErrorMessageDisplayedForUserLoginWithInvalidCredentials", groups = {"Regression"})
    public void verifyTheErrorMessageDisplayedForUserLoginWithInvalidCredentials() throws IOException {
        extentTest.get().assignCategory("Regression");

        login = new LoginPage(driver);
        login.enterInvalidUserNameLogin(login.getInvalidUsernameLogin());
        extentTest.get().log(Status.PASS, "Expected Invalid Login page Username Captured");
        login.enterInvalidPasswordLogin(login.getInvalidPasswordLogin());
        extentTest.get().log(Status.PASS, "Expected invalid Login page Password Captured");
        login.RememberMeLoginCheck("TRUE");

        login.loginButtonClick();

        String actualInvalidUserMessage = login.getActualInvalidUserMessage();
        String expectedInvalidUserMessage = login.getExpectedInvalidUserMessage();
        Assert.assertEquals(actualInvalidUserMessage, expectedInvalidUserMessage, "ERROR : Invalid user message not matching");
        extentTest.get().log(Status.PASS, "Successfully completed Assertion");
    }

    @Test(priority = 4, enabled = true, description = "TC_004_VerifyWhetherTheUserIsAbleToClickOnRememberMeCheckbox", groups = {"Regression"})
    public void verifyWhetherTheUserIsAbleToClickOnRememberMeCheckbox() throws IOException {
        extentTest.get().assignCategory("Regression");

        login = new LoginPage(driver);
        String ExpectedStatus=login.getExpectedRememberMeCheckBoxStatus();
        extentTest.get().log(Status.PASS, "Expected Remember me status Captured");
        login.RememberMeLoginCheck(ExpectedStatus);
        boolean actualStatus=login.getActualRememberMeCheckBoxStatus();
        extentTest.get().log(Status.PASS, "Actual Remember me status Captured");
        Assert.assertTrue(actualStatus,"ERROR : Remember me Checkbox assertion failed");
        extentTest.get().log(Status.PASS, "Successfully verified Checkbox selection");
    }
}
