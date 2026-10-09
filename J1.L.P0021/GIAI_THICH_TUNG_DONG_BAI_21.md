# Giải thích từng dòng code bài J1.L.P0021

Tài liệu bám theo 7 file Java hiện tại và số dòng thật. Mỗi dòng không rỗng, kể cả comment và dấu ngoặc, được liệt kê; dòng trống chỉ phân cách. Số dòng có thể thay đổi nếu code được sửa thêm.

## Hiểu dữ liệu trước

Một Student là một lần đăng ký môn trong một học kỳ, không phải toàn bộ hồ sơ một người. `(1, An, 1, Java)`, `(1, An, 2, Java)`, `(1, An, 2, .Net)` là 3 bản ghi của 1 mã. Báo cáo: `An | Java | 2` và `An | .Net | 1`.

Không trùng bộ ba `(id, semester, course)`. Cùng mã giữ cùng tên; đổi tên áp dụng tất cả bản ghi cùng mã, đổi môn/học kỳ hoặc xóa chỉ áp dụng bản ghi chọn.

Luồng: `Main → Controller.run → View/Utility nhập → StudentManager xử lý → View in`. MVC là lựa chọn tổ chức hiện tại; viết ít lớp hơn cũng được cho console nhỏ nhưng sẽ trộn nhập/in và xử lý.

- `==` với int so giá trị; với đối tượng so tham chiếu. String so nội dung bằng equals/equalsIgnoreCase.
- return kết thúc hàm; break thoát vòng/switch gần nhất; continue chuyển lượt vòng lặp.
- final trên tham chiếu cấm gán lại biến, không làm đối tượng/List bất biến.
- Java truyền giá trị của tham chiếu đối tượng. List kết quả có thể dùng chung đối tượng với List gốc.
- `;` kết thúc câu lệnh, `{}` xác định khối, `()` chứa tham số/đối số/điều kiện, `<>` chứa kiểu generic. Thụt đầu dòng giúp đọc, không xác định khối như Python.
- Các cách khác nêu ở đây có thể đúng; lựa chọn hiện tại phụ thuộc mục tiêu bài này, không có nghĩa cách khác luôn sai.

## Main.java

