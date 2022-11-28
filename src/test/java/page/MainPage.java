package page;

import data.ConfigReader;
import data.DataHelper;
import org.apache.http.client.AuthCache;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage {

    public WebDriver driver;
    private String baseUrl;

    @FindBy(xpath = "//div[@class='header-nav']/descendant::span[@class=\"auth-user__login-name\"]")
    private WebElement clientName;

    @FindBy(xpath = "//*[contains(@class,\"main-logo__img\")]")
    private WebElement mainLogo;

    public MainPage(WebDriver driver) {
        driver.get(ConfigReader.getProperty("url"));
        baseUrl = driver.getCurrentUrl();
        PageFactory.initElements(driver, this);
        this.driver = driver;
        WebDriverWait waiter = new WebDriverWait(driver, 10);
        waiter.until(ExpectedConditions.visibilityOf(mainLogo));
    }


    public LoginForm loginPageByURL() {
        driver.get(baseUrl + "/login/");
        return new LoginForm(driver);
    }

    public MainPage logOut() {
        driver.get(baseUrl + "/?logout");
        return this;
    }

    public String GetAuthName(){
        return clientName.getText();
    }
}
