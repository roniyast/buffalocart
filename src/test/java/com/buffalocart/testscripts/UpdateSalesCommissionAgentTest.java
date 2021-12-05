package com.buffalocart.testscripts;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.buffalocart.automationcore.Base;
import com.buffalocart.listener.TestListener;
import com.buffalocart.pages.*;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;

public class UpdateSalesCommissionAgentTest extends Base {
    LoginPage loginPage;
    HomePage home;
    UserManagementPage userManagementPage;
    SignOutPage signOut;
    UsersPage usersPage;
    SoftAssert softAssert;
    SalesCommissionAgentPage salesCommissionPage;
    UpdateSalesCommissionAgentPage updateSalesCommissionAgentPage;
    ThreadLocal<ExtentTest> extentTest = TestListener.getTestInstance();


    @Test(priority = 30, enabled = true, description = "TC_030_verifyEditSalesAgentDetails", groups = {"Smoke","Regression"})
    public void verifyEditSalesAgentDetails() throws IOException, InterruptedException {
        extentTest.get().assignCategory("Smoke");
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
        updateSalesCommissionAgentPage = salesCommissionPage.clickOnEditButton(salesCommissionPage.getSCAgentToUpdate());
        Thread.sleep(6000);
        updateSalesCommissionAgentPage.updateSCAValue();
        salesCommissionPage=updateSalesCommissionAgentPage.saveButtonclick();
        boolean value =salesCommissionPage.getTableDataContains
                (salesCommissionPage.getActualAllValueList(),
                        updateSalesCommissionAgentPage.getExpectedSCAValuetoUpdate());
        softAssert = new SoftAssert();
        softAssert.assertTrue(value,"ERROR : Updating Sales commission Agent is unsuccessful");
        signOut = home.clickOnUserName();
        signOut.userAccountSignOut();
        extentTest.get().log(Status.PASS, "Successfully Signed Out");
        softAssert.assertAll();

    }
}
