package com.qacart.todo.api;

import com.qacart.todo.objects.User;
import com.qacart.todo.utils.UserUtils;
import io.qameta.allure.Step;
import io.restassured.http.Cookies;
import io.restassured.http.Cookie;
import io.restassured.response.Response;

import java.util.List;

import static io.restassured.RestAssured.given;

public class RegisterApi {

    private List <Cookie> restAssuredCookies;
    private String accessToken;



    private String userID;

    private String firstName;

    public List <Cookie> getRestAssuredCookies() {
        return this.restAssuredCookies;
    }


    public String getAccessToken() {
        return this.accessToken;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getUserID() {
        return this.userID;
    }


    @Step
    public void register (){

        User user = new UserUtils().generateRandomUser();


        Response response = given()
                .baseUri("https://qacart-todo.herokuapp.com")
                .header("Content-Type","application/json")
                .body(user)
                .when()
                .post("/api/v1/users/register")
                .then()
                .extract().response();

        restAssuredCookies = response.detailedCookies().asList();
        accessToken = response.path("access_token");
        firstName = response.path("firstName");
        userID = response.path("userID");
        System.out.println(accessToken);

    }

}
