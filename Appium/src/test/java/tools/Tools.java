package tools;

import com.sun.javafx.scene.traversal.Direction;
import io.appium.java_client.MobileElement;
import io.appium.java_client.MultiTouchAction;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.TapOptions;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Rectangle;

import java.awt.*;
import java.time.Duration;

public class Tools {

    public static void regularTap(MobileElement mobileElement, AndroidDriver<AndroidElement> driver){

        MobileElement elem = mobileElement;
        TouchAction action = new TouchAction(driver);

        action.tap(new TapOptions()
                .withElement(ElementOption.element(elem)))
                .perform();

    }
    public static void regularTapWithDuration(MobileElement elem, AndroidDriver<AndroidElement> driver, int durationOfSeconds){

        TouchAction action = new TouchAction(driver);

        action.tap(new TapOptions()
                .withElement(ElementOption.element(elem)))
                .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(durationOfSeconds)))
                .perform();

    }
    public static void multiTaping(MobileElement mobileElement, AndroidDriver<AndroidElement> driver, int numOfTaps){
        MobileElement elem = mobileElement;
        TouchAction action = new TouchAction(driver);

        action.tap(new TapOptions()
                .withElement(ElementOption.element(elem))
                .withTapsCount(numOfTaps))
                .perform();
    }
    public static void pressWithDuration(MobileElement mobileElement, AndroidDriver<AndroidElement> driver, int durationOfSeconds){
        MobileElement elem = mobileElement;
        TouchAction action = new TouchAction(driver);

        action.press(new ElementOption()
                .withElement(elem))
                .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(durationOfSeconds)))
                .perform();

    }
    public static void longPressWithDuration(MobileElement mobileElement, AndroidDriver<AndroidElement> driver, int durationOfSeconds){
        MobileElement elem = mobileElement;
        TouchAction action = new TouchAction(driver);

        action.longPress(new LongPressOptions()
                .withElement(ElementOption.element(elem))
                .withDuration(Duration.ofSeconds(durationOfSeconds)));

    }
    public static void dragAndDropWithDuration(MobileElement dragStart, MobileElement dropFinish, AndroidDriver<AndroidElement> driver){
        MobileElement start = dragStart;
        MobileElement finish = dropFinish;
        TouchAction action = new TouchAction(driver);

        action.press(new ElementOption()
                .withElement(start))
                .moveTo(new ElementOption().withElement(finish))
                .release()
                .perform();


    }
    public static void dragAndDropWithCoordinates(MobileElement dragStart, AndroidDriver<AndroidElement> driver,int x,int y){
        MobileElement start = dragStart;
        TouchAction action = new TouchAction(driver);

        action.press(new ElementOption()
                .withElement(start))
                .moveTo(new PointOption().withCoordinates(x,y))
                .perform();

    }
    public static void swipeScreen(Direction dir,PointOption pointOptionStart, AndroidDriver<AndroidElement> driver) {
        System.out.println("swipeScreen(): dir: '" + dir + "'"); // always log your actions

        final int ANIMATION_TIME = 200;
        final int PRESS_TIME = 200;

        int edgeBorder = 10;

        PointOption pointOptionEnd;
        Dimension dims = driver.manage().window().getSize();
        pointOptionStart = PointOption.point(dims.width / 2, dims.height / 2);

        switch (dir) {
            case DOWN: // center of footer
                pointOptionEnd = PointOption.point(dims.width / 2, dims.height - edgeBorder);
                break;
            case UP: // center of header
                pointOptionEnd = PointOption.point(dims.width / 2, edgeBorder);
                break;
            case LEFT: // center of left side
                pointOptionEnd = PointOption.point(edgeBorder, dims.height / 2);
                break;
            case RIGHT: // center of right side
                pointOptionEnd = PointOption.point(dims.width - edgeBorder, dims.height / 2);
                break;
            default:
                throw new IllegalArgumentException("swipeScreen(): dir: '" + dir + "' NOT supported");
        }

        // execute swipe using TouchAction
        try {
            new TouchAction(driver)
                    .press(pointOptionStart)
                    // a bit more reliable when we add small wait
                    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(PRESS_TIME)))
                    .moveTo(pointOptionEnd)
                    .release()
                    .perform();
        } catch (Exception e) {
            System.err.println("swipeScreen(): TouchAction FAILED\n" + e.getMessage());
            return;
        }

        // always allow swipe action to complete
        try {
            Thread.sleep(ANIMATION_TIME);
        } catch (InterruptedException e) {
            // ignore
        }
    }
    public static void swipeElementAndroid(MobileElement el, Direction dir, AndroidDriver<AndroidElement> driver) {
        System.out.println("swipeElementAndroid(): dir: '" + dir + "'"); // always log your actions

        // Animation default time:
        //  - Android: 300 ms
        //  - iOS: 200 ms
        // final value depends on your app and could be greater
        final int ANIMATION_TIME = 200; // ms

        final int PRESS_TIME = 200; // ms

        int edgeBorder;
        PointOption pointOptionStart, pointOptionEnd;

        // init screen variables
        Rectangle rect = el.getRect();
        // sometimes it is needed to configure edgeBorders
        // you can also improve borders to have vertical/horizontal
        // or left/right/up/down border variables
        edgeBorder = 0;

        switch (dir) {
            case DOWN: // from up to down
                pointOptionStart = PointOption.point(rect.x + rect.width / 2,
                        rect.y + edgeBorder);
                pointOptionEnd = PointOption.point(rect.x + rect.width / 2,
                        rect.y + rect.height - edgeBorder);
                break;
            case UP: // from down to up
                pointOptionStart = PointOption.point(rect.x + rect.width / 2,
                        rect.y + rect.height - edgeBorder);
                pointOptionEnd = PointOption.point(rect.x + rect.width / 2,
                        rect.y + edgeBorder);
                break;
            case LEFT: // from right to left
                pointOptionStart = PointOption.point(rect.x + rect.width - edgeBorder,
                        rect.y + rect.height / 2);
                pointOptionEnd = PointOption.point(rect.x + edgeBorder,
                        rect.y + rect.height / 2);
                break;
            case RIGHT: // from left to right
                pointOptionStart = PointOption.point(rect.x + edgeBorder,
                        rect.y + rect.height / 2);
                pointOptionEnd = PointOption.point(rect.x + rect.width - edgeBorder,
                        rect.y + rect.height / 2);
                break;
            default:
                throw new IllegalArgumentException("swipeElementAndroid(): dir: '" + dir + "' NOT supported");
        }

        // execute swipe using TouchAction
        try {
            new TouchAction(driver)
                    .press(pointOptionStart)
                    // a bit more reliable when we add small wait
                    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(PRESS_TIME)))
                    .moveTo(pointOptionEnd)
                    .release().perform();
        } catch (Exception e) {
            System.err.println("swipeElementAndroid(): TouchAction FAILED\n" + e.getMessage());
            return;
        }

        // always allow swipe action to complete
        try {
            Thread.sleep(ANIMATION_TIME);
        } catch (InterruptedException e) {
            // ignore
        }
    }
    public static void zoomIn(AndroidElement elem, AndroidDriver<AndroidElement> driver){

        int x = elem.getLocation().getX() + elem.getSize().getWidth()/2;
        int y = elem.getLocation().getY() + elem.getSize().getHeight()/2;

        TouchAction finger1 = new TouchAction(driver);
        finger1.press(new ElementOption()
                .withElement(elem)
                .withCoordinates(x,y-10))
                .moveTo(new ElementOption()
                        .withElement(elem)
                        .withCoordinates(x,y-100));

        TouchAction finger2 = new TouchAction(driver);
        finger2.press(new ElementOption()
                .withElement(elem)
                .withCoordinates(x,y+10))
                .moveTo(new ElementOption()
                        .withElement(elem)
                        .withCoordinates(x,y+100));

        MultiTouchAction action = new MultiTouchAction(driver);
        action.add(finger1).add(finger2).perform();

    }
    public static void zoomOut(AndroidElement elem, AndroidDriver<AndroidElement> driver){

        int x = elem.getLocation().getX() + elem.getSize().getWidth()/2;
        int y = elem.getLocation().getY() + elem.getSize().getHeight()/2;

        TouchAction finger1 = new TouchAction(driver);
        finger1.press(new ElementOption()
                .withElement(elem)
                .withCoordinates(x,y-100))
                .moveTo(new ElementOption()
                        .withElement(elem)
                        .withCoordinates(x,y-10));

        TouchAction finger2 = new TouchAction(driver);
        finger2.press(new ElementOption()
                .withElement(elem)
                .withCoordinates(x,y+100))
                .moveTo(new ElementOption()
                        .withElement(elem)
                        .withCoordinates(x,y+10));

        MultiTouchAction action = new MultiTouchAction(driver);
        action.add(finger1).add(finger2).perform();

    }
    public enum Direction {
        UP,
        DOWN,
        LEFT,
        RIGHT;
    }
}
