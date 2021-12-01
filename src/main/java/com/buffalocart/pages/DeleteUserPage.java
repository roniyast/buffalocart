package com.buffalocart.pages;

import com.buffalocart.constants.Constants;
import com.buffalocart.utilities.TestHelperUtility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;
import java.util.List;

public class DeleteUserPage extends TestHelperUtility {
    WebDriver driver;
    public DeleteUserPage(WebDriver driver) throws IOException {
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }
    List<String> readExcelData = excel.readExcel(Constants.EXCEL_FILE_PATH, Constants.SHEET_NAME_DELETE_USER_PAGE);

    private final String _deleteOkButton="//button[@class='swal-button swal-button--confirm swal-button--danger']";
    @FindBy(xpath = _deleteOkButton)
    private WebElement deleteOkButton;

    public UsersPage clickOnOkButton() throws IOException {
        page.clickOnElement(deleteOkButton);
        return new UsersPage(driver);
    }

}
