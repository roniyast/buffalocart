package com.buffalocart.pages;

import com.buffalocart.constants.Constants;
import com.buffalocart.utilities.TestHelperUtility;
import org.openqa.selenium.WebDriver;
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
    public String getActualUpdateRolePageTitle(){
        return page.getPageTitle(driver);
    }
    public String getExpectedUpdateRolePageTitle(){
        return readExcelData.get(2);
    }
}
