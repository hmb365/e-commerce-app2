package com.regression.tests.loginTests;

import com.envision.automation.application.shoppingcart.LandingPage;
import com.envision.automation.application.shoppingcart.LoginPage;
import com.envision.automation.application.shoppingcart.MyAccountPage;
import com.envision.automation.application.testDataManager.TestJsonGenerator;
import com.envision.automation.framework.reusables.BaseAsserts;
import com.envision.automation.framework.reusables.BaseTest;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoginTests extends BaseTest {

    String username = TestJsonGenerator.getDataForLogin("username");
    String password = TestJsonGenerator.getDataForLogin("password");



    @Test
    public void validateSuccessfulLoginToApplication() throws IOException, ParseException {
        LandingPage landingPage = new LandingPage(browserManager.getDriver());
        LoginPage loginPage =landingPage
                .navigateToWebsite()
                .clickSignIn();

        MyAccountPage myAccountPage =
                loginPage
                        .loginToApplication(username,password);
        boolean status =myAccountPage.isSignOutPresentOnPage();
        BaseAsserts.ShouldBeTrue(status,"SignOut Not displayed");

        landingPage =myAccountPage.clickSignOut();
        status = landingPage.isSignInDisplayed();
        BaseAsserts.ShouldBeTrue(status,"SignIn is not displayed");
    }


    @Test
    public void validateUnsuccessfulLoginToApplication() throws IOException, InterruptedException {
        LandingPage landingPage = new LandingPage(browserManager.getDriver());
        LoginPage loginPage =landingPage
                .navigateToWebsite()
                .clickSignIn();

        MyAccountPage myAccountPage =
                loginPage
                        .loginToApplication(username,password)
                        .checkIfSignOutButtonDisplayed();

        Thread.sleep(30000);
    }

}
