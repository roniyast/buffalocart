package com.buffalocart.utilities;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class PageUtility {
    Actions action;
    Alert alert;
    Select select;
    List list;

    public void clickOnElement(WebElement element) {
        element.click();
    }

    public void enterText(WebElement element, String value) {
        element.sendKeys(value);
    }

    public String getAttributeValue(WebElement element, String attribute) {

        return element.getAttribute(attribute);
    }

    public String getPageTitle(WebDriver driver) {
        return driver.getTitle();
    }

    public List<WebElement> getWebElementList(WebDriver driver,String xpath){
        return driver.findElements(By.xpath(xpath));
    }

    public boolean isElementDisplayed(WebElement element) {
        return element.isDisplayed();
    }

    public boolean isElementSelected(WebElement element) {
        return element.isSelected();
    }

    public boolean isElementEnabled(WebElement element) {
        return element.isEnabled();
    }

    public void simpleAlert(WebDriver driver) {
        alert = driver.switchTo().alert();
        alert.accept();

    }

    public void confirmationAlert(WebDriver driver, String action) {
        alert = driver.switchTo().alert();
        if (action.equals("accept")) {
            alert.accept();
        } else if (action.equals("dismiss")) {
            alert.dismiss();
        } else {
            System.out.println("No Action is selected");
        }
    }

    public void promptAlert(WebDriver driver, String message, String action) {
        alert = driver.switchTo().alert();
        alert.sendKeys(message);
        if (action.equals("accept")) {
            alert.accept();
        } else if (action.equals("dismiss")) {
            alert.dismiss();
        } else {
            System.out.println("No Action is selected");
        }
    }

    public String getDataFromAlert(WebDriver driver) {
        alert = driver.switchTo().alert();
        return alert.getText();
    }

    public void moveToElement(WebElement element, WebDriver driver) {
        action = new Actions(driver);
        action.moveToElement(element);
    }

    public void doubleClick(WebElement element, WebDriver driver) {
        action = new Actions(driver);
        action.doubleClick();
    }

    public void rightClick(WebElement element, WebDriver driver) {
        action = new Actions(driver);
        action.contextClick(element).build().perform();
    }

    public void clickAndHold(WebDriver driver, WebElement element) {
        action = new Actions(driver);
        action.clickAndHold(element).dragAndDropBy(element, 500, 500).build().perform();
    }

    public void dragAndDrop(WebDriver driver, WebElement dragElement, WebElement dropElement) {
        action = new Actions(driver);
        action.dragAndDrop(dragElement, dropElement).build().perform();
    }

    public void selectDropdownByIndex(WebElement element, int index) {
        select = new Select(element);
        select.selectByIndex(index);
    }

    public void selectDropdownByWebElement(WebElement element, String value) {
        select = new Select(element);
        select.selectByValue(value);
    }

    public void selectDropdownByValue(WebElement element, int value) {
        select = new Select(element);
        select.selectByIndex(value);
    }

    public List<WebElement> selectGetOption(WebElement element) {
        select = new Select(element);
        list = new ArrayList();
        list.add(select.getOptions());
        return list;
    }

    public String getElementText(WebElement element) {
        return element.getText();
    }

}
