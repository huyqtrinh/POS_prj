package org.Testcases;

import manager.DriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.SignInPage;
import utils.RandomString;

public class Testcase02 {
    static HomePage objHomePage;
    SignInPage objSignInPage;
    DriverManager driverManager = new DriverManager();
    @Test
    public void Verify_error_message() throws InterruptedException {
        WebDriver driver = driverManager.createWebDriver();
        driver.get("https://react-redux.realworld.io/");
        objHomePage = new HomePage(driver);
        objSignInPage = new SignInPage(driver);
        objHomePage.clickSignIn();

        //Generate random account information
        String email = RandomString.getAlphaNumericString(8) + "@gmail.com";
        String password = RandomString.getAlphaNumericString(8);

        //Input all information
        objSignInPage.setTbx_Email(email);
        objSignInPage.setTbx_Password(password);

        //Click sign in
        objSignInPage.clickSignIn();
        Thread.sleep(5000);
        boolean isErrorDisplay = objSignInPage.chkErrorMsg();
        Thread.sleep(2000);
        Assert.assertTrue(isErrorDisplay,"Error message displayed. User cannot go to home page");

    }
}