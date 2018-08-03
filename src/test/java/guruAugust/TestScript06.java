package guruAugust;

import static org.testng.Assert.assertTrue;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestScript06 {
	

	public static WebDriver driver;
	public static String sheetName = "testData";
	
	@BeforeMethod
	  public void setUp() throws Exception{
		  	System.setProperty("webdriver.chrome.driver", TestUtil.BROWSER_PATH);
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.get(TestUtil.BaseUrl+"/V4/");
			driver.manage().timeouts().implicitlyWait(TestUtil.WaitTime, TimeUnit.SECONDS);
	  }
	  
	
	  @DataProvider(name="GuruLoginData")
	  public Object[][] getCRMTestData(){
			Object data[][] = TestUtil.getTestData(sheetName);
			return data;
	}
	  
	
	  @Test(dataProvider="GuruLoginData")
	  public void GuruLoginTest(String username, String password) throws Exception {
		 
			driver.findElement(By.xpath("//input[@name='uid']")).clear();
			driver.findElement(By.xpath("//input[@name='uid']")).sendKeys(username);

			driver.findElement(By.xpath("//input[@name='password']")).clear();
			driver.findElement(By.xpath("//input[@name='password']")).sendKeys(password);
			
			driver.findElement(By.xpath("//input[@name='btnLogin']")).click();

		  
		  try {
			
			  Alert alert = driver.switchTo().alert();
			  String alertText = alert.getText();
			  Assert.assertEquals(TestUtil.EXPECTED_ERROR, alertText);
			  
			  File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			  FileUtils.copyFile(srcFile, new File(System.getProperty("user.dir")+"/screenshots/"+ System.currentTimeMillis() + ".jpg"));
			  
			  
		} catch (NoAlertPresentException e) {
			
			//String actual_Title = driver.getTitle();
			//Assert.assertEquals(TestUtil.EXPECTED_TITLE, actual_Title);
			
			
			
			String pageText = driver.findElement(By.tagName("tbody")).getText();
			
			String FIRST_PATTERN = "mngr";
			String SECOND_PATTERN = "[0-9]+";
			
			String[] parts = pageText.split(":");
			String dynamicText = parts[1];
			
			assertTrue(dynamicText.substring(1, 5).equals(FIRST_PATTERN));
			
			String remain = dynamicText.substring(dynamicText.length() - 4);

			assertTrue(remain.matches(SECOND_PATTERN));
			
			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			String currentDir = System.getProperty("user.dir");
			
			FileUtils.copyFile(scrFile, new File(currentDir + "/screenshots/" + System.currentTimeMillis() + ".png"));
			
			
		}
		  
	  }
	  
	  
	  
	  
	  @AfterMethod
	  public void tearDown() throws Exception {
		  driver.close();
	  }

}
