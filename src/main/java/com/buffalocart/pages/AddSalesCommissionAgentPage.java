package com.buffalocart.pages;

import com.buffalocart.constants.Constants;
import com.buffalocart.utilities.TestHelperUtility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;
import java.util.List;

public class AddSalesCommissionAgentPage extends TestHelperUtility {
    WebDriver driver;
    public AddSalesCommissionAgentPage(WebDriver driver) throws IOException {
        this.driver= driver;
        PageFactory.initElements(driver,this);
    }
    List<String> readExcelData = excel.readExcel(Constants.EXCEL_FILE_PATH, Constants.SHEET_NAME_ADD_SALES_COMMISSION_AGENT_PAGE);

    private final String _surname="surname";
    @FindBy (id=_surname)
    private WebElement surname;
    private final String _firstName="first_name";
    @FindBy (id=_firstName)
    private WebElement firstName;
    private final String _lastName="last_name";
    @FindBy (id=_lastName)
    private WebElement lastName;
    private final String _email="email";
    @FindBy (id=_email)
    private WebElement email;
    private final String _contactNo="contact_no";
    @FindBy (id=_contactNo)
    private WebElement contactNo;
    private final String _address="address";
    @FindBy (id=_address)
    private WebElement address;
    private final String _cmmsnPercent="cmmsn_percent";
    @FindBy (id=_cmmsnPercent)
    private WebElement cmmsnPercent;
    private final String _saveButton="//button[@class='btn btn-primary']";
    @FindBy (xpath=_saveButton)
    private WebElement saveButton;

    public void enterValues(){
        page.enterText(surname,readExcelData.get(2));
        page.enterText(firstName,readExcelData.get(3));
        page.enterText(lastName,readExcelData.get(4));
        page.enterText(email,readExcelData.get(5));
        page.enterText(contactNo,readExcelData.get(6));
        page.enterText(address,readExcelData.get(7));
        page.enterText(cmmsnPercent,readExcelData.get(8));

    }
    public String getSCAAgent(){
        return readExcelData.get(9);
    }

    public SalesCommissionAgentPage saveButtonclick() throws IOException {
        page.clickOnElement(saveButton);
        return new SalesCommissionAgentPage(driver);
    }


}
