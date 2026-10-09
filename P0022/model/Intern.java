package model;

public class Intern extends Candidate {
    private final String majors;
    private final String semester;
    private final String universityName;

    /**
     * Khởi tạo đối tượng Intern với thông tin được cung cấp.
     * @param id mã định danh
     * @param firstName tên của ứng viên
     * @param lastName họ của ứng viên
     * @param birthYear năm sinh
     * @param address địa chỉ
     * @param phone số điện thoại
     * @param email địa chỉ email
     * @param majors chuyên ngành
     * @param semester học kỳ
     * @param universityName tên trường đại học
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
     * @return chuyên ngành
     */
    public String getMajors() { return majors; }
    /**
     * Lấy học kỳ.
     * @return học kỳ
     */
    public String getSemester() { return semester; }
    /**
     * Lấy tên trường đại học.
     * @return tên trường đại học
     */
    public String getUniversityName() { return universityName; }
}
