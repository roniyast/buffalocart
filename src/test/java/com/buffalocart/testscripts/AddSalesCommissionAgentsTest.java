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

public class AddSalesCommissionAgentsTest extends Base {
    LoginPage loginPage;
    HomePage home;
    UserManagementPage userManagementPage;
    SignOutPage signOut;
    UsersPage usersPage;
    SoftAssert softAssert;
    SalesCommissionAgentPage salesCommissionPage;
    AddSalesCommissionAgentPage addSaleCommissionAgentPage;
    ThreadLocal<ExtentTest> extentTest = TestListener.getTestInstance();


    @Test(priority = 29, enabled = true, description = "TC_029_verifyUserCanAddSalesAgent", groups = {"Smoke","Sanity","Regression"})
    public void verifyUserCanAddSalesAgent() throws IOException, InterruptedException {
        extentTest.get().assignCategory("Smoke");
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
        addSaleCommissionAgentPage = salesCommissionPage.clickAddSCAButton();
        Thread.sleep(4000);
        addSaleCommissionAgentPage.enterValues();
        salesCommissionPage = addSaleCommissionAgentPage.saveButtonclick();
        Thread.sleep(4000);
        List<String> SCAList = salesCommissionPage.getActualUsersList();
        System.out.println(SCAList);
        boolean value = salesCommissionPage.getTableDataContains(SCAList, addSaleCommissionAgentPage.getSCAAgent());
        softAssert = new SoftAssert();
        softAssert.assertTrue(value, "ERROR : Sales Commission Agent Addition Unsuccessful");
        signOut = home.clickOnUserName();
        signOut.userAccountSignOut();
        extentTest.get().log(Status.PASS, "Successfully Signed Out");
        softAssert.assertAll();
    }
}
