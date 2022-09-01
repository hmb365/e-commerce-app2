package com.envision.automation.framework.reusables;

import com.envision.automation.framework.browsers.BrowserManager;
import com.envision.automation.framework.configurations.ConfigurationLoader;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.sql.DriverManager;

public class BaseTest { //reusable class to perform test actions - opening browsers, closings, takingscreenshots

    public BaseUtils baseUtils;
    public String currentWindowSession;
    public BrowserManager browserManager;

    @BeforeSuite
    public void loadConfigurations() throws IOException {
        //Step1. Before anything runs, i want my configurations to be loaded.
        ConfigurationLoader configurationLoader = new ConfigurationLoader();
        configurationLoader.loadConfigurationsForFramework();
        browserManager = new BrowserManager();
    }


    @BeforeTest
    public void launchBrowser() throws Exception {
        browserManager.initializeBrowser();
        currentWindowSession =browserManager.getDriver().getWindowHandle();
    }

    @BeforeMethod
    public void testcaseRecorderStart(Method method) throws Exception {
        if(ConfigurationLoader.configOptions.isRecordVideo() && ConfigurationLoader.configOptions.getRunOn().equalsIgnoreCase("local"))
            ScreenRecorderUtils.startRecord(method.getName());

    }

    @AfterMethod
    public void testcaseRecorderStop(Method method) throws Exception {
        if (ConfigurationLoader.configOptions.isRecordVideo() && ConfigurationLoader.configOptions.getRunOn().equalsIgnoreCase("local"))
            ScreenRecorderUtils.stopRecord();
    }


    @AfterMethod
    public void afterEveryTestCaptureProofs(Method method) throws IOException {
        browserManager.captureProof(method.getName());
    }


    @AfterTest
    public void tearDownDriver(){
        browserManager.getDriver().quit();
    }

    public void sleepFor(int seconds){
        try {
            Thread.sleep(seconds * 1000);
        }catch(Exception e){

        }
    }

}
