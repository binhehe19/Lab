package view;

import java.util.Scanner;

public class Utility {
    private static final Scanner sc = new Scanner(System.in);

    public static String getString(String msg) {
        while (true) {
            System.out.print(msg);
            String s = sc.nextLine().trim();
            if (!s.isEmpty()) {
                return s;
            }
            System.out.println("Input cannot be empty! Please enter again.");
        }
    }

    public static int getInt(String msg, int min, int max) {
        while (true) {
            try {
                int val = Integer.parseInt(getString(msg));
                if (val >= min && val <= max) {
                    return val;
                }
                System.out.printf("Value must be between %d and %d!\n", min, max);
            } catch (NumberFormatException e) {
                System.out.println("Invalid integer! Please enter a number.");
            }
        }
    }

    public static boolean getYesNo(String msg) {
        while (true) {
            String choice = getString(msg + " (Y/N)? ");
            if (choice.equalsIgnoreCase("Y")) {
                return true;
            }
            if (choice.equalsIgnoreCase("N")) {
                return false;
            }
            System.out.println("Please enter Y or N!");
        }
    }

    public static String getCourse(String msg) {
        while (true) {
            String course = getString(msg);
            if (course.equalsIgnoreCase("Java")) return "Java";
            if (course.equalsIgnoreCase(".Net")) return ".Net";
            if (course.equalsIgnoreCase("C/C++")) return "C/C++";
            System.out.println("Course must be Java, .Net, or C/C++!");
        }
    }
}
