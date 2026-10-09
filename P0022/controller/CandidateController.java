package controller;

import model.Candidate;
import model.CandidateManager;
import model.Experience;
import model.Fresher;
import model.Intern;
import view.CandidateView;

public class CandidateController {
    private final CandidateManager model;
    private final CandidateView view;

    /**
     * Khởi tạo đối tượng CandidateController với thông tin được cung cấp.
     * @param model đối tượng quản lý dữ liệu
     * @param view đối tượng giao diện nhập và xuất
     */
    public CandidateController(CandidateManager model, CandidateView view) {
        this.model = model;
        this.view = view;
    }

    /**
     * Hiển thị menu và xử lý chức năng được chọn cho đến khi thoát.
     */
    public void run() {
        while (true) {
            int choice = view.showMenu();
            switch (choice) {
                case 1:
                case 2:
                case 3:
                    // Lựa chọn menu 1, 2, 3 tương ứng với loại ứng viên 0, 1, 2.
                    createCandidate(choice - 1);
                    break;
                case 4:
                    searchCandidate();
                    break;
                case 5:
                    view.showMessage("Goodbye!");
                    return;
            }
        }
    }

    /**
     * Nhập thông tin chung và riêng, tạo ứng viên theo loại và lưu vào danh sách.
     * @param type loại ứng viên: 0 là có kinh nghiệm, 1 là mới tốt nghiệp, 2 là thực tập sinh
     */
    public void createCandidate(int type) {
        do {
            String id;
            while (true) {
                id = view.getString("Candidate ID: ");
                if (!model.containsId(id)) break;
                view.showMessage("This ID already exists!");
            }

            // Bước 1: Nhập các thuộc tính chung của ứng viên.
            String firstName = view.getString("First name: ");
            String lastName = view.getString("Last name: ");
            int birthYear = view.getBirthYear();
            String address = view.getString("Address: ");
            String phone = view.getPhone();
            String email = view.getEmail();

            // Bước 2: Nhập thuộc tính riêng và tạo đối tượng thuộc lớp con phù hợp.
            Candidate candidate;
            if (type == 0) {
                int expInYear = view.getInt("Years of experience: ", 0, 100);
                String proSkill = view.getString("Professional skill: ");
                candidate = new Experience(id, firstName, lastName, birthYear,
                        address, phone, email, expInYear, proSkill);
            } else if (type == 1) {
                String graduationDate = view.getString("Graduation date: ");
                String graduationRank = view.getRank();
                String education = view.getString("Education (university): ");
                candidate = new Fresher(id, firstName, lastName, birthYear,
                        address, phone, email, graduationDate, graduationRank, education);
            } else {
                String majors = view.getString("Majors: ");
                String semester = view.getString("Semester: ");
                String universityName = view.getString("University name: ");
                candidate = new Intern(id, firstName, lastName, birthYear,
                        address, phone, email, majors, semester, universityName);
            }

            // Bước 3: Lưu ứng viên vào model và hỏi có tiếp tục hay không.
            if (model.add(candidate)) {
                view.showMessage("Candidate added successfully!");
            } else {
                view.showMessage("This ID already exists!");
            }
        } while (view.getYesNo("Do you want to continue"));
        view.showCandidates(model.getAll());
    }

    /**
     * Nhập từ khóa tên và loại ứng viên, sau đó hiển thị kết quả tìm kiếm.
     */
    public void searchCandidate() {
        view.showCandidates(model.getAll());
        if (model.getAll().isEmpty()) return;
        String keyword = view.getString("Input candidate name (First name or Last name): ");
        int type = view.getInt("Input type (0: Experience, 1: Fresher, 2: Intern): ", 0, 2);
        view.showSearchResults(model.search(keyword, type));
    }
}
