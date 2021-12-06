package com.buffalocart.testscripts;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.buffalocart.automationcore.Base;
import com.buffalocart.listener.TestListener;
import com.buffalocart.pages.ForgotPasswordPage;
import com.buffalocart.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class ForgotPasswordTest extends Base {

    LoginPage loginPage;
    ForgotPasswordPage forgotPwdPage;
    ThreadLocal<ExtentTest> extentTest = TestListener.getTestInstance();

    @Test(priority = 5, enabled = true, description = "TC_005_VerifyErrorMessageDisplayedOnResetPasswordPageWithInvalidEmailId", groups = {"Regression"})
    public void verifyErrorMessageDisplayedOnResetPasswordPageWithInvalidEmailId() throws IOException {

        extentTest.get().assignCategory("Regression");

        loginPage= new LoginPage(driver);
        forgotPwdPage=loginPage.clickOnForgotPassword();
        String expectedInvalidUserName = forgotPwdPage.getExpectedInvalidUserName();
        extentTest.get().log(Status.PASS, "Expected Invalid Username Captured");
        forgotPwdPage.setInvalidUserName(expectedInvalidUserName);
        extentTest.get().log(Status.PASS, "Expected Invalid Username Entered");
        forgotPwdPage.clickOnResetButton();
        extentTest.get().log(Status.PASS, "Successfully clicked Reset button");
        String actualInvalidUserNameMessage=forgotPwdPage.getActualErrorMessageBlockInvalidUser();
        String expectedInvalidUserNameMessage= forgotPwdPage.getExpectedErrorMessageBlockInvalidUser();
        Assert.assertEquals(actualInvalidUserNameMessage, expectedInvalidUserNameMessage, "ERROR : Invalid User Password Reset ");
        extentTest.get().log(Status.PASS, "verify invalid user password reset completed successfully");
    }


}
