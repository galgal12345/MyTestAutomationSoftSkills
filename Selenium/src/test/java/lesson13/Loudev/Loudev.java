package lesson13.Loudev;

import static org.testng.Assert.*;

import com.google.common.util.concurrent.Uninterruptibles;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;



@Listeners(lesson13.Listeners.class)
public class Loudev {

    WebDriver webDriver;

    @BeforeClass
    public void startSession() {
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        webDriver.get("http://loudev.com/");
    }

    @Test(description = "verifying elements")
    @Description("verifying all elements from 3 to 20")
    public void testVerifyElements(){
        Support.verifyElements(webDriver);
    }

    @AfterClass
    public void endSession() {
        webDriver.quit();
    }

}
