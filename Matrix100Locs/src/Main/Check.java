/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

import java.util.Scanner;

/**
 *
 * @author Admin
 */
public class Check {
public static final Scanner sc = new Scanner(System.in);

public static int getInt(String msg, int min, int max){
    int result = 0;
    do{
        try{
            System.out.println(msg);
            result = Integer.parseInt(sc.nextLine().trim());
            if(result < min || result > max){
                System.out.println("Invalid number");
                continue;
            }
            return result;
        }catch(Exception e){
            System.out.println("You must be choice 1->4");
        }
    }while(true);
}
}
