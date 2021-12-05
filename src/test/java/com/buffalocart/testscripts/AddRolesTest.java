package com.buffalocart.testscripts;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.buffalocart.automationcore.Base;
import com.buffalocart.listener.TestListener;
import com.buffalocart.pages.*;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;

public class AddRolesTest extends Base {
    LoginPage loginPage;
    HomePage home;
    UserManagementPage userManagementPage;
    UsersPage usersPage;
    DeleteUserPage deleteUserPage;
    SignOutPage signOut;
    EditUserPage editUserPage;
    RolesPage rolesPage;
    AddRolesPage addRolesPage;
    SoftAssert softAssert;

    ThreadLocal<ExtentTest> extentTest = TestListener.getTestInstance();


    @Test(priority = 22, enabled = true, description = "TC_022_VerifyAddRolesPageTitle ", groups = {"Regression"})
    public void verifyAddRolesPageTitle() throws IOException, InterruptedException {

        extentTest.get().assignCategory("Regression");
        loginPage = new LoginPage(driver);
        home = new HomePage(driver);
        editUserPage = new EditUserPage(driver);
        userManagementPage = loginPage.successfulLoginUserManagementPage();
        extentTest.get().log(Status.PASS, "Successfully logged into Home Page");
        usersPage = userManagementPage.userManagementTabClick();
        extentTest.get().log(Status.PASS, "Successfully clicked User Management Tab");
        rolesPage = usersPage.rolesTabClick();
        addRolesPage=rolesPage.addRolesButtonClick();
        String actualAddUserTitle = addRolesPage.getActualAddUsersPageTitle();
        String expectedAddUserTitle = addRolesPage.getExpectedAddUsersPageTitle();
        softAssert= new SoftAssert();
        softAssert.assertEquals(actualAddUserTitle,expectedAddUserTitle,"ERROR : Invalid Add Users Page Title Found");
        signOut = home.clickOnUserName();
        signOut.userAccountSignOut();
        extentTest.get().log(Status.PASS, "Successfully Signed Out");
        softAssert.assertAll();
    }
    @Test(priority = 23, enabled = true, description = "TC_023_VerifyUserCanAddRoles", groups = {"Smoke","Sanity","Regression"})
    public void VerifyUserCanAddRoles() throws IOException, InterruptedException {
        extentTest.get().assignCategory("Smoke");
        extentTest.get().assignCategory("Sanity");
        extentTest.get().assignCategory("Regression");
        loginPage = new LoginPage(driver);
        home = new HomePage(driver);
        editUserPage = new EditUserPage(driver);
        userManagementPage = loginPage.successfulLoginUserManagementPage();
        extentTest.get().log(Status.PASS, "Successfully logged into Home Page");
        usersPage = userManagementPage.userManagementTabClick();
        extentTest.get().log(Status.PASS, "Successfully clicked User Management Tab");
        rolesPage = usersPage.rolesTabClick();
        addRolesPage=rolesPage.addRolesButtonClick();
        addRolesPage.enterRoleName(addRolesPage.getUserRole());
        addRolesPage.clickOnUserPermissionSelectAllCheckbox();
        rolesPage=addRolesPage.clickOnSaveButton();
        Thread.sleep(6000);
        boolean value =rolesPage.getTableDataContains(rolesPage.getTableDataText(), rolesPage.getRoleToUpdateAndDelete());
        softAssert= new SoftAssert();
        softAssert.assertTrue(value,"ERROR : Successfully inserted Roles");
        signOut = home.clickOnUserName();
        signOut.userAccountSignOut();
        extentTest.get().log(Status.PASS, "Successfully Signed Out");
        softAssert.assertAll();
    }


}
