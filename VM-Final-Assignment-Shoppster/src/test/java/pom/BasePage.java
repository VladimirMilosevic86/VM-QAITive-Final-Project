package pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class BasePage extends CommonActions{
    WebDriver driver;
    WebDriverWait webDriverWait;

    public BasePage(WebDriver driver, WebDriverWait webDriverWait) {
        super(driver,webDriverWait);
        this.driver = driver;
        this.webDriverWait = webDriverWait;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "footer .btn.btn-primary")
    WebElement acceptCookies;
    @FindBy(xpath = "//ung-mini-login/a")
    WebElement homepageLoginButton;
    @FindBy(css = ".main-navigation__all-categories-title")
    WebElement allCategories;

    public void acceptCookies() throws InterruptedException {
        clickElement(acceptCookies);
    }
    public void clickHompageLoginButton() throws InterruptedException {
        clickElement(homepageLoginButton);
    }
    public void selectMainCategory(String mainCategory){
        Actions actions = new Actions(driver);
        actions.moveToElement(allCategories).build().perform();
        driver.findElement(By.cssSelector("[href='/sr-RS/c/"+mainCategory+"']")).click();
    }
}