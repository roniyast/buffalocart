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

    ForgotPasswordPage forgotPwdPage;
    ThreadLocal<ExtentTest> extentTest = TestListener.getTestInstance();

    @Test(priority = 5, enabled = true, description = "TC_005_VerifyErrorMessageDisplayedOnResetPasswordPageWithInvalidEmailId", groups = {"Regression"})
    public void VerifyErrorMessageDisplayedOnResetPasswordPageWithInvalidEmailId() throws IOException {

        extentTest.get().assignCategory("Regression");

        forgotPwdPage= new ForgotPasswordPage(driver);
        forgotPwdPage.forgotPasswordLinkClick();
        String expectedInvalidUserName = forgotPwdPage.getExpectedInvalidUserName();
        extentTest.get().log(Status.PASS, "Expected Invalid Username Captured");
        forgotPwdPage.setInvalidUserName(expectedInvalidUserName);
        forgotPwdPage.clickOnResetButton();
        String actualInvalidUserNameMessage=forgotPwdPage.getActualErrorMessageBlockInvalidUser();
        String expectedInvalidUserNameMessage= forgotPwdPage.getExpectedErrorMessageBlockInvalidUser();
        Assert.assertEquals(actualInvalidUserNameMessage, expectedInvalidUserNameMessage, "ERROR : Invalid User Password Reset ");
        extentTest.get().log(Status.PASS, "verify invalid user password reset completed successfully");
    }


}
