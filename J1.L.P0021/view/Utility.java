package view;

import java.util.Scanner;

public class Utility {
    private static final Scanner sc = new Scanner(System.in);

    public static String getString(String msg, boolean isEmpty) {
        while (true) {
            System.out.print(msg);
            String s = sc.nextLine().trim();
            if (isEmpty || !s.isEmpty()) {
                return s;
            }
            System.out.println("Input cannot be empty! Please enter again.");
        }
    }

    public static int getInt(String msg, int min, int max) {
        while (true) {
            System.out.print(msg);
            try {
                int val = Integer.parseInt(sc.nextLine().trim());
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
            System.out.print(msg + " (Y/N)? ");
            String choice = sc.nextLine().trim();
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
            System.out.print(msg);
            String course = sc.nextLine().trim();
            if (course.equalsIgnoreCase("Java") || 
                course.equalsIgnoreCase(".Net") || 
                course.equalsIgnoreCase("C/C++")) {
                return course;
            }
            System.out.println("Course must be Java, .Net, or C/C++!");
        }
    }
}
