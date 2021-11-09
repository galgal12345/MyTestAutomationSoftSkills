package lesson10;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class Controllers {

    WebDriver webDriver;

    @BeforeClass
    public void startSession() {
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        webDriver.get("https://atidcollege.co.il/Xamples/ex_controllers.html");
    }

    @AfterClass
    public void endSession() {
        webDriver.quit();
    }


    @Test
    public void testOne() {

        webDriver.findElement(By.name("firstname")).sendKeys("Gil");
        webDriver.findElement(By.name("lastname")).sendKeys("Almuly");

        Select selectCountry = new Select(webDriver.findElement(By.id("continents")));
        List<WebElement> countriesList = selectCountry.getOptions();
        for (int i = 0; i < countriesList.size(); i++) {
            if (countriesList.get(i).getText().equals("Europe")) {
                countriesList.get(i).click();
                break;
            }
        }

        webDriver.findElement(By.id("sex-1")).click();
        webDriver.findElement(By.id("exp-3")).click();

        webDriver.findElement(By.name("datepicker")).click();
        WebElement dataWidget = webDriver.findElement(By.id("ui-datepicker-div"));
        List<WebElement> list = dataWidget.findElements(By.tagName("td"));
        for (WebElement webElement : list) {
            if (webElement.getText().equals("17")) {
                webElement.click();
                break;
            }
        }

        webDriver.findElement(By.id("profession-1")).click();
        webDriver.findElement(By.id("photo")).sendKeys("C:/profile.png");
        webDriver.findElement(By.id("tool-1")).click();

        Select selectCommend = new Select(webDriver.findElement(By.id("selenium_commands")));
        List<WebElement> selectCommends = selectCommend.getOptions();
        for (int i = 0; i < selectCommends.size(); i++) {
            if (selectCommends.get(i).getText().equals("Browser Commands")) {
                selectCommends.get(i).click();
                break;
            }
        }

        webDriver.findElement(By.id("submit")).submit();
        String webElement = webDriver.getCurrentUrl();
        String[] strings = webElement.split("&");
        String[] str = strings[4].split("%2F");
        String year = null;
        String month = null;
        String day = null;

        if (webElement.contains("Gil") && webElement.contains("Almuly") && webElement.contains("europe"))
            System.out.println("Test Passed");
        else
            System.out.println("Test Failed");



        for (int i = 1; i < str.length; i++) {
            if (str[i].contains("2021"))
                 year = str[i];

            month = str[0].substring(11,13);

            if (str[i].contains("17"))
                 day = str[i];
        }
        System.out.println(year + " - " + month + " - " + day);

        //todo beautify the code letter!!!!!

    }
}
