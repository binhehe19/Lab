package controller;

import java.util.List;
import model.Student;
import model.StudentManager;
import view.StudentView;

public class StudentController {
    private final StudentManager model;
    private final StudentView view;

    /**
     * Khởi tạo đối tượng StudentController với thông tin được cung cấp.
     * @param model đối tượng quản lý dữ liệu
     * @param view đối tượng giao diện nhập và xuất
     */
    public StudentController(StudentManager model, StudentView view) {
        this.model = model;
        this.view = view;
    }

    /**
     * Hiển thị menu và xử lý chức năng được chọn cho đến khi thoát.
     */
    public void run() {
        while (true) {
            switch (view.showMenu()) {
                case 1: createStudent(); break;
                case 2: findAndSort(); break;
                case 3: updateOrDelete(); break;
                case 4: report(); break;
                case 5:
                    view.showMessage("Exiting program. Goodbye!");
                    return;
            }
        }
    }

    /**
     * Nhập và thêm bản ghi sinh viên; yêu cầu đủ 10 bản ghi trước khi cho phép dừng.
     */
    public void createStudent() {
        view.showMessage("--- Create New Student ---");
        while (true) {
            int id = view.getInt("Enter student ID: ", 1, Integer.MAX_VALUE);
            List<Student> existing = model.findById(id);
            String name;
            if (existing.isEmpty()) {
                name = view.getString("Enter student name: ");
            } else {
                name = existing.get(0).getName();
                view.showMessage("Adding a course for: " + name);
            }
            int semester = view.getInt("Enter semester: ", 1, Integer.MAX_VALUE);
            String course = view.getCourse("Enter course (Java, .Net, C/C++): ");

            if (!model.add(new Student(id, name, semester, course))) {
                view.showMessage("This student already has this course in this semester.");
                continue;
            }
            view.showMessage("Student added successfully!");

            if (model.size() < 10) {
                view.showMessage("You need to create at least 10 students. Current total: " + model.size());
            } else if (!view.getYesNo("Do you want to continue")) {
                break;
            }
        }
    }

    /**
     * Tìm tên sinh viên theo từ khóa và hiển thị kết quả sắp xếp theo tên.
     */
    public void findAndSort() {
        if (model.isEmpty()) {
            view.showMessage("Student list is empty!");
            return;
        }

        String keyword = view.getString("Enter student name to find: ");
        List<Student> matchedList = model.findAndSort(keyword);

        if (matchedList.isEmpty()) {
            view.showMessage("Student does not exist!");
            return;
        }

        view.showSearchResults(matchedList);
    }

    /**
     * Tìm sinh viên theo mã, chọn bản ghi và thực hiện cập nhật hoặc xóa.
     */
    public void updateOrDelete() {
        if (model.isEmpty()) {
            view.showMessage("Student list is empty!");
            return;
        }

        int id = view.getInt("Enter student ID to update or delete: ", 1, Integer.MAX_VALUE);
        List<Student> foundStudents = model.findById(id);

        if (foundStudents.isEmpty()) {
            view.showMessage("Student ID does not exist!");
            return;
        }

        view.showStudents(foundStudents);
        int index = 0;
        if (foundStudents.size() > 1) {
            index = view.getInt("Select record number: ", 1, foundStudents.size()) - 1;
        }
        Student selected = foundStudents.get(index);

        while (true) {
            String choice = view.getString("Do you want to update (U) or delete (D) student? ");
            if (choice.equalsIgnoreCase("U")) {
                view.showMessage("Updating student ID: " + selected.getId());
                view.showMessage("Name changes apply to all records with this ID.");
                String newName = view.getString("Enter new name: ");
                int newSemester = view.getInt("Enter new semester: ", 1, Integer.MAX_VALUE);
                String newCourse = view.getCourse("Enter new course (Java, .Net, C/C++): ");

                if (model.update(selected, newName, newSemester, newCourse)) {
                    view.showMessage("Updated student successfully!");
                } else {
                    view.showMessage("Update failed: this course already exists in this semester.");
                }
                break;
            } else if (choice.equalsIgnoreCase("D")) {
                model.delete(selected);
                view.showMessage("Deleted student successfully!");
                break;
            } else {
                view.showMessage("Please enter U or D!");
            }
        }
    }

    /**
     * Tạo và hiển thị báo cáo tổng số lần đăng ký môn học của từng sinh viên.
     */
    public void report() {
        if (model.isEmpty()) {
            view.showMessage("Student list is empty!");
            return;
        }

        view.showReport(model.report());
    }
}
