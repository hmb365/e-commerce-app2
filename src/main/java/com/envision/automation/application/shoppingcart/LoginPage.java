package com.envision.automation.application.shoppingcart;


import com.envision.automation.application.testDataManager.TestJsonGenerator;
import com.envision.automation.framework.configurations.ConfigurationLoader;
import com.envision.automation.framework.reusables.BaseUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.IOException;

public class LoginPage extends BaseUtils {

    //WebDriver driver; //so that we can pass the driver to another page

    LoginPage(WebDriver driver){
        super(driver);
        //this.driver = driver;
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
       WebElement displayForgotPassword = waitUntilElementVisibleAndGetElement("shoppingCart.LoginPage.chkForgotPassword", ConfigurationLoader.configOptions.getExplicitWait());
        return displayForgotPassword;
    }
    //Sarada_TC029
    public String retrievePassWord() throws IOException {
        clickOn("shoppingCart.LoginPage.chkForgotPassword");
        waitUntilElementVisibleAndTypeInto("shoppingCart.LoginPage.tbxRetrieve",ConfigurationLoader.configOptions.getExplicitWait(), TestJsonGenerator.getDataForLogin("username"));
        clickOn("shoppingCart.LoginPage.lnkRetrieve");
         String successMessage = waitUntilElementVisibleAndGetText("shoppingCart.LoginPage.lnkMessage",ConfigurationLoader.configOptions.getExplicitWait());
        return successMessage;
    }

    public MyAccountPage clickSubmit() throws IOException {

        clickOn("shoppingCart.LoginPage.btnSubmit");
        //clickOn("shoppingCart.LoginPage.btnSubmit");
        return new MyAccountPage(driver);
    }

    public MyAccountPage loginToApplication(String username, String password) throws IOException {
       return enterUsername(username)
                .enterPassword(password)
                .clickSubmit();
    }

}
