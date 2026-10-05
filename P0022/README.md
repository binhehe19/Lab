# J1.L.P0022 — Quản lý ứng viên theo MVC

Bài này dùng Java thuần, có cấu trúc giống bài 0021. Dữ liệu lưu trong ArrayList, đóng chương trình thì mất dữ liệu.

## Chạy chương trình

Mở terminal trong thư mục `D:\Lab\Lab\P0022`, chạy:

```powershell
javac -encoding UTF-8 -d out Main.java model/*.java view/*.java controller/*.java
java -cp out Main
```

## Nhớ vai trò từng phần

| Phần | File | Nhiệm vụ |
| --- | --- | --- |
| Model | Candidate | Chứa thuộc tính chung của ứng viên |
| Model | Experience, Fresher, Intern | Kế thừa Candidate, thêm thuộc tính riêng |
| Model | CandidateManager | Lưu danh sách, kiểm tra ID trùng, thêm, tìm kiếm |
| View | Utility | Nhập và kiểm tra dữ liệu, sai thì nhập lại |
| View | CandidateView | Hiện menu, nhập thông tin, in danh sách và kết quả |
| Controller | CandidateController | Gọi View để nhập → gọi Model xử lý → gọi View hiển thị |
| Khởi động | Main | Tạo Model, View, Controller rồi gọi run() |

Model không dùng Scanner hoặc System.out. Controller không tự in hay đọc bàn phím. Việc nhập/xuất nằm trong View và Utility.

## Thứ tự tự code lại

1. Viết `Candidate`: khai báo 8 thuộc tính chung → constructor → getter.
2. Viết 3 lớp con: `extends Candidate` → constructor gọi `super(...)` → lưu thuộc tính riêng → getter.
3. Viết `CandidateManager`: một ArrayList và các hàm `containsId`, `add`, `getAll`, `search`.
4. Viết `Utility`: vòng lặp nhập → kiểm tra → đúng thì return, sai thì thông báo và nhập lại.
5. Viết `CandidateView`: menu, các hàm nhập, hai hàm in danh sách/kết quả.
6. Viết `CandidateController`: vòng lặp menu, tạo ứng viên, tìm ứng viên.
7. Viết `Main`: nối các phần và chạy.

## Hai luồng cần thuộc

**Thêm:** chọn menu → kiểm tra ID → nhập thông tin chung → nhập thông tin riêng theo type → tạo đối tượng → thêm vào danh sách → hỏi Y/N → chọn N thì in danh sách.

**Tìm:** in danh sách theo 3 nhóm → nhập tên và type → duyệt danh sách → tên khớp và type đúng thì thêm vào kết quả → in kết quả.

Điều kiện tìm trong Model:

```java
if (matchesName && candidate.getType() == type) {
    result.add(candidate);
}
```

`matchesName` nghĩa là First Name **hoặc** Last Name chứa từ khóa. Chuyển về chữ thường để tìm không phân biệt hoa/thường. Ví dụ `eva` tìm được cả `Eva` và `Adeleva` nếu cùng loại.

## Kế thừa và đa hình trong bài

```java
Candidate candidate = new Experience(...);
```

Đây là minh họa, dấu `...` cần thay bằng các tham số thật khi code. Biến kiểu cha Candidate có thể giữ đối tượng lớp con Experience. Vì vậy một `List<Candidate>` lưu được cả Experience, Fresher và Intern. Khi gọi getter chung, không cần ép kiểu.

`super(...)` gọi constructor lớp cha để gán thông tin chung. `this.expInYear = expInYear` gán thông tin riêng của lớp con. Candidate là abstract vì chương trình tạo ứng viên thuộc một trong ba loại cụ thể.

Menu dùng 1, 2, 3; type theo đề dùng 0, 1, 2. Vì vậy Controller truyền `choice - 1` khi tạo.

## Điều kiện nhập theo đề

- Năm sinh: đúng 4 chữ số, từ 1900 đến năm hiện tại, lấy bằng `Year.now()`.
- Điện thoại: chỉ có chữ số, ít nhất 10 chữ số. Lưu String để giữ số 0 đầu.
- Email: dạng tài khoản@tên-miền, ví dụ `annguyen@fpt.edu.vn`.
- Kinh nghiệm: số nguyên từ 0 đến 100.
- Xếp loại: Excellence, Good, Fair, Poor; nhập không phân biệt hoa/thường.
- Thông tin chuỗi không được rỗng; ID không trùng, không phân biệt hoa/thường.

`[0-9]{4}`: đúng 4 chữ số. `[0-9]{10,}`: ít nhất 10 chữ số.

Đề không quy định định dạng ngày tốt nghiệp hoặc học kỳ nên lưu chuỗi không rỗng, giúp code đơn giản. Chỉ năm sinh được kiểm tra theo khoảng năm.

Đoạn giới thiệu có nhắc cập nhật/xóa, nhưng menu và chức năng chi tiết chỉ yêu cầu tạo, tìm và thoát. Bản này bám menu đó.

## Tự kiểm tra

Tạo đủ ba loại ứng viên. Thử năm sinh `1899`, `02000`, năm tương lai; điện thoại dưới 10 số hoặc có chữ; email thiếu @; kinh nghiệm -1 và 101; xếp loại sai. Chương trình phải yêu cầu nhập lại.

Tạo Experience tên Aguirre Eva và Antosova Adeleva, tìm `EVA` với type 0: phải ra cả hai. Tìm với type 1: không ra hai ứng viên Experience. Thử ID trùng, danh sách rỗng, chọn Y để tạo tiếp và N để quay về menu.
