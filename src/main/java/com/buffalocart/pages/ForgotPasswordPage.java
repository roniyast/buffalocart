package com.buffalocart.pages;

import com.buffalocart.constants.Constants;
import com.buffalocart.utilities.TestHelperUtility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;
import java.util.List;

public class ForgotPasswordPage<email> extends TestHelperUtility {
    WebDriver driver;
    List<String> readExcelData = excel.readExcel(Constants.EXCEL_FILE_PATH, Constants.SHEET_NAME_FORGOT_PASSWORD_PAGE);

    public ForgotPasswordPage(WebDriver driver) throws IOException {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    private final String _invalidUserName="email";
    @FindBy(id =_invalidUserName )
    public WebElement invalidUserName;

    private final String _forgotPasswordButton="//button[@class='btn btn-primary']";
    @FindBy(xpath =_forgotPasswordButton )
    public WebElement forgotPasswordButton;

    private final String _messageBlock="//span[@class='help-block']//strong";
    @FindBy(xpath =_messageBlock )
    public WebElement messageBlock;



}
