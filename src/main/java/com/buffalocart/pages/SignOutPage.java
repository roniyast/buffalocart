package com.buffalocart.pages;

import com.buffalocart.utilities.TestHelperUtility;
import org.apache.commons.logging.Log;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;

public class SignOutPage extends TestHelperUtility {
    WebDriver driver;

    public SignOutPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    private final String _userAccountSignOut ="//div[@class='pull-right']/a";
    @FindBy(xpath =_userAccountSignOut )
    private WebElement userAccountSignOut;

    public LoginPage userAccountSignOut() throws IOException {
        page.clickOnElement(userAccountSignOut);
        return new LoginPage(driver);
    }

    public LoginPage userAccountSignOutUsingJS() throws IOException {
        page.findElementUsingJavaScript(driver,userAccountSignOut);
        return new LoginPage(driver);
    }
}
