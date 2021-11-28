package com.buffalocart.testscripts;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.buffalocart.automationcore.Base;
import com.buffalocart.listener.TestListener;
import com.buffalocart.pages.HomePage;
import com.buffalocart.pages.LoginPage;
import com.buffalocart.pages.SignOutPage;
import com.buffalocart.pages.UserManagementPage;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.util.List;

public class UserManagementTest extends Base {

    LoginPage loginPage;
    HomePage home;
    UserManagementPage userManagementPage;
    SignOutPage signOut;
    ThreadLocal<ExtentTest> extentTest = TestListener.getTestInstance();

    @Test(priority = 9, enabled = true, description = "TC_009_VerifyTheUserManagementSubTabs", groups = {"Regression"})
    public void VerifyTheUserManagementSubTabs() throws IOException, InterruptedException {
        extentTest.get().assignCategory("Regression");

        loginPage = new LoginPage(driver);
        signOut = new SignOutPage(driver);
        home = new HomePage(driver);
        userManagementPage = loginPage.successfulLoginUserManagementPage();
        extentTest.get().log(Status.PASS, "Successfully logged into Home Page");
        List<String> expectedUserManagementSubTabs = userManagementPage.getExpectedUserManagementSubTabsText();
        extentTest.get().log(Status.PASS, "Successfully captured Expected User Management Tab Values");
        userManagementPage.userManagementTabClick();
        extentTest.get().log(Status.PASS, "Successfully Clicked into User Management Tab");
        List<String> actualUserManagementSubTabs = userManagementPage.getActualUserManagementSubTabsText();
        extentTest.get().log(Status.PASS, "Successfully captured Actual User Management Tab Values");
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(actualUserManagementSubTabs, expectedUserManagementSubTabs, "ERROR : UserManagement Tabs Text Mismatch");
        extentTest.get().log(Status.PASS, "Tab values provided are matching");
        home.clickOnUserName();
        signOut.userAccountSignOut();
        extentTest.get().log(Status.PASS, "Successfully Signed out");
    }

}
