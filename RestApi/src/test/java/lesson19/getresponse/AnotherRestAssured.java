package lesson19.getresponse;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Step;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class AnotherRestAssured {

    WebDriver webDriver;

    String city = "Jerusalem ,IL";
    String key = "0d8601326586d881c8f8fc9d73b36de3";
    String url = "http://api.openweathermap.org/data/2.5/weather";


    public static RequestSpecification httpRequest;
    public Response response;

    @BeforeClass
    public void start(){
        io.restassured.RestAssured.baseURI = url;
        httpRequest = io.restassured.RestAssured.given();

    }

    @Test
    public void test1() throws InterruptedException {
        response = httpRequest.get("?units=metric&q=" + city + "&appid=" + key);
        JsonPath jsonPath = response.jsonPath();
        System.out.println(jsonPath.get("sys.country").toString());
        assertEquals(jsonPath.get("sys.country"), "IL");
        String humidity = jsonPath.getString("main.humidity");
        test2(humidity);
    }

    @Step
    public void test2(String humidity) throws InterruptedException {

        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        webDriver.get("https://openweathermap.org/");

        webDriver.findElement(By.xpath("//*[@id='weather-widget']/div[1]/div/div/div[2]/div[1]/div/input")).sendKeys("Jerusalem ,IL");
        Thread.sleep(5000);
        webDriver.findElement(By.xpath("//*[@id='weather-widget']/div[1]/div/div/div[2]/div[1]/button")).click();
        Thread.sleep(5000);
        String testTwoHumidity = webDriver.findElement(By.xpath("//*[@id='weather-widget']/div[2]/div[1]/div[1]/div[2]/ul/li[3]")).getText();
        ////*[@id="weather-widget"]/div[2]/div[1]/div[1]/div[2]/ul/li[3]
        ////*[@id="weather-widget"]/div[2]/div[1]/div[1]/div[2]/div[2]/ul/li[3]/text()
        //מוסר השכל תבדוק אם הנתונים תואמים זה לא בהכרח המסלול אולי מדי פעם זה קשור ל אסרט
        System.out.println(humidity);
        System.out.println(testTwoHumidity);

        assertEquals(testTwoHumidity, humidity);
        webDriver.quit();
    }



}
