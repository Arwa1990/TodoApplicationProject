package com.qacart.todo.testcases;

import com.qacart.todo.api.RegisterApi;
import com.qacart.todo.api.TaskApi;
import com.qacart.todo.base.BaseTest;
import com.qacart.todo.config.EndPoint;
import com.qacart.todo.factory.DriverFactory;
import com.qacart.todo.pages.LoginPage;
import com.qacart.todo.utils.CookieUtils;
import com.qacart.todo.utils.PropertiesUtils;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.http.Cookie;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.Properties;


@Feature("Login Feature")

public class LoginTest extends BaseTest {


    @Story("Testing Add new Task")
    @Test(description = "Ya HALAAAAAAAAA")
    public void ShouldBeAbleToLoginWithNameAndPassword  (){



       getDriver().get("https://qacart-todo.herokuapp.com/");

        RegisterApi registerapi = new RegisterApi();
        registerapi.register();

        List<Cookie> restAsurredCookie = registerapi.getRestAssuredCookies();
        List<org.openqa.selenium.Cookie> seCookies = CookieUtils.convertRestAssuredCookiesToSeleniumCookies(restAsurredCookie);

        for( org.openqa.selenium.Cookie cookie : seCookies ){
            getDriver().manage().addCookie(cookie);
        }

        getDriver().get("https://qacart-todo.herokuapp.com"+ EndPoint.TODO_API_ENDPOINT);

        TaskApi taskApi = new TaskApi();

        taskApi.AddTask(registerapi.getAccessToken());

       // getDriver().get("https://qacart-todo.herokuapp.com"+ EndPoint.TASK_API_ENDPOINT);

       String actualResult = taskApi.getItem();
        System.out.println(actualResult);

        getDriver().get("https://qacart-todo.herokuapp.com"+ EndPoint.TODO_API_ENDPOINT);

        Assert.assertEquals(actualResult,"learn SELENIUM");

        //LoginPage loginPage = new LoginPage(getDriver());
       // loginPage.login("arwa.n.shaqour@gmail.com","Arw@1234");

       // Boolean isWelcomeDisplayed = getDriver().findElement(By.cssSelector("[data-testid=\"welcome\"]")).isDisplayed();
       // Assert.assertTrue(isWelcomeDisplayed);


    }


}
