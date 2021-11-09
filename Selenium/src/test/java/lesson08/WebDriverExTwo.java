package lesson08;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class WebDriverExTwo {
    WebDriver webDriver;

    @BeforeClass
    public void startSession() {
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
    }


    @Test
    public void testOne() {

        webDriver.get("https://www.google.com/");
        webDriver.get("https://www.bing.com/");
        webDriver.navigate().back();
        System.out.println(webDriver.getTitle());

        if (webDriver.getPageSource().contains("bubble"))
            System.out.println("Exists");
        else
            System.out.println("Not Exists");

    }


    @AfterClass
    public void endSession() {
        webDriver.quit();
    }
}
