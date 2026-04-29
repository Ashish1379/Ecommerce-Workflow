package utils;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import config.ConfigReader;

public class ExcelUtils {
	private Workbook workbook;
	private Sheet sheet;
	private FileInputStream fis;
	private DataFormatter formatter;
	private String[][] data;
	private ConfigReader configReader;

	// in constructor creating objects of config reader , fileinnputstream and
	// workbook variables
	// getting filepath using configReader methods and opening the workbook

	public ExcelUtils(String sheetName) throws IOException {
		configReader = new ConfigReader();
		String filepath = configReader.getExcelFilePath();
		fis = new FileInputStream(filepath);
		workbook = new XSSFWorkbook(fis);
		sheet = workbook.getSheet(sheetName);
	}

	// return total rows in sheet

	public int getRowCount() {
		return sheet.getPhysicalNumberOfRows();
	}

	// return total column the passed row number

	public int getColCount() {
		return sheet.getRow(0).getPhysicalNumberOfCells();
	}

	// reading the excel file and then sending the data in 2D string array

	public void getCellData() {
		formatter = new DataFormatter();
		data = new String[getRowCount() - 1][getColCount()];
		for (int i = 1; i < getRowCount(); i++) {
			Row row = sheet.getRow(i);
			for (int j = 0; j < getColCount(); j++) {
				Cell cell = row.getCell(j);
				data[i - 1][j] = formatter.formatCellValue(cell);
//				System.out.print(data[i][j] + " ");
			}
		}
	}

	// function for closing the open files

	public void closeExcelFile() throws IOException {
		workbook.close();
		fis.close();
	}

	// getting data from excel after reading and then closing the files

	public String[][] getData() throws IOException {
		getCellData();
		closeExcelFile();
		return data;
	}

}
