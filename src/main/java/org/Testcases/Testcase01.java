package org.Testcases;

import manager.DriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.SignUpPage;
import utils.RandomString;

public class Testcase01 {
    static HomePage objHomePage;
    SignUpPage objSignUpPage;
    DriverManager driverManager = new DriverManager();
    @Test
    public void Verify_create_account() throws InterruptedException {
        WebDriver driver = driverManager.createWebDriver();
        driver.get("https://react-redux.realworld.io/");
        objHomePage = new HomePage(driver);
        objSignUpPage = new SignUpPage(driver);
        objHomePage.clickSignUp();

        //Generate random account information
        String exp_username = RandomString.getAlphaNumericString(6);
        String password = RandomString.getAlphaNumericString(8);
        String email = RandomString.getAlphaNumericString(8) + "@gmail.com";

        //Input all information
        objSignUpPage.setTbx_Username(exp_username);
        objSignUpPage.setTbx_Email(email);
        objSignUpPage.setTbx_Password(password);

        //Click sign in
        objSignUpPage.clickSignIn();
        Thread.sleep(5000);
        String act_username = objHomePage.getLoginSuccess(exp_username);
        Thread.sleep(2000);
        Assert.assertEquals(exp_username,act_username,"User access the home page successfully");

    }
}