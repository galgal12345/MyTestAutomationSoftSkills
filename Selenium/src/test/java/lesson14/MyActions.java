package lesson14;
import static org.testng.Assert.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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

    @Test(priority = 1)
    public void testOne()  {

        WebElement dragable = webDriver.findElement(By.id("draggable"));
        WebElement dropable = webDriver.findElement(By.id("droppable"));
        Actions action = new Actions(webDriver);
        action.dragAndDrop(dragable,dropable).build().perform();
        System.out.println(webDriver.findElement(By.xpath("//*[@id='droppable']/p")).getText());
        assertTrue(webDriver.findElement(By.xpath("//*[@id='droppable']/p")).isDisplayed(), "Test One Failed: cant find text dropped");
    }

    @Test(priority = 2)
    public void testTwo() {

        List<WebElement> list1 = webDriver.findElements(By.tagName("li"));
        Actions actions = new Actions(webDriver);
        actions.clickAndHold(list1.get(1)).clickAndHold(list1.get(2));
        actions.build().perform();
    }

    @Test(priority = 3)
    public void testThree(){

        String expectedResult = "Hello World";
        WebElement dClick = webDriver.findElement(By.id("dbl_click"));
        Actions actions = new Actions(webDriver);
        actions.doubleClick(dClick);
        actions.build().perform();
        String doubleClickStr = webDriver.findElement(By.id("demo")).getText();
        System.out.println(doubleClickStr);
        assertEquals(doubleClickStr, expectedResult, "Test Three Failed: didn't found the string Hello World");
    }

    @Test(priority = 4)
    public void testFour(){

        String mouseHoverExpectedResult = "background-color: rgb(255, 255, 0);";

        Actions actions = new Actions(webDriver);
        WebElement elem1 = webDriver.findElement(By.id("mouse_hover"));
        actions.moveToElement(elem1).build().perform();

        String backGroundColor = webDriver.findElement(By.id("mouse_hover")).getAttribute("style");
        System.out.println(backGroundColor);
        assertEquals(backGroundColor, mouseHoverExpectedResult, "Test Four Failed");
    }

    @Test(priority = 5)
    public void testFive(){

        String scrolledElementExpectedResult = "This Element is Shown When Scrolled";
        WebElement elem = webDriver.findElement(By.id("scrolled_element"));
        JavascriptExecutor js = (JavascriptExecutor)webDriver;
        js.executeScript("arguments[0].scrollIntoView(true);",elem);
        String scrolledElement = webDriver.findElement(By.id("scrolled_element")).getText();
        System.out.println(scrolledElement);
        assertEquals(scrolledElement, scrolledElementExpectedResult, "Test Five Failed");
    }

    @AfterClass
    public void endSession() throws InterruptedException {
        Thread.sleep(3000);
        webDriver.quit();
    }
}
