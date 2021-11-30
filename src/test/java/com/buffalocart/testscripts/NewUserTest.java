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

public class NewUserTest extends Base {
    LoginPage loginPage;
    HomePage home;
    UserManagementPage userManagementPage;
    UsersPage usersPage;
    NewUserPage newUserPage;
    SignOutPage signOut;
    SoftAssert softAssert;

    ThreadLocal<ExtentTest> extentTest = TestListener.getTestInstance();

    @Test(priority = 13, enabled = true, description = "TC_013_VerifyTheErrorMessageDisplayedWithoutFillingMandatoryFieldsInAddUserForm", groups = { "Regression"})
    public void verifyTheErrorMessageDisplayedWithoutFillingMandatoryFieldsInAddUserForm() throws IOException, InterruptedException {

        extentTest.get().assignCategory("Regression");
        loginPage = new LoginPage(driver);
        home= new HomePage(driver);
        userManagementPage = loginPage.successfulLoginUserManagementPage();
        extentTest.get().log(Status.PASS, "Successfully logged into Home Page");
        usersPage=userManagementPage.userManagementTabClick();
        extentTest.get().log(Status.PASS, "Successfully clicked User Management Tab");
        usersPage.usersTabClick();
        extentTest.get().log(Status.PASS, "Successfully clicked Users Tab");
        newUserPage=usersPage.clickOnNewUser();
        extentTest.get().log(Status.PASS, "Successfully clicked New User Add Button");
        newUserPage.clickOnSaveButton();
        newUserPage.clickOnToastMessage();
        extentTest.get().log(Status.PASS, "Successfully clicked Save Button");
        String actualErrorMessageForMandatoryField = newUserPage.getActualErrorMessageForMandatoryField();
        extentTest.get().log(Status.PASS, "Captured Actual Error message for mandatory Field");
        String expectedErrorMessageForMandatoryField = newUserPage.getExpectedErrorMessageForMandatoryField();
        extentTest.get().log(Status.PASS, "Captured Expected Error message for mandatory Field");
        softAssert = new SoftAssert();
        softAssert.assertEquals(actualErrorMessageForMandatoryField,expectedErrorMessageForMandatoryField,"ERROR : Error message for filling Mandatory field mismatch");
        softAssert.assertAll();
        extentTest.get().log(Status.PASS, "Asserted successfully");
        signOut=home.clickOnUserName();
        signOut.userAccountSignOut();
        extentTest.get().log(Status.PASS, "Successfully Signed out");


    }
    @Test(priority = 14, enabled = true, description = "TC_014_VerifyUserLoginWithNewlyAddedUser", groups = { "Regression"})
    public void verifyUserLoginWithNewlyAddedUser() throws IOException, InterruptedException {

        extentTest.get().assignCategory("Regression");
        loginPage = new LoginPage(driver);
        home= new HomePage(driver);
        userManagementPage = loginPage.successfulLoginUserManagementPage();
        extentTest.get().log(Status.PASS, "Successfully logged into Home Page");
        usersPage=userManagementPage.userManagementTabClick();
        extentTest.get().log(Status.PASS, "Successfully clicked User Management Tab");
        usersPage.usersTabClick();
        extentTest.get().log(Status.PASS, "Successfully clicked Users Tab");
        newUserPage = usersPage.clickOnNewUser();
        extentTest.get().log(Status.PASS, "Successfully clicked Add new user");
        newUserPage.enterPrefix(newUserPage.getPrefix());
        newUserPage.enterFirstName(newUserPage.getFirstName());
        newUserPage.enterLastName(newUserPage.getLastName());
        newUserPage.enterEmail(newUserPage.getEmail());
        newUserPage.selectRole();
        newUserPage.enterUserName(newUserPage.getUserName());
        newUserPage.enterPassword(newUserPage.getPassword());
        newUserPage.enterConfirmPassWord(newUserPage.getConfirmPassword());
        extentTest.get().log(Status.PASS, "Successfully Entered User Details");
        usersPage =newUserPage.clickOnSaveButton();
        Thread.sleep(6000);
        extentTest.get().log(Status.PASS, "Successfully clicked Save button");
        Thread.sleep(6000);
        signOut=home.clickOnUserName();
        loginPage=signOut.userAccountSignOutUsingJS();
        extentTest.get().log(Status.PASS, "Successfully Signed out");
        loginPage.enterUserNameLogin(NewUserPage.getNewUserName());
        loginPage.enterPasswordLogin(newUserPage.getPassword());
        extentTest.get().log(Status.PASS, "New user credentials entered");
        home=loginPage.loginButtonClick();
        extentTest.get().log(Status.PASS, "Successfully logged back in with newly created user");
        String actualUser= home.getActualUserAccountName();
        String expectedUser= newUserPage.getNewUserHomePage();
        softAssert = new SoftAssert();
        softAssert.assertEquals(actualUser,expectedUser,"ERROR : Unsuccessful User creation");
        extentTest.get().log(Status.PASS, "Successfully Asserted actual and expected user names");
        softAssert.assertAll();
        signOut= home.clickOnUserName();
        signOut.userAccountSignOut();
        extentTest.get().log(Status.PASS, "Successfully logged out");
    }
    @Test(priority = 15, enabled = true, description = "TC_015_VerifyAddUsersPageTitle", groups = { "Regression"})
    public void verifyAddUsersPageTitle() throws IOException, InterruptedException {

        extentTest.get().assignCategory("Regression");
        loginPage = new LoginPage(driver);
        home = new HomePage(driver);
        userManagementPage = loginPage.successfulLoginUserManagementPage();
        extentTest.get().log(Status.PASS, "Successfully logged into Home Page");
        usersPage = userManagementPage.userManagementTabClick();
        extentTest.get().log(Status.PASS, "Successfully clicked User Management Tab");
        usersPage.usersTabClick();
        extentTest.get().log(Status.PASS, "Successfully clicked Users Tab");
        newUserPage = usersPage.clickOnNewUser();
        String actualTitle =newUserPage.getActualPageTitle();
        extentTest.get().log(Status.PASS, "Captured actual add user page title");
        String expectedTitle=newUserPage.getPageExpectedTitle();
        extentTest.get().log(Status.PASS, "Captured expected add user page title");
        softAssert = new SoftAssert();
        softAssert.assertEquals(actualTitle,expectedTitle,"ERROR : Invalid Add Users Page Title Found");
        softAssert.assertAll();
        extentTest.get().log(Status.PASS, "successfully asserted add user page title");
        signOut=home.clickOnUserName();
        signOut.userAccountSignOut();
        extentTest.get().log(Status.PASS, "Successfully Signed out");

    }

