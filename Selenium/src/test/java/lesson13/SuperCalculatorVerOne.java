package lesson13;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


/**
 * רק כפל
 * 2 קליקים על בחירת פעולת חשבון
 * <p>
 * בלולאה עד 3 שחוזרת על עצמה 3 פעמים
 * שולחים מפתחות לשדה טקסט 1
 * <p>
 * בלולאה עד 9 את אותו אינדקס פעמיים
 * שולחים מפתחות לשדה טקסט 2
 * <p>
 * לחיצה על GO
 * <p>
 * לקיחה תוצאה מהמסך והדפסה
 **/

public class SuperCalculatorVerOne {

    WebDriver webDriver;

    @BeforeClass
    public void startSession() {
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        webDriver.get("http://juliemr.github.io/protractor-demo/");
    }

    @Test
    public void testMultiplicationTable() throws InterruptedException {


        webDriver.findElement(By.tagName("select")).click();
        webDriver.findElement(By.xpath("//option[4]")).click();

        String rightFieldsNumToStr;
        String leftFieldsNumToStr;

        System.out.println("testMultiplicationTable");
        for (int j = 1; j <= 3; j++) {
            rightFieldsNumToStr = "" + 1;
            leftFieldsNumToStr = "" + j;
            webDriver.findElement(By.xpath("//input[1]")).sendKeys(rightFieldsNumToStr);
            webDriver.findElement(By.xpath("//input[2]")).sendKeys(leftFieldsNumToStr);
            webDriver.findElement(By.id("gobutton")).click();
            Thread.sleep(3000);
            System.out.println(webDriver.findElement(By.tagName("h2")).getText());

        }
    }
    @Test
    public void testMultiplicationTableToNine() throws InterruptedException {
        webDriver.findElement(By.tagName("select")).click();
        webDriver.findElement(By.xpath("//option[4]")).click();

        String rightFieldsNumToStr ;
        String leftFieldsNumToStr ;
        System.out.println("testMultiplicationTableToNine");
        for (int i = 1 ; i <= 3 ; i++)
            for (int j = 1 ; j <= 3 ; j++){
                rightFieldsNumToStr = "" + i;
                leftFieldsNumToStr = "" + j;
                webDriver.findElement(By.xpath("//input[1]")).sendKeys(rightFieldsNumToStr);
                webDriver.findElement(By.xpath("//input[2]")).sendKeys(leftFieldsNumToStr);
                webDriver.findElement(By.id("gobutton")).click();
                Thread.sleep(3000);
                System.out.println(webDriver.findElement(By.tagName("h2")).getText());

            }
    }

    @AfterClass
    public void endSession() {
        webDriver.quit();
    }
}
