/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

/**
 *
 * @author Admin
 */
public class Message {
   public static void doctorAddedSuccess(){
       System.out.println("Doctor added successfully!");
   }
   
   public static void doctorUpdateSuccess(){
       System.out.println("Doctor update successfully!");
   }
   
   public static void doctorDeleteSuccess(){
       System.out.println("Doctor delete successfully!");
   }
   
   public static void doctorCodeDuplicate(String code){
       System.out.println("Doctor code" + code + "is duplicate");
   }
   
   public static void doctorNotFound(){
       System.out.println("Doctor can't FOUND");
   }
   
   public static void displayError(String Err){
       System.out.println("Error: " + Err);
   }
   
   public static void searchResultFound(){
       System.out.println("Found doctor");
   }
   
   public static void notFoundDoctor(){
       System.out.println("Not Found");
   }
   
   public static void exitingProgram(){
       System.out.println("Exit Program");
   }
   
   public static void mess(String mess){
       System.out.println(mess);
   }
   
   public static void invalidDoctorCode(){
       System.out.println("Invalid");
   }
   
   public static void invalidSpecializationFormat(){
       System.out.println("invalid number");
   }
   
   public static void invalidAvailabilityFormat(){
       System.out.println("Invalid number");
   }
   
   public static void invalidOption(){
       System.out.println("Invalid option");
   }
}



