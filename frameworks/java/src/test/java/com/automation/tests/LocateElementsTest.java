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

@Epic("Locate Elements")
@Feature("Locating Elements")
public class LocateElementsTest extends BaseTest {

    private PracticeAppPage practiceAppPage;

    @Override
    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        super.setUp();
        practiceAppPage = new PracticeAppPage();
    }

    @Test(priority = 1, description = "Verify countries list element is found")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Find List Element")
    @Description("Locate the countries list element and verify it is displayed")
    public void testCountriesListIsDisplayed() {
        logger.info("Locating countries list element");
        Assert.assertTrue(practiceAppPage.isCountriesListDisplayed(),
                "Countries list element should be found and displayed");
        logger.info("Countries list element located successfully");
    }

    @Test(priority = 2, description = "Verify country item elements are found")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Find Item Elements")
    @Description("Locate country item elements and verify count is greater than zero")
    public void testCountryItemsFound() {
        logger.info("Locating country item elements");
        int count = practiceAppPage.getCountryItemCount();
        Assert.assertTrue(count > 0, "Should find at least one country item element");
        logger.info("Found {} country item elements", count);
    }

    @Test(priority = 3, description = "Verify country name elements have text")
    @Severity(SeverityLevel.NORMAL)
    @Story("Read Element Text")
    @Description("Locate country name elements and verify they contain text")
    public void testCountryNamesHaveText() {
        logger.info("Locating country name elements");
        var names = practiceAppPage.getCountryNames();
        Assert.assertFalse(names.isEmpty(), "Should find at least one country name");
        for (String name : names) {
            Assert.assertFalse(name.isEmpty(), "Country name should not be empty");
        }
        logger.info("Found country names: {}", names);
    }
}
