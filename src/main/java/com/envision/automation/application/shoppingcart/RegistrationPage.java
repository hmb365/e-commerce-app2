package com.envision.automation.application.shoppingcart;

import com.automation.framework.reusables.BaseUtils;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

public class RegistrationPage extends BaseUtils {

    public RegistrationPage(WebDriver driver){
        super(driver);
    }

    public RegistrationPage enterFirstName(String firstName) throws IOException {
        typeInto("shoppingCart.RegistrationPage.tbxFirstName",firstName);
        return this;
    }

    public RegistrationPage enterLastName(String lastName) throws IOException {
        typeInto("shoppingCart.RegistrationPage.tbxLastName",lastName);
        return this;
    }

    public RegistrationPage enterPassword(String password) throws IOException {
        typeInto("shoppingCart.RegistrationPage.tbxPassword",password);
        return this;
    }

    public RegistrationPage selectTitleMr() throws IOException {
        clickOn("shoppingCart.RegistrationPage.chkbxTitle");
        return this;
    }

}
