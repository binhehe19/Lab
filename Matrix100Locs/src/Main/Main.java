/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Main;

/**
 *
 * @author Admin
 */
public class Main {
  
    public static void displayMenu(){
        System.out.println("===Calcu====");
        System.out.println("1. Add Matrix");
        System.out.println("2. Sub Matrix");
        System.out.println("3. Mul Matrix");
        System.out.println("4. Exit");
    }
    public static void main(String[] args) {
    Input inputer1 = new Input();
    Input inputer2 = new  Input();
    Matrix matrix, otherMatrix, result;
        while (true) {
            displayMenu();
            int choice = Check.getInt("Enter the Choice: ", 1, 4);
            switch (choice) {
                case 1:
                    System.out.println("===Add Matrix===");
                    int row = Check.getInt("Enter row the matrix1: ", 1, Integer.MAX_VALUE);
                    int col = Check.getInt("Enter col the matrix1: ", 1, Integer.MAX_VALUE);
                    matrix = inputer1.input("Enter the matrix1: ", row, col);
                    row = Check.getInt("Enter row the matrix2: ", 1, Integer.MAX_VALUE);
                    col = Check.getInt("Enter col the matrix2: ", 1, Integer.MAX_VALUE);
                    if (matrix.getRow() != row || matrix.getCol() != col) {
                        System.out.println("Row and Col of 2 matrix not equal");
                        break;
                    }
                    otherMatrix = inputer2.input("Enter the matrix2: ", row, col);
                    System.out.println("=====Result======");
                    System.out.println(matrix.toString());
                    System.out.println("+");
                    System.out.println(otherMatrix.toString());
                    System.out.println("=");
                    try {
                        result = matrix.add(otherMatrix);
                        System.out.println(result.toString());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 2:
                    System.out.println("===SubTract====");
                    row = Check.getInt("Enter row the matrix1: ", 1, Integer.MAX_VALUE);
                    col = Check.getInt("Enter col the matrix1: ", 1, Integer.MAX_VALUE);
                    matrix = inputer1.input("Enter the matrix1: ", row, col);
                    row = Check.getInt("Enter row the matrix2: ", 1, Integer.MAX_VALUE);
                    col = Check.getInt("Enter col the matrix2: ", 1, Integer.MAX_VALUE);
                    if (matrix.getRow() != row || matrix.getCol() != col) {
                        System.out.println("Row and Col of 2 matrix not equal");
                        break;
                    }
                    otherMatrix = inputer2.input("Enter the matrix2: ", row, col);
                    System.out.println("=====Result======");
                    System.out.println(matrix.toString());
                    System.out.println("-");
                    System.out.println(otherMatrix.toString());
                    System.out.println("=");
                    try {
                        result = matrix.sub(otherMatrix);
                        System.out.println(result.toString());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 3:
                    System.out.println("===Mul===");
                    row = Check.getInt("Enter row the matrix1: ", 1, Integer.MAX_VALUE);
                    col = Check.getInt("Enter col the matrix1: ", 1, Integer.MAX_VALUE);
                    matrix = inputer1.input("Enter the matrix1: ", row, col);
                    row = Check.getInt("Enter row the matrix2: ", 1, Integer.MAX_VALUE);
                    
                    if ( matrix.getCol() != row) {
                        System.out.println("Col of matrix1 not equal row of matrix2");
                        break;
                    }
                    col = Check.getInt("Enter col the matrix2: ", 1, Integer.MAX_VALUE);
                    otherMatrix = inputer2.input("Enter the matrix2: ", row, col);
                    System.out.println("=====Result======");
                    System.out.println(matrix.toString());
                    System.out.println("*");
                    System.out.println(otherMatrix.toString());
                    System.out.println("=");
                    try {
                        result = matrix.mul(otherMatrix);
                        System.out.println(result.toString());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 4:
                    System.exit(0);
            }

        }
    }
}
