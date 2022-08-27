package com.envision.automation.framework.browsers;

import com.automation.framework.configurations.ConfigurationLoader;
import com.automation.framework.reusables.BaseUtils;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class BrowserManager {

    private WebDriver driver;

    public WebDriver getDriver() {
        return driver;
    }

    public void initializeBrowser() throws Exception {
        Browser browser = null;

        if(BrowserType.EDGE.toString().equalsIgnoreCase(ConfigurationLoader.configOptions.getBrowserType())){
            browser = new Edge();
        }else if (BrowserType.CHROME.toString().equalsIgnoreCase(ConfigurationLoader.configOptions.getBrowserType())){
            browser = new Chrome();
        }else if (BrowserType.FIREFOX.toString().equalsIgnoreCase(ConfigurationLoader.configOptions.getBrowserType())){
            browser = new Firefox();
        }
        else if (BrowserType.IE.toString().equalsIgnoreCase(ConfigurationLoader.configOptions.getBrowserType())){
            browser = new InternetExplorer();
        }else{
            throw new UnsupportedOperationException("Invalid Browser Value provided in Config file. Browser not supported");
        }

        this.driver = browser.launchBrowser();
        this.driver.manage().window().maximize(); //maximizes the browser
        this.driver.manage().timeouts().pageLoadTimeout(ConfigurationLoader.configOptions.getPageLoadWait(), TimeUnit.SECONDS); // default page load wait, wait for page to load completely
        this.driver.manage().timeouts().implicitlyWait(ConfigurationLoader.configOptions.getImplicitWait(), TimeUnit.SECONDS);// default element load wait, that before failing the script due to no element error, wait for 30 seconds

    }

    public void captureProof(String testName) throws IOException {
        BaseUtils baseUtils = new BaseUtils(getDriver());
        baseUtils.takeScreenshot(testName);
    }
}
