package utils;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Driver {
	
	public static WebDriver driver;
	
	public static WebDriver getBrowser() {
		if(config.browser.equalsIgnoreCase("chrome")) {
			// Set browser properties
			System.setProperty("webdriver.chrome.driver", "..\\VitAutomation\\Drivers\\chromedriver.exe");

			// Launch Browser
			driver = new ChromeDriver();

			// Maximize the application
			driver.manage().window().maximize();
			
			// Hit the URL of the application
			driver.get(config.url);
			
			// Implicitly Wait Statement
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		}
		
		return driver;
	}
	
	public static void quit() {
		System.out.println("Quitting the browser");
		driver.quit();
	}
}
