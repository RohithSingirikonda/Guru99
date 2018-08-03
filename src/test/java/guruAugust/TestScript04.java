package guruAugust;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestScript04 {
	

	public static WebDriver driver;
	public static Xls_Reader reader = new Xls_Reader(System.getProperty("user.dir")+"/src/test/java/guruAugust/GuruData.xlsx");	
	
	public static void setUp(){
		System.setProperty("webdriver.chrome.driver", TestUtil.BROWSER_PATH);
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(TestUtil.BaseUrl+"/V4/");
		driver.manage().timeouts().implicitlyWait(TestUtil.WaitTime, TimeUnit.SECONDS);
	}
	
	
	public static void main(String[] args) {
		
		int rowCount = reader.getRowCount("Data");
		
		for (int rowNum = 2; rowNum <=rowCount; rowNum++) {
			
			setUp();

			driver.findElement(By.xpath("//input[@name='uid']")).clear();
			driver.findElement(By.xpath("//input[@name='uid']")).sendKeys(reader.getCellData("Data", "username", rowNum));

			driver.findElement(By.xpath("//input[@name='password']")).clear();;
			driver.findElement(By.xpath("//input[@name='password']")).sendKeys(reader.getCellData("Data", "password", rowNum));  //status
			
			driver.findElement(By.xpath("//input[@name='btnLogin']")).click();

			
			try {
				
				Alert alert = driver.switchTo().alert();
				String alertText  = alert.getText();
				if (alertText.contains(reader.getCellData("Data", "expected", rowNum))) {
					reader.setCellData("Data", "actual", rowNum, alertText);
					reader.setCellData("Data", "status", rowNum, "Passed");
				} else {
					reader.setCellData("Data", "actual", rowNum, alertText);
					reader.setCellData("Data", "status", rowNum, "Failed");
				}
				
			} catch (NoAlertPresentException e) {
				
				String actualTitle = driver.getTitle();
				
				if (actualTitle.equals(reader.getCellData("Data", "expected", rowNum))) {
					reader.setCellData("Data", "actual", rowNum, actualTitle);
					reader.setCellData("Data", "status", rowNum, "Passed");
				} else {
					reader.setCellData("Data", "actual", rowNum, actualTitle);
					reader.setCellData("Data", "status", rowNum, "Failed");
				}
				
				
			}
			
			driver.close();
		}
		

	}

}
