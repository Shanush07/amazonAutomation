package pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class POMTestClass1 {
    private WebDriver driver;

    // Constructor to initialize the WebDriver and PageFactory
    public POMTestClass1(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Method to get the current URL
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    // Method to get the page title
    public String getPageTitle() {
        return driver.getTitle();
    }
}
