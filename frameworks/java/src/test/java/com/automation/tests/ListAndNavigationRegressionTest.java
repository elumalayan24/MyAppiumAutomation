package com.automation.tests;

import com.automation.pages.HomePage;
import com.automation.pages.PracticeAppPage;
import com.automation.utils.ElementUtils;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Epic("Regression Suite")
@Feature("List and Navigation")
public class ListAndNavigationRegressionTest extends BaseTest {

    private HomePage homePage;

    @Override
    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        super.setUp();
        homePage = new HomePage();
    }

    @Test(priority = 1, description = "Countries List: verify list is displayed with items")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Countries List Display")
    @Description("Navigate to Countries List screen and verify the list is displayed with country items")
    public void testCountriesListDisplayed() {
        logger.info("Testing countries list display");

        homePage.clickCountriesList();
        PracticeAppPage countriesPage = new PracticeAppPage();

        Assert.assertTrue(countriesPage.isCountriesListDisplayed(),
                "Countries list should be displayed");

        int count = countriesPage.getCountryItemCount();
        Assert.assertTrue(count > 0,
                "Countries list should contain at least one country item");

        logger.info("Countries list verified with {} visible items", count);
    }

    @Test(priority = 2, description = "Countries List: scroll to a specific country")
    @Severity(SeverityLevel.NORMAL)
    @Story("Countries List Scroll")
    @Description("Navigate to Countries List and scroll to find 'India' using UiScrollable")
    public void testScrollToCountry() {
        logger.info("Testing scroll to specific country");

        homePage.clickCountriesList();
        PracticeAppPage countriesPage = new PracticeAppPage();

        Assert.assertTrue(countriesPage.isCountriesListDisplayed(),
                "Countries list should be displayed");

        WebElement country = ElementUtils.scrollToText("India");
        Assert.assertNotNull(country,
                "Should be able to scroll to and find 'India' in the countries list");
        Assert.assertTrue(country.isDisplayed(),
                "India element should be visible after scrolling");

        logger.info("Successfully scrolled to 'India' in the countries list");
    }

    @Test(priority = 3, description = "Countries List: tap a country and navigate back")
    @Severity(SeverityLevel.NORMAL)
    @Story("Countries List Navigation")
    @Description("Navigate to Countries List, tap a country, and verify back navigation returns to the list")
    public void testTapCountryAndNavigateBack() {
        logger.info("Testing tap country and navigate back");

        homePage.clickCountriesList();
        PracticeAppPage countriesPage = new PracticeAppPage();

        Assert.assertTrue(countriesPage.isCountriesListDisplayed(),
                "Countries list should be displayed before tapping");

        countriesPage.clickFirstCountry();
        logger.info("Tapped first country");

        homePage.navigateBack();

        Assert.assertTrue(countriesPage.isCountriesListDisplayed(),
                "Countries list should be displayed after navigating back");

        logger.info("Successfully navigated back to countries list after tapping a country");
    }
}
