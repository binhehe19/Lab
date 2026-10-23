package view;

import java.util.List;
import model.Candidate;

public class CandidateView {
    /**
     * Hiển thị các chức năng của chương trình và nhận lựa chọn.
     * EN: Show the menu and get a choice.
     * @return lựa chọn hợp lệ từ 1 đến 5 / valid choice from 1 to 5
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
     * EN: Print a message on the screen.
     * @param message thông báo hiển thị hoặc hướng dẫn nhập / message or input prompt
     */
    public void showMessage(String message) { System.out.println(message); }
    /**
     * Nhập chuỗi, loại bỏ khoảng trắng hai đầu và yêu cầu nhập lại nếu rỗng.
     * EN: Read text, remove spaces at both ends and ask again if empty.
     * @param message thông báo hiển thị hoặc hướng dẫn nhập / message or input prompt
     * @return chuỗi không rỗng đã loại bỏ khoảng trắng hai đầu / non-empty text with spaces removed at both ends
     */
    public String getString(String message) { return Utility.getString(message); }
    /**
     * Nhập số nguyên và yêu cầu nhập lại cho đến khi nằm trong giới hạn.
     * EN: Read a whole number. Ask again if it is outside the range.
     * @param message thông báo hiển thị hoặc hướng dẫn nhập / message or input prompt
     * @param min giá trị nhỏ nhất được chấp nhận / lowest allowed value
     * @param max giá trị lớn nhất được chấp nhận / highest allowed value
     * @return số nguyên trong khoảng từ min đến max, bao gồm hai đầu / whole number from min to max, including both limits
     */
    public int getInt(String message, int min, int max) { return Utility.getInt(message, min, max); }
    /**
     * Nhập năm sinh gồm 4 chữ số, từ 1900 đến năm hiện tại.
     * EN: Read a four-digit birth year from 1900 to the current year.
     * @return năm sinh hợp lệ / valid birth year
     */
    public int getBirthYear() { return Utility.getBirthYear("Birth year: "); }
    /**
     * Nhập lựa chọn Y hoặc N, không phân biệt chữ hoa và chữ thường.
     * EN: Read Y or N. Accept both upper and lower case.
     * @param message thông báo hiển thị hoặc hướng dẫn nhập / message or input prompt
     * @return true nếu chọn Y; false nếu chọn N / true for Y, false for N
     */
    public boolean getYesNo(String message) { return Utility.getYesNo(message); }
    /**
     * Nhập xếp loại tốt nghiệp: Excellence, Good, Fair hoặc Poor.
     * EN: Read a graduation rank: Excellence, Good, Fair or Poor.
     * @return xếp loại hợp lệ đã được chuẩn hóa / valid rank in the standard spelling
     */
    public String getRank() { return Utility.getRank("Graduation rank: "); }

    /**
     * Nhập số điện thoại chỉ gồm chữ số và có ít nhất 10 chữ số.
     * EN: Read a phone number with at least 10 digits and no other characters.
     * @return số điện thoại hợp lệ / valid phone number
     */
    public String getPhone() {
        return Utility.getPattern("Phone: ", "[0-9]{10,}", "Phone must have at least 10 digits.");
    }

    /**
     * Nhập địa chỉ email và kiểm tra định dạng bằng biểu thức chính quy.
     * EN: Read an email and check its format with a pattern.
     * @return địa chỉ email hợp lệ / valid email address
     */
    public String getEmail() {
        return Utility.getPattern("Email: ",
                "[A-Za-z0-9_+%-]+(?:\\.[A-Za-z0-9_+%-]+)*@[A-Za-z0-9-]+(?:\\.[A-Za-z0-9-]+)*\\.[A-Za-z]{2,}",
                "Invalid email! Example: annguyen@fpt.edu.vn");
    }

    /**
     * Hiển thị tên ứng viên theo từng nhóm: có kinh nghiệm, mới tốt nghiệp và thực tập sinh.
     * EN: Show candidate names in three groups: Experience, Fresher and Intern.
     * @param candidates danh sách ứng viên cần hiển thị / candidate list to show
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
     * EN: Show the search results.
     * @param candidates danh sách ứng viên cần hiển thị / candidate list to show
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
