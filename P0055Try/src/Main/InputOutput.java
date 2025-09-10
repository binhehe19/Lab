/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

import java.util.Scanner;

/**
 *
 * @author Admin
 */
public class InputOutput {
    private  final Scanner scanner = new Scanner(System.in);

    public void displayMenu() {
        System.out.println("========= Doctor Management ==========");
        System.out.println("1. Add Doctor");
        System.out.println("2. Update Doctor");
        System.out.println("3. Delete Doctor");
        System.out.println("4. Search Doctor");
        System.out.println("5. Exit");
    }

   public String inputString(String prompt, boolean allowEmpty) {
        String input = null;
        boolean validInput = false;

        while (!validInput) {
            try {
                System.out.print(prompt);
                input = scanner.nextLine().trim();

                // Nếu không cho phép chuỗi rỗng
                if (!allowEmpty && input.isEmpty()) {
                    throw new Exception("Lỗi: Giá trị không được để trống!");
                }

                validInput = true; // Đầu vào hợp lệ
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return input;
    }
    
    public String inputDoctorCode(String prompt, DoctorManager manager) {
        String code = null;
        boolean validCode = false;

        while (!validCode) {
            code = inputString(prompt, false); // Bắt buộc phải nhập
            if (!manager.doctorExists(code)) {
                System.out.println("Lỗi: Không tìm thấy bác sĩ với mã này. Vui lòng nhập lại.");
            } else {
                validCode = true; // Mã bác sĩ hợp lệ
            }
        }
        return code;
    }


    public int inputInt(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
    }
   
    public int getInt(String prompt){
        System.out.println(prompt);
        return scanner.nextInt();
    }



    public void displayMessage(String message) {
        System.out.println(message);
    }

    void displayDoctor(Doctor doctor) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
