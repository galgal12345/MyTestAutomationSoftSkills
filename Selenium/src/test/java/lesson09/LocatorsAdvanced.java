package lesson09;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class LocatorsAdvanced {

    WebDriver webDriver;
    @BeforeClass
    public void startSession() {
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        webDriver.get("https://atidcollege.co.il/Xamples/ex_locators.html");
    }

    @Test
    public void testOne() {

        WebElement webElement1 = webDriver.findElement(By.id("locator_id"));
        WebElement webElement2 = webDriver.findElement(By.name("locator_name"));
        WebElement webElement3 = webDriver.findElement(By.tagName("p"));
        WebElement webElement4 = webDriver.findElement(By.className("locator_class"));
        WebElement webElement5 = webDriver.findElement(By.linkText("myLocator(5)"));
        WebElement webElement6 = webDriver.findElement(By.partialLinkText("(6)"));
        WebElement webElement7 = webDriver.findElement(By.cssSelector("input[myname='selenium']"));
        WebElement webElement8 = webDriver.findElement(By.xpath("//button[class=contains(@id,'btn-2')]"));

        System.out.println(webElement1);
        System.out.println(webElement2);
        System.out.println(webElement3);
        System.out.println(webElement4);
        System.out.println(webElement5);
        System.out.println(webElement6);
        System.out.println(webElement7);
        System.out.println(webElement8);


    }
    @Test
    public void testTwo() {

        List<WebElement> webElement1 = webDriver.findElements(By.id("locator_id"));
        List<WebElement> webElement2 = webDriver.findElements(By.name("locator_name"));
        List<WebElement> webElement3 = webDriver.findElements(By.tagName("p"));
        List<WebElement> webElement4 = webDriver.findElements(By.className("locator_class"));
        List<WebElement> webElement5 = webDriver.findElements(By.linkText("myLocator(5)"));
        List<WebElement> webElement6 = webDriver.findElements(By.partialLinkText("(6)"));
        List<WebElement> webElement7 = webDriver.findElements(By.cssSelector("input[myname='selenium']"));
        List<WebElement> webElement8 = webDriver.findElements(By.xpath("//button[class=contains(@id,'btn-2')]"));

        for (WebElement element: webElement1)
            System.out.println(element.getText());
        for (WebElement element: webElement2)
            System.out.println(element.getText());
        for (WebElement element: webElement3)
            System.out.println(element.getText());
        for (WebElement element: webElement4)
            System.out.println(element.getText());
        for (WebElement element: webElement5)
            System.out.println(element.getText());
        for (WebElement element: webElement6)
            System.out.println(element.getText());
        for (WebElement element: webElement7)//todo:ask roan why this is not working!!!!!!!!!!
            System.out.println(element.getAttribute("value"));
        for (WebElement element: webElement8)
            System.out.println(element.getText());



    }

    @AfterClass
    public void endSession() {
        webDriver.quit();
    }
}
