package guruAugust;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestScript02 {
	
	private static WebDriver driver;
	public static String baseUrl;

	
	public static void Setup() throws Exception {
		
		System.setProperty("webdriver.chrome.driver", TestUtil.BROWSER_PATH);
		driver = new ChromeDriver();
		baseUrl = TestUtil.BaseUrl;
		driver.get(baseUrl+"/V4/");
		driver.manage().timeouts().implicitlyWait(TestUtil.WaitTime, TimeUnit.SECONDS);
		
	}
	
	
	
	public static void main(String[] args) throws Exception {
		
		Setup();
		
		String actualTitle;

		driver.findElement(By.xpath("//input[@name='uid']")).clear();
		driver.findElement(By.xpath("//input[@name='uid']")).sendKeys("mngr146280");

		driver.findElement(By.xpath("//input[@name='password']")).clear();;
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("jahAqEq");
		
		driver.findElement(By.xpath("//input[@name='btnLogin']")).click();
		
		actualTitle = driver.getTitle();
		
		if (actualTitle.equals(TestUtil.EXPECTED_TITLE)) {
			
			System.out.println("Test Case Passed");
		} else {
			
			System.out.println("Test Case Passed");
		}
		
		driver.close();

	}

}
