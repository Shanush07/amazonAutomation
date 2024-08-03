package testNG;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import pom.POMTestClass1;
import utils.Driver;
import utils.WebLibrary;

public class TestClass1 {
    private WebDriver webdriver;
    private POMTestClass1 pomTestClass1;

    @BeforeSuite
    public void invokeBrowser() {
        // Initialize the WebDriver and set it in WebLibrary
        webdriver = Driver.getBrowser();
        WebLibrary.setDriver(webdriver);

        // Initialize the Page Object class
        pomTestClass1 = new POMTestClass1(webdriver);

        // Set implicit wait
        WebLibrary.waitForPageToLoad();
    }

    @Test
    public void urlValidation() {
        // Explicit wait for the URL to be the expected URL
        WebLibrary.waitForPageToLoad();
        String url = pomTestClass1.getCurrentUrl();
        Assert.assertEquals(url, "https://www.amazon.in/", "User has not accessed the Amazon website");
    }

    @Test
    public void titleValidation() {
        // Explicit wait for the page title to be the expected title
        WebLibrary.waitForPageToLoad();
        String pageTitle = pomTestClass1.getPageTitle();
        String expectedTitle = "Online Shopping site in India: Shop Online for Mobiles, Books, Watches, Shoes and More - Amazon.in";
        Assert.assertEquals(pageTitle, expectedTitle, "Page title is incorrect");
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() {
        // Quit the WebDriver
        if (webdriver != null) {
            webdriver.quit();
        }
    }
}
