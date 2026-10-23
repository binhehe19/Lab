package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CandidateManager {
    // Danh sách lưu được đối tượng thuộc cả ba lớp con của Candidate.
    // EN: This list can store all three candidate types.
    private final List<Candidate> candidates = new ArrayList<>();

    /**
     * Kiểm tra mã ứng viên đã tồn tại, không phân biệt chữ hoa và chữ thường.
     * EN: Check if the candidate ID exists. Ignore letter case.
     * @param id mã định danh / ID
     * @return true nếu mã đã tồn tại; false nếu chưa tồn tại / true if the ID exists, otherwise false
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
     * EN: Add a candidate if the ID does not exist.
     * @param candidate ứng viên cần thêm / candidate to add
     * @return true nếu thêm thành công; false nếu mã đã tồn tại / true if added, false if the ID already exists
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
     * EN: Return a copy of the list of all candidates.
     * @return danh sách mới chứa các đối tượng ứng viên hiện có / a new list of the current candidates
     */
    public List<Candidate> getAll() {
        return new ArrayList<>(candidates);
    }

    /**
     * Tìm ứng viên theo một phần tên hoặc họ và đúng loại được chọn.
     * EN: Find candidates by part of their first or last name and the chosen type.
     * @param keyword từ khóa tìm kiếm tên / name keyword
     * @param type loại ứng viên: 0 là có kinh nghiệm, 1 là mới tốt nghiệp, 2 là thực tập sinh / candidate type: 0 for Experience, 1 for Fresher, 2 for Intern
     * @return danh sách ứng viên phù hợp; danh sách rỗng nếu không tìm thấy / matching candidates, or an empty list if none are found
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
