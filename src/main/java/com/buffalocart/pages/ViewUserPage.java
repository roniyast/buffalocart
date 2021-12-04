package com.buffalocart.pages;

import com.buffalocart.constants.Constants;
import com.buffalocart.utilities.TestHelperUtility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ViewUserPage extends TestHelperUtility {
    WebDriver driver;

    public ViewUserPage(WebDriver driver) throws IOException {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    List<String> readExcelData = excel.readExcel(Constants.EXCEL_FILE_PATH, Constants.SHEET_NAME_VIEW_USER_PAGE);

    private final String _userData = "//div[@class='col-md-6']/p[3]";
    @FindBy(xpath = _userData)
    private List<WebElement> userData;

    public List<String> getViewUserDetails() throws InterruptedException {
        Thread.sleep(6000);
        List<WebElement> user = page.getWebElementList(driver, _userData);
        List<String> userDetails = new ArrayList<>();
        userDetails.add(user.get(0).getText());
        System.out.println(userDetails);
        return userDetails;
    }

    public String getUserToCheck() {
        return readExcelData.get(2);
    }

    public boolean checkContainsInViewUser(List<String> userDetails, String userToSearch) {
        System.out.println(userToSearch);
        boolean value = false;
        if (userDetails.get(0).equals(userToSearch)) {
            value = true;
        }
        return value;
    }
}

