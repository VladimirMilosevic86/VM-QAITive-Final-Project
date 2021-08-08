package pom;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommonActions {
    WebDriver driver;
    WebDriverWait wdWait;

    public CommonActions(WebDriver driver, WebDriverWait webDriverWait) {
        this.driver = driver;
        this.wdWait = webDriverWait;
    }

    public void setAttributeValue(WebElement element, String attribute, String value){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].setAttribute(arguments[1],arguments[2])",
                element, attribute, value
        );
    }
    public void clickElement(WebElement element) throws InterruptedException {
        //scrollElementIntoView(element);
        wdWait.until(ExpectedConditions.elementToBeClickable(element));
        Actions actions = new Actions(driver);
        actions.moveToElement(element).build().perform();
        try {
            element.click();
        } catch (StaleElementReferenceException e){
            element.click();
        }
    }
    public void typeText(WebElement element, String value){
        wdWait.until(ExpectedConditions.visibilityOf(element));
        element.clear();
        element.sendKeys(value);
    }
    public String getTextFromElement(WebElement element){
        wdWait.until(ExpectedConditions.visibilityOf(element));
        return element.getText();
    }
    public String getTextFromElementIfTextPresent(WebElement element, String value){
        wdWait.until(ExpectedConditions.visibilityOf(element));
        wdWait.until(ExpectedConditions.textToBePresentInElement(element, value));
        return element.getText();
    }
    public void selectElement(WebElement element, String text){
        //wdWait.until(ExpectedConditions.visibilityOf(element));
        Select select = new Select(element);
        select.selectByVisibleText(text);
    }
    public void selectElement(WebElement element, int index){
        //wdWait.until(ExpectedConditions.visibilityOf(element));
        Select select = new Select(element);
        select.selectByIndex(index);
    }
    public void selectElementByValue(WebElement element, String text){
        //wdWait.until(ExpectedConditions.visibilityOf(element));
        Select select = new Select(element);
        select.selectByValue(text);
    }
    public void scrollElementIntoView(WebElement element){
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }
    public static String getOnlyDigits(String s) {
        Pattern pattern = Pattern.compile("[^0-9]");
        Matcher matcher = pattern.matcher(s);
        String number = matcher.replaceAll("");
        return number;
    }
}
