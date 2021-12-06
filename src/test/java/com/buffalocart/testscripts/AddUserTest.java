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

public class AddUserTest extends Base {
    LoginPage loginPage;
    UserPage home;
    UserManagementPage userManagementPage;
    UsersPage usersPage;
    AddUserPage addUserPage;
    SignOutPage signOut;
    SoftAssert softAssert;

    ThreadLocal<ExtentTest> extentTest = TestListener.getTestInstance();

    @Test(priority = 13, enabled = true, description = "TC_013_VerifyTheErrorMessageDisplayedWithoutFillingMandatoryFieldsInAddUserForm", groups = { "Regression"})
    public void verifyTheErrorMessageDisplayedWithoutFillingMandatoryFieldsInAddUserForm() throws IOException, InterruptedException {

        extentTest.get().assignCategory("Regression");
        loginPage = new LoginPage(driver);
        home = loginPage.successfulLoginHomePage();
        home.clickOnEndTour();
        extentTest.get().log(Status.PASS, "Successfully logged into Home Page");
        userManagementPage=home.userManagementTabClick();
        extentTest.get().log(Status.PASS, "Successfully clicked User Management Tab");
        usersPage= userManagementPage.usersTabClick();
        extentTest.get().log(Status.PASS, "Successfully clicked Users Tab");

        addUserPage =usersPage.clickOnNewUser();
        extentTest.get().log(Status.PASS, "Successfully clicked New User Add Button");
        addUserPage.clickOnSaveButton();
        addUserPage.clickOnToastMessage();
        extentTest.get().log(Status.PASS, "Successfully clicked Save Button");
        String actualErrorMessageForMandatoryField = addUserPage.getActualErrorMessageForMandatoryField();
        extentTest.get().log(Status.PASS, "Captured Actual Error message for mandatory Field");
        String expectedErrorMessageForMandatoryField = addUserPage.getExpectedErrorMessageForMandatoryField();
        extentTest.get().log(Status.PASS, "Captured Expected Error message for mandatory Field");
        softAssert = new SoftAssert();
        softAssert.assertEquals(actualErrorMessageForMandatoryField,expectedErrorMessageForMandatoryField,"ERROR : Error message for filling Mandatory field mismatch");
        extentTest.get().log(Status.PASS, "Asserted successfully");
        signOut=home.clickOnUserName();
        signOut.userAccountSignOut();
        extentTest.get().log(Status.PASS, "Successfully Signed out");
        softAssert.assertAll();


    }
    @Test(priority = 14, enabled = true, description = "TC_014_VerifyUserLoginWithNewlyAddedUser", groups = { "Regression"})
    public void verifyUserLoginWithNewlyAddedUser() throws IOException, InterruptedException {

        extentTest.get().assignCategory("Regression");
        loginPage = new LoginPage(driver);
        home = loginPage.successfulLoginHomePage();
        home.clickOnEndTour();
        extentTest.get().log(Status.PASS, "Successfully logged into Home Page");
        userManagementPage=home.userManagementTabClick();
        extentTest.get().log(Status.PASS, "Successfully clicked User Management Tab");
        usersPage= userManagementPage.usersTabClick();
        extentTest.get().log(Status.PASS, "Successfully clicked Users Tab");
        addUserPage = usersPage.clickOnNewUser();
        extentTest.get().log(Status.PASS, "Successfully clicked Add new user");
        addUserPage.enterPrefix(addUserPage.getPrefix());
        addUserPage.enterFirstName(addUserPage.getFirstName());
        addUserPage.enterLastName(addUserPage.getLastName());
        addUserPage.enterEmail(addUserPage.getEmail());
        addUserPage.selectRole();
        addUserPage.enterUserName(addUserPage.getUserName());
        addUserPage.enterPassword(addUserPage.getPassword());
        addUserPage.enterConfirmPassWord(addUserPage.getConfirmPassword());
        extentTest.get().log(Status.PASS, "Successfully Entered User Details");
        usersPage = addUserPage.clickOnSaveButton();
        extentTest.get().log(Status.PASS, "Successfully clicked Save button");
        Thread.sleep(8000);
        signOut=home.clickOnUserName();
        loginPage=signOut.userAccountSignOutUsingJS();
        extentTest.get().log(Status.PASS, "Successfully Signed out");
        loginPage.enterUserNameLogin(AddUserPage.getNewUserName());
        loginPage.enterPasswordLogin(addUserPage.getPassword());
        extentTest.get().log(Status.PASS, "New user credentials entered");
        home=loginPage.loginButtonClick();
        extentTest.get().log(Status.PASS, "Successfully logged back in with newly created user");
        String actualUser= home.getActualUserAccountName();
        String expectedUser= addUserPage.getNewUserHomePage();
        softAssert = new SoftAssert();
        softAssert.assertEquals(actualUser,expectedUser,"ERROR : Unsuccessful User creation");
        extentTest.get().log(Status.PASS, "Successfully Asserted actual and expected user names");
        signOut= home.clickOnUserName();
        signOut.userAccountSignOut();
        extentTest.get().log(Status.PASS, "Successfully logged out");
        softAssert.assertAll();
    }
    @Test(priority = 15, enabled = true, description = "TC_015_VerifyAddUsersPageTitle", groups = { "Regression"})
    public void verifyAddUsersPageTitle() throws IOException, InterruptedException {

        extentTest.get().assignCategory("Regression");
        loginPage = new LoginPage(driver);
        home = loginPage.successfulLoginHomePage();
        home.clickOnEndTour();
        extentTest.get().log(Status.PASS, "Successfully logged into Home Page");
        userManagementPage=home.userManagementTabClick();
        extentTest.get().log(Status.PASS, "Successfully clicked User Management Tab");
        usersPage= userManagementPage.usersTabClick();
        extentTest.get().log(Status.PASS, "Successfully clicked Users Tab");
        addUserPage = usersPage.clickOnNewUser();
        String actualTitle = addUserPage.getActualPageTitle();
        extentTest.get().log(Status.PASS, "Captured actual add user page title");
        String expectedTitle= addUserPage.getPageExpectedTitle();
        extentTest.get().log(Status.PASS, "Captured expected add user page title");
        softAssert = new SoftAssert();
        softAssert.assertEquals(actualTitle,expectedTitle,"ERROR : Invalid Add Users Page Title Found");
        extentTest.get().log(Status.PASS, "successfully asserted add user page title");
        signOut=home.clickOnUserName();
        signOut.userAccountSignOut();
        extentTest.get().log(Status.PASS, "Successfully Signed out");
        softAssert.assertAll();
    }

