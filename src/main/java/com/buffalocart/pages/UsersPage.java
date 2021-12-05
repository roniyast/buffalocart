package com.buffalocart.pages;

import com.buffalocart.constants.Constants;
import com.buffalocart.utilities.TableUtility;
import com.buffalocart.utilities.TestHelperUtility;
import com.buffalocart.utilities.WaitUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class UsersPage extends TestHelperUtility {
    WebDriver driver;
    boolean values;
    WebElement editButton, viewButton, deleteButton;

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

    private final String _editButton = "//a[@class='btn btn-xs btn-primary']";

    private final String _deleteButton = "//button[@class='btn btn-xs btn-danger delete_user_button']";

    private final String _viewButton = "//a[@class='btn btn-xs btn-info']";

    private final String _usersTab2 = "//i[@class='fa fa-briefcase']/following-sibling::span[@class='title']";
    @FindBy(xpath = _usersTab2)
    private WebElement usersTab2Roles;

    private final String _usersTab3 = "//i[@class='fa fa-handshake-o']/following-sibling::span[@class='title']";
    @FindBy(xpath = _usersTab3)
    private WebElement usersTab3Roles;

    public RolesPage rolesTabClick() throws IOException {
        waitUtility.waitForVisibilityOfElement(driver, WaitUtility.LocatorType.Xpath, _usersTab2, WaitUtility.EXPLICIT_WAIT);
        page.clickOnElement(usersTab2Roles);
        return new RolesPage(driver);
    }

    public void usersTabClick() throws InterruptedException, IOException {
        waitUtility.waitForVisibilityOfElement(driver, WaitUtility.LocatorType.Xpath, _usersTab1, WaitUtility.EXPLICIT_WAIT);
        page.clickOnElement(usersTab);
    }
    public SalesCommissionAgentPage SCATabClick() throws IOException {
        waitUtility.waitForVisibilityOfElement(driver, WaitUtility.LocatorType.Xpath, _usersTab2, WaitUtility.EXPLICIT_WAIT);
        page.clickOnElement(usersTab2Roles);
        return new SalesCommissionAgentPage(driver);
    }
    public SalesCommissionAgentPage salesCommissionTabClick() throws InterruptedException, IOException {
        waitUtility.waitForVisibilityOfElement(driver, WaitUtility.LocatorType.Xpath, _usersTab1, WaitUtility.EXPLICIT_WAIT);
        page.clickOnElement(usersTab3Roles);
        return new SalesCommissionAgentPage(driver);
    }

    public String getActualUsersPageTitle() {
        return page.getPageTitle(driver);
    }

    public String getExpectedUsersPageTitle() {
        return readExcelData.get(2);
    }

    public List<String> getActualUsersList() {
        waitUtility.waitForVisibilityOfElement(driver, WaitUtility.LocatorType.Xpath, _usersSearch, WaitUtility.EXPLICIT_WAIT);
        List<WebElement> usersListWebElement = page.getWebElementList(driver, _usersSearch);
        List<String> usersList = new ArrayList<>();
        for (int i = 0; i < usersListWebElement.size(); i++) {
            usersList.add(page.getElementText(usersListWebElement.get(i)));
        }
        return usersList;
    }

    public String getActualUserIdAfterSearch() {
        waitUtility.waitForVisibilityOfElement(driver, WaitUtility.LocatorType.Xpath, _usersSearch, WaitUtility.EXPLICIT_WAIT_USER_NAME);
        List<WebElement> usersListWebElement = page.getWebElementList(driver, _usersSearch);
        String actualUserValue = page.getElementText(usersListWebElement.get(0));
        if (actualUserValue != " ") {
            return actualUserValue;
        } else {
            return " ";
        }
    }

    public String getExpectedUser() {
        return readExcelData.get(4);
    }

    public String getActualInvalidUserMessageAfterSearch() {
        waitUtility.waitForVisibilityOfElement(driver, WaitUtility.LocatorType.Xpath, _usersInvalidSearch, WaitUtility.EXPLICIT_WAIT_USER_NAME);
        List<WebElement> usersListWebElement = page.getWebElementList(driver, _usersInvalidSearch);
        String actualUserValue = page.getElementText(usersListWebElement.get(0));
        System.out.println(actualUserValue);
        return actualUserValue;
    }
    public String getExpectedInvalidUserMessageAfterSearch() {
        return readExcelData.get(8);

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

    public List<ArrayList<String>> getTableDataText() {
        waitUtility.waitForVisibilityOfElement(driver, WaitUtility.LocatorType.Xpath, _editButton, WaitUtility.EXPLICIT_WAIT_USER_NAME);
        return TableUtility.getGridData(rowElement, colElement);
    }

    public List<ArrayList<WebElement>> getTableDataWebElement() {
        return tableUtility.actionData(rowElement, colElement);
    }

    public boolean getTableDataContains(List<ArrayList<String>> tableData, String expectedUserName) {
        boolean value = false;
        for (int i = 0; i < tableData.size(); i++) {
            if (tableData.get(i).contains(expectedUserName)) {
                value = true;
                System.out.println("inside loop");
            }
        }
        return value;
    }

    public String getUserToBeDeleted() {
        return readExcelData.get(10);
    }

    public String getUserToBeViewed() {
        return readExcelData.get(12);
    }

    public EditUserPage clickOnEditButton(String userName) throws IOException {
        waitUtility.waitForVisibilityOfElement(driver, WaitUtility.LocatorType.Xpath, _editButton, WaitUtility.EXPLICIT_WAIT_USER_NAME);
        List<ArrayList<WebElement>> actionData = tableUtility.actionData(rowElement, colElement);
        if (!values)
            for (int i = 0; i < actionData.size(); i++) {
                for (int j = 0; j < actionData.get(0).size(); j++) {
                    WebElement data = actionData.get(i).get(j);

                    if (!values) {
                        String tData = data.getText();
                        if (tData.contains(userName)) {
                            editButton = driver.findElement(
                                    By.xpath("//table[@id='users_table']//tbody//tr[" + (i + 1) + "]//td[5]//a[1]"));
                            page.clickOnElement(editButton);
                            values = true;
                            break;
                        }
                    }
                }

            }
        return new EditUserPage(driver);
    }

    public DeleteUserPage clickOnDeleteButton(String userName) throws IOException {
        waitUtility.waitForVisibilityOfElement(driver, WaitUtility.LocatorType.Xpath, _deleteButton, WaitUtility.EXPLICIT_WAIT_USER_NAME);
        List<ArrayList<WebElement>> actionData = tableUtility.actionData(rowElement, colElement);
        if (!values)
            for (int i = 0; i < actionData.size(); i++) {
                for (int j = 0; j < actionData.get(0).size(); j++) {
                    WebElement data = actionData.get(i).get(j);

                    if (!values) {
                        String tData = data.getText();
                        if (tData.contains(userName)) {
                            deleteButton = driver.findElement(
                                    By.xpath(("//table[@id='users_table']//tbody//tr[" + (i + 1) + "]//td[5]//button")));
                            page.clickOnElement(deleteButton);
                            values = true;
                            break;
                        }
                    }
                }

            }
        return new DeleteUserPage(driver);

    }

    public ViewUserPage clickOnViewButton(String userName) throws IOException {
        waitUtility.waitForVisibilityOfElement(driver, WaitUtility.LocatorType.Xpath, _viewButton, WaitUtility.EXPLICIT_WAIT_USER_NAME);
        List<ArrayList<WebElement>> actionData = tableUtility.actionData(rowElement, colElement);
        if (!values)
            for (int i = 0; i < actionData.size(); i++) {
                for (int j = 0; j < actionData.get(0).size(); j++) {
                    WebElement data = actionData.get(i).get(j);

                    if (!values) {
                        String tData = data.getText();
                        if (tData.contains(userName)) {
                            viewButton = driver.findElement(
                                    By.xpath("//table[@id='users_table']//tbody//tr[" + (i + 1) + "]//td[5]//a[2]"));
                            page.clickOnElement(viewButton);
                            values = true;
                            break;
                        }
                    }
                }

            }
        return new ViewUserPage(driver);
    }

}
