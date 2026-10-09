package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CandidateManager {
    // Danh sách lưu được đối tượng thuộc cả ba lớp con của Candidate.
    private final List<Candidate> candidates = new ArrayList<>();

    /**
     * Kiểm tra mã ứng viên đã tồn tại, không phân biệt chữ hoa và chữ thường.
     * @param id mã định danh
     * @return true nếu mã đã tồn tại; false nếu chưa tồn tại
     */
    public boolean containsId(String id) {
        for (Candidate candidate : candidates) {
            if (candidate.getId().equalsIgnoreCase(id)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Thêm ứng viên vào danh sách nếu mã chưa tồn tại.
     * @param candidate ứng viên cần thêm
     * @return true nếu thêm thành công; false nếu mã đã tồn tại
     */
    public boolean add(Candidate candidate) {
        if (containsId(candidate.getId())) {
            return false;
        }
        candidates.add(candidate);
        return true;
    }

    /**
     * Tạo bản sao danh sách chứa tất cả ứng viên hiện có.
     * @return danh sách mới chứa các đối tượng ứng viên hiện có
     */
    public List<Candidate> getAll() {
        return new ArrayList<>(candidates);
    }

    /**
     * Tìm ứng viên theo một phần tên hoặc họ và đúng loại được chọn.
     * @param keyword từ khóa tìm kiếm tên
     * @param type loại ứng viên: 0 là có kinh nghiệm, 1 là mới tốt nghiệp, 2 là thực tập sinh
     * @return danh sách ứng viên phù hợp; danh sách rỗng nếu không tìm thấy
     */
    public List<Candidate> search(String keyword, int type) {
        List<Candidate> result = new ArrayList<>();
        String name = keyword.toLowerCase(Locale.ROOT);
        for (Candidate candidate : candidates) {
            boolean matchesName = candidate.getFirstName().toLowerCase(Locale.ROOT).contains(name)
                    || candidate.getLastName().toLowerCase(Locale.ROOT).contains(name);
            if (matchesName && candidate.getType() == type) {
                result.add(candidate);
            }
        }
        return result;
    }
}
