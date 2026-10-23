package model;

public class Intern extends Candidate {
    private final String majors;
    private final String semester;
    private final String universityName;

    /**
     * Khởi tạo đối tượng Intern với thông tin được cung cấp.
     * EN: Create a Intern object with the given information.
     * @param id mã định danh / ID
     * @param firstName tên của ứng viên / first name
     * @param lastName họ của ứng viên / last name
     * @param birthYear năm sinh / birth year
     * @param address địa chỉ / address
     * @param phone số điện thoại / phone number
     * @param email địa chỉ email / email address
     * @param majors chuyên ngành / major
     * @param semester học kỳ / semester
     * @param universityName tên trường đại học / university name
     */
    public Intern(String id, String firstName, String lastName, int birthYear,
            String address, String phone, String email, String majors,
            String semester, String universityName) {
        super(id, firstName, lastName, birthYear, address, phone, email, 2);
        this.majors = majors;
        this.semester = semester;
        this.universityName = universityName;
    }

    /**
     * Lấy chuyên ngành.
     * EN: Get the major.
     * @return chuyên ngành / major
     */
    public String getMajors() { return majors; }
    /**
     * Lấy học kỳ.
     * EN: Get the semester.
     * @return học kỳ / semester
     */
    public String getSemester() { return semester; }
    /**
     * Lấy tên trường đại học.
     * EN: Get the university name.
     * @return tên trường đại học / university name
     */
    public String getUniversityName() { return universityName; }
}
