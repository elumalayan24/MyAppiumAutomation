package com.automation.pages;

import com.automation.utils.AppiumByLocators;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;

public class AlertDialogPage extends BasePage {

    @AndroidFindBy(id = "com.expandtesting.practice:id/btn_alert_dialog")
    private WebElement showAlertButton;

    private WebElement alertTitle;

    @AndroidFindBy(id = "android:id/message")
    private WebElement alertMessage;

    @AndroidFindBy(id = "android:id/button1")
    private WebElement alertOkButton;

    @AndroidFindBy(id = "android:id/button2")
    private WebElement alertCancelButton;

    @Step("Check if alert dialog screen is displayed")
    public boolean isScreenDisplayed() {
        try {
            return isDisplayed(showAlertButton);
        } catch (Exception e) {
            return isDisplayed(AppiumByLocators.byTextContains("Alert"));
        }
    }

    @Step("Click show alert button")
    public void clickShowAlert() {
        click(showAlertButton);
        logger.info("Clicked show alert button");
    }

    @Step("Check if alert dialog is displayed")
    public boolean isAlertDisplayed() {
        return isDisplayed(alertTitle);
    }

    @Step("Get alert title text")
    public String getAlertTitle() {
        return getText(alertTitle);
    }

    @Step("Get alert message text")
    public String getAlertMessage() {
        return getText(alertMessage);
    }

    @Step("Click OK on alert dialog")
    public void clickAlertOk() {
        click(alertOkButton);
        logger.info("Clicked OK on alert dialog");
    }

    @Step("Click Cancel on alert dialog")
    public void clickAlertCancel() {
        click(alertCancelButton);
        logger.info("Clicked Cancel on alert dialog");
    }

    @Step("Navigate back from alert dialog screen")
    public void navigateBack() {
        driver.navigate().back();
        logger.info("Navigated back from alert dialog screen");
    }
}
