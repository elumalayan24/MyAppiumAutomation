package com.automation.driver;

import io.appium.java_client.android.AndroidDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DriverManager {

    private static final Logger logger = LoggerFactory.getLogger(DriverManager.class);
    private static final ThreadLocal<AndroidDriver> driverThreadLocal = new ThreadLocal<>();

    private DriverManager() {
    }

    public static AndroidDriver getDriver() {
        AndroidDriver driver = driverThreadLocal.get();
        if (driver == null) {
            throw new IllegalStateException("Driver not initialized. Call initDriver() first.");
        }
        return driver;
    }

    public static void initDriver() {
        if (driverThreadLocal.get() != null) {
            logger.warn("Driver already initialized for this thread. Quitting existing driver.");
            quitDriver();
        }
        AndroidDriver driver = DriverFactory.createDriver();
        driverThreadLocal.set(driver);
        logger.info("Driver initialized for thread: {}", Thread.currentThread().getName());
    }

    public static void quitDriver() {
        AndroidDriver driver = driverThreadLocal.get();
        if (driver != null) {
            try {
                driver.quit();
                logger.info("Driver quit successfully for thread: {}", Thread.currentThread().getName());
            } catch (Exception e) {
                logger.error("Error quitting driver: {}", e.getMessage());
            } finally {
                driverThreadLocal.remove();
            }
        }
    }

    public static boolean isDriverActive() {
        return driverThreadLocal.get() != null;
    }
}
