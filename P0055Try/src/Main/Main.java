/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Main;


import java.util.HashMap;

/**
 *
 * @author Admin
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
      DoctorManager manager = new DoctorManager();
      InputOutput io = new InputOutput();
      
      while(true){
          try{
              io.displayMenu();
              int choice = io.inputInt("Enter the choice: ");
              switch(choice){
                  case 1:
                      String code = io.inputString("Enter the code: ", false);
                      String name = io.inputString("Enter the name: ", false);
                      String specialization =io.inputString("Enter the specialization: ", false);
                      int availability = io.inputInt("Enter the availability: ");
                      if(availability < 0){
                          Message.invalidAvailabilityFormat();
                          break;
                      }
                      
                      if(manager.addDoctor(new Doctor(code, name, specialization, availability))){
                          Message.doctorAddedSuccess();
                      }else{
                          Message.doctorCodeDuplicate(code);
                      }
                      break;
                  case 2:
                      if(manager.doctorDB.isEmpty()){
                          System.out.println("No data. Returning to main menu");
                          break;
                      }
                      code =io.inputDoctorCode("Enter the code: ",manager);
                      name =io.inputString("Enter the name: ",true);
                      specialization =io.inputString("Enter the specialization: ",true);
                      availability =io.inputInt("Enter the availability: ");
                      if(manager.updateDoctor(code, name, specialization, availability)){
                          Message.doctorUpdateSuccess();
                      }else{
                          Message.doctorNotFound();
                      }
                      break;
                  case 3:  // Trường hợp xóa bác sĩ
                    
                  code = io.inputString("Enter the code: ", false);
                    if (!manager.deleteDoctor(code)) {
                        if (manager.doctorDB.isEmpty()) {  // Kiểm tra nếu không có dữ liệu
                            System.out.println("No data available. Exiting the program.");
                            // Thoát chương trình
                        } else {
                            Message.doctorNotFound();
                        }
                    } else {
                        Message.doctorDeleteSuccess();
                    }
                    break;
                  case 4:
                      Message.mess("=====Search Doctor======");
                      String searchTerm = io.inputString("Enter the text: ",true);
                      HashMap<String, Doctor> found = manager.searchDoctor(searchTerm);
                      if(!found.isEmpty()){
                          Message.mess("=====Result=====");
                          Message.mess("code     name          specialization    availability");
                          for(Doctor d : found.values()){
                              Message.mess(d.toString());
                          }
                      }else{
                          Message.notFoundDoctor();
                      }
                      break;
                  case 5:
                      Message.exitingProgram();
                      return;
                 default:
                     Message.invalidOption();
              }
          }catch(Exception e){
              Message.displayError(e.getMessage());
          }
      }
    }
}