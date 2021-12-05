package com.buffalocart.testscripts;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.buffalocart.automationcore.Base;
import com.buffalocart.listener.TestListener;
import com.buffalocart.pages.*;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;

public class DeleteSalesCommissionAgentTest extends Base {
    LoginPage loginPage;
    HomePage home;
    UserManagementPage userManagementPage;
    SignOutPage signOut;
    UsersPage usersPage;
    SoftAssert softAssert;
    SalesCommissionAgentPage salesCommissionPage;
    DeleteSalesCommissionAgentPage deleteSalesCommissionAgentPage;
    ThreadLocal<ExtentTest> extentTest = TestListener.getTestInstance();


    @Test(priority = 31, enabled = true, description = "TC_031_verifyUserCanDeleteASalesCommissionAgents", groups = {"Sanity","Regression"})
    public void verifyUserCanDeleteASalesCommissionAgents() throws IOException, InterruptedException {
        extentTest.get().assignCategory("Sanity");
        extentTest.get().assignCategory("Regression");
        loginPage = new LoginPage(driver);
        home = new HomePage(driver);

        userManagementPage = loginPage.successfulLoginUserManagementPage();
        extentTest.get().log(Status.PASS, "Successfully logged into Home Page");
        usersPage = userManagementPage.userManagementTabClick();
        extentTest.get().log(Status.PASS, "Successfully clicked User Management Tab");
        Thread.sleep(200);
        salesCommissionPage = usersPage.salesCommissionTabClick();
        Thread.sleep(6000);
        deleteSalesCommissionAgentPage = salesCommissionPage.clickOnDeleteButton(salesCommissionPage.getSCAgentToUpdate());
        Thread.sleep(6000);
        salesCommissionPage = deleteSalesCommissionAgentPage.clickOnOkButton();
        Thread.sleep(6000);
        boolean value =salesCommissionPage.getTableDataContains(salesCommissionPage.getActualUsersList(), salesCommissionPage.getSCAgentToUpdate());
        softAssert= new SoftAssert();
        softAssert.assertFalse(value,"ERROR : Deletion Unsuccessful");
        signOut = home.clickOnUserName();
        signOut.userAccountSignOut();
        extentTest.get().log(Status.PASS, "Successfully Signed Out");
        softAssert.assertAll();

    }
}
