package com.automation.utils;

import com.automation.driver.DriverManager;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ScreenshotUtils {

    private static final Logger logger = LoggerFactory.getLogger(ScreenshotUtils.class);

    private ScreenshotUtils() {
    }

    @Attachment(value = "{screenshotName}", type = "image/png")
    public static byte[] capture(String screenshotName) {
        logger.info("Capturing screenshot: {}", screenshotName);
        return ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.BYTES);
    }

    @Attachment(value = "Failure Screenshot", type = "image/png")
    public static byte[] captureOnFailure() {
        logger.info("Capturing failure screenshot");
        try {
            return ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.BYTES);
        } catch (Exception e) {
            logger.error("Failed to capture screenshot: {}", e.getMessage());
            return new byte[0];
        }
    }
}
