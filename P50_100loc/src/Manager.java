/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Wind
 */
import java.util.List;
import java.util.ArrayList;


public class Manager {

        public static List<Float> calculateEquation(float a, float b) {
            List<Float> result = new ArrayList<>();

            if (a == 0) {
                if (b == 0) {
                    return new ArrayList<>(); // Infinitely many solutions
                } else {
                    return null; // No solution
                }
            } else {
                result.add(-b / a); // One solution
            }
            return result;
        }

        // Function to calculate quadratic equation (ax^2 + bx + c = 0)
        public static List<Float> calculateQuadraticEquation(float a, float b, float c) {
            List<Float> result = new ArrayList<>();
            float delta = b * b - 4 * a * c;

            if( a == 0){
                return result = calculateEquation(b,c);
            }
            else {
                if (delta < 0) {
                    return null; // No real solution
                } else if (delta == 0) {
                    result.add(-b / (2 * a));
                    result.add(-b / (2 * a));// One solution
                } else {
                    result.add((-b + (float) Math.sqrt(delta)) / (2 * a)); // First solution
                    result.add((-b - (float) Math.sqrt(delta)) / (2 * a)); // Second solution


                      

                }
            }

            return result;
        }

        
        
        // Function to check and display even, odd, and perfect square numbers from the solution list


        // Check if a number is even
        public static boolean isEven(float number) {
            return number % 2 == 0;
        }

        // Check if a number is odd
        public static boolean isOdd(float number) {
            return number %  2 != 0;
        }

        // Check if a number is a perfect square
        public static boolean isPerfectSquare(float number) {
            double sqrt = Math.sqrt(number);
            return (sqrt - Math.floor(sqrt)) == 0;
        }
    }
