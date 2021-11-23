import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.jsoup.nodes.Document;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import javax.tools.Tool;
import java.util.concurrent.TimeUnit;

@Listeners(Tools.Listeners.class)
public class Flow {

    //Jsoup
    Document doc;

    //WEB
    WebDriver webDriver;

    //API/JAR
    String baseURL = "https://reqres.in/api/";
    public static Response response;
    public static RequestSpecification httpRequest;

    @BeforeClass
    public void startSession() {

        //WEB
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.get("https://reqres.in/");

        //API/JAR
        RestAssured.baseURI = baseURL;
        httpRequest = RestAssured.given();
        httpRequest.header("Content-Type", "application/json");

    }

    @Test(priority = 1, description = "short fill it")
    @Description("long fill it")
    public void test1(){

    }
    @Test(priority = 2, description = "short fill it")
    @Description("long fill it")
    public void test2(){

    }
    @Test(priority = 3, description = "short fill it")
    @Description("long fill it")
    public void test3(){

    }
    @Test(priority = 4, description = "short fill it")
    @Description("long fill it")
    public void test4(){

    }

    @AfterClass
    public void endSession() {
        webDriver.quit();
    }
}
