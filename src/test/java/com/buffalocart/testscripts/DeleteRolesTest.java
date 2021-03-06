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

public class DeleteRolesTest extends Base {
    LoginPage loginPage;
    UserPage home;
    UserManagementPage userManagementPage;
    UsersPage usersPage;
    UpdateUserPage updateUserPage;
    RolesPage rolesPage;
    DeleteRolesPage deleteRolesPage;
    SignOutPage signOut;
    SoftAssert softAssert;

    ThreadLocal<ExtentTest> extentTest = TestListener.getTestInstance();

    @Test(priority = 26, enabled = true, description = "TC_026_verifyUserCanDeleteARoleFromTheList", groups = {"Regression"})
    public void verifyUserCanDeleteARoleFromTheList() throws IOException, InterruptedException {
        extentTest.get().assignCategory("Regression");


        loginPage = new LoginPage(driver);
        home = loginPage.successfulLoginHomePage();
        home.clickOnEndTour();
        extentTest.get().log(Status.PASS, "Successfully logged into Home Page");
        userManagementPage=home.userManagementTabClick();
        extentTest.get().log(Status.PASS, "Successfully clicked User Management Tab");
        rolesPage = userManagementPage.rolesTabClick();
        extentTest.get().log(Status.PASS, "Successfully clicked Roles Tab");
        Thread.sleep(3000);
        deleteRolesPage = rolesPage.clickOnDeleteButton(rolesPage.getRoleToUpdateAndDelete());
        extentTest.get().log(Status.PASS, "Successfully navigated to delete user section");
        rolesPage=deleteRolesPage.clickOnOkButton();
        extentTest.get().log(Status.PASS, "Successfully clicked ok for deletion");
        Thread.sleep(6000);
        List<ArrayList<String>> data=rolesPage.getTableDataText();
        boolean value = rolesPage.getTableDataContains(data, rolesPage.getRoleToUpdateAndDelete());
        softAssert = new SoftAssert();
        softAssert.assertFalse(value,"ERROR : Role Deletion unsuccessful");
        extentTest.get().log(Status.PASS, "Checked for the Deleted value in Roles page");
        signOut = home.clickOnUserName();
        signOut.userAccountSignOut();
        extentTest.get().log(Status.PASS, "Successfully Signed Out");
        softAssert.assertAll();
    }
}
