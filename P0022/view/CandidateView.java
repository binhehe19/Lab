package view;

import java.util.List;
import model.Candidate;

public class CandidateView {
    /**
     * Hiển thị các chức năng của chương trình và nhận lựa chọn.
     * @return lựa chọn hợp lệ từ 1 đến 5
     */
    public int showMenu() {
        showMessage("\nCANDIDATE MANAGEMENT SYSTEM");
        showMessage("1. Experience");
        showMessage("2. Fresher");
        showMessage("3. Internship");
        showMessage("4. Searching");
        showMessage("5. Exit");
        return getInt("Please choose (1-5): ", 1, 5);
    }

    /**
     * In thông báo ra màn hình.
     * @param message thông báo hiển thị hoặc hướng dẫn nhập
     */
    public void showMessage(String message) { System.out.println(message); }
    /**
     * Nhập chuỗi, loại bỏ khoảng trắng hai đầu và yêu cầu nhập lại nếu rỗng.
     * @param message thông báo hiển thị hoặc hướng dẫn nhập
     * @return chuỗi không rỗng đã loại bỏ khoảng trắng hai đầu
     */
    public String getString(String message) { return Utility.getString(message); }
    /**
     * Nhập số nguyên và yêu cầu nhập lại cho đến khi nằm trong giới hạn.
     * @param message thông báo hiển thị hoặc hướng dẫn nhập
     * @param min giá trị nhỏ nhất được chấp nhận
     * @param max giá trị lớn nhất được chấp nhận
     * @return số nguyên trong khoảng từ min đến max, bao gồm hai đầu
     */
    public int getInt(String message, int min, int max) { return Utility.getInt(message, min, max); }
    /**
     * Nhập năm sinh gồm 4 chữ số, từ 1900 đến năm hiện tại.
     * @return năm sinh hợp lệ
     */
    public int getBirthYear() { return Utility.getBirthYear("Birth year: "); }
    /**
     * Nhập lựa chọn Y hoặc N, không phân biệt chữ hoa và chữ thường.
     * @param message thông báo hiển thị hoặc hướng dẫn nhập
     * @return true nếu chọn Y; false nếu chọn N
     */
    public boolean getYesNo(String message) { return Utility.getYesNo(message); }
    /**
     * Nhập xếp loại tốt nghiệp: Excellence, Good, Fair hoặc Poor.
     * @return xếp loại hợp lệ đã được chuẩn hóa
     */
    public String getRank() { return Utility.getRank("Graduation rank: "); }

    /**
     * Nhập số điện thoại chỉ gồm chữ số và có ít nhất 10 chữ số.
     * @return số điện thoại hợp lệ
     */
    public String getPhone() {
        return Utility.getPattern("Phone: ", "[0-9]{10,}", "Phone must have at least 10 digits.");
    }

    /**
     * Nhập địa chỉ email và kiểm tra định dạng bằng biểu thức chính quy.
     * @return địa chỉ email hợp lệ
     */
    public String getEmail() {
        return Utility.getPattern("Email: ",
                "[A-Za-z0-9_+%-]+(?:\\.[A-Za-z0-9_+%-]+)*@[A-Za-z0-9-]+(?:\\.[A-Za-z0-9-]+)*\\.[A-Za-z]{2,}",
                "Invalid email! Example: annguyen@fpt.edu.vn");
    }

    /**
     * Hiển thị tên ứng viên theo từng nhóm: có kinh nghiệm, mới tốt nghiệp và thực tập sinh.
     * @param candidates danh sách ứng viên cần hiển thị
     */
    public void showCandidates(List<Candidate> candidates) {
        String[] titles = {"EXPERIENCE", "FRESHER", "INTERN"};
        showMessage("\nList of candidates:");
        for (int type = 0; type < titles.length; type++) {
            showMessage("=========== " + titles[type] + " CANDIDATE ===========");
            for (Candidate candidate : candidates) {
                if (candidate.getType() == type) {
                    showMessage(candidate.getFullName());
                }
            }
        }
        if (candidates.isEmpty()) showMessage("Candidate list is empty.");
    }

    /**
     * Hiển thị danh sách kết quả tìm kiếm.
     * @param candidates danh sách ứng viên cần hiển thị
     */
    public void showSearchResults(List<Candidate> candidates) {
        if (candidates.isEmpty()) {
            showMessage("No candidates found.");
            return;
        }
        showMessage("The candidates found:");
        showMessage("Name | Birth year | Address | Phone | Email | Type");
        for (Candidate candidate : candidates) {
            System.out.printf("%s | %d | %s | %s | %s | %d%n",
                    candidate.getFullName(), candidate.getBirthYear(), candidate.getAddress(),
                    candidate.getPhone(), candidate.getEmail(), candidate.getType());
        }
    }
}
