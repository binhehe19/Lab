package model;

public class Experience extends Candidate {
    private final int expInYear;
    private final String proSkill;

    /**
     * Khởi tạo đối tượng Experience với thông tin được cung cấp.
     * EN: Create a Experience object with the given information.
     * @param id mã định danh / ID
     * @param firstName tên của ứng viên / first name
     * @param lastName họ của ứng viên / last name
     * @param birthYear năm sinh / birth year
     * @param address địa chỉ / address
     * @param phone số điện thoại / phone number
     * @param email địa chỉ email / email address
     * @param expInYear số năm kinh nghiệm / years of experience
     * @param proSkill kỹ năng chuyên môn / professional skill
     */
    public Experience(String id, String firstName, String lastName, int birthYear,
            String address, String phone, String email, int expInYear, String proSkill) {
        // Gọi constructor của Candidate để lưu các thuộc tính chung.
        // EN: Call the Candidate constructor to save common information.
        super(id, firstName, lastName, birthYear, address, phone, email, 0);
        this.expInYear = expInYear;
        this.proSkill = proSkill;
    }

    /**
     * Lấy số năm kinh nghiệm.
     * EN: Get the years of experience.
     * @return số năm kinh nghiệm / years of experience
     */
    public int getExpInYear() { return expInYear; }
    /**
     * Lấy kỹ năng chuyên môn.
     * EN: Get the professional skill.
     * @return kỹ năng chuyên môn / professional skill
     */
    public String getProSkill() { return proSkill; }
}
