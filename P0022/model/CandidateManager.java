package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CandidateManager {
    // Mot danh sach luu duoc ca ba lop con cua Candidate.
    private final List<Candidate> candidates = new ArrayList<>();

    public boolean containsId(String id) {
        for (Candidate candidate : candidates) {
            if (candidate.getId().equalsIgnoreCase(id)) {
                return true;
            }
        }
        return false;
    }

    public boolean add(Candidate candidate) {
        if (containsId(candidate.getId())) {
            return false;
        }
        candidates.add(candidate);
        return true;
    }

    public List<Candidate> getAll() {
        return new ArrayList<>(candidates);
    }

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
