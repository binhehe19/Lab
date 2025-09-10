/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Wind
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        while (true) {
            int choice = AppManager.menu();
            switch (choice) {
                case 1:
                    AppManager.calculateSuperlativeEquation();
                    break;
                case 2:

                    AppManager.calculateQuadraticEquation();
                    break;
                case 3:
                    return;
            }
        }
    }


}
