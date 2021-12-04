package com.buffalocart.testscripts;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.buffalocart.automationcore.Base;
import com.buffalocart.listener.TestListener;
import com.buffalocart.pages.*;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;

public class ViewUserTest extends Base {
    LoginPage loginPage;
    HomePage home;
    UserManagementPage userManagementPage;
    UsersPage usersPage;
    ViewUserPage viewUserPage;
    EditUserPage editUserPage;
    SignOutPage signOut;
    SoftAssert softAssert;

    ThreadLocal<ExtentTest> extentTest = TestListener.getTestInstance();

    @Test(priority = 20, enabled = true, description = "TC_020_verifyTheDetailsDisplayedOnViewUserPage", groups = {"Regression"})
    public void verifyTheDetailsDisplayedOnViewUserPage() throws IOException, InterruptedException {

        extentTest.get().assignCategory("Regression");
        loginPage = new LoginPage(driver);
        home = new HomePage(driver);
        editUserPage = new EditUserPage(driver);
        userManagementPage = loginPage.successfulLoginUserManagementPage();
        extentTest.get().log(Status.PASS, "Successfully logged into Home Page");
        usersPage = userManagementPage.userManagementTabClick();
        extentTest.get().log(Status.PASS, "Successfully clicked User Management Tab");
        usersPage.usersTabClick();
        Thread.sleep(6000);
        extentTest.get().log(Status.PASS, "Successfully clicked User Tab");
        String userToBeViewed = usersPage.getUserToBeViewed();
        viewUserPage = usersPage.clickOnViewButton(userToBeViewed);
        extentTest.get().log(Status.PASS, "Successfully clicked the view button");
        Thread.sleep(6000);
        boolean value=viewUserPage.checkContainsInViewUser
                (viewUserPage.getViewUserDetails(),viewUserPage.getUserToCheck());
        extentTest.get().log(Status.PASS, "Checked for the Viewed User is as same the one requested");
        softAssert=new SoftAssert();
        softAssert.assertTrue(value,"ERROR : Specified User is not Matching");
        signOut = home.clickOnUserName();
        signOut.userAccountSignOut();
        extentTest.get().log(Status.PASS, "Successfully Signed Out");
        softAssert.assertAll();
    }
}
