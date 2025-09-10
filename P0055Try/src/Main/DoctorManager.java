/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

import java.util.HashMap;

/**
 *
 * @author Admin
 */
public class DoctorManager {
    HashMap<String, Doctor> doctorDB = new HashMap<>();

    public boolean addDoctor(Doctor doctor) throws Exception {
        if (doctorDB.containsKey(doctor.getCode())) {
            return false;  // Trả về false nếu mã bác sĩ đã tồn tại
        }
        doctorDB.put(doctor.getCode(), doctor);
        return true;  // Trả về true nếu thêm thành công
    }

    public boolean updateDoctor(String code, String name, String specialization, int availability) throws Exception {
        Doctor doctor = doctorDB.get(code);
        if (doctor == null) {
            return false;  // Trả về false nếu không tìm thấy mã bác sĩ
        }

        // Nếu các giá trị không rỗng hoặc hợp lệ thì mới cập nhật
        if (!name.trim().isEmpty()) {
            doctor.setName(name);
        }
        if (!specialization.trim().isEmpty()) {
            doctor.setSpecialization(specialization);
        }
        if (availability >= 0) {
            doctor.setAvailability(availability);
        }

        return true;  // Trả về true nếu cập nhật thành công
    }

  
    public boolean deleteDoctor(String code) throws Exception {
        // Kiểm tra nếu không có dữ liệu trong doctorDB
        if (doctorDB.isEmpty()) {
            return false;  // Trả về false nếu không có dữ liệu
        }

        if (!doctorDB.containsKey(code)) {
            return false;  // Trả về false nếu không tìm thấy mã bác sĩ
        }

        doctorDB.remove(code);
        return true;  // Trả về true nếu xóa thành công
    }

    public HashMap<String, Doctor> searchDoctor(String searchTerm) {
        // Kiểm tra nếu doctorDB rỗng
        if (doctorDB.isEmpty()) {
            System.out.println("No data available.");  // Thông báo nếu không có dữ liệu
            return new HashMap<>();  // Trả về một HashMap rỗng
        }

        HashMap<String, Doctor> result = new HashMap<>();

        if (searchTerm.trim().isEmpty()) {
            // Nếu không nhập gì, trả về tất cả các bác sĩ
            return doctorDB;
        }

        for (Doctor doctor : doctorDB.values()) {
            // Kiểm tra nếu mã, tên, hoặc chuyên môn của bác sĩ chứa chuỗi tìm kiếm
            if (doctor.getCode().toLowerCase().contains(searchTerm.toLowerCase())
                    || doctor.getName().toLowerCase().contains(searchTerm.toLowerCase())
                    || doctor.getSpecialization().toLowerCase().contains(searchTerm.toLowerCase())) {
                result.put(doctor.getCode(), doctor);  // Thêm bác sĩ vào kết quả tìm kiếm
            }
        }

        return result;  // Trả về danh sách các bác sĩ tìm thấy
    }

    public boolean doctorExists(String code) {
        return doctorDB.containsKey(code);  // Kiểm tra nếu bác sĩ có tồn tại trong DB
    }
}
