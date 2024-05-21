package org.example;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws IOException {

        System.out.println("::::   Welcome to JSON Parser   ::::");
        while(true){
            System.out.println("Please git the path(absoulte path) of the your file");
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
                while ((nextLine = reader.readLine()) != null){
                    System.out.println(nextLine);
                }
                reader.close();
            }catch (Exception e){
                System.out.println("Please provide valid path");
            }

        }
    }
}