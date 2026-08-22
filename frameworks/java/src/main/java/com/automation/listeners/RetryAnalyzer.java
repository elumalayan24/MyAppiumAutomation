package com.automation.listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {

    private static final Logger logger = LoggerFactory.getLogger(RetryAnalyzer.class);
    private static final int MAX_RETRY_COUNT = 2;
    private int retryCount = 0;

    @Override
    public boolean retry(ITestResult result) {
        if (retryCount < MAX_RETRY_COUNT) {
            retryCount++;
            logger.info("Retrying test '{}' - attempt {}/{}", result.getMethod().getMethodName(), retryCount, MAX_RETRY_COUNT);
            return true;
        }
        return false;
    }
}
