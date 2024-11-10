package com.qacart.todo.testcases;

import com.qacart.todo.api.RegisterApi;
import com.qacart.todo.api.TaskApi;
import com.qacart.todo.base.BaseTest;
import com.qacart.todo.config.EndPoint;
import com.qacart.todo.factory.DriverFactory;
import com.qacart.todo.pages.LoginPage;
import com.qacart.todo.utils.CookieUtils;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import io.restassured.http.Cookie;
import org.apache.commons.lang3.ObjectUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

@Feature("Todo Feature")

public class TodoTest extends BaseTest {


    @Story("Testing Adding task correctly")
    @Test(enabled = false)
    public void ShouldBeAbleToAddNewTodo() {

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


       // LoginPage loginPage = new LoginPage(getDriver());
     //   loginPage.login("arwa.n.shaqour@gmail.com","Arw@1234");

       // getDriver().findElement(By.cssSelector("[data-testid=\"add\"]")).click();
      //  getDriver().findElement(By.cssSelector("[data-testid=\"new-todo\"]")).sendKeys("Learn Selenium");
      //  getDriver().findElement(By.cssSelector("[data-testid=\"submit-newTask\"]")).click();
      //  String ActualResult = getDriver().findElement(By.cssSelector("[data-testid=\"todo-item\"]")).getText();
        String ActualResult = taskApi.getItem();
        Assert.assertEquals(ActualResult,"Learn Selenium");

    }


    @Story("Testing delete button")
    @Description("It will delete to do we used API Call")
    @Step
    @Test(description = "Ya MARHABA")
    public void ShouldBeAbleToDeleteTodo() {

        getDriver().get("https://qacart-todo.herokuapp.com/");

        RegisterApi registerapi = new RegisterApi();
        registerapi.register();

        List<Cookie> restAsurredCookie = registerapi.getRestAssuredCookies();
        List<org.openqa.selenium.Cookie> seCookies = CookieUtils.convertRestAssuredCookiesToSeleniumCookies(restAsurredCookie);

        for( org.openqa.selenium.Cookie cookie : seCookies ){
            getDriver().manage().addCookie(cookie);
        }

        TaskApi taskApi = new TaskApi();
        taskApi.AddTask(registerapi.getAccessToken());

        getDriver().get("https://qacart-todo.herokuapp.com"+ EndPoint.TODO_API_ENDPOINT);

        getDriver().findElement(By.cssSelector("[data-testid=\"delete\"]")).click();
        Boolean ActualResult = getDriver().findElement(By.cssSelector("[data-testid=\"no-todos\"]")).isDisplayed();

        Assert.assertTrue(ActualResult);

    }



}
