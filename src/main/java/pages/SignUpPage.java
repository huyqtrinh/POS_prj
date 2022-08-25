package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignUpPage {
    WebDriver driver;
    public SignUpPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//input[@placeholder='Username']")
    WebElement tbx_Username;
    @FindBy(xpath = "//input[@placeholder='Email']")
    WebElement tbx_Email;
    @FindBy(xpath = "//input[@placeholder='Password']")
    WebElement tbx_Password;
    @FindBy(xpath = "//button[@type='submit']")
    WebElement btn_SignIn;

    public void setTbx_Username(String username){
        tbx_Username.sendKeys(username);
    }
    public void setTbx_Email(String email){
        tbx_Email.sendKeys(email);
    }
    public void setTbx_Password(String password){
        tbx_Password.sendKeys(password);
    }
    public void clickSignIn() {
        btn_SignIn.click();
    }
}
