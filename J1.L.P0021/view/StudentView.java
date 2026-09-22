package view;

import java.util.List;
import java.util.Map;
import model.Student;

public class StudentView {
    private final Menu menu = new Menu();

    public StudentView() {
        menu.add("1. Create");
        menu.add("2. Find and Sort");
        menu.add("3. Update/Delete");
        menu.add("4. Report");
        menu.add("5. Exit");
    }

    public int showMenu() {
        showMessage("WELCOME TO STUDENT MANAGEMENT");
        menu.print();
        return menu.getUserChoices();
    }

    public void showMessage(String message) { System.out.println(message); }
    public String getString(String message, boolean allowEmpty) {
        return Utility.getString(message, allowEmpty);
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
        for (Student student : students) {
            System.out.printf("%-10d | %-20s | %-10d | %-10s%n",
                    student.getId(), student.getName(), student.getSemester(), student.getCourse());
        }
    }

    public void showReport(Map<String, Integer> report) {
        showMessage("\n--- Report ---");
        for (Map.Entry<String, Integer> entry : report.entrySet()) {
            String[] parts = entry.getKey().split("\\|");
            System.out.println(parts[0] + " | " + parts[1] + " | " + entry.getValue());
        }
        System.out.println();
    }
}
