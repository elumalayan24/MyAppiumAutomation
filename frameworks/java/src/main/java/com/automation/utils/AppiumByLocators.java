package com.automation.utils;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;

/**
 * Convenience wrappers around AppiumBy.androidUIAutomator for common UiSelector strategies.
 */
public final class AppiumByLocators {

    private AppiumByLocators() {
    }

    public static By byText(String text) {
        return AppiumBy.androidUIAutomator("new UiSelector().text(\"" + text + "\")");
    }

    public static By byTextContains(String text) {
        return AppiumBy.androidUIAutomator("new UiSelector().textContains(\"" + text + "\")");
    }

    public static By byTextStartsWith(String text) {
        return AppiumBy.androidUIAutomator("new UiSelector().textStartsWith(\"" + text + "\")");
    }

    public static By byResourceId(String resourceId) {
        return AppiumBy.androidUIAutomator("new UiSelector().resourceId(\"" + resourceId + "\")");
    }

    public static By byContentDesc(String description) {
        return AppiumBy.androidUIAutomator("new UiSelector().description(\"" + description + "\")");
    }

    public static By byContentDescContains(String description) {
        return AppiumBy.androidUIAutomator("new UiSelector().descriptionContains(\"" + description + "\")");
    }

    public static By byClassName(String className) {
        return AppiumBy.androidUIAutomator("new UiSelector().className(\"" + className + "\")");
    }

    public static By byIndex(String className, int index) {
        return AppiumBy.androidUIAutomator(
                "new UiSelector().className(\"" + className + "\").index(" + index + ")");
    }
}
