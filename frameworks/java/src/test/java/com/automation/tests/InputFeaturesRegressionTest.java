package com.automation.tests;

import com.automation.pages.CounterPage;
import com.automation.pages.EchoMessagePage;
import com.automation.pages.HomePage;
import com.automation.pages.LanguagesPage;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Epic("Regression Suite")
@Feature("Input Features")
public class InputFeaturesRegressionTest extends BaseTest {

    private HomePage homePage;

    @Override
    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        super.setUp();
        homePage = new HomePage();
    }

    @Test(priority = 1, description = "Counter: increment counter and verify count value changes")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Counter Feature")
    @Description("Navigate to Counter screen, click increment, and verify the counter value changes")
    public void testCounterIncrement() {
        logger.info("Testing counter increment functionality");

        homePage.clickCounter();
        CounterPage counterPage = new CounterPage();

        Assert.assertTrue(counterPage.isScreenDisplayed(),
                "Counter screen should be displayed");

        String initialValue = counterPage.getCounterValue();
        logger.info("Initial counter value: {}", initialValue);

        counterPage.clickIncrement();

        String updatedValue = counterPage.getCounterValue();
        logger.info("Updated counter value: {}", updatedValue);

        Assert.assertNotEquals(updatedValue, initialValue,
                "Counter value should change after increment");

        logger.info("Counter increment verified: {} -> {}", initialValue, updatedValue);
    }

    @Test(priority = 2, description = "Echo Message: type message and verify echo")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Echo Message Feature")
    @Description("Navigate to Echo Message screen, type a message, send it, and verify it echoes back")
    public void testEchoMessage() {
        logger.info("Testing echo message functionality");

        homePage.clickEchoMessage();
        EchoMessagePage echoPage = new EchoMessagePage();

        Assert.assertTrue(echoPage.isScreenDisplayed(),
                "Echo Message screen should be displayed");

        String testMessage = "Hello Automation";
        echoPage.typeMessage(testMessage);
        echoPage.clickSend();

        String echoText = echoPage.getEchoText();
        Assert.assertTrue(echoText.contains(testMessage),
                "Echo text should contain the sent message '" + testMessage
                        + "' but found '" + echoText + "'");

        logger.info("Echo message verified: sent '{}', received '{}'", testMessage, echoText);
    }

    @Test(priority = 3, description = "Languages: verify languages list is populated")
    @Severity(SeverityLevel.NORMAL)
    @Story("Languages Feature")
    @Description("Navigate to Languages screen and verify that a list of languages is displayed")
    public void testLanguagesListed() {
        logger.info("Testing languages list functionality");

        homePage.clickLanguages();
        LanguagesPage languagesPage = new LanguagesPage();

        Assert.assertTrue(languagesPage.isScreenDisplayed(),
                "Languages screen should be displayed");

        int languageCount = languagesPage.getLanguageCount();
        Assert.assertTrue(languageCount > 0,
                "Languages list should contain at least one language item");

        logger.info("Languages list verified with {} items", languageCount);
    }
}
