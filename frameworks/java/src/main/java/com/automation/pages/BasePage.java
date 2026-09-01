package com.automation.pages;

import com.automation.config.ConfigManager;
import com.automation.driver.DriverManager;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.List;

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

    // --- By locator overloads ---

    @Step("Find element by locator")
    protected WebElement findElement(By locator) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    @Step("Find elements by locator")
    protected List<WebElement> findElements(By locator) {
        return driver.findElements(locator);
    }

    @Step("Click on element by locator")
    protected void click(By locator) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        element.click();
        logger.debug("Clicked on element located by: {}", locator);
    }

    @Step("Type '{text}' into element by locator")
    protected void type(By locator, String text) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        element.clear();
        element.sendKeys(text);
        logger.debug("Typed '{}' into element located by: {}", text, locator);
    }

    @Step("Get text from element by locator")
    protected String getText(By locator) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        String text = element.getText();
        logger.debug("Got text '{}' from element located by: {}", text, locator);
        return text;
    }

    @Step("Check if element is displayed by locator")
    protected boolean isDisplayed(By locator) {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).isDisplayed();
        } catch (Exception e) {
            logger.debug("Element not displayed: {}", locator);
            return false;
        }
    }

    @Step("Wait for element to be visible by locator")
    protected WebElement waitForVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    @Step("Wait for element to be clickable by locator")
    protected WebElement waitForClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }
}
