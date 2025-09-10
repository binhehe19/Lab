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
  private static final Scanner sc = new Scanner(System.in);
  
  public static int getInt(String msgInfo, String msgErrOutOfRange, String msgErrNumber, int min, int max){
    do{
        try{
            System.out.println(msgInfo);
            int number = Integer.parseInt(sc.nextLine().trim());
            if(number >= min && number <= max){
                return number;
            }else{
                System.out.println(msgErrOutOfRange);
            }
        }catch(NumberFormatException e){
            System.out.println(msgErrNumber);
        }
    }while(true);
}
  
  public static String getString(String msgInfo, String msgErr, final String regex){
      do{
          try{
              System.out.println(msgInfo);
              String str= sc.nextLine().toUpperCase();
              if(str.matches(regex)){
                  return str;
              }else{
                  System.out.println(msgErr);
              }
          }catch(Exception e){
              System.out.println(msgErr);
          }
      }while(true);
  }
}