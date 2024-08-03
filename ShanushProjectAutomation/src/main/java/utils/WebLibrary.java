package utils;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebLibrary {

	private static WebDriver driver;
	private static WebDriverWait wait;

	// Initialize the WebDriver and WebDriverWait
	public static void setDriver(WebDriver webDriver) {
		driver = webDriver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Use a default value or adjust as needed
	}

	// Set an implicit wait for page load
	public static void waitForPageToLoad() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); // Adjust as needed
	}

	// Explicitly wait for an element to be clickable and return it
	public static WebElement waitForElementToBeClickable(By locator) {
		return wait.until(ExpectedConditions.elementToBeClickable(locator));
	}

	// Explicitly wait for an element to be visible and return it
	public static WebElement waitForElementToBeVisible(By locator) {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	// Check if an element is visible and return its displayed status
	public static boolean waitForElementToBeDisplayed(By locator) {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).isDisplayed();
	}

	// Wait for an element to be clickable and then click it
	public static void waitForElementAndClick(By locator) {
		WebElement element = waitForElementToBeClickable(locator);
		element.click();
	}

	// Send keys to an element after waiting for it to be visible
	public static void sendKeys(By locator, String keys) {
		WebElement element = waitForElementToBeVisible(locator);
		element.sendKeys(keys);
	}

	// Switch to a frame identified by the locator
	public static void switchToFrame(By locator) {
		WebElement frameElement = waitForElementToBeVisible(locator);
		driver.switchTo().frame(frameElement);
	}

	// Switch back to the parent frame
	public static void switchToParentFrame() {
		driver.switchTo().parentFrame();
	}

	// Switch to a new window
	public static void switchToNewWindow() {
		String parentWindow = driver.getWindowHandle();
		for (String window : driver.getWindowHandles()) {
			if (!parentWindow.equals(window)) {
				driver.switchTo().window(window);
				break;
			}
		}
	}

	// Pause execution for a specified amount of time
	public static void waitFor(int milliseconds) {
		try {
			Thread.sleep(milliseconds);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}

	// Execute JavaScript to scroll an element into view
	public static void scrollIntoView(WebElement element) {
		if (driver instanceof JavascriptExecutor) {
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		}
	}

	public static void waitForPageTitleToBe(String expectedTitle) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(config.globalWait));
		wait.until(ExpectedConditions.titleIs(expectedTitle));
	}

}
