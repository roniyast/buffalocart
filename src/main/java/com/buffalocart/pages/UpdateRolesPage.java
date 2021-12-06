package com.buffalocart.pages;

import com.buffalocart.constants.Constants;
import com.buffalocart.utilities.TestHelperUtility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;
import java.util.List;

public class UpdateRolesPage extends TestHelperUtility {
    WebDriver driver;
    List<String> readExcelData = excel.readExcel(Constants.EXCEL_FILE_PATH, Constants.SHEET_NAME_UPDATE_ROLES_PAGE);
    public UpdateRolesPage(WebDriver driver) throws IOException {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
    public final String _selectRoles="//form[@id='role_form']/div[3]/div[2]/div/label";
    @FindBy(xpath = _selectRoles)
    private WebElement selectRoles;

    public final String _Status="//form[@id='role_form']/div[3]/div[2]/div/label/div";
    @FindBy(xpath = _Status)
    private WebElement status;

    private final String _updateButton ="button[class='btn btn-primary pull-right']";
    @FindBy(css = _updateButton)
    private WebElement updateButton;


    public void clickOnRolesPermissionSelectAllCheckbox() {
        page.clickOnElement(selectRoles);
    }
    public String getActualUpdateRolePageTitle(){
        return page.getPageTitle(driver);
    }
    public String getExpectedUpdateRolePageTitle(){
        return readExcelData.get(2);
    }

    public RolesPage clickOnUpdateButton() throws IOException {
        page.clickOnElement(updateButton);
        return new RolesPage(driver);
    }

    public String getActualStatus(){
        return page.getAttributeValue(status,"aria-checked");
    }
    public String getExpectedStatus(){
        return "true";
    }
}
