package lesson14;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;

public class DataDrivenTesting {

    WebDriver webDriver;

    @BeforeClass
    public void startSession(){
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        webDriver.get("http://wikipedia.org/");

    }

    @Test(priority = 1, dataProvider = "data-provider" ,dataProviderClass = StaticProvider.class)
    public void testOne(String key, String expectedResult){

        webDriver.findElement(By.id("searchInput")).sendKeys(key);
        webDriver.findElement(By.xpath("//*[@id='search-form']/fieldset/button/i")).click();
        assertEquals(webDriver.findElement(By.id("firstHeading")).getText(), expectedResult, "Test One Failed");
        webDriver.get("http://wikipedia.org/");

    }

    @AfterClass
    public void endSession() {
        webDriver.quit();
    }




}
