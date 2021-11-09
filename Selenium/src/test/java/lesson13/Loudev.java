package lesson13;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Loudev {

    WebDriver webDriver;

    @BeforeClass
    public void startSession(){
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        webDriver.get("https://demo.nopcommerce.com/");
    }

    @Test(description = "navigating pages")
    @Description("Navigating to page Camera $ Photo page")
    public void testNavigation(){
        webDriver.findElement(By.linkText("electronics")).click();
    }

    @AfterClass
    public void endSession() {
        webDriver.quit();
    }
}
