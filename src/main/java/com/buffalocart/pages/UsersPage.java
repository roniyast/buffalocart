package com.buffalocart.pages;

import com.buffalocart.constants.Constants;
import com.buffalocart.utilities.TableUtility;
import com.buffalocart.utilities.TestHelperUtility;
import com.buffalocart.utilities.WaitUtility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class UsersPage extends TestHelperUtility {
    WebDriver driver;

    public UsersPage(WebDriver driver) throws IOException {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    List<String> readExcelData = excel.readExcel(Constants.EXCEL_FILE_PATH, Constants.SHEET_NAME_USERS_PAGE);

    private final String _usersTab1 = "//i[@class='fa fa-user']/following-sibling::span[@class='title']";
    @FindBy(xpath = _usersTab1)
    private WebElement usersTab;

    private final String _userValueSearch = "//input[@class='form-control input-sm']";
    @FindBy(xpath = _userValueSearch)
    private WebElement userValueSearch;

    private final String _usersSearch = "//table[@id='users_table']//tr/td[@class='sorting_1']";
    @FindBy(xpath = _usersSearch)
    private WebElement usersSearch;

    private final String _usersInvalidSearch = "//td[@class='dataTables_empty']";
    @FindBy(xpath = _usersInvalidSearch)
    private WebElement usersInvalidSearch;

    private final String _newUserAdd = "//a[@class='btn btn-block btn-primary']";
    @FindBy(xpath = _newUserAdd)
    private WebElement newUserAdd;

    private final String _rElement = "//table[@id='users_table']//tbody//tr";
    @FindBy(xpath = _rElement)
    private List<WebElement> rowElement;

    private final String _cElement = "//table[@id='users_table']//tbody//tr//td";
    @FindBy(xpath = _cElement)
    private List<WebElement> colElement;

    public void usersTabClick() throws InterruptedException {
        waitUtility.waitForVisibilityOfElement(driver, WaitUtility.LocatorType.Xpath, _usersTab1,waitUtility.EXPLICIT_WAIT);
        page.clickOnElement(usersTab);
    }

    public String getActualUsersPageTitle() {
        return page.getPageTitle(driver);
    }

    public String getExpectedUsersPageTitle() {
        String expectedTitle = readExcelData.get(2);
        return expectedTitle;
    }

    public List<String> getActualUsersList() {
        waitUtility.waitForVisibilityOfElement(driver, WaitUtility.LocatorType.Xpath, _usersSearch,waitUtility.EXPLICIT_WAIT);
        List<WebElement> usersListWebElement = page.getWebElementList(driver, _usersSearch);
        List<String> usersList = new ArrayList<>();
        for (int i = 0; i < usersListWebElement.size(); i++) {
            usersList.add(page.getElementText(usersListWebElement.get(i)));
        }
        return usersList;
    }

    public String getActualUserIdAfterSearch() {
        waitUtility.waitForVisibilityOfElement(driver, WaitUtility.LocatorType.Xpath, _usersSearch,waitUtility.EXPLICIT_WAIT);
        List<WebElement> usersListWebElement = page.getWebElementList(driver, _usersSearch);
        String actualUserValue = page.getElementText(usersListWebElement.get(0));
        if (actualUserValue != " ") {
            System.out.println("True");
            return actualUserValue;
        } else {
            return " ";
        }
    }

    public String getExpectedUser() {
        return readExcelData.get(4);
    }

    public String getActualInvalidUserIdAfterSearch() {
        waitUtility.waitForVisibilityOfElement(driver, WaitUtility.LocatorType.Xpath, _usersInvalidSearch,waitUtility.EXPLICIT_WAIT);
        List<WebElement> usersListWebElement = page.getWebElementList(driver, _usersInvalidSearch);
        String actualUserValue = page.getElementText(usersListWebElement.get(0));
        System.out.println(actualUserValue);
        return actualUserValue;
    }

    public String getExpectedInvalidUser() {
        return readExcelData.get(6);
    }

    public void enterSearchValue(String value) {
        page.enterText(userValueSearch, value);
    }

    public NewUserPage clickOnNewUser() throws IOException {
        page.clickOnElement(newUserAdd);
        return new NewUserPage(driver);
    }
    public List<ArrayList<String>> getTableData() {
        return TableUtility.getGridData(rowElement, colElement);
       // return table.actionData(rowElement, colElement);

    }
    public void getTableDataContains(List<ArrayList<String>> tableData,String expectedUserName){
        boolean value = false;
        for(int i=0;i<tableData.size();i++){
        if(tableData.get(i).contains(expectedUserName)){
            value= true;
        }
        }
    }



}
