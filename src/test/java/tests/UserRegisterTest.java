package tests;
import lib.DataGenerator;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import lib.Assertions;
import lib.BaseTestCase;
import org.junit.jupiter.api.Test;
import lib.ApiCoreRequest;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.HashMap;
import java.util.Map;


public class UserRegisterTest extends BaseTestCase {
    private final ApiCoreRequest apiCoreRequest= new ApiCoreRequest();
    @Test
    public void testCreateUserWithExistingEmail(){
        String email = "vinokotov@example.com";

        Map<String,String> userData = new HashMap<>();
        userData.put("email", email);
        userData.put("password","123");
        userData.put("username","learnqa");
        userData.put("firstName","learnqa");
        userData.put("lastName","learnqa");

        Response responseCreateAuth = RestAssured
                .given()
                .body(userData)
                .post("https://playground.learnqa.ru/api/user/")
                .andReturn();
        Assertions.assertResponseCOdeEquals(responseCreateAuth,400);
        Assertions.assertResponseTextEquals(responseCreateAuth,"Users with email '"+ email + "' already exists");
    }

    @Test
    public void testCreateUserSuccesfully(){
        String email = DataGenerator.getRandomEmail();

        Map<String,String> userData = new HashMap<>();
        userData.put("email", email);
        userData.put("password","123");
        userData.put("username","learnqa");
        userData.put("firstName","learnqa");
        userData.put("lastName","learnqa");

        Response responseCreateAuth = RestAssured
                .given()
                .body(userData)
                .post("https://playground.learnqa.ru/api/user/")
                .andReturn();
        Assertions.assertResponseCOdeEquals(responseCreateAuth,200);
        Assertions.assertJsonHasField(responseCreateAuth, "id");
        System.out.println(responseCreateAuth.asString());
    }

    @Test
    public void testCreateUserWithIncorrectEmail(){


        Map<String,String> userData = new HashMap<>();
        userData.put("email", "123.com");
        userData.put("password","123");
        userData.put("username","learnqa");
        userData.put("firstName","learnqa");
        userData.put("lastName","learnqa");

        Response responseCreateUserWithIncorrectMail = apiCoreRequest
                .makePostRequest("https://playground.learnqa.ru/api/user/", userData);

        Assertions.assertResponseTextEquals(responseCreateUserWithIncorrectMail,
                "Invalid email format");
        System.out.println(responseCreateUserWithIncorrectMail.asString());
    }
    @ParameterizedTest
    @CsvSource({
            "email,123@mail.com, password,123, username,learnqa, firstName,learnqa, lastName",
            "email,123@mail.com, password,123, username,learnqa, lastName, learnqa, firstName",
            "email,123@mail.com, password,123, lastName,learnqa, firstName,learnqa, username",
            "email,123@mail.com,  lastName,learnqa, firstName,learnqa, username,learnqa, password",
            " password,123, lastName,learnqa, firstName,learnqa, username,learnqa, email"


    })

    public void testEmptyField(String field1, String value1, String field2,
                               String value2,String field3,
                               String value3, String field4, String value4, String expected) {
        Map<String, String> body = new HashMap<>();

        body.put(field1, value1);
        body.put(field2, value2);
        body.put(field3, value3);
        body.put(field4, value4);

        Response responseCreateUserWithEmptyFields = apiCoreRequest
                .makePostRequest("https://playground.learnqa.ru/api/user/", body);
        //responseCreateUserWithEmptyFields.prettyPrint();
        Assertions.assertResponseTextEquals(responseCreateUserWithEmptyFields,
                "The following required params are missed: " + expected);
    }

        @Test
        public void testCreateUserWithShortName () {


            Map<String, String> userData = new HashMap<>();
            userData.put("email", "123.com");
            userData.put("password", "123");
            userData.put("username", "learnqa");
            userData.put("firstName", "l");
            userData.put("lastName", "learnqa");

            Response requestWthShortName = apiCoreRequest
                    .makePostRequest("https://playground.learnqa.ru/api/user/", userData);
            System.out.println(requestWthShortName.asString());
            Assertions.assertResponseTextEquals(requestWthShortName,"The value of 'firstName' field is too short");
        }

    @Test
    public void testCreateUserWithLongName () {


        Map<String, String> userData = new HashMap<>();
        userData.put("email", "123.com");
        userData.put("password", "123");
        userData.put("username", "learnqa");
        userData.put("firstName", "gggggdhsadhjksahfkjsahfhkjsahfkahsfhdashfjhsjkahfkjsahfhsakhfkhsakjfhjkshafkjhsajkhfhsakfhksafhksahfkjsakfhsajkfhjkhafjhjadkhfjkshajkfhsjakhfjksahfjhsajkfhsahfhsahfshakjfhskjahfjksahfkjhsajkfhsjakhfsahjkfhskajhfjksfhkjashfjksahfhjhhjfsahfhaskfhhashhhh");
        userData.put("lastName", "learnqa");

        Response requestWthLongName = apiCoreRequest
                .makePostRequest("https://playground.learnqa.ru/api/user/", userData);
        System.out.println(requestWthLongName.asString());
        Assertions.assertResponseTextEquals(requestWthLongName,"The value of 'firstName' field is too long");
    }
    }