package Utilites;


import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.*;

public class Utility {

    private static final String SCREENSHOTS_PATH = "Test_Output/ScreenShot/";


    public static void clickingOnElement(WebDriver driver, By locator) {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(locator));
        driver.findElement(locator).click();
    }
    public static void sendData(WebDriver driver, By locator, String data) {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
        driver.findElement(locator).sendKeys(data);
    }

    public static String getText(WebDriver driver, By locator) {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
        return driver.findElement(locator).getText();

    }

    public static WebDriverWait generalWait(WebDriver driver) {
        return new WebDriverWait(driver, Duration.ofSeconds(5));
    }
    public static void scrolling(WebDriver driver, By locator) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", findWebElement(driver, locator));
    }

    public static WebElement findWebElement(WebDriver driver, By locator) {
        return driver.findElement(locator);
    }
    public static String getTimeStamp() {
        return new SimpleDateFormat("yyyy-MM-dd-hh-mm-ssa").format(new Date());
    }
    public  static void takeScreenShot(WebDriver driver, String screenshotName) {
        try {
            // Capture screenshot using TakesScreenshot
            File screenshotSrc = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            // Save screenshot to a file if needed
            File screenshotFile = new File(SCREENSHOTS_PATH + screenshotName + "-" + getTimeStamp() + ".png");
            FileUtils.copyFile(screenshotSrc, screenshotFile);

            // Attach the screenshot to Allure

        } catch (IOException e) {
            //LogsUtils.error(e.getMessage());

        }
    }


    public static File getLatestFile(String folderPath) {
        File folder = new File(folderPath);
        File[] files = folder.listFiles();
        assert files != null;
        if (files.length == 0)
            return null;
        Arrays.sort(files, Comparator.comparingLong(File::lastModified).reversed());

        return files[0];
    }
    public static int GenerateRandomNumber(int UberBound)
    {
        return new Random().nextInt(UberBound)+1;

    }

    public static Set<Integer>GenerateUniqueNumber(int UberBound,int TotalNumOfProduct)
    {
        Set<Integer> RandomNumUnique =new HashSet<>();
        while (RandomNumUnique.size()<UberBound){
            int added=GenerateRandomNumber(TotalNumOfProduct);
            RandomNumUnique.add(added);
        }
        return RandomNumUnique;
    }
    public static Boolean VerifyUrl(WebDriver driver,String ExpectUrl)
    {
        try {
            Utility.generalWait(driver).until(ExpectedConditions.urlToBe(ExpectUrl));
            return true;
        }catch (Exception e)
        {
            return false;
        }
    }
}
