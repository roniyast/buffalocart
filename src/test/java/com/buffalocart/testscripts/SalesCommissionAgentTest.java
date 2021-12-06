package com.buffalocart.testscripts;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.buffalocart.automationcore.Base;
import com.buffalocart.listener.TestListener;
import com.buffalocart.pages.*;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;

public class SalesCommissionAgentTest extends Base {
    LoginPage loginPage;
    UserPage home;
    UserManagementPage userManagementPage;
    SignOutPage signOut;
    UsersPage usersPage;
    SoftAssert softAssert;
    SalesCommissionAgentPage salesCommissionPage;
    ThreadLocal<ExtentTest> extentTest = TestListener.getTestInstance();


    @Test(priority = 28, enabled = true, description = "TC_028_verifySalesCommissionAgentsPageTitle", groups = {"Regression"})
    public void verifySalesCommissionAgentsPageTitle() throws IOException, InterruptedException {
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
        Thread.sleep(6000);
        String actualSCATitle= salesCommissionPage.getActualSalesCommissionPageTitle();
        String expectedSCATitle= salesCommissionPage.getExpectedSalesCommissionPageTitle();
        softAssert=new SoftAssert();
        softAssert.assertEquals(actualSCATitle,expectedSCATitle,"ERROR :Invalid Sales Commission Agent Page Tilte Found");
        signOut=home.clickOnUserName();
        signOut.userAccountSignOut();
        softAssert.assertAll();
    }
}
