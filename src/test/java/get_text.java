import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class get_text {
    @Test
    public void getTextt(){
        Response response = RestAssured
                .get("https://playground.learnqa.ru/ajax/api/check_auth_cookie")
                .andReturn();
        System.out.println(response.asString().length());
        assertTrue(response.asString().length() >15, "Message has less than 15 symbols");


    }
}
