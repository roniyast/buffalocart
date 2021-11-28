package com.buffalocart.testscripts;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.buffalocart.automationcore.Base;
import com.buffalocart.listener.TestListener;
import com.buffalocart.pages.*;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.awt.*;
import java.io.IOException;
import java.util.List;

public class UsersTest extends Base {

    LoginPage loginPage;
    HomePage home;
    UserManagementPage userManagementPage;
    SignOutPage signOut;
    UsersPage usersPage;
    ThreadLocal<ExtentTest> extentTest = TestListener.getTestInstance();

    @Test(priority = 10, enabled = true, description = "TC_010_VerifyUsersPageTitle", groups = {"Regression"})
    public void VerifyUsersPageTitle() throws IOException, InterruptedException {
        extentTest.get().assignCategory("Regression");
        loginPage = new LoginPage(driver);
        usersPage = new UsersPage(driver);
        signOut = new SignOutPage(driver);
        userManagementPage = loginPage.successfulLoginUserManagementPage();
        extentTest.get().log(Status.PASS, "Successfully logged into Home Page");
        userManagementPage.userManagementTabClick();
        extentTest.get().log(Status.PASS, "Successfully clicked User Management Tab");
        usersPage.usersTabClick();
        extentTest.get().log(Status.PASS, "Successfully clicked Users Tab");
        String expectedUsersPageTitle = usersPage.getExpectedUsersPageTitle();
        extentTest.get().log(Status.PASS, "Successfully captured  expected Users page title");
        System.out.println(expectedUsersPageTitle);
        String actualUsersPageTitle = usersPage.getActualUsersPageTitle();
        extentTest.get().log(Status.PASS, "Successfully captured  actual Users page title");
        System.out.println(actualUsersPageTitle);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(actualUsersPageTitle, expectedUsersPageTitle, "ERROR :  Invalid User Page Title found");
        extentTest.get().log(Status.PASS, "Successfully asserted Users page title");
        home.clickOnUserName();
        signOut.userAccountSignOut();
    }

    @Test(priority = 11, enabled = true, description = "TC_011_VerifyUserSearchWithValidData", groups = {"Smoke","Sanity","Regression"})
    public void VerifyUserSearchWithValidData() throws IOException, InterruptedException {
        extentTest.get().assignCategory("Smoke");
        extentTest.get().assignCategory("Sanity");
        extentTest.get().assignCategory("Regression");
        loginPage = new LoginPage(driver);
        usersPage = new UsersPage(driver);
        home = new HomePage(driver);
        signOut = new SignOutPage(driver);
        userManagementPage = loginPage.successfulLoginUserManagementPage();
        extentTest.get().log(Status.PASS, "Successfully logged into Home Page");
        userManagementPage.userManagementTabClick();
        extentTest.get().log(Status.PASS, "Successfully clicked User Management Tab");
        usersPage.usersTabClick();
        extentTest.get().log(Status.PASS, "Successfully clicked Users Tab");

        String expectedUser = usersPage.getExpectedUser();
        extentTest.get().log(Status.PASS, "Successfully Captured expected User id");

        usersPage.enterSearchValue(expectedUser);
        String actualUserValue = usersPage.getActualUserIdAfterSearch();
        extentTest.get().log(Status.PASS, "Successfully Captured actual User id after search");
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(actualUserValue, expectedUser, "ERROR : Value after search Mismatch");
        home.clickOnUserName();
        signOut.userAccountSignOut();
        extentTest.get().log(Status.PASS, "Successfully Signed out");

    }

    @Test(priority = 12, enabled = true, description = "TC_012_VerifyMessageDisplayedByUserSearchWithInvalidData", groups = {"Smoke","Regression"})
    public void VerifyMessageDisplayedByUserSearchWithInvalidData() throws IOException, InterruptedException {
        extentTest.get().assignCategory("Smoke");
        extentTest.get().assignCategory("Regression");
        loginPage = new LoginPage(driver);
        usersPage = new UsersPage(driver);
        home = new HomePage(driver);
        signOut = new SignOutPage(driver);
        userManagementPage = loginPage.successfulLoginUserManagementPage();
        extentTest.get().log(Status.PASS, "Successfully logged into Home Page");
        userManagementPage.userManagementTabClick();
        extentTest.get().log(Status.PASS, "Successfully clicked User Management Tab");
        usersPage.usersTabClick();
        extentTest.get().log(Status.PASS, "Successfully clicked Users Tab");

        String expectedInvalidUserValue = usersPage.getExpectedInvalidUser();
        extentTest.get().log(Status.PASS, "Successfully Captured expected Invalid User id");

        usersPage.enterSearchValue(expectedInvalidUserValue);
        String actualInvalidUserValue = usersPage.getActualUserIdAfterSearch();
        extentTest.get().log(Status.PASS, "Successfully Captured actual Invalid  User id after search");
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(actualInvalidUserValue, expectedInvalidUserValue, "ERROR : Value after Invalid User search message Mismatch");
        home.clickOnUserName();
        signOut.userAccountSignOut();
        extentTest.get().log(Status.PASS, "Successfully Signed out");


    }

}