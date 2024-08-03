package pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    private WebDriver driver;

    // Constructor to initialize the WebDriver and PageFactory
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Page-specific WebElements
    @FindBy(xpath = "//a[@id='nav-link-accountList']")
    private WebElement accountsLists;

    @FindBy(xpath = "//a[@class='nav-action-signin-button' and @rel='nofollow']/span[@class='nav-action-inner' and text()='Sign in']")
    private WebElement signIn;

    @FindBy(xpath = "//input[@name='email']")
    private WebElement emailInputBox;

    @FindBy(xpath = "//input[@class='a-button-input']")
    private WebElement continueButton;

    @FindBy(xpath = "//input[@id='ap_password']")
    private WebElement passwordInputBox;

    @FindBy(xpath = "//input[@id='signInSubmit']")
    private WebElement submitButton;

    // Method to navigate to the Sign-In page
    public void navigateToSignInPage() {
        Actions action = new Actions(driver);
        action.moveToElement(accountsLists).moveToElement(signIn).click().build().perform();
    }

    // Method to enter invalid email
    public void enterInvalidEmail(String email) {
        emailInputBox.click();
        emailInputBox.sendKeys(email);
        continueButton.click();
    }

    // Method to enter invalid password
    public void enterInvalidPassword(String password) {
        passwordInputBox.click();
        passwordInputBox.sendKeys(password);
        submitButton.click();
    }
}
