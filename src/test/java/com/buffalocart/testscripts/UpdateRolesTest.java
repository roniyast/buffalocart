package com.buffalocart.testscripts;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.buffalocart.automationcore.Base;
import com.buffalocart.listener.TestListener;
import com.buffalocart.pages.*;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;

public class UpdateRolesTest extends Base {
    LoginPage loginPage;
    HomePage home;
    UserManagementPage userManagementPage;
    UsersPage usersPage;
    DeleteUserPage deleteUserPage;
    SignOutPage signOut;
    EditUserPage editUserPage;
    RolesPage rolesPage;
    UpdateRolesPage updateRolesPage;
    SoftAssert softAssert;

    ThreadLocal<ExtentTest> extentTest = TestListener.getTestInstance();
    @Test(priority = 24, enabled = true, description = "TC_024_VerifyEditRolePageTitle", groups = {"Regression"})
    public void verifyEditRolePageTitle() throws IOException, InterruptedException {
        extentTest.get().assignCategory("Regression");
        loginPage = new LoginPage(driver);
        home = new HomePage(driver);
        editUserPage = new EditUserPage(driver);
        userManagementPage = loginPage.successfulLoginUserManagementPage();
        extentTest.get().log(Status.PASS, "Successfully logged into Home Page");
        usersPage = userManagementPage.userManagementTabClick();
        extentTest.get().log(Status.PASS, "Successfully clicked User Management Tab");
        rolesPage = usersPage.rolesTabClick();
        updateRolesPage= rolesPage.clickOnUpdateButton(rolesPage.getRoleToUpdateAndDelete());
        String actualUpdateRolePageTitle= updateRolesPage.getActualUpdateRolePageTitle();
        String expectedUpdateRolePageTitle=updateRolesPage.getExpectedUpdateRolePageTitle();
        softAssert = new SoftAssert();
        softAssert.assertEquals(actualUpdateRolePageTitle,expectedUpdateRolePageTitle,"ERROR : Invalid Update Users Page Title Found");
        signOut = home.clickOnUserName();
        signOut.userAccountSignOut();
        extentTest.get().log(Status.PASS, "Successfully Signed Out");
        softAssert.assertAll();

    }
}