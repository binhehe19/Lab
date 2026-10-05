package view;

import java.util.List;
import model.Candidate;

public class CandidateView {
    public int showMenu() {
        showMessage("\nCANDIDATE MANAGEMENT SYSTEM");
        showMessage("1. Experience");
        showMessage("2. Fresher");
        showMessage("3. Internship");
        showMessage("4. Searching");
        showMessage("5. Exit");
        return getInt("Please choose (1-5): ", 1, 5);
    }

    public void showMessage(String message) { System.out.println(message); }
    public String getString(String message) { return Utility.getString(message); }
    public int getInt(String message, int min, int max) { return Utility.getInt(message, min, max); }
    public int getBirthYear() { return Utility.getBirthYear("Birth year: "); }
    public boolean getYesNo(String message) { return Utility.getYesNo(message); }
    public String getRank() { return Utility.getRank("Graduation rank: "); }

    public String getPhone() {
        return Utility.getPattern("Phone: ", "[0-9]{10,}", "Phone must have at least 10 digits.");
    }

    public String getEmail() {
        return Utility.getPattern("Email: ",
                "[A-Za-z0-9_+%-]+(?:\\.[A-Za-z0-9_+%-]+)*@[A-Za-z0-9-]+(?:\\.[A-Za-z0-9-]+)*\\.[A-Za-z]{2,}",
                "Invalid email! Example: annguyen@fpt.edu.vn");
    }

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
