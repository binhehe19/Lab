package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Locale;
import java.util.List;

public class StudentManager {
    private final List<Student> students = new ArrayList<>();

    /**
     * Kiểm tra danh sách sinh viên có rỗng hay không.
     * @return true nếu danh sách rỗng; false nếu có bản ghi
     */
    public boolean isEmpty() { return students.isEmpty(); }
    /**
     * Đếm số bản ghi đăng ký môn học trong danh sách.
     * @return số bản ghi hiện có
     */
    public int size() { return students.size(); }
    /**
     * Thêm bản ghi sinh viên nếu không trùng mã, học kỳ và môn học; dùng tên đã có của cùng mã.
     * @param student bản ghi sinh viên
     * @return true nếu thêm thành công; false nếu bản ghi bị trùng
     */
    public boolean add(Student student) {
        if (isDuplicate(null, student.getId(), student.getSemester(), student.getCourse())) {
            return false;
        }
        // Dùng lại tên sinh viên đã được lưu cho cùng mã.
        for (Student existing : students) {
            if (existing.getId() == student.getId()) {
                student.setName(existing.getName());
                break;
            }
        }
        students.add(student);
        return true;
    }

    /**
     * Kiểm tra trùng mã sinh viên, học kỳ và môn học, bỏ qua bản ghi đang sửa.
     * @param excluded bản ghi bỏ qua khi kiểm tra trùng; null khi thêm mới
     * @param id mã định danh
     * @param semester học kỳ
     * @param course môn học
     * @return true nếu có bản ghi trùng; false nếu không trùng
     */
    private boolean isDuplicate(Student excluded, int id, int semester, String course) {
        // Khi sửa, bỏ qua bản ghi đang sửa. Khi thêm, excluded bằng null.
        for (Student student : students) {
            if (student != excluded && student.getId() == id
                    && student.getSemester() == semester
                    && student.getCourse().equalsIgnoreCase(course)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Tìm sinh viên có tên chứa từ khóa và sắp xếp tên tăng dần, không phân biệt hoa thường.
     * @param keyword từ khóa tìm kiếm tên
     * @return danh sách kết quả đã sắp xếp; danh sách rỗng nếu không tìm thấy
     */
    public List<Student> findAndSort(String keyword) {
        List<Student> result = new ArrayList<>();
        String searchName = keyword.toLowerCase(Locale.ROOT);
        for (Student student : students) {
            if (student.getName().toLowerCase(Locale.ROOT).contains(searchName)) {
                result.add(student);
            }
        }
        Collections.sort(result, new Comparator<Student>() {
            /**
             * So sánh tên hai sinh viên, không phân biệt chữ hoa và chữ thường.
             * @param first sinh viên thứ nhất
             * @param second sinh viên thứ hai
             * @return số âm, 0 hoặc số dương khi tên thứ nhất đứng trước, bằng hoặc đứng sau tên thứ hai
             */
            @Override
            public int compare(Student first, Student second) {
                return first.getName().compareToIgnoreCase(second.getName());
            }
        });
        return result;
    }

    /**
     * Tìm tất cả bản ghi sinh viên có mã được chỉ định.
     * @param id mã định danh
     * @return danh sách bản ghi cùng mã; danh sách rỗng nếu không tìm thấy
     */
    public List<Student> findById(int id) {
        List<Student> result = new ArrayList<>();
        for (Student student : students) {
            if (student.getId() == id) { result.add(student); }
        }
        return result;
    }

    /**
     * Cập nhật học kỳ và môn học nếu không trùng; đổi tên cho mọi bản ghi cùng mã.
     * @param student bản ghi sinh viên
     * @param name tên sinh viên
     * @param semester học kỳ
     * @param course môn học
     * @return true nếu cập nhật thành công; false nếu trùng học kỳ và môn học
     */
    public boolean update(Student student, String name, int semester, String course) {
        if (isDuplicate(student, student.getId(), semester, course)) {
            return false;
        }
        // Đổi tên cho tất cả bản ghi có cùng mã sinh viên.
        for (Student existing : students) {
            if (existing.getId() == student.getId()) {
                existing.setName(name);
            }
        }
        student.setSemester(semester);
        student.setCourse(course);
        return true;
    }

    /**
     * Xóa bản ghi sinh viên được chọn khỏi danh sách.
     * @param selected bản ghi sinh viên cần xóa
     */
    public void delete(Student selected) { students.remove(selected); }

    /**
     * Tổng hợp số lần đăng ký theo mã sinh viên và môn học.
     * @return danh sách các dòng báo cáo
     */
    public List<Report> report() {
        List<Report> result = new ArrayList<>();
        for (Student student : students) {
            // Tìm dòng báo cáo cùng mã và môn học: đã có thì tăng tổng, chưa có thì thêm.
            Report matched = null;
            for (Report row : result) {
                if (row.getStudentId() == student.getId()
                        && row.getCourse().equalsIgnoreCase(student.getCourse())) {
                    matched = row;
                    break;
                }
            }
            if (matched == null) {
                result.add(new Report(student));
            } else {
                matched.incrementTotal();
            }
        }
        return result;
    }
}
