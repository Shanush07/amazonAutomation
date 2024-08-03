package testNG;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import pom.WishListPage;
import pom.PayModulePage;
import utils.Driver;
import utils.WebLibrary;

public class TestClass2 {
    private WebDriver webdriver;
    private WishListPage wishListPage;
    private PayModulePage payModulePage;

    @BeforeSuite
    public void invokeBrowser() {
        // Initialize the WebDriver and set it in WebLibrary
        webdriver = Driver.getBrowser();
        WebLibrary.setDriver(webdriver);

        // Initialize the Page Object classes
        wishListPage = new WishListPage(webdriver);
        payModulePage = new PayModulePage(webdriver);

        // Set implicit wait
        WebLibrary.waitForPageToLoad();
    }

    @Test
    public void wishlistValidation() {
        // Navigate to "Create a Wish List"
        wishListPage.navigateToCreateWishList();

        // Validate navigation to "Create a Wish List"
        boolean isWishListTabDisplayed = wishListPage.isWishListTabDisplayed();
        Assert.assertTrue(isWishListTabDisplayed, "Navigation to 'Create a Wishlist' --> FAIL");
        System.out.println("Navigation to 'Create a Wishlist' --> PASS");

        WebLibrary.waitFor(1500); // Use WebLibrary for sleep

        // Navigate back to the previous page
        wishListPage.navigateBack();
    }

    @Test
    public void paymoduleValidation() {
        // Click the Hamburger Menu
        payModulePage.clickHamburgerMenu();

        WebLibrary.waitFor(1500); // Use WebLibrary for sleep

        // Scroll until you reach the Amazon Pay Module
        payModulePage.navigateToAmazonPay();

        // Validate navigation to the Amazon Pay module
        boolean isAmazonPayModuleDisplayed = payModulePage.isAmazonPayModuleDisplayed();
        Assert.assertTrue(isAmazonPayModuleDisplayed, "Navigation to 'Amazon Pay' module --> FAIL");
        System.out.println("Navigation to 'Amazon Pay' module --> PASS");

        WebLibrary.waitFor(1500); // Use WebLibrary for sleep

        // Navigate back to the previous page
        payModulePage.navigateBack();
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() {
        // Quit the WebDriver
        if (webdriver != null) {
            webdriver.quit();
        }
    }
}
