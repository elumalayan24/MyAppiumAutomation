package com.automation.pages;

import com.automation.utils.AppiumByLocators;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;

public class ExpandBankPage extends BasePage {

    @AndroidFindBy(id = "com.expandtesting.practice:id/tv_bank_title")
    private WebElement bankTitle;

    @AndroidFindBy(id = "com.expandtesting.practice:id/btn_login")
    private WebElement loginButton;

    @Step("Check if expand bank screen is displayed")
    public boolean isScreenDisplayed() {
        try {
            return isDisplayed(bankTitle);
        } catch (Exception e) {
            return isDisplayed(AppiumByLocators.byTextContains("Bank"));
        }
    }

    @Step("Check if login button is displayed")
    public boolean isLoginButtonDisplayed() {
        return isDisplayed(loginButton);
    }

    @Step("Navigate back from expand bank screen")
    public void navigateBack() {
        driver.navigate().back();
        logger.info("Navigated back from expand bank screen");
    }
}
