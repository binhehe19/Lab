/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

/**
 *
 * @author Admin
 */
public class Doctor {
 private String code;
    private String name;
    private String specialization;
    private int availability;

    public Doctor(String code, String name, String specialization, int availability) {
        if (code == null || code.isEmpty() ||
            name == null || name.isEmpty() ||
            specialization == null || specialization.isEmpty() ||
            availability < 0) {
            throw new IllegalArgumentException("Invalid input: Code, Name, Specialization cannot be empty and Availability must be >= 0.");
        }
        this.code = code;
        this.name = name;
        this.specialization = specialization;
        this.availability = availability;
    }

    // Getters and Setters for Encapsulation
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        if (code == null || code.isEmpty()) {
            throw new IllegalArgumentException("Code cannot be null or empty.");
        }
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty.");
        }
        this.name = name;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        if (specialization == null || specialization.isEmpty()) {
            throw new IllegalArgumentException("Specialization cannot be null or empty.");
        }
        this.specialization = specialization;
    }

    public int getAvailability() {
        return availability;
    }

    public void setAvailability(int availability) {
        if (availability < 0) {
            throw new IllegalArgumentException("Availability must be >= 0.");
        }
        this.availability = availability;
    }

    @Override
    public String toString() {
        return String.format("%-8s %-15s %-15s %4d",
                code, name, specialization, availability);
    }
}
