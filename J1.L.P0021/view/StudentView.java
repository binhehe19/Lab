package view;

import java.util.List;
import model.Report;
import model.Student;

public class StudentView {
    /**
     * Hiển thị các chức năng của chương trình và nhận lựa chọn.
     * @return lựa chọn hợp lệ từ 1 đến 5
     */
    public int showMenu() {
        showMessage("WELCOME TO STUDENT MANAGEMENT");
        showMessage("1. Create");
        showMessage("2. Find and Sort");
        showMessage("3. Update/Delete");
        showMessage("4. Report");
        showMessage("5. Exit");
        return getInt("Please choose (1-5): ", 1, 5);
    }

    /**
     * In thông báo ra màn hình.
     * @param message thông báo hiển thị hoặc hướng dẫn nhập
     */
    public void showMessage(String message) { System.out.println(message); }
    /**
     * Nhập chuỗi, loại bỏ khoảng trắng hai đầu và yêu cầu nhập lại nếu rỗng.
     * @param message thông báo hiển thị hoặc hướng dẫn nhập
     * @return chuỗi không rỗng đã loại bỏ khoảng trắng hai đầu
     */
    public String getString(String message) {
        return Utility.getString(message);
    }
    /**
     * Nhập số nguyên và yêu cầu nhập lại cho đến khi nằm trong giới hạn.
     * @param message thông báo hiển thị hoặc hướng dẫn nhập
     * @param min giá trị nhỏ nhất được chấp nhận
     * @param max giá trị lớn nhất được chấp nhận
     * @return số nguyên trong khoảng từ min đến max, bao gồm hai đầu
     */
    public int getInt(String message, int min, int max) {
        return Utility.getInt(message, min, max);
    }
    /**
     * Nhập và chuẩn hóa môn học hợp lệ: Java, .Net hoặc C/C++.
     * @param message thông báo hiển thị hoặc hướng dẫn nhập
     * @return tên môn học hợp lệ đã được chuẩn hóa
     */
    public String getCourse(String message) { return Utility.getCourse(message); }
    /**
     * Nhập lựa chọn Y hoặc N, không phân biệt chữ hoa và chữ thường.
     * @param message thông báo hiển thị hoặc hướng dẫn nhập
     * @return true nếu chọn Y; false nếu chọn N
     */
    public boolean getYesNo(String message) { return Utility.getYesNo(message); }

    /**
     * Hiển thị danh sách kết quả tìm kiếm.
     * @param students danh sách bản ghi sinh viên cần hiển thị
     */
    public void showSearchResults(List<Student> students) {
        showMessage("\n--- Found & Sorted Students ---");
        for (Student student : students) {
            System.out.println(student.getName() + " | " + student.getSemester() + " | " + student.getCourse());
        }
        System.out.println();
    }

    /**
     * Hiển thị các bản ghi sinh viên kèm số thứ tự để lựa chọn.
     * @param students danh sách bản ghi sinh viên cần hiển thị
     */
    public void showStudents(List<Student> students) {
        showMessage("Found student(s):");
        showMessage("No. | ID | Name | Semester | Course");
        for (int i = 0; i < students.size(); i++) {
            Student student = students.get(i);
            System.out.printf("%d | %d | %s | %d | %s%n",
                    i + 1, student.getId(), student.getName(), student.getSemester(), student.getCourse());
        }
    }

    /**
     * Hiển thị tên sinh viên, môn học và tổng số lần đăng ký.
     * @param report danh sách các dòng báo cáo
     */
    public void showReport(List<Report> report) {
        showMessage("\n--- Report ---");
        for (Report row : report) {
            System.out.println(row.getStudentName() + " | " + row.getCourse() + " | " + row.getTotal());
        }
        System.out.println();
    }
}
