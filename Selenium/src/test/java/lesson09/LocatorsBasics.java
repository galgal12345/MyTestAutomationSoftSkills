package lesson09;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.*;

public class LocatorsBasics {
    WebDriver webDriver;

    @BeforeClass
    public void startSession() {
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();

    }

    @AfterClass
    public void endSession() {
        webDriver.quit();
    }


//    @Test
//    public void testOne() {
//
//
//        //Ex 1
//        webDriver.get("https://www.selenium.dev/");
//
//        int countLinks = 0;
//        int countLinksContainsSelenium = 0;
//        int countLinksContainsSmallSelenium = 0;
//        System.out.println(webDriver.findElement(By.tagName("svg")));
//        System.out.println(webDriver.findElement(By.id("selenium_logo")));
//
//        List<WebElement> links = webDriver.findElements(By.tagName("a"));
//        List<WebElement> namedSelenium = webDriver.findElements(By.partialLinkText("Selenium"));
//        List<WebElement> namedSmallSelenium = webDriver.findElements(By.partialLinkText("selenium"));
//
//        for (WebElement link : links) {
//            countLinks++;
//        }
//        for (WebElement name : namedSelenium) {
//            countLinksContainsSelenium++;
//        }
//        for (WebElement name : namedSmallSelenium) {
//            countLinksContainsSmallSelenium++;
//        }
//        System.out.println(countLinks);
//        System.out.println(countLinksContainsSelenium);
//        System.out.println(countLinksContainsSmallSelenium);
//
//    }

    @Test
    public void testTwo() {
        //Ex 2
        webDriver.get("https://www.wikipedia.org/");



        List<WebElement>  webElementOne =  webDriver.findElements(By.className("central-featured-logo"));
        List<WebElement> webElementTwo =  webDriver.findElements(By.id("searchInput"));
        List<WebElement> webElementThree = webDriver.findElements(By.id("searchLanguage"));
        List<WebElement> webElementFour = webDriver.findElements(By.className("footer-sidebar-content"));


        for (WebElement element: webElementOne) {
            System.out.println(element);
        }for (WebElement element: webElementTwo) {
            System.out.println(element);
        }for (WebElement element: webElementThree) {
            System.out.println(element);
        }for (WebElement element: webElementFour) {
            System.out.println(element);
        }


    }

}
