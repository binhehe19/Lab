/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

/**
 *
 * @author Admin
 */
public class Input {
    private Matrix matrix;

    public Matrix input(String msg, int row, int col) {
        int data[][] = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                data[i][j] = Check.getInt(msg + String.format("[%d][%d]", i + 1, j + 1), Integer.MIN_VALUE, Integer.MAX_VALUE);

            }
            try {
                matrix = new Matrix(data);

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        }
        return matrix;
    }
}
