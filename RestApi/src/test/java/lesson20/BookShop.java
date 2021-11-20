package lesson20;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static org.testng.Assert.*;
import java.io.IOException;
import java.util.List;

public class BookShop {

    WebDriver webDriver;
    Document doc;//Jsoup
    String baseURL = "http://localhost:8080/";

    public static RequestSpecification httpRequest;
    public static Response response;

    @BeforeClass
    public void start() {

        RestAssured.baseURI = baseURL;
        httpRequest = RestAssured.given();
        httpRequest.header("Content-Type", "application/json");

        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
    }

    @Test(priority = 1)
    public void testVerifyThirtyThreeBooks() throws IOException {

        doc = Jsoup.connect("http://localhost:8080").get();
        List<Element> products = doc.getElementsByAttribute("class");
        System.out.println(products.size());
        assertEquals(products.size(), 33);
    }
    @Test(priority = 2)
    public void testSumAllTotalPricesWithWebDriver() throws InterruptedException {

        float sumPrices  = 0;
        webDriver.get("http://localhost:8080/admin/orders");
        Thread.sleep(3000);
        WebElement dataWidget = webDriver.findElement(By.tagName("tbody"));
        List<WebElement> totalPriceList = dataWidget.findElements(By.cssSelector("td[class$='col-total']"));
        for (WebElement totalPrice : totalPriceList){
            StringBuilder sb = new StringBuilder(totalPrice.getText());
            sb.deleteCharAt(0);
            System.out.print(Float.parseFloat(sb.toString()) +  " , ");
            sumPrices += Float.parseFloat(sb.toString());
        }
        System.out.println();
        System.out.println("sum of prices with webDriver: " + sumPrices);
        testSumAllTotalPricesWithJSON(sumPrices);
    }
    @Step
    public void testSumAllTotalPricesWithJSON(float webDriverSumPrices){
        response = httpRequest.get("http://localhost:8080/admin/orders.json");
        JsonPath jp = response.jsonPath();
        //jp.prettyPrint();
        float sumPrices = 0;
        List<String> allTotalPrices = jp.getList("total_price");
        for(String totalPrice : allTotalPrices){
            System.out.print(Float.parseFloat(totalPrice) +  " , ");
            sumPrices += Float.parseFloat(totalPrice);
        }
        System.out.println();
        System.out.println("sumPrices of prices with JSON: " + sumPrices);
        assertEquals(sumPrices, webDriverSumPrices, "Step 'testSumAllTotalPricesWithJSON()' Failed");

    }//todo: there is a problem with the summarize with JSON and webDriver: they are not Equal
    @AfterClass
    public void endSession() {
        webDriver.quit();
    }
}
