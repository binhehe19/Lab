import controller.CandidateController;
import model.CandidateManager;
import view.CandidateView;

public class Main {
    /**
     * Khởi chạy chương trình quản lý bằng mô hình MVC.
     * EN: Start the management program using MVC.
     * @param args tham số dòng lệnh / command-line arguments
     */
    public static void main(String[] args) {
        CandidateManager model = new CandidateManager();
        CandidateView view = new CandidateView();
        new CandidateController(model, view).run();
    }
}
