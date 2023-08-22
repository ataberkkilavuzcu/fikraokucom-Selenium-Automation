package DataStore;

import java.io.FileInputStream;
import java.io.FileOutputStream;


import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelWriting {

    public static int rowI = 1;
    String path = "/Users/ataberk/Desktop/projects/fikracom automation/fikracomautomation/jokes/Jokes.xlsx";
    
    public ExcelWriting(){

    }

    public void writeExcel(String sheetName, int rowIndex, int colIndex, String data) {
        try {
            FileInputStream fileInput = new FileInputStream(path);
            Workbook workbook = WorkbookFactory.create(fileInput);
            Sheet sheet = workbook.getSheet(sheetName);

            if (sheet == null) {
                // If the sheet doesn't exist
                System.out.println("Sheet not found: " + sheetName);
                return;
            }

            Row row = sheet.getRow(rowIndex);
            if (row == null) {
                row = sheet.createRow(rowIndex);
            }

            Cell cell = row.createCell(colIndex);
            cell.setCellValue(data);

            FileOutputStream fileOutput = new FileOutputStream(path);
            workbook.write(fileOutput);
            fileOutput.close(); // Don't forget to close the output stream



        } catch (Exception e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }
    }
    
}
