/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

import static Main.Base.DEC;
import java.math.BigInteger;

/**
 *
 * @author Admin
 */
public class BaseNumber {

    private Base base; // Cơ số của số
    private String number; // Chuỗi biểu diễn số

    // Kiểm tra xem chuỗi số có hợp lệ với cơ số đã cho hay không
    public boolean isValueNumber(Base base, String number) { 
        switch (base) {
            case BIN:
                return number.matches("[01]+"); // Kiểm tra số nhị phân
            case DEC:
                return number.matches("[0-9]+"); // Kiểm tra số thập phân
            case HEX:
                return number.matches("[0-9A-F]+"); // Kiểm tra số thập lục phân
            default:
                throw new AssertionError(); // Ném ngoại lệ nếu cơ số không hợp lệ
        }
    }

    // Constructor để tạo đối tượng BaseNumber
    public BaseNumber(Base base, String number) throws Exception {
        if (isValueNumber(base, number)) { // Kiểm tra tính hợp lệ của số
            this.base = base; // Gán cơ số
            this.number = number; // Gán chuỗi số
        } else {
            throw new Exception("Invalid number of base"); // Ném ngoại lệ nếu số không hợp lệ
        }
    }

    // Getter cho cơ số
    public Base getBase() {
        return base;
    }

    // Setter cho cơ số, kiểm tra tính hợp lệ của số với cơ số mới
    public void setBase(Base base) throws Exception {
        if (isValueNumber(base, number)) {
            this.base = base;
        } else {
            throw new Exception("Invalid number of base");
        }
    }

    // Getter cho chuỗi số
    public String getNumber() {
        return number;
    }

    // Setter cho chuỗi số, kiểm tra tính hợp lệ của số với cơ số hiện tại
    public void setNumber(String number) throws Exception {
        if (isValueNumber(base, number)) {
            this.number = number;
        } else {
            throw new Exception("Invalid number of base");
        }
    }

    // Chuyển đổi số sang cơ số 10
    public BaseNumber convertToDec() throws Exception {
        BigInteger decNum = BigInteger.ZERO; // Khởi tạo số thập phân
        //decNum sẽ được sử dụng để cộng dồn từng giá trị của chữ số tại mỗi vị trí trong chuỗi number (sau khi chuyển đổi theo cơ số)
        BigInteger base = BigInteger.valueOf(this.base.getValue()); // Lấy giá trị cơ số lưu dữ trong thuộc tính this base chuyển đổi nó thành Biginter vd nếu là dec thì  2
        BigInteger power = BigInteger.ONE; // Khởi tạo lũy thừa
        for (int i = number.length() - 1; i >= 0; i--) { // Duyệt chuỗi số từ phải sang trái
            char digit = number.charAt(i); // Lấy từng ký tự số
            BigInteger digitNum = BigInteger.valueOf(Character.getNumericValue(digit)); // Chuyển ký tự sang giá trị số
            //vvalueOf nhận 1 giá trị kiểu long rồi chuyển nó thành kiểu BigInteger tránh bị tràn dữ liệu
            //Character.getNumericValue là 1 phương thức giúp chuyển đổi 1 số hoặc 1 ký tự thành 1 số thập phân tương ứng
            decNum = decNum.add(digitNum.multiply(power)); // Nhân giá  thừa và cộng vào số trị số với lũythập phân
            power = power.multiply(base); // Tăng lũy thừa
        }
        return new BaseNumber(Base.DEC, decNum.toString()); // Trả về số thập phân
    }

    // Chuyển đổi số từ cơ số 10 sang cơ số khác
    public BaseNumber convertDecOut(Base outBase) throws Exception {
        BigInteger decNum = new BigInteger(convertToDec().number); // Chuyển đổi sang cơ số 10 trước
        StringBuilder reverseResult = new StringBuilder(); // Chuỗi lưu kết quả đảo ngược
        while (decNum.compareTo(BigInteger.ZERO) != 0) { // Vòng lặp chia liên tục cho đến khi số bằng 0
            int remain = decNum.mod(BigInteger.valueOf(outBase.getValue())).intValue(); // Lấy phần dư khi chia cho cơ số đích outBase.getvalu là remain
            //outBase chứa thông tin cơ số cần đổi .getvalue() trả về giá trị số nguyên của cơ số, vs sang hệ BIN thì outBase.getValue() sẽ là 2
            decNum = decNum.divide(BigInteger.valueOf(outBase.getValue())); // Chia số cho cơ số đích
            //sau khi lấy dư decNum được cập nhật bằng cách chia cho outBASE ( divide phép chia lấy nguyên bỏ phần dư)
            switch (remain) { // Xử lý phần dư để chuyển sang ký tự tương ứng
                case 10:
                    reverseResult.insert(0, "A"); // Chèn ký tự vào đầu chuỗi
                    break;
                case 11:
                    reverseResult.insert(0, "B");
                    break;
                case 12:
                    reverseResult.insert(0, "C");
                    break;
                case 13:
                    reverseResult.insert(0, "D");
                    break;
                case 14:
                    reverseResult.insert(0, "E");
                    break;
                case 15:
                    reverseResult.insert(0, "F");
                    break;
                default:
                    reverseResult.insert(0, remain);//thêm trực tiếp vào chuỗi reverseResult từ 0-9
                    //Vì remain nằm trong default case, nó có giá trị từ 0 đến 9 (vì các trường hợp từ 10 đến 15 đã được xử lý riêng trong các case khác của switch).
            }
        }
        if (reverseResult.toString().isEmpty()) { // Nếu chuỗi rỗng, gán giá trị 0
            reverseResult.insert(0, "0");
        }
        BaseNumber result = new BaseNumber(outBase, reverseResult.toString()); // Tạo đối tượng BaseNumber với cơ số và chuỗi kết quả
        return result; // Trả về kết quả
    }

    // Chuyển đổi số sang cơ số đích
    public BaseNumber getOutPutByBase(Base outBase) throws Exception {
        return new BaseNumber(outBase, convertDecOut(outBase).number); // Chuyển đổi sang cơ số đích và trả về kết quả
    }  
    
}
