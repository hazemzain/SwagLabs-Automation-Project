package Tests;

import DriverFactory.DriverFactory;
import Pages.P01_LoginPage;
import Pages.P02_LandingPage;
import Utilites.DataUtilits;
import Utilites.LogsUtils;
import Utilites.Utility;
import com.github.javafaker.Faker;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;

import static DriverFactory.DriverFactory.getDriver;
import static Utilites.DataUtilits.getPropertyValue;

public class TC04_CheckOutTest {
    private final String UserName= DataUtilits.getJsonData("ValidLoginData","username");
    private final String Password=DataUtilits.getJsonData("ValidLoginData","password");

    private final String FIRSTNAME = DataUtilits.getJsonData("information", "fName") + "-" + Utility.getTimeStamp();
    private final String LASTNAME = DataUtilits.getJsonData("information", "lName") + "-" + Utility.getTimeStamp();
    private final String ZIPCODE = new Faker().number().digits(5);
    @BeforeMethod
    public void SetupPage() throws IOException {
        DriverFactory.setupDriver(getPropertyValue("environment","Browser"));
        getDriver().get(getPropertyValue("environment","LoginUrl"));
        getDriver().manage().window().maximize();
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

    }

    @Test
    public void CheckOutStepOneTestCase() throws IOException {
         new P01_LoginPage(getDriver())
                .EnterUserName(UserName)
                .EnterPassword(Password)
                .ClickLoginButton()
                .AddRandomProductsToCard(3,6)
                .ClickOnCartButton()
                .ClickOnCheckOutButton()
                .fillingInformationForm(FIRSTNAME, LASTNAME, ZIPCODE).clickOnContinueButton();
        LogsUtils.info(FIRSTNAME + " " + LASTNAME + " " + ZIPCODE);

        Assert.assertTrue(Utility.VerifyUrl(getDriver(),getPropertyValue("environment", "CHECKOUT_URL")));

    }

    @AfterTest
    public void Quit()
    {

    }
}
