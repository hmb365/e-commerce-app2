package com.regression.tests.registrationTests;

import com.envision.automation.application.shoppingcart.LandingPage;
import com.envision.automation.application.shoppingcart.RegistrationPage;
import com.envision.automation.application.testDataManager.RandomDataGenerator;
import com.envision.automation.application.testDataManager.TestJsonGenerator;
import com.envision.automation.framework.reusables.BaseTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class RegistrationTests extends BaseTest {


    String firstNmae = TestJsonGenerator.getDataForRegistration("firstName");
    String lastName = TestJsonGenerator.getDataForRegistration("lastName");
    String password = TestJsonGenerator.getDataForRegistration("password");
    String emailId = TestJsonGenerator.getDataForRegistration("emailId");


    @Test (description = "Registration Test using valid data")
    public void validateRegisterUserFunctionality() throws InterruptedException, IOException {
        LandingPage landingPage = new LandingPage(browserManager.getDriver());
        landingPage
                .navigateToWebsite()
                .clickSignIn();
        RegistrationPage registrationPage=
                landingPage.enterEmailAddress(emailId)
                .clickOnCreateAnAccount();

        registrationPage
                .selectTitleMr()
                .enterFirstName(firstNmae)
                .enterLastName(lastName)
                .enterPassword(password);

    }

    @Test (description = "Registration Test using valid data")
    public void validateRegistrationWithRandomData() throws InterruptedException, IOException {
        LandingPage landingPage = new LandingPage(browserManager.getDriver());
        landingPage
                .navigateToWebsite()
                .clickSignIn();
        RegistrationPage registrationPage=
                landingPage.enterEmailAddress(RandomDataGenerator.getEmailAddress())
                        .clickOnCreateAnAccount();

        registrationPage
                .selectTitleMr()
                .enterFirstName(RandomDataGenerator.getFirstName())
                .enterLastName(RandomDataGenerator.getLastName())
                .enterPassword(RandomDataGenerator.getUniquePassword());
        Thread.sleep(5000);
    }

}
