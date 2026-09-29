package com.automation.pages;

import com.automation.utils.AppiumByLocators;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;

public class EchoMessagePage extends BasePage {

    @AndroidFindBy(id = "com.expandtesting.practice:id/et_message")
    private WebElement messageInput;

    @AndroidFindBy(id = "com.expandtesting.practice:id/btn_send")
    private WebElement sendButton;

    @AndroidFindBy(id = "com.expandtesting.practice:id/tv_echo")
    private WebElement echoText;

    @Step("Check if echo message screen is displayed")
    public boolean isScreenDisplayed() {
        try {
            return isDisplayed(messageInput);
        } catch (Exception e) {
            return isDisplayed(AppiumByLocators.byTextContains("Echo"));
        }
    }

    @Step("Type message: {message}")
    public void typeMessage(String message) {
        type(messageInput, message);
        logger.info("Typed message: {}", message);
    }

    @Step("Click send button")
    public void clickSend() {
        click(sendButton);
        logger.info("Clicked send button");
    }

    @Step("Get echo text")
    public String getEchoText() {
        return getText(echoText);
    }

    @Step("Navigate back from echo message screen")
    public void navigateBack() {
        driver.navigate().back();
        logger.info("Navigated back from echo message screen");
    }
}
