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
     * @param id mã định danh
     * @param firstName tên của ứng viên
     * @param lastName họ của ứng viên
     * @param birthYear năm sinh
     * @param address địa chỉ
     * @param phone số điện thoại
     * @param email địa chỉ email
     * @param type loại ứng viên: 0 là có kinh nghiệm, 1 là mới tốt nghiệp, 2 là thực tập sinh
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
     * @return mã định danh
     */
    public String getId() { return id; }
    /**
     * Lấy tên của ứng viên.
     * @return tên của ứng viên
     */
    public String getFirstName() { return firstName; }
    /**
     * Lấy họ của ứng viên.
     * @return họ của ứng viên
     */
    public String getLastName() { return lastName; }
    /**
     * Lấy họ tên đầy đủ của ứng viên.
     * @return họ tên đầy đủ của ứng viên
     */
    public String getFullName() { return firstName + " " + lastName; }
    /**
     * Nhập năm sinh gồm 4 chữ số, từ 1900 đến năm hiện tại.
     * @return năm sinh hợp lệ
     */
    public int getBirthYear() { return birthYear; }
    /**
     * Lấy địa chỉ.
     * @return địa chỉ
     */
    public String getAddress() { return address; }
    /**
     * Nhập số điện thoại chỉ gồm chữ số và có ít nhất 10 chữ số.
     * @return số điện thoại hợp lệ
     */
    public String getPhone() { return phone; }
    /**
     * Nhập địa chỉ email và kiểm tra định dạng bằng biểu thức chính quy.
     * @return địa chỉ email hợp lệ
     */
    public String getEmail() { return email; }
    /**
     * Lấy loại ứng viên: 0 là có kinh nghiệm, 1 là mới tốt nghiệp, 2 là thực tập sinh.
     * @return loại ứng viên: 0 là có kinh nghiệm, 1 là mới tốt nghiệp, 2 là thực tập sinh
     */
    public int getType() { return type; }
}
