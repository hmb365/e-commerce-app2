package com.envision.automation.application.shoppingcart;


import com.envision.automation.framework.reusables.BaseAsserts;
import com.envision.automation.framework.reusables.BaseUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.IOException;

public class MyAccountPage extends BaseUtils {


    public MyAccountPage(WebDriver driver){
        super(driver);

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
