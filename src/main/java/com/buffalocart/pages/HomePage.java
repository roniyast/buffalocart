package com.buffalocart.pages;

import com.buffalocart.constants.Constants;
import com.buffalocart.utilities.TestHelperUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;
import java.util.List;

public class HomePage extends TestHelperUtility {

    WebDriver driver;
    List<String> readExcelData = excel.readExcel(Constants.EXCEL_FILE_PATH, Constants.SHEET_NAME_LOGIN_PAGE);

    public HomePage(WebDriver driver) throws IOException {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    private final String _EndTour="//button[@class='btn btn-default btn-sm']";
    @FindBy(xpath = _EndTour)
    private WebElement endTour;

    private final String _userAccountName ="//a[@class='dropdown-toggle']//span";
    @FindBy(xpath =_userAccountName )
    private WebElement userAccountName;

    private final String _userAccountSignOut ="//div[@class='pull-right']/a";
    @FindBy(xpath =_userAccountSignOut )
    private WebElement userAccountSignOut;

   public void clickOnEndTour(){endTour.click();}

    public String getActualUserAccountName(){
        return page.getElementText(userAccountName);
    }

    public String getExpectedUserAccountName(){
        return  readExcelData.get(8);
    }

    public void clickOnUserName(){
        page.clickOnElement(userAccountName);
    }

    public void userAccountSignOut(){
        page.clickOnElement(userAccountSignOut);
    }



}
