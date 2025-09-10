package Main;

/**
 *
 * @author Admin
 */
public class Main {

    public static void main(String[] args) throws Exception {
        
        
        do { // Vòng lặp vô hạn để nhập số mới
            BaseInput inputer = new BaseInput(); // Tạo đối tượng BaseInput
            BaseNumber number = inputer.input(); // Nhập số từ người dùng
           
            do { // Vòng lặp để chọn cơ số đích và chuyển đổi
                try {
                    int choice = Check.getInt("1. for bin, 2. for dec, 3. for hex, 4. Exit\n" + "Enter the choice: ", "Just be 1->3", "Invalid", 1, 3); // Nhập lựa chọn cơ số đích

                    BaseNumber result = null; // Khởi tạo biến kết quả
                    switch (choice) {
                        case 1:
                            result = number.getOutPutByBase(Base.BIN); // Chuyển đổi sang nhị phân
                            break;
                        case 2:
                            result = number.getOutPutByBase(Base.DEC); // Chuyển đổi sang thập phân
                            break;
                        case 3:
                            result = number.getOutPutByBase(Base.HEX); // Chuyển đổi sang thập lục phân
                            break;
                       // Thoát chương trình
                    }
                    System.out.println("Number after convert: " + result.getNumber()); // In kết quả
                    break; // Thoát khỏi vòng lặp nội bộ sau khi chuyển đổi thành công
                } catch (Exception e) {
                    System.out.println(e.getMessage()); // In thông báo lỗi
                }
            } while (true);
        } while (true);
    }
}

