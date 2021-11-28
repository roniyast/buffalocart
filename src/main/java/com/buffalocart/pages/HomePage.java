package com.buffalocart.pages;

import com.aventstack.extentreports.Status;
import com.buffalocart.constants.Constants;
import com.buffalocart.utilities.TestHelperUtility;
import com.buffalocart.utilities.WaitUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class HomePage extends TestHelperUtility {

    WebDriver driver;
    List<String> readExcelData = excel.readExcel(Constants.EXCEL_FILE_PATH, Constants.SHEET_NAME_HOME_PAGE);

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



    private final String _homePageDate="//div[@class='m-8 pull-left mt-15 hidden-xs']//strong";
    @FindBy(xpath = _homePageDate)
    private WebElement homePageDate;

   public void clickOnEndTour(){endTour.click();}

    public String getActualHomePageTitle(){
       return page.getPageTitle(driver);
    }
    public String getExpectedHomePageTitle(){
        return readExcelData.get(2);
    }
    public String getActualUserAccountName(){
        return page.getElementText(userAccountName);
    }

    public String getExpectedUserAccountName(){
        return  readExcelData.get(4);
    }

    public String getActualHomePageDate(){
       return page.getElementText(homePageDate);
   }

    public String getExpectedHomePageDate(){
        String eDate = new SimpleDateFormat("MM/dd/yyyy").format(new Date());
        return eDate;
   }
    public SignOutPage clickOnUserName() throws InterruptedException {
       Thread.sleep(1000);
        //waitUtility.waitForVisibilityOfElement(driver, WaitUtility.LocatorType.Xpath,_userAccountName);
        page.clickOnElement(userAccountName);
        return new SignOutPage(driver);
    }


}
