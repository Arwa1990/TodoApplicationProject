package com.qacart.todo.base;

import com.qacart.todo.factory.DriverFactory;
import groovyjarjarantlr4.v4.codegen.model.OutputFile;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;


public class BaseTest {

    protected ThreadLocal <WebDriver> driver = new ThreadLocal<>();

    public WebDriver getDriver() {
        return this.driver.get();
    }

    public  void setDriver( WebDriver driver) {
        this.driver.set(driver);
    }


    @BeforeMethod
    public void setup(){

        WebDriver driver = new DriverFactory().intializeDriver();
        setDriver(driver);
    }

    @Step
    @AfterMethod
    public void teardown(ITestResult result){

        String testcaseName = result.getMethod().getMethodName();
        File destfile = new File("target"+File.separator+"screenshots"+File.separator+testcaseName+".png");
        takeScreenshot(destfile);
        getDriver().quit();
    }

    public void takeScreenshot(File destfile){

        File file = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(file, destfile);
            InputStream is = new FileInputStream(destfile);
            Allure.addAttachment("screenshot",is);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
