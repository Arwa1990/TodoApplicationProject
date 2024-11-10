package com.qacart.todo.utils;

import io.qameta.allure.Step;

import java.io.*;
import java.util.Properties;

public class PropertiesUtils {

    @Step

    public static Properties loadProperties (String filePath) {

        File myFile = new File(filePath);
        InputStream inputStream = null;
        try {

            inputStream = new FileInputStream(myFile);
            Properties myProperties = new Properties();
            myProperties.load(inputStream);
            inputStream.close();
            return myProperties;

        }  catch (FileNotFoundException e) {
            throw new RuntimeException("File is not found");
        }catch (IOException e) {
            throw new RuntimeException("Error while loading properties");

        }


    }
}
