package com.envision.automation.framework.browsers;

import com.envision.automation.framework.configurations.ConfigurationLoader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class Edge extends Browser{

    EdgeOptions edgeOptions;

    @Override
    public void setCapabilities() {
        edgeOptions = new EdgeOptions();
        setHeadless(ConfigurationLoader.configOptions.isHeadless());
        setRunOn(ConfigurationLoader.configOptions.getRunOn());
        Map<String,String> prefrences = new HashMap<>();
        prefrences.put("excludeSwitches","disable-popup-blocking");
        prefrences.put("download.default_directory",System.getProperty("user.dir")+"/src/test/resources/downloads/");
        edgeOptions.setCapability("prefs",prefrences);
    }

    @Override
    public WebDriver launchBrowser() throws Exception {
        setCapabilities();
        WebDriver driver = null;
        if(getRunOn().equalsIgnoreCase("local")){
            System.setProperty("webdriver.edge.driver",ConfigurationLoader.configOptions.getEdgeDriverPath());
            driver = new EdgeDriver(edgeOptions); //launch the browser
        }else{
            driver = new RemoteWebDriver(new URL(ConfigurationLoader.configOptions.getSeleniumHubUrl()),edgeOptions);
        }
        return driver;
    }


}
