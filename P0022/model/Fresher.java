package model;

public class Fresher extends Candidate {
    private final String graduationDate;
    private final String graduationRank;
    private final String education;

    /**
     * Khởi tạo đối tượng Fresher với thông tin được cung cấp.
     * @param id mã định danh
     * @param firstName tên của ứng viên
     * @param lastName họ của ứng viên
     * @param birthYear năm sinh
     * @param address địa chỉ
     * @param phone số điện thoại
     * @param email địa chỉ email
     * @param graduationDate ngày tốt nghiệp
     * @param graduationRank xếp loại tốt nghiệp
     * @param education trường đào tạo
     */
    public Fresher(String id, String firstName, String lastName, int birthYear,
            String address, String phone, String email, String graduationDate,
            String graduationRank, String education) {
        super(id, firstName, lastName, birthYear, address, phone, email, 1);
        this.graduationDate = graduationDate;
        this.graduationRank = graduationRank;
        this.education = education;
    }

    /**
     * Lấy ngày tốt nghiệp.
     * @return ngày tốt nghiệp
     */
    public String getGraduationDate() { return graduationDate; }
    /**
     * Lấy xếp loại tốt nghiệp.
     * @return xếp loại tốt nghiệp
     */
    public String getGraduationRank() { return graduationRank; }
    /**
     * Lấy trường đào tạo.
     * @return trường đào tạo
     */
    public String getEducation() { return education; }
}
