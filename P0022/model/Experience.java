package model;

public class Experience extends Candidate {
    private final int expInYear;
    private final String proSkill;

    /**
     * Khởi tạo đối tượng Experience với thông tin được cung cấp.
     * @param id mã định danh
     * @param firstName tên của ứng viên
     * @param lastName họ của ứng viên
     * @param birthYear năm sinh
     * @param address địa chỉ
     * @param phone số điện thoại
     * @param email địa chỉ email
     * @param expInYear số năm kinh nghiệm
     * @param proSkill kỹ năng chuyên môn
     */
    public Experience(String id, String firstName, String lastName, int birthYear,
            String address, String phone, String email, int expInYear, String proSkill) {
        // Gọi constructor của Candidate để lưu các thuộc tính chung.
        super(id, firstName, lastName, birthYear, address, phone, email, 0);
        this.expInYear = expInYear;
        this.proSkill = proSkill;
    }

    /**
     * Lấy số năm kinh nghiệm.
     * @return số năm kinh nghiệm
     */
    public int getExpInYear() { return expInYear; }
    /**
     * Lấy kỹ năng chuyên môn.
     * @return kỹ năng chuyên môn
     */
    public String getProSkill() { return proSkill; }
}
