package com.buffalocart.testscripts;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.buffalocart.automationcore.Base;
import com.buffalocart.listener.TestListener;
import com.buffalocart.pages.HomePage;
import com.buffalocart.pages.LoginPage;
import com.buffalocart.pages.SignOutPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;

public class HomeTest extends Base {

    HomePage home;
    LoginPage login;
    SignOutPage signOut;
    ThreadLocal<ExtentTest> extentTest = TestListener.getTestInstance();

    public void successfulLogin() throws IOException {
        login = new LoginPage(driver);
        login.enterUserNameLogin(login.getUsernameLogin());
        login.enterPasswordLogin(login.getPasswordLogin());
        login.RememberMeLoginCheck(login.getExpectedRememberMeCheckBoxStatus());

        login.loginButtonClick();
        extentTest.get().log(Status.PASS, "Successfully entered Valid Login credentials");
        home.clickOnEndTour();

    }


    @Test(priority = 6, enabled = true, description = "TC_006_VerifyHomePageTitle", groups = {"Regression"})
    public void VerifyHomePageTitle() throws IOException {

        extentTest.get().assignCategory("Regression");
        successfulLogin();
        SoftAssert softAssert = new SoftAssert();
        String ActualHomePageTitle = home.getActualHomePageTitle();
        extentTest.get().log(Status.PASS, "Actual Home page title generated");
        String expectedHomePageTitle = home.getExpectedHomePageTitle();
        extentTest.get().log(Status.PASS, "Expected Home page title generated");
        softAssert.assertEquals(ActualHomePageTitle, expectedHomePageTitle, "ERROR : Invalid Home Page Title Found");
        extentTest.get().log(Status.PASS, "verify Home Page title test case passed");
        home.clickOnUserName();
        signOut.userAccountSignOut();
        extentTest.get().log(Status.PASS, "Successfully Signed out");
    }

    @Test(priority = 7, enabled = true, description = "TC_007_VerifyDateDisplayedInHomePage", groups = {"Regression"})
    public void VerifyDateDisplayedInHomePage () throws IOException {

        extentTest.get().assignCategory("Regression");
        successfulLogin();
        SoftAssert softAssert = new SoftAssert();
        String expectedHomePageDate = home.getExpectedHomePageDate();
        extentTest.get().log(Status.PASS, "Expected Home page Date generated");
        String actualHomePageDate = home.getActualHomePageDate();
        extentTest.get().log(Status.PASS, "Actual Home page Date generated");
        softAssert.assertEquals(actualHomePageDate,expectedHomePageDate,"ERROR : Home Page Date not Valid");
        extentTest.get().log(Status.PASS, "verify Home Page Date test case passed");
        home.clickOnUserName();
        signOut.userAccountSignOut();
        extentTest.get().log(Status.PASS, "Successfully Signed out");

    }


}