package view;

import java.util.Scanner;

public class Utility {
    private static final Scanner sc = new Scanner(System.in);

    /**
     * Nhập chuỗi, loại bỏ khoảng trắng hai đầu và yêu cầu nhập lại nếu rỗng.
     * @param msg thông báo hướng dẫn nhập
     * @return chuỗi không rỗng đã loại bỏ khoảng trắng hai đầu
     */
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

    /**
     * Nhập số nguyên và yêu cầu nhập lại cho đến khi nằm trong giới hạn.
     * @param msg thông báo hướng dẫn nhập
     * @param min giá trị nhỏ nhất được chấp nhận
     * @param max giá trị lớn nhất được chấp nhận
     * @return số nguyên trong khoảng từ min đến max, bao gồm hai đầu
     */
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

    /**
     * Nhập lựa chọn Y hoặc N, không phân biệt chữ hoa và chữ thường.
     * @param msg thông báo hướng dẫn nhập
     * @return true nếu chọn Y; false nếu chọn N
     */
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

    /**
     * Nhập và chuẩn hóa môn học hợp lệ: Java, .Net hoặc C/C++.
     * @param msg thông báo hướng dẫn nhập
     * @return tên môn học hợp lệ đã được chuẩn hóa
     */
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
