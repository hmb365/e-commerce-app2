package com.envision.automation.framework.browsers;

import org.openqa.selenium.WebDriver;

public class InternetExplorer extends Browser{
    @Override
    public void setCapabilities() {
        throw new UnsupportedOperationException("Browser not supported");
    }

    @Override
    public WebDriver launchBrowser() throws Exception {
        throw new UnsupportedOperationException("Browser not supported");
    }
}
