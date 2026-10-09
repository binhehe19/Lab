import controller.CandidateController;
import model.CandidateManager;
import view.CandidateView;

public class Main {
    /**
     * Khởi chạy chương trình quản lý bằng mô hình MVC.
     * @param args tham số dòng lệnh
     */
    public static void main(String[] args) {
        CandidateManager model = new CandidateManager();
        CandidateView view = new CandidateView();
        new CandidateController(model, view).run();
    }
}
