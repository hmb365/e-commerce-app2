package com.envision.automation.application.shoppingcart;


import com.envision.automation.framework.reusables.BaseUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.IOException;

public class LoginPage extends BaseUtils {

    WebDriver driver; //so that we can pass the driver to another page

    LoginPage(WebDriver driver){
        super(driver);
        this.driver = driver;
    }

    public LoginPage enterUsername(String username) throws IOException {
        typeInto("shoppingCart.LoginPage.tbxUsername",username);
        return this;
    }

    public LoginPage enterPassword(String password) throws IOException {
        typeInto("shoppingCart.LoginPage.tbxPassword",password);
        return this;
    }
    //Sarada_TC028
    public WebElement  forgotPassWordDisplay() throws IOException {
       WebElement displayForgotPassword = waitUntilElementVisibleAndGetElement("shoppingCart.LoginPage.chkForgotPassword",30);
        return displayForgotPassword;
    }

    public MyAccountPage clickSubmit() throws IOException {
        clickOn("shoppingCart.LoginPage.btnSubmit");
        return new MyAccountPage(driver);
    }

    public MyAccountPage loginToApplication(String username, String password) throws IOException {
       return enterUsername(username)
                .enterPassword(password)
                .clickSubmit();
    }

}
