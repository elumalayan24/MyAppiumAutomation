package com.automation.pages;

import com.automation.utils.AppiumByLocators;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;

public class ScaleImagePage extends BasePage {

    @AndroidFindBy(id = "com.expandtesting.practice:id/iv_scale_image")
    private WebElement scaleImage;

    @AndroidFindBy(id = "com.expandtesting.practice:id/tv_scale_value")
    private WebElement scaleValue;

    @Step("Check if scale image screen is displayed")
    public boolean isScreenDisplayed() {
        try {
            return isDisplayed(scaleImage);
        } catch (Exception e) {
            return isDisplayed(AppiumByLocators.byTextContains("Scale"));
        }
    }

    @Step("Check if image element is displayed")
    public boolean isImageDisplayed() {
        return isDisplayed(scaleImage);
    }

    @Step("Get scale value text")
    public String getScaleValue() {
        return getText(scaleValue);
    }

    @Step("Navigate back from scale image screen")
    public void navigateBack() {
        driver.navigate().back();
        logger.info("Navigated back from scale image screen");
    }
}
