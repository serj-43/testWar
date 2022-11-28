package page;

import data.DataHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginForm {

    public WebDriver driver;

    @FindBy(id = "login-input")
    private WebElement loginInput;

    @FindBy(id = "password")
    private WebElement passwordInput;

    @FindBy(id = "email")
    private WebElement byEmail;

    @FindBy(id = "phone")
    private WebElement byPhone;

    @FindBy(xpath = "//button[contains(@class,\"w-button\")]")
    private WebElement buttonSubmit;

    @FindBy(xpath = "//*[contains(@class,\"w-input mb-3 w-input_md\")]//div[@class=\"w-input__icon-wrapper\"]//*[@class=\"w-input__icon\"]")
    private WebElement passVisibility;

    @FindBy(xpath = "//*[contains(@class,\"error-notice mb-5\")]")
    private WebElement wrongLoginMessage;

    public LoginForm(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        WebDriverWait waiter = new WebDriverWait(driver, 10);
        waiter.until(ExpectedConditions.visibilityOf(loginInput));
    }

    public MainPage LoginByLogin(DataHelper.AuthInfo info) {
        loginInput.sendKeys(info.getLogin());
        passwordInput.sendKeys(info.getPassword());
        buttonSubmit.click();
        return new MainPage(driver);
    }

    public String LoginWrongData(DataHelper.AuthInfo info) {
        loginInput.sendKeys(info.getLogin());
        passwordInput.sendKeys(info.getPassword());
        buttonSubmit.click();
        return wrongLoginMessage.getText();
    }

    public LoginForm ChangePassVisibility() {
        passVisibility.click();
        return this;
    }

    public MainPage LoginByPhone(DataHelper.AuthInfo info, DataHelper.ClientPhone phone) {
        byPhone.click();
        loginInput.sendKeys(phone.getPhone());
        passwordInput.sendKeys(info.getPassword());
        buttonSubmit.click();
        return new MainPage(driver);
    }

    public MainPage LoginByEmail(DataHelper.AuthInfo info, DataHelper.ClientMail mail) {
        byEmail.click();
        loginInput.sendKeys(mail.getEmail());
        passwordInput.sendKeys(info.getPassword());
        buttonSubmit.click();
        return new MainPage(driver);
    }
}
