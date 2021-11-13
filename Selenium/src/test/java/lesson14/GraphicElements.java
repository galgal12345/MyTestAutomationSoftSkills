package lesson14;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Screen;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class GraphicElements {

    WebDriver webDriver;
    Screen screen;

    @BeforeClass
    public void startSession(){
        WebDriverManager.edgedriver().setup();
        webDriver = new EdgeDriver();
        webDriver.get("https://www.google.com/maps");
        screen = new Screen();
    }

    @Test(priority = 1)
    public void testOne() throws FindFailed, InterruptedException {

        Thread.sleep(5000);
        screen.click("C:/images/plus.png");
        screen.click("C:/images/minus.png");
        screen.type("C:/images/search.png", "akko");
        screen.click("C:/images/search.png");
        Thread.sleep(3000);
        screen.click("C:/images/hotel.png");

    }

    @AfterClass
    public void endSession() throws InterruptedException {
        Thread.sleep(3000);
        webDriver.quit();
    }

}
