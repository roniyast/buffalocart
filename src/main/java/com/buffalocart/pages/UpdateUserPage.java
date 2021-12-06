package com.buffalocart.pages;

import com.buffalocart.constants.Constants;
import com.buffalocart.utilities.TestHelperUtility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;
import java.util.List;

public class UpdateUserPage extends TestHelperUtility {

    WebDriver driver;
    public UpdateUserPage(WebDriver driver) throws IOException {
        this.driver= driver;
        PageFactory.initElements(driver, this);
    }
    List<String> readExcelData = excel.readExcel(Constants.EXCEL_FILE_PATH, Constants.SHEET_NAME_EDIT_USER_PAGE);

    private final String _emailEdit="email";
    @FindBy(id=_emailEdit)
    private WebElement emailEdit;

    private final String _updateButton= "submit_user_button";
    @FindBy(id=_updateButton)
    private WebElement updateButton;

    public String getValueEdit(){
        return readExcelData.get(6);
    }

    public void setValueEmail(){
        page.clearText(emailEdit);
        page.enterText(emailEdit,getValueEdit());
    }


    public String getDeleteUserSearch(){
        return readExcelData.get(10);
    }
    public UsersPage updateButtonClick() throws IOException {
        page.clickOnElement(updateButton);
        return new UsersPage(driver);
    }
    public String getActualEditUserPageTitle(){
        return page.getPageTitle(driver);
    }
    public String getExpectedEditUserPageTitle(){
        return readExcelData.get(2);
    }
}
