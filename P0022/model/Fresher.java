package model;

public class Fresher extends Candidate {
    private final String graduationDate;
    private final String graduationRank;
    private final String education;

    /**
     * Khởi tạo đối tượng Fresher với thông tin được cung cấp.
     * EN: Create a Fresher object with the given information.
     * @param id mã định danh / ID
     * @param firstName tên của ứng viên / first name
     * @param lastName họ của ứng viên / last name
     * @param birthYear năm sinh / birth year
     * @param address địa chỉ / address
     * @param phone số điện thoại / phone number
     * @param email địa chỉ email / email address
     * @param graduationDate ngày tốt nghiệp / graduation date
     * @param graduationRank xếp loại tốt nghiệp / graduation rank
     * @param education trường đào tạo / school or university
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
     * EN: Get the graduation date.
     * @return ngày tốt nghiệp / graduation date
     */
    public String getGraduationDate() { return graduationDate; }
    /**
     * Lấy xếp loại tốt nghiệp.
     * EN: Get the graduation rank.
     * @return xếp loại tốt nghiệp / graduation rank
     */
    public String getGraduationRank() { return graduationRank; }
    /**
     * Lấy trường đào tạo.
     * EN: Get the school or university.
     * @return trường đào tạo / school or university
     */
    public String getEducation() { return education; }
}
