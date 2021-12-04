package com.buffalocart.utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class WaitUtility {
    WebDriverWait wait;
    public static final long PAGE_LOAD_WAIT = 20;
    public static final long EXPLICIT_WAIT = 20;
    public static final long IMPLICIT_WAIT = 20;
    public static final long EXPLICIT_WAIT_USER_NAME = 6000;

    public enum LocatorType {
        Id, Xpath, CssSelector, LinkText, PartialLinkText, TagName, Name
    }

    public void waitForVisibilityOfElement(WebDriver driver, Enum locatorType, String target,long waitParameter) {
        wait = new WebDriverWait(driver, TimeUnit.SECONDS.toSeconds(waitParameter));
        if (locatorType.equals(LocatorType.Id)) {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(target)));
        } else if (locatorType.equals(LocatorType.Name)) {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(target)));
        } else if (locatorType.equals(LocatorType.Xpath)) {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(target)));
        } else if (locatorType.equals(LocatorType.TagName)) {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName(target)));
        } else if (locatorType.equals(LocatorType.CssSelector)) {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(target)));
        } else if (locatorType.equals(LocatorType.LinkText)) {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(target)));
        } else if (locatorType.equals(LocatorType.PartialLinkText)) {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText(target)));
        }
        else{

        }
    }
    @Deprecated
    public void elementToBeClickable(WebDriver driver,  long explicitWaitUserName) {
            driver.manage().timeouts().implicitlyWait(EXPLICIT_WAIT_USER_NAME,TimeUnit.SECONDS) ;
        }


}
