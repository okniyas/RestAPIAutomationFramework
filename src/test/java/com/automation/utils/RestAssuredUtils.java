package com.automation.utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class RestAssuredUtils {

    static RequestSpecification requestSpecification = RestAssured.given();
    static String endPoint;
    static Response response;

    public static void setEndPoint(String endPoint){
        RestAssuredUtils.endPoint = endPoint;

    }

    public static Response get(){
        response = requestSpecification.get(endPoint);
        return response;
    }

    public static Response post(){
        requestSpecification.log().all(); // just for logging purpose
        response = requestSpecification.post(endPoint);
//        response.then().log().all();

        return response;
    }

    public static Response put(){

        requestSpecification.log().all(); // just for logging purpose
        response = requestSpecification.put(endPoint);
//        response.then().log().all();
        return response;
    }

    public static void setHeader(String key, String value){
        requestSpecification.header(key, value);
    }

    public static void setBody(String fileName){
        String jsonFolderPath = ConfigReader.getConfigValue("json.folder.path");
        requestSpecification.body(getDataFromFile(jsonFolderPath + fileName));
    }

    public static int getStatusCode(){
        return response.getStatusCode();
    }

    public static String getDataFromFile(String filePath){
        String content = null;
        try {
            content = new Scanner(new FileInputStream(filePath)).useDelimiter("//Z").next();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return content;

    }

    public static String getResponseValue(String value) {
        System.out.println("===========>>"+ response.jsonPath().getString(value));
        return response.jsonPath().getString(value);
    }
}
