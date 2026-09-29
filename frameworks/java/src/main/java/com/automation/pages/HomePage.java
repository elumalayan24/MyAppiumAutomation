package com.automation.pages;

import com.automation.utils.AppiumByLocators;
import com.automation.utils.ElementUtils;
import io.qameta.allure.Step;

import java.time.Duration;

public class HomePage extends BasePage {

    private static final String COUNTER_TEXT = "01 COUNTER";
    private static final String ECHO_MESSAGE_TEXT = "02 ECHO MESSAGE";
    private static final String LANGUAGES_TEXT = "03 LANGUAGES";
    private static final String COUNTRIES_LIST_TEXT = "04 COUNTRIES LIST";
    private static final String GESTURE_DEMO_TEXT = "05 GESTURE DEMO";
    private static final String TOUCH_ME_TEXT = "06 TOUCH ME";
    private static final String SCALE_IMAGE_TEXT = "07 SCALE IMAGE";
    private static final String DRAG_AND_DROP_TEXT = "08 DRAG AND DROP";
    private static final String ALERT_DIALOG_TEXT = "09 ALERT DIALOG";
    private static final String WEBVIEW_TEXT = "10 WEBVIEW";
    private static final String ANIMATION_DEMO_TEXT = "11 ANIMATION DEMO";
    private static final String EXPAND_BANK_TEXT = "12 EXPAND BANK";

    private static final String APP_TITLE_TEXT = "The Practice App";

    private static final String[] ALL_BUTTON_TEXTS = {
            COUNTER_TEXT, ECHO_MESSAGE_TEXT, LANGUAGES_TEXT, COUNTRIES_LIST_TEXT,
            GESTURE_DEMO_TEXT, TOUCH_ME_TEXT, SCALE_IMAGE_TEXT, DRAG_AND_DROP_TEXT,
            ALERT_DIALOG_TEXT, WEBVIEW_TEXT, ANIMATION_DEMO_TEXT, EXPAND_BANK_TEXT
    };

    @Step("Check if home screen is displayed")
    public boolean isHomeScreenDisplayed() {
        return isDisplayed(AppiumByLocators.byTextContains("Practice App"));
    }

    @Step("Get app title text")
    public String getAppTitle() {
        return getText(AppiumByLocators.byText(APP_TITLE_TEXT));
    }

    @Step("Check if footer is displayed")
    public boolean isFooterDisplayed() {
        return isDisplayed(AppiumByLocators.byTextContains("Expand Testing"));
    }

    @Step("Check if feature button is displayed: {buttonText}")
    public boolean isFeatureButtonDisplayed(String buttonText) {
        try {
            ElementUtils.scrollToText(buttonText);
            return true;
        } catch (Exception e) {
            logger.debug("Button '{}' not found", buttonText);
            return false;
        }
    }

    @Step("Check if all 12 feature buttons are displayed")
    public boolean areAllFeatureButtonsDisplayed() {
        for (String buttonText : ALL_BUTTON_TEXTS) {
            if (!isFeatureButtonDisplayed(buttonText)) {
                logger.warn("Feature button '{}' not found", buttonText);
                return false;
            }
        }
        return true;
    }

    @Step("Click Counter button")
    public void clickCounter() {
        click(AppiumByLocators.byText(COUNTER_TEXT));
        logger.info("Clicked Counter button");
    }

    @Step("Click Echo Message button")
    public void clickEchoMessage() {
        click(AppiumByLocators.byText(ECHO_MESSAGE_TEXT));
        logger.info("Clicked Echo Message button");
    }

    @Step("Click Languages button")
    public void clickLanguages() {
        click(AppiumByLocators.byText(LANGUAGES_TEXT));
        logger.info("Clicked Languages button");
    }

    @Step("Click Countries List button")
    public void clickCountriesList() {
        click(AppiumByLocators.byText(COUNTRIES_LIST_TEXT));
        logger.info("Clicked Countries List button");
    }

    @Step("Click Gesture Demo button")
    public void clickGestureDemo() {
        click(AppiumByLocators.byText(GESTURE_DEMO_TEXT));
        logger.info("Clicked Gesture Demo button");
    }

    @Step("Click Touch Me button")
    public void clickTouchMe() {
        click(AppiumByLocators.byText(TOUCH_ME_TEXT));
        logger.info("Clicked Touch Me button");
    }

    @Step("Click Scale Image button")
    public void clickScaleImage() {
        click(AppiumByLocators.byText(SCALE_IMAGE_TEXT));
        logger.info("Clicked Scale Image button");
    }

    @Step("Click Drag And Drop button")
    public void clickDragAndDrop() {
        click(AppiumByLocators.byText(DRAG_AND_DROP_TEXT));
        logger.info("Clicked Drag And Drop button");
    }

    @Step("Click Alert Dialog button")
    public void clickAlertDialog() {
        click(AppiumByLocators.byText(ALERT_DIALOG_TEXT));
        logger.info("Clicked Alert Dialog button");
    }

    @Step("Click WebView button")
    public void clickWebView() {
        click(AppiumByLocators.byText(WEBVIEW_TEXT));
        logger.info("Clicked WebView button");
    }

    @Step("Click Animation Demo button")
    public void clickAnimationDemo() {
        click(AppiumByLocators.byText(ANIMATION_DEMO_TEXT));
        logger.info("Clicked Animation Demo button");
    }

    @Step("Click Expand Bank button")
    public void clickExpandBank() {
        click(AppiumByLocators.byText(EXPAND_BANK_TEXT));
        logger.info("Clicked Expand Bank button");
    }

    @Step("Navigate back to home screen")
    public void navigateBack() {
        driver.navigate().back();
        logger.info("Navigated back");
    }

    @Step("Send app to background for {seconds} seconds and resume")
    public void backgroundAndResume(int seconds) {
        driver.runAppInBackground(Duration.ofSeconds(seconds));
        logger.info("App sent to background for {} seconds and resumed", seconds);
    }
}
