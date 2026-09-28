# Cách tự viết lại bài J1.L.P0021

Bài này cần nhớ luồng xử lý hơn là thuộc từng dòng. Chương trình dùng Java cơ bản, ArrayList, vòng lặp và if/else; phần sắp xếp giữ Collections.sort và Comparator theo hướng dẫn trong đề.

## 1. Thứ tự viết

1. `model/Student.java`: bốn thuộc tính id, name, semester, course; constructor và getter/setter.
2. `model/Report.java`: một dòng báo cáo gồm ID, tên, môn, số lần học.
3. `view/Utility.java`: nhập chuỗi không rỗng → số trong khoảng → Y/N → môn hợp lệ.
4. `model/StudentManager.java`: giữ ArrayList và viết từng chức năng xử lý.
5. `view/StudentView.java`: in menu, danh sách, kết quả tìm kiếm và báo cáo.
6. `controller/StudentController.java`: ghép nhập liệu, xử lý và thông báo.
7. `Main.java`: tạo model, tạo view, truyền vào controller rồi gọi run.

Có thể viết Main và menu sớm để chạy thử, sau đó hoàn thiện từng chức năng.

## 2. Nhớ vai trò của các lớp

- Student và Report: giữ dữ liệu.
- StudentManager: xử lý dữ liệu, không nhập hoặc in ra màn hình.
- Utility: đọc bàn phím và kiểm tra đầu vào.
- StudentView: giao tiếp với người dùng; các hàm nhập gọi Utility.
- StudentController: quyết định gọi hàm nào và theo thứ tự nào.
- Main: khởi động chương trình.

Đề yêu cầu OOP, không ghi bắt buộc MVC. Bản này giữ MVC từ code ban đầu để mỗi lớp có nhiệm vụ rõ ràng. Menu chỉ có năm lựa chọn cố định nên in trực tiếp trong StudentView, không cần lớp Menu riêng.

## 3. Điểm quan trọng nhất về dữ liệu

Một Student trong danh sách là **một lần đăng ký môn trong một học kỳ**.

Ví dụ:

```text
1 | An | 1 | Java
1 | An | 2 | Java
1 | An | 2 | .Net
```

Đó là một người, ba bản ghi. Báo cáo là:

```text
An | Java | 2
An | .Net | 1
```

Quy tắc hiện tại: cùng ID thì cùng tên; trùng cả ID, học kỳ và môn thì không thêm. Khi đổi tên, cập nhật mọi bản ghi có cùng ID. Khi xóa, chỉ xóa bản ghi đã chọn.

## 4. Công thức cho từng chức năng

### Create

Nhập ID → tìm ID → nếu đã có thì dùng tên cũ, chưa có thì nhập tên → nhập học kỳ và môn → kiểm tra trùng → thêm → nếu đủ 10 bản ghi thì hỏi tiếp tục.

`continue` khi trùng giúp quay lại nhập mà không tính bản ghi lỗi vào tổng số.

### Find and Sort

Tạo danh sách kết quả → duyệt danh sách gốc → tên chứa từ khóa thì thêm vào kết quả → sắp xếp kết quả → trả về để in.

`contains()` tìm một phần tên. Chuyển cả tên và từ khóa sang chữ thường để không phân biệt hoa/thường. `Locale.ROOT` giúp kết quả không phụ thuộc ngôn ngữ máy.

Trong Comparator, chỉ cần hiểu:

```java
return first.getName().compareToIgnoreCase(second.getName());
```

Giá trị âm: first đứng trước; 0: bằng nhau theo phép so sánh; dương: first đứng sau. Không cần tự viết thuật toán sắp xếp.

### Update/Delete

Nhập ID → tìm các bản ghi → không có thì thông báo → có nhiều thì chọn một → nhập U/D → sửa hoặc xóa.

Sửa phải kiểm tra trùng **trước khi thay đổi dữ liệu**. Hàm isDuplicate nhận `excluded` để bỏ qua chính đối tượng đang sửa. `student != excluded` so sánh hai tham chiếu đối tượng; không phải so sánh nội dung chuỗi.

### Report

Tạo danh sách báo cáo rỗng. Với mỗi Student, tìm dòng báo cáo cùng ID và môn:

- Tìm thấy: tăng total.
- Chưa thấy: tạo Report mới, total bắt đầu bằng 1.

Hai vòng lặp dễ theo dõi, phù hợp bài thực hành nhỏ. Độ phức tạp trường hợp xấu nhất O(n²); có thể cải thiện bằng Map nhưng chưa cần cho mục tiêu luyện viết bài này.

## 5. Những chỗ đề chưa rõ

- Đề vừa ghi “at least 10” vừa ghi “greater than 10”. Bản này hỏi Y/N ngay khi có đủ 10 bản ghi, và hỏi sau mỗi lần thêm tiếp theo.
- Đề không quy định kiểu ID và học kỳ. Bản hiện tại giữ số nguyên dương từ code ban đầu. Nếu giảng viên yêu cầu mã như SE001 hoặc học kỳ như Fall2026, phải đổi hai thuộc tính này sang String và sửa phần nhập/so sánh tương ứng.
- Ví dụ báo cáo trong đề không khớp bảng đầu vào: bảng có Nguyen Van B học .Net nhưng kết quả lại có Nguyen Van C. Code tổng hợp từ dữ liệu thực tế theo ID và môn.
- Chống trùng và đồng bộ tên theo ID là quy tắc của bản hiện tại; đề không mô tả chi tiết các trường hợp này.
- Dữ liệu nằm trong bộ nhớ và mất khi thoát; đề không yêu cầu lưu file.

## 6. Biên dịch trên máy khác

Cần JDK và mở terminal tại thư mục chứa Main.java. Giữ đúng các thư mục model, view, controller và các dòng package tương ứng.

```text
javac -encoding UTF-8 -d out Main.java model/*.java view/*.java controller/*.java
java -cp out Main
```

## 7. Tự kiểm tra khi thực hành

1. Chọn tìm/sửa/báo cáo khi danh sách trống.
2. Nhập sai menu, số âm, chuỗi rỗng và môn không hợp lệ.
3. Thêm đủ 10 bản ghi, chọn N để về menu.
4. Thêm trùng ID + học kỳ + môn, kiểm tra bị từ chối.
5. Tìm một phần tên, đổi hoa/thường, kiểm tra thứ tự tên.
6. Sửa một bản ghi thành bản ghi trùng, kiểm tra dữ liệu cũ còn nguyên.
7. Đổi tên một ID có nhiều môn, kiểm tra tên đổi đồng bộ.
8. Xóa một bản ghi rồi xem lại báo cáo.

Khi luyện, viết xong một chức năng thì chạy thử chức năng đó. Hãy nhớ chuỗi thao tác “nhập → tìm/kiểm tra → xử lý → hiển thị” và giải thích được mỗi vòng lặp.
