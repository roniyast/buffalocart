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

public class RolesTest extends Base {
    LoginPage loginPage;
    UserPage home;
    UserManagementPage userManagementPage;
    UsersPage usersPage;
    AddUserPage addUserPage;
    SignOutPage signOut;
    RolesPage rolesPage;
    AddRolesPage addRolesPage;
    SoftAssert softAssert;

    ThreadLocal<ExtentTest> extentTest = TestListener.getTestInstance();

    @Test(priority = 21, enabled = true, description = "TC_021_verifyAddRolesPageTitle", groups = {"Smoke","Sanity","Regression"})
    public void verifyRolesPageTitle() throws IOException, InterruptedException {

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
        String actualRolesPageTitle = rolesPage.getActualRolesPageTitle();
        String expectedRolesPageTitle = rolesPage.getExpectedRolesPageTitle();
        softAssert = new SoftAssert();
        softAssert.assertEquals(actualRolesPageTitle, expectedRolesPageTitle, "ERROR : Invalid Roles Page Title Found");
        extentTest.get().log(Status.PASS, "Successfully Asserted Roles page title");
        signOut = home.clickOnUserName();
        signOut.userAccountSignOut();
        extentTest.get().log(Status.PASS, "Successfully Signed Out");
        softAssert.assertAll();
    }
    @Test(priority = 27, enabled = true, description = "TC_027_verifyWhetherTheAddedRoleInRolePageIsListedInRolesFieldInUserAddPage", groups = {"Regression"})
    public void verifyWhetherTheAddedRoleInRolePageIsListedInRolesFieldInUserAddPage() throws IOException, InterruptedException {

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
        extentTest.get().log(Status.PASS, "Successfully clicked Add Roles Button");
        addRolesPage.enterRoleName(addRolesPage.getUserRole());
        addRolesPage.clickOnUserPermissionSelectAllCheckbox();
        rolesPage=addRolesPage.clickOnSaveButton();
        extentTest.get().log(Status.PASS, "Successfully entered details for new Roles and saved it");
        Thread.sleep(6000);
        usersPage=userManagementPage.usersTabClick();
        extentTest.get().log(Status.PASS, "Successfully navigated to Users tab");
        Thread.sleep(400);
        addUserPage =usersPage.clickOnNewUser();
        extentTest.get().log(Status.PASS, "Successfully clicked Add new User button");
        Thread.sleep(6000);
        List<String> actualOptions = addUserPage.getRoles();
        String expectedOption = addRolesPage.getUserRole();
        boolean value =addRolesPage.getOptionContains(actualOptions,expectedOption);
        softAssert= new SoftAssert();
        System.out.println(value);
        softAssert.assertTrue(value,"ERROR : Successfully displayed value in the add user Roles dropdown");
        extentTest.get().log(Status.PASS, "Successfully checked for the display of newly added Role in Add User page");
        signOut = home.clickOnUserName();
        signOut.userAccountSignOut();
        extentTest.get().log(Status.PASS, "Successfully Signed Out");
        softAssert.assertAll();
    }


}
