# J1.L.P0023 — Fruit Shop theo MVC

Java thuần, cấu trúc tương tự bài 21 và 22. Comment Việt–Anh dùng câu ngắn, dễ hiểu.
Dữ liệu lưu trong bộ nhớ, thoát chương trình thì mất dữ liệu.

## Chạy

Mở terminal trong thư mục P0023:

```powershell
javac -encoding UTF-8 -d out Main.java model/*.java view/*.java controller/*.java
java -cp out Main
```

Cần JDK 8 trở lên.

## Vai trò các lớp

| Lớp | Nhiệm vụ |
| --- | --- |
| Fruit | ID, tên, giá, tồn kho, xuất xứ |
| OrderItem | ID trái cây, tên, giá lúc mua, số lượng và thành tiền |
| FruitManager | ArrayList trái cây, xử lý mua và Hashtable đơn theo tên khách |
| Utility | Một Scanner, kiểm tra dữ liệu nhập |
| FruitView | Menu và toàn bộ nhập/xuất |
| FruitController | Điều phối tạo trái cây, xem đơn và mua hàng |
| Main | Tạo đối tượng và chạy |

Chỉ main dùng static. Hàm trả dữ liệu có đúng một return.
Các hàm chỉ in hoặc điều phối giữ void để không tạo kết quả giả.
createFruit trả số loại đã thêm; menu hiện chưa cần sử dụng số này.
Model không đọc bàn phím và không in màn hình.
Chỉ dùng ArrayList và Hashtable để lưu các danh sách theo yêu cầu đề.

## Luồng xử lý

- Tạo: nhập ID chưa trùng → tên → giá → số lượng → xuất xứ → hỏi tiếp tục.
  Chọn N thì hiện tất cả trái cây rồi về menu.
- Mua: hiện hàng còn tồn → chọn Item → nhập số lượng → trừ tồn kho → hỏi đặt ngay.
  Chọn N để mua tiếp; chọn Y để xem giỏ và nhập tên khách.
- Một loại trái cây được chọn nhiều lần trong cùng giỏ sẽ gộp số lượng.
- Nếu mua hết hàng, chương trình chuyển đến bước hoàn tất đơn để tránh chọn từ danh sách rỗng.
- Khách nhập cùng tên (phân biệt hoa/thường) nhiều lần sẽ giữ toàn bộ các lần mua dưới tên đó,
  không ghi đè đơn cũ. Các lần mua khác nhau có thể hiện thành các dòng riêng.
- Hashtable không bảo đảm thứ tự hiển thị khách.

## Kiểm tra dữ liệu

Chuỗi không rỗng; ID không trùng và không phân biệt hoa/thường.
Giá là số hữu hạn lớn hơn 0. Số lượng là số nguyên dương; số mua không vượt tồn kho.
Lựa chọn menu/Item phải trong giới hạn, xác nhận chỉ nhận Y/N.
Giá dùng double cho bài console đơn giản, in hai chữ số thập phân.

## Tự thử

Tạo Apple giá 1, tồn 3 và Mango giá 2, tồn 2.
Mua 3 Apple và 2 Mango: tổng 7, tồn kho hết. Lưu tên Marry Carie rồi xem đơn.
Thử ID trùng, chuỗi rỗng, giá âm/NaN/Infinity, số lượng có chữ/âm/vượt tồn kho,
Item sai và xác nhận sai. Chương trình phải yêu cầu nhập lại.
Mua một trái cây hai lần trong một giỏ và đặt hai lần cùng tên khách để kiểm tra không mất dữ liệu.
