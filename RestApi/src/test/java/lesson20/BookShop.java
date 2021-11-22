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
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

import java.io.IOException;
import java.util.List;

public class BookShop {

    WebDriver webDriver;
    Document doc;//Jsoup
    String baseURL = "http://localhost:8080/";

    float webDriverSumPrices = 0;
    float jsonSumPrices = 0;

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

    @Test(priority = 2, dataProvider = "my-data-provider")
    public void testSumAllTotalPricesWithWebDriver(int i) throws InterruptedException {


        webDriver.get("http://localhost:8080/admin/orders");
        Thread.sleep(3000);
        WebElement dataWidget = webDriver.findElement(By.tagName("tbody"));
        List<WebElement> totalPriceList = dataWidget.findElements(By.cssSelector("td[class$='col-total']"));

        for (WebElement totalPrice : totalPriceList) {
            StringBuilder sb = new StringBuilder(totalPrice.getText());
            sb.deleteCharAt(0);
            System.out.print(Float.parseFloat(sb.toString()) + " , ");
            webDriverSumPrices += Float.parseFloat(sb.toString());
        }

        System.out.println();
        System.out.println("sum of prices with webDriver: " + webDriverSumPrices);
        testSumAllTotalPricesWithJSON(webDriverSumPrices, i);
        webDriver.findElement(By.xpath("//*[@id=\"index_footer\"]/nav/span[@class='page']/a[@rel='next' and text()='" + i + "']")).click();
        Thread.sleep(3000);
    }

    @Step
    public void testSumAllTotalPricesWithJSON(float webDriverSumPrices,int i) {
        response = httpRequest.get("http://localhost:8080/admin/orders.json?order=id_desc&page=" + i);
        JsonPath jp = response.jsonPath();

        List<String> allTotalPrices = jp.getList("total_price");
        for (String totalPrice : allTotalPrices) {
            System.out.print(Float.parseFloat(totalPrice) + " , ");
            jsonSumPrices += Float.parseFloat(totalPrice);
        }
        System.out.println();
        System.out.println("webDriverSumPrices of prices with JSON: " + jsonSumPrices);
        //assertEquals(webDriverSumPrices, webDriverSumPrices, "Step 'testSumAllTotalPricesWithJSON()' Failed");

    }

    @Test(priority = 3)
    @DataProvider(name = "my-data-provider")
    public Object[] summarizeAllPages() {

        return new Object[]{1,2,3,4,5,6,7,8,9,10,
                            11,12,13,14,15,16,17,18,19,20,
                            21,22,23,24,25,26,27,28,29,30};
    }


    @AfterClass
    public void endSession() {
        webDriver.quit();
    }
}
