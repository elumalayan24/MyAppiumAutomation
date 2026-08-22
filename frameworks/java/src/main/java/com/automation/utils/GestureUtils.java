package com.automation.utils;

import com.automation.driver.DriverManager;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.Collections;

public class GestureUtils {

    private static final Logger logger = LoggerFactory.getLogger(GestureUtils.class);
    private static final PointerInput FINGER = new PointerInput(PointerInput.Kind.TOUCH, "finger");

    private GestureUtils() {
    }

    public static void tap(int x, int y) {
        Sequence tap = new Sequence(FINGER, 1)
                .addAction(FINGER.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), x, y))
                .addAction(FINGER.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(new Pause(FINGER, Duration.ofMillis(100)))
                .addAction(FINGER.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        DriverManager.getDriver().perform(Collections.singletonList(tap));
        logger.debug("Tapped at coordinates: ({}, {})", x, y);
    }

    public static void tap(WebElement element) {
        Point center = getCenter(element);
        tap(center.getX(), center.getY());
    }

    public static void longPress(WebElement element, Duration duration) {
        Point center = getCenter(element);
        Sequence longPress = new Sequence(FINGER, 1)
                .addAction(FINGER.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), center.getX(), center.getY()))
                .addAction(FINGER.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(new Pause(FINGER, duration))
                .addAction(FINGER.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        DriverManager.getDriver().perform(Collections.singletonList(longPress));
        logger.debug("Long pressed on element for {}ms", duration.toMillis());
    }

    public static void swipe(Direction direction) {
        AndroidDriver driver = DriverManager.getDriver();
        Dimension size = driver.manage().window().getSize();

        int startX, startY, endX, endY;
        switch (direction) {
            case UP -> {
                startX = size.getWidth() / 2;
                startY = (int) (size.getHeight() * 0.7);
                endX = startX;
                endY = (int) (size.getHeight() * 0.3);
            }
            case DOWN -> {
                startX = size.getWidth() / 2;
                startY = (int) (size.getHeight() * 0.3);
                endX = startX;
                endY = (int) (size.getHeight() * 0.7);
            }
            case LEFT -> {
                startX = (int) (size.getWidth() * 0.8);
                startY = size.getHeight() / 2;
                endX = (int) (size.getWidth() * 0.2);
                endY = startY;
            }
            case RIGHT -> {
                startX = (int) (size.getWidth() * 0.2);
                startY = size.getHeight() / 2;
                endX = (int) (size.getWidth() * 0.8);
                endY = startY;
            }
            default -> throw new IllegalArgumentException("Unknown direction: " + direction);
        }

        performSwipe(startX, startY, endX, endY, Duration.ofMillis(800));
        logger.debug("Swiped {}", direction);
    }

    public static void scroll(Direction direction, int times) {
        for (int i = 0; i < times; i++) {
            swipe(direction);
        }
    }

    public static void dragAndDrop(WebElement source, WebElement target) {
        Point sourceCenter = getCenter(source);
        Point targetCenter = getCenter(target);

        Sequence dragAndDrop = new Sequence(FINGER, 1)
                .addAction(FINGER.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), sourceCenter.getX(), sourceCenter.getY()))
                .addAction(FINGER.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(new Pause(FINGER, Duration.ofMillis(500)))
                .addAction(FINGER.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(), targetCenter.getX(), targetCenter.getY()))
                .addAction(FINGER.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        DriverManager.getDriver().perform(Collections.singletonList(dragAndDrop));
        logger.debug("Dragged from ({}, {}) to ({}, {})", sourceCenter.getX(), sourceCenter.getY(), targetCenter.getX(), targetCenter.getY());
    }

    private static void performSwipe(int startX, int startY, int endX, int endY, Duration duration) {
        Sequence swipe = new Sequence(FINGER, 1)
                .addAction(FINGER.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY))
                .addAction(FINGER.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(FINGER.createPointerMove(duration, PointerInput.Origin.viewport(), endX, endY))
                .addAction(FINGER.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        DriverManager.getDriver().perform(Collections.singletonList(swipe));
    }

    private static Point getCenter(WebElement element) {
        Point location = element.getLocation();
        Dimension size = element.getSize();
        return new Point(location.getX() + size.getWidth() / 2, location.getY() + size.getHeight() / 2);
    }

    public enum Direction {
        UP, DOWN, LEFT, RIGHT
    }
}
