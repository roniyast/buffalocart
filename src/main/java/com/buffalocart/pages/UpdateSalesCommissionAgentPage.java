package com.buffalocart.pages;

import com.buffalocart.constants.Constants;
import com.buffalocart.utilities.TestHelperUtility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;
import java.util.List;

public class UpdateSalesCommissionAgentPage extends TestHelperUtility {
    WebDriver driver;
    public UpdateSalesCommissionAgentPage(WebDriver driver) throws IOException {
        this.driver= driver;
        PageFactory.initElements(driver,this);
    }
    List<String> readExcelData = excel.readExcel(Constants.EXCEL_FILE_PATH, Constants.SHEET_NAME_UPDATE_SALES_COMMISSION_AGENT_PAGE);

    private final String _email="email";
    @FindBy(id=_email)
    private WebElement email;

    private final String _saveButton="//button[@class='btn btn-primary']";
    @FindBy (xpath=_saveButton)
    private WebElement saveButton;

    public String getExpectedSCAValuetoUpdate(){
        return readExcelData.get(2);
    }

    public void updateSCAValue() throws IOException {
        page.clearText(email);
        page.enterText(email,getExpectedSCAValuetoUpdate());
    }

    public SalesCommissionAgentPage saveButtonclick() throws IOException {
        page.clickOnElement(saveButton);
        return new SalesCommissionAgentPage(driver);
    }



}
