package lib;

import io.restassured.response.Response;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class Assertions {

    public static void assertJsonByName(Response Response, String name, int expectedValue) {
    Response.then().assertThat().body("$", hasKey(name));

        int value = Response.jsonPath().getInt(name);
        assertEquals(expectedValue, value,  "JSON value is not equal to expected value");
}
    public static void assertResponseTextEquals(Response Response, String expectedAnswer){
        assertEquals(
                expectedAnswer,
                Response.asString(),
                Response.asString()
        );
    }
    public static void assertResponseCOdeEquals(Response Response, int expectedStatusCode){
        assertEquals(
                expectedStatusCode,
                Response.statusCode(),
                "Response status code is not as expected"
        );
    }
    public static void assertJsonHasField(Response Response, String expectedFieldName) {
        Response.then().assertThat().body("$", hasKey(expectedFieldName));

    }
    public static void assertJsonHasFields(Response Response, String[] expectedFieldNames){
        for (String expectedFieldName : expectedFieldNames){
            assertJsonHasField(Response, expectedFieldName);
        }
    }
    public static void assertJsonHasNotField(Response Response, String unexpectedFieldName){
        Response.then().assertThat().body("$", not(hasKey(unexpectedFieldName)));
    }
    public static void assertJsonHasOnlyField(Response Response, String expectedFieldName) {
        // Проверяем, что присутствует только одно поле и это поле совпадает с ожидаемым
        Response.then().assertThat()
                .body("$", hasKey(expectedFieldName));


    }
     }