package model;

public abstract class Candidate {
    private final String id;
    private final String firstName;
    private final String lastName;
    private final int birthYear;
    private final String address;
    private final String phone;
    private final String email;
    private final int type;

    public Candidate(String id, String firstName, String lastName, int birthYear,
            String address, String phone, String email, int type) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthYear = birthYear;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.type = type;
    }

    public String getId() { return id; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getFullName() { return firstName + " " + lastName; }
    public int getBirthYear() { return birthYear; }
    public String getAddress() { return address; }
    public String getPhone() { return phone; }
    public String getEmail() { return email; }
    public int getType() { return type; }
}
