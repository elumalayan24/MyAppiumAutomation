package com.automation.pages;

import com.automation.utils.AppiumByLocators;
import com.automation.utils.GestureUtils;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;

public class DragAndDropPage extends BasePage {

    @AndroidFindBy(id = "com.expandtesting.practice:id/draggable")
    private WebElement draggableElement;

    @AndroidFindBy(id = "com.expandtesting.practice:id/drop_target")
    private WebElement dropTarget;

    @AndroidFindBy(id = "com.expandtesting.practice:id/tv_drag_result")
    private WebElement dragResult;

    @Step("Check if drag and drop screen is displayed")
    public boolean isScreenDisplayed() {
        try {
            return isDisplayed(draggableElement);
        } catch (Exception e) {
            return isDisplayed(AppiumByLocators.byTextContains("Drag"));
        }
    }

    @Step("Check if draggable element is displayed")
    public boolean isDraggableDisplayed() {
        return isDisplayed(draggableElement);
    }

    @Step("Check if drop target is displayed")
    public boolean isDropTargetDisplayed() {
        return isDisplayed(dropTarget);
    }

    @Step("Perform drag and drop")
    public void performDragAndDrop() {
        GestureUtils.dragAndDrop(draggableElement, dropTarget);
        logger.info("Performed drag and drop");
    }

    @Step("Get drag result text")
    public String getDragResult() {
        return getText(dragResult);
    }

    @Step("Navigate back from drag and drop screen")
    public void navigateBack() {
        driver.navigate().back();
        logger.info("Navigated back from drag and drop screen");
    }
}
