import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Manager {
    private final ArrayList<Student> studentList = new ArrayList<>();

    public Manager() {
    }

    public void createStudent() {
        System.out.println("--- Create New Student ---");
        while (true) {
            int id = Utility.getInt("Enter student ID: ", 1, Integer.MAX_VALUE);
            String name = Utility.getString("Enter student name: ", false);
            int semester = Utility.getInt("Enter semester: ", 1, 10);
            String course = Utility.getCourse("Enter course (Java, .Net, C/C++): ");

            studentList.add(new Student(id, name, semester, course));
            System.out.println("Student added successfully!");

            if (studentList.size() < 10) {
                System.out.println("You need to create at least 10 students. Current total: " + studentList.size());
            } else {
                if (!Utility.getYesNo("Do you want to continue")) {
                    break;
                }
            }
        }
    }

    public void findAndSort() {
        if (studentList.isEmpty()) {
            System.out.println("Student list is empty!");
            return;
        }

        String keyword = Utility.getString("Enter student name to find: ", false).toLowerCase();
        List<Student> matchedList = new ArrayList<>();

        for (Student st : studentList) {
            if (st.getName().toLowerCase().contains(keyword)) {
                matchedList.add(st);
            }
        }

        if (matchedList.isEmpty()) {
            System.out.println("Student does not exist!");
            return;
        }

        Collections.sort(matchedList);

        System.out.println("\n--- Found & Sorted Students ---");
        for (Student st : matchedList) {
            System.out.println(st.getName() + " | " + st.getSemester() + " | " + st.getCourse());
        }
        System.out.println();
    }

    public void updateOrDelete() {
        if (studentList.isEmpty()) {
            System.out.println("Student list is empty!");
            return;
        }

        int id = Utility.getInt("Enter student ID to update or delete: ", 1, Integer.MAX_VALUE);
        List<Student> foundStudents = new ArrayList<>();

        for (Student st : studentList) {
            if (st.getId() == id) {
                foundStudents.add(st);
            }
        }

        if (foundStudents.isEmpty()) {
            System.out.println("Student ID does not exist!");
            return;
        }

        System.out.println("Found student(s):");
        for (Student st : foundStudents) {
            System.out.println(st.getId() + " | " + st.getName() + " | " + st.getSemester() + " | " + st.getCourse());
        }

        while (true) {
            String choice = Utility.getString("Do you want to update (U) or delete (D) student? ", false);
            if (choice.equalsIgnoreCase("U")) {
                for (Student st : foundStudents) {
                    System.out.println("Updating student ID: " + st.getId());
                    String newName = Utility.getString("Enter new name: ", false);
                    int newSemester = Utility.getInt("Enter new semester: ", 1, 10);
                    String newCourse = Utility.getCourse("Enter new course (Java, .Net, C/C++): ");

                    st.setName(newName);
                    st.setSemester(newSemester);
                    st.setCourse(newCourse);
                }
                System.out.println("Updated student successfully!");
                break;
            } else if (choice.equalsIgnoreCase("D")) {
                studentList.removeAll(foundStudents);
                System.out.println("Deleted student successfully!");
                break;
            } else {
                System.out.println("Please enter U or D!");
            }
        }
    }

    public void report() {
        if (studentList.isEmpty()) {
            System.out.println("Student list is empty!");
            return;
        }

        Map<String, Integer> reportMap = new LinkedHashMap<>();
        for (Student st : studentList) {
            String key = st.getName() + "|" + st.getCourse();
            reportMap.put(key, reportMap.getOrDefault(key, 0) + 1);
        }

        System.out.println("\n--- Report ---");
        for (Map.Entry<String, Integer> entry : reportMap.entrySet()) {
            String[] parts = entry.getKey().split("\\|");
            System.out.println(parts[0] + " | " + parts[1] + " | " + entry.getValue());
        }
        System.out.println();
    }
}
