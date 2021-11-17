package lesson18;//package <set your test package>;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.MalformedURLException;
import java.util.List;

    import static org.testng.Assert.*;

public class MobileMethods {

    private String reportDirectory = "reports";
    private String reportFormat = "xml";
    private String testName = "Untitled";
    protected AndroidDriver<AndroidElement> driver = null;

    DesiredCapabilities dc = new DesiredCapabilities();

    @BeforeClass
    public void setUp() throws MalformedURLException {
        dc.setCapability("reportDirectory", reportDirectory);
        dc.setCapability("reportFormat", reportFormat);
        dc.setCapability("testName", testName);
        dc.setCapability(MobileCapabilityType.UDID, "16af5295");
        dc.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.example.android.apis");
        dc.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, ".ApiDemos");
        driver = new AndroidDriver<>(new URL("http://localhost:4723/wd/hub"), dc);
        //driver.setLogLevel(Level.INFO);
    }

    @Test(priority = 1)
    public void testVerifyElevenRecords() {

        int listItemsExpectedResult = 11;
       List<AndroidElement> cells = driver.findElements(By.id("text1"));
       System.out.println(cells.size());
       assertEquals(cells.size(), listItemsExpectedResult, "Test One Failed: the count of list item does not equal to eleven");

    }

    @Test(priority = 2)
    public void testDimensionalPrinting() {

        System.out.println(driver.findElement(By.xpath("//*[@text='Content']")).getRect().getWidth());
        System.out.println(driver.findElement(By.xpath("//*[@text='Content']")).getRect().getHeight());
        System.out.println(driver.findElement(By.xpath("//*[@text='Content']")).getRect().getX());
        System.out.println(driver.findElement(By.xpath("//*[@text='Content']")).getRect().getY());

    }
    @Test(priority = 3)
    public void testPrintActivityName() {

        System.out.println(driver.currentActivity());
        System.out.println(driver.getDeviceTime());
    }
    @Test(priority = 4)
    public void testCheckIfAppInstalled() {

        assertTrue(driver.isAppInstalled("com.example.android.apis"), "Test Failed: this app is not installed on the phone" );
    }
    @Test(priority = 5)
    public void testCheckLandscape() throws InterruptedException {

        if (driver.getOrientation().equals(ScreenOrientation.PORTRAIT)) {
            driver.rotate(ScreenOrientation.LANDSCAPE);
            Thread.sleep(5000);
            driver.rotate(ScreenOrientation.PORTRAIT);
        }

    }
    @Test(priority = 6)
    public void testNotificationScreenShot() throws IOException {

        driver.openNotifications();
        File notificationScreenShot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(notificationScreenShot, new File("c:/android_screen_shots/notificationScreenShot.png"));

        driver.pressKey(new KeyEvent().withKey(AndroidKey.HOME));
        File homeScreenShot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(homeScreenShot, new File("c:/android_screen_shots/homeScreenShot.png"));

    }
    @Test(priority = 7)
    public void testCheckIfPhoneLocked() throws InterruptedException {

        if (!driver.isDeviceLocked()){
            driver.lockDevice();
            Thread.sleep(3000);
            driver.unlockDevice();
        }
    }
    @Test(priority = 8)
    public void testVerifyListViewShownFourTimes(){

        String source = driver.getPageSource();
        int count = StringUtils.countMatches(source, "ListView");
        assertEquals(count, 4, "Test VerifyListViewShownFourTimes Failed: not equals to 4");
    }
    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}