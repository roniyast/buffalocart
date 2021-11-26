package com.buffalocart.utilities;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExcelUtility {
    public static HSSFWorkbook wb;
    public static HSSFSheet sh;
    public static FileInputStream f;

    public List<String> readExcel(String excel, String sheetName) throws IOException {

        String path = System.getProperty("user.dir") + File.separator + excel;
        List<String> list = new ArrayList<String>();
        DataFormatter formatter = new DataFormatter();
        FileInputStream file = new FileInputStream(path);
        HSSFWorkbook workbook = new HSSFWorkbook(file);
        HSSFSheet sheet = workbook.getSheet(sheetName);
        for (Row r : sheet) {
            for (Cell c : r) {
                list.add(formatter.formatCellValue(c));
            }
        }
        return list;
    }
}
