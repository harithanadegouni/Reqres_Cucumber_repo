package Stepdefinitions;

import Pages.Routes;
import io.cucumber.java.en.Given;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;


import static io.restassured.RestAssured.given;

public class APITestingStepDefinitions {
    private static final Logger logger = LogManager.getLogger(APITestingStepDefinitions.class);
    @Given("I check get request response for single user")
    public void I_check_get_request_response(){
        Response response=
                given()
                .when()
                .get(Routes.get_url)
                        .thenReturn();

        int statusCode=response.getStatusCode();
        if(statusCode ==200)
        {
            logger.info("API request Status is OK");
        }

        ResponseBody body= response.getBody();
        String bodyAsString = body.asString();

        Assert.assertEquals(bodyAsString.contains("janet.weaver@reqres.in"),true);
    }

    @Given("I check post request response for create user")
    public void I_check_post_request_response(){
         String payload="{\n" +
                "\"name\":\n" +
                "\"APITestingSampleUser\",\n" +
                "\"job\":\n" +
                "\"leader\"\n" +
                "}";
        String expectedResponseBody="{\n" +
                "    \"name\": \"APITestingSampleUser\",\n" +
                "    \"job\": \"leader\",\n" +
                "    \"id\": \"612\",\n" +
                "    \"createdAt\": \"2023-04-10T05:11:34.199Z\"\n" +
                "}";
        Response response= given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)
                .when()
                .post(Routes.post_url);

        int statusCode=response.getStatusCode();
        if(statusCode ==201)
        {
            logger.info("API request Status Created");
        }
        ResponseBody body= response.getBody();
        String bodyAsString = body.asString();
        Assert.assertEquals(bodyAsString.contains("APITestingSampleUser"),true);
    }
}
