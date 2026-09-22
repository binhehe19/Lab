package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class StudentManager {
    private final List<Student> students = new ArrayList<>();

    public boolean isEmpty() { return students.isEmpty(); }
    public int size() { return students.size(); }
    public void add(Student student) { students.add(student); }

    public List<Student> findAndSort(String keyword) {
        List<Student> result = new ArrayList<>();
        for (Student student : students) {
            if (student.getName().toLowerCase().contains(keyword.toLowerCase())) {
                result.add(student);
            }
        }
        Collections.sort(result);
        return result;
    }

    public List<Student> findById(int id) {
        List<Student> result = new ArrayList<>();
        for (Student student : students) {
            if (student.getId() == id) { result.add(student); }
        }
        return result;
    }

    public void update(Student student, String name, int semester, String course) {
        student.setName(name);
        student.setSemester(semester);
        student.setCourse(course);
    }

    public void delete(List<Student> selected) { students.removeAll(selected); }

    public Map<String, Integer> report() {
        Map<String, Integer> result = new LinkedHashMap<>();
        for (Student student : students) {
            String key = student.getName() + "|" + student.getCourse();
            result.put(key, result.getOrDefault(key, 0) + 1);
        }
        return result;
    }
}
