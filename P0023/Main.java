import controller.FruitController;
import model.FruitManager;
import view.FruitView;

public class Main {
    // Khởi chạy chương trình. / Start the program.
    public static void main(String[] args) {
        new FruitController(new FruitManager(), new FruitView()).run();
    }
}
