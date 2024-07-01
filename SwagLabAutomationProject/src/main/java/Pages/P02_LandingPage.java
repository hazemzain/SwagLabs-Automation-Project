package Pages;

import Utilites.LogsUtils;
import Utilites.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import javax.swing.text.Utilities;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class P02_LandingPage {
    static float TotalPrice=0;
    private final By AddToCardButtonForProduct=By.xpath("//button[@class]");
    private final By NumberInTheCardForProductsAdded=By.className("shopping_cart_badge");
    private final By NumberOfProductSelected= By.xpath("//button[.='Remove']");
    private final By ButtonCardProducts=By.xpath("//a[@class='shopping_cart_link']");
    private final By pricesOfSelectedProductsLocator = By.xpath("//button[.=\"Remove\"] //preceding-sibling::div[@class='inventory_item_price']");
    private static List<WebElement>AllProducts;
    private static List<WebElement>SelectedProducts;
    private static List<WebElement>PriceOfSelectedProducts;
    private final WebDriver driver;

    public P02_LandingPage(WebDriver driver) {
        this.driver = driver;
    }
    public P03_CartPage ClickOnCartButton()
    {
        Utility.clickingOnElement(driver,ButtonCardProducts);

        return new P03_CartPage(driver);
    }
    public Boolean CartPageVerifyUrl(String ExpectUrl)
    {
        try {
            Utility.generalWait(driver).until(ExpectedConditions.urlToBe(ExpectUrl));
            return true;
        }catch (Exception e)
        {
            return false;
        }
    }

    public P02_LandingPage AddAllProductsToCard()
    {
        AllProducts=driver.findElements(AddToCardButtonForProduct);
        for(int i=1;i<=AllProducts.size();i++)
        {
            By AddToCardButtonForOneProduct= By.xpath("(//button[@class])[" + i + "]");

            Utility.clickingOnElement(driver,AddToCardButtonForOneProduct);

        }

        return this;
    }
    public P02_LandingPage AddRandomProductsToCard(int NumberOfProductToSelect,int TotalNumberOfProduct)
    {
        Set<Integer>RondomNumberSet=new HashSet<>();
        RondomNumberSet=Utility.GenerateUniqueNumber(NumberOfProductToSelect,TotalNumberOfProduct);
        for(int Random:RondomNumberSet)
        {
            LogsUtils.info("Random Number Is ="+Random);
            By AddToCardButtonForOneProduct= By.xpath("(//button[@class])[" + Random + "]");

            Utility.clickingOnElement(driver,AddToCardButtonForOneProduct);


        }

        return this;
    }
    public String GetNumberOfProductsAddToCard()
    {
        try {
            return Utility.getText(driver,NumberInTheCardForProductsAdded);
        }catch (Exception e)
        {
            System.out.println("No Element Found");
            LogsUtils.error(e.getMessage());
            return "0";
        }


    }
    public String NumOfSelectedProducts()
    {
        try {
            SelectedProducts=driver.findElements(NumberOfProductSelected);
            return String.valueOf(SelectedProducts.size());
        }catch (Exception e)
        {
            System.out.println("No Element Selected Found");
            LogsUtils.error(e.getMessage());
            return "0";
        }

    }
    public Boolean ComparingTheNumberOfProductAdd()
    {
        return GetNumberOfProductsAddToCard().equals(NumOfSelectedProducts());
    }
    public String getTotalPriceOfSelectedProducts() {
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

}
