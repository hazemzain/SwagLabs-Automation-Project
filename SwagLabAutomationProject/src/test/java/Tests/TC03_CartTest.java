package Tests;

import DriverFactory.DriverFactory;
import Pages.P01_LoginPage;
import Pages.P02_LandingPage;
import Pages.P03_CartPage;
import Utilites.DataUtilits;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;

import static DriverFactory.DriverFactory.getDriver;

public class TC03_CartTest {
    private final String UserName= DataUtilits.getJsonData("ValidLoginData","username");
    private final String Password=DataUtilits.getJsonData("ValidLoginData","password");
    @BeforeMethod
    public void SetupPage() throws IOException {
        DriverFactory.setupDriver(DataUtilits.getPropertyValue("environment","Browser"));
        getDriver().get(DataUtilits.getPropertyValue("environment","LoginUrl"));
        getDriver().manage().window().maximize();
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

    }

    @Test
    public void ComparingPricesTestCase() throws IOException {
       String TotalPriceOfSelectedProduct = new P01_LoginPage(getDriver())
                .EnterUserName(UserName)
                .EnterPassword(Password)
                .ClickLoginButton()
                .AddRandomProductsToCard(2,6)
                .getTotalPriceOfSelectedProducts();

        Assert.assertTrue(new P02_LandingPage(getDriver()).ClickOnCartButton().ComparePrices(TotalPriceOfSelectedProduct));

    }

    @AfterTest
    public void Quit()
    {

    }
}
