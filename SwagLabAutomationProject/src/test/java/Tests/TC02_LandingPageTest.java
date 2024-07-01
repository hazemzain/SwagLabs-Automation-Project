package Tests;

import DriverFactory.DriverFactory;
import Pages.P01_LoginPage;
import Pages.P02_LandingPage;
import Utilites.DataUtilits;
import Utilites.Utility;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;

import static DriverFactory.DriverFactory.getDriver;

public class TC02_LandingPageTest {
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
    public void CheckingNumberOFSelectedProductsTestCase() throws IOException {
        new P01_LoginPage(getDriver())
                .EnterUserName(UserName)
                .EnterPassword(Password)
                .ClickLoginButton()
                .AddAllProductsToCard();
        Assert.assertTrue(new P02_LandingPage(getDriver()).ComparingTheNumberOfProductAdd());

    }
    @Test
    public void AddingNumberOFRandomProductsTestCase() throws IOException {
        new P01_LoginPage(getDriver())
                .EnterUserName(UserName)
                .EnterPassword(Password)
                .ClickLoginButton()
                .AddRandomProductsToCard(3,6);
        Assert.assertTrue(new P02_LandingPage(getDriver()).ComparingTheNumberOfProductAdd());

    }

    @Test
    public void ClickOnCartButtonTestCase() throws IOException {
         new P01_LoginPage(getDriver())
                .EnterUserName(UserName)
                .EnterPassword(Password)
                .ClickLoginButton()
                .ClickOnCartButton();

        Assert.assertTrue(new P02_LandingPage(getDriver()).CartPageVerifyUrl(DataUtilits.getPropertyValue("environment","CartPageUrl")));

    }

    @AfterTest
    public void Quit()
    {

    }
}
