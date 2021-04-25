package utilities;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ExcelUtility {

    static XSSFWorkbook workbook;
    static XSSFSheet sheet;

    public static Object[][] getTestData(String path, String sheetName) {

        FileInputStream file = null;
        //FileOutputStream fileOutputStream;
        try {
            file = new FileInputStream(path);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            workbook = new XSSFWorkbook(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        sheet = workbook.getSheet(sheetName);
        //Object[4][2]
        Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
        for (int i = 0; i < sheet.getLastRowNum(); i++) {
            for (int j = 0; j < sheet.getRow(0).getLastCellNum(); j++) {
                data[i][j] = sheet.getRow(i + 1).getCell(j).getStringCellValue();
                //data[0][0] = getCell(0), getCell(1),1<4, getRow(2).getCell(1).getStringCellValue()
            }
        }
        return data;
    }
}
