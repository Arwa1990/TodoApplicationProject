package com.qacart.todo.utils;

import io.qameta.allure.Step;
import io.restassured.http.Cookie;

import java.util.ArrayList;
import java.util.List;

public class CookieUtils {

    @Step
    public static List<org.openqa.selenium.Cookie> convertRestAssuredCookiesToSeleniumCookies(List<Cookie> restAsurredCookie){


       List<org.openqa.selenium.Cookie> seleniumCookie = new ArrayList<>();

       for(io.restassured.http.Cookie cookie : restAsurredCookie){

           org.openqa.selenium.Cookie SeCookie = new org.openqa.selenium.Cookie(cookie.getName(), cookie.getValue()) ;
           seleniumCookie.add(SeCookie);

       }

   return seleniumCookie;

    }
}
