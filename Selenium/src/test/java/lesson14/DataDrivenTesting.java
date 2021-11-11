package lesson14;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class DataDrivenTesting {

    WebDriver webDriver;

    @BeforeClass
    public void startSession(){
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        webDriver.get("https://www.wikipedia.org/");
    }

    @Test(dataProvider = "data-provider")
    public void testOne(String url, String expectedResult){
        //todo:continue!!!!!!!!!!!!
        webDriver.get(url);
        assertEquals(webDriver.findElement(By.id("firstHeading")).getText(), expectedResult);

    }

    @DataProvider(name="data-provider")
    public Object[][] getDataObject(){
        return new Object[][] {
                {"https://www.wikipedia.org/"},{"Israel"},
                {"https://www.wikipedia.org/"},{"Automation"},
                {"https://www.wikipedia.org/"},{"BlahBlah"}
        };
    }

    @AfterClass
    public void endSession() {
        webDriver.quit();
    }
}
