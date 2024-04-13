import io.restassured.RestAssured;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class RedirectHeaders {
    @Test
    public void redirectHeaders(){
        Map<String, String> params = new HashMap<>();

        Response response = RestAssured
                .given()
                .redirects()
                .follow(false)
                .get("https://playground.learnqa.ru/api/long_redirect")
                .andReturn();


        String responseHeader = response.getHeader("Location");
        System.out.println(responseHeader);


    }
}

