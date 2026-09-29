package com.automation.pages;

import com.automation.utils.AppiumByLocators;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

public class LanguagesPage extends BasePage {

    @AndroidFindBy(id = "com.expandtesting.practice:id/lv_languages")
    private WebElement languagesList;

    @AndroidFindBy(id = "com.expandtesting.practice:id/tv_language")
    private List<WebElement> languageItems;

    @Step("Check if languages screen is displayed")
    public boolean isScreenDisplayed() {
        try {
            return isDisplayed(languagesList);
        } catch (Exception e) {
            return isDisplayed(AppiumByLocators.byTextContains("Language"));
        }
    }

    @Step("Get count of language items")
    public int getLanguageCount() {
        waitForVisible(languagesList);
        return languageItems.size();
    }

    @Step("Get list of language names")
    public List<String> getLanguageNames() {
        waitForVisible(languagesList);
        return languageItems.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    @Step("Navigate back from languages screen")
    public void navigateBack() {
        driver.navigate().back();
        logger.info("Navigated back from languages screen");
    }
}
