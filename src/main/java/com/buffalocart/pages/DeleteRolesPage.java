package com.buffalocart.pages;

import com.buffalocart.utilities.TestHelperUtility;
import com.buffalocart.utilities.WaitUtility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;

public class DeleteRolesPage extends TestHelperUtility {
    WebDriver driver;
    public DeleteRolesPage(WebDriver driver){
        this.driver= driver;
        PageFactory.initElements(driver,this);
    }

    private final String _deleteOkButton="//button[@class='swal-button swal-button--confirm swal-button--danger']";
    @FindBy(xpath = _deleteOkButton)
    private WebElement deleteOkButton;

    public RolesPage clickOnOkButton() throws IOException {
        waitUtility.waitForVisibilityOfElement(driver, WaitUtility.LocatorType.Xpath, _deleteOkButton, WaitUtility.EXPLICIT_WAIT_USER_NAME);
        page.clickOnElement(deleteOkButton);
        return new RolesPage(driver);
    }

}
