/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

/**
 *
 * @author Admin
 */
public enum Base {
    DEC(10), HEX(16), BIN(2); // Khai báo các hằng số enum với giá trị cơ số tương ứng
    
    private int base; // Biến lưu giá trị cơ số

    private Base(int base) { // Constructor private để khởi tạo giá trị cơ số
        this.base = base;
    }

    public static Base getBase(int type) { // Phương thức static để lấy đối tượng Base từ giá trị số nguyên
        switch (type) {
            case 10:
                return DEC; // Trả về DEC nếu type là 10
            case 16:
                return HEX; // Trả về HEX nếu type là 16
            case 2:
                return BIN; // Trả về BIN nếu type là 2
            default:
                throw new AssertionError(); // Ném ngoại lệ nếu type không hợp lệ
        }
    }

    public int getValue() { // Phương thức getter để lấy giá trị cơ số
        return base;
    }
}
