package com.qacart.todo.utils;

import java.util.Properties;

public class ConfigUtils {


    private Properties properties;
    private static ConfigUtils configUtils;

    private ConfigUtils (){
        String env = System.getProperty("env","production");
        switch(env){
            case "PRODUCTION":
                properties = PropertiesUtils.loadProperties("C:\\Users\\arwash\\IdeaProjects\\TodoApplicationProject\\src\\test\\java\\com\\qacart\\todo\\config\\production.properties");
                break;

            case "LOCAL":
                properties = PropertiesUtils.loadProperties("C:\\Users\\arwash\\IdeaProjects\\TodoApplicationProject\\src\\test\\java\\com\\qacart\\todo\\config\\production.properties");
                break;

            default:
                throw new RuntimeException("Environment is not SUPPORTED !");
        }

    }

    public static ConfigUtils getInstance(){

        if(configUtils == null){
            configUtils = new ConfigUtils();
        }
        return configUtils ;

    }

    public String getBaseUrl(){

        return properties.getProperty("baseUrl");
    }

    public String getEmail(){

        return properties.getProperty("email");
    }

    public String getPassword(){

        return properties.getProperty("password");
    }

}
