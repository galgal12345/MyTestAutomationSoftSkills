package BootCampAutomationTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

@Listeners(tools.Listeners.class)
public class PizzaFixed {
    WebDriver webDriver;

    @BeforeClass
    public void startSession(){
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        webDriver.get("https://atidcollege.co.il/Xamples/pizza/");
    }

    @Test(priority = 1)
    public void testOne()  {

        String expectedResult = "$7.50";
        System.out.println(webDriver.findElement(By.xpath("//*[@id='field_5_2']/div/span")).getText());
        assertEquals(webDriver.findElement(By.xpath("//*[@id='field_5_2']/div/span")).getText(),expectedResult, "Test One Failed: first price is not $7.50");

    }

    @Test(priority = 2)
    public void testTwo()  {

        webDriver.findElement(By.id("input_5_22_3")).sendKeys("Gil");
        webDriver.findElement(By.id("input_5_22_6")).sendKeys("Almuly");

        Select pickupOrDelivery = new Select(webDriver.findElement(By.id("input_5_21")));
        List<WebElement> pickupOrDeliveryList = pickupOrDelivery.getOptions();
        for (int i = 0; i < pickupOrDeliveryList.size(); i++) {
            if (pickupOrDeliveryList.get(i).getText().equals("Delivery +$3.00")) {
                System.out.println(pickupOrDeliveryList.get(i).getText());
                pickupOrDeliveryList.get(i).click();
                break;
            }
        }
        String dropDownExpectedResult = "$10.50";
        assertEquals(webDriver.findElement(By.xpath("//*[@id='field_5_2']/div/span")).getText(),dropDownExpectedResult, "Test Two Failed the price is not 10.50");
    }
    @Test(priority = 3)
    public void testThree()  {

        WebElement ifrm = webDriver.findElement(By.cssSelector("iframe[src='coupon.html']"));
        webDriver.switchTo().frame(ifrm);
        System.out.println(webDriver.findElement(By.id("coupon_Number")).getText());
        String s = webDriver.findElement(By.id("coupon_Number")).getText();
        webDriver.switchTo().defaultContent();
        webDriver.findElement(By.id("input_5_20")).sendKeys(s);
        webDriver.findElement(By.id("gform_submit_button_5")).click();

        String popupExpectedResult = "Gil Almuly 088-234";
        Alert popup = webDriver.switchTo().alert();
        String popupText = popup.getText();
        System.out.println(popupText);
        popup.accept();
        assertEquals(popupText, popupExpectedResult, "Test Three Failed: Expected Result is not Equal To Actual");


    }

    @AfterClass
    public void endSession() {
        webDriver.close();
        webDriver.quit();
    }
}
