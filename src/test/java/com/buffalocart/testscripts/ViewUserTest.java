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
    UserPage home;
    UserManagementPage userManagementPage;
    UsersPage usersPage;
    ViewUserPage viewUserPage;
    UpdateUserPage updateUserPage;
    SignOutPage signOut;
    SoftAssert softAssert;

    ThreadLocal<ExtentTest> extentTest = TestListener.getTestInstance();

    @Test(priority = 20, enabled = true, description = "TC_020_verifyTheDetailsDisplayedOnViewUserPage", groups = {"Regression"})
    public void verifyTheDetailsDisplayedOnViewUserPage() throws IOException, InterruptedException {

        extentTest.get().assignCategory("Regression");
        loginPage = new LoginPage(driver);
        home = loginPage.successfulLoginHomePage();
        home.clickOnEndTour();
        extentTest.get().log(Status.PASS, "Successfully logged into Home Page");
        userManagementPage=home.userManagementTabClick();
        extentTest.get().log(Status.PASS, "Successfully clicked User Management Tab");
        usersPage= userManagementPage.usersTabClick();
        extentTest.get().log(Status.PASS, "Successfully clicked Users Tab");
        Thread.sleep(6000);
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
