package com.automation.tests;

import com.automation.pages.PracticeAppPage;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Epic("Practice App")
@Feature("Touch Me")
public class TouchMeTest extends BaseTest {

    private PracticeAppPage practiceAppPage;

    @Override
    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        super.setUp();
        practiceAppPage = new PracticeAppPage();
    }

    @Test(description = "Verify clicking Touch Me button")
    @Severity(SeverityLevel.NORMAL)
    @Story("Touch Me Button")
    @Description("Click the Touch Me button and verify no errors")
    public void testClickTouchMe() {
        logger.info("Clicking Touch Me button");
        practiceAppPage.clickTouchMe();
        logger.info("Touch Me button clicked successfully");
    }
}
