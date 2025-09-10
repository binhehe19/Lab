/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Wind
 */
import java.util.Scanner;

public class check {
    public static int checkInt(int min, int max) {
        Scanner sc = new Scanner(System.in);
        int result = 0;
        while (true) {
            try {
                result = Integer.parseInt(sc.nextLine().trim());
                if (result < min || result > max) {
                    throw new NumberFormatException();
                }
                return result;
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number between " + min + " and " + max);

            }

        }
    }

    public static float checkin(String message) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            try {
                System.out.print(message);
                String input = sc.nextLine().trim();
                float result = Float.parseFloat(input);
                return result;
            } catch (NumberFormatException e) {
                System.out.println("Please input number");
            }
        }
    }
}
