package com.envision.automation.application.shoppingcart;


import com.envision.automation.framework.reusables.BaseAsserts;
import com.envision.automation.framework.reusables.BaseUtils;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.IOException;

public class LandingPage extends BaseUtils {


    public LandingPage(WebDriver driver){
        super(driver);
    }


    public LandingPage navigateToWebsite(){
        launchApplication("http://automationpractice.com");
        return this;
    }


    public LoginPage clickSignIn() throws IOException {
        clickOn("shoppingCart.LandingPage.btnSignIn");
        sleepFor(10);
        return new LoginPage(driver);
    }

    public boolean isSignInDisplayed() throws IOException {
        WebElement element =waitUntilVisibleAndReturnElement("shoppingCart.LandingPage.btnSignIn",20);
        return element.isDisplayed(); //true or false.
    }

    public LandingPage enterEmailAddress(String emailAddress) throws IOException {
        typeInto("shoppingCart.LoginPage.tbxEmailAddress",emailAddress);
        return this;
    }


    public RegistrationPage clickOnCreateAnAccount() throws IOException {
        clickOn("shoppingCart.LoginPage.btnCreateAnAccount");
        return new RegistrationPage(this.driver);
    }
}
