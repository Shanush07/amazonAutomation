package testNG;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import pom.LoginPage;
import utils.Driver;
import utils.WebLibrary;
import utils.config;

public class TestClass3 {
    private WebDriver webdriver;
    private LoginPage loginPage;

    @BeforeSuite
    public void invokeBrowser() {
        // Initialize the WebDriver and set it in WebLibrary
        webdriver = Driver.getBrowser();
        WebLibrary.setDriver(webdriver);

        // Initialize the Page Object class
        loginPage = new LoginPage(webdriver);
    }

    @Test
    public void invalidLoginValidation() {
        // Navigate to the Sign-In page
        loginPage.navigateToSignInPage();

        // Enter invalid email and password
        loginPage.enterInvalidEmail("automation@gmail.com");
        loginPage.enterInvalidPassword("DummyPassword");

        // Validate the error message for invalid credentials
        String currentUrl = webdriver.getCurrentUrl();
        Assert.assertNotEquals(currentUrl, config.url, "Invalid Credentials Validation --> FAIL");
        System.out.println("Invalid Credentials Validation --> PASS");
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() {
        // Quit the WebDriver
        if (webdriver != null) {
            webdriver.quit();
        }
    }
}
