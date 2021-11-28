package com.buffalocart.testscripts;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.buffalocart.automationcore.Base;
import com.buffalocart.listener.TestListener;
import com.buffalocart.pages.*;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;

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
    public void VerifyTheErrorMessageDisplayedWithoutFillingMandatoryFieldsInAddUserForm() throws IOException, InterruptedException {

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
    public void VerifyUserLoginWithNewlyAddedUser() throws IOException, InterruptedException {

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
        newUserPage.clickOnSaveButton();
        extentTest.get().log(Status.PASS, "Successfully clicked Save button");
        signOut=home.clickOnUserName();
        loginPage=signOut.userAccountSignOut();
        extentTest.get().log(Status.PASS, "Successfully Signed out");
        loginPage.getNewUsernameLogin();
        loginPage.getNewUserPasswordLogin();
        extentTest.get().log(Status.PASS, "New user credentials entered");
        loginPage.loginButtonClick();
        String actualTitle= home.getActualHomePageTitle();
        String expectedTitle= home.getExpectedHomePageTitle();
        softAssert = new SoftAssert();
        softAssert.assertEquals(actualTitle,expectedTitle,"Unsuccessful User creation");
        softAssert.assertAll();
        signOut= home.clickOnUserName();
        signOut.userAccountSignOut();
        extentTest.get().log(Status.PASS, "Clicked on log out");

    }
    @Test(priority = 15, enabled = true, description = "TC_015_VerifyAddUsersPageTitle", groups = { "Regression"})
    public void VerifyAddUsersPageTitle() throws IOException, InterruptedException {

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
        String expectedTitle=newUserPage.getPageExpectedTitle();
        softAssert = new SoftAssert();
        softAssert.assertEquals(actualTitle,expectedTitle,"ERROR : Invalid Add Users Page Title Found");
        softAssert.assertAll();
        signOut=home.clickOnUserName();
        signOut.userAccountSignOut();
        extentTest.get().log(Status.PASS, "Successfully Signed out");

    }


}
