package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
    WebDriver driver;
    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//*[text()=\"Sign up\"]")
    WebElement btn_SignUp;
    @FindBy(xpath = "//a[contains(@href, '#login')]")
    WebElement btn_SignIn;

    public void clickSignUp() {
        btn_SignUp.click();
    }
    public void clickSignIn() {
        btn_SignIn.click();
    }
    public String getLoginSuccess(String username) {
        String xpath = "//a[contains(@href,'" + username + "')]";
        WebElement txtUsername = driver.findElement(By.xpath(xpath));
        return txtUsername.getText();
    }
}
