package excel_utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ExcelUtilities {

    public Map<String,String> getColumnData(String file, String sheetName) throws IOException {
        FileInputStream fis = new FileInputStream(file);
        Workbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheet(sheetName);
        int lastRowNum = sheet.getLastRowNum();

        Map<String,String> data = new HashMap<>();

        for (int i = 0; i <=lastRowNum; i++){
            Row row = sheet.getRow(i);
            Cell keyCell = row.getCell(0 );
            String key = keyCell.getStringCellValue().trim();
            Cell valueCell = row.getCell(1);
            String value = valueCell.getStringCellValue().trim();
            data.put(key,value);
        }
        return data;
    }

    public Map<String, String> getRowData(String file, String sheetName, String row) throws IOException {
        FileInputStream fis = new FileInputStream(file);
        Workbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheet(sheetName);
        int rowNum = Integer.parseInt(row);
        int lastColumnNum = sheet.getRow(0).getLastCellNum();

        Map<String,String> data = new HashMap<>();

        for (int i = 0; i<lastColumnNum; i++){
            String key;
            String value;

            try {
                key = sheet.getRow(0).getCell(i).getStringCellValue().trim();
            }catch (Exception e){
                try {
                    key = String.valueOf(sheet.getRow(0).getCell(i).getNumericCellValue()).replace(".0","");
                }catch (Exception ex2){
                    key = null;
                }
            }

            try{
                value = sheet.getRow((rowNum)).getCell(i).getStringCellValue().trim();
            } catch (Exception e){
                try {
                    value = String.valueOf(sheet.getRow((rowNum)).getCell(i).getNumericCellValue()).replace(".0", "");
                }catch (Exception e2){
                    value = null;
                }
            }

            data.put(key, value);
        }
         return data;
    }

}