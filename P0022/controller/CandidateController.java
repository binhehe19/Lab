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

    public CandidateController(CandidateManager model, CandidateView view) {
        this.model = model;
        this.view = view;
    }

    public void run() {
        while (true) {
            int choice = view.showMenu();
            switch (choice) {
                case 1:
                case 2:
                case 3:
                    // Menu 1,2,3 tuong ung type 0,1,2.
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

    public void createCandidate(int type) {
        do {
            String id;
            while (true) {
                id = view.getString("Candidate ID: ");
                if (!model.containsId(id)) break;
                view.showMessage("This ID already exists!");
            }

            // Buoc 1: Nhap cac thuoc tinh chung.
            String firstName = view.getString("First name: ");
            String lastName = view.getString("Last name: ");
            int birthYear = view.getBirthYear();
            String address = view.getString("Address: ");
            String phone = view.getPhone();
            String email = view.getEmail();

            // Buoc 2: Nhap thuoc tinh rieng va tao dung lop con.
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

            // Buoc 3: Luu vao Model, hoi tiep tuc.
            if (model.add(candidate)) {
                view.showMessage("Candidate added successfully!");
            } else {
                view.showMessage("This ID already exists!");
            }
        } while (view.getYesNo("Do you want to continue"));
        view.showCandidates(model.getAll());
    }

    public void searchCandidate() {
        view.showCandidates(model.getAll());
        if (model.getAll().isEmpty()) return;
        String keyword = view.getString("Input candidate name (First name or Last name): ");
        int type = view.getInt("Input type (0: Experience, 1: Fresher, 2: Intern): ", 0, 2);
        view.showSearchResults(model.search(keyword, type));
    }
}
