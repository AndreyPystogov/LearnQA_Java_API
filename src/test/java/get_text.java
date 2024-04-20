import io.restassured.RestAssured;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class get_text {
    @Test
    public void getTextt(){
        Response response = RestAssured
                .get("https://playground.learnqa.ru/api/homework_header")
                .andReturn();

    Headers headers = response.getHeaders();

        assertTrue(headers.hasHeaderWithName("x-secret-homework-header"),
                "Some secret value");



    }
}
