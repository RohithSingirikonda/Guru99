package guruAugustTestNG;

import guruAugust.TestUtil;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class AameritradeTest {

	public static WebDriver driver;
	public static String sheetName = "language";
	
	
	@BeforeMethod
	  public void setUp() throws Exception{
		  	System.setProperty("webdriver.chrome.driver", TestUtil.BROWSER_PATH);
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.get("https://invest.ameritrade.com/grid/p/login");
			driver.manage().timeouts().implicitlyWait(TestUtil.WaitTime, TimeUnit.SECONDS);
	  }
	  
	  
	  @DataProvider(name="languageTest")
	  public Object[][] getCRMTestData(){
			Object data[][] = TestUtil.getTestData(sheetName);
			return data;
	}
	
	  @Test(dataProvider="languageTest")
	  public void LoginTest(String language) throws Exception {
		
		  
		WebElement temp = driver.findElement(By.xpath("//a[text()='"+language+"']"));
		temp.click();  
		WebElement loginBtn = driver.findElement(By.xpath("//*[@class='loginBtnWrap']//span[contains(@widgetid,'loginButton')]"));
		
		  //Check English language
		if (language.contains("English")) {
			loginBtn.click();
		} 
		
		//checks second Language
		else if (language.contains("简体中文")) {
			loginBtn.click();
		}
		
		//Checks other language		
		else {
			loginBtn.click();
		}
		
	  }
	  
	  
	  
	  
	  @AfterMethod
	  public void tearDown() throws Exception {
		  driver.close();
	  }


}
