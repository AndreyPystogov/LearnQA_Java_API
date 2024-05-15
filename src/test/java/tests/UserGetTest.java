package tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import lib.Assertions;
import lib.BaseTestCase;
import org.junit.jupiter.api.Test;
import lib.ApiCoreRequest;
import java.util.HashMap;
import java.util.Map;

public class UserGetTest extends BaseTestCase {
    private final ApiCoreRequest apiCoreRequest= new ApiCoreRequest();
    @Test

    public void testGetUserDataNotAuth(){
        Response responseUserData = RestAssured
                .get("https://playground.learnqa.ru/api/user/2")
                .andReturn();

       Assertions.assertJsonHasField(responseUserData,"username");
       Assertions.assertJsonHasNotField(responseUserData,"lastname");
       Assertions.assertJsonHasNotField(responseUserData,"firstname");
       Assertions.assertJsonHasNotField(responseUserData,"email");
    }
    @Test
    public void testGetUserDetailIsAuthAsSameUser() {
        Map<String,String> authData = new HashMap<>();
        authData.put("email", "vinkotov@example.com");
        authData.put("password","1234");


        Response responseGetAuth = apiCoreRequest
                .makePostRequest("https://playground.learnqa.ru/api/user/login", authData);


        String header = this.getHeader(responseGetAuth, "x-csrf-token");
        String cookie = this.getCookie(responseGetAuth,"auth_sid");

        Response responseUserData = apiCoreRequest
                .makeGetRequest("https://playground.learnqa.ru/api/user/2",header,cookie);

        String[] expectedFields =  {"firstName","username", "email", "lastName","id"};

       Assertions.assertJsonHasFields(responseUserData, expectedFields);


    }

    @Test
    public void testGetAnotherDataUser() {
        Map<String,String> authData = new HashMap<>();
        authData.put("email", "vinkotov@example.com");
        authData.put("password","1234");


        Response responseGetAuthData = apiCoreRequest
                .makePostRequest("https://playground.learnqa.ru/api/user/login", authData);


        String header = this.getHeader(responseGetAuthData, "x-csrf-token");
        String cookie = this.getCookie(responseGetAuthData,"auth_sid");

        Response responseUserData = apiCoreRequest
                .makeGetRequest("https://playground.learnqa.ru/api/user/1",header,cookie);

        Assertions.assertJsonHasOnlyField(responseUserData, "username");
        responseUserData.prettyPrint();




    }
}
