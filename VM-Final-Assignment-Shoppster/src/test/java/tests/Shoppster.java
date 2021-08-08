package tests;

import excel_utils.ExcelUtilities;
import io.qameta.allure.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pom.BasePage;
import pom.LoginRegistrationPage;
import pom.ProductsPage;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Epic("Functional Tests")
public class Shoppster extends BaseTest {

    @BeforeMethod(description = "Runs browser and starts test")
    @Parameters ({"browser"})
    public void startTest(String browser) {
        init(browser, 30);
    }

    @AfterMethod(description = "Closes browsers and finishes tests")
    public void finishTest() throws IOException {
        reportScreenshot("Screenshot_" + System.currentTimeMillis(), "Screenshot of Failure");
        tearDown();
    }

    @Test(description = "Register with valid data")
    @Severity(SeverityLevel.CRITICAL)
    @Feature("Registration Test")
    @Description("Test description: Verify registration with correct data")
    @Attachment(value = "Registration Test Attachment",type = "image/png")
    @Parameters ({"firstName", "lastName", "password", "confirmPassword", "selector", "text"})
    public void accountRegistrationTest(String firstName, String lastName, String password, String confirmPassword, String selector, String text) throws InterruptedException, IOException {
        driver.get("https://www.shoppster.com/sr-RS/");

        wdWait = new WebDriverWait(driver, 30);
        BasePage basePage = new BasePage(driver, wdWait);

        basePage.acceptCookies();
        basePage.clickHompageLoginButton();

        LoginRegistrationPage loginRegistrationPage = new LoginRegistrationPage(driver, wdWait);

        loginRegistrationPage.registerAccount(
                firstName,
                lastName,
                "mail"+System.currentTimeMillis()+"@gmail.com",
                password,
                confirmPassword);
        reportScreenshot("Registration_Form", "Screenshot after registration form was filled in");
        Assert.assertEquals(basePage.getTextFromElement(driver.findElement(By.xpath(selector))), text);
    }

    @Test(description = "Shoppster Filter Test")
    @Severity(SeverityLevel.CRITICAL)
    @Feature("Filters Test")
    @Description("Test description: Verify filters functionality")
    @Attachment(value = "Filter Test Attachment",type = "image/png")
    @Parameters ({"mainCategory", "moreLessFilterCategory", "brandFilter", "priceFilter", "vendorFilter", "expectedColor", "expectedBrand", "expectedVendor", "excelSheet", "excelRow"})
    public void filterTest(String mainCategory, String moreLessFilterCategory, String brandFilter, String priceFilter, String vendorFilter, String expectedColor, String expectedBrand, String expectedVendor, String excelSheet, String excelRow) throws InterruptedException, IOException {
        driver.get("https://www.shoppster.com/sr-RS/");

        wdWait = new WebDriverWait(driver, 30);
        BasePage basePage = new BasePage(driver, wdWait);

        Map<String,String> data = new ExcelUtilities().getRowData("src/test/testData/Filters.xlsx", excelSheet, excelRow);

        basePage.acceptCookies();
        basePage.selectMainCategory(mainCategory);

        ProductsPage productsPage = new ProductsPage(driver, wdWait);

        productsPage.selectCategory(data.get("Category"));
        pause(5);
        productsPage.moreLessFilters(moreLessFilterCategory);
        pause(5);
        productsPage.setFilterCheckbox("Brend", brandFilter);
        pause(5);
        productsPage.setFilterColor(data.get("Color"));
        pause(5);
        productsPage.setFilterCheckbox("Cena", priceFilter);
        pause(5);
        productsPage.setFilterCheckbox("Prodavac", vendorFilter);
        pause(5);

        reportScreenshot("Filtered_products", "Screenshot after products are filtered");

        for (int i = 0; i < productsPage.colors.size(); i++) {
            Assert.assertEquals(productsPage.colors.get(i).getCssValue("background-color"), expectedColor);
        }
        for (int i = 0; i < productsPage.brandTitles.size(); i++) {
            Assert.assertTrue((productsPage.brandTitles.get(i).getText().contains(expectedBrand)));
        }
        for (int i = 0; i < productsPage.prices.size(); i++) {
            String s = productsPage.prices.get(i).getText();
            int n = Integer.parseInt(s.replaceAll("[^0-9]", ""));
            Assert.assertTrue(n >= 10000 && n <= 20000);
        }
        for (int i = 0; i < productsPage.vendorTitles.size(); i++) {
            Assert.assertTrue((productsPage.vendorTitles.get(i).getText().contains(expectedVendor)));
        }
    }
}
