package utilities;

import java.io.FileInputStream;
import java.util.Date;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

//this is utility class


public class utility {

	public static String timestamp() {
		Date d = new Date();
		String datetext = d.toString().replace(" ", "_").replace(":", "_");
		return datetext + "@gmail.com";
	}

	public static Object[][] generateTestData(String sheetname) throws Exception {

		FileInputStream file = new FileInputStream(".//src/test/java//resources//testdata.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		XSSFSheet sheet = workbook.getSheet(sheetname);
		int rowcount = sheet.getLastRowNum();
		int colcount = sheet.getRow(0).getLastCellNum();
		Object[][] data = new Object[rowcount][colcount];
		for (int i = 0; i < rowcount; i++) {
			XSSFRow row = sheet.getRow(i + 1);
			for (int j = 0; j < colcount; j++) {
				XSSFCell cell = row.getCell(j);
				if (cell != null) {
					CellType celltype = cell.getCellType();
					switch (celltype) {
					case STRING:
						data[i][j] = cell.getStringCellValue();
						break;

					case NUMERIC:
						data[i][j] = Integer.toString((int) cell.getNumericCellValue());
						break;

					}
				}

				else
					data[i][j] = "";

			}
		}
		workbook.close(); // Close workbook
		file.close(); // Close FileInputStream
		return data;

	}
}
