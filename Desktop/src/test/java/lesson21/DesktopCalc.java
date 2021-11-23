package lesson21;

import io.appium.java_client.windows.WindowsDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class DesktopCalc {

    private WindowsDriver driver;
    private DesiredCapabilities capabilities;
    private final String calcApp = "Microsoft.WindowsCalculator_8wekyb3d8bbwe!App";

    @BeforeClass
    public void startSession() throws IOException {
        capabilities = new DesiredCapabilities();
        capabilities.setCapability("app", calcApp);
        driver = new WindowsDriver(new URL("http://127.0.0.1:4723"), capabilities);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @Test
    public void test() {
        System.out.println("I made it");
    }

    @AfterClass
    public void closeSession() {
        driver.quit();
    }
}
