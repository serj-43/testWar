package test;

import data.ConfigReader;
import data.DataHelper;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import page.LoginForm;
import page.MainPage;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.*;

public class AuthTest {

    private WebDriver driver;

    @BeforeClass
    public void setupTest() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }


    @AfterClass
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }


    @AfterMethod
    public void logOutAfter() {
        var mainPage = new MainPage(driver);
        mainPage.logOut();
    }

    @Test
    public void ClientLoginPass() {
        var authInfo = DataHelper.getAuthInfo();
        var mainPage = new MainPage(driver);
        LoginForm loginPage;
        loginPage = mainPage.loginPageByURL();
        mainPage = loginPage.LoginByLogin(authInfo);
        assertEquals(mainPage.GetAuthName(), authInfo.getName());
    }

    @Test
    public void ClientLoginVisiblePass() {
        var authInfo = DataHelper.getAuthInfo();
        var mainPage = new MainPage(driver);
        LoginForm loginPage;
        loginPage = mainPage.loginPageByURL();
        loginPage.ChangePassVisibility();
        mainPage = loginPage.LoginByLogin(authInfo);
        assertEquals(mainPage.GetAuthName(), authInfo.getName());
    }

    @Test
    public void ClientEmailPass() {
        var authInfo = DataHelper.getAuthInfo();
        var clientMails = DataHelper.getClientMail(authInfo);
        var mainPage = new MainPage(driver);
        LoginForm loginPage;
        loginPage = mainPage.loginPageByURL();
        mainPage = loginPage.LoginByEmail(authInfo, clientMails);
        assertEquals(mainPage.GetAuthName(), authInfo.getName());
    }

    @Test
    public void ClientPhonePass() {
        var authInfo = DataHelper.getAuthInfo();
        var clientPhones = DataHelper.getClientPhone(authInfo);
        var mainPage = new MainPage(driver);
        LoginForm loginPage;
        loginPage = mainPage.loginPageByURL();
        mainPage = loginPage.LoginByPhone(authInfo, clientPhones);
        assertEquals(mainPage.GetAuthName(), authInfo.getName());
    }

    @Test
    public void ClientWrongLoginPass() {
        var authInfo = DataHelper.getWrongAuthInfoLog();
        var mainPage = new MainPage(driver);
        LoginForm loginPage;
        loginPage = mainPage.loginPageByURL();
        String messageText = loginPage.LoginWrongData(authInfo);
        assertEquals(messageText, "Логин или пароль введены неверно");
    }

    @Test
    public void ClientWrongPassPass() {
        var authInfo = DataHelper.getWrongAuthInfoPass();
        var mainPage = new MainPage(driver);
        LoginForm loginPage;
        loginPage = mainPage.loginPageByURL();
        String messageText = loginPage.LoginWrongData(authInfo);
        assertEquals(messageText, "Логин или пароль введены неверно");
    }
}
