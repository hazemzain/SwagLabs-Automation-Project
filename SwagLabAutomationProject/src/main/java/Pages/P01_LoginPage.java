package Pages;

import Utilites.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static Utilites.Utility.sendData;

public class P01_LoginPage {
    private final By UserName=By.id("user-name");
    private final By Password=By.id("password");
    private final By LoginButton=By.id("login-button");
    private final WebDriver driver;

    public P01_LoginPage(WebDriver driver) {
        this.driver = driver;
    }
    public P01_LoginPage EnterUserName(String UserNameValue)
    {
        Utility.sendData(driver,UserName,UserNameValue);

        return this;
    }
    public P01_LoginPage EnterPassword(String PasswordValue)
    {
        Utility.sendData(driver,Password,PasswordValue);

        return this;
    }
    public P02_LandingPage ClickLoginButton()
    {
        Utility.clickingOnElement(driver,LoginButton);

        return new P02_LandingPage(driver);
    }
    public Boolean AssertLoginTc(String ExpectedValue)
    {
        return driver.getCurrentUrl().equals(ExpectedValue);

    }


}
