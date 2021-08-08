package tests;

import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import selenium_core.DriverManager;
import selenium_core.DriverManagerFactory;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class BaseTest {
    public WebDriver driver;
    DriverManager driverManager;
    public WebDriverWait wdWait;

    public void init(String browser, int implicitWait){
        driverManager = DriverManagerFactory.getDriverManager(browser);
        driver = driverManager.getWebDriver();
        driver.manage().timeouts().implicitlyWait(implicitWait, TimeUnit.SECONDS);
    }

    public void tearDown(){
        driverManager.quitWebDriver();
    }

    public void takeScreenshot(String name) throws IOException {
        File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(file, new File("src/test/screenshots/"+name+".png"));
    }

    public void reportScreenshot(String name, String description) throws IOException {
        takeScreenshot(name);
        Path content = Paths.get("src/test/screenshots/"+name+".png");
        try(InputStream is = Files.newInputStream(content)){
            Allure.addAttachment(description,is);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public String getRandomString(int num){
        char[] chars = {'A','a','B','b','C','c','D','d'};
        String randomChars = "";
        for (int i = 0; i < num; i++){
            Random r = new Random();
            int newR = r.nextInt(chars.length-1);
            randomChars+=chars[newR];
        }
        return randomChars;
    }

    public void pause(int sec) throws InterruptedException {
        Thread.sleep(sec*1000);
    }

}