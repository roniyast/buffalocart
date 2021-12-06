package com.buffalocart.testscripts;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.buffalocart.automationcore.Base;
import com.buffalocart.listener.TestListener;
import com.buffalocart.pages.*;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.util.List;

public class AddRolesTest extends Base {
    LoginPage loginPage;
    UserPage home;
    UserManagementPage userManagementPage;
    UsersPage usersPage;
    AddUserPage addUserPage;
    SignOutPage signOut;
    UpdateUserPage updateUserPage;
    RolesPage rolesPage;
    AddRolesPage addRolesPage;
    SoftAssert softAssert;

    ThreadLocal<ExtentTest> extentTest = TestListener.getTestInstance();


    @Test(priority = 22, enabled = true, description = "TC_022_verifyAddRolesPageTitle ", groups = {"Regression"})
    public void verifyAddRolesPageTitle() throws IOException, InterruptedException {

        extentTest.get().assignCategory("Regression");


        loginPage = new LoginPage(driver);
        home = loginPage.successfulLoginHomePage();
        home.clickOnEndTour();
        extentTest.get().log(Status.PASS, "Successfully logged into Home Page");
        userManagementPage=home.userManagementTabClick();
        extentTest.get().log(Status.PASS, "Successfully clicked User Management Tab");
        usersPage= userManagementPage.usersTabClick();
        extentTest.get().log(Status.PASS, "Successfully clicked Users Tab");
        rolesPage = userManagementPage.rolesTabClick();
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
    public void verifyUserCanAddRoles() throws IOException, InterruptedException {

        extentTest.get().assignCategory("Smoke");
        extentTest.get().assignCategory("Sanity");
        extentTest.get().assignCategory("Regression");


        loginPage = new LoginPage(driver);
        home = loginPage.successfulLoginHomePage();
        home.clickOnEndTour();
        extentTest.get().log(Status.PASS, "Successfully logged into Home Page");
        userManagementPage=home.userManagementTabClick();
        extentTest.get().log(Status.PASS, "Successfully clicked User Management Tab");
        rolesPage = userManagementPage.rolesTabClick();
        extentTest.get().log(Status.PASS, "Successfully clicked Roles Tab");
        addRolesPage=rolesPage.addRolesButtonClick();
        addRolesPage.enterRoleName(addRolesPage.getUserRole());
        addRolesPage.clickOnUserPermissionSelectAllCheckbox();
        rolesPage=addRolesPage.clickOnSaveButton();
        extentTest.get().log(Status.PASS, "Successfully entered New roles details and clicked save button");
        Thread.sleep(6000);
        boolean value =rolesPage.getTableDataContains(rolesPage.getTableDataText(), rolesPage.getRoleToUpdateAndDelete());
        softAssert= new SoftAssert();
        softAssert.assertTrue(value,"ERROR : Successfully inserted Roles");
        extentTest.get().log(Status.PASS, "Successfully Asserted for the newly added role in roles page");
        signOut = home.clickOnUserName();
        signOut.userAccountSignOut();
        extentTest.get().log(Status.PASS, "Successfully Signed Out");
        softAssert.assertAll();
    }

}
