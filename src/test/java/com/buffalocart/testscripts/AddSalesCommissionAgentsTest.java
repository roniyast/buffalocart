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
    UserPage home;
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
        home = loginPage.successfulLoginHomePage();
        home.clickOnEndTour();
        extentTest.get().log(Status.PASS, "Successfully logged into Home Page");
        userManagementPage=home.userManagementTabClick();
        extentTest.get().log(Status.PASS, "Successfully clicked User Management Tab");
        usersPage= userManagementPage.usersTabClick();
        extentTest.get().log(Status.PASS, "Successfully clicked Users Tab");
        Thread.sleep(3000);
        salesCommissionPage = userManagementPage.salesCommissionTabClick();
        extentTest.get().log(Status.PASS, "Successfully navigated to SCAgents Page");
        Thread.sleep(3000);
        addSaleCommissionAgentPage = salesCommissionPage.clickAddSCAButton();
        extentTest.get().log(Status.PASS, "Successfully clicked Add SCAgents Button");
        Thread.sleep(4000);
        addSaleCommissionAgentPage.enterValues();
        extentTest.get().log(Status.PASS, "Successfully Entered SCAgents Values");
        salesCommissionPage = addSaleCommissionAgentPage.saveButtonclick();
        extentTest.get().log(Status.PASS, "Successfully clicked save button");
        Thread.sleep(6000);
        List<String> SCAList = salesCommissionPage.getActualUsersList();
        System.out.println(SCAList);
        boolean value = salesCommissionPage.getTableDataContains(SCAList, addSaleCommissionAgentPage.getSCAAgent());
        softAssert = new SoftAssert();
        softAssert.assertTrue(value, "ERROR : Sales Commission Agent Addition Unsuccessful");
        extentTest.get().log(Status.PASS, "Successfully Asserted the added SCAgents Details");
        signOut = home.clickOnUserName();
        signOut.userAccountSignOut();
        extentTest.get().log(Status.PASS, "Successfully Signed Out");
        softAssert.assertAll();
    }
}
