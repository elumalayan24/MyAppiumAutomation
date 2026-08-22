package com.automation.utils;

import com.automation.config.ConfigManager;
import com.automation.driver.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.NoSuchElementException;

public class WaitUtils {

    private static final Logger logger = LoggerFactory.getLogger(WaitUtils.class);

    private WaitUtils() {
    }

    public static WebElement waitForVisible(WebElement element) {
        int timeout = ConfigManager.getInstance().getTimeoutConfig().getExplicit();
        return new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(timeout))
                .until(ExpectedConditions.visibilityOf(element));
    }

    public static WebElement waitForVisible(By locator) {
        int timeout = ConfigManager.getInstance().getTimeoutConfig().getExplicit();
        return new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(timeout))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static WebElement waitForClickable(WebElement element) {
        int timeout = ConfigManager.getInstance().getTimeoutConfig().getExplicit();
        return new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(timeout))
                .until(ExpectedConditions.elementToBeClickable(element));
    }

    public static WebElement waitForClickable(By locator) {
        int timeout = ConfigManager.getInstance().getTimeoutConfig().getExplicit();
        return new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(timeout))
                .until(ExpectedConditions.elementToBeClickable(locator));
    }

    public static boolean waitForInvisible(WebElement element) {
        int timeout = ConfigManager.getInstance().getTimeoutConfig().getExplicit();
        return new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(timeout))
                .until(ExpectedConditions.invisibilityOf(element));
    }

    public static WebElement fluentWait(By locator, int timeoutSeconds, int pollingMs) {
        return new FluentWait<>(DriverManager.getDriver())
                .withTimeout(Duration.ofSeconds(timeoutSeconds))
                .pollingEvery(Duration.ofMillis(pollingMs))
                .ignoring(NoSuchElementException.class)
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static boolean waitForTextPresent(WebElement element, String text) {
        int timeout = ConfigManager.getInstance().getTimeoutConfig().getExplicit();
        return new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(timeout))
                .until(ExpectedConditions.textToBePresentInElement(element, text));
    }
}
