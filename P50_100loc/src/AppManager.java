
import java.util.ArrayList;
import java.util.List;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Wind
 */
public class AppManager {

    public static  int menu(){
        System.out.println("========= Equation Program =========");
        System.out.println("1. Calculate Superlative Equation");
        System.out.println("2. Calculate Quadratic Equation");
        System.out.println("3. Exit");
        System.out.print("Please choice one option: ");
        int choice = check.checkInt(1,3);
        return choice;
    }
    
        public static void calculateSuperlativeEquation() {
        float a = check.checkin("Enter coefficient a: "); // Assuming check.checkin() returns a valid float
        float b = check.checkin("Enter coefficient b: ");

        // Tính nghiệm phương trình bậc nhất
        List<Float> result = Manager.calculateEquation(a, b);

        if (result != null) {
            // Nếu danh sách có đủ phần tử (nếu bạn thực sự cần xử lý sublist)
            displayResults(result);
        }
        else {
            displayResults(result);
            result = new ArrayList<>();
        }

        result.add(a);
        result.add(b);
        checkAndDisplayNumbers(result); // Display even, odd, and perfect square numbers
    }

    // Method to calculate and display result for quadratic equation
    public static void calculateQuadraticEquation() {
        float a = check.checkin("Enter coefficient a: "); // Assuming check.checkDouble() returns a valid float
        float b = check.checkin("Enter coefficient b: ");
        float c = check.checkin("Enter coefficient c: ");

        List<Float> result = Manager.calculateQuadraticEquation(a, b, c);// Calculate quadratic equation
        if (result != null) displayResults(result); // Display solution

        else{
            displayResults(result);
            result = new ArrayList<>();
        }
            result.add(a);
            result.add(b);
            result.add(c);

        checkAndDisplayNumbers(result); // Display even, odd, and perfect square numbers
    }

    // Helper function to display results
    public static void displayResults(List<Float> result) {
        if (result == null) {
            System.out.println("No solution.");
        } else if (result.isEmpty()) {
            System.out.println("Infinitely many solutions.");
        } else {
            System.out.print("Solution(s):");

            for (int i = 0; i < result.size(); i++) {
                if (i != result.size() - 1) {


                    System.out.printf("x%d = %.3f %s", i + 1, result.get(i), "and ");

                } else {
                    System.out.printf("x%d = %.3f", i + 1, result.get(i));
                }
            }

            System.out.println(); // New line after solutions
        }
    }

    // Function to check and display even, odd, and perfect square numbers
    public static void checkAndDisplayNumbers(List<Float> numbers) {
        if (numbers == null || numbers.isEmpty()) {
            return;
        }

        // Display odd numbers
        System.out.print("Odd Number(s): ");
        boolean firstOdd = true;  // Track first element to handle commas
        for (Float number : numbers) {
            if (Manager.isOdd(number)) {
                if (!firstOdd) {
                    System.out.print(", ");
                }
                System.out.print(number);
                firstOdd = false;
            }
        }
        System.out.println();


        // Display even numbers
        System.out.print("Number is Even: ");
        boolean firstEven = true;
        for (Float number : numbers) {
            if (Manager.isEven(number)) {
                if (!firstEven) {
                    System.out.print(", ");
                }
                System.out.print(number);
                firstEven = false;
            }
        }


        System.out.println();
        System.out.print("Number is Equare:  ");
        // Display perfect square numbers
        boolean firstPerfectSquare = true;
        for (Float number : numbers) {
            if (Manager.isPerfectSquare(number)) {
                if (!firstPerfectSquare) {
                    System.out.print(", ");
                }
                System.out.print(number);
                firstPerfectSquare = false;
            }
        }


        System.out.println();
    }

}
