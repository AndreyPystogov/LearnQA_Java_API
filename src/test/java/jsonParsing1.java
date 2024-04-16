import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Test;
import java.util.concurrent.TimeUnit;

public class jsonParsing1 {
    @Test
    public void jsonParsing() throws InterruptedException {
       // Map<String, String> params = new HashMap<>();
      // params.put("token");

        JsonPath response = RestAssured
                //.given()
                //.queryParams(params)
                .get("https://playground.learnqa.ru/ajax/api/longtime_job")
                .jsonPath();

        String token = response.get("token");
        int time = response.get("seconds");

        JsonPath response2 = RestAssured
                .given()
                .queryParams("token",token)
                .get("https://playground.learnqa.ru/ajax/api/longtime_job")
                .jsonPath();
        String status = response2.get("status");
        if (status.equals("Job is NOT ready"))
            System.out.println(status);

        System.out.println("Wait " + time + " seconds");
        Thread.sleep(time*1000);

        JsonPath response3 = RestAssured
                .given()
                .queryParams("token",token)
                .get("https://playground.learnqa.ru/ajax/api/longtime_job")
                .jsonPath();
        String result = response3.get("result");
        status = response3.get("status");


        if (status.equals("Job is ready") && !result.isEmpty() )
            System.out.println("Test passed");
    }
}
