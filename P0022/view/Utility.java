package view;

import java.time.Year;
import java.util.Scanner;

public class Utility {
    private static final Scanner sc = new Scanner(System.in);

    /**
     * Nhập chuỗi, loại bỏ khoảng trắng hai đầu và yêu cầu nhập lại nếu rỗng.
     * @param message thông báo hiển thị hoặc hướng dẫn nhập
     * @return chuỗi không rỗng đã loại bỏ khoảng trắng hai đầu
     */
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

    /**
     * Nhập số nguyên và yêu cầu nhập lại cho đến khi nằm trong giới hạn.
     * @param message thông báo hiển thị hoặc hướng dẫn nhập
     * @param min giá trị nhỏ nhất được chấp nhận
     * @param max giá trị lớn nhất được chấp nhận
     * @return số nguyên trong khoảng từ min đến max, bao gồm hai đầu
     */
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

    /**
     * Nhập chuỗi và yêu cầu nhập lại cho đến khi khớp biểu thức chính quy.
     * @param message thông báo hiển thị hoặc hướng dẫn nhập
     * @param pattern biểu thức chính quy dùng để kiểm tra dữ liệu
     * @param error thông báo khi dữ liệu không đúng định dạng
     * @return chuỗi hợp lệ khớp với pattern
     */
    public static String getPattern(String message, String pattern, String error) {
        while (true) {
            String value = getString(message);
            if (value.matches(pattern)) {
                return value;
            }
            System.out.println(error);
        }
    }

    /**
     * Nhập năm sinh gồm 4 chữ số, từ 1900 đến năm hiện tại.
     * @param message thông báo hiển thị hoặc hướng dẫn nhập
     * @return năm sinh hợp lệ
     */
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

    /**
     * Nhập xếp loại tốt nghiệp: Excellence, Good, Fair hoặc Poor.
     * @param message thông báo hiển thị hoặc hướng dẫn nhập
     * @return xếp loại hợp lệ đã được chuẩn hóa
     */
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

    /**
     * Nhập lựa chọn Y hoặc N, không phân biệt chữ hoa và chữ thường.
     * @param message thông báo hiển thị hoặc hướng dẫn nhập
     * @return true nếu chọn Y; false nếu chọn N
     */
    public static boolean getYesNo(String message) {
        while (true) {
            String value = getString(message + " (Y/N)? ");
            if (value.equalsIgnoreCase("Y")) return true;
            if (value.equalsIgnoreCase("N")) return false;
            System.out.println("Please enter Y or N!");
        }
    }
}