    @Test(priority = 16, enabled = true, description = "TC_016_VerifyUserCanAddUserDetails", groups = { "Regression"})
    public void verifyUserCanAddUserDetails() throws IOException, InterruptedException {
        extentTest.get().assignCategory("Smoke");
        extentTest.get().assignCategory("Sanity");
        extentTest.get().assignCategory("Regression");
        loginPage = new LoginPage(driver);
        home= new HomePage(driver);
        userManagementPage = loginPage.successfulLoginUserManagementPage();
        extentTest.get().log(Status.PASS, "Successfully logged into Home Page");
        usersPage=userManagementPage.userManagementTabClick();
        extentTest.get().log(Status.PASS, "Successfully clicked User Management Tab");
        usersPage.usersTabClick();
        extentTest.get().log(Status.PASS, "Successfully clicked Users Tab");
        newUserPage = usersPage.clickOnNewUser();
        newUserPage.enterPrefix(newUserPage.getPrefix());
        newUserPage.enterFirstName(newUserPage.getFirstName());
        newUserPage.enterLastName(newUserPage.getLastName());
        newUserPage.enterEmail(newUserPage.getEmail());
        newUserPage.selectRole();
        newUserPage.enterUserName(newUserPage.getUserName());
        newUserPage.enterPassword(newUserPage.getPassword());
        newUserPage.enterConfirmPassWord(newUserPage.getConfirmPassword());
        extentTest.get().log(Status.PASS, "Successfully Entered User Details");
        usersPage=newUserPage.clickOnSaveButton();
        extentTest.get().log(Status.PASS, "Successfully clicked Save button");
        Thread.sleep(6000);
        String expectedUserName= NewUserPage.getNewUserName();
        List<ArrayList<String>> tableData = usersPage.getTableDataText();
        usersPage.getTableDataContains(tableData,expectedUserName);
        extentTest.get().log(Status.PASS, "Checked for the newly enetered data in table ");
        signOut= home.clickOnUserName();
        signOut.userAccountSignOut();
        extentTest.get().log(Status.PASS, "Clicked on log out");

    }



}
