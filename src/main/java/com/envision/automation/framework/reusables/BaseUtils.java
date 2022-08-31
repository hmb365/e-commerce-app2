package com.envision.automation.framework.reusables;

import com.envision.automation.framework.configurations.ConfigurationLoader;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.*;
import sun.reflect.annotation.ExceptionProxy;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.TimeUnit;

public class BaseUtils { //Reusable class to perform selenium actions

    public WebDriver driver;
    static int waitTime = ConfigurationLoader.configOptions.getExplicitWait();

    public BaseUtils(WebDriver driver){
        this.driver = driver;
    }

    public void sleepFor(int seconds){
        try{
            Thread.sleep(seconds *1000);
        }catch(Exception e){

        }
    }

    public void takeScreenshot(String testName) throws IOException {
        if(ConfigurationLoader.configOptions.isTakeScreenshot()) {
            TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
            File screenshot = takesScreenshot.getScreenshotAs(OutputType.FILE); //raw out into a file
            FileUtils.copyFile(
                    screenshot,
                    new File(
                            System.getProperty("user.dir") + "/src/test/resources/screenshots/screenshot-" + testName + ".png"));
        }
    }

    public String getObjectRepositoryLocator(String locatorName) throws IOException {
        Properties properties = new Properties();
        properties.load(new FileInputStream(new File(System.getProperty("user.dir")+"/src/main/resources/objectRepository/OR.properties")));
        return properties.getProperty(locatorName);
    }

    //crap!!! never do like this
//    public static WebDriver launchBrowser(String browser) throws MalformedURLException {//downloading a file
//        WebDriver driver = null;
//        if(browser.equalsIgnoreCase("Chrome")){
//            ChromeOptions chromeOptions = new ChromeOptions();
//            Map<String,String> prefrences = new HashMap<>();
//            prefrences.put("excludeSwitches","disable-popup-blocking");
//            prefrences.put("download.default_directory",System.getProperty("user.dir")+"/src/test/resources/downloads/");
//            chromeOptions.setExperimentalOption("prefs",prefrences);
//            if(ConfigurationLoader.configOptions.isHeadless()) {
//                chromeOptions.setHeadless(true);
//            }
//            if(ConfigurationLoader.configOptions.getRunOn().equalsIgnoreCase("local")) {
//                System.setProperty("webdriver.chrome.driver", ConfigurationLoader.configOptions.getChromeDriverPath());
//                driver = new ChromeDriver(chromeOptions); //launch the browser
//            }else if(ConfigurationLoader.configOptions.getRunOn().equalsIgnoreCase("remote")){
//                driver = new RemoteWebDriver(new URL(ConfigurationLoader.configOptions.getSeleniumHubUrl()),chromeOptions);
//            }
//        }else if(browser.equalsIgnoreCase("Edge")){
//            EdgeOptions edgeOptions = new EdgeOptions();
//            Map<String,String> prefrences = new HashMap<>();
//            prefrences.put("excludeSwitches","disable-popup-blocking");
//            prefrences.put("download.default_directory",System.getProperty("user.dir")+"/src/test/resources/downloads/");
//            edgeOptions.setCapability("prefs",prefrences);
//            System.setProperty("webdriver.edge.driver",ConfigurationLoader.configOptions.getEdgeDriverPath());
//            driver = new EdgeDriver(); //launch the browser
//        }else if(browser.equalsIgnoreCase("Firefox")){
//            System.setProperty("webdriver.gecko.driver", ConfigurationLoader.configOptions.getGeckoDriverPath());
//            driver = new FirefoxDriver();
//        }
//        driver.manage().window().maximize(); //maximizes the browser
//        driver.manage().timeouts().pageLoadTimeout(waitTime, TimeUnit.SECONDS); // default page load wait, wait for page to load completely
//        driver.manage().timeouts().implicitlyWait(waitTime,TimeUnit.SECONDS);// default element load wait, that before failing the script due to no element error, wait for 30 seconds
//
//        return driver;
//    }


    public List<WebElement> findElementsBy(String locateBy, String locatorValue ){
        List<WebElement> listOfWebElements = null;
        switch(locateBy){
            case "id":
                listOfWebElements = driver.findElements(By.id(locatorValue));
                break;
            case "name":
                listOfWebElements = driver.findElements(By.name(locatorValue));
                break;
            case "class":
                listOfWebElements = driver.findElements(By.className(locatorValue));
                break;
            case "tag":
                listOfWebElements = driver.findElements(By.tagName(locatorValue));
                break;
            case "lt":
                listOfWebElements = driver.findElements(By.linkText(locatorValue));
                break;
            case "plt":
                listOfWebElements = driver.findElements(By.partialLinkText(locatorValue));
                break;
            case "css":
                listOfWebElements = driver.findElements(By.cssSelector(locatorValue));
                break;
            case "xpath":
                listOfWebElements = driver.findElements(By.xpath(locatorValue));
                break;
        }
        scrollToElementView(listOfWebElements.get(0));
        return listOfWebElements;
    }



