package com.automation.tests;

import com.automation.driver.DriverManager;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.Test;

@Epic("Launch Sample App")
@Feature("Launching App")
public class LaunchAppTest extends BaseTest {

    @Test(description = "Verify Practice App launches successfully")
    @Severity(SeverityLevel.BLOCKER)
    @Story("App Launch")
    @Description("Launch the Practice App and verify the driver session is active")
    public void testAppLaunches() {
        logger.info("Verifying Practice App launched successfully");
        Assert.assertNotNull(DriverManager.getDriver(), "Driver should be initialized");
        logger.info("Practice App launched successfully");
    }
}
