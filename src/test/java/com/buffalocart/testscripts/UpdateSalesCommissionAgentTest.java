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
    UserPage home;
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
        home = loginPage.successfulLoginHomePage();
        home.clickOnEndTour();
        extentTest.get().log(Status.PASS, "Successfully logged into Home Page");
        userManagementPage=home.userManagementTabClick();
        Thread.sleep(1000);
        extentTest.get().log(Status.PASS, "Successfully clicked User Management Tab");
        salesCommissionPage= userManagementPage.salesCommissionTabClick();
        Thread.sleep(3000);
        extentTest.get().log(Status.PASS, "Successfully clicked SCA Tab");
        updateSalesCommissionAgentPage = salesCommissionPage.clickOnEditButton(salesCommissionPage.getSCAgentToUpdate());
        Thread.sleep(6000);
        extentTest.get().log(Status.PASS, "Successfully navigated to SCAgents Edit section");
        updateSalesCommissionAgentPage.updateSCAValue();
        extentTest.get().log(Status.PASS, "Successfully entered updating values");
        salesCommissionPage=updateSalesCommissionAgentPage.saveButtonclick();
        extentTest.get().log(Status.PASS, "Clicked Ok button in update section");
        Thread.sleep(6000);
        boolean value =salesCommissionPage.getTableDataContains
                (salesCommissionPage.getActualAllValueList(),
                        updateSalesCommissionAgentPage.getExpectedSCAValuetoUpdate());
        softAssert = new SoftAssert();
        softAssert.assertTrue(value,"ERROR : Updating Sales commission Agent is unsuccessful");
        extentTest.get().log(Status.PASS, "Successfully Asserted Updated values");
        signOut = home.clickOnUserName();
        signOut.userAccountSignOut();
        extentTest.get().log(Status.PASS, "Successfully Signed Out");
        softAssert.assertAll();
    }
}
