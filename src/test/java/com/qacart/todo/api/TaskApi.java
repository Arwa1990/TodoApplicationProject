package com.qacart.todo.api;

import com.qacart.todo.config.EndPoint;
import com.qacart.todo.objects.Task;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class TaskApi {

    private String item;
    public String getItem() {
        return this.item;
    }

    @Step
    public void AddTask(String token){

        Task task = new Task("learn SELENIUM",false);

        Response response = given()
                .baseUri("https://qacart-todo.herokuapp.com")
                .header("Content-Type","application/json")
                .body(task)
                .auth().oauth2(token)
        .when()
                .post(EndPoint.TASK_API_ENDPOINT)
        .then()
                .log().all()
                .extract().response();


         item = response.path("item");



        if (response.statusCode()!=201){
            throw new RuntimeException("Sth went wrong");
        }



    }

}
