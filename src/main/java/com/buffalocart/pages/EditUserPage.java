package com.buffalocart.pages;

import com.buffalocart.constants.Constants;
import com.buffalocart.utilities.TestHelperUtility;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.util.List;

public class EditUserPage extends TestHelperUtility {

    WebDriver driver;
    public EditUserPage(WebDriver driver) throws IOException {
        this.driver= driver;
    }
    List<String> readExcelData = excel.readExcel(Constants.EXCEL_FILE_PATH, Constants.SHEET_NAME_EDIT_USER_PAGE);


}
