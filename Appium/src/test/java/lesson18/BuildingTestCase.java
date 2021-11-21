package lesson18;//package <set your test package>;
import io.appium.java_client.android.Activity;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;
import java.net.URL;
import java.net.MalformedURLException;
import java.util.logging.Level;

public class BuildingTestCase {
    private String reportDirectory = "C:/AppiumStudionReports/";
    private String reportFormat = "xml";
    private String testName = "AppiumStudionTest.html";
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
        driver.setLogLevel(Level.INFO);
    }

    @Test
    public void testUntitled1() {
        driver.executeScript("experitest:client.verifyElementFound(\"NATIVE\", \"xpath=//*[@text='Views']\",0)");
    }

    @Test
    public void testUntitled2() {
        driver.startActivity(new Activity("com.experitest.ExperiBank", ".LoginActivity"));
    }
    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}