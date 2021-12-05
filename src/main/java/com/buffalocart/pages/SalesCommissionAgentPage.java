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

public class SalesCommissionAgentPage extends TestHelperUtility {
    WebDriver driver;
    boolean values;
    WebElement editButton,deleteButton;
    public SalesCommissionAgentPage(WebDriver driver) throws IOException {
        this.driver= driver;
        PageFactory.initElements(driver,this);
    }
    List<String> readExcelData = excel.readExcel(Constants.EXCEL_FILE_PATH, Constants.SHEET_NAME_SALES_COMMISSION_AGENT_PAGE);

    private final String _addSCAButton="//button[@class='btn btn-primary btn-modal pull-right']";
    @FindBy(xpath = _addSCAButton )
    private WebElement addSCAButton;

    private final String _SCASearch = "//table[@id='sales_commission_agent_table']//tr/td[@class='sorting_1']";
    @FindBy(xpath = _SCASearch)
    private WebElement SCASearch;

    private final String _SCASearchEmail = "//table[@id='sales_commission_agent_table']//tr/td";
    @FindBy(xpath = _SCASearchEmail)
    private WebElement SCASearchEmail;

    private final String _rElement = "//table[@id='sales_commission_agent_table']//tbody//tr";
    @FindBy(xpath = _rElement)
    private List<WebElement> rowElement;

    private final String _cElement = "//table[@id='sales_commission_agent_table']//tbody//tr//td";
    @FindBy(xpath = _cElement)
    private List<WebElement> colElement;


    private final String _editButton = "//button[@class='btn btn-xs btn-modal btn-primary']";

    private final String _deleteButton = "//button[@class='btn btn-xs btn-danger delete_commsn_agnt_button']";



    public AddSalesCommissionAgentPage clickAddSCAButton() throws IOException {
        page.clickOnElement(addSCAButton);
        return new AddSalesCommissionAgentPage(driver);
    }
    public String getActualSalesCommissionPageTitle(){
        return page.getPageTitle(driver);
    }

    public String getExpectedSalesCommissionPageTitle(){
        return readExcelData.get(2);
    }


    public List<String> getActualUsersList() {
        List<WebElement> SCAAgentsWebElement = page.getWebElementList(driver, _SCASearch);
        List<String> usersList = new ArrayList<>();
        for (int i = 0; i < SCAAgentsWebElement.size(); i++) {
            usersList.add(page.getElementText(SCAAgentsWebElement.get(i)));
        }
        System.out.println(usersList);
        return usersList;
    }
    public List<String> getActualAllValueList() {
        List<WebElement> SCAAgentsWebElement = page.getWebElementList(driver, _SCASearchEmail);
        List<String> usersList = new ArrayList<>();
        for (int i = 0; i < SCAAgentsWebElement.size(); i++) {
            usersList.add(page.getElementText(SCAAgentsWebElement.get(i)));
        }
        System.out.println(usersList);
        return usersList;
    }
    public boolean getTableDataContains(List<String> tableData, String expectedUserName) {
        boolean value = false;
            if (tableData.contains(expectedUserName)) {
                value = true;
            }
        return value;
    }
    public List<ArrayList<String>> getTableDataText() {
        waitUtility.waitForVisibilityOfElement(driver, WaitUtility.LocatorType.Xpath, _editButton, WaitUtility.EXPLICIT_WAIT_USER_NAME);
        return TableUtility.getGridData(rowElement, colElement);
    }

    public List<ArrayList<WebElement>> getTableDataWebElement() {
        return tableUtility.actionData(rowElement, colElement);
    }

    public String getSCAgentToUpdate(){
        return readExcelData.get(4);
    }
    public UpdateSalesCommissionAgentPage clickOnEditButton(String userName) throws IOException {
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
                                    By.xpath("//table[@id='sales_commission_agent_table']//tbody//tr[" + (i + 1) + "]//td[6]//button[1]"));
                            page.clickOnElement(editButton);
                            values = true;
                            break;
                        }
                    }
                }

            }
        return new UpdateSalesCommissionAgentPage(driver);
    }

    public DeleteSalesCommissionAgentPage clickOnDeleteButton(String userName) throws IOException {
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
                                    By.xpath(("//table[@id='sales_commission_agent_table']//tbody//tr[" + (i + 1) + "]//td[6]//button[2]")));
                            page.clickOnElement(deleteButton);
                            values = true;
                            break;
                        }
                    }
                }

            }
        return new DeleteSalesCommissionAgentPage(driver);

    }

}
