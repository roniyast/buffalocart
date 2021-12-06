package com.buffalocart.testscripts;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.buffalocart.automationcore.Base;
import com.buffalocart.listener.TestListener;
import com.buffalocart.pages.*;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UpdateUserTest extends Base {
    LoginPage loginPage;
    UserPage home;
    UserManagementPage userManagementPage;
    UsersPage usersPage;
    SignOutPage signOut;
    UpdateUserPage updateUserPage;
    SoftAssert softAssert;

    ThreadLocal<ExtentTest> extentTest = TestListener.getTestInstance();

    @Test(priority = 17, enabled = true, description = "TC_017_VerifyEditUserPageTitle", groups = {"Sanity","Regression"})
    public void verifyEditUserPageTitle() throws IOException, InterruptedException {

        extentTest.get().assignCategory("Sanity");
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
        String userName = usersPage.getExpectedUser();
        updateUserPage = usersPage.clickOnEditButton(userName);
        Thread.sleep(6000);
        extentTest.get().log(Status.PASS, "Successfully captured username to search");
        String actualEditUserPageTitle = updateUserPage.getActualEditUserPageTitle();
        extentTest.get().log(Status.PASS, "Successfully captured Actual Edit User Page Title");
        String expectedEditUserPageTitle = updateUserPage.getExpectedEditUserPageTitle();
        extentTest.get().log(Status.PASS, "Successfully captured Expected Edit User Page Title");
        softAssert = new SoftAssert();
        softAssert.assertEquals(actualEditUserPageTitle, expectedEditUserPageTitle, "ERROR : Invalid Edit User Page Title Found");
        extentTest.get().log(Status.PASS, "Successfully Asserted Actual and Expected Titles");
        signOut = home.clickOnUserName();
        signOut.userAccountSignOut();
        extentTest.get().log(Status.PASS, "Successfully Signed Out");
        softAssert.assertAll();
    }

    @Test(priority = 18, enabled = true, description = "TC_018_VerifyUserCanEditTheUserDetails  ", groups = {"Sanity","Regression"})
    public void verifyUserCanEditTheUserDetails() throws IOException, InterruptedException {

        extentTest.get().assignCategory("Sanity");
        extentTest.get().assignCategory("Regression");

        loginPage = new LoginPage(driver);
        home = loginPage.successfulLoginHomePage();
        home.clickOnEndTour();
        extentTest.get().log(Status.PASS, "Successfully logged into Home Page");
        userManagementPage=home.userManagementTabClick();
        extentTest.get().log(Status.PASS, "Successfully clicked User Management Tab");
        usersPage= userManagementPage.usersTabClick();
        extentTest.get().log(Status.PASS, "Successfully clicked Users Tab");
        String userName = usersPage.getExpectedUser();
        updateUserPage=usersPage.clickOnEditButton(userName);
        extentTest.get().log(Status.PASS, "Successfully captured username to search");
        Thread.sleep(8000);
        updateUserPage.setValueEmail();
        extentTest.get().log(Status.PASS, "Edited email for a user");
        usersPage= updateUserPage.updateButtonClick();
        extentTest.get().log(Status.PASS, "Successfully clicked Update button");
        List<ArrayList<String>> data=usersPage.getTableDataText();
        usersPage.getTableDataContains(data, updateUserPage.getValueEdit());
        extentTest.get().log(Status.PASS, "Checked for the updated value in table data");
        Thread.sleep(8000);
        signOut = home.clickOnUserName();
        signOut.userAccountSignOut();
        extentTest.get().log(Status.PASS, "Successfully Signed Out");
    }
}
