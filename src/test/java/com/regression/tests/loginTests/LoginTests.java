package com.regression.tests.loginTests;

import com.envision.automation.application.shoppingcart.LandingPage;
import com.envision.automation.application.shoppingcart.LoginPage;
import com.envision.automation.application.shoppingcart.MyAccountPage;
import com.envision.automation.application.testDataManager.TestJsonGenerator;
import com.envision.automation.framework.reusables.BaseAsserts;
import com.envision.automation.framework.reusables.BaseTest;
import com.envision.automation.framework.reusables.Constants;
import com.envision.automation.framework.reusables.DataProviderUtils;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoginTests extends BaseTest {

    String username = TestJsonGenerator.getDataForLogin("username");
    String password = TestJsonGenerator.getDataForLogin("password");

        //TC_022 - Positive Scenario : Valid Data Login
    @Test (dataProvider = "LoginDataProvider",dataProviderClass = DataProviderUtils.class)
    public void validateSuccessfulLoginToApplication(String username,String password) throws IOException {
        LandingPage landingPage = new LandingPage(browserManager.getDriver());
        LoginPage loginPage =landingPage
                .navigateToWebsite()
                .clickSignIn();
        MyAccountPage myAccountPage =
                loginPage
                        .loginToApplication(username,password);

        //TC-022  Ensure that your user name is displayed on home page
        String userNameDisplay= myAccountPage.userNameDisplay();
        System.out.println("UserName displayed: "+ userNameDisplay);

        boolean status =myAccountPage.isSignOutPresentOnPage();
        BaseAsserts.ShouldBeTrue(status,"SignOut Not displayed");

        landingPage =myAccountPage.clickSignOut();
        status = landingPage.isSignInDisplayed();
        BaseAsserts.ShouldBeTrue(status,"SignIn is not displayed");
    }
    @Test
    //TC_028-Checking forgot password link
    public void forgotPasswordLinkTest() throws IOException {
        LandingPage landingPage = new LandingPage(browserManager.getDriver());
        LoginPage loginPage =landingPage
                .navigateToWebsite()
                .clickSignIn();
        BaseAsserts.ShouldBeTrue(loginPage.forgotPassWordDisplay(),Constants.FORGOT_PASSWORD_DISPLAY_MESSAGE);
    }

    @Test
    //Tc_029- retrieving password link
    public void retrievePasswordTest() throws IOException {
        LandingPage landingPage = new LandingPage(browserManager.getDriver());
        LoginPage loginPage =landingPage
                .navigateToWebsite()
                .clickSignIn();

        String message = loginPage.retrievePassWord();
        BaseAsserts.ShouldBeEqual(message,Constants.PASSWORD_RETRIEVAL_MESSAGE+ TestJsonGenerator.getDataForLogin("username"),"Assertion failed");
    }

    //TC_023 -Negative Scenario: Invalid Data Login
    @Test (dataProvider = "randomLoginDataProvider",dataProviderClass = DataProviderUtils.class)
    public void validateUnsuccessfulLoginToApplication(String username,String password) throws IOException {
        LandingPage landingPage = new LandingPage(browserManager.getDriver());
        LoginPage loginPage =landingPage
                .navigateToWebsite()
                .clickSignIn();

        MyAccountPage myAccountPage =
                loginPage
                        .loginToApplication(username,password);

        //TC-023  receive invalid credentials error message
        String invalidCredentialsMsgDisplayText= myAccountPage.invalidCredentialsMsgDisplay();
        System.out.println("Invalid Credentials Msg Display: "+ invalidCredentialsMsgDisplayText);

//        myAccountPage.checkIfSignOutButtonDisplayed();

    }
}
