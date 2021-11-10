package lesson13.Loudev;

import static org.testng.Assert.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class Support {

    public static Object verifyElements(WebDriver webDriver) {
        

        List<String> myElemsList = new ArrayList<>();
        WebElement widget = webDriver.findElement(By.xpath("//*[@id='ms-aloha']/div[1]"));
        List<WebElement> elems = widget.findElements(By.xpath("//*[@id='ms-aloha']/div[1]/ul/li"));
        for (WebElement elem : elems)
            if (elem.isDisplayed())
                myElemsList.add(elem.getText());

        int j = 3;
        ArrayList<String> elemsExpectedResult = new ArrayList<>();
        for (int i = 0; i < myElemsList.size(); i++, j++)
            elemsExpectedResult.add(i, "elem " + j);

        System.out.println(myElemsList);
        System.out.println(elemsExpectedResult);

        for (int i = 0; i < myElemsList.size(); i++)
            assertEquals(myElemsList.get(i), elemsExpectedResult.get(i), "Test Failed: elems don't match");

        //return to Loudev class so that the web browser will be closed
        return null;

    }


}
