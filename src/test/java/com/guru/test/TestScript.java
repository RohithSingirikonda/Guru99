package com.guru.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;

import com.qa.testbase.TestBase;
import com.qa.util.TestUtil;

public class TestScript extends TestBase{

	WebDriver driver;
	TestUtil testUtil;
	
	
  public TestScript(){
		super();
  }
	
	
  @BeforeMethod
  public void setUp() throws Exception{
		initialize();
  }

  
	@DataProvider(name = "LoginTestDetails")
	public Object[][] getNewToursTestData(){
		Object data[][] = TestUtil.getTestData("LoginDetails");
		return data;
	}
	
  
  @Test(dataProvider="LoginTestDetails")
  public void LoginTest(String username, String password) {
	  String actualBoxMsg;
		
	  driver.findElement(By.xpath("//input[@name='uid']")).sendKeys(username);;
	  driver.findElement(By.xpath("//input[@name='password']")).sendKeys(password);;
	  driver.findElement(By.xpath("//input[@name='btnLogin']")).click();
	  
	  try {
		
		  	Alert alt = driver.switchTo().alert();
			actualBoxMsg = alt.getText(); 
			alt.accept();
			assertEquals(actualBoxMsg,Util.EXPECT_ERROR);
			
	  } catch (NoAlertPresentException e) {
		
		    String pageText = driver.findElement(By.tagName("tbody")).getText();
			String[] parts = pageText.split(":");
			String dynamicText = parts[1];
			assertTrue(dynamicText.substring(1, 5).equals("mngr"));
			String remain = dynamicText.substring(dynamicText.length() - 4);
			assertTrue(remain.matches(Util.SECOND_PATTERN));
		
	  }	  
	  
  }
  
  
  @AfterMethod
  public void tearDown(){
	  driver.close();
  }

}
