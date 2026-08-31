package com.automation.tests;

import com.automation.driver.AppiumServerManager;
import com.automation.driver.DriverManager;
import com.automation.listeners.TestListener;
import com.automation.utils.ScreenshotUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

@Listeners(TestListener.class)
public abstract class BaseTest {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @BeforeSuite(alwaysRun = true)
    public void startAppiumServer() {
        AppiumServerManager.startServer();
    }

    @AfterSuite(alwaysRun = true)
    public void stopAppiumServer() {
        AppiumServerManager.stopServer();
    }

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        logger.info("Setting up driver for test");
        DriverManager.initDriver();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            logger.info("Test failed, capturing screenshot before driver cleanup");
            ScreenshotUtils.capture("FAIL_" + result.getMethod().getMethodName());
        }
        logger.info("Tearing down driver after test");
        DriverManager.quitDriver();
    }
}
