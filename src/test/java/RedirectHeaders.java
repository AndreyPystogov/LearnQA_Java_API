import io.restassured.RestAssured;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class RedirectHeaders {
    int StatusCode = 300;
    int counter =0;
    String url = "https://playground.learnqa.ru/api/long_redirect";

    @Test
    public void redirectHeaders() {
        do {
            Map<String, String> params = new HashMap<>();

            Response response = RestAssured
                    .given()
                    .redirects()
                    .follow(false)
                    .get(url)
                    .andReturn();

            int StatusCode = response.getStatusCode();
            String responseHeader = response.getHeader("Location");
            System.out.println(responseHeader);
            System.out.println(StatusCode);
            url = responseHeader;
            counter ++;
            System.out.println(counter);
        }
        while (StatusCode != 200);

    }

}

