package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        System.out.println("::::   Welcome to JSON Parser   ::::");
        while(true){
            System.out.println("Please give the path(absoulte path) of the your file");
            Scanner sc=new Scanner(System.in);
            String userInput=sc.nextLine();

            // if the user want to exit
            if(userInput.equalsIgnoreCase("exit")){
                break;
            }

            try{

                // reading file from given path
                BufferedReader reader = new BufferedReader(new FileReader(userInput));
                String nextLine;
                String userJson="";
                while ((nextLine = reader.readLine()) != null){
                    System.out.println(nextLine);
                    userJson+=nextLine;
                }
                reader.close();

                JsonParser jsonParser=new JsonParser();
                StringBuilder structuredLog=new StringBuilder();
                if(jsonParser.isValidJson(userJson, structuredLog)){
                    System.out.println("This is a valid json  :: "+structuredLog);

                }else{
                    System.out.println("This is invalid JSON");
                }

            }catch (Exception e){
                System.out.println("Please provide valid path");
            }

        }
    }
}