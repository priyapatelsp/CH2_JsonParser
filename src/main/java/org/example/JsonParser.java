package org.example;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;

public class JsonParser {

    /***
     * This method checks if the JSON is a valid JSON or not
     * by returning false on JSON being null or empty string
     * @param userJson
     * @return
     */
    public boolean isValidJson(String userJson , StringBuilder structuredLog){
        if(userJson == null || userJson.isEmpty()){
            System.out.print("This Json is empty :: ");
            return false;
        }
        userJson = userJson.replace("\n", "").replace("\r", "").replace("\t", "").replace(" ", "");

        if(!checkValidJson(userJson)){
            return false;
        }
        if(userJson.startsWith("[") && userJson.endsWith("]")){
            return checkArray(userJson,structuredLog);
        }
        if(userJson.startsWith("{") && userJson.endsWith("}")){
            return checkJson(userJson,structuredLog);
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

    private static boolean checkArray(String userJson,StringBuilder structuredLog)
    {
        userJson = userJson.substring(1, userJson.length() - 1).trim();

        if (userJson.isBlank()) {
            structuredLog.append("[]"); // Empty array
            return true;
        }

        String[] values = userJson.split(",");
        structuredLog.append(formatArrayLog(values));
        return true;
    }

    private static String formatArrayLog(String[] values) {
        StringBuilder formattedLog = new StringBuilder("[");
        for (String value : values) {
            formattedLog.append(value.trim()).append(", ");
        }
        if (formattedLog.length() > 1) {
            formattedLog.setLength(formattedLog.length() - 2);
        }
        formattedLog.append("]");
        return formattedLog.toString();
    }

    public static boolean checkJson(String json, StringBuilder structuredLog) {
        structuredLog.setLength(0);
        json = json.substring(1, json.length() - 1).trim();

        if (json.isBlank()) {
            structuredLog.append("{}"); // Empty object
            return true;
        }

        String[] keyValuePairs = json.split(",");
        Map<String, String> parsedContents = new HashMap<>();

        for (String pair : keyValuePairs) {
            int colonIndex = pair.indexOf(':');
            if (colonIndex == -1) return false;

            String key = pair.substring(0, colonIndex).trim();
            String value = pair.substring(colonIndex + 1).trim();

            if (!(key.startsWith("\"") && key.endsWith("\""))) return false;
            key = key.substring(1, key.length() - 1);

            if (!key.isEmpty())
            {
                parsedContents.put(key, value);
            }
        }

        structuredLog.append(formatLog(parsedContents));
        return true;
    }
    private static String formatLog(Map<String, String> parsedContents) {
        StringBuilder formattedLog = new StringBuilder("{");
        for (Map.Entry<String, String> entry : parsedContents.entrySet()) {
            formattedLog.append("\"").append(entry.getKey()).append("\": \"").append(entry.getValue()).append("\", ");
        }
        if (formattedLog.length() > 1) {
            formattedLog.setLength(formattedLog.length() - 2);
        }
        formattedLog.append("}");
        return formattedLog.toString();
    }


}
