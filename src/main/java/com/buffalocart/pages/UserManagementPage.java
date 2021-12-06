package com.buffalocart.pages;

import com.buffalocart.constants.Constants;
import com.buffalocart.utilities.TestHelperUtility;
import com.buffalocart.utilities.WaitUtility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserManagementPage extends TestHelperUtility {
    WebDriver driver;
    public UserManagementPage(WebDriver driver) throws IOException {
        this.driver= driver;
        PageFactory.initElements(driver, this);
    }
    List<String> readExcelData = excel.readExcel(Constants.EXCEL_FILE_PATH, Constants.SHEET_NAME_USER_MANAGEMENT_PAGE);



    private final String _userManagementSubTabs="//ul[@class='treeview-menu menu-open']//span[@class='title']";

    private final String _usersTab1 = "//i[@class='fa fa-user']/following-sibling::span[@class='title']";
    @FindBy(xpath = _usersTab1)
    private WebElement usersTab;

    private final String _usersTab2 = "//i[@class='fa fa-briefcase']/following-sibling::span[@class='title']";
    @FindBy(xpath = _usersTab2)
    private WebElement usersTab2Roles;

    private final String _usersTab3 = "//i[@class='fa fa-handshake-o']/following-sibling::span[@class='title']";
    @FindBy(xpath = _usersTab3)
    private WebElement usersTab3SCAgents;

    public List<WebElement> getUserManagementSubTabs() throws InterruptedException {
        waitUtility.waitForVisibilityOfElement(driver, WaitUtility.LocatorType.Xpath,_userManagementSubTabs,waitUtility.EXPLICIT_WAIT);
       return page.getWebElementList(driver,_userManagementSubTabs);
    }

    public UsersPage usersTabClick() throws InterruptedException, IOException {
        waitUtility.waitForVisibilityOfElement(driver, WaitUtility.LocatorType.Xpath, _usersTab1, WaitUtility.EXPLICIT_WAIT);
        page.clickOnElement(usersTab);
        return new UsersPage(driver);
    }

    public RolesPage rolesTabClick() throws IOException {
        waitUtility.waitForVisibilityOfElement(driver, WaitUtility.LocatorType.Xpath, _usersTab2, WaitUtility.EXPLICIT_WAIT);
        page.clickOnElement(usersTab2Roles);
        return new RolesPage(driver);
    }

    public SalesCommissionAgentPage salesCommissionTabClick() throws InterruptedException, IOException {
        waitUtility.waitForVisibilityOfElement(driver, WaitUtility.LocatorType.Xpath, _usersTab3, WaitUtility.EXPLICIT_WAIT);
        page.clickOnElement(usersTab3SCAgents);
        return new SalesCommissionAgentPage(driver);
    }

    public List<String> getActualUserManagementSubTabsText() throws InterruptedException {
        List<String> actualUserManagementSubTabsText = new ArrayList<>();
        List<WebElement> userManagementSubTabWebElement =getUserManagementSubTabs();
        for(int i=0;i<userManagementSubTabWebElement.size();i++){
            actualUserManagementSubTabsText.add(page.getElementText(userManagementSubTabWebElement.get(i)));
        }
        System.out.println(actualUserManagementSubTabsText);
        return actualUserManagementSubTabsText;
    }

    public List<String> getExpectedUserManagementSubTabsText(){
        List<String> expectedUserManagementSubTabs = new ArrayList<>();
        expectedUserManagementSubTabs.add(readExcelData.get(2));
        expectedUserManagementSubTabs.add(readExcelData.get(4));
        expectedUserManagementSubTabs.add(readExcelData.get(6));

        return expectedUserManagementSubTabs;
    }


}
