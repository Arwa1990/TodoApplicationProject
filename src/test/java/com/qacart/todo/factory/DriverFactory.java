package com.qacart.todo.factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.decorators.WebDriverDecorator;

import java.time.Duration;

public class DriverFactory {




// jhsfoADHF
    public WebDriver intializeDriver (){

        String browser = System.getProperty("browser","CHROME");
        WebDriver driver;


        switch(browser){

            case "CHROME" :
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;

            case "FIREFOX":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;

            case "SAFARI" :
                driver = new SafariDriver();
                break;

            default :
                throw new RuntimeException("The browser is not supported");
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.manage().window().maximize();
        return driver;
    }

}
