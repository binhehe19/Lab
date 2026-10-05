package view;

import java.time.Year;
import java.util.Scanner;

public class Utility {
    private static final Scanner sc = new Scanner(System.in);

    public static String getString(String message) {
        while (true) {
            System.out.print(message);
            String value = sc.nextLine().trim();
            if (!value.isEmpty()) {
                return value;
            }
            System.out.println("Input cannot be empty!");
        }
    }

    public static int getInt(String message, int min, int max) {
        while (true) {
            try {
                int value = Integer.parseInt(getString(message));
                if (value >= min && value <= max) {
                    return value;
                }
                System.out.printf("Enter a number from %d to %d.%n", min, max);
            } catch (NumberFormatException e) {
                System.out.println("Invalid integer!");
            }
        }
    }

    public static String getPattern(String message, String pattern, String error) {
        while (true) {
            String value = getString(message);
            if (value.matches(pattern)) {
                return value;
            }
            System.out.println(error);
        }
    }

    public static int getBirthYear(String message) {
        int currentYear = Year.now().getValue();
        while (true) {
            String value = getPattern(message, "[0-9]{4}", "Birth year must have 4 digits.");
            int year = Integer.parseInt(value);
            if (year >= 1900 && year <= currentYear) {
                return year;
            }
            System.out.println("Birth year must be from 1900 to " + currentYear + ".");
        }
    }

    public static String getRank(String message) {
        String[] ranks = {"Excellence", "Good", "Fair", "Poor"};
        while (true) {
            String value = getString(message);
            for (String rank : ranks) {
                if (rank.equalsIgnoreCase(value)) {
                    return rank;
                }
            }
            System.out.println("Rank must be Excellence, Good, Fair or Poor.");
        }
    }

    public static boolean getYesNo(String message) {
        while (true) {
            String value = getString(message + " (Y/N)? ");
            if (value.equalsIgnoreCase("Y")) return true;
            if (value.equalsIgnoreCase("N")) return false;
            System.out.println("Please enter Y or N!");
        }
    }
}
