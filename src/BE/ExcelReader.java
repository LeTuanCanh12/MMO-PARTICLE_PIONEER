package BE;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelReader {
	public static String[] readExcelFile(String filePath) {
		List<String> dataList = new ArrayList<>();

		try (FileInputStream fis = new FileInputStream(filePath);
			Workbook workbook = new XSSFWorkbook(fis)) {

			// Đọc sheet đầu tiên từ workbook
			Sheet sheet = workbook.getSheetAt(0);

			// Lặp qua từng dòng trong sheet
			Iterator<Row> rowIterator = sheet.iterator();
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();

				// Lặp qua từng ô trong dòng
				Iterator<Cell> cellIterator = row.cellIterator();
				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();

					// Đọc giá trị từ ô
					String cellValue = getCellValue(cell);

					// Thêm giá trị vào danh sách
					dataList.add(cellValue);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Chuyển danh sách thành mảng các chuỗi
		String[] dataArray = dataList.toArray(new String[0]);
		// Trả về mảng chuỗi
		return dataArray;
	}

	private static String getCellValue(Cell cell) {

		return cell.getStringCellValue();

	}

}
