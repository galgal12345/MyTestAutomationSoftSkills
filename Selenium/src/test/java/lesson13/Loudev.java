package lesson13;

import static org.testng.Assert.*;
import com.google.common.util.concurrent.Uninterruptibles;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Listeners(lesson13.Listeners.class)
public class Loudev {

    WebDriver webDriver;

    @BeforeClass
    public void startSession() {
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        webDriver.get("https://demo.nopcommerce.com/");
    }

    @Test(description = "navigating pages")
    @Description("Navigating to page Camera $ Photo page")
    public void testNavigation() {

        //navigate to cameras page
        webDriver.findElement(By.xpath("//div[6]/div[2]/ul[1]/li[2]/a")).click();
        webDriver.findElement(By.xpath("//h2/a[@title='Show products in category Camera & photo']")).click();
        testLowToHighPrices();

        Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);

    }

    @Step("Filtering the prices from low to high")
    public void testLowToHighPrices(){
        //arraylist for camera prices
        List<String> cameraPrices = new ArrayList<>();
        WebElement dataWidget = webDriver.findElement(By.className("item-grid"));
        List<WebElement> cells = dataWidget.findElements(By.tagName("span"));
        for (WebElement cell : cells)
            cameraPrices.add(cell.getText());

        //Before Filter
        System.out.println(cameraPrices);

        //Filter price from low to high
        Collections.swap(cameraPrices, 0, 1);

        //After Filter
        System.out.println(cameraPrices);

        VerifyThreeProducts(cameraPrices);
    }

    @Step("Verifying that we have only three products")
    public void VerifyThreeProducts(List<String> cameraPrices){
        //Verify There are  3 products
        assertEquals(cameraPrices.size(),3, "Test Failed: There are NOT 3 products");
    }

    @Test(description = "check product names")
    @Description("checking all the three product names that they are equals to our expected result list that we made")
    public void testProductNamesMatch(){

        //expected result names
        ArrayList<String> expectedResult = new ArrayList<String>();
        expectedResult.add("Nikon D5500 DSLR");
        expectedResult.add("Leica T Mirrorless Digital Camera");
        expectedResult.add("Apple iCam");

        //web names
        List<String> productsNamesList = new ArrayList<>();
        WebElement dataWidget = webDriver.findElement(By.className("item-grid"));
        List<WebElement> productsNames = dataWidget.findElements(By.xpath("//h2/a"));
        for (WebElement productsName : productsNames)
            productsNamesList.add(productsName.getText());

        //Verify
        System.out.println(expectedResult);
        System.out.println(productsNamesList);
        assertEquals(productsNamesList, expectedResult, "Test Failed: product names Doesn't match");

    }

    @Test(description = "checking Three stars or higher")
    @Description("checking if there is a product in the web page that is not 3 stars or higher")
    public void testThreeStarsOrHigher(){

        // names
        ArrayList<String> expectedResult = new ArrayList<String>();
        expectedResult.add("Nikon D5500 DSLR");
        expectedResult.add("Leica T Mirrorless Digital Camera");
        expectedResult.add("Apple iCam");

        //web stars
        List<Integer> ratingStarsList = new ArrayList<>();
        WebElement dataWidget = webDriver.findElement(By.className("item-grid"));
        List<WebElement> ratingStars = dataWidget.findElements(By.xpath("//div[@class='rating']/div"));
        for (WebElement ratingStar : ratingStars)
            ratingStarsList.add(ratingStar.getSize().getWidth());


        for (int i = 0 ; i < ratingStarsList.size() ; i++)
            assertTrue(ratingStarsList.get(i) >=76, "Test Failed: " + expectedResult.get(i) + " Doesn't have 3 or more stars");

    }

    @AfterClass
    public void endSession() {
        webDriver.quit();
    }
}
