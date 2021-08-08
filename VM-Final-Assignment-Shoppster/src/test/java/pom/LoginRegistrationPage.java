package pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class LoginRegistrationPage extends CommonActions{

    WebDriver driver;
    WebDriverWait webDriverWait;

    public LoginRegistrationPage(WebDriver driver, WebDriverWait webDriverWait) {
        super(driver, webDriverWait);
        this.driver = driver;
        this.webDriverWait = webDriverWait;
        PageFactory.initElements(driver, this);
    }

    @FindBy (css = ".btn.btn-block.btn-action.btn-register")
    WebElement registrationButton;
    @FindBy (css = ".btn.btn-action.join-sc__proceed-button")
    WebElement withoutMembershipButton;
    @FindBy (xpath = "//input[@name=\"firstname\"]")
    WebElement firstNameField;
    @FindBy (xpath = "//input[@name=\"lastname\"]")
    WebElement lastNameField;
    @FindBy(xpath = "//input[@name=\"email\"]")
    WebElement emailField;
    @FindBy(xpath = "//input[@name=\"password\"]")
    WebElement passwordField;
    @FindBy(xpath = "//input[@name=\"confirmpassword\"]")
    WebElement confirmPasswordField;
    @FindBy (xpath = "//input[@name=\"termsandconditions\"]")
    WebElement termsAndConditions;
    @FindBy (css = ".btn.btn-block.btn-secondary--red")
    WebElement submitRegistrationButton;


    public void clickRegistrationButton() throws InterruptedException {
        clickElement(registrationButton);
    }
    public void clickWithoutMembershipButton() throws InterruptedException {
        clickElement(withoutMembershipButton);
    }
    public void enterFirstName(String value) {
        typeText(firstNameField, value);
    }
    public void enterLastName(String value) {
        typeText(lastNameField, value);
    }
    public void enterEmail(String value) {
        typeText(emailField, value);
    }
    public void enterPassword(String value) {
        typeText(passwordField, value);
    }
    public void enterConfirmPassword(String value) {
        typeText(confirmPasswordField, value);
    }
    public void acceptTermsAndConditions() throws InterruptedException {
        clickElement(termsAndConditions);
    }
    public void clickSubmitRegistrationButton() throws InterruptedException {
        clickElement(submitRegistrationButton);
    }
    public void registerAccount (String firstName, String lastName, String email, String password, String confirmPassword) throws InterruptedException {
        clickRegistrationButton();
        clickWithoutMembershipButton();
        enterFirstName(firstName);
        enterLastName(lastName);
        enterEmail(email);
        enterPassword(password);
        enterConfirmPassword(confirmPassword);
        acceptTermsAndConditions();
        clickSubmitRegistrationButton();
    }

}
