package com.qa.testbase;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import com.qa.util.TestUtil;

public class TestBase {
	
	public static WebDriver driver;
	public static Properties prop;
	
	public TestBase(){
		
		try{
			
			prop = new Properties();
			FileInputStream fip = new FileInputStream(System.getProperty("user.dir")+"/src/main/java/com/qa/config/config.properties");
			prop.load(fip);
			
		}catch(FileNotFoundException e){
			e.printStackTrace();			
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void initialize(){
		
		String browserName = prop.getProperty("browser");
		
		if (browserName.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/drivers/chromedriver.exe");
			driver = new ChromeDriver();
		} else if(browserName.equals("headlessbrowser")) {
			
			driver = new HtmlUnitDriver();
			
		}else {
			System.out.println("Please Assign the Browser property");
		}
		
		
		driver.manage().window().maximize();
		//driver.manage().timeouts().pageLoadTimeout(TestUtil.PageLoad_TimeOut, TimeUnit.MILLISECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.Implicit_Wait, TimeUnit.MILLISECONDS);
		driver.get(prop.getProperty("url"));
	}
	
	
	
	
	
	
	
	

}
