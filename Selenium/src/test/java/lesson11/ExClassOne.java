package lesson11;



import static org.testng.Assert.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ExClassOne {

    private WebDriver webDriver;

    @BeforeClass
    public void startSession(){
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        webDriver.get("https://www.techlistic.com/p/demo-selenium-practice.html");
    }

    @Test
    public void testOne(){

        int count = 0;
       WebElement webElement =  webDriver.findElement(By.xpath("//table/tbody"));
        List<WebElement> webElementList = webElement.findElements(By.tagName("tr"));
        for (WebElement we : webElementList)
            count++;
        String totalValue = webDriver.findElement(By.xpath("//table/tfoot/tr/td")).getText();
        String subStringTotalValue = totalValue.substring(0,1);
        int subStringTotalValueToInt = Integer.parseInt(subStringTotalValue);
        assertEquals(subStringTotalValueToInt, count,"the integers are NOT equal");

    }

    @Test
    public void testTwo(){

        WebElement webElement2 =  webDriver.findElement(By.xpath("//table/tbody"));
        List<WebElement> webElementList2 = webElement2.findElements(By.tagName("tr"));
        for (WebElement we : webElementList2)
            System.out.println(we.getText());

    }

    @Test
    public void testThree(){

        //list that i made to compare
        List<String> heightStructure = new ArrayList<>();
        heightStructure.add("829m");
        heightStructure.add("601m");
        heightStructure.add("509m");
        heightStructure.add("492m");

        //initialize other list to store all heights
        List<String> highs = new ArrayList<>();

        WebElement tbodyElement =  webDriver.findElement(By.xpath("//table/tbody"));
        List<WebElement> tbodyElementsList = tbodyElement.findElements(By.tagName("tr"));

        //storing all the heights with for each
        for (WebElement elem : tbodyElementsList)
            highs.add(elem.findElements(By.tagName("td")).get(2).getText());

        //verifying with assert that the to list are equals with the same value
        for (int i = 0 ; i < heightStructure.size() ; i++)
            assertEquals(highs.get(i),heightStructure.get(i),"ERROR");
    }



    @AfterClass
    public void endSession(){
        webDriver.quit();
    }
}
