package lesson10;
import static org.testng.Assert.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class AssertsAndVerifications {
    private WebDriver webDriver;

    @BeforeClass
    public void startSession(){
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        webDriver.get("https://atidcollege.co.il/Xamples/bmi/");
    }

    @Test
    public void testOne(){

        webDriver.findElement(By.id("weight")).sendKeys("65");
        webDriver.findElement(By.id("hight")).sendKeys("174");

        webDriver.findElement(By.id("calculate_data")).click();

        String expectedResult = "21";
        String actualResult = webDriver.findElement(By.id("bmi_result")).getAttribute("value");//todo:!!!

        if (webDriver.findElement(By.id("bmi_result")).isDisplayed())
            assertEquals(actualResult, expectedResult, "Assert Failed: INCORRECT bmi result");
    }

    @Test
    public void testTwo(){
        System.out.println(webDriver.findElement(By.id("calculate_data")).getSize());
        System.out.println(webDriver.findElement(By.id("calculate_data")).getLocation());

        assertTrue(webDriver.findElement(By.id("calculate_data")).isDisplayed());
        assertTrue(webDriver.findElement(By.id("calculate_data")).isEnabled());
        assertFalse(webDriver.findElement(By.id("calculate_data")).isSelected());

        assertEquals(webDriver.findElement(By.id("calculate_data")).getTagName(), "input");
        assertEquals(webDriver.findElement(By.id("calculate_data")).getAttribute("value"), "Calculate BMI");

        assertFalse(webDriver.findElement(By.id("new_input")).isDisplayed(), "Please fill all the fields correctly");

    }

    @AfterClass
    public void endSession(){
        webDriver.quit();
    }
}
