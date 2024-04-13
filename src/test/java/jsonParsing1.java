import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Test;
import java.util.Arrays;

import java.util.HashMap;
import java.util.Map;

public class jsonParsing1 {
    @Test
    public void jsonParsing(){
       // Map<String, String> params = new HashMap<>();
       // params.put("timestamp");

        JsonPath response = RestAssured
                .given()
                //.queryParams(params)
                .get("https://playground.learnqa.ru/api/get_json_homework")
                .jsonPath();

        String answer = response.get("messages[1].message");
        System.out.println(answer);

    }
}
