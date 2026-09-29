package com.automation.tests;

import com.automation.pages.HomePage;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Epic("Regression Suite")
@Feature("Home Screen")
public class HomeScreenRegressionTest extends BaseTest {

    private HomePage homePage;

    @Override
    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        super.setUp();
        homePage = new HomePage();
    }

    @Test(priority = 1, description = "Verify home screen title and all 12 feature buttons are displayed")
    @Severity(SeverityLevel.BLOCKER)
    @Story("Home Screen Layout")
    @Description("Verify that the home screen shows the app title 'The Practice App' and all 12 feature buttons")
    public void testHomeScreenTitleAndButtons() {
        logger.info("Verifying home screen title and feature buttons");

        Assert.assertTrue(homePage.isHomeScreenDisplayed(),
                "Home screen should be displayed with app title");

        String title = homePage.getAppTitle();
        Assert.assertEquals(title, "The Practice App",
                "App title should be 'The Practice App'");

        Assert.assertTrue(homePage.areAllFeatureButtonsDisplayed(),
                "All 12 feature buttons should be displayed on the home screen");

        logger.info("Home screen title and all 12 feature buttons verified");
    }

    @Test(priority = 2, description = "Verify feature buttons navigate to their screens and back")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Home Screen Navigation")
    @Description("Verify that clicking feature buttons navigates away from home and back button returns to home")
    public void testFeatureButtonNavigation() {
        logger.info("Verifying feature button navigation round-trip");

        // Test Counter (first button)
        homePage.clickCounter();
        Assert.assertFalse(homePage.isHomeScreenDisplayed(),
                "Home screen should not be displayed after clicking Counter");
        homePage.navigateBack();
        Assert.assertTrue(homePage.isHomeScreenDisplayed(),
                "Home screen should be displayed after navigating back from Counter");

        // Test Touch Me (middle button)
        homePage.clickTouchMe();
        Assert.assertFalse(homePage.isHomeScreenDisplayed(),
                "Home screen should not be displayed after clicking Touch Me");
        homePage.navigateBack();
        Assert.assertTrue(homePage.isHomeScreenDisplayed(),
                "Home screen should be displayed after navigating back from Touch Me");

        // Test Expand Bank (last button)
        homePage.clickExpandBank();
        Assert.assertFalse(homePage.isHomeScreenDisplayed(),
                "Home screen should not be displayed after clicking Expand Bank");
        homePage.navigateBack();
        Assert.assertTrue(homePage.isHomeScreenDisplayed(),
                "Home screen should be displayed after navigating back from Expand Bank");

        logger.info("Feature button navigation round-trip verified for Counter, Touch Me, Expand Bank");
    }

    @Test(priority = 3, description = "Verify app background/resume preserves home screen state")
    @Severity(SeverityLevel.NORMAL)
    @Story("App Lifecycle")
    @Description("Verify that sending the app to background and resuming preserves the home screen state")
    public void testBackgroundResumePreservesHomeScreen() {
        logger.info("Verifying background/resume preserves home screen");

        Assert.assertTrue(homePage.isHomeScreenDisplayed(),
                "Home screen should be displayed before backgrounding");

        homePage.backgroundAndResume(3);

        Assert.assertTrue(homePage.isHomeScreenDisplayed(),
                "Home screen should still be displayed after resuming from background");

        String title = homePage.getAppTitle();
        Assert.assertEquals(title, "The Practice App",
                "App title should still be 'The Practice App' after resume");

        logger.info("Home screen state preserved after background/resume");
    }
}
