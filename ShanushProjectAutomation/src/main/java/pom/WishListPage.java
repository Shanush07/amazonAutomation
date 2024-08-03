package pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class WishListPage {
    private WebDriver driver;

    // Constructor to initialize the WebDriver and PageFactory
    public WishListPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Page-specific WebElements
    @FindBy(xpath = "//a[@id='nav-link-accountList']")
    private WebElement accountsLists;

    @FindBy(xpath = "//span[normalize-space()='Create a Wish List']")
    private WebElement createWishList;

    @FindBy(xpath = "//li[@id='my-lists-tab']")
    private WebElement wishListTab;

    // Method to navigate to "Create a Wish List"
    public void navigateToCreateWishList() {
        Actions action = new Actions(driver);
        action.moveToElement(accountsLists).moveToElement(createWishList).click().build().perform();
    }

    // Method to check if the Wish List tab is displayed
    public boolean isWishListTabDisplayed() {
        return wishListTab.isDisplayed();
    }

    // Method to navigate back
    public void navigateBack() {
        driver.navigate().back();
    }
}
