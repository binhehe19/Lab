import java.util.ArrayList;

public class Menu extends ArrayList<String> {
    public void print() {
        System.out.println("-----------------------------");
        for (String option : this) {
            System.out.println(option);
        }
        System.out.println("-----------------------------");
    }

    public int getUserChoices() {
        return Utility.getInt("Please enter choice (1-5): ", 1, 5);
    }
}