    @Test(priority = 16, enabled = true, description = "TC_016_VerifyUserCanAddUserDetails", groups = { "Smoke","Sanity","Regression"})
    public void verifyUserCanAddUserDetails() throws IOException, InterruptedException {

        extentTest.get().assignCategory("Smoke");
        extentTest.get().assignCategory("Sanity");
        extentTest.get().assignCategory("Regression");

        loginPage = new LoginPage(driver);
        home = loginPage.successfulLoginHomePage();
        home.clickOnEndTour();
        extentTest.get().log(Status.PASS, "Successfully logged into Home Page");
        userManagementPage=home.userManagementTabClick();
        extentTest.get().log(Status.PASS, "Successfully clicked User Management Tab");
        usersPage= userManagementPage.usersTabClick();
        extentTest.get().log(Status.PASS, "Successfully clicked Users Tab");
        addUserPage = usersPage.clickOnNewUser();
        addUserPage.enterPrefix(addUserPage.getPrefix());
        addUserPage.enterFirstName(addUserPage.getFirstName());
        addUserPage.enterLastName(addUserPage.getLastName());
        addUserPage.enterEmail(addUserPage.getEmail());
        addUserPage.selectRole();
        addUserPage.enterUserName(addUserPage.getUserName());
        addUserPage.enterPassword(addUserPage.getPassword());
        addUserPage.enterConfirmPassWord(addUserPage.getConfirmPassword());
        extentTest.get().log(Status.PASS, "Successfully Entered User Details");
        usersPage= addUserPage.clickOnSaveButton();
        extentTest.get().log(Status.PASS, "Successfully clicked Save button");
        Thread.sleep(6000);
        String expectedUserName= AddUserPage.getNewUserName();
        List<ArrayList<String>> tableData = usersPage.getTableDataText();
        usersPage.getTableDataContains(tableData,expectedUserName);
        extentTest.get().log(Status.PASS, "Checked for the newly enetered data in table ");
        signOut= home.clickOnUserName();
        signOut.userAccountSignOut();
        extentTest.get().log(Status.PASS, "Clicked on log out");

    }



}
