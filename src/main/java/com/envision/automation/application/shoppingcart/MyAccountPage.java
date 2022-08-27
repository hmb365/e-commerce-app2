package com.envision.automation.application.shoppingcart;

import com.automation.framework.reusables.BaseAsserts;
import com.automation.framework.reusables.BaseUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.IOException;

public class MyAccountPage extends BaseUtils {

    WebDriver driver;
    public MyAccountPage(WebDriver driver){
        super(driver);
        this.driver = driver;
    }

    public boolean isSignOutPresentOnPage() throws IOException {
        WebElement signOut =waitUntilVisibleAndReturnElement("shoppingCart.MyAccountPage.btnSignOut",20);
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
