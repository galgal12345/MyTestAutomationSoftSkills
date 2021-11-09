package lesson12;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

@Listeners(lesson12.Listeners.class)
public class ErrorHandlingListeners {

    WebDriver webDriver;

    @BeforeClass
    public void startSession() {
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        webDriver.get("https://atidcollege.co.il/Xamples/ex_synchronization.html");
    }

    @Test
    public void testWithTryCatch() throws InterruptedException {

        webDriver.findElement(By.id("btn")).click();
        System.out.println("The val is: " + webDriver.findElement(By.xpath("//div/form/div/input[@id='checkbox']")));
        assertTrue(webDriver.findElement(By.xpath("//div/form/div/input[@id='checkbox']")).isDisplayed(), "Error");
        Thread.sleep(5000);
        System.out.println("The val is: " + webDriver.findElement(By.xpath("//div/form/div/input[@id='checkbox']")));
        assertTrue(webDriver.findElement(By.xpath("//div/form/div/input[@id='checkbox']")).isDisplayed(), "Error");
        System.out.println("Test Faild: elem exist on screen");

    }




    @AfterClass
    public void endSession() {
        webDriver.quit();
    }


}
