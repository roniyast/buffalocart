package com.buffalocart.utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class TableUtility extends TestHelperUtility{


    public List<String> tableManipulation(WebDriver driver ,List<WebElement> rowWebElements,List<WebElement> cellWebElements){
        List<String> tableCellValues = new ArrayList<>();
        for (int i = 0; i < rowWebElements.size(); i++) {
            List<WebElement> actualRowWebElement =rowWebElements;
            for (int j = 0; j < actualRowWebElement.size(); j++) {
                tableCellValues.add(actualRowWebElement.get(j).getText());
            }
        }
        return tableCellValues;
    }

}
