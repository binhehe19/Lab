/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Validate;

import java.util.Scanner;

/**
 *
 * @author admin
 */
public class Check {
    private final Scanner sc = new Scanner(System.in);
    public int getInt(String mss, int min, int max) {
    int result;
    while (true) {
        try {
            System.out.println(mss);
            result = Integer.parseInt(sc.nextLine().trim());
            if (result >= min && result <= max) {
                break;
            } else {
                System.out.println("Input number must be between " + min + " and " + max);
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid integer. Please enter a valid integer.");
        }
    }
    return result;
}

    public double getDouble(String mss){
        double result;
        while(true){
            try{
                System.out.println(mss);
                result = Double.parseDouble(sc.nextLine().trim());
                break;
            }catch(Exception e){
                System.out.println("Invalid number.");
            }
        }
        return result;
    }
    public String getString( String mss){
        String result;
        while (true) {            
            try{
                System.out.println(mss);
                result = sc.nextLine().trim();
                if(!result.isEmpty()){
                    break;
                }
        }catch(Exception e){
                System.out.println("Invalid input");
        }
    }
        return result;
    }
    public String getStringExit(String mss){
        System.out.print(mss);
        String result = sc.nextLine().trim();
        if(result.isEmpty()){
            return null;
        }
        return result;
               
    }
}
