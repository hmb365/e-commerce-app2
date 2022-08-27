package com.envision.automation.application.testDataManager;

import com.automation.framework.reusables.JsonUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class TestJsonGenerator {

    public static String getDataForLogin(String key) {
        String value = null;
        try {
            JsonUtils tdg = new JsonUtils();
            tdg.loadTestDataJson();
            JSONObject loginData = (JSONObject) tdg.getJsonObject("loginData");
            value =tdg.getJsonValue(loginData,key);
        }catch(Exception e){

        }
        return value;
    }

    public static String getDataForRegistration(String key)  {
        String value = null;
        try {
            JsonUtils tdg = new JsonUtils();
            tdg.loadTestDataJson();
            JSONObject loginData = (JSONObject) tdg.getJsonObject("registrationData");
            value =tdg.getJsonValue(loginData,key);
        }catch(Exception e){

        }
        return value;
    }


}
