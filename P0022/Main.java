import controller.CandidateController;
import model.CandidateManager;
import view.CandidateView;

public class Main {
    public static void main(String[] args) {
        CandidateManager model = new CandidateManager();
        CandidateView view = new CandidateView();
        new CandidateController(model, view).run();
    }
}
