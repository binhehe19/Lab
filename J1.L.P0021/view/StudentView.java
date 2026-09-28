package view;

import java.util.List;
import model.Report;
import model.Student;

public class StudentView {
    public int showMenu() {
        showMessage("WELCOME TO STUDENT MANAGEMENT");
        showMessage("1. Create");
        showMessage("2. Find and Sort");
        showMessage("3. Update/Delete");
        showMessage("4. Report");
        showMessage("5. Exit");
        return getInt("Please choose (1-5): ", 1, 5);
    }

    public void showMessage(String message) { System.out.println(message); }
    public String getString(String message) {
        return Utility.getString(message);
    }
    public int getInt(String message, int min, int max) {
        return Utility.getInt(message, min, max);
    }
    public String getCourse(String message) { return Utility.getCourse(message); }
    public boolean getYesNo(String message) { return Utility.getYesNo(message); }

    public void showSearchResults(List<Student> students) {
        showMessage("\n--- Found & Sorted Students ---");
        for (Student student : students) {
            System.out.println(student.getName() + " | " + student.getSemester() + " | " + student.getCourse());
        }
        System.out.println();
    }

    public void showStudents(List<Student> students) {
        showMessage("Found student(s):");
        showMessage("No. | ID | Name | Semester | Course");
        for (int i = 0; i < students.size(); i++) {
            Student student = students.get(i);
            System.out.printf("%d | %d | %s | %d | %s%n",
                    i + 1, student.getId(), student.getName(), student.getSemester(), student.getCourse());
        }
    }

    public void showReport(List<Report> report) {
        showMessage("\n--- Report ---");
        for (Report row : report) {
            System.out.println(row.getStudentName() + " | " + row.getCourse() + " | " + row.getTotal());
        }
        System.out.println();
    }
}
