package com.buffalocart.pages;

import com.buffalocart.constants.Constants;
import com.buffalocart.utilities.TestHelperUtility;
import com.buffalocart.utilities.WaitUtility;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;
import java.util.List;

public class NewUserPage extends TestHelperUtility {
    WebDriver driver;

    public NewUserPage(WebDriver driver) throws IOException {
      this.driver = driver;
      PageFactory.initElements(driver, this);
    }
    List<String> readExcelData = excel.readExcel(Constants.EXCEL_FILE_PATH, Constants.SHEET_NAME_NEW_USERS_PAGE);

    private final String _prefix = "surname";
    @FindBy(id = _prefix)
    private WebElement prefix;
    private final String _firstName = "first_name";
    @FindBy(id = _firstName)
    private WebElement firstName;
    private final String _firstNameError = "first_name-error";
    @FindBy(id = _firstNameError)
    private WebElement firstNameError;
    private final String _lastName = "last_name";
    @FindBy(id = _lastName)
    private WebElement lastName;
    private final String _email = "email";
    @FindBy(id = _email)
    private WebElement e_mail;
    private final String _role = "role";
    @FindBy(id = _role)
    private WebElement role;
    private final String _userName="username";
    @FindBy(id =_userName)
    private WebElement userName;
    private final String _password = "password";
    @FindBy(id = _password)
    private WebElement password;
    private final String _confirmPassword = "confirm_password";
    @FindBy(id = _confirmPassword)
    private WebElement confirmPassword;
    private final String _saveButton = "//button[@class='btn btn-primary pull-right']";
    @FindBy(xpath = _saveButton)
    WebElement saveButton;

    public String getPrefix() {
        return readExcelData.get(4);
    }

    public void enterPrefix(String prefixValue) {
        page.enterText(prefix, prefixValue);
    }

    public String getFirstName() {
        return readExcelData.get(5);
    }

    public void enterFirstName(String fName) {
        page.enterText(firstName, fName);
    }

    public String getLastName() {
        return readExcelData.get(6);
    }

    public void enterLastName(String lName) {
        page.enterText(lastName, lName);
        }

    public String getEmail() {
        return random.getRandomEmail();
    }

    public void enterEmail(String eMail) {
       page.enterText(e_mail,eMail);
    }

    public void selectRole() {
        waitUtility.waitForVisibilityOfElement(driver, WaitUtility.LocatorType.Id, _role);
        page.selectDropdownByIndex(role,Integer.parseInt(readExcelData.get(7)));
    }

    public String getUserName(){
        return readExcelData.get(8);
    }
    public void enterUserName(String uName){
        page.enterText(userName,uName);
    }

    public String getPassword() {
        return readExcelData.get(9);
    }

    public void enterPassword(String passwordValue) {
        page.enterText(password,passwordValue);
    }

    public String getConfirmPassword() {
        return readExcelData.get(10);
    }

    public void enterConfirmPassWord(String confirmPasswordValue) {
        page.enterText(confirmPassword, confirmPasswordValue);
    }

    public void clickOnSaveButton() throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.getElementById('submit_user_button').scrollIntoView();");
        page.clickOnElement(saveButton);

    }

    public String getActualErrorMessageForMandatoryField() throws InterruptedException {
        return page.getElementText(firstNameError);
    }

    public String getExpectedErrorMessageForMandatoryField(){
        return readExcelData.get(2);
    }
    public String getActualPageTitle() {
        return page.getPageTitle(driver);
    }

    public String getPageExpectedTitle() {
        return readExcelData.get(11);
    }

}