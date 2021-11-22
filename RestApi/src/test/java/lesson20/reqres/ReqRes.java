package lesson20.reqres;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.jsoup.nodes.Document;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

import java.util.concurrent.TimeUnit;

public class ReqRes {

    int intExpectedId = 0 ;

    WebDriver webDriver;
    Document doc;//Jsoup
    String baseURL = "https://reqres.in/api/";

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
        webDriver.get("https://reqres.in/");
    }
    @Test(priority = 1)
    public void test1(){

        webDriver.findElement(By.xpath("//*[@id=\"console\"]/div[2]/div[1]/p/strong/a/span")).click();
        response = httpRequest.get("https://reqres.in/api/users?page=1");
        response.prettyPrint();
        System.out.println(response.time());//1466
        System.out.println(response.timeIn(TimeUnit.MILLISECONDS));//1466
        System.out.println(response.getContentType());//application/json; charset=utf-8
        System.out.println(response.getStatusCode());//200
        System.out.println(response.getHeaders().asList());
        System.out.println(response.getHeader("Date"));

    }

    @Test(priority = 2,dataProvider = "index-page-data")
    public void test2(int indexPage, int indexData){

        intExpectedId++;
        String myStrExpectedId= "" + intExpectedId;
        response = httpRequest.get("https://reqres.in/api/users?page=" + indexPage);
        JsonPath jsonPath = response.jsonPath();
        System.out.println(jsonPath.get("data["+indexData+"].id").toString());
        assertEquals(jsonPath.get("data["+indexData+"].id").toString(), myStrExpectedId);
    }

    @Test(priority = 2)
    @DataProvider(name = "index-page-data")
    public Object[][] test3(){
        return new Object[][]{{1,0},{2,1}};
    }



    @AfterClass
    public void endSession() {
        webDriver.quit();
    }
}
