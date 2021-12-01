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

public class DeleteUserTest extends Base {
    LoginPage loginPage;
    HomePage home;
    UserManagementPage userManagementPage;
    UsersPage usersPage;
    DeleteUserPage deleteUserPage;
    SignOutPage signOut;
    EditUserPage editUserPage;
    SoftAssert softAssert;

    ThreadLocal<ExtentTest> extentTest = TestListener.getTestInstance();

    @Test(priority = 19, enabled = true, description = "TC_019_verifyUserCanDeleteUser", groups = {"Regression"})
    public void verifyUserCanDeleteUser() throws IOException, InterruptedException {

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
        String userDelete = usersPage.getUserToBeDeleted();
        deleteUserPage = usersPage.clickOnDeleteButton(userDelete);
        Thread.sleep(6000);
        usersPage=deleteUserPage.clickOnOkButton();
        Thread.sleep(6000);
        List<ArrayList<String>> data=usersPage.getTableDataText();
        boolean value =usersPage.getTableDataContains(data,usersPage.getUserToBeDeleted());
        softAssert = new SoftAssert();
        softAssert.assertFalse(value,"ERROR : User Deletion Unsuccessful");
        signOut = home.clickOnUserName();
        signOut.userAccountSignOut();
        extentTest.get().log(Status.PASS, "Successfully Signed Out");
        softAssert.assertAll();
    }
}

