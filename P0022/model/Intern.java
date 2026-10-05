package model;

public class Intern extends Candidate {
    private final String majors;
    private final String semester;
    private final String universityName;

    public Intern(String id, String firstName, String lastName, int birthYear,
            String address, String phone, String email, String majors,
            String semester, String universityName) {
        super(id, firstName, lastName, birthYear, address, phone, email, 2);
        this.majors = majors;
        this.semester = semester;
        this.universityName = universityName;
    }

    public String getMajors() { return majors; }
    public String getSemester() { return semester; }
    public String getUniversityName() { return universityName; }
}
