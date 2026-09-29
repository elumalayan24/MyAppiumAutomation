package com.automation.pages;

import com.automation.utils.AppiumByLocators;
import com.automation.utils.GestureUtils;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;

public class GestureDemoPage extends BasePage {

    @AndroidFindBy(id = "com.expandtesting.practice:id/gesture_area")
    private WebElement gestureArea;

    @AndroidFindBy(id = "com.expandtesting.practice:id/tv_gesture_result")
    private WebElement gestureResult;

    @Step("Check if gesture demo screen is displayed")
    public boolean isScreenDisplayed() {
        try {
            return isDisplayed(gestureArea);
        } catch (Exception e) {
            return isDisplayed(AppiumByLocators.byTextContains("Gesture"));
        }
    }

    @Step("Check if gesture area is displayed")
    public boolean isGestureAreaDisplayed() {
        return isDisplayed(gestureArea);
    }

    @Step("Perform swipe up on gesture area")
    public void swipeUp() {
        GestureUtils.swipe(GestureUtils.Direction.UP);
        logger.info("Performed swipe up gesture");
    }

    @Step("Get gesture result text")
    public String getGestureResult() {
        return getText(gestureResult);
    }

    @Step("Navigate back from gesture demo screen")
    public void navigateBack() {
        driver.navigate().back();
        logger.info("Navigated back from gesture demo screen");
    }
}
