package com.automation.pages;

import com.automation.config.ConfigManager;
import com.automation.driver.DriverManager;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public abstract class BasePage {

    protected final Logger logger = LoggerFactory.getLogger(getClass());
    protected final AndroidDriver driver;
    protected final WebDriverWait wait;

    protected BasePage() {
        this.driver = DriverManager.getDriver();
        int timeout = ConfigManager.getInstance().getTimeoutConfig().getExplicit();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(timeout)), this);
    }

    @Step("Click on element")
    protected void click(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
        logger.debug("Clicked on element: {}", element);
    }

    @Step("Type '{text}' into element")
    protected void type(WebElement element, String text) {
        wait.until(ExpectedConditions.visibilityOf(element));
        element.clear();
        element.sendKeys(text);
        logger.debug("Typed '{}' into element: {}", text, element);
    }

    @Step("Get text from element")
    protected String getText(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
        String text = element.getText();
        logger.debug("Got text '{}' from element: {}", text, element);
        return text;
    }

    @Step("Check if element is displayed")
    protected boolean isDisplayed(WebElement element) {
        try {
            return wait.until(ExpectedConditions.visibilityOf(element)).isDisplayed();
        } catch (Exception e) {
            logger.debug("Element not displayed: {}", element);
            return false;
        }
    }

    @Step("Wait for element to be visible")
    protected WebElement waitForVisible(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    @Step("Wait for element to be clickable")
    protected WebElement waitForClickable(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }
}
