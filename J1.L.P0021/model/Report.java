package model;

// One row in the report, grouped by student ID and course.
public class Report {
    private final int studentId;
    private final String studentName;
    private final String course;
    private int total;

    public Report(Student student) {
        studentId = student.getId();
        studentName = student.getName();
        course = student.getCourse();
        total = 1;
    }

    public int getStudentId() { return studentId; }
    public String getStudentName() { return studentName; }
    public String getCourse() { return course; }
    public int getTotal() { return total; }
    public void incrementTotal() { total++; }
}
