package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Locale;
import java.util.List;

public class StudentManager {
    private final List<Student> students = new ArrayList<>();

    public boolean isEmpty() { return students.isEmpty(); }
    public int size() { return students.size(); }
    public boolean add(Student student) {
        if (isDuplicate(null, student.getId(), student.getSemester(), student.getCourse())) {
            return false;
        }
        // Reuse the name already associated with this ID.
        for (Student existing : students) {
            if (existing.getId() == student.getId()) {
                student.setName(existing.getName());
                break;
            }
        }
        students.add(student);
        return true;
    }

    private boolean isDuplicate(Student excluded, int id, int semester, String course) {
        // Khi sua, bo qua chinh ban ghi dang sua. Khi them, excluded = null.
        for (Student student : students) {
            if (student != excluded && student.getId() == id
                    && student.getSemester() == semester
                    && student.getCourse().equalsIgnoreCase(course)) {
                return true;
            }
        }
        return false;
    }

    public List<Student> findAndSort(String keyword) {
        List<Student> result = new ArrayList<>();
        String searchName = keyword.toLowerCase(Locale.ROOT);
        for (Student student : students) {
            if (student.getName().toLowerCase(Locale.ROOT).contains(searchName)) {
                result.add(student);
            }
        }
        Collections.sort(result, new Comparator<Student>() {
            @Override
            public int compare(Student first, Student second) {
                return first.getName().compareToIgnoreCase(second.getName());
            }
        });
        return result;
    }

    public List<Student> findById(int id) {
        List<Student> result = new ArrayList<>();
        for (Student student : students) {
            if (student.getId() == id) { result.add(student); }
        }
        return result;
    }

    public boolean update(Student student, String name, int semester, String course) {
        if (isDuplicate(student, student.getId(), semester, course)) {
            return false;
        }
        // A name change applies to all records of the same student.
        for (Student existing : students) {
            if (existing.getId() == student.getId()) {
                existing.setName(name);
            }
        }
        student.setSemester(semester);
        student.setCourse(course);
        return true;
    }

    public void delete(Student selected) { students.remove(selected); }

    public List<Report> report() {
        List<Report> result = new ArrayList<>();
        for (Student student : students) {
            // Tim dong bao cao cung ID va mon: co thi tang, chua co thi them.
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
