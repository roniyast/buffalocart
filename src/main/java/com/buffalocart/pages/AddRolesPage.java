package com.buffalocart.pages;

import com.buffalocart.constants.Constants;
import com.buffalocart.utilities.TestHelperUtility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;
import java.util.List;

public class AddRolesPage extends TestHelperUtility {
    public AddRolesPage addRolesPage;
    WebDriver driver;
    public AddRolesPage(WebDriver driver) throws IOException {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
    List<String> readExcelData = excel.readExcel(Constants.EXCEL_FILE_PATH, Constants.SHEET_NAME_ADD_ROLES_PAGE);


    private final String _roleName ="name";
    @FindBy(id = _roleName)
    private WebElement roleName;

    private final String _selectAll ="//div[@class='col-md-2']//input[@class='check_all input-icheck']";
    @FindBy(xpath = _selectAll)
    private List<WebElement> selectAll;

    private final String _UserselectAll ="//*[@id='role_add_form']/div[3]/div[2]/div/label";
    @FindBy(xpath = _UserselectAll)
    private WebElement userSelectAll;


    private final String _RolesSelectAll ="//*[@id='role_add_form']/div[4]/div[2]/div/label";
    @FindBy(xpath = _RolesSelectAll)
    private WebElement rolesSelectAll;

    private final String _supplierSelectAll ="//*[@id='role_add_form']/div[5]/div[2]/div/label";
    @FindBy(xpath = _supplierSelectAll)
    private WebElement supplierSelectAll;


    private final String _customerSelectAll ="//*[@id='role_add_form']/div[6]/div[2]/div/label";
    @FindBy(xpath = _customerSelectAll)
    private WebElement customerSelectAll;

    private final String _SaveButton ="button[class='btn btn-primary pull-right']";
    @FindBy(css = _SaveButton)
    private WebElement saveButton;

    private final String _userMenu = "//li[@class='dropdown user user-menu']";
    @FindBy(xpath = _userMenu)
    private WebElement userMenu;

    public String getActualAddUsersPageTitle(){
        return page.getPageTitle(driver);
    }
    public String getExpectedAddUsersPageTitle(){
        return readExcelData.get(2);
    }
    public String getUserRole(){
        return readExcelData.get(4);
    }

    public void enterRoleName(String newRoleName) {
        page.enterText(roleName, newRoleName);
    }
    public void clickOnUserPermissionSelectAllCheckbox() {
        page.clickOnElement(userSelectAll);
    }
    public void clickOnRolesPermissionSelectAllCheckbox() {
        page.clickOnElement(rolesSelectAll);
    }
    public void clickOnSupplierPermissionSelectAllCheckbox() {
        page.clickOnElement(supplierSelectAll);
    }
    public void clickOnCustomerPermissionSelectAllCheckbox() {
        page.clickOnElement(customerSelectAll);
    }

    public RolesPage clickOnSaveButton() throws IOException {
        page.clickOnElement(saveButton);
        return new RolesPage(driver);
    }

}
