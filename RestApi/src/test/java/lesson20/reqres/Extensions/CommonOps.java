package lesson20.reqres.Extensions;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import lesson20.reqres.PageObjects.ReqResPage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;

public class CommonOps extends Basics {


    @BeforeClass
    public void startSession() {
        restAssuredURL = "https://reqres.in/";
        intExpectedId = 0;
        MyApiStarter();
        MyWebStarter(restAssuredURL);
        reqResPage = PageFactory.initElements(webDriver, ReqResPage.class);

    }

    @Step("Get 1 Person")
    public static void MyApiStarter() {

        RestAssured.baseURI = restAssuredURL;
        httpRequest = RestAssured.given();
        httpRequest.header("Content-Type", "application/json");

    }

    @Step("Get 1 Person")
    public static void MyWebStarter(String webDriverURL) {
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        webDriver.get(webDriverURL);
        webDriver.manage().window().maximize();

    }

    @Step("Get 1 Person")
    public static void basicsResponseOperations(String getHttpRequest, TimeUnit timeUnit, String getHeader) {

        response = httpRequest.get(getHttpRequest);
        response.prettyPrint();
        System.out.println(response.time());//1466
        System.out.println(response.timeIn(timeUnit));//1466
        System.out.println(response.getContentType());//application/json; charset=utf-8
        System.out.println(response.getStatusCode());//200
        System.out.println(response.getHeaders().asList());
        System.out.println(response.getHeader(getHeader));
    }

    @Step("Get 1 Person")
    public static void checkIdsWithDataProvider(int indexData, String whichPage) {

        intExpectedId++;
        String myStrExpectedId = "" + intExpectedId;
        response = httpRequest.get("api/users?page=" + whichPage);
        JsonPath jsonPath = response.getBody().jsonPath();
        assertEquals(jsonPath.get("data[" + indexData + "]['id']").toString(), myStrExpectedId);

    }

    @Step("Get 1 Person")
    public static void getOnePerson(String theNumOfPersonOnJson) {
        response = httpRequest.get("api/users/" + theNumOfPersonOnJson);
        response.prettyPrint();
    }

    @Step("Add 1 Person")
    public static void postNewPerson( String myFname, String myLname) {

        JSONObject params = new JSONObject();
        params.put("name", myFname);
        params.put("job", myLname);

        httpRequest.body(params.toJSONString());
        response = httpRequest.post("api/users");

        response.prettyPrint();
        assertEquals(response.getStatusCode(), 201);

    }

    @Step("asdgdsfh")
    public void updatePerson(String hisName, String hisJob){

        JSONObject item = new JSONObject();
        item.put("name", hisName);
        item.put("job", hisJob);

        httpRequest.body(item.toJSONString());
        response = httpRequest.put("/api/users/2");

        response.prettyPrint();
        assertEquals(response.getStatusCode(), 200);
    }

    @Step("asdfqwegh")
    public void deletePerson(){

        System.out.println("Before Delete");
        response = httpRequest.get("/api/users/2");
        response.prettyPrint();
        response = httpRequest.delete("/api/users/2");
        System.out.println("After Delete");
        response.prettyPrint();
        assertEquals(response.getStatusCode(), 204);
    }

    @DataProvider(name = "index-page-data")
    public Object[] MyDataProvider() {
        return new Object[]{0, 1, 2, 3, 4, 5};
    }

    @AfterClass
    public void endSession() {
        if (webDriver != null)
            webDriver.quit();
    }
}
