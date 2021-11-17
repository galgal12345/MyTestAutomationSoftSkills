package lesson17;//package <set your test package>;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.By;
import org.testng.annotations.*;
import java.net.URL;
import java.net.MalformedURLException;
import java.util.logging.Level;

public class EriBank {
    private String reportDirectory = "reports";
    private String reportFormat = "xml";
    private String testName = "EriBank";
    protected AndroidDriver<AndroidElement> driver = null;

    DesiredCapabilities dc = new DesiredCapabilities();

    @BeforeMethod
    public void setUp() throws MalformedURLException {
        dc.setCapability("reportDirectory", reportDirectory);
        dc.setCapability("reportFormat", reportFormat);
        dc.setCapability("testName", testName);
        dc.setCapability(MobileCapabilityType.UDID, "16af5295");
        dc.setCapability(MobileCapabilityType.APP, "C:\\Users\\GIL\\AppData\\Roaming\\appiumstudio\\original-apks\\com.experitest.ExperiBank.LoginActivity.2.apk");
        dc.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.experitest.ExperiBank");
        dc.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, ".LoginActivity");
        driver = new AndroidDriver<>(new URL("http://localhost:4723/wd/hub"), dc);
        //driver.setLogLevel(Level.INFO);
    }

    @Test
    public void testUntitled() {
        driver.hideKeyboard();
        driver.findElement(By.xpath("//*[@id='usernameTextField']")).sendKeys("gil");
        driver.findElement(By.xpath("//*[@id='passwordTextField']")).sendKeys("311285738");
        driver.findElement(By.xpath("//*[@text='Login']")).click();
        driver.findElement(By.xpath("//*[@text='Close']")).click();

    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}