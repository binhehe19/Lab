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

    /**
     * Khởi tạo đối tượng Candidate với thông tin được cung cấp.
     * EN: Create a Candidate object with the given information.
     * @param id mã định danh / ID
     * @param firstName tên của ứng viên / first name
     * @param lastName họ của ứng viên / last name
     * @param birthYear năm sinh / birth year
     * @param address địa chỉ / address
     * @param phone số điện thoại / phone number
     * @param email địa chỉ email / email address
     * @param type loại ứng viên: 0 là có kinh nghiệm, 1 là mới tốt nghiệp, 2 là thực tập sinh / candidate type: 0 for Experience, 1 for Fresher, 2 for Intern
     */
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

    /**
     * Lấy mã định danh.
     * EN: Get the ID.
     * @return mã định danh / ID
     */
    public String getId() { return id; }
    /**
     * Lấy tên của ứng viên.
     * EN: Get the first name.
     * @return tên của ứng viên / first name
     */
    public String getFirstName() { return firstName; }
    /**
     * Lấy họ của ứng viên.
     * EN: Get the last name.
     * @return họ của ứng viên / last name
     */
    public String getLastName() { return lastName; }
    /**
     * Lấy họ tên đầy đủ của ứng viên.
     * EN: Get the full name.
     * @return họ tên đầy đủ của ứng viên / full name
     */
    public String getFullName() { return firstName + " " + lastName; }
    /**
     * Nhập năm sinh gồm 4 chữ số, từ 1900 đến năm hiện tại.
     * EN: Read a four-digit birth year from 1900 to the current year.
     * @return năm sinh hợp lệ / valid birth year
     */
    public int getBirthYear() { return birthYear; }
    /**
     * Lấy địa chỉ.
     * EN: Get the address.
     * @return địa chỉ / address
     */
    public String getAddress() { return address; }
    /**
     * Nhập số điện thoại chỉ gồm chữ số và có ít nhất 10 chữ số.
     * EN: Read a phone number with at least 10 digits and no other characters.
     * @return số điện thoại hợp lệ / valid phone number
     */
    public String getPhone() { return phone; }
    /**
     * Nhập địa chỉ email và kiểm tra định dạng bằng biểu thức chính quy.
     * EN: Read an email and check its format with a pattern.
     * @return địa chỉ email hợp lệ / valid email address
     */
    public String getEmail() { return email; }
    /**
     * Lấy loại ứng viên: 0 là có kinh nghiệm, 1 là mới tốt nghiệp, 2 là thực tập sinh.
     * EN: Get the candidate type: 0 for Experience, 1 for Fresher, 2 for Intern.
     * @return loại ứng viên: 0 là có kinh nghiệm, 1 là mới tốt nghiệp, 2 là thực tập sinh / candidate type: 0 for Experience, 1 for Fresher, 2 for Intern
     */
    public int getType() { return type; }
}
