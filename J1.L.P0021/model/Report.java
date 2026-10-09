package model;

/** Một dòng báo cáo được nhóm theo mã sinh viên và môn học. */
public class Report {
    private final int studentId;
    private final String studentName;
    private final String course;
    private int total;

    /**
     * Khởi tạo đối tượng Report với thông tin được cung cấp.
     * @param student bản ghi sinh viên
     */
    public Report(Student student) {
        studentId = student.getId();
        studentName = student.getName();
        course = student.getCourse();
        total = 1;
    }

    /**
     * Lấy mã sinh viên.
     * @return mã sinh viên
     */
    public int getStudentId() { return studentId; }
    /**
     * Lấy tên sinh viên.
     * @return tên sinh viên
     */
    public String getStudentName() { return studentName; }
    /**
     * Nhập và chuẩn hóa môn học hợp lệ: Java, .Net hoặc C/C++.
     * @return tên môn học hợp lệ đã được chuẩn hóa
     */
    public String getCourse() { return course; }
    /**
     * Lấy tổng số lần đăng ký môn học.
     * @return tổng số lần đăng ký môn học
     */
    public int getTotal() { return total; }
    /**
     * Tăng tổng số lần đăng ký môn học trong dòng báo cáo lên một.
     */
    public void incrementTotal() { total++; }
}
