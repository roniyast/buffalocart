package com.buffalocart.utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class TableUtility {


    public List<String> tableManipulation(WebDriver driver, List<WebElement> rowWebElements, List<WebElement> cellWebElements) throws InterruptedException {
        /*List<String> tableCellValues = new ArrayList<>();
        for (int i = 0; i < rowWebElements.size(); i++) {
            List<WebElement> actualRowWebElement =cellWebElements;
            for (int j = 0; j < actualRowWebElement.size(); j++) {
                tableCellValues.add(actualRowWebElement.get(j).getText());
            }
        }
        return tableCellValues;*/
        Thread.sleep(3000);
        List<WebElement> rowElement = driver.findElements(By.xpath("//table[@id='users_table']//tr"));
        List<WebElement> columnElement = driver.findElements(By.xpath("//table[@id='users_table']//tr/td"));
        List<String> tableCellValues = new ArrayList<>();
        for (int i = 0; i < rowElement.size(); i++) {
            List<WebElement> actualRowWebElement = driver.findElements(By.xpath("//table[@id='users_table']//tr/td"));
            for (int j = 0; j < actualRowWebElement.size(); j++) {
                tableCellValues.add(actualRowWebElement.get(j).getText());
            }
        }
        System.out.println(tableCellValues);
        return tableCellValues;
    }
}

