package com.buffalocart.pages;

import com.buffalocart.utilities.TestHelperUtility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class DeleteRolesPage extends TestHelperUtility {
    WebDriver driver;
    public DeleteRolesPage(WebDriver driver){
        this.driver= driver;
        PageFactory.initElements(driver,this);
    }
}
