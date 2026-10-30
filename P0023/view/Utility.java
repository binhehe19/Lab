package view;

import java.util.Scanner;

public class Utility {
    private final Scanner scanner = new Scanner(System.in);

    // Bỏ khoảng trắng hai đầu, không nhận chuỗi rỗng. / Trim text and do not accept empty input.
    public String getString(String message) {
        String value;
        do {
            System.out.print(message);
            value = scanner.nextLine().trim();
            if (value.isEmpty()) {
                System.out.println("Input cannot be empty!");
            }
        } while (value.isEmpty());
        return value;
    }

    // Nhập số nguyên trong giới hạn. / Read a whole number within the range.
    public int getInt(String message, int min, int max) {
        int value = 0;
        boolean valid = false;
        do {
            try {
                value = Integer.parseInt(getString(message));
                valid = value >= min && value <= max;
                if (!valid) {
                    System.out.printf("Enter a number from %d to %d.%n", min, max);
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid integer!");
            }
        } while (!valid);
        return value;
    }

    // Giá phải là số hữu hạn lớn hơn 0. / Price must be a finite number above zero.
    public double getPrice(String message) {
        double value = 0;
        boolean valid = false;
        do {
            try {
                value = Double.parseDouble(getString(message));
                valid = Double.isFinite(value) && value > 0;
                if (!valid) {
                    System.out.println("Price must be a finite number greater than 0.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid price!");
            }
        } while (!valid);
        return value;
    }

    // Chỉ nhận Y hoặc N. / Only accept Y or N.
    public boolean getYesNo(String message) {
        String value;
        boolean valid;
        do {
            value = getString(message + " (Y/N)? ");
            valid = value.equalsIgnoreCase("Y") || value.equalsIgnoreCase("N");
            if (!valid) {
                System.out.println("Please enter Y or N!");
            }
        } while (!valid);
        return value.equalsIgnoreCase("Y");
    }
}
