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
    UserPage home;
    UserManagementPage userManagementPage;
    UsersPage usersPage;
    DeleteUserPage deleteUserPage;
    SignOutPage signOut;
    SoftAssert softAssert;

    ThreadLocal<ExtentTest> extentTest = TestListener.getTestInstance();

    @Test(priority = 19, enabled = true, description = "TC_019_verifyUserCanDeleteUser", groups = {"Regression"})
    public void verifyUserCanDeleteUser() throws IOException, InterruptedException {

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
        String userDelete = usersPage.getUserToBeDeleted();
        Thread.sleep(8000);
        deleteUserPage = usersPage.clickOnDeleteButton(userDelete);
        extentTest.get().log(Status.PASS, "Successfully clicked Delete Button");
        Thread.sleep(6000);
        usersPage=deleteUserPage.clickOnOkButton();
        Thread.sleep(6000);
        extentTest.get().log(Status.PASS, "Successfully clicked ok button");
        List<ArrayList<String>> data=usersPage.getTableDataText();
        boolean value =usersPage.getTableDataContains(data,usersPage.getUserToBeDeleted());
        extentTest.get().log(Status.PASS, "Checked for the user after deleting");
        softAssert = new SoftAssert();
        softAssert.assertFalse(value,"ERROR : User Deletion Unsuccessful");
        signOut = home.clickOnUserName();
        signOut.userAccountSignOut();
        extentTest.get().log(Status.PASS, "Successfully Signed Out");
        softAssert.assertAll();
    }
}

