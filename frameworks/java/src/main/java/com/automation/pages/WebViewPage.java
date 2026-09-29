package com.automation.pages;

import com.automation.utils.AppiumByLocators;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;

import java.util.Set;

public class WebViewPage extends BasePage {

    @AndroidFindBy(id = "com.expandtesting.practice:id/webview")
    private WebElement webView;

    @AndroidFindBy(id = "com.expandtesting.practice:id/et_url")
    private WebElement urlInput;

    @AndroidFindBy(id = "com.expandtesting.practice:id/btn_load")
    private WebElement loadButton;

    @Step("Check if webview screen is displayed")
    public boolean isScreenDisplayed() {
        try {
            return isDisplayed(webView);
        } catch (Exception e) {
            return isDisplayed(AppiumByLocators.byTextContains("WebView"));
        }
    }

    @Step("Check if webview element is displayed")
    public boolean isWebViewDisplayed() {
        return isDisplayed(webView);
    }

    @Step("Get available contexts")
    public Set<String> getContexts() {
        Set<String> contexts = driver.getContextHandles();
        logger.info("Available contexts: {}", contexts);
        return contexts;
    }

    @Step("Navigate back from webview screen")
    public void navigateBack() {
        driver.navigate().back();
        logger.info("Navigated back from webview screen");
    }
}
