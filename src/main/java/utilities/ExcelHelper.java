package utilities;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelHelper {

	Workbook book;
	Sheet sheet;

	// open excel
	public void openExcel(String folderName, String fileName, String sheetName) {
		try {
			FileInputStream fis = new FileInputStream(GenericHelper.getFilePath("resources", "testcases.xlsx"));
			if (fileName.endsWith("xls")) {
				book = new HSSFWorkbook(fis);
			} else if (fileName.endsWith("xlsx")) {
				book = new XSSFWorkbook(fis);
			} else {
				fis.close();
				throw new RuntimeException("invalid file extension");
			}

			sheet = book.getSheet(sheetName);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	// count the number of rows
	public int getRows() {
		return sheet.getLastRowNum();
	}

	// count the number of columns
	public int getColumns() {
		return sheet.getRow(0).getLastCellNum();
	}

	// read the cell data at location
	public String readCell(int rnum, int cnum) {
		String data = "";
		Cell cell = sheet.getRow(rnum).getCell(cnum);
		try {
			CellType cellType = cell.getCellType();
			switch (cellType) {
			case STRING:
				data = cell.getStringCellValue();
				break;
			case NUMERIC:
				int value = (int) cell.getNumericCellValue();
				data = Integer.toString(value);
				break;
			default:
				break;
			}
		} catch (Exception e) {

		}
		return data;
	}

	// close excel
	public void closeExcel() {
		try {
			book.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

//	public static void main(String[] args) {
//		ExcelHelper excel = new ExcelHelper();
//		excel.openExcel("resources", "testcases.xlsx", "teststeps");
//		int nor = excel.getRows();
//		int noc = excel.getColumns();
//		for (int i = 1; i <= nor; i++) {
//			for (int j = 0; j < noc; j++) {
//				System.out.print(excel.readCell(i, j)+"\t");
//			}
//			System.out.println();
//		}
//		
//		excel.closeExcel();
//
//	}

}
