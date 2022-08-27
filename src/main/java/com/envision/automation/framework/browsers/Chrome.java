package com.envision.automation.framework.browsers;

import com.automation.framework.configurations.ConfigurationLoader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class Chrome extends Browser {

    ChromeOptions chromeOptions;

    @Override
    public void setCapabilities() {
        chromeOptions = new ChromeOptions();
        setHeadless(ConfigurationLoader.configOptions.isHeadless());
        setRunOn(ConfigurationLoader.configOptions.getRunOn());
        if(isHeadless()){
            chromeOptions.setHeadless(true);
        }
        Map<String,String> prefrences = new HashMap<>();
        prefrences.put("excludeSwitches","disable-popup-blocking");
        prefrences.put("download.default_directory",System.getProperty("user.dir")+"/src/test/resources/downloads/");
        chromeOptions.setExperimentalOption("prefs",prefrences);
    }

    @Override
    public WebDriver launchBrowser() throws Exception {
        setCapabilities();
        WebDriver driver = null;
        if(getRunOn().equalsIgnoreCase("local")){
            System.setProperty("webdriver.chrome.driver", ConfigurationLoader.configOptions.getChromeDriverPath());
            driver = new ChromeDriver(chromeOptions); //launch the browser
        }else{
            driver = new RemoteWebDriver(new URL(ConfigurationLoader.configOptions.getSeleniumHubUrl()),chromeOptions);
        }
        return driver;
    }
}
