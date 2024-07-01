package Pages;

import Utilites.LogsUtils;
import Utilites.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class P03_CartPage {
    private final WebDriver driver;
    static float TotalPrice=0;
    private final By pricesOfSelectedProductsLocator = By.xpath("//button[.=\"Remove\"] //preceding-sibling::div[@class='inventory_item_price']");
    private final By CheckOutButton=By.id("checkout");
    public P03_CartPage(WebDriver driver) {
        this.driver=driver;
    }

    public String getTotalPriceOfProducts() {
        try {
            List<WebElement> pricesOfSelectedProducts = driver.findElements(pricesOfSelectedProductsLocator);
            for (int i = 1; i <= pricesOfSelectedProducts.size(); i++) {
                By elements = By.xpath("(//button[.=\"Remove\"] //preceding-sibling::div[@class='inventory_item_price'])[" + i + "]");
                String fullText = Utility.getText(driver, elements);
                TotalPrice += Float.parseFloat(fullText.replace("$", ""));
            }
            LogsUtils.info("Total Price " + TotalPrice);
            return String.valueOf(TotalPrice);
        } catch (Exception e) {
            LogsUtils.error(e.getMessage());
            return "0";
        }
    }
    public Boolean ComparePrices(String Price)
    {
        return getTotalPriceOfProducts().equals(Price);
    }
    public P04_CheckOutPage ClickOnCheckOutButton()
    {
        Utility.clickingOnElement(driver,CheckOutButton);
        return new P04_CheckOutPage(driver);
    }




}
