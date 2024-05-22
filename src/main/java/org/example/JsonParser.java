package org.example;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonParser {

    /***
     * This method checks if the JSON is a valid JSON or not
     * by returning false on JSON being null or empty string
     * @param userJson
     * @return
     */
    public boolean isValidJson(String userJson){
        if(userJson == null || userJson.isEmpty()){
            System.out.print("This Json is empty :: ");
            return false;
        }
        userJson = userJson.replace("\n", "").replace("\r", "").replace("\t", "").replace(" ", "");

        if(!checkValidJson(userJson)){
            return false;
        }
        return true;
    }
    private boolean checkValidJson(String userJson) {

        try {

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(userJson);
            if(jsonNode.isNull())return false;

        } catch (JsonParseException e) {

            System.out.println("Invalid JSON: " + e.getMessage());
            return false;

        } catch (Exception e) {

            System.out.println("Error: " + e.getMessage());
            return false;

        }

return true;
    }
}
