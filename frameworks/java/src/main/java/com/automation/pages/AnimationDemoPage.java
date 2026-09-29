package com.automation.pages;

import com.automation.utils.AppiumByLocators;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;

public class AnimationDemoPage extends BasePage {

    @AndroidFindBy(id = "com.expandtesting.practice:id/btn_animate")
    private WebElement animateButton;

    @AndroidFindBy(id = "com.expandtesting.practice:id/animation_target")
    private WebElement animationTarget;

    @Step("Check if animation demo screen is displayed")
    public boolean isScreenDisplayed() {
        try {
            return isDisplayed(animateButton);
        } catch (Exception e) {
            return isDisplayed(AppiumByLocators.byTextContains("Animation"));
        }
    }

    @Step("Click animate button")
    public void clickAnimate() {
        click(animateButton);
        logger.info("Clicked animate button");
    }

    @Step("Check if animation target is displayed")
    public boolean isAnimationTargetDisplayed() {
        return isDisplayed(animationTarget);
    }

    @Step("Navigate back from animation demo screen")
    public void navigateBack() {
        driver.navigate().back();
        logger.info("Navigated back from animation demo screen");
    }
}
