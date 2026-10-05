package model;

public class Experience extends Candidate {
    private final int expInYear;
    private final String proSkill;

    public Experience(String id, String firstName, String lastName, int birthYear,
            String address, String phone, String email, int expInYear, String proSkill) {
        // super goi constructor cua Candidate de luu cac thuoc tinh chung.
        super(id, firstName, lastName, birthYear, address, phone, email, 0);
        this.expInYear = expInYear;
        this.proSkill = proSkill;
    }

    public int getExpInYear() { return expInYear; }
    public String getProSkill() { return proSkill; }
}
