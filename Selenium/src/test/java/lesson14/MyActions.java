package lesson14;
import static org.testng.Assert.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class MyActions {
    WebDriver webDriver;

    @BeforeClass
    public void startSession(){
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        webDriver.get("https://atidcollege.co.il/Xamples/ex_actions.html");
    }

    @Test
    public void testOne()  {
        WebElement dragable = webDriver.findElement(By.id("draggable"));
        WebElement dropable = webDriver.findElement(By.id("droppable"));
        Actions action = new Actions(webDriver);
        action.dragAndDrop(dragable,dropable).build().perform();

        System.out.println(webDriver.findElement(By.xpath("//*[@id='droppable']/p")).getText());
        assertTrue(webDriver.findElement(By.xpath("//*[@id='droppable']/p")).isDisplayed(), "Test Failed: cant find text dropped");
    }

    @Test
    public void testTwo()  {
        //todo:continue!!!!
        List<WebElement> list1 = webDriver.findElements(By.xpath("//*[@id='contact_info_left']"));
        Actions actions = new Actions(webDriver);
        actions.clickAndHold(list1.get(0)).clickAndHold(list1.get(1));
        actions.build().perform();

    }
    @AfterClass
    public void endSession() {
        webDriver.quit();
    }
}
