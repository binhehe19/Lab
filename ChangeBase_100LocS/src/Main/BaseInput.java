/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

/**
 *
 * @author Admin
 */
// Lớp để nhập liệu cơ số và số
//phương thức input() nhận đầu vào từ người dùng, xác định cơ số của số, và tạo một đối tượng BaseNumber để lưu trữ cả số và cơ số của nó. Đối tượng này sau đó được trả về để sử dụng trong các phần khác của chương trình.
public class BaseInput {

    private Base base; // Biến lưu cơ số
    private String number; // Biến lưu chuỗi số

    public BaseNumber input() throws Exception { // Phương thức nhập liệu từ người dùng
        int choice = Check.getInt("1. for bin, 2. for dec, 3. for hex\n" + "Enter the choice: ", "Just 1 -> 3", "Invalid", 1, 3); // Nhập lựa chọn cơ số

        switch (choice) {
            case 1:
                base = Base.BIN; // Gán giá trị của Base.BIN cho biến base  ( thiết lập biến base để chỉ ra rằng số được nhập vào là một số nhị phân. )
                number = Check.getString("Enter the number: ", "Invalid", "[01]+"); // Nhập số nhị phân
                break;
            case 2:
                base = Base.DEC; // Gán cơ số thập phân
                number = Check.getString("Enter the number: ", "Invalid", "[0-9]+"); // Nhập số thập phân
                break;
            case 3:
                base = Base.HEX; // Gán cơ số thập lục phân
                number = Check.getString("Enter the number: ", "Invalid", "[0-9A-F]+"); // Nhập số thập lục phân
                break;
            default:
                throw new AssertionError(); // Ném ngoại lệ nếu lựa chọn không hợp lệ
        }
        return new BaseNumber(base, number);// Trả về đối tượng BaseNumber
    }
}
