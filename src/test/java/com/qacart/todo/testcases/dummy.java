package com.qacart.todo.testcases;


import com.qacart.todo.api.RegisterApi;
import com.qacart.todo.api.TaskApi;
import com.qacart.todo.base.BasePage;
import com.qacart.todo.base.BaseTest;
import com.qacart.todo.factory.DriverFactory;
import com.qacart.todo.utils.ConfigUtils;
import com.qacart.todo.utils.CookieUtils;
import io.restassured.http.Cookie;
import io.restassured.http.Cookies;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import java.util.List;


public class dummy extends BaseTest {



    public void main(String[] args) {



       getDriver().get("https://qacart-todo.herokuapp.com/");

        RegisterApi registerapi = new RegisterApi();
        registerapi.register();

        List<Cookie> restAsurredCookie = registerapi.getRestAssuredCookies();


        List<org.openqa.selenium.Cookie> seCookies = CookieUtils.convertRestAssuredCookiesToSeleniumCookies(restAsurredCookie);



        for( org.openqa.selenium.Cookie cookie : seCookies ){

            getDriver().manage().addCookie(cookie);
        }

        getDriver().get("https://qacart-todo.herokuapp.com/todo");


        String test1=  registerapi.getAccessToken();
        String test2=  registerapi.getFirstName();
        String test3=  registerapi.getUserID();


    }
}
