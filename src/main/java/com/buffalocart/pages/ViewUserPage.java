package com.buffalocart.pages;

import com.buffalocart.constants.Constants;
import com.buffalocart.utilities.TestHelperUtility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;
import java.util.List;

public class ViewUserPage extends TestHelperUtility {
    WebDriver driver;
    public ViewUserPage(WebDriver driver) throws IOException {
        this.driver= driver;
        PageFactory.initElements(driver,this);
    }

    List<String> readExcelData = excel.readExcel(Constants.EXCEL_FILE_PATH, Constants.SHEET_NAME_VIEW_USER_PAGE);

}
