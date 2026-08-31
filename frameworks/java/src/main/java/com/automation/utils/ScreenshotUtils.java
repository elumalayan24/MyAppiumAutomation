package com.automation.utils;

import com.automation.driver.DriverManager;
import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ScreenshotUtils {

    private static final Logger logger = LoggerFactory.getLogger(ScreenshotUtils.class);
    private static final String SCREENSHOT_DIR = "target/screenshots";
    private static final DateTimeFormatter TIMESTAMP_FORMAT = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");

    private ScreenshotUtils() {
    }

    public static byte[] capture(String screenshotName) {
        if (!DriverManager.isDriverActive()) {
            logger.warn("Cannot capture screenshot '{}': driver is not active", screenshotName);
            return new byte[0];
        }

        logger.info("Capturing screenshot: {}", screenshotName);
        byte[] screenshot = ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.BYTES);

        Allure.addAttachment(screenshotName, "image/png", new ByteArrayInputStream(screenshot), ".png");
        saveToFile(screenshot, screenshotName);

        return screenshot;
    }

    public static byte[] captureOnFailure() {
        if (!DriverManager.isDriverActive()) {
            logger.warn("Cannot capture failure screenshot: driver is not active");
            return new byte[0];
        }

        logger.info("Capturing failure screenshot");
        try {
            byte[] screenshot = ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.BYTES);

            Allure.addAttachment("Failure Screenshot", "image/png", new ByteArrayInputStream(screenshot), ".png");
            saveToFile(screenshot, "failure");

            return screenshot;
        } catch (Exception e) {
            logger.error("Failed to capture screenshot: {}", e.getMessage());
            return new byte[0];
        }
    }

    private static void saveToFile(byte[] screenshot, String name) {
        try {
            Path dir = Paths.get(SCREENSHOT_DIR);
            Files.createDirectories(dir);
            String timestamp = LocalDateTime.now().format(TIMESTAMP_FORMAT);
            String sanitizedName = name.replaceAll("[^a-zA-Z0-9_-]", "_");
            Path filePath = dir.resolve(sanitizedName + "_" + timestamp + ".png");
            Files.write(filePath, screenshot);
            logger.info("Screenshot saved to: {}", filePath);
        } catch (IOException e) {
            logger.error("Failed to save screenshot to file: {}", e.getMessage());
        }
    }
}
