package testNG;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import pom.SearchPage;
import utils.Driver;
import utils.WebLibrary;

public class TestClass4 {
    WebDriver webdriver;
    SearchPage searchPage;

    @BeforeSuite
    public void invokeBrowser() {
        // Initialize the WebDriver and set it in WebLibrary
        webdriver = Driver.getBrowser();
        WebLibrary.setDriver(webdriver);
        searchPage = new SearchPage(webdriver);  // Initialize Page Object
    }

    @Test
    public void searchValidation() {
        // Perform the search operation
        searchPage.searchForMobile("mi mobile");
        searchPage.validateSearchResults();
        searchPage.filterByCustomerReview();
        searchPage.selectAndValidateMobile();
        searchPage.validateMobilePrice();
        searchPage.validateDeliveryLocation("632001");
        searchPage.validateSponsoredLink();
        searchPage.addToCartAndValidate();
        searchPage.scrollToTechnicalDetails();
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() {
        if (webdriver != null) {
            webdriver.quit();
        }
    }
}
