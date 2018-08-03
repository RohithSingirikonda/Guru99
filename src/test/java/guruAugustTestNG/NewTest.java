package guruAugustTestNG;

import static org.testng.Assert.assertTrue;
import guruAugust.TestUtil;
import guruAugust.Xls_Reader;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;

public class NewTest {
	
	public static WebDriver driver;
	public static String sheetName = "testDataA";
	
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
			  
		} catch (NoAlertPresentException e) {
			
			//String actual_Title = driver.getTitle();
			//Assert.assertEquals(TestUtil.EXPECTED_TITLE, actual_Title);
			
			
			
			String pageText = driver.findElement(By.tagName("tbody")).getText();

			// Extract the dynamic text mngrXXXX on page		
			String[] parts = pageText.split(TestUtil.PATTERN);
			String dynamicText = parts[1];

			// Check that the dynamic text is of pattern mngrXXXX
			// First 4 characters must be "mngr"
			assertTrue(dynamicText.substring(1, 5).equals(TestUtil.FIRST_PATTERN));
			// remain stores the "XXXX" in pattern mngrXXXX
			String remain = dynamicText.substring(dynamicText.length() - 4);
			// Check remain string must be numbers;
			assertTrue(remain.matches(TestUtil.SECOND_PATTERN));
			
		}
		  
	  }
	  
	  
	  
	  
	  @AfterMethod
	  public void tearDown() throws Exception {
		  driver.close();
	  }

}
