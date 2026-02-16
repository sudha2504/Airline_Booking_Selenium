package com.docker.tests;

import com.docker.listeners.TestListener;
import com.docker.util.Config;
import com.docker.util.Constants;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestClass;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

@Listeners({TestListener.class})
public abstract class BaseTest {

    protected WebDriver driver;
    private static final Logger log = LoggerFactory.getLogger(BaseTest.class);

    @BeforeSuite
    public void setConfig() throws IOException {
        Config.initialise();
    }

    @BeforeTest
    public void setDriver(ITestContext ctx) throws MalformedURLException {
        this.driver = Boolean.parseBoolean(Config.get(Constants.GRID_ENABLED)) ? remoteDriver() : localDriver();
        ctx.setAttribute(Constants.DRIVER, this.driver);
    }

    public WebDriver localDriver(){
//        Driver Setup
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver();
    }

    public WebDriver remoteDriver() throws MalformedURLException {
//        Driver Setup
        Capabilities capabilities = new ChromeOptions();
        if (Constants.FIREFOX.equalsIgnoreCase(Config.get(Constants.BROWSER))){
            capabilities = new FirefoxOptions();
        }

        String urlFormat = Config.get(Constants.GRID_URL_FORMAT);
        String hubHost = Config.get(Constants.GRID_HUB_HOST);
        String url = String.format(urlFormat,hubHost);
        log.info("Grid URL: {}",url);
        return new RemoteWebDriver(new URL(url), capabilities);
    }

    @AfterTest
    public void closeDriver(){
        this.driver.quit();
    }

}
