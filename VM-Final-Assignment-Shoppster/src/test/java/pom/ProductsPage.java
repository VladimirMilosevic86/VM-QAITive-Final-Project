package pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import tests.Shoppster;

import java.util.List;

public class ProductsPage extends CommonActions{

    WebDriver driver;
    WebDriverWait webDriverWait;

    public ProductsPage(WebDriver driver, WebDriverWait webDriverWait) {
        super(driver, webDriverWait);
        this.driver = driver;
        this.webDriverWait = webDriverWait;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//div[@class=\"container plp__grid-container\"]//div[contains(text(),\"Armani Exchange\")]")
    public List<WebElement> brandTitles;
    @FindBy(xpath = "//div[@class=\"container plp__grid-container\"]//ung-colors-indicator//span")
    public List<WebElement> colors;
    @FindBy(xpath = "//div[@class=\"container plp__grid-container\"]//span[@class=\"price__value--normal\"]")
    public List<WebElement> prices;
    @FindBy(xpath = "//div[@class=\"container plp__grid-container\"]//a[text()=\" Sportina doo \"]")
    public List<WebElement> vendorTitles;

    public void selectCategory(String category) throws InterruptedException {
        clickElement(driver.findElement(By.xpath("//a[@class='cx-facet-header-link' and contains(text(),'Kategorije')]/../..//ul//a[contains(text(),'"+category+"')]")));
    }
    public void setFilterColor(String color) throws InterruptedException {
        clickElement(driver.findElement(By.xpath("//a[@class='cx-facet-header-link' and contains(text(),'Boja')]/../..//li/div[@title='"+color+"']")));
    }
    public void setFilterCheckbox(String filterCategory, String filterCheckbox) throws InterruptedException {
        clickElement(driver.findElement(By.xpath("//a[@class='cx-facet-header-link' and contains(text(),'"+filterCategory+"')]/../..//li//span[contains(text(),'"+filterCheckbox+"')]/../input")));
    }
    public void moreLessFilters(String msFilterCategory) throws InterruptedException {
        clickElement(driver.findElement(By.xpath("//a[@class='cx-facet-header-link' and contains(text(),'"+msFilterCategory+"')]/../..//li[last()]")));
    }
}