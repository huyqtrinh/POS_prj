package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignInPage {
    WebDriver driver;
    public SignInPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//input[@placeholder='Email']")
    WebElement tbx_Email;
    @FindBy(xpath = "//input[@placeholder='Password']")
    WebElement tbx_Password;
    @FindBy(xpath = "//button[@type='submit']")
    WebElement btn_SignIn;
    @FindBy(xpath = "//*[@class='error-messages']")
    WebElement txt_ErrorMsg;

    public void setTbx_Email(String email){
        tbx_Email.sendKeys(email);
    }
    public void setTbx_Password(String password){
        tbx_Password.sendKeys(password);
    }
    public void clickSignIn() {
        btn_SignIn.click();
    }
    public boolean chkErrorMsg(){
        return txt_ErrorMsg.isDisplayed();
    }

}
