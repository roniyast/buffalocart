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
    UserPage home;
    UserManagementPage userManagementPage;
    SignOutPage signOut;
    RolesPage rolesPage;
    UpdateRolesPage updateRolesPage;
    SoftAssert softAssert;

    ThreadLocal<ExtentTest> extentTest = TestListener.getTestInstance();

    @Test(priority = 24, enabled = true, description = "TC_024_VerifyEditRolePageTitle", groups = {"Regression"})
    public void verifyEditRolePageTitle() throws IOException, InterruptedException {

        extentTest.get().assignCategory("Regression");


        loginPage = new LoginPage(driver);
        home = loginPage.successfulLoginHomePage();
        home.clickOnEndTour();
        extentTest.get().log(Status.PASS, "Successfully logged into Home Page");
        userManagementPage=home.userManagementTabClick();
        extentTest.get().log(Status.PASS, "Successfully clicked User Management Tab");
        rolesPage = userManagementPage.rolesTabClick();
        extentTest.get().log(Status.PASS, "Successfully clicked Roles Tab");
        updateRolesPage = rolesPage.clickOnUpdateButton(rolesPage.getRoleToUpdateAndDelete());
        extentTest.get().log(Status.PASS, "Successfully navigated to update role page ");
        String actualUpdateRolePageTitle = updateRolesPage.getActualUpdateRolePageTitle();
        String expectedUpdateRolePageTitle = updateRolesPage.getExpectedUpdateRolePageTitle();
        softAssert = new SoftAssert();
        softAssert.assertEquals(actualUpdateRolePageTitle, expectedUpdateRolePageTitle, "ERROR : Invalid Update Users Page Title Found");
        extentTest.get().log(Status.PASS, "Successfully asserted for the update role page title");
        signOut = home.clickOnUserName();
        signOut.userAccountSignOut();
        extentTest.get().log(Status.PASS, "Successfully Signed Out");
        softAssert.assertAll();

    }

    @Test(priority = 25, enabled = true, description = "TC_025_verifyUserCanUpdateARole ", groups = {"Regression"})
    public void verifyUserCanUpdateARole() throws IOException, InterruptedException {

        extentTest.get().assignCategory("Regression");


        loginPage = new LoginPage(driver);
        home = loginPage.successfulLoginHomePage();
        home.clickOnEndTour();
        extentTest.get().log(Status.PASS, "Successfully logged into Home Page");
        userManagementPage=home.userManagementTabClick();
        extentTest.get().log(Status.PASS, "Successfully clicked User Management Tab");
        rolesPage = userManagementPage.rolesTabClick();
        extentTest.get().log(Status.PASS, "Successfully clicked Roles Tab");
        updateRolesPage = rolesPage.clickOnUpdateButton(rolesPage.getRoleToUpdateAndDelete());
        extentTest.get().log(Status.PASS, "Successfully navigated to the edit roles page");
        Thread.sleep(6000);
        updateRolesPage.clickOnRolesPermission();
        Thread.sleep(100);
        rolesPage=updateRolesPage.clickOnUpdateButton();
        updateRolesPage = rolesPage.clickOnUpdateButton(rolesPage.getRoleToUpdateAndDelete());
        extentTest.get().log(Status.PASS, "Successfully Entered updated roles details");
        Thread.sleep(10000);
        String actualValue=updateRolesPage.getActualStatus();
        String expectedValue = updateRolesPage.getExpectedStatus();
        softAssert = new SoftAssert();
        softAssert.assertEquals(actualValue,expectedValue,"ERROR : Updating Failure");
        extentTest.get().log(Status.PASS, "Successfully Asserted for the updated roles in the roles page");
        signOut = home.clickOnUserName();
        signOut.userAccountSignOut();
        extentTest.get().log(Status.PASS, "Successfully Signed Out");
        softAssert.assertAll();

    }
}