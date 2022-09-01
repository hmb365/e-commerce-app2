package com.envision.automation.application.shoppingcart;


import com.envision.automation.framework.configurations.ConfigurationLoader;
import com.envision.automation.framework.reusables.BaseAsserts;
import com.envision.automation.framework.reusables.BaseUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.IOException;

public class MyAccountPage extends BaseUtils {

    public MyAccountPage(WebDriver driver){
        super(driver);

    }

    //TC-022  Ensure that your user name is displayed on home page.
    public String userNameDisplay() throws IOException {
        String userNameDisplayText = waitUntilElementVisibleAndGetText("shoppingCart.LoginPage.userNameDisplay", ConfigurationLoader.configOptions.getExplicitWait());
        return userNameDisplayText;
    }
    //TC-023  receive invalid credentials error message
    public String invalidCredentialsMsgDisplay() throws IOException {
        String invalidCredentialsMsgDisplayText = waitUntilElementVisibleAndGetText("shoppingCart.LoginPage.invalidCredentialsMsgDisplayText", ConfigurationLoader.configOptions.getExplicitWait());
        return invalidCredentialsMsgDisplayText;
    }
    public boolean isSignOutPresentOnPage() throws IOException {
        WebElement signOut =waitUntilVisibleAndReturnElement("shoppingCart.MyAccountPage.btnSignOut",30);
        return signOut.isDisplayed();
    }

    public MyAccountPage checkIfSignOutButtonDisplayed() throws IOException {
        BaseAsserts.ShouldBeTrue(isSignOutPresentOnPage());
        return this;
    }

    public LandingPage clickSignOut() throws IOException {
        clickOn("shoppingCart.MyAccountPage.btnSignOut");
        return new LandingPage(this.driver);
    }
}
