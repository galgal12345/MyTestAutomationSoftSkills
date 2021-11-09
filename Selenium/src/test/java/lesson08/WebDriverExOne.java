package lesson08;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class WebDriverExOne {

    WebDriver webDriver;

    @BeforeClass
    public void startSession(){
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        webDriver.get("https://www.imdb.com/");
    }
    @AfterClass
    public void endSession(){
        webDriver.quit();
    }


    @Test
    public void testOne(){

        if(webDriver.getTitle().equals("Title IMDb: Ratings, Reviews, and Where to Watch the Best Movies & TV Shows"))
            System.out.println("title passed");
        else
            System.out.println("title failed");

        System.out.println("Title " + webDriver.getTitle());
    }


    @Test
    public void testTwo(){

        if(webDriver.getCurrentUrl().equals("https://www.imdb.com/"))
            System.out.println("url passed");
        else
            System.out.println("url failed");

        System.out.println("Url " + webDriver.getCurrentUrl());
    }
}