| Dòng | Code | Làm gì, vì sao dùng và cách khác |
| --- | --- | --- |
| 1 | `import controller.StudentController;` | Cho dùng tên ngắn của controller.StudentController. Có thể viết tên đầy đủ mỗi chỗ dùng nhưng dài hơn. Import không tạo đối tượng hay chạy hàm; import cụ thể rõ phụ thuộc hơn *. |
| 2 | `import model.StudentManager;` | Cho dùng tên ngắn của model.StudentManager. Có thể viết tên đầy đủ mỗi chỗ dùng nhưng dài hơn. Import không tạo đối tượng hay chạy hàm; import cụ thể rõ phụ thuộc hơn *. |
| 3 | `import view.StudentView;` | Cho dùng tên ngắn của view.StudentView. Có thể viết tên đầy đủ mỗi chỗ dùng nhưng dài hơn. Import không tạo đối tượng hay chạy hàm; import cụ thể rõ phụ thuộc hơn *. |
| 5 | `public class Main {` | Khai báo lớp Main truy cập được từ package khác; { mở thân. Class gom dữ liệu/hành vi, không cần kế thừa khi chưa có quan hệ cha/con. |
| 6 | `/**` | Mở comment tài liệu. Không thực thi; /** dùng với @param/@return, // thích hợp ghi chú ngắn. |
| 7 | `* Khởi chạy chương trình quản lý bằng mô hình MVC.` | Nội dung comment: Khởi chạy chương trình quản lý bằng mô hình MVC. Không thay đổi chương trình. |
| 8 | `* @param args tham số dòng lệnh` | Mô tả tham số args. Tag khớp tên trong chữ ký, không tạo/kiểm tra tham số khi chạy. |
| 9 | `*/` | Đóng comment tài liệu; dòng sau được xử lý là code trở lại. |
| 10 | `public static void main(String[] args) {` | Khai báo phương thức main. public để JVM truy cập, static không cần new Main, void không trả giá trị. String[] args nhận đối số dòng lệnh, hiện chưa dùng. |
| 11 | `StudentManager model = new StudentManager();` | Tạo manager với danh sách rỗng và giữ tham chiếu trong model. Tạo một lần để mọi chức năng dùng chung dữ liệu, không tạo lại làm mất danh sách. |
| 12 | `StudentView view = new StudentView();` | Tạo giao diện console. Tách view để xử lý dữ liệu không phụ thuộc cách nhập/in. |
| 13 | `new StudentController(model, view).run();` | Tạo controller nhận cùng model/view rồi chạy menu. Có thể tách biến controller để debug; viết gộp vì Main không dùng lại biến đó. |
| 14 | `}` | Đóng khối gần nhất: if, vòng lặp, hàm hoặc lớp theo vị trí. Biến cục bộ trong khối hết phạm vi tại đây. |
| 15 | `}` | Đóng khối gần nhất: if, vòng lặp, hàm hoặc lớp theo vị trí. Biến cục bộ trong khối hết phạm vi tại đây. |

## model/Student.java

| Dòng | Code | Làm gì, vì sao dùng và cách khác |
| --- | --- | --- |
| 1 | `package model;` | Đặt lớp trong package model, tương ứng thư mục dự án. Bỏ package phải sửa import/cách gọi; dùng package để tổ chức và tránh trùng tên lớp. |
| 3 | `/** Mỗi đối tượng lưu một môn học của sinh viên trong một học kỳ. */` | Mở comment tài liệu và đóng ngay cùng dòng. Không thực thi; /** dùng với @param/@return, // thích hợp ghi chú ngắn. |
| 4 | `public class Student {` | Khai báo lớp Student truy cập được từ package khác; { mở thân. Class gom dữ liệu/hành vi, không cần kế thừa khi chưa có quan hệ cha/con. |
| 5 | `private int id;` | Trường private lưu mã sinh viên. int phù hợp số nguyên, không cần double có phần thập phân. Không final vì cần gán/sửa. Getter/setter che trường; hiện setter chưa xác thực. |
| 6 | `private String name;` | Trường private lưu tên sinh viên. String giữ chuỗi chữ; char chỉ một ký tự. Không final vì cần gán/sửa. Getter/setter che trường; hiện setter chưa xác thực. |
| 7 | `private int semester;` | Trường private lưu học kỳ. int phù hợp số nguyên, không cần double có phần thập phân. Không final vì cần gán/sửa. Getter/setter che trường; hiện setter chưa xác thực. |
| 8 | `private String course;` | Trường private lưu môn học. String giữ chuỗi chữ; char chỉ một ký tự. Không final vì cần gán/sửa. Getter/setter che trường; hiện setter chưa xác thực. |
| 10 | `/**` | Mở comment tài liệu. Không thực thi; /** dùng với @param/@return, // thích hợp ghi chú ngắn. |
| 11 | `* Khởi tạo đối tượng Student chưa có thông tin.` | Nội dung comment: Khởi tạo đối tượng Student chưa có thông tin. Không thay đổi chương trình. |
| 12 | `*/` | Đóng comment tài liệu; dòng sau được xử lý là code trở lại. |
| 13 | `public Student() {` | Constructor cùng tên lớp, không có kiểu trả về, chạy khi new. Constructor rỗng chưa dùng trong luồng hiện tại, cho int=0/String=null. Có thể bỏ nếu chỉ muốn bản ghi đủ thông tin. |
| 14 | `}` | Đóng khối gần nhất: if, vòng lặp, hàm hoặc lớp theo vị trí. Biến cục bộ trong khối hết phạm vi tại đây. |
| 16 | `/**` | Mở comment tài liệu. Không thực thi; /** dùng với @param/@return, // thích hợp ghi chú ngắn. |
| 17 | `* Khởi tạo đối tượng Student với thông tin được cung cấp.` | Nội dung comment: Khởi tạo đối tượng Student với thông tin được cung cấp. Không thay đổi chương trình. |
| 18 | `* @param id mã định danh` | Mô tả tham số id. Tag khớp tên trong chữ ký, không tạo/kiểm tra tham số khi chạy. |
| 19 | `* @param name tên sinh viên` | Mô tả tham số name. Tag khớp tên trong chữ ký, không tạo/kiểm tra tham số khi chạy. |
| 20 | `* @param semester học kỳ` | Mô tả tham số semester. Tag khớp tên trong chữ ký, không tạo/kiểm tra tham số khi chạy. |
| 21 | `* @param course môn học` | Mô tả tham số course. Tag khớp tên trong chữ ký, không tạo/kiểm tra tham số khi chạy. |
| 22 | `*/` | Đóng comment tài liệu; dòng sau được xử lý là code trở lại. |
| 23 | `public Student(int id, String name, int semester, String course) {` | Constructor cùng tên lớp, không có kiểu trả về, chạy khi new. Nhận dữ liệu hoặc phụ thuộc để gán trong thân; tiện hơn tạo rỗng rồi gọi nhiều setter. |
| 24 | `this.id = id;` | Gán tham số vào trường của đối tượng; this phân biệt hai tên giống nhau. Không this sẽ tự gán tham số mà không đổi trường. |
| 25 | `this.name = name;` | Gán tham số vào trường của đối tượng; this phân biệt hai tên giống nhau. Không this sẽ tự gán tham số mà không đổi trường. |
| 26 | `this.semester = semester;` | Gán tham số vào trường của đối tượng; this phân biệt hai tên giống nhau. Không this sẽ tự gán tham số mà không đổi trường. |
| 27 | `this.course = course;` | Gán tham số vào trường của đối tượng; this phân biệt hai tên giống nhau. Không this sẽ tự gán tham số mà không đổi trường. |
| 28 | `}` | Đóng khối gần nhất: if, vòng lặp, hàm hoặc lớp theo vị trí. Biến cục bộ trong khối hết phạm vi tại đây. |
| 30 | `/**` | Mở comment tài liệu. Không thực thi; /** dùng với @param/@return, // thích hợp ghi chú ngắn. |
| 31 | `* Lấy mã định danh.` | Nội dung comment: Lấy mã định danh. Không thay đổi chương trình. |
| 32 | `* @return mã định danh` | Mô tả giá trị trả về, không thay thế return. void và constructor không có @return. |
| 33 | `*/` | Đóng comment tài liệu; dòng sau được xử lý là code trở lại. |
| 34 | `public int getId() {` | Getter không tham số vì đọc đối tượng hiện tại; trả kiểu trường đã khai báo. Không nhập bàn phím hoặc kiểm tra lại. |
| 35 | `return id;` | Trả trường id và kết thúc getter; chỉ đọc dữ liệu đã lưu. |
| 36 | `}` | Đóng khối gần nhất: if, vòng lặp, hàm hoặc lớp theo vị trí. Biến cục bộ trong khối hết phạm vi tại đây. |
| 38 | `/**` | Mở comment tài liệu. Không thực thi; /** dùng với @param/@return, // thích hợp ghi chú ngắn. |
| 39 | `* Cập nhật mã định danh.` | Nội dung comment: Cập nhật mã định danh. Không thay đổi chương trình. |
| 40 | `* @param id mã định danh` | Mô tả tham số id. Tag khớp tên trong chữ ký, không tạo/kiểm tra tham số khi chạy. |
| 41 | `*/` | Đóng comment tài liệu; dòng sau được xử lý là code trở lại. |
| 42 | `public void setId(int id) {` | Setter nhận giá trị mới, trả void vì chỉ gán. Che trường private và có thể thêm kiểm tra sau này; hiện chưa tự xác thực. |
| 43 | `this.id = id;` | Gán tham số vào trường của đối tượng; this phân biệt hai tên giống nhau. Không this sẽ tự gán tham số mà không đổi trường. |
| 44 | `}` | Đóng khối gần nhất: if, vòng lặp, hàm hoặc lớp theo vị trí. Biến cục bộ trong khối hết phạm vi tại đây. |
| 46 | `/**` | Mở comment tài liệu. Không thực thi; /** dùng với @param/@return, // thích hợp ghi chú ngắn. |
| 47 | `* Lấy tên sinh viên.` | Nội dung comment: Lấy tên sinh viên. Không thay đổi chương trình. |
| 48 | `* @return tên sinh viên` | Mô tả giá trị trả về, không thay thế return. void và constructor không có @return. |
| 49 | `*/` | Đóng comment tài liệu; dòng sau được xử lý là code trở lại. |
| 50 | `public String getName() {` | Getter không tham số vì đọc đối tượng hiện tại; trả kiểu trường đã khai báo. Không nhập bàn phím hoặc kiểm tra lại. |
| 51 | `return name;` | Trả trường name và kết thúc getter; chỉ đọc dữ liệu đã lưu. |
| 52 | `}` | Đóng khối gần nhất: if, vòng lặp, hàm hoặc lớp theo vị trí. Biến cục bộ trong khối hết phạm vi tại đây. |
| 54 | `/**` | Mở comment tài liệu. Không thực thi; /** dùng với @param/@return, // thích hợp ghi chú ngắn. |
| 55 | `* Cập nhật tên sinh viên.` | Nội dung comment: Cập nhật tên sinh viên. Không thay đổi chương trình. |
| 56 | `* @param name tên sinh viên` | Mô tả tham số name. Tag khớp tên trong chữ ký, không tạo/kiểm tra tham số khi chạy. |
| 57 | `*/` | Đóng comment tài liệu; dòng sau được xử lý là code trở lại. |
| 58 | `public void setName(String name) {` | Setter nhận giá trị mới, trả void vì chỉ gán. Che trường private và có thể thêm kiểm tra sau này; hiện chưa tự xác thực. |
| 59 | `this.name = name;` | Gán tham số vào trường của đối tượng; this phân biệt hai tên giống nhau. Không this sẽ tự gán tham số mà không đổi trường. |
| 60 | `}` | Đóng khối gần nhất: if, vòng lặp, hàm hoặc lớp theo vị trí. Biến cục bộ trong khối hết phạm vi tại đây. |
| 62 | `/**` | Mở comment tài liệu. Không thực thi; /** dùng với @param/@return, // thích hợp ghi chú ngắn. |
| 63 | `* Lấy học kỳ.` | Nội dung comment: Lấy học kỳ. Không thay đổi chương trình. |
| 64 | `* @return học kỳ` | Mô tả giá trị trả về, không thay thế return. void và constructor không có @return. |
| 65 | `*/` | Đóng comment tài liệu; dòng sau được xử lý là code trở lại. |
| 66 | `public int getSemester() {` | Getter không tham số vì đọc đối tượng hiện tại; trả kiểu trường đã khai báo. Không nhập bàn phím hoặc kiểm tra lại. |
| 67 | `return semester;` | Trả trường semester và kết thúc getter; chỉ đọc dữ liệu đã lưu. |
| 68 | `}` | Đóng khối gần nhất: if, vòng lặp, hàm hoặc lớp theo vị trí. Biến cục bộ trong khối hết phạm vi tại đây. |
| 70 | `/**` | Mở comment tài liệu. Không thực thi; /** dùng với @param/@return, // thích hợp ghi chú ngắn. |
| 71 | `* Cập nhật học kỳ.` | Nội dung comment: Cập nhật học kỳ. Không thay đổi chương trình. |
| 72 | `* @param semester học kỳ` | Mô tả tham số semester. Tag khớp tên trong chữ ký, không tạo/kiểm tra tham số khi chạy. |
| 73 | `*/` | Đóng comment tài liệu; dòng sau được xử lý là code trở lại. |
| 74 | `public void setSemester(int semester) {` | Setter nhận giá trị mới, trả void vì chỉ gán. Che trường private và có thể thêm kiểm tra sau này; hiện chưa tự xác thực. |
| 75 | `this.semester = semester;` | Gán tham số vào trường của đối tượng; this phân biệt hai tên giống nhau. Không this sẽ tự gán tham số mà không đổi trường. |
| 76 | `}` | Đóng khối gần nhất: if, vòng lặp, hàm hoặc lớp theo vị trí. Biến cục bộ trong khối hết phạm vi tại đây. |
| 78 | `/**` | Mở comment tài liệu. Không thực thi; /** dùng với @param/@return, // thích hợp ghi chú ngắn. |
| 79 | `* Lấy tên môn học đang được lưu.` | Nội dung comment: Lấy tên môn học đang được lưu. Không thay đổi chương trình. |
| 80 | `* @return tên môn học của bản ghi` | Mô tả giá trị trả về, không thay thế return. void và constructor không có @return. |
| 81 | `*/` | Đóng comment tài liệu; dòng sau được xử lý là code trở lại. |
| 82 | `public String getCourse() {` | Khai báo phương thức getCourse. Student/Report chỉ đọc trường. Utility/view có tham số lời nhắc và nhập môn. Cùng tên nhưng nhiệm vụ khác nhau. |
| 83 | `return course;` | Trả trường course và kết thúc getter; chỉ đọc dữ liệu đã lưu. |
| 84 | `}` | Đóng khối gần nhất: if, vòng lặp, hàm hoặc lớp theo vị trí. Biến cục bộ trong khối hết phạm vi tại đây. |
| 86 | `/**` | Mở comment tài liệu. Không thực thi; /** dùng với @param/@return, // thích hợp ghi chú ngắn. |
| 87 | `* Cập nhật môn học.` | Nội dung comment: Cập nhật môn học. Không thay đổi chương trình. |
| 88 | `* @param course môn học` | Mô tả tham số course. Tag khớp tên trong chữ ký, không tạo/kiểm tra tham số khi chạy. |
| 89 | `*/` | Đóng comment tài liệu; dòng sau được xử lý là code trở lại. |
| 90 | `public void setCourse(String course) {` | Setter nhận giá trị mới, trả void vì chỉ gán. Che trường private và có thể thêm kiểm tra sau này; hiện chưa tự xác thực. |
| 91 | `this.course = course;` | Gán tham số vào trường của đối tượng; this phân biệt hai tên giống nhau. Không this sẽ tự gán tham số mà không đổi trường. |
| 92 | `}` | Đóng khối gần nhất: if, vòng lặp, hàm hoặc lớp theo vị trí. Biến cục bộ trong khối hết phạm vi tại đây. |
| 94 | `}` | Đóng khối gần nhất: if, vòng lặp, hàm hoặc lớp theo vị trí. Biến cục bộ trong khối hết phạm vi tại đây. |

## model/Report.java

| Dòng | Code | Làm gì, vì sao dùng và cách khác |
| --- | --- | --- |
| 1 | `package model;` | Đặt lớp trong package model, tương ứng thư mục dự án. Bỏ package phải sửa import/cách gọi; dùng package để tổ chức và tránh trùng tên lớp. |
| 3 | `/** Một dòng báo cáo được nhóm theo mã sinh viên và môn học. */` | Mở comment tài liệu và đóng ngay cùng dòng. Không thực thi; /** dùng với @param/@return, // thích hợp ghi chú ngắn. |
| 4 | `public class Report {` | Khai báo lớp Report truy cập được từ package khác; { mở thân. Class gom dữ liệu/hành vi, không cần kế thừa khi chưa có quan hệ cha/con. |
| 5 | `private final int studentId;` | Trường private lưu mã nhóm báo cáo. int phù hợp số nguyên, không cần double có phần thập phân. final gán một lần khi tạo Report vì khóa/tên/môn không đổi; total vẫn đổi. |
| 6 | `private final String studentName;` | Trường private lưu tên trong báo cáo. String giữ chuỗi chữ; char chỉ một ký tự. final gán một lần khi tạo Report vì khóa/tên/môn không đổi; total vẫn đổi. |
| 7 | `private final String course;` | Trường private lưu môn học. String giữ chuỗi chữ; char chỉ một ký tự. final gán một lần khi tạo Report vì khóa/tên/môn không đổi; total vẫn đổi. |
| 8 | `private int total;` | Trường private lưu tổng lần đăng ký. int phù hợp số nguyên, không cần double có phần thập phân. Không final vì cần gán/sửa. Getter/setter che trường; hiện setter chưa xác thực. |
| 10 | `/**` | Mở comment tài liệu. Không thực thi; /** dùng với @param/@return, // thích hợp ghi chú ngắn. |
| 11 | `* Khởi tạo đối tượng Report với thông tin được cung cấp.` | Nội dung comment: Khởi tạo đối tượng Report với thông tin được cung cấp. Không thay đổi chương trình. |
| 12 | `* @param student bản ghi sinh viên` | Mô tả tham số student. Tag khớp tên trong chữ ký, không tạo/kiểm tra tham số khi chạy. |
| 13 | `*/` | Đóng comment tài liệu; dòng sau được xử lý là code trở lại. |
| 14 | `public Report(Student student) {` | Constructor cùng tên lớp, không có kiểu trả về, chạy khi new. Nhận dữ liệu hoặc phụ thuộc để gán trong thân; tiện hơn tạo rỗng rồi gọi nhiều setter. |
| 15 | `studentId = student.getId();` | Chụp mã lúc tạo báo cáo; chỉ giữ dữ liệu cần thay vì giữ toàn bộ Student. |
| 16 | `studentName = student.getName();` | Chụp tên lúc tạo Report. Report cũ không tự đổi khi Student đổi; report() tạo lại từ dữ liệu hiện tại. |
| 17 | `course = student.getCourse();` | Chụp môn dùng để nhóm/in. Getter chỉ đọc dữ liệu, không nhập bàn phím. |
| 18 | `total = 1;` | Bắt đầu một vì dòng này tạo từ một đăng ký có sẵn. Bắt đầu 0 mà không tăng ngay sẽ đếm thiếu. |
| 19 | `}` | Đóng khối gần nhất: if, vòng lặp, hàm hoặc lớp theo vị trí. Biến cục bộ trong khối hết phạm vi tại đây. |
| 21 | `/**` | Mở comment tài liệu. Không thực thi; /** dùng với @param/@return, // thích hợp ghi chú ngắn. |
| 22 | `* Lấy mã sinh viên.` | Nội dung comment: Lấy mã sinh viên. Không thay đổi chương trình. |
| 23 | `* @return mã sinh viên` | Mô tả giá trị trả về, không thay thế return. void và constructor không có @return. |
| 24 | `*/` | Đóng comment tài liệu; dòng sau được xử lý là code trở lại. |
| 25 | `public int getStudentId() { return studentId; }` | Hàm getStudentId gộp thân một dòng: trả trực tiếp trường dữ liệu, không nhập hoặc chuẩn hóa. |
| 26 | `/**` | Mở comment tài liệu. Không thực thi; /** dùng với @param/@return, // thích hợp ghi chú ngắn. |
| 27 | `* Lấy tên sinh viên.` | Nội dung comment: Lấy tên sinh viên. Không thay đổi chương trình. |
| 28 | `* @return tên sinh viên` | Mô tả giá trị trả về, không thay thế return. void và constructor không có @return. |
| 29 | `*/` | Đóng comment tài liệu; dòng sau được xử lý là code trở lại. |
| 30 | `public String getStudentName() { return studentName; }` | Hàm getStudentName gộp thân một dòng: trả trực tiếp trường dữ liệu, không nhập hoặc chuẩn hóa. |
| 31 | `/**` | Mở comment tài liệu. Không thực thi; /** dùng với @param/@return, // thích hợp ghi chú ngắn. |
| 32 | `* Lấy tên môn học đang được lưu.` | Nội dung comment: Lấy tên môn học đang được lưu. Không thay đổi chương trình. |
| 33 | `* @return tên môn học của bản ghi` | Mô tả giá trị trả về, không thay thế return. void và constructor không có @return. |
| 34 | `*/` | Đóng comment tài liệu; dòng sau được xử lý là code trở lại. |
| 35 | `public String getCourse() { return course; }` | Hàm getCourse gộp thân một dòng: trả trực tiếp trường dữ liệu, không nhập hoặc chuẩn hóa. |
| 36 | `/**` | Mở comment tài liệu. Không thực thi; /** dùng với @param/@return, // thích hợp ghi chú ngắn. |
| 37 | `* Lấy tổng số lần đăng ký môn học.` | Nội dung comment: Lấy tổng số lần đăng ký môn học. Không thay đổi chương trình. |
| 38 | `* @return tổng số lần đăng ký môn học` | Mô tả giá trị trả về, không thay thế return. void và constructor không có @return. |
| 39 | `*/` | Đóng comment tài liệu; dòng sau được xử lý là code trở lại. |
| 40 | `public int getTotal() { return total; }` | Hàm getTotal gộp thân một dòng: trả trực tiếp trường dữ liệu, không nhập hoặc chuẩn hóa. |
| 41 | `/**` | Mở comment tài liệu. Không thực thi; /** dùng với @param/@return, // thích hợp ghi chú ngắn. |
| 42 | `* Tăng tổng số lần đăng ký môn học trong dòng báo cáo lên một.` | Nội dung comment: Tăng tổng số lần đăng ký môn học trong dòng báo cáo lên một. Không thay đổi chương trình. |
| 43 | `*/` | Đóng comment tài liệu; dòng sau được xử lý là code trở lại. |
| 44 | `public void incrementTotal() { total++; }` | total++ tương đương total=total+1. void vì cập nhật bộ đếm; đọc tổng qua getTotal. |
| 45 | `}` | Đóng khối gần nhất: if, vòng lặp, hàm hoặc lớp theo vị trí. Biến cục bộ trong khối hết phạm vi tại đây. |

## model/StudentManager.java

| Dòng | Code | Làm gì, vì sao dùng và cách khác |
| --- | --- | --- |
| 1 | `package model;` | Đặt lớp trong package model, tương ứng thư mục dự án. Bỏ package phải sửa import/cách gọi; dùng package để tổ chức và tránh trùng tên lớp. |
| 3 | `import java.util.ArrayList;` | Cho dùng tên ngắn của java.util.ArrayList. Có thể viết tên đầy đủ mỗi chỗ dùng nhưng dài hơn. Import không tạo đối tượng hay chạy hàm; import cụ thể rõ phụ thuộc hơn *. |
| 4 | `import java.util.Collections;` | Cho dùng tên ngắn của java.util.Collections. Có thể viết tên đầy đủ mỗi chỗ dùng nhưng dài hơn. Import không tạo đối tượng hay chạy hàm; import cụ thể rõ phụ thuộc hơn *. |
| 5 | `import java.util.Comparator;` | Cho dùng tên ngắn của java.util.Comparator. Có thể viết tên đầy đủ mỗi chỗ dùng nhưng dài hơn. Import không tạo đối tượng hay chạy hàm; import cụ thể rõ phụ thuộc hơn *. |
| 6 | `import java.util.Locale;` | Cho dùng tên ngắn của java.util.Locale. Có thể viết tên đầy đủ mỗi chỗ dùng nhưng dài hơn. Import không tạo đối tượng hay chạy hàm; import cụ thể rõ phụ thuộc hơn *. |
| 7 | `import java.util.List;` | Cho dùng tên ngắn của java.util.List. Có thể viết tên đầy đủ mỗi chỗ dùng nhưng dài hơn. Import không tạo đối tượng hay chạy hàm; import cụ thể rõ phụ thuộc hơn *. |
| 9 | `public class StudentManager {` | Khai báo lớp StudentManager truy cập được từ package khác; { mở thân. Class gom dữ liệu/hành vi, không cần kế thừa khi chưa có quan hệ cha/con. |
| 10 | `private final List<Student> students = new ArrayList<>();` | List là interface, ArrayList là triển khai tự tăng dung lượng. <Student> kiểm tra kiểu phần tử; <> suy ra kiểu. ArrayList tiện duyệt/truy cập chỉ số; mảng phải tự quản lý dung lượng, LinkedList không có lợi rõ rệt ở đây. final cấm gán lại biến nhưng vẫn cho thêm/xóa. |
| 12 | `/**` | Mở comment tài liệu. Không thực thi; /** dùng với @param/@return, // thích hợp ghi chú ngắn. |
| 13 | `* Kiểm tra danh sách sinh viên có rỗng hay không.` | Nội dung comment: Kiểm tra danh sách sinh viên có rỗng hay không. Không thay đổi chương trình. |
| 14 | `* @return true nếu danh sách rỗng; false nếu có bản ghi` | Mô tả giá trị trả về, không thay thế return. void và constructor không có @return. |
| 15 | `*/` | Đóng comment tài liệu; dòng sau được xử lý là code trở lại. |
| 16 | `public boolean isEmpty() { return students.isEmpty(); }` | Hàm isEmpty gộp thân một dòng: trả boolean kiểm tra danh sách rỗng. |
| 17 | `/**` | Mở comment tài liệu. Không thực thi; /** dùng với @param/@return, // thích hợp ghi chú ngắn. |
| 18 | `* Đếm số bản ghi đăng ký môn học trong danh sách.` | Nội dung comment: Đếm số bản ghi đăng ký môn học trong danh sách. Không thay đổi chương trình. |
| 19 | `* @return số bản ghi hiện có` | Mô tả giá trị trả về, không thay thế return. void và constructor không có @return. |
| 20 | `*/` | Đóng comment tài liệu; dòng sau được xử lý là code trở lại. |
| 21 | `public int size() { return students.size(); }` | Hàm size gộp thân một dòng: trả int số bản ghi, không phải số người. |
| 22 | `/**` | Mở comment tài liệu. Không thực thi; /** dùng với @param/@return, // thích hợp ghi chú ngắn. |
| 23 | `* Thêm bản ghi sinh viên nếu không trùng mã, học kỳ và môn học; dùng tên đã có của cùng mã.` | Nội dung comment: Thêm bản ghi sinh viên nếu không trùng mã, học kỳ và môn học; dùng tên đã có của cùng mã. Không thay đổi chương trình. |
| 24 | `* @param student bản ghi sinh viên` | Mô tả tham số student. Tag khớp tên trong chữ ký, không tạo/kiểm tra tham số khi chạy. |
| 25 | `* @return true nếu thêm thành công; false nếu bản ghi bị trùng` | Mô tả giá trị trả về, không thay thế return. void và constructor không có @return. |
| 26 | `*/` | Đóng comment tài liệu; dòng sau được xử lý là code trở lại. |
| 27 | `public boolean add(Student student) {` | Khai báo phương thức add. Nhận Student gom đủ thông tin, trả boolean thành công hoặc trùng. |
| 28 | `if (isDuplicate(null, student.getId(), student.getSemester(), student.getCourse())) {` | Kiểm tra trùng bộ ba mã/học kỳ/môn trước khi thêm. null nghĩa là không bỏ qua bản ghi nào. Chỉ kiểm tra mã sẽ cấm cùng người đăng ký thêm môn/học kỳ. |
| 29 | `return false;` | Kết thúc với false: add/update là thất bại do trùng, isDuplicate là duyệt hết không trùng, getYesNo là N. Ý nghĩa phụ thuộc hàm. |
| 30 | `}` | Đóng khối gần nhất: if, vòng lặp, hàm hoặc lớp theo vị trí. Biến cục bộ trong khối hết phạm vi tại đây. |
| 31 | `// Dùng lại tên sinh viên đã được lưu cho cùng mã.` | Ghi chú nội bộ: Dùng lại tên sinh viên đã được lưu cho cùng mã. Không thực thi. |
| 32 | `for (Student existing : students) {` | Enhanced for duyệt tham chiếu Student trong List, không cần chỉ số. Sửa trường đối tượng không đổi cấu trúc List; không thêm/xóa List đang duyệt theo cách này. |
| 33 | `if (existing.getId() == student.getId()) {` | Tìm bản ghi cùng mã. add chỉ lấy một tên có sẵn; update cần đi hết để đồng bộ mọi tên. |
| 34 | `student.setName(existing.getName());` | Dùng tên đã gắn với mã cho bản ghi mới. Model vẫn tự giữ quy tắc dù controller cũng lấy tên cũ. |
| 35 | `break;` | Thoát vòng/switch gần nhất: add dừng khi lấy tên, report dừng khi tìm nhóm, controller dừng khi thao tác xong/chọn N. Không tự thoát hàm/chương trình. |
| 36 | `}` | Đóng khối gần nhất: if, vòng lặp, hàm hoặc lớp theo vị trí. Biến cục bộ trong khối hết phạm vi tại đây. |
| 37 | `}` | Đóng khối gần nhất: if, vòng lặp, hàm hoặc lớp theo vị trí. Biến cục bộ trong khối hết phạm vi tại đây. |
| 38 | `students.add(student);` | Thêm tham chiếu vào dữ liệu gốc sau kiểm tra; ArrayList không tự chống trùng. |
| 39 | `return true;` | Kết thúc với true: add/update thành công, isDuplicate có trùng, getYesNo là Y. true không luôn nghĩa dữ liệu hợp lệ. |
| 40 | `}` | Đóng khối gần nhất: if, vòng lặp, hàm hoặc lớp theo vị trí. Biến cục bộ trong khối hết phạm vi tại đây. |
| 42 | `/**` | Mở comment tài liệu. Không thực thi; /** dùng với @param/@return, // thích hợp ghi chú ngắn. |
| 43 | `* Kiểm tra trùng mã sinh viên, học kỳ và môn học, bỏ qua bản ghi đang sửa.` | Nội dung comment: Kiểm tra trùng mã sinh viên, học kỳ và môn học, bỏ qua bản ghi đang sửa. Không thay đổi chương trình. |
| 44 | `* @param excluded bản ghi bỏ qua khi kiểm tra trùng; null khi thêm mới` | Mô tả tham số excluded. Tag khớp tên trong chữ ký, không tạo/kiểm tra tham số khi chạy. |
| 45 | `* @param id mã định danh` | Mô tả tham số id. Tag khớp tên trong chữ ký, không tạo/kiểm tra tham số khi chạy. |
| 46 | `* @param semester học kỳ` | Mô tả tham số semester. Tag khớp tên trong chữ ký, không tạo/kiểm tra tham số khi chạy. |
| 47 | `* @param course môn học` | Mô tả tham số course. Tag khớp tên trong chữ ký, không tạo/kiểm tra tham số khi chạy. |
| 48 | `* @return true nếu có bản ghi trùng; false nếu không trùng` | Mô tả giá trị trả về, không thay thế return. void và constructor không có @return. |
| 49 | `*/` | Đóng comment tài liệu; dòng sau được xử lý là code trở lại. |
| 50 | `private boolean isDuplicate(Student excluded, int id, int semester, String course) {` | Khai báo phương thức isDuplicate. private vì kiểm tra nội bộ. excluded dùng chung cho thêm/sửa tránh hai hàm kiểm tra giống nhau. |
| 51 | `// Khi sửa, bỏ qua bản ghi đang sửa. Khi thêm, excluded bằng null.` | Ghi chú nội bộ: Khi sửa, bỏ qua bản ghi đang sửa. Khi thêm, excluded bằng null. Không thực thi. |
| 52 | `for (Student student : students) {` | Enhanced for duyệt tham chiếu Student trong List, không cần chỉ số. Sửa trường đối tượng không đổi cấu trúc List; không thêm/xóa List đang duyệt theo cách này. |
| 53 | `if (student != excluded && student.getId() == id` | Bỏ qua đúng đối tượng đang sửa bằng != so tham chiếu rồi so mã int bằng ==. So nội dung để bỏ qua có thể bỏ nhầm bản ghi khác. |
| 54 | `&& student.getSemester() == semester` | AND thêm điều kiện cùng học kỳ. == phù hợp int; OR sẽ từ chối cả đăng ký chỉ trùng mã hoặc học kỳ. |
| 55 | `&& student.getCourse().equalsIgnoreCase(course)) {` | AND thêm cùng môn theo nội dung bỏ qua hoa/thường. == với String chỉ so tham chiếu nên không phù hợp. |
| 56 | `return true;` | Kết thúc với true: add/update thành công, isDuplicate có trùng, getYesNo là Y. true không luôn nghĩa dữ liệu hợp lệ. |
| 57 | `}` | Đóng khối gần nhất: if, vòng lặp, hàm hoặc lớp theo vị trí. Biến cục bộ trong khối hết phạm vi tại đây. |
| 58 | `}` | Đóng khối gần nhất: if, vòng lặp, hàm hoặc lớp theo vị trí. Biến cục bộ trong khối hết phạm vi tại đây. |
| 59 | `return false;` | Kết thúc với false: add/update là thất bại do trùng, isDuplicate là duyệt hết không trùng, getYesNo là N. Ý nghĩa phụ thuộc hàm. |
| 60 | `}` | Đóng khối gần nhất: if, vòng lặp, hàm hoặc lớp theo vị trí. Biến cục bộ trong khối hết phạm vi tại đây. |
| 62 | `/**` | Mở comment tài liệu. Không thực thi; /** dùng với @param/@return, // thích hợp ghi chú ngắn. |
| 63 | `* Tìm sinh viên có tên chứa từ khóa và sắp xếp tên tăng dần, không phân biệt hoa thường.` | Nội dung comment: Tìm sinh viên có tên chứa từ khóa và sắp xếp tên tăng dần, không phân biệt hoa thường. Không thay đổi chương trình. |
| 64 | `* @param keyword từ khóa tìm kiếm tên` | Mô tả tham số keyword. Tag khớp tên trong chữ ký, không tạo/kiểm tra tham số khi chạy. |
| 65 | `* @return danh sách kết quả đã sắp xếp; danh sách rỗng nếu không tìm thấy` | Mô tả giá trị trả về, không thay thế return. void và constructor không có @return. |
| 66 | `*/` | Đóng comment tài liệu; dòng sau được xử lý là code trở lại. |
| 67 | `public List<Student> findAndSort(String keyword) {` | Khai báo phương thức findAndSort. Ở model nhận từ khóa và trả List vì có nhiều kết quả; ở controller void vì hỏi từ khóa, gọi model rồi hiển thị. |
| 68 | `List<Student> result = new ArrayList<>();` | Tạo danh sách kết quả riêng để không lọc/sắp xếp trực tiếp danh sách gốc. Phần tử thêm vào vẫn là tham chiếu Student gốc, không phải bản sao sâu. |
| 69 | `String searchName = keyword.toLowerCase(Locale.ROOT);` | Đổi từ khóa sang chữ thường một lần. Locale.ROOT tránh phụ thuộc locale mặc định của máy; không loại bỏ dấu tiếng Việt. |
| 70 | `for (Student student : students) {` | Enhanced for duyệt tham chiếu Student trong List, không cần chỉ số. Sửa trường đối tượng không đổi cấu trúc List; không thêm/xóa List đang duyệt theo cách này. |
| 71 | `if (student.getName().toLowerCase(Locale.ROOT).contains(searchName)) {` | Đổi tên sang chữ thường rồi tìm một phần tên. contains khớp đoạn bất kỳ; equalsIgnoreCase cần cả tên giống nhau, startsWith chỉ xét đầu tên. |
| 72 | `result.add(student);` | Thêm tham chiếu bản ghi khớp, không clone; List kết quả và gốc cùng thấy sửa đổi trên đối tượng. |
| 73 | `}` | Đóng khối gần nhất: if, vòng lặp, hàm hoặc lớp theo vị trí. Biến cục bộ trong khối hết phạm vi tại đây. |
| 74 | `}` | Đóng khối gần nhất: if, vòng lặp, hàm hoặc lớp theo vị trí. Biến cục bộ trong khối hết phạm vi tại đây. |
| 75 | `Collections.sort(result, new Comparator<Student>() {` | Sắp xếp kết quả bằng Comparator vô danh. Comparator chọn tiêu chí tên mà không buộc Student triển khai Comparable với thứ tự mặc định. Lambda hoặc result.sort cũng đúng và gọn hơn; cách này thể hiện rõ compare. |
| 76 | `/**` | Mở comment tài liệu. Không thực thi; /** dùng với @param/@return, // thích hợp ghi chú ngắn. |
| 77 | `* So sánh tên hai sinh viên, không phân biệt chữ hoa và chữ thường.` | Nội dung comment: So sánh tên hai sinh viên, không phân biệt chữ hoa và chữ thường. Không thay đổi chương trình. |
| 78 | `* @param first sinh viên thứ nhất` | Mô tả tham số first. Tag khớp tên trong chữ ký, không tạo/kiểm tra tham số khi chạy. |
| 79 | `* @param second sinh viên thứ hai` | Mô tả tham số second. Tag khớp tên trong chữ ký, không tạo/kiểm tra tham số khi chạy. |
| 80 | `* @return số âm, 0 hoặc số dương khi tên thứ nhất đứng trước, bằng hoặc đứng sau tên thứ hai` | Mô tả giá trị trả về, không thay thế return. void và constructor không có @return. |
| 81 | `*/` | Đóng comment tài liệu; dòng sau được xử lý là code trở lại. |
| 82 | `@Override` | Compiler kiểm tra compare thật sự ghi đè phương thức Comparator. Bỏ annotation vẫn chạy nếu chữ ký đúng, nhưng giữ giúp bắt sai tên/tham số. |
| 83 | `public int compare(Student first, Student second) {` | Khai báo phương thức compare. Hợp đồng Comparator nhận hai Student và trả dấu int biểu diễn nhỏ hơn/bằng/lớn hơn; boolean không đủ ba trạng thái. |
| 84 | `return first.getName().compareToIgnoreCase(second.getName());` | Trả số âm, 0 hoặc dương khi tên first đứng trước, bằng hoặc sau second theo so chuỗi bỏ qua hoa/thường. Không cần đúng -1/1. Không dùng == so nội dung; đây không phải thứ tự từ điển tiếng Việt theo Collator. |
| 85 | `}` | Đóng khối gần nhất: if, vòng lặp, hàm hoặc lớp theo vị trí. Biến cục bộ trong khối hết phạm vi tại đây. |
| 86 | `});` | Đóng lớp vô danh bằng }, đóng lời gọi sort bằng ), kết thúc câu lệnh bằng ;. |
| 87 | `return result;` | Trả List kết quả; không khớp thì List rỗng thay null để caller gọi isEmpty an toàn. |
| 88 | `}` | Đóng khối gần nhất: if, vòng lặp, hàm hoặc lớp theo vị trí. Biến cục bộ trong khối hết phạm vi tại đây. |
| 90 | `/**` | Mở comment tài liệu. Không thực thi; /** dùng với @param/@return, // thích hợp ghi chú ngắn. |
| 91 | `* Tìm tất cả bản ghi sinh viên có mã được chỉ định.` | Nội dung comment: Tìm tất cả bản ghi sinh viên có mã được chỉ định. Không thay đổi chương trình. |
| 92 | `* @param id mã định danh` | Mô tả tham số id. Tag khớp tên trong chữ ký, không tạo/kiểm tra tham số khi chạy. |
| 93 | `* @return danh sách bản ghi cùng mã; danh sách rỗng nếu không tìm thấy` | Mô tả giá trị trả về, không thay thế return. void và constructor không có @return. |
| 94 | `*/` | Đóng comment tài liệu; dòng sau được xử lý là code trở lại. |
| 95 | `public List<Student> findById(int id) {` | Khai báo phương thức findById. Trả List thay vì một Student vì cùng mã có nhiều đăng ký. |
| 96 | `List<Student> result = new ArrayList<>();` | Tạo danh sách kết quả riêng để không lọc/sắp xếp trực tiếp danh sách gốc. Phần tử thêm vào vẫn là tham chiếu Student gốc, không phải bản sao sâu. |
| 97 | `for (Student student : students) {` | Enhanced for duyệt tham chiếu Student trong List, không cần chỉ số. Sửa trường đối tượng không đổi cấu trúc List; không thêm/xóa List đang duyệt theo cách này. |
| 98 | `if (student.getId() == id) { result.add(student); }` | Đúng mã thì thêm tham chiếu vào kết quả; không break vì cùng mã có thể có nhiều đăng ký. Viết gộp một dòng chỉ là định dạng. |
| 99 | `}` | Đóng khối gần nhất: if, vòng lặp, hàm hoặc lớp theo vị trí. Biến cục bộ trong khối hết phạm vi tại đây. |
| 100 | `return result;` | Trả List kết quả; không khớp thì List rỗng thay null để caller gọi isEmpty an toàn. |
| 101 | `}` | Đóng khối gần nhất: if, vòng lặp, hàm hoặc lớp theo vị trí. Biến cục bộ trong khối hết phạm vi tại đây. |
| 103 | `/**` | Mở comment tài liệu. Không thực thi; /** dùng với @param/@return, // thích hợp ghi chú ngắn. |
| 104 | `* Cập nhật học kỳ và môn học nếu không trùng; đổi tên cho mọi bản ghi cùng mã.` | Nội dung comment: Cập nhật học kỳ và môn học nếu không trùng; đổi tên cho mọi bản ghi cùng mã. Không thay đổi chương trình. |
| 105 | `* @param student bản ghi sinh viên` | Mô tả tham số student. Tag khớp tên trong chữ ký, không tạo/kiểm tra tham số khi chạy. |
| 106 | `* @param name tên sinh viên` | Mô tả tham số name. Tag khớp tên trong chữ ký, không tạo/kiểm tra tham số khi chạy. |
| 107 | `* @param semester học kỳ` | Mô tả tham số semester. Tag khớp tên trong chữ ký, không tạo/kiểm tra tham số khi chạy. |
| 108 | `* @param course môn học` | Mô tả tham số course. Tag khớp tên trong chữ ký, không tạo/kiểm tra tham số khi chạy. |
| 109 | `* @return true nếu cập nhật thành công; false nếu trùng học kỳ và môn học` | Mô tả giá trị trả về, không thay thế return. void và constructor không có @return. |
| 110 | `*/` | Đóng comment tài liệu; dòng sau được xử lý là code trở lại. |
| 111 | `public boolean update(Student student, String name, int semester, String course) {` | Khai báo phương thức update. Nhận bản ghi và thông tin mới, trả boolean để báo trùng trước khi sửa. |
| 112 | `if (isDuplicate(student, student.getId(), semester, course)) {` | Kiểm tra thông tin dự định sửa trước mọi setter và bỏ qua chính student. Nếu không bỏ qua, giữ môn/học kỳ cũ cũng bị coi trùng chính mình. |
| 113 | `return false;` | Kết thúc với false: add/update là thất bại do trùng, isDuplicate là duyệt hết không trùng, getYesNo là N. Ý nghĩa phụ thuộc hàm. |
| 114 | `}` | Đóng khối gần nhất: if, vòng lặp, hàm hoặc lớp theo vị trí. Biến cục bộ trong khối hết phạm vi tại đây. |
| 115 | `// Đổi tên cho tất cả bản ghi có cùng mã sinh viên.` | Ghi chú nội bộ: Đổi tên cho tất cả bản ghi có cùng mã sinh viên. Không thực thi. |
| 116 | `for (Student existing : students) {` | Enhanced for duyệt tham chiếu Student trong List, không cần chỉ số. Sửa trường đối tượng không đổi cấu trúc List; không thêm/xóa List đang duyệt theo cách này. |
| 117 | `if (existing.getId() == student.getId()) {` | Tìm bản ghi cùng mã. add chỉ lấy một tên có sẵn; update cần đi hết để đồng bộ mọi tên. |
| 118 | `existing.setName(name);` | Đổi tên trên mọi bản ghi cùng mã. Chỉ đổi selected sẽ làm một người có nhiều tên khác nhau. |
| 119 | `}` | Đóng khối gần nhất: if, vòng lặp, hàm hoặc lớp theo vị trí. Biến cục bộ trong khối hết phạm vi tại đây. |
| 120 | `}` | Đóng khối gần nhất: if, vòng lặp, hàm hoặc lớp theo vị trí. Biến cục bộ trong khối hết phạm vi tại đây. |
| 121 | `student.setSemester(semester);` | Sau kiểm tra trùng, chỉ đổi học kỳ của bản ghi chọn. Làm trước kiểm tra sẽ cần khôi phục nếu bị từ chối. |
| 122 | `student.setCourse(course);` | Chỉ đổi môn của bản ghi chọn; các đăng ký khác cùng mã giữ môn riêng. |
| 123 | `return true;` | Kết thúc với true: add/update thành công, isDuplicate có trùng, getYesNo là Y. true không luôn nghĩa dữ liệu hợp lệ. |
| 124 | `}` | Đóng khối gần nhất: if, vòng lặp, hàm hoặc lớp theo vị trí. Biến cục bộ trong khối hết phạm vi tại đây. |
| 126 | `/**` | Mở comment tài liệu. Không thực thi; /** dùng với @param/@return, // thích hợp ghi chú ngắn. |
| 127 | `* Xóa bản ghi sinh viên được chọn khỏi danh sách.` | Nội dung comment: Xóa bản ghi sinh viên được chọn khỏi danh sách. Không thay đổi chương trình. |
| 128 | `* @param selected bản ghi sinh viên cần xóa` | Mô tả tham số selected. Tag khớp tên trong chữ ký, không tạo/kiểm tra tham số khi chạy. |
| 129 | `*/` | Đóng comment tài liệu; dòng sau được xử lý là code trở lại. |
| 130 | `public void delete(Student selected) { students.remove(selected); }` | void xóa selected bằng remove(Object); Student chưa override equals nên dùng danh tính mặc định. Có thể trả boolean remove nếu cần báo bản ghi không tồn tại. |
| 132 | `/**` | Mở comment tài liệu. Không thực thi; /** dùng với @param/@return, // thích hợp ghi chú ngắn. |
| 133 | `* Tổng hợp số lần đăng ký theo mã sinh viên và môn học.` | Nội dung comment: Tổng hợp số lần đăng ký theo mã sinh viên và môn học. Không thay đổi chương trình. |
| 134 | `* @return danh sách các dòng báo cáo` | Mô tả giá trị trả về, không thay thế return. void và constructor không có @return. |
| 135 | `*/` | Đóng comment tài liệu; dòng sau được xử lý là code trở lại. |
| 136 | `public List<Report> report() {` | Khai báo phương thức report. Manager trả List<Report> tổng hợp; controller void vì chỉ tính và hiển thị. |
| 137 | `List<Report> result = new ArrayList<>();` | Tạo báo cáo mới mỗi lần gọi, tránh số liệu cũ sau thêm/sửa/xóa. |
| 138 | `for (Student student : students) {` | Enhanced for duyệt tham chiếu Student trong List, không cần chỉ số. Sửa trường đối tượng không đổi cấu trúc List; không thêm/xóa List đang duyệt theo cách này. |
| 139 | `// Tìm dòng báo cáo cùng mã và môn học: đã có thì tăng tổng, chưa có thì thêm.` | Ghi chú nội bộ: Tìm dòng báo cáo cùng mã và môn học: đã có thì tăng tổng, chưa có thì thêm. Không thực thi. |
| 140 | `Report matched = null;` | Mỗi lượt Student bắt đầu với trạng thái chưa tìm được nhóm báo cáo. null nghĩa là chưa có đối tượng, không phải Report rỗng. |
| 141 | `for (Report row : result) {` | Duyệt các nhóm báo cáo đã có để tìm nhóm phù hợp. Hai vòng lặp dễ hiểu nhưng xấu nhất O(n²); Map khóa mã/môn nhanh hơn khi dữ liệu lớn, thêm thiết kế khóa. |
| 142 | `if (row.getStudentId() == student.getId()` | Nhóm theo mã để hai người trùng tên không bị gộp. Không xét học kỳ vì báo cáo cộng qua các học kỳ. |
| 143 | `&& row.getCourse().equalsIgnoreCase(student.getCourse())) {` | Nhóm phải cùng môn nữa; cùng mã khác môn cần dòng riêng. So nội dung bỏ qua hoa/thường. |
| 144 | `matched = row;` | Giữ tham chiếu dòng tìm thấy để tăng đúng đối tượng trong result, không sao chép. |
| 145 | `break;` | Thoát vòng/switch gần nhất: add dừng khi lấy tên, report dừng khi tìm nhóm, controller dừng khi thao tác xong/chọn N. Không tự thoát hàm/chương trình. |
| 146 | `}` | Đóng khối gần nhất: if, vòng lặp, hàm hoặc lớp theo vị trí. Biến cục bộ trong khối hết phạm vi tại đây. |
| 147 | `}` | Đóng khối gần nhất: if, vòng lặp, hàm hoặc lớp theo vị trí. Biến cục bộ trong khối hết phạm vi tại đây. |
| 148 | `if (matched == null) {` | Chưa có nhóm thì tạo mới. == là cách đúng để so với null; không gọi equals trên null. |
| 149 | `result.add(new Report(student));` | Tạo dòng báo cáo từ bản ghi hiện tại. Constructor bắt đầu total=1 vì đã có một lần đăng ký. |
| 150 | `} else {` | Đóng nhánh if và mở trường hợp ngược lại; không cần xét lại điều kiện. |
| 151 | `matched.incrementTotal();` | Nhóm đã có thì tăng tổng một đơn vị, không tạo thêm dòng trùng nhóm. |
| 152 | `}` | Đóng khối gần nhất: if, vòng lặp, hàm hoặc lớp theo vị trí. Biến cục bộ trong khối hết phạm vi tại đây. |
| 153 | `}` | Đóng khối gần nhất: if, vòng lặp, hàm hoặc lớp theo vị trí. Biến cục bộ trong khối hết phạm vi tại đây. |
| 154 | `return result;` | Trả List kết quả; không khớp thì List rỗng thay null để caller gọi isEmpty an toàn. |
| 155 | `}` | Đóng khối gần nhất: if, vòng lặp, hàm hoặc lớp theo vị trí. Biến cục bộ trong khối hết phạm vi tại đây. |
| 156 | `}` | Đóng khối gần nhất: if, vòng lặp, hàm hoặc lớp theo vị trí. Biến cục bộ trong khối hết phạm vi tại đây. |

## view/Utility.java

| Dòng | Code | Làm gì, vì sao dùng và cách khác |
| --- | --- | --- |
| 1 | `package view;` | Đặt lớp trong package view, tương ứng thư mục dự án. Bỏ package phải sửa import/cách gọi; dùng package để tổ chức và tránh trùng tên lớp. |
| 3 | `import java.util.Scanner;` | Cho dùng tên ngắn của java.util.Scanner. Có thể viết tên đầy đủ mỗi chỗ dùng nhưng dài hơn. Import không tạo đối tượng hay chạy hàm; import cụ thể rõ phụ thuộc hơn *. |
| 5 | `public class Utility {` | Khai báo lớp Utility truy cập được từ package khác; { mở thân. Class gom dữ liệu/hành vi, không cần kế thừa khi chưa có quan hệ cha/con. |
| 6 | `private static final Scanner sc = new Scanner(System.in);` | Một Scanner dùng chung đọc bàn phím: private giới hạn truy cập, static dùng chung, final cấm gán lại. Tránh tạo nhiều bộ đọc trên System.in. Không đóng trong từng hàm vì sẽ đóng luôn đầu vào. |
| 8 | `/**` | Mở comment tài liệu. Không thực thi; /** dùng với @param/@return, // thích hợp ghi chú ngắn. |
| 9 | `* Nhập chuỗi, loại bỏ khoảng trắng hai đầu và yêu cầu nhập lại nếu rỗng.` | Nội dung comment: Nhập chuỗi, loại bỏ khoảng trắng hai đầu và yêu cầu nhập lại nếu rỗng. Không thay đổi chương trình. |
| 10 | `* @param msg thông báo hướng dẫn nhập` | Mô tả tham số msg. Tag khớp tên trong chữ ký, không tạo/kiểm tra tham số khi chạy. |
| 11 | `* @return chuỗi không rỗng đã loại bỏ khoảng trắng hai đầu` | Mô tả giá trị trả về, không thay thế return. void và constructor không có @return. |
| 12 | `*/` | Đóng comment tài liệu; dòng sau được xử lý là code trở lại. |
| 13 | `public static String getString(String msg) {` | Khai báo phương thức getString. Nhận lời nhắc và trả chuỗi hợp lệ; static ở Utility vì không cần trạng thái riêng từng đối tượng. |
| 14 | `while (true) {` | Lặp chưa biết số lượt nhập/tiếp tục; thoát bằng break/return bên trong. for cố định số lượt không phù hợp; do/while cũng được với điều kiện tương ứng. |
| 15 | `System.out.print(msg);` | In lời nhắc không xuống dòng để nhập ngay sau đó. println cũng dùng được nhưng đưa con trỏ xuống dòng kế tiếp. |
| 16 | `String s = sc.nextLine().trim();` | Đọc cả dòng và bỏ khoảng trắng hai đầu. next chỉ đọc một từ nên không phù hợp tên có dấu cách. trim giữ khoảng trắng giữa tên, không bao phủ mọi khoảng trắng Unicode. |
| 17 | `if (!s.isEmpty()) {` | ! đảo boolean để nhận chuỗi không rỗng sau trim. Chuỗi toàn dấu cách thông thường đã thành rỗng; không dùng == so nội dung. |
| 18 | `return s;` | Trả chuỗi đã trim/không rỗng và kết thúc cả hàm lẫn vòng nhập, không cần break. |
| 19 | `}` | Đóng khối gần nhất: if, vòng lặp, hàm hoặc lớp theo vị trí. Biến cục bộ trong khối hết phạm vi tại đây. |
| 20 | `System.out.println("Input cannot be empty! Please enter again.");` | In "Input cannot be empty! Please enter again." rồi xuống dòng. Thông báo lỗi rồi while sẽ hỏi lại; println không tự lặp hoặc xác thực. |
| 21 | `}` | Đóng khối gần nhất: if, vòng lặp, hàm hoặc lớp theo vị trí. Biến cục bộ trong khối hết phạm vi tại đây. |
| 22 | `}` | Đóng khối gần nhất: if, vòng lặp, hàm hoặc lớp theo vị trí. Biến cục bộ trong khối hết phạm vi tại đây. |
| 24 | `/**` | Mở comment tài liệu. Không thực thi; /** dùng với @param/@return, // thích hợp ghi chú ngắn. |
| 25 | `* Nhập số nguyên và yêu cầu nhập lại cho đến khi nằm trong giới hạn.` | Nội dung comment: Nhập số nguyên và yêu cầu nhập lại cho đến khi nằm trong giới hạn. Không thay đổi chương trình. |
| 26 | `* @param msg thông báo hướng dẫn nhập` | Mô tả tham số msg. Tag khớp tên trong chữ ký, không tạo/kiểm tra tham số khi chạy. |
| 27 | `* @param min giá trị nhỏ nhất được chấp nhận` | Mô tả tham số min. Tag khớp tên trong chữ ký, không tạo/kiểm tra tham số khi chạy. |
| 28 | `* @param max giá trị lớn nhất được chấp nhận` | Mô tả tham số max. Tag khớp tên trong chữ ký, không tạo/kiểm tra tham số khi chạy. |
| 29 | `* @return số nguyên trong khoảng từ min đến max, bao gồm hai đầu` | Mô tả giá trị trả về, không thay thế return. void và constructor không có @return. |
| 30 | `*/` | Đóng comment tài liệu; dòng sau được xử lý là code trở lại. |
| 31 | `public static int getInt(String msg, int min, int max) {` | Khai báo phương thức getInt. Nhận lời nhắc/min/max để tái dùng cho menu, mã, học kỳ và số thứ tự. |
| 32 | `while (true) {` | Lặp chưa biết số lượt nhập/tiếp tục; thoát bằng break/return bên trong. for cố định số lượt không phù hợp; do/while cũng được với điều kiện tương ứng. |
| 33 | `try {` | Mở vùng có thể lỗi parseInt. Kiểm tra min/max không xử lý chuỗi abc nên cần bắt exception để nhập lại. |
| 34 | `int val = Integer.parseInt(getString(msg));` | Đọc chuỗi rồi đổi sang int; nextLine nhất quán tránh xuống dòng còn lại khi trộn nextInt/nextLine. parseInt lỗi khi có chữ, số thập phân hoặc vượt miền int. |
| 35 | `if (val >= min && val <= max) {` | Nhận khoảng đóng min..max; AND yêu cầu cả hai giới hạn, >=/<= nhận cả hai đầu. |
| 36 | `return val;` | Trả int đã đúng min..max và kết thúc vòng nhập. |
| 37 | `}` | Đóng khối gần nhất: if, vòng lặp, hàm hoặc lớp theo vị trí. Biến cục bộ trong khối hết phạm vi tại đây. |
| 38 | `System.out.printf("Value must be between %d and %d!\n", min, max);` | %d điền hai số nguyên; printf tránh nối chuỗi dài. \n xuống dòng; %n là cách xuống dòng theo nền tảng. |
| 39 | `} catch (NumberFormatException e) {` | Bắt riêng lỗi chuyển chuỗi thành int. e là đối tượng lỗi hiện chưa dùng. Bắt Exception quá rộng có thể che lỗi lập trình không liên quan. |
| 40 | `System.out.println("Invalid integer! Please enter a number.");` | In "Invalid integer! Please enter a number." rồi xuống dòng. Thông báo lỗi rồi while sẽ hỏi lại; println không tự lặp hoặc xác thực. |
| 41 | `}` | Đóng khối gần nhất: if, vòng lặp, hàm hoặc lớp theo vị trí. Biến cục bộ trong khối hết phạm vi tại đây. |
| 42 | `}` | Đóng khối gần nhất: if, vòng lặp, hàm hoặc lớp theo vị trí. Biến cục bộ trong khối hết phạm vi tại đây. |
| 43 | `}` | Đóng khối gần nhất: if, vòng lặp, hàm hoặc lớp theo vị trí. Biến cục bộ trong khối hết phạm vi tại đây. |
| 45 | `/**` | Mở comment tài liệu. Không thực thi; /** dùng với @param/@return, // thích hợp ghi chú ngắn. |
| 46 | `* Nhập lựa chọn Y hoặc N, không phân biệt chữ hoa và chữ thường.` | Nội dung comment: Nhập lựa chọn Y hoặc N, không phân biệt chữ hoa và chữ thường. Không thay đổi chương trình. |
| 47 | `* @param msg thông báo hướng dẫn nhập` | Mô tả tham số msg. Tag khớp tên trong chữ ký, không tạo/kiểm tra tham số khi chạy. |
| 48 | `* @return true nếu chọn Y; false nếu chọn N` | Mô tả giá trị trả về, không thay thế return. void và constructor không có @return. |
| 49 | `*/` | Đóng comment tài liệu; dòng sau được xử lý là code trở lại. |
| 50 | `public static boolean getYesNo(String msg) {` | Khai báo phương thức getYesNo. Trả boolean cho quyết định, caller không phải tự so chuỗi Y/N. |
| 51 | `while (true) {` | Lặp chưa biết số lượt nhập/tiếp tục; thoát bằng break/return bên trong. for cố định số lượt không phù hợp; do/while cũng được với điều kiện tương ứng. |
| 52 | `String choice = getString(msg + " (Y/N)? ");` | Nối lời nhắc Y/N và dùng lại getString kiểm tra không rỗng; không tạo Scanner hay kiểm tra rỗng riêng. |
| 53 | `if (choice.equalsIgnoreCase("Y")) {` | Nhận Y/y bằng so nội dung, không dùng == so tham chiếu dữ liệu bàn phím. |
| 54 | `return true;` | Kết thúc với true: add/update thành công, isDuplicate có trùng, getYesNo là Y. true không luôn nghĩa dữ liệu hợp lệ. |
| 55 | `}` | Đóng khối gần nhất: if, vòng lặp, hàm hoặc lớp theo vị trí. Biến cục bộ trong khối hết phạm vi tại đây. |
| 56 | `if (choice.equalsIgnoreCase("N")) {` | Nhận N/n. Không cần else if vì nhánh Y đã return. |
| 57 | `return false;` | Kết thúc với false: add/update là thất bại do trùng, isDuplicate là duyệt hết không trùng, getYesNo là N. Ý nghĩa phụ thuộc hàm. |
| 58 | `}` | Đóng khối gần nhất: if, vòng lặp, hàm hoặc lớp theo vị trí. Biến cục bộ trong khối hết phạm vi tại đây. |
| 59 | `System.out.println("Please enter Y or N!");` | In "Please enter Y or N!" rồi xuống dòng. Thông báo lỗi rồi while sẽ hỏi lại; println không tự lặp hoặc xác thực. |
| 60 | `}` | Đóng khối gần nhất: if, vòng lặp, hàm hoặc lớp theo vị trí. Biến cục bộ trong khối hết phạm vi tại đây. |
| 61 | `}` | Đóng khối gần nhất: if, vòng lặp, hàm hoặc lớp theo vị trí. Biến cục bộ trong khối hết phạm vi tại đây. |
| 63 | `/**` | Mở comment tài liệu. Không thực thi; /** dùng với @param/@return, // thích hợp ghi chú ngắn. |
| 64 | `* Nhập và chuẩn hóa môn học hợp lệ: Java, .Net hoặc C/C++.` | Nội dung comment: Nhập và chuẩn hóa môn học hợp lệ: Java, .Net hoặc C/C++. Không thay đổi chương trình. |
| 65 | `* @param msg thông báo hướng dẫn nhập` | Mô tả tham số msg. Tag khớp tên trong chữ ký, không tạo/kiểm tra tham số khi chạy. |
| 66 | `* @return tên môn học hợp lệ đã được chuẩn hóa` | Mô tả giá trị trả về, không thay thế return. void và constructor không có @return. |
| 67 | `*/` | Đóng comment tài liệu; dòng sau được xử lý là code trở lại. |
| 68 | `public static String getCourse(String msg) {` | Khai báo phương thức getCourse. Student/Report chỉ đọc trường. Utility/view có tham số lời nhắc và nhập môn. Cùng tên nhưng nhiệm vụ khác nhau. |
| 69 | `while (true) {` | Lặp chưa biết số lượt nhập/tiếp tục; thoát bằng break/return bên trong. for cố định số lượt không phù hợp; do/while cũng được với điều kiện tương ứng. |
| 70 | `String course = getString(msg);` | Nhập môn không rỗng trước khi kiểm tra ba giá trị cho phép. |
| 71 | `if (course.equalsIgnoreCase("Java")) return "Java";` | Nhận Java không phân biệt hoa/thường nhưng trả dạng Java chuẩn. Không trả course để tránh nhiều cách viết trong dữ liệu. |
| 72 | `if (course.equalsIgnoreCase(".Net")) return ".Net";` | Nhận .Net bỏ qua hoa/thường và chuẩn hóa. Dấu chấm là ký tự thường khi so chuỗi, không phải regex. |
| 73 | `if (course.equalsIgnoreCase("C/C++")) return "C/C++";` | Nhận C/C++ và chuẩn hóa. + và / không cần escape trong so chuỗi trực tiếp. |
| 74 | `System.out.println("Course must be Java, .Net, or C/C++!");` | In "Course must be Java, .Net, or C/C++!" rồi xuống dòng. Thông báo lỗi rồi while sẽ hỏi lại; println không tự lặp hoặc xác thực. |
| 75 | `}` | Đóng khối gần nhất: if, vòng lặp, hàm hoặc lớp theo vị trí. Biến cục bộ trong khối hết phạm vi tại đây. |
| 76 | `}` | Đóng khối gần nhất: if, vòng lặp, hàm hoặc lớp theo vị trí. Biến cục bộ trong khối hết phạm vi tại đây. |
| 77 | `}` | Đóng khối gần nhất: if, vòng lặp, hàm hoặc lớp theo vị trí. Biến cục bộ trong khối hết phạm vi tại đây. |

## view/StudentView.java

| Dòng | Code | Làm gì, vì sao dùng và cách khác |
| --- | --- | --- |
| 1 | `package view;` | Đặt lớp trong package view, tương ứng thư mục dự án. Bỏ package phải sửa import/cách gọi; dùng package để tổ chức và tránh trùng tên lớp. |
| 3 | `import java.util.List;` | Cho dùng tên ngắn của java.util.List. Có thể viết tên đầy đủ mỗi chỗ dùng nhưng dài hơn. Import không tạo đối tượng hay chạy hàm; import cụ thể rõ phụ thuộc hơn *. |
| 4 | `import model.Report;` | Cho dùng tên ngắn của model.Report. Có thể viết tên đầy đủ mỗi chỗ dùng nhưng dài hơn. Import không tạo đối tượng hay chạy hàm; import cụ thể rõ phụ thuộc hơn *. |
| 5 | `import model.Student;` | Cho dùng tên ngắn của model.Student. Có thể viết tên đầy đủ mỗi chỗ dùng nhưng dài hơn. Import không tạo đối tượng hay chạy hàm; import cụ thể rõ phụ thuộc hơn *. |
| 7 | `public class StudentView {` | Khai báo lớp StudentView truy cập được từ package khác; { mở thân. Class gom dữ liệu/hành vi, không cần kế thừa khi chưa có quan hệ cha/con. |
| 8 | `/**` | Mở comment tài liệu. Không thực thi; /** dùng với @param/@return, // thích hợp ghi chú ngắn. |
| 9 | `* Hiển thị các chức năng của chương trình và nhận lựa chọn.` | Nội dung comment: Hiển thị các chức năng của chương trình và nhận lựa chọn. Không thay đổi chương trình. |
| 10 | `* @return lựa chọn hợp lệ từ 1 đến 5` | Mô tả giá trị trả về, không thay thế return. void và constructor không có @return. |
| 11 | `*/` | Đóng comment tài liệu; dòng sau được xử lý là code trở lại. |
| 12 | `public int showMenu() {` | Khai báo phương thức showMenu. Trả lựa chọn đã xác thực, không tự thực hiện nghiệp vụ trong view. |
| 13 | `showMessage("WELCOME TO STUDENT MANAGEMENT");` | In "WELCOME TO STUDENT MANAGEMENT". Gọi view thống nhất cách hiển thị, không thay đổi model. |
| 14 | `showMessage("1. Create");` | In "1. Create". Gọi view thống nhất cách hiển thị, không thay đổi model. |
| 15 | `showMessage("2. Find and Sort");` | In "2. Find and Sort". Gọi view thống nhất cách hiển thị, không thay đổi model. |
| 16 | `showMessage("3. Update/Delete");` | In "3. Update/Delete". Gọi view thống nhất cách hiển thị, không thay đổi model. |
| 17 | `showMessage("4. Report");` | In "4. Report". Gọi view thống nhất cách hiển thị, không thay đổi model. |
| 18 | `showMessage("5. Exit");` | In "5. Exit". Gọi view thống nhất cách hiển thị, không thay đổi model. |
| 19 | `return getInt("Please choose (1-5): ", 1, 5);` | Trả lựa chọn 1..5 đã xác thực cho controller. View bảo đảm miền trước khi switch. |
| 20 | `}` | Đóng khối gần nhất: if, vòng lặp, hàm hoặc lớp theo vị trí. Biến cục bộ trong khối hết phạm vi tại đây. |
| 22 | `/**` | Mở comment tài liệu. Không thực thi; /** dùng với @param/@return, // thích hợp ghi chú ngắn. |
| 23 | `* In thông báo ra màn hình.` | Nội dung comment: In thông báo ra màn hình. Không thay đổi chương trình. |
| 24 | `* @param message thông báo hiển thị hoặc hướng dẫn nhập` | Mô tả tham số message. Tag khớp tên trong chữ ký, không tạo/kiểm tra tham số khi chạy. |
| 25 | `*/` | Đóng comment tài liệu; dòng sau được xử lý là code trở lại. |
| 26 | `public void showMessage(String message) { System.out.println(message); }` | void in message qua println và xuống dòng; tách hàm thống nhất giao diện, gộp một dòng chỉ là định dạng. |
| 27 | `/**` | Mở comment tài liệu. Không thực thi; /** dùng với @param/@return, // thích hợp ghi chú ngắn. |
| 28 | `* Nhập chuỗi, loại bỏ khoảng trắng hai đầu và yêu cầu nhập lại nếu rỗng.` | Nội dung comment: Nhập chuỗi, loại bỏ khoảng trắng hai đầu và yêu cầu nhập lại nếu rỗng. Không thay đổi chương trình. |
| 29 | `* @param message thông báo hiển thị hoặc hướng dẫn nhập` | Mô tả tham số message. Tag khớp tên trong chữ ký, không tạo/kiểm tra tham số khi chạy. |
| 30 | `* @return chuỗi không rỗng đã loại bỏ khoảng trắng hai đầu` | Mô tả giá trị trả về, không thay thế return. void và constructor không có @return. |
| 31 | `*/` | Đóng comment tài liệu; dòng sau được xử lý là code trở lại. |
| 32 | `public String getString(String message) {` | Khai báo phương thức getString. Nhận lời nhắc và trả chuỗi hợp lệ; static ở Utility vì không cần trạng thái riêng từng đối tượng. |
| 33 | `return Utility.getString(message);` | Ủy quyền nhập cho Utility static rồi trả kết quả; controller chỉ biết view, không cần biết Scanner. |
| 34 | `}` | Đóng khối gần nhất: if, vòng lặp, hàm hoặc lớp theo vị trí. Biến cục bộ trong khối hết phạm vi tại đây. |
| 35 | `/**` | Mở comment tài liệu. Không thực thi; /** dùng với @param/@return, // thích hợp ghi chú ngắn. |
| 36 | `* Nhập số nguyên và yêu cầu nhập lại cho đến khi nằm trong giới hạn.` | Nội dung comment: Nhập số nguyên và yêu cầu nhập lại cho đến khi nằm trong giới hạn. Không thay đổi chương trình. |
| 37 | `* @param message thông báo hiển thị hoặc hướng dẫn nhập` | Mô tả tham số message. Tag khớp tên trong chữ ký, không tạo/kiểm tra tham số khi chạy. |
| 38 | `* @param min giá trị nhỏ nhất được chấp nhận` | Mô tả tham số min. Tag khớp tên trong chữ ký, không tạo/kiểm tra tham số khi chạy. |
| 39 | `* @param max giá trị lớn nhất được chấp nhận` | Mô tả tham số max. Tag khớp tên trong chữ ký, không tạo/kiểm tra tham số khi chạy. |
| 40 | `* @return số nguyên trong khoảng từ min đến max, bao gồm hai đầu` | Mô tả giá trị trả về, không thay thế return. void và constructor không có @return. |
| 41 | `*/` | Đóng comment tài liệu; dòng sau được xử lý là code trở lại. |
| 42 | `public int getInt(String message, int min, int max) {` | Khai báo phương thức getInt. Nhận lời nhắc/min/max để tái dùng cho menu, mã, học kỳ và số thứ tự. |
| 43 | `return Utility.getInt(message, min, max);` | Truyền lời nhắc/giới hạn sang Utility để không lặp logic kiểm tra số trong view. |
| 44 | `}` | Đóng khối gần nhất: if, vòng lặp, hàm hoặc lớp theo vị trí. Biến cục bộ trong khối hết phạm vi tại đây. |
| 45 | `/**` | Mở comment tài liệu. Không thực thi; /** dùng với @param/@return, // thích hợp ghi chú ngắn. |
| 46 | `* Nhập và chuẩn hóa môn học hợp lệ: Java, .Net hoặc C/C++.` | Nội dung comment: Nhập và chuẩn hóa môn học hợp lệ: Java, .Net hoặc C/C++. Không thay đổi chương trình. |
| 47 | `* @param message thông báo hiển thị hoặc hướng dẫn nhập` | Mô tả tham số message. Tag khớp tên trong chữ ký, không tạo/kiểm tra tham số khi chạy. |
| 48 | `* @return tên môn học hợp lệ đã được chuẩn hóa` | Mô tả giá trị trả về, không thay thế return. void và constructor không có @return. |
| 49 | `*/` | Đóng comment tài liệu; dòng sau được xử lý là code trở lại. |
| 50 | `public String getCourse(String message) { return Utility.getCourse(message); }` | View ủy quyền getCourse sang Utility static rồi trả kết quả. Thân một dòng chỉ là định dạng, có thể tách dòng. |
| 51 | `/**` | Mở comment tài liệu. Không thực thi; /** dùng với @param/@return, // thích hợp ghi chú ngắn. |
| 52 | `* Nhập lựa chọn Y hoặc N, không phân biệt chữ hoa và chữ thường.` | Nội dung comment: Nhập lựa chọn Y hoặc N, không phân biệt chữ hoa và chữ thường. Không thay đổi chương trình. |
| 53 | `* @param message thông báo hiển thị hoặc hướng dẫn nhập` | Mô tả tham số message. Tag khớp tên trong chữ ký, không tạo/kiểm tra tham số khi chạy. |
| 54 | `* @return true nếu chọn Y; false nếu chọn N` | Mô tả giá trị trả về, không thay thế return. void và constructor không có @return. |
| 55 | `*/` | Đóng comment tài liệu; dòng sau được xử lý là code trở lại. |
| 56 | `public boolean getYesNo(String message) { return Utility.getYesNo(message); }` | View ủy quyền getYesNo sang Utility static rồi trả kết quả. Thân một dòng chỉ là định dạng, có thể tách dòng. |
| 58 | `/**` | Mở comment tài liệu. Không thực thi; /** dùng với @param/@return, // thích hợp ghi chú ngắn. |
| 59 | `* Hiển thị danh sách kết quả tìm kiếm.` | Nội dung comment: Hiển thị danh sách kết quả tìm kiếm. Không thay đổi chương trình. |
| 60 | `* @param students danh sách bản ghi sinh viên cần hiển thị` | Mô tả tham số students. Tag khớp tên trong chữ ký, không tạo/kiểm tra tham số khi chạy. |
| 61 | `*/` | Đóng comment tài liệu; dòng sau được xử lý là code trở lại. |
| 62 | `public void showSearchResults(List<Student> students) {` | Khai báo phương thức showSearchResults. Nhận List đã tìm/sắp xếp để in, không tính lại. |
| 63 | `showMessage("\n--- Found & Sorted Students ---");` | In "\n--- Found & Sorted Students ---". Gọi view thống nhất cách hiển thị, không thay đổi model. |
| 64 | `for (Student student : students) {` | Enhanced for duyệt tham chiếu Student trong List, không cần chỉ số. Sửa trường đối tượng không đổi cấu trúc List; không thêm/xóa List đang duyệt theo cách này. |
| 65 | `System.out.println(student.getName() + " &#124; " + student.getSemester() + " &#124; " + student.getCourse());` | In student.getName() + " &#124; " + student.getSemester() + " &#124; " + student.getCourse() rồi xuống dòng. Dấu + nối cột/dấu phân cách; getter chỉ đọc để hiển thị. |
| 66 | `}` | Đóng khối gần nhất: if, vòng lặp, hàm hoặc lớp theo vị trí. Biến cục bộ trong khối hết phạm vi tại đây. |
| 67 | `System.out.println();` | In dòng trống phân cách phần hiển thị; không ảnh hưởng dữ liệu. |
| 68 | `}` | Đóng khối gần nhất: if, vòng lặp, hàm hoặc lớp theo vị trí. Biến cục bộ trong khối hết phạm vi tại đây. |
| 70 | `/**` | Mở comment tài liệu. Không thực thi; /** dùng với @param/@return, // thích hợp ghi chú ngắn. |
| 71 | `* Hiển thị các bản ghi sinh viên kèm số thứ tự để lựa chọn.` | Nội dung comment: Hiển thị các bản ghi sinh viên kèm số thứ tự để lựa chọn. Không thay đổi chương trình. |
| 72 | `* @param students danh sách bản ghi sinh viên cần hiển thị` | Mô tả tham số students. Tag khớp tên trong chữ ký, không tạo/kiểm tra tham số khi chạy. |
| 73 | `*/` | Đóng comment tài liệu; dòng sau được xử lý là code trở lại. |
| 74 | `public void showStudents(List<Student> students) {` | Khai báo phương thức showStudents. Nhận List để in kèm số thứ tự, controller quyết định lựa chọn. |
| 75 | `showMessage("Found student(s):");` | In "Found student(s):". Gọi view thống nhất cách hiển thị, không thay đổi model. |
| 76 | `showMessage("No. &#124; ID &#124; Name &#124; Semester &#124; Course");` | In "No. &#124; ID &#124; Name &#124; Semester &#124; Course". Gọi view thống nhất cách hiển thị, không thay đổi model. |
| 77 | `for (int i = 0; i < students.size(); i++) {` | Duyệt chỉ số để in số thứ tự: bắt đầu 0, < tránh vượt giới hạn, i++ tăng một. Enhanced for cũng được nếu thêm biến đếm. |
| 78 | `Student student = students.get(i);` | Lấy tham chiếu tại i để đọc các cột mà không lặp get(i). Không new vì chỉ hiển thị bản ghi có sẵn. |
| 79 | `System.out.printf("%d &#124; %d &#124; %s &#124; %d &#124; %s%n",` | Mẫu bảng: %d cho int, %s cho String, %n xuống dòng theo nền tảng. Đối số ở dòng kế tiếp để dòng không quá dài. |
| 80 | `i + 1, student.getId(), student.getName(), student.getSemester(), student.getCourse());` | Điền các cột đúng thứ tự mẫu. i+1 in số thứ tự từ 1 cho người dùng dù List đánh chỉ số từ 0. |
| 81 | `}` | Đóng khối gần nhất: if, vòng lặp, hàm hoặc lớp theo vị trí. Biến cục bộ trong khối hết phạm vi tại đây. |
| 82 | `}` | Đóng khối gần nhất: if, vòng lặp, hàm hoặc lớp theo vị trí. Biến cục bộ trong khối hết phạm vi tại đây. |
| 84 | `/**` | Mở comment tài liệu. Không thực thi; /** dùng với @param/@return, // thích hợp ghi chú ngắn. |
| 85 | `* Hiển thị tên sinh viên, môn học và tổng số lần đăng ký.` | Nội dung comment: Hiển thị tên sinh viên, môn học và tổng số lần đăng ký. Không thay đổi chương trình. |
| 86 | `* @param report danh sách các dòng báo cáo` | Mô tả tham số report. Tag khớp tên trong chữ ký, không tạo/kiểm tra tham số khi chạy. |
| 87 | `*/` | Đóng comment tài liệu; dòng sau được xử lý là code trở lại. |
| 88 | `public void showReport(List<Report> report) {` | Khai báo phương thức showReport. Nhận dữ liệu tổng hợp để in, không tính báo cáo trong view. |
| 89 | `showMessage("\n--- Report ---");` | In "\n--- Report ---". Gọi view thống nhất cách hiển thị, không thay đổi model. |
| 90 | `for (Report row : report) {` | Duyệt từng dòng báo cáo để in. Không cần chỉ số nên enhanced for gọn hơn vòng for đếm; view chỉ đọc, không tăng tổng lại. |
| 91 | `System.out.println(row.getStudentName() + " &#124; " + row.getCourse() + " &#124; " + row.getTotal());` | In row.getStudentName() + " &#124; " + row.getCourse() + " &#124; " + row.getTotal() rồi xuống dòng. Dấu + nối cột/dấu phân cách; getter chỉ đọc để hiển thị. |
| 92 | `}` | Đóng khối gần nhất: if, vòng lặp, hàm hoặc lớp theo vị trí. Biến cục bộ trong khối hết phạm vi tại đây. |
| 93 | `System.out.println();` | In dòng trống phân cách phần hiển thị; không ảnh hưởng dữ liệu. |
| 94 | `}` | Đóng khối gần nhất: if, vòng lặp, hàm hoặc lớp theo vị trí. Biến cục bộ trong khối hết phạm vi tại đây. |
| 95 | `}` | Đóng khối gần nhất: if, vòng lặp, hàm hoặc lớp theo vị trí. Biến cục bộ trong khối hết phạm vi tại đây. |

## controller/StudentController.java

| Dòng | Code | Làm gì, vì sao dùng và cách khác |
| --- | --- | --- |
| 1 | `package controller;` | Đặt lớp trong package controller, tương ứng thư mục dự án. Bỏ package phải sửa import/cách gọi; dùng package để tổ chức và tránh trùng tên lớp. |
| 3 | `import java.util.List;` | Cho dùng tên ngắn của java.util.List. Có thể viết tên đầy đủ mỗi chỗ dùng nhưng dài hơn. Import không tạo đối tượng hay chạy hàm; import cụ thể rõ phụ thuộc hơn *. |
| 4 | `import model.Student;` | Cho dùng tên ngắn của model.Student. Có thể viết tên đầy đủ mỗi chỗ dùng nhưng dài hơn. Import không tạo đối tượng hay chạy hàm; import cụ thể rõ phụ thuộc hơn *. |
| 5 | `import model.StudentManager;` | Cho dùng tên ngắn của model.StudentManager. Có thể viết tên đầy đủ mỗi chỗ dùng nhưng dài hơn. Import không tạo đối tượng hay chạy hàm; import cụ thể rõ phụ thuộc hơn *. |
| 6 | `import view.StudentView;` | Cho dùng tên ngắn của view.StudentView. Có thể viết tên đầy đủ mỗi chỗ dùng nhưng dài hơn. Import không tạo đối tượng hay chạy hàm; import cụ thể rõ phụ thuộc hơn *. |
| 8 | `public class StudentController {` | Khai báo lớp StudentController truy cập được từ package khác; { mở thân. Class gom dữ liệu/hành vi, không cần kế thừa khi chưa có quan hệ cha/con. |
| 9 | `private final StudentManager model;` | Giữ manager nhận từ ngoài; private bảo vệ truy cập, final gán một lần ở constructor. Danh sách trong manager vẫn đổi được. |
| 10 | `private final StudentView view;` | Giữ view dùng chung thay vì tạo lại ở mỗi chức năng; final cấm đổi tham chiếu. |
| 12 | `/**` | Mở comment tài liệu. Không thực thi; /** dùng với @param/@return, // thích hợp ghi chú ngắn. |
| 13 | `* Khởi tạo đối tượng StudentController với thông tin được cung cấp.` | Nội dung comment: Khởi tạo đối tượng StudentController với thông tin được cung cấp. Không thay đổi chương trình. |
| 14 | `* @param model đối tượng quản lý dữ liệu` | Mô tả tham số model. Tag khớp tên trong chữ ký, không tạo/kiểm tra tham số khi chạy. |
| 15 | `* @param view đối tượng giao diện nhập và xuất` | Mô tả tham số view. Tag khớp tên trong chữ ký, không tạo/kiểm tra tham số khi chạy. |
| 16 | `*/` | Đóng comment tài liệu; dòng sau được xử lý là code trở lại. |
| 17 | `public StudentController(StudentManager model, StudentView view) {` | Constructor cùng tên lớp, không có kiểu trả về, chạy khi new. Nhận dữ liệu hoặc phụ thuộc để gán trong thân; tiện hơn tạo rỗng rồi gọi nhiều setter. |
| 18 | `this.model = model;` | Gán tham số vào trường controller, this phân biệt hai tên giống nhau. Nhận manager từ ngoài giúp dùng đúng dữ liệu Main đã tạo. |
| 19 | `this.view = view;` | Gán view vào trường. view=view không có this chỉ tự gán tham số, không cập nhật trường. |
| 20 | `}` | Đóng khối gần nhất: if, vòng lặp, hàm hoặc lớp theo vị trí. Biến cục bộ trong khối hết phạm vi tại đây. |
| 22 | `/**` | Mở comment tài liệu. Không thực thi; /** dùng với @param/@return, // thích hợp ghi chú ngắn. |
| 23 | `* Hiển thị menu và xử lý chức năng được chọn cho đến khi thoát.` | Nội dung comment: Hiển thị menu và xử lý chức năng được chọn cho đến khi thoát. Không thay đổi chương trình. |
| 24 | `*/` | Đóng comment tài liệu; dòng sau được xử lý là code trở lại. |
| 25 | `public void run() {` | Khai báo phương thức run. Không tham số vì đã giữ model/view; void vì điều phối menu cho đến khi thoát. |
| 26 | `while (true) {` | Lặp chưa biết số lượt nhập/tiếp tục; thoát bằng break/return bên trong. for cố định số lượt không phù hợp; do/while cũng được với điều kiện tương ứng. |
| 27 | `switch (view.showMenu()) {` | Gọi menu rồi chọn nhánh số nguyên. if/else cũng đúng; switch dễ đọc với các lựa chọn cố định. |
| 28 | `case 1: createStudent(); break;` | Thêm rồi thoát switch, while tiếp tục menu. Thiếu break chạy sang case 2; return sẽ kết thúc run. |
| 29 | `case 2: findAndSort(); break;` | Tìm/sắp xếp rồi thoát switch; tách hàm để run chỉ điều phối. |
| 30 | `case 3: updateOrDelete(); break;` | Sửa/xóa rồi thoát switch, không chạy tiếp báo cáo. |
| 31 | `case 4: report(); break;` | Báo cáo rồi thoát switch để menu tiếp tục. |
| 32 | `case 5:` | Nhánh thoát. Không có default vì showMenu đã giới hạn 1..5; nếu nguồn lựa chọn thay đổi có thể cần default. |
| 33 | `view.showMessage("Exiting program. Goodbye!");` | In "Exiting program. Goodbye!". Gọi view thống nhất cách hiển thị, không thay đổi model. |
| 34 | `return;` | Kết thúc hàm void. Trong run quay về Main rồi kết thúc; trong chức năng con quay về run nên menu tiếp tục. Khác break chỉ thoát lặp/switch gần nhất. |
| 35 | `}` | Đóng khối gần nhất: if, vòng lặp, hàm hoặc lớp theo vị trí. Biến cục bộ trong khối hết phạm vi tại đây. |
| 36 | `}` | Đóng khối gần nhất: if, vòng lặp, hàm hoặc lớp theo vị trí. Biến cục bộ trong khối hết phạm vi tại đây. |
| 37 | `}` | Đóng khối gần nhất: if, vòng lặp, hàm hoặc lớp theo vị trí. Biến cục bộ trong khối hết phạm vi tại đây. |
| 39 | `/**` | Mở comment tài liệu. Không thực thi; /** dùng với @param/@return, // thích hợp ghi chú ngắn. |
| 40 | `* Nhập và thêm bản ghi sinh viên; yêu cầu đủ 10 bản ghi trước khi cho phép dừng.` | Nội dung comment: Nhập và thêm bản ghi sinh viên; yêu cầu đủ 10 bản ghi trước khi cho phép dừng. Không thay đổi chương trình. |
| 41 | `*/` | Đóng comment tài liệu; dòng sau được xử lý là code trở lại. |
| 42 | `public void createStudent() {` | Khai báo phương thức createStudent. Điều phối nhập nhiều bản ghi; thông tin nhận qua view nên không cần tham số. |
| 43 | `view.showMessage("--- Create New Student ---");` | In "--- Create New Student ---". Gọi view thống nhất cách hiển thị, không thay đổi model. |
| 44 | `while (true) {` | Lặp chưa biết số lượt nhập/tiếp tục; thoát bằng break/return bên trong. for cố định số lượt không phù hợp; do/while cũng được với điều kiện tương ứng. |
| 45 | `int id = view.getInt("Enter student ID: ", 1, Integer.MAX_VALUE);` | Nhập mã int dương tối đa 2147483647. String phù hợp mã SE001 hoặc cần giữ số 0 đầu; int là quy ước bản hiện tại. |
| 46 | `List<Student> existing = model.findById(id);` | Tìm mọi đăng ký cùng mã để quyết định dùng tên cũ hay nhập tên mới. |
| 47 | `String name;` | Khai báo trước if/else để dùng sau cả hai nhánh; Java buộc gán trước khi đọc, không cần giá trị rỗng giả. |
| 48 | `if (existing.isEmpty()) {` | Mã chưa có thì hỏi tên mới. List rỗng thay null giúp kiểm tra trực tiếp không phải kiểm tra null. |
| 49 | `name = view.getString("Enter student name: ");` | Nhập tên không rỗng qua view; không thêm Scanner trong controller vì Utility đã xử lý. |
| 50 | `} else {` | Đóng nhánh if và mở trường hợp ngược lại; không cần xét lại điều kiện. |
| 51 | `name = existing.get(0).getName();` | Lấy tên đầu tiên cùng mã; get(0) an toàn vì nhánh này chỉ chạy khi List không rỗng. Mọi bản ghi cùng mã giữ cùng tên. |
| 52 | `view.showMessage("Adding a course for: " + name);` | In "Adding a course for: " + name. Gọi view thống nhất cách hiển thị, không thay đổi model. |
| 53 | `}` | Đóng khối gần nhất: if, vòng lặp, hàm hoặc lớp theo vị trí. Biến cục bộ trong khối hết phạm vi tại đây. |
| 54 | `int semester = view.getInt("Enter semester: ", 1, Integer.MAX_VALUE);` | Nhập học kỳ nguyên dương. Nếu học kỳ là Fall2026 nên dùng String; int không bắt buộc cho mọi thiết kế. |
| 55 | `String course = view.getCourse("Enter course (Java, .Net, C/C++): ");` | Nhận một trong ba môn dạng chuẩn; getString đơn thuần không chặn môn ngoài danh sách. |
| 57 | `if (!model.add(new Student(id, name, semester, course))) {` | Tạo Student rồi gọi model.add; ! nhận trường hợp thất bại. Có thể tách Student và boolean để debug; viết gộp vì dùng một lần. |
| 58 | `view.showMessage("This student already has this course in this semester.");` | In "This student already has this course in this semester.". Gọi view thống nhất cách hiển thị, không thay đổi model. |
| 59 | `continue;` | Khi trùng, bỏ phần còn lại lượt thêm và quay về đầu while nhập lại từ ID. break sẽ dừng vòng, return dừng cả hàm. |
| 60 | `}` | Đóng khối gần nhất: if, vòng lặp, hàm hoặc lớp theo vị trí. Biến cục bộ trong khối hết phạm vi tại đây. |
| 61 | `view.showMessage("Student added successfully!");` | In "Student added successfully!". Gọi view thống nhất cách hiển thị, không thay đổi model. |
| 63 | `if (model.size() < 10) {` | Chưa đủ 10 bản ghi thì bắt nhập tiếp; size không đếm số mã khác nhau. |
| 64 | `view.showMessage("You need to create at least 10 students. Current total: " + model.size());` | In "You need to create at least 10 students. Current total: " + model.size(). Gọi view thống nhất cách hiển thị, không thay đổi model. |
| 65 | `} else if (!view.getYesNo("Do you want to continue")) {` | Từ 10 bản ghi trở lên mới hỏi tiếp tục. N trả false, ! đảo true để chạy break; Y lặp tiếp. |
| 66 | `break;` | Thoát vòng/switch gần nhất: add dừng khi lấy tên, report dừng khi tìm nhóm, controller dừng khi thao tác xong/chọn N. Không tự thoát hàm/chương trình. |
| 67 | `}` | Đóng khối gần nhất: if, vòng lặp, hàm hoặc lớp theo vị trí. Biến cục bộ trong khối hết phạm vi tại đây. |
| 68 | `}` | Đóng khối gần nhất: if, vòng lặp, hàm hoặc lớp theo vị trí. Biến cục bộ trong khối hết phạm vi tại đây. |
| 69 | `}` | Đóng khối gần nhất: if, vòng lặp, hàm hoặc lớp theo vị trí. Biến cục bộ trong khối hết phạm vi tại đây. |
| 71 | `/**` | Mở comment tài liệu. Không thực thi; /** dùng với @param/@return, // thích hợp ghi chú ngắn. |
| 72 | `* Tìm tên sinh viên theo từ khóa và hiển thị kết quả sắp xếp theo tên.` | Nội dung comment: Tìm tên sinh viên theo từ khóa và hiển thị kết quả sắp xếp theo tên. Không thay đổi chương trình. |
| 73 | `*/` | Đóng comment tài liệu; dòng sau được xử lý là code trở lại. |
| 74 | `public void findAndSort() {` | Khai báo phương thức findAndSort. Ở model nhận từ khóa và trả List vì có nhiều kết quả; ở controller void vì hỏi từ khóa, gọi model rồi hiển thị. |
| 75 | `if (model.isEmpty()) {` | Chặn danh sách gốc rỗng trước khi hỏi vô ích. Kiểm tra sớm giảm lồng if. |
| 76 | `view.showMessage("Student list is empty!");` | In "Student list is empty!". Gọi view thống nhất cách hiển thị, không thay đổi model. |
| 77 | `return;` | Kết thúc hàm void. Trong run quay về Main rồi kết thúc; trong chức năng con quay về run nên menu tiếp tục. Khác break chỉ thoát lặp/switch gần nhất. |
| 78 | `}` | Đóng khối gần nhất: if, vòng lặp, hàm hoặc lớp theo vị trí. Biến cục bộ trong khối hết phạm vi tại đây. |
| 80 | `String keyword = view.getString("Enter student name to find: ");` | Nhập từ khóa không rỗng; contains chuỗi rỗng sẽ khớp mọi tên nên giao diện không nhận. |
| 81 | `List<Student> matchedList = model.findAndSort(keyword);` | Model lọc/sắp xếp, controller giữ kết quả để kiểm tra/in; view không làm thuật toán. |
| 83 | `if (matchedList.isEmpty()) {` | Không tên nào khớp dù danh sách gốc có thể có dữ liệu; khác model.isEmpty. |
| 84 | `view.showMessage("Student does not exist!");` | In "Student does not exist!". Gọi view thống nhất cách hiển thị, không thay đổi model. |
| 85 | `return;` | Kết thúc hàm void. Trong run quay về Main rồi kết thúc; trong chức năng con quay về run nên menu tiếp tục. Khác break chỉ thoát lặp/switch gần nhất. |
| 86 | `}` | Đóng khối gần nhất: if, vòng lặp, hàm hoặc lớp theo vị trí. Biến cục bộ trong khối hết phạm vi tại đây. |
| 88 | `view.showSearchResults(matchedList);` | Gửi kết quả đã sắp xếp cho view, không định dạng từng dòng trong controller. |
| 89 | `}` | Đóng khối gần nhất: if, vòng lặp, hàm hoặc lớp theo vị trí. Biến cục bộ trong khối hết phạm vi tại đây. |
| 91 | `/**` | Mở comment tài liệu. Không thực thi; /** dùng với @param/@return, // thích hợp ghi chú ngắn. |
| 92 | `* Tìm sinh viên theo mã, chọn bản ghi và thực hiện cập nhật hoặc xóa.` | Nội dung comment: Tìm sinh viên theo mã, chọn bản ghi và thực hiện cập nhật hoặc xóa. Không thay đổi chương trình. |
| 93 | `*/` | Đóng comment tài liệu; dòng sau được xử lý là code trở lại. |
| 94 | `public void updateOrDelete() {` | Khai báo phương thức updateOrDelete. Gộp hai thao tác vì cùng bước tìm mã/chọn bản ghi; có thể tách hàm nhỏ nếu luồng mở rộng. |
| 95 | `if (model.isEmpty()) {` | Chặn danh sách gốc rỗng trước khi hỏi vô ích. Kiểm tra sớm giảm lồng if. |
| 96 | `view.showMessage("Student list is empty!");` | In "Student list is empty!". Gọi view thống nhất cách hiển thị, không thay đổi model. |
| 97 | `return;` | Kết thúc hàm void. Trong run quay về Main rồi kết thúc; trong chức năng con quay về run nên menu tiếp tục. Khác break chỉ thoát lặp/switch gần nhất. |
| 98 | `}` | Đóng khối gần nhất: if, vòng lặp, hàm hoặc lớp theo vị trí. Biến cục bộ trong khối hết phạm vi tại đây. |
| 100 | `int id = view.getInt("Enter student ID to update or delete: ", 1, Integer.MAX_VALUE);` | Nhập mã cần sửa/xóa với cùng kiểu/giới hạn lúc tạo. |
| 101 | `List<Student> foundStudents = model.findById(id);` | Tìm mọi đăng ký cùng mã để chọn đúng môn/học kỳ. |
| 103 | `if (foundStudents.isEmpty()) {` | Mã không tồn tại thì dừng trước get(0), tránh IndexOutOfBoundsException. |
| 104 | `view.showMessage("Student ID does not exist!");` | In "Student ID does not exist!". Gọi view thống nhất cách hiển thị, không thay đổi model. |
| 105 | `return;` | Kết thúc hàm void. Trong run quay về Main rồi kết thúc; trong chức năng con quay về run nên menu tiếp tục. Khác break chỉ thoát lặp/switch gần nhất. |
| 106 | `}` | Đóng khối gần nhất: if, vòng lặp, hàm hoặc lớp theo vị trí. Biến cục bộ trong khối hết phạm vi tại đây. |
| 108 | `view.showStudents(foundStudents);` | In danh sách kèm số thứ tự để người dùng chọn bản ghi. |
| 109 | `int index = 0;` | Mặc định chọn đầu danh sách nếu chỉ một kết quả; không hỏi thừa. |
| 110 | `if (foundStudents.size() > 1) {` | Chỉ hỏi số thứ tự khi có nhiều lựa chọn. |
| 111 | `index = view.getInt("Select record number: ", 1, foundStudents.size()) - 1;` | Đổi số thứ tự 1..n thành chỉ số 0..n-1 bằng trừ 1; không trừ sẽ lệch và chọn cuối vượt giới hạn. |
| 112 | `}` | Đóng khối gần nhất: if, vòng lặp, hàm hoặc lớp theo vị trí. Biến cục bộ trong khối hết phạm vi tại đây. |
| 113 | `Student selected = foundStudents.get(index);` | Lấy tham chiếu bản ghi chọn. Kết quả chứa cùng đối tượng gốc nên sửa selected ảnh hưởng dữ liệu gốc. |
| 115 | `while (true) {` | Lặp chưa biết số lượt nhập/tiếp tục; thoát bằng break/return bên trong. for cố định số lượt không phù hợp; do/while cũng được với điều kiện tương ứng. |
| 116 | `String choice = view.getString("Do you want to update (U) or delete (D) student? ");` | getString chỉ kiểm tra rỗng; if/else sau đó kiểm tra U/D và yêu cầu nhập lại khi sai. |
| 117 | `if (choice.equalsIgnoreCase("U")) {` | Nhận cập nhật U/u theo nội dung chuỗi. |
| 118 | `view.showMessage("Updating student ID: " + selected.getId());` | In "Updating student ID: " + selected.getId(). Gọi view thống nhất cách hiển thị, không thay đổi model. |
| 119 | `view.showMessage("Name changes apply to all records with this ID.");` | In "Name changes apply to all records with this ID.". Gọi view thống nhất cách hiển thị, không thay đổi model. |
| 120 | `String newName = view.getString("Enter new name: ");` | Giữ tên mới trong biến tạm, chưa sửa đối tượng trước kiểm tra trùng. |
| 121 | `int newSemester = view.getInt("Enter new semester: ", 1, Integer.MAX_VALUE);` | Giữ học kỳ mới hợp lệ trong biến tạm để tránh sửa nửa chừng. |
| 122 | `String newCourse = view.getCourse("Enter new course (Java, .Net, C/C++): ");` | Giữ môn mới hợp lệ và dạng chuẩn trước khi model kiểm tra. |
| 124 | `if (model.update(selected, newName, newSemester, newCourse)) {` | Model kiểm tra rồi sửa; boolean quyết định thông báo. Trùng là thất bại nghiệp vụ bình thường nên không cần exception. |
| 125 | `view.showMessage("Updated student successfully!");` | In "Updated student successfully!". Gọi view thống nhất cách hiển thị, không thay đổi model. |
| 126 | `} else {` | Đóng nhánh if và mở trường hợp ngược lại; không cần xét lại điều kiện. |
| 127 | `view.showMessage("Update failed: this course already exists in this semester.");` | In "Update failed: this course already exists in this semester.". Gọi view thống nhất cách hiển thị, không thay đổi model. |
| 128 | `}` | Đóng khối gần nhất: if, vòng lặp, hàm hoặc lớp theo vị trí. Biến cục bộ trong khối hết phạm vi tại đây. |
| 129 | `break;` | Thoát vòng/switch gần nhất: add dừng khi lấy tên, report dừng khi tìm nhóm, controller dừng khi thao tác xong/chọn N. Không tự thoát hàm/chương trình. |
| 130 | `} else if (choice.equalsIgnoreCase("D")) {` | Không chọn U thì kiểm tra D/d; lựa chọn khác vào else và hỏi lại. |
| 131 | `model.delete(selected);` | Xóa đúng đối tượng chọn bằng remove(Object). Student chưa override equals nên dùng danh tính mặc định; không xóa cả mã vì đã chọn một đăng ký. |
| 132 | `view.showMessage("Deleted student successfully!");` | In "Deleted student successfully!". Gọi view thống nhất cách hiển thị, không thay đổi model. |
| 133 | `break;` | Thoát vòng/switch gần nhất: add dừng khi lấy tên, report dừng khi tìm nhóm, controller dừng khi thao tác xong/chọn N. Không tự thoát hàm/chương trình. |
| 134 | `} else {` | Đóng nhánh if và mở trường hợp ngược lại; không cần xét lại điều kiện. |
| 135 | `view.showMessage("Please enter U or D!");` | In "Please enter U or D!". Gọi view thống nhất cách hiển thị, không thay đổi model. |
| 136 | `}` | Đóng khối gần nhất: if, vòng lặp, hàm hoặc lớp theo vị trí. Biến cục bộ trong khối hết phạm vi tại đây. |
| 137 | `}` | Đóng khối gần nhất: if, vòng lặp, hàm hoặc lớp theo vị trí. Biến cục bộ trong khối hết phạm vi tại đây. |
| 138 | `}` | Đóng khối gần nhất: if, vòng lặp, hàm hoặc lớp theo vị trí. Biến cục bộ trong khối hết phạm vi tại đây. |
| 140 | `/**` | Mở comment tài liệu. Không thực thi; /** dùng với @param/@return, // thích hợp ghi chú ngắn. |
| 141 | `* Tạo và hiển thị báo cáo tổng số lần đăng ký môn học của từng sinh viên.` | Nội dung comment: Tạo và hiển thị báo cáo tổng số lần đăng ký môn học của từng sinh viên. Không thay đổi chương trình. |
| 142 | `*/` | Đóng comment tài liệu; dòng sau được xử lý là code trở lại. |
| 143 | `public void report() {` | Khai báo phương thức report. Manager trả List<Report> tổng hợp; controller void vì chỉ tính và hiển thị. |
| 144 | `if (model.isEmpty()) {` | Chặn danh sách gốc rỗng trước khi hỏi vô ích. Kiểm tra sớm giảm lồng if. |
| 145 | `view.showMessage("Student list is empty!");` | In "Student list is empty!". Gọi view thống nhất cách hiển thị, không thay đổi model. |
| 146 | `return;` | Kết thúc hàm void. Trong run quay về Main rồi kết thúc; trong chức năng con quay về run nên menu tiếp tục. Khác break chỉ thoát lặp/switch gần nhất. |
| 147 | `}` | Đóng khối gần nhất: if, vòng lặp, hàm hoặc lớp theo vị trí. Biến cục bộ trong khối hết phạm vi tại đây. |
| 149 | `view.showReport(model.report());` | Model tính báo cáo mới và view in. Có thể tách List<Report> để debug; viết gộp vì chỉ dùng một lần. |
| 150 | `}` | Đóng khối gần nhất: if, vòng lặp, hàm hoặc lớp theo vị trí. Biến cục bộ trong khối hết phạm vi tại đây. |
| 151 | `}` | Đóng khối gần nhất: if, vòng lặp, hàm hoặc lớp theo vị trí. Biến cục bộ trong khối hết phạm vi tại đây. |

## Theo dõi một lần sửa và báo cáo

Danh sách có S1 `(1, An, 1, Java)`, S2 `(1, An, 2, Java)`, S3 `(1, An, 2, .Net)`:

1. findById(1) tạo List mới chứa cùng tham chiếu S1/S2/S3, không tạo Student mới.
2. Chọn số 2 → index=1 → selected trỏ S2.
3. Sửa tên Bình, học kỳ 3, Java: isDuplicate bỏ qua S2, không có `(1, 3, Java)` khác nên cho sửa.
4. Vòng đổi tên cập nhật S1/S2/S3 thành Bình; setter học kỳ chỉ đổi S2 thành 3.
5. report: S1 tạo Java=1, S2 tăng Java=2, S3 tạo .Net=1.
6. Xóa S2 khỏi List gốc rồi tính báo cáo mới: Java=1, .Net=1. List tìm cũ vẫn có thể giữ tham chiếu S2 nếu chưa được tạo lại.

Nếu sửa S2 thành học kỳ 1, Java thì trùng S1. update trả false trước mọi setter nên cả tên và thông tin cũ giữ nguyên.

## Giới hạn và lựa chọn khác

- size đếm đăng ký, không đếm người. createStudent hỏi dừng khi danh sách có ít nhất 10 bản ghi, không bắt thêm mới 10 bản ghi mỗi lần vào. Khi trùng code hỏi lại từ ID.
- Model dựa vào dữ liệu hợp lệ của controller/view; gọi trực tiếp với null, số âm hay Student không nằm trong List chưa được bảo vệ đầy đủ. Getter/setter không tự bảo đảm hợp lệ nếu chỉ đọc/gán.
- Constructor rỗng và setId chưa cần trong luồng chính. Có thể bỏ nếu chỉ muốn tạo dữ liệu đủ thông tin và không đổi mã. Constructor rỗng tạo int=0/String=null.
- Tìm mã trên ArrayList O(n). Find and Sort quét O(n) rồi sort k kết quả O(k log k), chưa tính độ dài chuỗi. Report xấu nhất O(n²); Map khóa mã/môn có thể tổng hợp trung bình O(n), đổi lại thêm thiết kế khóa và thứ tự hiển thị.
- compareToIgnoreCase không bảo đảm thứ tự từ điển tiếng Việt; Collator phù hợp quy tắc ngôn ngữ. Chuyển chữ thường không phải bỏ dấu.
- String phù hợp mã SE001 hoặc học kỳ Fall2026; int phù hợp quy ước bản này. Enum có thể hạn chế môn ở tầng model tốt hơn String.
- Tên lặp trên nhiều bản ghi để đơn giản; hệ thống lớn có thể tách hồ sơ và đăng ký môn để tránh đồng bộ tên.
- Report chụp dữ liệu lúc tạo, không tự cập nhật theo Student; chức năng tính báo cáo mới mỗi lần gọi.
- Dữ liệu chỉ trong RAM, thoát chương trình mất dữ liệu; muốn giữ thì thêm lưu file/cơ sở dữ liệu.

## Tự kiểm tra hiểu bài

1. Vì sao update truyền excluded còn add truyền null?
2. Vì sao môn dùng equalsIgnoreCase nhưng bản ghi bị loại dùng !=?
3. Vì sao findById trả List dù chỉ nhận một mã?
4. Vì sao số thứ tự phải trừ 1?
5. Vì sao sửa selected từ kết quả làm thay đổi bản ghi gốc?
6. Vì sao final List vẫn add/remove được?
7. Vì sao update đổi tên đi hết vòng nhưng add lấy tên thì break?
8. Vì sao total bắt đầu 1 và báo cáo không nhóm học kỳ?
9. return trong chức năng con có dừng chương trình không?
10. Vì sao nextLine rồi parseInt thay vì trộn nextInt/nextLine?
