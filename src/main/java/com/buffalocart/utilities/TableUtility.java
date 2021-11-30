package com.buffalocart.utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TableUtility {

    public static List<ArrayList<String>> getGridData(List<WebElement> rowItems, List<WebElement> columnItems)
    {

        int rSize = rowItems.size();
        int cSize = columnItems.size();
        int clistSize = (columnItems.size() / rowItems.size() -1)  ;
        System.out.println("rSize ="+rSize+"  cSize="+cSize+"  clistSize= "+clistSize);

        String[] columnList = new String[clistSize]; // 20/5 --->4
        List<ArrayList<String>> gridData = new ArrayList<ArrayList<String>>();
        int x = 0;
        int s = columnList.length;
        for (int i = 0; i < rowItems.size(); i++) {

            for (int j = 0; j < columnList.length; j++) {

                columnList[j] = columnItems.get(x).getText();

                x++;
            }
            x++;
            gridData.add(new ArrayList<String>(Arrays.asList(columnList)));

        }
        return gridData;
    }

    public  List<ArrayList<WebElement>> actionData(List<WebElement> rowItems, List<WebElement> columnItems)
    {


        int rSize = rowItems.size();
        int cSize = columnItems.size();
        int clistSize = (columnItems.size() / rowItems.size() )  ;
        System.out.println("rSize ="+rSize+"  cSize="+cSize+"  clistSize= "+clistSize);

        WebElement[] columnList = new WebElement[clistSize]; // 20/5 --->4
        List<ArrayList<WebElement>> gridData = new ArrayList<ArrayList<WebElement>>();
        int x = 0;
        int s = columnList.length;
        for (int i = 0; i < rowItems.size(); i++) {

            for (int j = 0; j < columnList.length; j++) {

                columnList[j] = columnItems.get(x);

                x++;
            }
            x++;
            gridData.add(new ArrayList<WebElement>(Arrays.asList(columnList)));

        }
        return gridData;
    }


}

