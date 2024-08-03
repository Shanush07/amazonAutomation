package pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import utils.WebLibrary;

public class SearchPage {
    WebDriver driver;

    // PageFactory initialization
    public SearchPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Page specific WebElements
    @FindBy(id = "twotabsearchtextbox")
    private WebElement searchBox;

    @FindBy(id = "nav-search-submit-button")
    private WebElement searchButton;

    @FindBy(xpath = "//span[@class='a-color-state a-text-bold']")
    private WebElement searchResultLabel;

    @FindBy(xpath = "//div[3]/ul[1]/span[1]/span[1]/li[1]/span[1]/a[1]")
    private WebElement customerReviewFilter;

    @FindBy(xpath = "//span[@class='a-size-medium a-color-base a-text-normal' and text()='Redmi 13C (Starfrost White, 4GB RAM, 128GB Storage) | Powered by 4G MediaTek Helio G85 | 90Hz Display | 50MP AI Triple Camera']")
    private WebElement desiredMobile;

    @FindBy(xpath = "//span[@class='a-price aok-align-center reinventPricePriceToPayMargin priceToPay']/span[2]/span[2]")
    private WebElement mobilePrice;

    @FindBy(id = "contextualIngressPtLabel_deliveryShortLine")
    private WebElement deliveryLocationLink;

    @FindBy(id = "GLUXZipUpdateInput")
    private WebElement pinCodeInput;

    @FindBy(xpath = "//input[@class='a-button-input' and @aria-labelledby='GLUXZipUpdate-announce']")
    private WebElement pinCodeSubmitButton;

    @FindBy(id = "ape_Detail_hero-quick-promo_Desktop_iframe")
    private WebElement sponsoredLinkFrame;

    @FindBy(xpath = "//div[@id='adLink']/a")
    private WebElement sponsoredLink;

    @FindBy(xpath = "//input[@id='add-to-cart-button' and @type='submit']")
    private WebElement addToCartButton;

    @FindBy(xpath = "//div[@id='attachDisplayAddBaseAlert']/div[@class='a-box-inner a-alert-container']/h4[@class='a-alert-heading' and text()='Added to Cart']")
    private WebElement addedToCartAlert;

    @FindBy(xpath = "//a[@id='attach-close_sideSheet-link']")
    private WebElement closeCartAlert;

    @FindBy(xpath = "//h1[@class='a-size-base-plus a-text-bold']")
    private WebElement technicalDetailsSection;

    // Page Actions
    public void searchForMobile(String mobileName) {
        WebLibrary.sendKeys(By.id("twotabsearchtextbox"), mobileName);
        WebLibrary.waitForElementAndClick(By.id("nav-search-submit-button"));
    }

    public void validateSearchResults() {
        boolean isDisplayed = WebLibrary.waitForElementToBeDisplayed(By.xpath("//span[@class='a-color-state a-text-bold']"));
        Assert.assertTrue(isDisplayed, "Search results are not displayed.");
    }

    public void filterByCustomerReview() {
        WebLibrary.waitForElementAndClick(By.xpath("//div[3]/ul[1]/span[1]/span[1]/li[1]/span[1]/a[1]"));
        WebLibrary.waitForPageToLoad();
    }

    public void selectAndValidateMobile() {
        WebElement desiredMobileElement = WebLibrary.waitForElementToBeVisible(By.xpath("//span[@class='a-size-medium a-color-base a-text-normal' and text()='Redmi 13C (Starfrost White, 4GB RAM, 128GB Storage) | Powered by 4G MediaTek Helio G85 | 90Hz Display | 50MP AI Triple Camera']"));
        WebLibrary.scrollIntoView(desiredMobileElement);
        WebLibrary.waitForElementAndClick(By.xpath("//span[@class='a-size-medium a-color-base a-text-normal' and text()='Redmi 13C (Starfrost White, 4GB RAM, 128GB Storage) | Powered by 4G MediaTek Helio G85 | 90Hz Display | 50MP AI Triple Camera']"));
        WebLibrary.switchToNewWindow();

        // Add explicit wait for the title to be set
        WebLibrary.waitForPageTitleToBe("Redmi 13C (Starfrost White, 4GB RAM, 128GB Storage) | Powered by 4G MediaTek Helio G85 | 90Hz Display | 50MP AI Triple Camera : Amazon.in");

        // Validate the title
        validateTitle("Redmi 13C (Starfrost White, 4GB RAM, 128GB Storage) | Powered by 4G MediaTek Helio G85 | 90Hz Display | 50MP AI Triple Camera : Amazon.in");
    }


    public void validateMobilePrice() {
        String price = WebLibrary.waitForElementToBeVisible(By.xpath("//span[@class='a-price aok-align-center reinventPricePriceToPayMargin priceToPay']/span[2]/span[2]")).getText();
        Assert.assertNotNull(price, "Mobile price not found.");
    }

    public void validateDeliveryLocation(String pinCode) {
        WebLibrary.waitForElementAndClick(By.id("contextualIngressPtLabel_deliveryShortLine"));
        WebLibrary.sendKeys(By.id("GLUXZipUpdateInput"), pinCode);
        WebLibrary.waitForElementAndClick(By.xpath("//input[@class='a-button-input' and @aria-labelledby='GLUXZipUpdate-announce']"));
        WebLibrary.waitFor(1500);
    }

    public void validateSponsoredLink() {
        WebLibrary.switchToFrame(By.id("ape_Detail_hero-quick-promo_Desktop_iframe"));
        String originalWindowUrl = driver.getCurrentUrl();
        WebLibrary.waitForElementAndClick(By.xpath("//div[@id='adLink']/a"));
        WebLibrary.switchToParentFrame();
        String newWindowUrl = driver.getCurrentUrl();
        WebLibrary.waitFor(1500);
        Assert.assertNotEquals(originalWindowUrl, newWindowUrl, "A new window has not been opened.");
        driver.navigate().back();
    }

    public void addToCartAndValidate() {
        WebLibrary.waitForElementAndClick(By.xpath("//input[@id='add-to-cart-button' and @type='submit']"));
        WebLibrary.waitFor(5000);
        boolean isAddedToCart = WebLibrary.waitForElementToBeDisplayed(By.xpath("//div[@id='attachDisplayAddBaseAlert']/div[@class='a-box-inner a-alert-container']/h4[@class='a-alert-heading' and text()='Added to Cart']"));
        Assert.assertTrue(isAddedToCart, "Add to Cart message not displayed.");
        WebLibrary.waitForElementAndClick(By.xpath("//a[@id='attach-close_sideSheet-link']"));
    }

    public void scrollToTechnicalDetails() {
        WebLibrary.scrollIntoView(technicalDetailsSection);
    }

    private void validateTitle(String expectedTitle) {
        String actualTitle = driver.getTitle();
        Assert.assertEquals(actualTitle, expectedTitle, "Title validation failed.");
    }
}
