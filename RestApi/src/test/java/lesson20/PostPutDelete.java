package lesson20;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class PostPutDelete {

    String baseURL = "http://localhost:9000/";

    public static RequestSpecification request;
    public static Response response;

    @BeforeClass
    public void start() {
        RestAssured.baseURI = baseURL;
        request = RestAssured.given();
        request.header("Content-Type", "application/json");
    }

    @Test(priority = 1)
    public void testPost() {
        JSONObject params = new JSONObject();
        params.put("firstName", "gil");
        params.put("lastName", "almuly");
        params.put("email", "almuly.gil212@gmail.com");
        params.put("programme", "Engineering");

        request.body(params.toJSONString());
        response = request.post("/student");

        System.out.println(response.getBody().asString());
        assertEquals(response.getStatusCode(), 201);
    }

    @Test(priority = 2)
    public void testNewStudent() {

        String message;
        JSONObject item = new JSONObject();
        item.put("firstName", "noam");
        item.put("lastName", "almuly");
        item.put("email", "almuly.gil212@gmail.com");
        item.put("programme", "Engineering");

        JSONArray array = new JSONArray();
        array.add("Python Course");
        array.add("CSharp Course");
        array.add("Java Course");

        item.put("courses", array);

        message = item.toString();
        System.out.println(message);

        request.body(item.toJSONString());
        response = request.post("/student");

        System.out.println(response.getBody().asString());
        assertEquals(response.getStatusCode(), 201);
    }

    @Test(priority = 3)
    public void testPut() {
        JSONObject params = new JSONObject();
        params.put("firstName", "noam");
        params.put("lastName", "almuly");
        params.put("email", "almuly.gil212@gmail.com");
        params.put("programme", "Electricity");

        request.body(params.toJSONString());
        response = request.put("/student/98");

        System.out.println(response.getBody().asString());
        assertEquals(response.getStatusCode(), 200);
    }

    @Test(priority = 4)
    public void testDelete() {
        JSONObject params = new JSONObject();
        params.put("firstName", "noam");
        params.put("lastName", "almuly");
        params.put("email", "almuly.gil212@gmail.com");
        params.put("programme", "Electricity");

        request.body(params.toJSONString());
        response = request.delete("/student/98");

        System.out.println(response.getBody().asString());
        assertEquals(response.getStatusCode(), 204);
    }


}
