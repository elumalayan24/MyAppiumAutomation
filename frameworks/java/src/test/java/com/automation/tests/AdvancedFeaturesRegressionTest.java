package com.automation.tests;

import com.automation.pages.AlertDialogPage;
import com.automation.pages.DragAndDropPage;
import com.automation.pages.HomePage;
import com.automation.pages.WebViewPage;
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
@Feature("Advanced Features")
public class AdvancedFeaturesRegressionTest extends BaseTest {

    private HomePage homePage;

    @Override
    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        super.setUp();
        homePage = new HomePage();
    }

    @Test(priority = 1, description = "Drag and Drop: navigate and verify drag and drop elements")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Drag and Drop Feature")
    @Description("Navigate to Drag and Drop screen and verify draggable and drop target elements are displayed")
    public void testDragAndDropScreen() {
        logger.info("Testing drag and drop screen");

        homePage.clickDragAndDrop();
        DragAndDropPage dragAndDropPage = new DragAndDropPage();

        Assert.assertTrue(dragAndDropPage.isScreenDisplayed(),
                "Drag and Drop screen should be displayed");

        Assert.assertTrue(dragAndDropPage.isDraggableDisplayed(),
                "Draggable element should be displayed");

        Assert.assertTrue(dragAndDropPage.isDropTargetDisplayed(),
                "Drop target element should be displayed");

        logger.info("Drag and drop screen elements verified");
    }

    @Test(priority = 2, description = "Alert Dialog: navigate and trigger/verify dialog")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Alert Dialog Feature")
    @Description("Navigate to Alert Dialog screen, trigger an alert, and verify the dialog is displayed and dismissible")
    public void testAlertDialogTrigger() {
        logger.info("Testing alert dialog trigger");

        homePage.clickAlertDialog();
        AlertDialogPage alertDialogPage = new AlertDialogPage();

        Assert.assertTrue(alertDialogPage.isScreenDisplayed(),
                "Alert Dialog screen should be displayed");

        alertDialogPage.clickShowAlert();

        Assert.assertTrue(alertDialogPage.isAlertDisplayed(),
                "Alert dialog should be displayed after clicking show alert");

        alertDialogPage.clickAlertOk();

        logger.info("Alert dialog trigger and dismiss verified");
    }

    @Test(priority = 3, description = "WebView: navigate and verify webview loads")
    @Severity(SeverityLevel.NORMAL)
    @Story("WebView Feature")
    @Description("Navigate to WebView screen and verify the webview element is displayed")
    public void testWebViewLoads() {
        logger.info("Testing webview screen");

        homePage.clickWebView();
        WebViewPage webViewPage = new WebViewPage();

        Assert.assertTrue(webViewPage.isScreenDisplayed(),
                "WebView screen should be displayed");

        Assert.assertTrue(webViewPage.isWebViewDisplayed(),
                "WebView element should be displayed");

        logger.info("WebView screen verified");
    }
}
