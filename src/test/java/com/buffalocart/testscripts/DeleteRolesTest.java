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
    HomePage home;
    UserManagementPage userManagementPage;
    UsersPage usersPage;
    EditUserPage editUserPage;
    RolesPage rolesPage;
    DeleteRolesPage deleteRolesPage;
    SignOutPage signOut;
    SoftAssert softAssert;

    ThreadLocal<ExtentTest> extentTest = TestListener.getTestInstance();

    @Test(priority = 26, enabled = true, description = "TC_026_verifyUserCanDeleteARoleFromTheList", groups = {"Regression"})
    public void verifyUserCanDeleteARoleFromTheList() throws IOException, InterruptedException {
        extentTest.get().assignCategory("Regression");
        loginPage = new LoginPage(driver);
        home = new HomePage(driver);
        editUserPage = new EditUserPage(driver);
        userManagementPage = loginPage.successfulLoginUserManagementPage();
        extentTest.get().log(Status.PASS, "Successfully logged into Home Page");
        usersPage = userManagementPage.userManagementTabClick();
        extentTest.get().log(Status.PASS, "Successfully clicked User Management Tab");
        rolesPage = usersPage.rolesTabClick();
        Thread.sleep(3000);
        deleteRolesPage = rolesPage.clickOnDeleteButton(rolesPage.getRoleToUpdateAndDelete());
        rolesPage=deleteRolesPage.clickOnOkButton();
        Thread.sleep(6000);
        List<ArrayList<String>> data=rolesPage.getTableDataText();
        boolean value = rolesPage.getTableDataContains(data, rolesPage.getRoleToUpdateAndDelete());
        softAssert = new SoftAssert();
        softAssert.assertFalse(value,"ERROR : Role Deletion unsuccessful");
        extentTest.get().log(Status.PASS, "Checked for the updated value in table data");
        signOut = home.clickOnUserName();
        signOut.userAccountSignOut();
        extentTest.get().log(Status.PASS, "Successfully Signed Out");
        softAssert.assertAll();
    }
}
