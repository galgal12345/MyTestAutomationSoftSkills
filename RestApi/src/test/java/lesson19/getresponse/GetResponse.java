package lesson19.getresponse;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class GetResponse {

    String city = "Haifa ,IL";
    String key = "0d8601326586d881c8f8fc9d73b36de3";
    String url = "http://api.openweathermap.org/data/2.5/weather";

    public static RequestSpecification httpRequest;
    public Response response;

    @BeforeClass
    public void start(){
        RestAssured.baseURI = url;
        httpRequest = RestAssured.given();
    }

    @Test
    public void test1() {
        response = httpRequest.get("?units=metric&q=" + city + "&appid=" + key);
        //System.out.println(response.getBody().asString());
        response.prettyPrint();
    }

}
