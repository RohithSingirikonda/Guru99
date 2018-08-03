package guruAugust;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestScript01 {

	private static WebDriver driver;
	
	public static void main(String[] args) throws Exception {
		
		System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"/drivers/chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("http://www.demo.guru99.com/V4/");

		driver.findElement(By.xpath("//input[@name='uid']")).sendKeys("mngr146280");
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("jahAqEq");
		driver.findElement(By.xpath("//input[@name='btnLogin']")).click();
		

	}

}
