package pom;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PayModulePage {
    private WebDriver driver;

    // Constructor to initialize the WebDriver and PageFactory
    public PayModulePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Page-specific WebElements
    @FindBy(xpath = "//a[@id=\"nav-hamburger-menu\"]")
    private WebElement hamburgerMenu;

    @FindBy(xpath = "//ul[@class='hmenu hmenu-visible']/li/a[text()='Amazon Pay']")
    private WebElement amazonPay;

    @FindBy(xpath = "//span[text()='Your Transactions']")
    private WebElement amazonPayModule;

    // Method to click the Hamburger Menu
    public void clickHamburgerMenu() {
        hamburgerMenu.click();
    }

    // Method to scroll to and click Amazon Pay
    public void navigateToAmazonPay() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", amazonPay);
        amazonPay.click();
    }

    // Method to check if the Amazon Pay module is displayed
    public boolean isAmazonPayModuleDisplayed() {
        return amazonPayModule.isDisplayed();
    }

    // Method to navigate back
    public void navigateBack() {
        driver.navigate().back();
    }
}
