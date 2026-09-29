package com.automation.tests;

import com.automation.pages.GestureDemoPage;
import com.automation.pages.HomePage;
import com.automation.pages.ScaleImagePage;
import com.automation.pages.TouchMePage;
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
@Feature("Gesture and Touch")
public class GestureAndTouchRegressionTest extends BaseTest {

    private HomePage homePage;

    @Override
    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        super.setUp();
        homePage = new HomePage();
    }

    @Test(priority = 1, description = "Gesture Demo: navigate and verify gesture screen elements")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Gesture Demo Feature")
    @Description("Navigate to Gesture Demo screen and verify the gesture area element is displayed")
    public void testGestureDemoScreen() {
        logger.info("Testing gesture demo screen");

        homePage.clickGestureDemo();
        GestureDemoPage gestureDemoPage = new GestureDemoPage();

        Assert.assertTrue(gestureDemoPage.isScreenDisplayed(),
                "Gesture Demo screen should be displayed");

        Assert.assertTrue(gestureDemoPage.isGestureAreaDisplayed(),
                "Gesture area should be displayed on the gesture demo screen");

        logger.info("Gesture demo screen elements verified");
    }

    @Test(priority = 2, description = "Touch Me: navigate, tap, and verify result")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Touch Me Feature")
    @Description("Navigate to Touch Me screen, tap the touch area, and verify a result is displayed")
    public void testTouchMeTapAndResult() {
        logger.info("Testing touch me tap and result");

        homePage.clickTouchMe();
        TouchMePage touchMePage = new TouchMePage();

        Assert.assertTrue(touchMePage.isScreenDisplayed(),
                "Touch Me screen should be displayed");

        touchMePage.tapTouchArea();

        Assert.assertTrue(touchMePage.isTouchResultDisplayed(),
                "Touch result should be displayed after tapping");

        String result = touchMePage.getTouchResult();
        Assert.assertNotNull(result, "Touch result text should not be null");
        Assert.assertFalse(result.trim().isEmpty(),
                "Touch result text should not be empty after tap");

        logger.info("Touch me tap verified with result: {}", result);
    }

    @Test(priority = 3, description = "Scale Image: navigate and verify image element")
    @Severity(SeverityLevel.NORMAL)
    @Story("Scale Image Feature")
    @Description("Navigate to Scale Image screen and verify the image element is displayed")
    public void testScaleImageScreen() {
        logger.info("Testing scale image screen");

        homePage.clickScaleImage();
        ScaleImagePage scaleImagePage = new ScaleImagePage();

        Assert.assertTrue(scaleImagePage.isScreenDisplayed(),
                "Scale Image screen should be displayed");

        Assert.assertTrue(scaleImagePage.isImageDisplayed(),
                "Image element should be displayed on the scale image screen");

        logger.info("Scale image screen elements verified");
    }
}
