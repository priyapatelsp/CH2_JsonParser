package org.example;

public class JsonParser {

    /***
     * This method checks if the JSON is a valid JSON or not
     * by returning false on JSON being null or empty string
     * @param userJson
     * @return
     */
    public boolean isValidJson(String userJson){
        if(userJson == null || userJson.isEmpty()){
            return false;
        }
        return true;
    }
}
