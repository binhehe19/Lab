import controller.StudentController;
import model.StudentManager;
import view.StudentView;

public class Main {
    /**
     * Khởi chạy chương trình quản lý bằng mô hình MVC.
     * @param args tham số dòng lệnh
     */
    public static void main(String[] args) {
        StudentManager model = new StudentManager();
        StudentView view = new StudentView();
        new StudentController(model, view).run();
    }
}