    public WebElement findElementBy(String locateBy, String locatorValue ){
        WebElement element = null;
        switch(locateBy){
            case "id":
               element = driver.findElement(By.id(locatorValue));
               break;
            case "name":
                element = driver.findElement(By.name(locatorValue));
                break;
            case "class":
                element = driver.findElement(By.className(locatorValue));
                break;
            case "tag":
                element = driver.findElement(By.tagName(locatorValue));
                break;
            case "lt":
                element = driver.findElement(By.linkText(locatorValue));
                break;
            case "plt":
                element = driver.findElement(By.partialLinkText(locatorValue));
                break;
            case "css":
                element = driver.findElement(By.cssSelector(locatorValue));
                break;
            case "xpath":
                element = driver.findElement(By.xpath(locatorValue));
                break;
        }
        scrollToElementView(element);
        return element;
    }

    public By findBy(String locateBy, String locatorValue ){
        By by = null;
        switch(locateBy){
            case "xpath":
                by = By.xpath(locatorValue);
                break;
            case "id":
                by = By.id(locatorValue);
                break;
            case "name":
                by = By.name(locatorValue);
                break;
            case "css":
                by = By.cssSelector(locatorValue);
                break;
            case "class":
                by = By.className(locatorValue);
                break;
            case "lt":
                by = By.linkText(locatorValue);
                break;
            case "plt":
                by = By.partialLinkText(locatorValue);
                break;
            case "tag":
                by = By.tagName(locatorValue);
                break;
        }

        return by;
    }

    public void launchApplication(String url){
        driver.get(url);
    }

    public void clickOn(String locatorName) throws IOException {
        waitUntilElementClickableAndClick(locatorName,waitTime);
    }

    public void typeInto(String locatorName,String value) throws IOException {
        waitUntilElementVisibleAndTypeInto(locatorName,waitTime,value);
    }

    public void doubleClickOn(String locatorName) throws IOException {
        WebElement elementToClick =waitUntilElementClickable(locatorName,waitTime);
        Actions actions = new Actions(driver);
        actions.doubleClick(elementToClick).build().perform();
    }

    public void rightClickOn(String locatorName) throws IOException {
        WebElement elementToClick =waitUntilElementClickable(locatorName,waitTime);
        Actions actions = new Actions(driver);
        actions.contextClick(elementToClick).build().perform();
    }

    public void mouseClickOn(String locatorName) throws IOException {
        WebElement elementToClick =waitUntilElementClickable(locatorName,waitTime);
        Actions actions = new Actions(driver);
        actions.moveToElement(elementToClick).click().build().perform();
    }

    public void mouseHoverTo(String locatorName) throws IOException {
        WebElement elementToClick =waitUntilVisibleAndReturnElement(locatorName,waitTime);
        Actions actions = new Actions(driver);
        actions.moveToElement(elementToClick).build().perform();
    }


    public void mouseClickAndTypeInto(String locatorName,String value) throws IOException {
        WebElement elementToClick =waitUntilVisibleAndReturnElement(locatorName,waitTime);
        Actions actions = new Actions(driver);
        actions.moveToElement(elementToClick).click().sendKeys(value).build().perform();
    }



    public void selectBy(String howToSelect,String listValue, String locatorName) throws IOException {
        WebElement elementToSelect = waitUntilVisibleAndReturnElement(locatorName,waitTime);; //Step 1, find an element to select
        Select selectObj = new Select(elementToSelect); //create an object of select class and pass element as constructor param.
        //3 ways to select a value, select by value, select by index, select by visibletext
        if(howToSelect.equalsIgnoreCase("ByValue")){
            selectObj.selectByValue(listValue);
        }else if(howToSelect.equalsIgnoreCase("ByVisibleText")){
            selectObj.selectByVisibleText(listValue);
        }else if(howToSelect.equalsIgnoreCase("ByIndex")){
            selectObj.selectByIndex(Integer.parseInt(listValue));
        }

    }

    public void acceptAlert() {
        driver.switchTo().alert().accept();
    }

    public void dismissAlert() {
        driver.switchTo().alert().dismiss();
    }

    public void typeIntoAlertAndAccept(String value) {
        Alert alert =driver.switchTo().alert();
        alert.sendKeys(value);
        alert.accept();
    }


    public void switchToIframe(String locatorName ) throws IOException {
        String elementHow =getObjectRepositoryLocator(locatorName);
        String how = elementHow.split(":",2)[0];
        String howValue = elementHow.split(":",2)[1];
        WebElement elementToSwitch = findElementBy(how,howValue); //Step 1, find an element to select
        driver.switchTo().frame(elementToSwitch);
    }

    public void switchToMainPage(){
        driver.switchTo().defaultContent();
    }


    public void switchToSecondWindow(){
        Set<String> setOfWindows = driver.getWindowHandles();
        List<String> listOfWindows = new ArrayList<String>(setOfWindows);
        int secondWindowIndex = 1;
        driver.switchTo().window(listOfWindows.get(secondWindowIndex));
    }

