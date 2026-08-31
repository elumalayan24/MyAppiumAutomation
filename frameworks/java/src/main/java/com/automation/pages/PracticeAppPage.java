package com.automation.pages;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

public class PracticeAppPage extends BasePage {

    @AndroidFindBy(id = "com.expandtesting.practice:id/lv_countries")
    private WebElement countriesList;

    @AndroidFindBy(id = "com.expandtesting.practice:id/ll_parent")
    private List<WebElement> countryItems;

    @AndroidFindBy(id = "com.expandtesting.practice:id/tv_country")
    private List<WebElement> countryNames;

    @AndroidFindBy(id = "com.expandtesting.practice:id/btn_multi_touch")
    private WebElement touchMe;


    @Step("Check if countries list is displayed")
    public boolean isCountriesListDisplayed() {
        return isDisplayed(countriesList);
    }

    @Step("Get count of country items")
    public int getCountryItemCount() {
        waitForVisible(countriesList);
        return countryItems.size();
    }

    @Step("Get list of country names")
    public List<String> getCountryNames() {
        waitForVisible(countriesList);
        return countryNames.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    @Step("Click on country at index {index}")
    public void clickCountryAtIndex(int index) {
        waitForVisible(countriesList);
        if (index < 0 || index >= countryItems.size()) {
            throw new IndexOutOfBoundsException(
                    "Country index " + index + " out of range. Available: " + countryItems.size());
        }
        click(countryItems.get(index));
        logger.info("Clicked on country at index {}", index);
    }

    @Step("Click on first country")
    public void clickFirstCountry() {
        clickCountryAtIndex(0);
    }

    @Step("Click TouchMe")
    public void clickTouchMe() {
        click(touchMe);
    }
}