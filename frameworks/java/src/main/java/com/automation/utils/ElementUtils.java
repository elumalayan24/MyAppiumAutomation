package com.automation.utils;

import com.automation.config.ConfigManager;
import com.automation.driver.DriverManager;
import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class ElementUtils {

    private static final Logger logger = LoggerFactory.getLogger(ElementUtils.class);

    private ElementUtils() {
    }

    // ──────────────────────────────────────────────
    // Click actions
    // ──────────────────────────────────────────────

    @Step("Click on element")
    public static void click(WebElement element) {
        WaitUtils.waitForClickable(element).click();
        logger.debug("Clicked on element: {}", element);
    }

    @Step("Click on element located by: {locator}")
    public static void click(By locator) {
        WaitUtils.waitForClickable(locator).click();
        logger.debug("Clicked on element: {}", locator);
    }

    @Step("Click element if displayed")
    public static boolean clickIfDisplayed(WebElement element) {
        if (isDisplayed(element)) {
            element.click();
            logger.debug("Clicked displayed element: {}", element);
            return true;
        }
        logger.debug("Element not displayed, skip click: {}", element);
        return false;
    }

    @Step("Click element if displayed: {locator}")
    public static boolean clickIfDisplayed(By locator) {
        if (isDisplayed(locator)) {
            getDriver().findElement(locator).click();
            logger.debug("Clicked displayed element: {}", locator);
            return true;
        }
        logger.debug("Element not displayed, skip click: {}", locator);
        return false;
    }

    // ──────────────────────────────────────────────
    // Type / input actions
    // ──────────────────────────────────────────────

    @Step("Type '{text}' into element")
    public static void type(WebElement element, String text) {
        WaitUtils.waitForVisible(element);
        element.clear();
        element.sendKeys(text);
        logger.debug("Typed '{}' into element: {}", text, element);
    }

    @Step("Type '{text}' into element located by: {locator}")
    public static void type(By locator, String text) {
        WebElement element = WaitUtils.waitForVisible(locator);
        element.clear();
        element.sendKeys(text);
        logger.debug("Typed '{}' into element: {}", text, locator);
    }

    @Step("Send keys '{text}' without clearing")
    public static void sendKeys(WebElement element, String text) {
        WaitUtils.waitForVisible(element);
        element.sendKeys(text);
        logger.debug("Sent keys '{}' to element: {}", text, element);
    }

    @Step("Clear element")
    public static void clear(WebElement element) {
        WaitUtils.waitForVisible(element);
        element.clear();
        logger.debug("Cleared element: {}", element);
    }

    @Step("Clear element located by: {locator}")
    public static void clear(By locator) {
        WebElement element = WaitUtils.waitForVisible(locator);
        element.clear();
        logger.debug("Cleared element: {}", locator);
    }

    // ──────────────────────────────────────────────
    // Read actions
    // ──────────────────────────────────────────────

    @Step("Get text from element")
    public static String getText(WebElement element) {
        WaitUtils.waitForVisible(element);
        String text = element.getText();
        logger.debug("Got text '{}' from element: {}", text, element);
        return text;
    }

    @Step("Get text from element located by: {locator}")
    public static String getText(By locator) {
        WebElement element = WaitUtils.waitForVisible(locator);
        String text = element.getText();
        logger.debug("Got text '{}' from element: {}", text, locator);
        return text;
    }

    @Step("Get attribute '{attribute}' from element")
    public static String getAttribute(WebElement element, String attribute) {
        WaitUtils.waitForVisible(element);
        String value = element.getAttribute(attribute);
        logger.debug("Got attribute '{}={}' from element: {}", attribute, value, element);
        return value;
    }

    @Step("Get attribute '{attribute}' from element located by: {locator}")
    public static String getAttribute(By locator, String attribute) {
        WebElement element = WaitUtils.waitForVisible(locator);
        String value = element.getAttribute(attribute);
        logger.debug("Got attribute '{}={}' from element: {}", attribute, value, locator);
        return value;
    }

    @Step("Get content description from element")
    public static String getContentDesc(WebElement element) {
        return getAttribute(element, "content-desc");
    }

    @Step("Get texts from all elements matching: {locator}")
    public static List<String> getTexts(By locator) {
        List<WebElement> elements = getElements(locator);
        List<String> texts = elements.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
        logger.debug("Got {} texts from elements: {}", texts.size(), locator);
        return texts;
    }

    // ──────────────────────────────────────────────
    // State checks
    // ──────────────────────────────────────────────

    @Step("Check if element is displayed")
    public static boolean isDisplayed(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (Exception e) {
            logger.debug("Element not displayed: {}", element);
            return false;
        }
    }

    @Step("Check if element is displayed: {locator}")
    public static boolean isDisplayed(By locator) {
        try {
            return getDriver().findElement(locator).isDisplayed();
        } catch (Exception e) {
            logger.debug("Element not displayed: {}", locator);
            return false;
        }
    }

    @Step("Check if element is displayed with wait")
    public static boolean isDisplayedWithWait(WebElement element) {
        try {
            WaitUtils.waitForVisible(element);
            return true;
        } catch (Exception e) {
            logger.debug("Element not visible after wait: {}", element);
            return false;
        }
    }

    @Step("Check if element is displayed with wait: {locator}")
    public static boolean isDisplayedWithWait(By locator) {
        try {
            WaitUtils.waitForVisible(locator);
            return true;
        } catch (Exception e) {
            logger.debug("Element not visible after wait: {}", locator);
            return false;
        }
    }

    @Step("Check if element is enabled")
    public static boolean isEnabled(WebElement element) {
        try {
            WaitUtils.waitForVisible(element);
            return element.isEnabled();
        } catch (Exception e) {
            logger.debug("Element not enabled: {}", element);
            return false;
        }
    }

    @Step("Check if element is enabled: {locator}")
    public static boolean isEnabled(By locator) {
        try {
            WebElement element = WaitUtils.waitForVisible(locator);
            return element.isEnabled();
        } catch (Exception e) {
            logger.debug("Element not enabled: {}", locator);
            return false;
        }
    }

    @Step("Check if element is selected/checked")
    public static boolean isSelected(WebElement element) {
        try {
            WaitUtils.waitForVisible(element);
            return element.isSelected();
        } catch (Exception e) {
            logger.debug("Element not selected: {}", element);
            return false;
        }
    }

    @Step("Check if element is selected/checked: {locator}")
    public static boolean isSelected(By locator) {
        try {
            WebElement element = WaitUtils.waitForVisible(locator);
            return element.isSelected();
        } catch (Exception e) {
            logger.debug("Element not selected: {}", locator);
            return false;
        }
    }

    @Step("Check if checkbox/toggle is checked")
    public static boolean isChecked(WebElement element) {
        String checked = element.getAttribute("checked");
        return "true".equalsIgnoreCase(checked);
    }

    // ──────────────────────────────────────────────
    // Checkbox / toggle actions
    // ──────────────────────────────────────────────

    @Step("Set checkbox to checked")
    public static void check(WebElement element) {
        if (!isChecked(element)) {
            click(element);
            logger.debug("Checked element: {}", element);
        }
    }

    @Step("Set checkbox to unchecked")
    public static void uncheck(WebElement element) {
        if (isChecked(element)) {
            click(element);
            logger.debug("Unchecked element: {}", element);
        }
    }

    // ──────────────────────────────────────────────
    // Element lookup
    // ──────────────────────────────────────────────

    @Step("Find element by: {locator}")
    public static WebElement getElement(By locator) {
        return WaitUtils.waitForVisible(locator);
    }

    @Step("Find all elements matching: {locator}")
    public static List<WebElement> getElements(By locator) {
        int timeout = ConfigManager.getInstance().getTimeoutConfig().getExplicit();
        new WebDriverWait(getDriver(), Duration.ofSeconds(timeout))
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
        return getDriver().findElements(locator);
    }

    @Step("Get count of elements matching: {locator}")
    public static int getElementCount(By locator) {
        try {
            return getDriver().findElements(locator).size();
        } catch (Exception e) {
            logger.debug("No elements found for: {}", locator);
            return 0;
        }
    }

    @Step("Check if element exists in DOM: {locator}")
    public static boolean isPresent(By locator) {
        return getElementCount(locator) > 0;
    }

    // ──────────────────────────────────────────────
    // Scroll-to-element helpers (UiScrollable)
    // ──────────────────────────────────────────────

    @Step("Scroll to element with text: {visibleText}")
    public static WebElement scrollToText(String visibleText) {
        String uiSelector = "new UiScrollable(new UiSelector().scrollable(true))"
                + ".scrollIntoView(new UiSelector().textContains(\"" + visibleText + "\"))";
        WebElement element = getDriver().findElement(
                io.appium.java_client.AppiumBy.androidUIAutomator(uiSelector));
        logger.debug("Scrolled to text: {}", visibleText);
        return element;
    }

    @Step("Scroll to element with content-desc: {contentDesc}")
    public static WebElement scrollToContentDesc(String contentDesc) {
        String uiSelector = "new UiScrollable(new UiSelector().scrollable(true))"
                + ".scrollIntoView(new UiSelector().descriptionContains(\"" + contentDesc + "\"))";
        WebElement element = getDriver().findElement(
                io.appium.java_client.AppiumBy.androidUIAutomator(uiSelector));
        logger.debug("Scrolled to content-desc: {}", contentDesc);
        return element;
    }

    // ──────────────────────────────────────────────
    // Toast / text presence
    // ──────────────────────────────────────────────

    @Step("Wait for toast message: {text}")
    public static boolean waitForToast(String text) {
        try {
            By toastLocator = By.xpath("//android.widget.Toast");
            int timeout = ConfigManager.getInstance().getTimeoutConfig().getExplicit();
            WebElement toast = new WebDriverWait(getDriver(), Duration.ofSeconds(timeout))
                    .until(ExpectedConditions.presenceOfElementLocated(toastLocator));
            boolean found = toast.getText().contains(text);
            logger.debug("Toast with text '{}' found: {}", text, found);
            return found;
        } catch (Exception e) {
            logger.debug("Toast with text '{}' not found", text);
            return false;
        }
    }

    @Step("Wait until element text equals: {expectedText}")
    public static boolean waitForTextToBe(WebElement element, String expectedText) {
        return WaitUtils.waitForTextPresent(element, expectedText);
    }

    // ──────────────────────────────────────────────
    // Internals
    // ──────────────────────────────────────────────

    private static AndroidDriver getDriver() {
        return DriverManager.getDriver();
    }
}
