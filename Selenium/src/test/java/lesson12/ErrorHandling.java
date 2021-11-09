package lesson12;

import static org.testng.Assert.*;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;



public class ErrorHandling {

    WebDriver webDriver;

    @BeforeClass
    public void startSession(){
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        webDriver.get("https://atidcollege.co.il/Xamples/ex_synchronization.html");
    }

    @Test
    public void testWithTryCatch() throws InterruptedException {
        try {

            webDriver.findElement(By.id("btn")).click();
            System.out.println("The val is: " + webDriver.findElement(By.xpath("//div/form/div/input[@id='checkbox']")));
            assertTrue(webDriver.findElement(By.xpath("//div/form/div/input[@id='checkbox']")).isDisplayed(), "Error");
            Thread.sleep(5000);
            System.out.println("The val is: " + webDriver.findElement(By.xpath("//div/form/div/input[@id='checkbox']")));
            assertTrue(webDriver.findElement(By.xpath("//div/form/div/input[@id='checkbox']")).isDisplayed(), "Error");
            System.out.println("Test Faild: elem exist on screen");

        } catch (NoSuchElementException e) {
            System.out.println("Test Passed: elem does NOT exist on screen");
            e.printStackTrace();
        }


    }

    @Test
    public void testWithoutTryCatch() throws InterruptedException {
        webDriver.findElement(By.id("btn")).click();
        Thread.sleep(5000);

        if (webDriver.findElements(By.xpath("//div/form/div/input[@id='checkbox']")).isEmpty())
            System.out.println("Test Passed: elem does NOT exist on screen");
        else
            System.out.println("Test Failed: elem exist on screen");


    }
    @AfterClass
    public void endSession() {
        webDriver.quit();
    }
}