    public void switchToLastWindow(){
        Set<String> setOfWindows = driver.getWindowHandles();
        List<String> listOfWindows = new ArrayList<String>(setOfWindows);
        int lastWindow = listOfWindows.size()-1;
        driver.switchTo().window(listOfWindows.get(lastWindow));
    }

    public void switchToSecondLastWindow(){
        Set<String> setOfWindows = driver.getWindowHandles();
        List<String> listOfWindows = new ArrayList<String>(setOfWindows);
        int lastWindow = listOfWindows.size()-2;
        driver.switchTo().window(listOfWindows.get(lastWindow));
    }

    public void closeAllOtherWindowsExceptMain(String currentWindow){
        Set<String> allWindowsOpen = driver.getWindowHandles();// sessions id of all windows open
        List<String> windows = new ArrayList<String>(allWindowsOpen);
        for(int i=0;i< windows.size();i++){
            if(!windows.get(i).equals(currentWindow)){
                driver.switchTo().window(windows.get(i)).close();
            }
        }
        driver.switchTo().window(currentWindow);
    }

    public void openWindows(int noOfWindows){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        for(int i=0;i<noOfWindows;i++){
            js.executeScript("window.open();");
        }
    }

    public void openNewTabWith(String url){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.open();");
        driver.get(url);
    }


    public void scrollToTopOfPage(){
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("window.scrollTo(0,-document.body.scrollHeight);");

    }

    public void scrollToBottomOfPage(){
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("window.scrollTo(0,document.body.scrollHeight);");
    }

    public void scrollDownToMiddleOfPage(){
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("window.scrollTo(0,document.body.scrollHeight/2);");
    }

    public void scrollTopToMiddleOfPage(){
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("window.scrollTo(0,-document.body.scrollHeight/2);");
    }

    public void scrollToElementView(WebElement element){
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].scrollIntoView(true);",element);
    }

    public void waitUntilElementVisible(WebElement element, int noOfSeconds){
        Wait wait = new WebDriverWait(driver,noOfSeconds);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public WebElement waitUntilVisibleAndReturnElement(String locatorName,int noOfSeconds) throws IOException {
        String elementHow =getObjectRepositoryLocator(locatorName);
        String how = elementHow.split(":",2)[0];
        String howValue = elementHow.split(":",2)[1];
        By by =findBy(how,howValue);
        WebDriverWait wait = new WebDriverWait(driver,noOfSeconds);
        WebElement element =wait.until(ExpectedConditions.visibilityOfElementLocated(by));

        return element;
    }

    public WebElement waitUntilElementClickableAndClick(String locatorName,int noOfSeconds) throws IOException {

        String elementHow =getObjectRepositoryLocator(locatorName);
        String how = elementHow.split(":",2)[0];
        String howValue = elementHow.split(":",2)[1];
        By by =findBy(how,howValue);
        WebDriverWait wait = new WebDriverWait(driver,noOfSeconds);
        wait.until(ExpectedConditions.presenceOfElementLocated(by));
        WebElement element =wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        wait.until(ExpectedConditions.elementToBeClickable(element));
        scrollToElementView(element);
        element.click();
        return element;
    }

    public WebElement waitUntilElementClickable(String locatorName,int noOfSeconds) throws IOException {
        String elementHow =getObjectRepositoryLocator(locatorName);
        String how = elementHow.split(":",2)[0];
        String howValue = elementHow.split(":",2)[1];
        By by =findBy(how,howValue);
        WebDriverWait wait = new WebDriverWait(driver,noOfSeconds);
        WebElement element =wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        wait.until(ExpectedConditions.elementToBeClickable(element));
        scrollToElementView(element);
        return element;
    }


    public WebElement waitUntilElementVisibleAndTypeInto(String locatorName,int noOfSeconds,String textToType) throws IOException {
        String elementHow =getObjectRepositoryLocator(locatorName);
        String how = elementHow.split(":",2)[0];
        String howValue = elementHow.split(":",2)[1];
        By by =findBy(how,howValue);
        WebDriverWait wait = new WebDriverWait(driver,noOfSeconds);
        WebElement element =wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        wait.until(ExpectedConditions.elementToBeClickable(element));
        scrollToElementView(element);
        element.click();
        element.clear();
        element.sendKeys(textToType);
        return element;
    }

    public String waitUntilElementVisibleAndGetText(String locatorName,int noOfSeconds,String textToType) throws IOException {
        String elementHow =getObjectRepositoryLocator(locatorName);
        String how = elementHow.split(":",2)[0];
        String howValue = elementHow.split(":",2)[1];
        By by =findBy(how,howValue);
        WebDriverWait wait = new WebDriverWait(driver,noOfSeconds);
        WebElement element =wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        scrollToElementView(element);
        return element.getText();
    }

    public boolean waitUntilElementDisappears(String locatorName,int noOfSeconds) throws IOException {
        String elementHow =getObjectRepositoryLocator(locatorName);
        String how = elementHow.split(":",2)[0];
        String howValue = elementHow.split(":",2)[1];
        By by =findBy(how,howValue);
        WebDriverWait wait = new WebDriverWait(driver,noOfSeconds);
        boolean status =wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
        return status;
    }




}
