package lesson21;

import org.junit.After;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class ElectronApp {

    public WebDriver driver;
    public ChromeOptions options;
    public DesiredCapabilities capabilities;

    @BeforeClass
    public void startSession(){
        System.setProperty("webdriver.chrome.driver", "C:\\electrondriver.exe");
        options = new ChromeOptions();
        options.setBinary("C:\\Automation\\Electrons\\Electron API Demos.exe");
        capabilities = new DesiredCapabilities();
        capabilities.setCapability("chromeOptions", options);
        capabilities.setBrowserName("chrome");
        driver = new ChromeDriver(capabilities);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @Test
    public void test1(){

        Actions actions = new Actions(driver);
        System.out.println(driver.findElement(By.xpath("//*[@id=\"windows-section\"]/header/div/h1")).getText());
        driver.findElement(By.id("button-menus")).click();
        driver.findElement(By.id("application-menu-demo-toggle")).click();

        WebElement elem = driver.findElement(By.xpath("//*[@id=\"menus-section\"]/div[1]/div/div/div/ul/li[2]/a"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);",elem);

    }
    @AfterClass
    public void endSession(){
        driver.quit();
    }
}
