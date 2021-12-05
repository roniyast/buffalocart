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

public class RolesPage extends TestHelperUtility {
    WebDriver driver;
    List<String> readExcelData = excel.readExcel(Constants.EXCEL_FILE_PATH, Constants.SHEET_NAME_ROLES_PAGE);
    boolean values;
    WebElement updateRolesButton,deleteRolesButton;
    public RolesPage (WebDriver driver) throws IOException {
        this.driver= driver;
        PageFactory.initElements(driver,this);
    }

    private final String _addRolesButton="//a[@class='btn btn-block btn-primary']";
    @FindBy(xpath = _addRolesButton)
    private WebElement addRolesButton;

    private final String _updateRolesButton="//td//a[@class='btn btn-xs btn-primary']";

    private final String _deleteRolesButton ="//td//button[@class='btn btn-xs btn-danger delete_role_button']";

    private final String _rElement = "//table[@id='roles_table']//tbody//tr";
    @FindBy(xpath = _rElement)
    private List<WebElement> rowElement;

    private final String _cElement = "//table[@id='roles_table']//tbody//tr//td";
    @FindBy(xpath = _cElement)
    private List<WebElement> colElement;


    public String getActualRolesPageTitle(){
        return page.getPageTitle(driver);
    }

    public String getExpectedRolesPageTitle(){
        return readExcelData.get(2);
    }

    public AddRolesPage addRolesButtonClick() throws IOException {
        page.clickOnElement(addRolesButton);
        return new AddRolesPage(driver);
    }

    public String getRoleToUpdateAndDelete(){
        return readExcelData.get(4);
    }
    public List<ArrayList<String>> getTableDataText() {
        waitUtility.waitForVisibilityOfElement(driver, WaitUtility.LocatorType.Xpath, _updateRolesButton, WaitUtility.EXPLICIT_WAIT_USER_NAME);
        return TableUtility.getGridData(rowElement, colElement);
    }
    public boolean getTableDataContains(List<ArrayList<String>> tableData, String expectedUserName) {
        boolean value = false;
        for (int i = 0; i < tableData.size(); i++) {
            if (tableData.get(i).contains(expectedUserName)) {
                value = true;
            }
        }
        return value;
    }

    public UpdateRolesPage clickOnUpdateButton(String userName) throws IOException {
        waitUtility.waitForVisibilityOfElement(driver, WaitUtility.LocatorType.Xpath,_updateRolesButton, WaitUtility.EXPLICIT_WAIT_USER_NAME);
        List<ArrayList<WebElement>> actionData = tableUtility.actionData(rowElement, colElement);
        if (!values)
            for (int i = 0; i < actionData.size(); i++) {
                for (int j = 0; j < actionData.get(0).size(); j++) {
                    WebElement data = actionData.get(i).get(j);

                    if (!values) {
                        String tData = data.getText();
                        if (tData.contains(userName)) {
                            updateRolesButton = driver.findElement(
                                    By.xpath("//table[@id='roles_table']//tbody//tr["+(i+1)+"]//td//i"));
                            page.clickOnElement(updateRolesButton);
                            values = true;
                            break;
                        }
                    }
                }

            }
        return new UpdateRolesPage(driver);

    }
    public DeleteRolesPage clickOnDeleteButton(String userName) throws IOException {
        waitUtility.waitForVisibilityOfElement(driver, WaitUtility.LocatorType.Xpath,_deleteRolesButton, WaitUtility.EXPLICIT_WAIT_USER_NAME);
        List<ArrayList<WebElement>> actionData = tableUtility.actionData(rowElement, colElement);
        if (!values)
            for (int i = 0; i < actionData.size(); i++) {
                for (int j = 0; j < actionData.get(0).size(); j++) {
                    WebElement data = actionData.get(i).get(j);

                    if (!values) {
                        String tData = data.getText();
                        if (tData.contains(userName)) {
                            deleteRolesButton = driver.findElement(
                                    By.xpath(("//table[@id='roles_table']//tbody//tr["+(i + 1) +"]//td//button")));
                            page.clickOnElement(deleteRolesButton);
                            values = true;
                            break;
                        }
                    }
                }

            }
        return new DeleteRolesPage(driver);
    }

}
