package com.automation.listeners;

import com.automation.utils.ScreenshotUtils;
import io.qameta.allure.Allure;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

    private static final Logger logger = LoggerFactory.getLogger(TestListener.class);

    @Override
    public void onTestStart(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        logger.info("========== STARTING TEST: {} ==========", testName);
        Allure.label("testType", "ui");
        Allure.label("framework", "appium");
        Allure.label("platform", "android");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        logger.info("========== TEST PASSED: {} ==========", result.getMethod().getMethodName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        logger.error("========== TEST FAILED: {} ==========", testName);
        logger.error("Failure reason: {}", result.getThrowable().getMessage());

        try {
            ScreenshotUtils.captureOnFailure();
        } catch (Exception e) {
            logger.error("Could not capture failure screenshot: {}", e.getMessage());
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        logger.warn("========== TEST SKIPPED: {} ==========", result.getMethod().getMethodName());
    }
}
