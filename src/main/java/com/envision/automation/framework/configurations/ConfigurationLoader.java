package com.envision.automation.framework.configurations;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigurationLoader {

    public static ConfigOptions configOptions;
    private Properties properties;

    public void readConfigFileProperties() throws IOException {
        properties = new Properties();
        File configFile = new File(System.getProperty("user.dir")
                +"/src/main/resources/configs/appConfig.properties");
        properties.load(new FileInputStream(configFile));
    }

    public String getProperty(String name){
        return properties.getProperty(name);
    }

    public void loadConfigurationsForFramework() throws IOException {
        configOptions = new ConfigOptions();
        readConfigFileProperties();
        configOptions.setBrowserType(getProperty("browserType"));
        configOptions.setImplicitWait(Integer.parseInt(getProperty("implicitWait")));
        configOptions.setPageLoadWait(Integer.parseInt(getProperty("pageLoadWait")));
        configOptions.setExplicitWait(Integer.parseInt(getProperty("explicitWait")));
        configOptions.setTakeScreenshot(Boolean.parseBoolean(getProperty("takeScreenshot")));
        configOptions.setChromeDriverPath(getProperty("chromeDriverPath"));
        configOptions.setEdgeDriverPath(getProperty("edgeDriverPath"));
        configOptions.setGeckoDriverPath(getProperty("geckoDriverPath"));
        configOptions.setInternetDriverPath(getProperty("internetDriverPath"));
        configOptions.setRunOn(getProperty("runOn"));
        configOptions.setHeadless(Boolean.parseBoolean(getProperty("headless")));
        configOptions.setSeleniumHubUrl(getProperty("seleniumHubUrl"));
        configOptions.setNoOfDataSets(Integer.parseInt(getProperty("noOfDataSets")));
    }

}
