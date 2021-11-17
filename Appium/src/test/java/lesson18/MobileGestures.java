package lesson18;//package <set your test package>;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;
import tools.Tools;

import static org.testng.Assert.*;


import java.net.URL;
import java.net.MalformedURLException;

public class MobileGestures {

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
    public void testDoSomeStuff() {

        MobileElement mobileElementView = driver.findElement(By.xpath("//*[@text='Views']"));
        MobileElement mobileElementDataWidget = driver.findElement(By.xpath("//*[@bounds='[0,1001][1080,1127]']"));
        MobileElement mobileElementInline = driver.findElement(By.xpath("//*[@bounds='[0,356][1080,482]']"));

        Tools.regularTap(mobileElementView,driver);
        Tools.regularTap(mobileElementDataWidget,driver);
        Tools.regularTap(mobileElementInline,driver);

        MobileElement dragHoursStart = driver.findElement(By.xpath("//*[@contentDescription='12']"));
        MobileElement dropHoursFinish = driver.findElement(By.xpath("//*[@contentDescription='9']"));

        Tools.dragAndDropWithDuration(dragHoursStart, dropHoursFinish,driver);

        MobileElement dragMinutesStart = driver.findElement(By.xpath("//*[@contentDescription='15']"));
        MobileElement dropMinutesFinish = driver.findElement(By.xpath("//*[@contentDescription='45']"));

        Tools.dragAndDropWithDuration(dragMinutesStart, dropMinutesFinish,driver);

        String expectedHour = "9";
        String expectedMinutes = "45";

        assertEquals(driver.findElement(By.xpath("//*[@text='9']")).getText(), expectedHour);
        assertEquals(driver.findElement(By.xpath("//*[@text='45']")).getText(), expectedMinutes);

        driver.pressKey(new KeyEvent().withKey(AndroidKey.BACK));
        driver.pressKey(new KeyEvent().withKey(AndroidKey.BACK));
        driver.pressKey(new KeyEvent().withKey(AndroidKey.BACK));

    }

    @Test(priority = 2)
    public void testTwo() {

        MobileElement mobileElementView = driver.findElement(By.xpath("//*[@text='Views']"));
        MobileElement mobileElementExpandableLists = driver.findElement(By.xpath("//*[@bounds='[0,1259][1080,1385]']"));
        MobileElement mobileElementCustomAdapter = driver.findElement(By.xpath("//*[@bounds='[0,227][1080,353]']"));

        Tools.regularTap(mobileElementView,driver);
        Tools.regularTap(mobileElementExpandableLists,driver);
        Tools.regularTap(mobileElementCustomAdapter,driver);

        Tools.longPressWithDuration(driver.findElement(By.xpath("//*[@text='People Names']")),driver,2);
        MobileElement mobileElementSimpleMenu = driver.findElement(By.xpath("//*[@text='People Names']"));

        driver.pressKey(new KeyEvent().withKey(AndroidKey.BACK));
        Tools.regularTap(mobileElementCustomAdapter,driver);
        Tools.pressWithDuration(driver.findElement(By.xpath("//*[@text='People Names']")),driver,2);

        driver.pressKey(new KeyEvent().withKey(AndroidKey.BACK));
        Tools.regularTap(mobileElementCustomAdapter,driver);
        Tools.regularTapWithDuration(driver.findElement(By.xpath("//*[@text='People Names']")),driver,2);
        assertTrue(mobileElementSimpleMenu.isDisplayed());

    }


    @AfterClass
    public void tearDown() {
        driver.quit();
    }

}

