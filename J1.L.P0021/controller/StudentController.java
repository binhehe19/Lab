package controller;

import java.util.List;
import model.Student;
import model.StudentManager;
import view.StudentView;

public class StudentController {
    private final StudentManager model;
    private final StudentView view;

    public StudentController(StudentManager model, StudentView view) {
        this.model = model;
        this.view = view;
    }

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

    public void createStudent() {
        view.showMessage("--- Create New Student ---");
        while (true) {
            int id = view.getInt("Enter student ID: ", 1, Integer.MAX_VALUE);
            String name = view.getString("Enter student name: ", false);
            int semester = view.getInt("Enter semester: ", 1, 10);
            String course = view.getCourse("Enter course (Java, .Net, C/C++): ");

            model.add(new Student(id, name, semester, course));
            view.showMessage("Student added successfully!");

            if (model.size() < 10) {
                view.showMessage("You need to create at least 10 students. Current total: " + model.size());
            } else {
                if (!view.getYesNo("Do you want to continue")) {
                    break;
                }
            }
        }
    }

    public void findAndSort() {
        if (model.isEmpty()) {
            view.showMessage("Student list is empty!");
            return;
        }

        String keyword = view.getString("Enter student name to find: ", false).toLowerCase();
        List<Student> matchedList = model.findAndSort(keyword);

        if (matchedList.isEmpty()) {
            view.showMessage("Student does not exist!");
            return;
        }

        view.showSearchResults(matchedList);
    }

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

        while (true) {
            String choice = view.getString("Do you want to update (U) or delete (D) student? ", false);
            if (choice.equalsIgnoreCase("U")) {
                for (Student st : foundStudents) {
                    view.showMessage("Updating student ID: " + st.getId());
                    String newName = view.getString("Enter new name: ", false);
                    int newSemester = view.getInt("Enter new semester: ", 1, 10);
                    String newCourse = view.getCourse("Enter new course (Java, .Net, C/C++): ");

                    model.update(st, newName, newSemester, newCourse);
                }
                view.showMessage("Updated student successfully!");
                break;
            } else if (choice.equalsIgnoreCase("D")) {
                model.delete(foundStudents);
                view.showMessage("Deleted student successfully!");
                break;
            } else {
                view.showMessage("Please enter U or D!");
            }
        }
    }

    public void report() {
        if (model.isEmpty()) {
            view.showMessage("Student list is empty!");
            return;
        }

        view.showReport(model.report());
    }
}
