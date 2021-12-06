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
    UserPage home;
    UserManagementPage userManagementPage;
    SignOutPage signOut;
    SoftAssert softAssert;
    SalesCommissionAgentPage salesCommissionPage;
    DeleteSalesCommissionAgentPage deleteSalesCommissionAgentPage;
    ThreadLocal<ExtentTest> extentTest = TestListener.getTestInstance();


    @Test(priority = 31, enabled = true, description = "TC_031_verifyUserCanDeleteASalesCommissionAgents", groups = {"Sanity","Regression"})
    public void verifyUserCanDeleteASalesCommissionAgents() throws IOException, InterruptedException {

        extentTest.get().assignCategory("Sanity");
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
        deleteSalesCommissionAgentPage = salesCommissionPage.clickOnDeleteButton(salesCommissionPage.getSCAgentToUpdate());
        Thread.sleep(6000);
        extentTest.get().log(Status.PASS, "Successfully navigated to SCAgents delete section");
        salesCommissionPage = deleteSalesCommissionAgentPage.clickOnOkButton();
        extentTest.get().log(Status.PASS, "Successfully clicked Ok button");
        Thread.sleep(6000);
        boolean value =salesCommissionPage.getTableDataContains(salesCommissionPage.getActualUsersList(), salesCommissionPage.getSCAgentToUpdate());
        softAssert= new SoftAssert();
        softAssert.assertFalse(value,"ERROR : Deletion Unsuccessful");
        extentTest.get().log(Status.PASS, "Successfully Asserted Deleted values");
        signOut = home.clickOnUserName();
        signOut.userAccountSignOut();
        extentTest.get().log(Status.PASS, "Successfully Signed Out");
        softAssert.assertAll();

    }
}
