package lesson19.getresponse;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static org.testng.Assert.*;


public class RestAssured {

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
    public void test1() {
        response = httpRequest.get("?units=metric&q=" + city + "&appid=" + key);
        response.prettyPrint();
        System.out.println(response.statusLine());
        System.out.println(response.time());
        assertTrue(response.getContentType().contains("json"));
        assertEquals(response.getStatusCode(), 200);

    }
}
