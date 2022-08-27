package com.envision.automation.framework.reusables;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class JsonUtils {

    JSONObject object;

    public JSONObject loadTestDataJson() throws IOException, ParseException {
        File jsonFile = new File(System.getProperty("user.dir")+"/src/test/resources/testData/test-data.json");
        JSONParser jsonParser = new JSONParser();
        FileReader fr = new FileReader(jsonFile);
        this.object =(JSONObject) jsonParser.parse(fr);
        return (JSONObject)object;
    }

    public Object getJsonObject(String dataName){
        JSONObject jsonObject = (JSONObject) this.object.get(dataName);
        return jsonObject;
    }

    public String getJsonValue(JSONObject dataObject,String key){
       String value = dataObject.get(key).toString();
       return value;
    }

    public static void main(String[] args) throws IOException, ParseException {
        JsonUtils tdg =new JsonUtils();
        tdg.loadTestDataJson();
        JSONObject loginData = (JSONObject) tdg.getJsonObject("loginData");
        String value =tdg.getJsonValue(loginData,"password");
        System.out.println(value);
    }
}
