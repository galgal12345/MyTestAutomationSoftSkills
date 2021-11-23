package lesson20.reqres.Extensions;


import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lesson20.reqres.PageObjects.ReqResPage;
import org.jsoup.nodes.Document;
import org.openqa.selenium.WebDriver;

public class Basics {

    //EXPECTED
    protected static int intExpectedId;

    //WEB
    protected static WebDriver webDriver;

    //REST-API
    protected static Response response;
    protected static RequestSpecification httpRequest;
    protected static String restAssuredURL;

    // Reqres
    protected static ReqResPage reqResPage;
}
