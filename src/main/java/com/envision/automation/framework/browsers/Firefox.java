package com.envision.automation.framework.browsers;

import com.envision.automation.framework.configurations.ConfigurationLoader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class Firefox extends Browser{

    FirefoxOptions firefoxOptions;

    @Override
    public void setCapabilities() {
        firefoxOptions = new FirefoxOptions();
        setRunOn(ConfigurationLoader.configOptions.getRunOn());
    }

    @Override
    public WebDriver launchBrowser() throws Exception {
        setCapabilities();
        WebDriver driver = null;
        if(getRunOn().equalsIgnoreCase("local")){
            System.setProperty("webdriver.gecko.driver", ConfigurationLoader.configOptions.getGeckoDriverPath());
            driver = new FirefoxDriver();
        }else{
            driver = new RemoteWebDriver(new URL(ConfigurationLoader.configOptions.getSeleniumHubUrl()),firefoxOptions);
        }
        return driver;
    }

}
