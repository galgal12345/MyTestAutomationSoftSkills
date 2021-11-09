package lesson11;

import static org.testng.Assert.*;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SwitchAndNavigation {

    WebDriver webDriver;

    @BeforeClass
    public void startSession() {
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        webDriver.get("https://atidcollege.co.il/Xamples/ex_switch_navigation.html");
    }

    @Test
    public void testShowAlert() {
        //2
        webDriver.findElement(By.id("btnAlert")).click();
        Alert popup = webDriver.switchTo().alert();
        String alertText = popup.getText();
        assertEquals(alertText, "This is my alert", "Error Occurred:failed to getText() from popup");
        System.out.println(alertText);
        popup.accept();
        assertEquals(webDriver.findElement(By.id("output")).getText(), "Alert is gone.", "Error Occurred:failed to getText() from output id");
    }

    @Test
    public void testShowPrompt() {
        //3
        webDriver.findElement(By.id("btnPrompt")).click();
        Alert popup = webDriver.switchTo().alert();
        String promptText = popup.getText();
        assertEquals(promptText, "Hi, What's Your Name ?", "Error Occurred:failed to getText() from prompt");
        System.out.println(promptText);
        popup.sendKeys("Gil Almuly");
        popup.accept();
        String editText = webDriver.findElement(By.id("output")).getText();
        assertEquals(editText, "Gil Almuly", "Error Occurred:failed to getText() from editText");
    }

    @Test
    public void testInlineFrame() {
        //4
        WebElement ifrm = webDriver.findElement(By.cssSelector("iframe[src='ex_switch_newFrame.html']"));
        webDriver.switchTo().frame(ifrm);
        System.out.println(webDriver.findElement(By.id("iframe_container")).getText());
    }

    @Test
    public void testOpenNewTab() {
        //5
        String expectedNewTabText = "This is a new tab";

        String winHandle = webDriver.getWindowHandle();
        webDriver.findElement(By.id("btnNewTab")).click();

        for(String myWinHandle : webDriver.getWindowHandles())
            webDriver.switchTo().window(myWinHandle);

        System.out.println(webDriver.findElement(By.id("new_tab_container")).getText());
        assertEquals(webDriver.findElement(By.id("new_tab_container")).getText(), expectedNewTabText, "Not The Same Text From Tab");

        webDriver.switchTo().window(winHandle);
    }

    @Test
    public void testOpenNewWindow() {

        String expectedWindowResult = "This is a new window";

        String winHandle = webDriver.getWindowHandle();
        webDriver.findElement(By.cssSelector("a[href='ex_switch_newWindow.html']")).click();

        for(String myWinHandle : webDriver.getWindowHandles())
            webDriver.switchTo().window(myWinHandle);

        System.out.println(webDriver.findElement(By.id("new_window_container")).getText());

        assertEquals(webDriver.findElement(By.id("new_window_container")).getText(), expectedWindowResult, "Not The Same Text From Window");

        webDriver.switchTo().window(winHandle);


    }

    @AfterClass
    public void endSession() {
        webDriver.quit();
    }
}
