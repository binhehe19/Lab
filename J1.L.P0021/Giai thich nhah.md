# Giải thích nhanh bài 21 trước khi đi học

Tập trung hiểu luồng và các chỗ dễ bị hỏi trước. Chưa cần đọc hết tài liệu giải thích từng dòng.

## 1. Ba phút: nhớ nhiệm vụ các lớp

| Lớp | Nhiệm vụ |
| --- | --- |
| `Student` | Lưu một đăng ký môn của sinh viên trong một học kỳ. |
| `Report` | Lưu một dòng báo cáo: mã, tên, môn và tổng số lần đăng ký. |
| `StudentManager` | Quản lý danh sách: thêm, tìm, sắp xếp, sửa, xóa và tổng hợp báo cáo. |
| `Utility` | Nhập bàn phím và kiểm tra dữ liệu hợp lệ. |
| `StudentView` | Hiển thị thông tin và gọi Utility để nhập. |
| `StudentController` | Điều phối thứ tự nhập, xử lý và hiển thị. |
| `Main` | Tạo model, view, controller rồi chạy chương trình. |

Luồng cần nhớ: **Main → Controller → View/Utility nhập → Manager xử lý → View hiển thị.**

## 2. Năm phút: nhớ bốn chức năng

| Chức năng | Thứ tự xử lý |
| --- | --- |
| Thêm | Nhập ID → tìm ID → dùng tên cũ hoặc nhập tên mới → nhập học kỳ/môn → kiểm tra trùng → thêm. |
| Tìm và sắp xếp | Nhập từ khóa → lọc tên chứa từ khóa → sắp xếp tên → hiển thị. |
| Sửa/xóa | Nhập ID → tìm các bản ghi → chọn bản ghi → nhập U/D → sửa hoặc xóa. |
| Báo cáo | Duyệt từng đăng ký → tìm dòng cùng ID và môn → có thì tăng tổng, chưa có thì tạo dòng tổng bằng 1. |

## 3. Bảy phút: các chỗ dễ bị hỏi

| Code/khái niệm | Câu trả lời ngắn và lý do |
| --- | --- |
| `List<Student> students = new ArrayList<>();` | List là kiểu khai báo, ArrayList là triển khai danh sách tự tăng dung lượng. `<Student>` kiểm tra kiểu phần tử; `<>` suy ra kiểu. Mảng có độ dài cố định nên phải tự quản lý khi thêm dữ liệu. |
| Vì sao dùng ArrayList thay LinkedList? | Bài này thường duyệt và lấy phần tử theo chỉ số; ArrayList phù hợp. LinkedList vẫn dùng được nhưng chưa có lợi ích rõ rệt trong luồng này. |
| `==` với số nguyên | So giá trị, ví dụ hai ID kiểu int. |
| `==` / `!=` với đối tượng | So tham chiếu: có phải cùng một đối tượng hay không. Không dùng để so nội dung chuỗi. |
| `equalsIgnoreCase()` | So nội dung String và bỏ qua hoa/thường, phù hợp môn học và lựa chọn Y/N, U/D. |
| `student != excluded` | Khi sửa, bỏ qua chính bản ghi đang sửa để không bị coi là trùng với bản thân. |
| Thêm truyền `null`, sửa truyền `student` | Thêm không cần bỏ qua bản ghi nào; sửa phải bỏ qua chính đối tượng được chọn. |
| `return` | Kết thúc hàm hiện tại. Trong chức năng con thì quay về menu; trong run thì quay về Main rồi kết thúc chương trình. |
| `break` | Thoát vòng lặp hoặc switch gần nhất, không tự thoát cả hàm. |
| `continue` | Bỏ phần còn lại của lượt lặp và chuyển sang lượt tiếp theo. Khi thêm trùng, quay lại nhập từ ID. |
| Chọn số thứ tự rồi `- 1` | Người dùng đếm từ 1, List đánh chỉ số từ 0. Chọn số 2 tương ứng index 1. |
| `final List` vẫn thêm/xóa được | final cấm gán biến sang List khác, không cấm thay đổi phần tử bên trong List. |
| `nextLine()` rồi `parseInt()` | Dùng một cách đọc cả dòng, tránh ký tự xuống dòng còn lại khi trộn nextInt và nextLine. parseInt đổi chuỗi thành số; catch xử lý khi nhập sai. |
| `contains()` thay `equalsIgnoreCase()` khi tìm tên | contains tìm một phần tên; equalsIgnoreCase đòi cả tên giống nhau. Code đổi cả tên và từ khóa sang chữ thường trước khi contains. |
| `Comparator` | Quy định cách so hai Student theo tên. compare trả số âm, 0 hoặc dương để biểu diễn đứng trước, bằng hoặc đứng sau; không cần đúng -1 hoặc 1. |
| Kiểm tra trùng trước setter | Nếu bị trùng thì trả false ngay, dữ liệu cũ còn nguyên; không phải khôi phục dữ liệu đã sửa. |
| Sửa selected từ List kết quả ảnh hưởng List gốc | Hai List chứa cùng tham chiếu Student. List kết quả mới không có nghĩa Student bên trong là bản sao mới. |

