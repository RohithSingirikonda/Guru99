package guruAugust;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.File;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.sun.jna.platform.FileUtils;


public class TestUtil {

	WebDriver driver;
	
	public static final String BROWSER_PATH = System.getProperty("user.dir")+"/drivers/chromedriver.exe";

	public static String BaseUrl = "http://www.demo.guru99.com";
	
	public static final String UserName = "mngr146280";
	public static final String Password = "jahAqEq";
	
	
	public static int WaitTime = 20;
	
	public static String EXPECTED_TITLE = "Guru99 Bank Manager HomePage";
	public static String EXPECTED_ERROR = "User or Password is not valid";
	
	
	public static final String PATTERN = ":";
	public static final String FIRST_PATTERN = "mngr";
	public static final String SECOND_PATTERN = "[0-9]+"; 
	
	
	public static String TESTDATA_SHEET_PATH = System.getProperty("user.dir")+"/src/test/java/guruAugust/GuruData.xlsx";
	
	static Workbook book;
	static Sheet sheet;
	
	public static Object[][] getTestData(String sheetName) {
		FileInputStream file = null;
		try {
			file = new FileInputStream(TESTDATA_SHEET_PATH);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			book = WorkbookFactory.create(file);
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		sheet = book.getSheet(sheetName);
		Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		// System.out.println(sheet.getLastRowNum() + "--------" +
		// sheet.getRow(0).getLastCellNum());
		for (int i = 0; i < sheet.getLastRowNum(); i++) {
			for (int k = 0; k < sheet.getRow(0).getLastCellNum(); k++) {
				data[i][k] = sheet.getRow(i + 1).getCell(k).toString();
				// System.out.println(data[i][k]);
			}
		}
		return data;
	}
	
		
}
