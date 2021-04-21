package me.shivzee;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class ResponseParser {
    private JSONParser parser;

    public ResponseParser(){
        parser = new JSONParser();
    }

    public String getKeyValue(String key , String jsonString){
        try{
            JSONObject json = (JSONObject) parser.parse(jsonString);
            return json.get(key).toString();
        }
        catch (Exception exception){
            System.out.println("Exception Caught Something went wrong "+exception);
            return "";
        }
    }
}
