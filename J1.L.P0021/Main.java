import controller.StudentController;
import model.StudentManager;
import view.StudentView;

public class Main {
    public static void main(String[] args) {
        StudentManager model = new StudentManager();
        StudentView view = new StudentView();
        new StudentController(model, view).run();
    }
}
