package lesson11;

import static org.testng.Assert.*;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Synchronization {

    WebDriver webDriver;

    @BeforeClass
    public void startSession() {
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        webDriver.get("https://atidcollege.co.il/Xamples/ex_synchronization.html");
    }

    @Test
    public void testPushTheBtnStartRnd() {

        String expectedResult = "My Rendered Element After Fact!";
        webDriver.findElement(By.id("rendered")).click();
        WebDriverWait wait = new WebDriverWait(webDriver, 5);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("finish2")));
        assertEquals(webDriver.findElement(By.id("finish2")).getText(), expectedResult, "RND elements not equal");
    }

    @Test
    public void testPushBtnStartHidden() throws InterruptedException {
        webDriver.findElement(By.id("hidden")).click();
        Thread.sleep(5000);
        webDriver.findElement(By.cssSelector("div[id='loading1'][style='display: none;']"));
    }

    @Test
    public void testPushBtnRemove() {
        String expectedResult = "It's gone!";
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.MINUTES);
        webDriver.findElement(By.xpath("//form/button[@id='btn']")).click();
        assertEquals(webDriver.findElement(By.id("message")).getText(),expectedResult,"the element is not recognized from btnRemove");
    }

    @AfterClass
    public void endSession(){
        webDriver.quit();
    }
}
