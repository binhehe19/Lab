package model;

public class Fresher extends Candidate {
    private final String graduationDate;
    private final String graduationRank;
    private final String education;

    public Fresher(String id, String firstName, String lastName, int birthYear,
            String address, String phone, String email, String graduationDate,
            String graduationRank, String education) {
        super(id, firstName, lastName, birthYear, address, phone, email, 1);
        this.graduationDate = graduationDate;
        this.graduationRank = graduationRank;
        this.education = education;
    }

    public String getGraduationDate() { return graduationDate; }
    public String getGraduationRank() { return graduationRank; }
    public String getEducation() { return education; }
}
