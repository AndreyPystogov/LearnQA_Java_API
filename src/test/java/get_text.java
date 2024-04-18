import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class get_text {
    @Test
    public void getTextt(){
        Response response = RestAssured
                .get("https://playground.learnqa.ru/api/homework_cookie")
                .andReturn();

        Map<String,String> cookies = response.getCookies();
        System.out.println(cookies);
        assertTrue(cookies.containsKey("HomeWork"), "Something wrong with cookie key");
        assertTrue(cookies.containsValue("hw_value"), "Something wrong with cookie value");


    }
}