## 4. Điểm quan trọng nhất về dữ liệu

**Một Student là một đăng ký môn trong một học kỳ.** Cùng ID có thể xuất hiện nhiều lần; chỉ từ chối khi trùng cả **ID + học kỳ + môn**.

Ví dụ:

```text
1 | An | 1 | Java
1 | An | 2 | Java
1 | An | 2 | .Net
```

Đây là **3 bản ghi của 1 sinh viên**. Báo cáo:

```text
An | Java | 2
An | .Net | 1
```

- `size()` đếm bản ghi, không đếm số ID khác nhau. Chức năng thêm hỏi dừng khi danh sách có ít nhất 10 bản ghi.
- Đổi tên thì cập nhật tất cả bản ghi cùng ID để giữ cùng người có cùng tên.
- Đổi học kỳ/môn hoặc xóa thì chỉ tác động bản ghi đã chọn.
- Báo cáo nhóm theo ID và môn, không theo học kỳ, để cộng số lần đăng ký qua các học kỳ.
- Dòng báo cáo mới bắt đầu `total = 1` vì đã có một đăng ký; gặp đăng ký cùng nhóm thì tăng thêm 1.

## 5. Cách trả lời “vì sao không dùng cách khác?”

Mẫu trả lời: **“Cách khác vẫn dùng được, em chọn cách này vì…”** rồi nêu lý do cụ thể.

- **Vì sao dùng List thay mảng?** Vì số bản ghi tăng/giảm khi thêm và xóa, List quản lý dung lượng thuận tiện hơn.
- **Vì sao tách MVC?** Để mỗi lớp có nhiệm vụ rõ: view nhập/in, model xử lý dữ liệu, controller điều phối. Chương trình console nhỏ vẫn có thể viết ít lớp hơn.
- **Vì sao dùng Comparator thay Comparable?** Vì muốn chọn tiêu chí sắp theo tên bên ngoài Student; có thể thêm tiêu chí khác mà không thay thứ tự mặc định của lớp.
- **Vì sao báo cáo dùng hai vòng lặp thay Map?** Vì dễ theo dõi trong bài nhỏ. Trường hợp xấu là O(n²); khi dữ liệu lớn, Map với khóa ID/môn có thể tổng hợp trung bình O(n).
- **Vì sao ID dùng int thay String?** Bản hiện tại quy ước ID là số nguyên dương. Nếu cần mã SE001 hoặc giữ số 0 đầu thì String phù hợp hơn.
- **Vì sao getYesNo trả boolean thay String?** Caller chỉ cần quyết định tiếp tục hay dừng, không phải so Y/N lại.

## 6. Chú ý để không giải thích sai

- `Student.getCourse()` và `Report.getCourse()` chỉ **lấy môn đang lưu**; `Utility.getCourse()` mới **nhập và kiểm tra môn**.
- `true` không luôn nghĩa thành công: `isDuplicate()` trả true nghĩa là **có trùng**, còn `add()` trả true nghĩa là **thêm thành công**.
- `private` và getter/setter giúp che trường dữ liệu, nhưng setter hiện chỉ gán nên không tự bảo đảm dữ liệu hợp lệ.
- Đổi sang chữ thường không phải bỏ dấu tiếng Việt; compareToIgnoreCase cũng không bảo đảm thứ tự từ điển tiếng Việt.
- Dữ liệu nằm trong RAM, thoát chương trình sẽ mất; code hiện chưa lưu file.

Trước khi đi học, tự nói lại được **vai trò lớp → luồng chức năng → quy tắc trùng → sáu khái niệm chính: List, so sánh, excluded, return/break/continue, chỉ số, final**.
