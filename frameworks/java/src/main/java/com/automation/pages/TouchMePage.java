package com.automation.pages;

import com.automation.utils.AppiumByLocators;
import com.automation.utils.GestureUtils;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;

public class TouchMePage extends BasePage {

    @AndroidFindBy(id = "com.expandtesting.practice:id/touch_area")
    private WebElement touchArea;

    @AndroidFindBy(id = "com.expandtesting.practice:id/tv_touch_result")
    private WebElement touchResult;

    @Step("Check if touch me screen is displayed")
    public boolean isScreenDisplayed() {
        try {
            return isDisplayed(touchArea);
        } catch (Exception e) {
            return isDisplayed(AppiumByLocators.byTextContains("Touch"));
        }
    }

    @Step("Tap on the touch area")
    public void tapTouchArea() {
        click(touchArea);
        logger.info("Tapped on touch area");
    }

    @Step("Tap on the touch area using gesture")
    public void gestureTapTouchArea() {
        GestureUtils.tap(touchArea);
        logger.info("Gesture tapped on touch area");
    }

    @Step("Get touch result text")
    public String getTouchResult() {
        return getText(touchResult);
    }

    @Step("Check if touch result is displayed")
    public boolean isTouchResultDisplayed() {
        return isDisplayed(touchResult);
    }

    @Step("Navigate back from touch me screen")
    public void navigateBack() {
        driver.navigate().back();
        logger.info("Navigated back from touch me screen");
    }
}
