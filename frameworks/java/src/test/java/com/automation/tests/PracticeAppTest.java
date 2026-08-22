package com.automation.tests;

import com.automation.pages.PracticeAppPage;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Epic("Practice App")
@Feature("Countries List")
public class PracticeAppTest extends BaseTest {

    private PracticeAppPage practiceAppPage;

    @Override
    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        super.setUp();
        practiceAppPage = new PracticeAppPage();
    }

    @Test(priority = 1, description = "Verify app launches and countries list is displayed")
    @Severity(SeverityLevel.BLOCKER)
    @Story("App Launch")
    @Description("Verify that the Practice App launches successfully and the countries list is visible")
    public void testAppLaunches() {
        logger.info("Verifying app launches with countries list visible");
        Assert.assertTrue(practiceAppPage.isCountriesListDisplayed(),
                "Countries list should be displayed after app launch");
        logger.info("Countries list is displayed");
    }

    @Test(priority = 2, description = "Verify countries list has items")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Countries List")
    @Description("Verify that the countries list contains at least one country item")
    public void testCountriesListHasItems() {
        logger.info("Verifying countries list contains items");
        int count = practiceAppPage.getCountryItemCount();
        Assert.assertTrue(count > 0, "Countries list should have at least one item");
        logger.info("Found {} countries in the list", count);
    }

    @Test(priority = 3, description = "Verify clicking first country item")
    @Severity(SeverityLevel.NORMAL)
    @Story("Country Selection")
    @Description("Verify that clicking on the first country in the list works without errors")
    public void testClickFirstCountry() {
        logger.info("Clicking on first country in the list");
        practiceAppPage.clickFirstCountry();
        logger.info("Successfully clicked first country");
    }
}
