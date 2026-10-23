package view;

import java.time.Year;
import java.util.Scanner;

public class Utility {
    private static final Scanner sc = new Scanner(System.in);

    /**
     * Nhập chuỗi, loại bỏ khoảng trắng hai đầu và yêu cầu nhập lại nếu rỗng.
     * EN: Read text, remove spaces at both ends and ask again if empty.
     * @param message thông báo hiển thị hoặc hướng dẫn nhập / message or input prompt
     * @return chuỗi không rỗng đã loại bỏ khoảng trắng hai đầu / non-empty text with spaces removed at both ends
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
     * EN: Read a whole number. Ask again if it is outside the range.
     * @param message thông báo hiển thị hoặc hướng dẫn nhập / message or input prompt
     * @param min giá trị nhỏ nhất được chấp nhận / lowest allowed value
     * @param max giá trị lớn nhất được chấp nhận / highest allowed value
     * @return số nguyên trong khoảng từ min đến max, bao gồm hai đầu / whole number from min to max, including both limits
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
     * EN: Read text again until it matches the pattern.
     * @param message thông báo hiển thị hoặc hướng dẫn nhập / message or input prompt
     * @param pattern biểu thức chính quy dùng để kiểm tra dữ liệu / pattern used to check input
     * @param error thông báo khi dữ liệu không đúng định dạng / error message for the wrong format
     * @return chuỗi hợp lệ khớp với pattern / valid text that matches the pattern
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
     * EN: Read a four-digit birth year from 1900 to the current year.
     * @param message thông báo hiển thị hoặc hướng dẫn nhập / message or input prompt
     * @return năm sinh hợp lệ / valid birth year
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
     * EN: Read a graduation rank: Excellence, Good, Fair or Poor.
     * @param message thông báo hiển thị hoặc hướng dẫn nhập / message or input prompt
     * @return xếp loại hợp lệ đã được chuẩn hóa / valid rank in the standard spelling
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
     * EN: Read Y or N. Accept both upper and lower case.
     * @param message thông báo hiển thị hoặc hướng dẫn nhập / message or input prompt
     * @return true nếu chọn Y; false nếu chọn N / true for Y, false for N
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
