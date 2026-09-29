package com.automation.pages;

import com.automation.utils.AppiumByLocators;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;

public class CounterPage extends BasePage {

    @AndroidFindBy(id = "com.expandtesting.practice:id/tv_counter")
    private WebElement counterValue;

    @AndroidFindBy(id = "com.expandtesting.practice:id/btn_increment")
    private WebElement incrementButton;

    @AndroidFindBy(id = "com.expandtesting.practice:id/btn_decrement")
    private WebElement decrementButton;

    @Step("Check if counter screen is displayed")
    public boolean isScreenDisplayed() {
        try {
            return isDisplayed(counterValue);
        } catch (Exception e) {
            return isDisplayed(AppiumByLocators.byTextContains("Counter"));
        }
    }

    @Step("Get counter value text")
    public String getCounterValue() {
        return getText(counterValue);
    }

    @Step("Click increment button")
    public void clickIncrement() {
        click(incrementButton);
        logger.info("Clicked increment button");
    }

    @Step("Click decrement button")
    public void clickDecrement() {
        click(decrementButton);
        logger.info("Clicked decrement button");
    }

    @Step("Navigate back from counter screen")
    public void navigateBack() {
        driver.navigate().back();
        logger.info("Navigated back from counter screen");
    }
}
