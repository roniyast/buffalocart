package com.buffalocart.pages;

import com.buffalocart.constants.Constants;
import com.buffalocart.utilities.TestHelperUtility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;
import java.util.List;

public class LoginPage extends TestHelperUtility {

    WebDriver driver;
    List<String> readExcelData = excel.readExcel(Constants.EXCEL_FILE_PATH, Constants.SHEET_NAME_LOGIN_PAGE);

    public LoginPage(WebDriver driver) throws IOException {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    private final String _userName ="username";
    @FindBy(id = _userName)
    private WebElement userName;

    private final String _password ="password";
    @FindBy(id = _password)
    private WebElement password;

    private final String _rememberMe ="//div[@class='checkbox']//input[@name='remember']";
    @FindBy(xpath = _rememberMe)
    private WebElement rememberMe;

    private final String _invalidUserMessage ="//span[@class='help-block']//strong";
    @FindBy(xpath = _invalidUserMessage)
    private WebElement invalidUserMessage;

    private final String _loginButton ="//button[@type='submit']";
    @FindBy(xpath = _loginButton)
    private WebElement loginButton;

    private final String _forgotPassword ="//a[@class='btn btn-link']";
    @FindBy(xpath = _forgotPassword)
    private WebElement forgotPassword;



    public String getActualLoginPageTitle() {
        return page.getPageTitle(driver);
    }

    public String getExpectedLoginPageTitle() throws IOException {
       return readExcelData.get(2);
    }
    public String getUsernameLogin(){
        return readExcelData.get(4);
    }
    public String getPasswordLogin(){

        return readExcelData.get(6);
    }

    public String getInvalidUsernameLogin(){
        return readExcelData.get(10);
    }
    public String getInvalidPasswordLogin(){

        return readExcelData.get(12);
    }


    public void enterUserNameLogin(String userNameToEnter) {
        page.enterText(userName, userNameToEnter);
    }

    public void enterPasswordLogin(String passwordToEnter) {
        page.enterText(password, passwordToEnter);
    }

    public void enterInvalidUserNameLogin(String userNameToEnter) {
        page.enterText(userName, userNameToEnter);
    }

    public void enterInvalidPasswordLogin(String passwordToEnter) {
        page.enterText(password, passwordToEnter);
    }

    public void RememberMeLoginCheck(String rememberMeToCheck) {
        if (rememberMeToCheck.equals("TRUE")) {
            page.clickOnElement(rememberMe);
        }
    }
   public HomePage loginButtonClick() throws IOException {
        page.clickOnElement(loginButton);
        return new HomePage(driver);
    }
    public ForgotPasswordPage clickOnForgotPassword() throws IOException {
        page.clickOnElement(forgotPassword);
        return new ForgotPasswordPage(driver);
    }
    public String getActualInvalidUserMessage() throws IOException {
        return page.getElementText(invalidUserMessage);
    }
    public String getExpectedInvalidUserMessage() throws IOException {
        return readExcelData.get(14);
    }

    public String getExpectedRememberMeCheckBoxStatus(){
        return readExcelData.get(16);
    }
    public boolean getActualRememberMeCheckBoxStatus(){
        return page.isElementSelected(rememberMe);
    }

}
