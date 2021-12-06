package com.buffalocart.testscripts;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.buffalocart.automationcore.Base;
import com.buffalocart.listener.TestListener;
import com.buffalocart.pages.UserPage;
import com.buffalocart.pages.LoginPage;
import com.buffalocart.pages.SignOutPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class SignOutTest extends Base {
    UserPage home;
    LoginPage login;
    SignOutPage signOut;
    ThreadLocal<ExtentTest> extentTest = TestListener.getTestInstance();

    @Test(priority = 8, enabled = true, description = "TC_008_VerifyWhetherUserIsNavigatingToLoginPageByClickingOnSignOutButton", groups = {"Smoke","Regression"})
    public void verifyWhetherUserIsNavigatingToLoginPageByClickingOnSignOutButton () throws IOException, InterruptedException {
        extentTest.get().assignCategory("Regression");
        extentTest.get().assignCategory("Smoke");
        login = new LoginPage(driver);
        signOut= new SignOutPage(driver);
        home =login.successfulLoginHomePage();
        extentTest.get().log(Status.PASS, "Successfully entered Valid Login credentials");
        home.clickOnEndTour();
        home.clickOnUserName();
        signOut.userAccountSignOut();
        extentTest.get().log(Status.PASS, "Successfully clicked Signout button");
        String actualLoginPageTitle=login.getActualLoginPageTitle();
        extentTest.get().log(Status.PASS, "Actual Login Page Title captured");
        String expectedLoginPageTitle=login.getExpectedLoginPageTitle();
        extentTest.get().log(Status.PASS, "Expected Login Page Title captured");
        Assert.assertEquals(actualLoginPageTitle,expectedLoginPageTitle,"ERROR :Navigation to login page after signing out is not working ");
        extentTest.get().log(Status.PASS, "Successfully navigated to the Login Page after signing out");
    }

}
