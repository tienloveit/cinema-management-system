# MoviePTIT Cinema Ticket

MoviePTIT là ứng dụng đặt vé xem phim trực tuyến, được xây dựng theo mô hình full-stack. Dự án hỗ trợ khách hàng xem phim và suất chiếu, chọn ghế theo thời gian thực, đặt vé và thanh toán; đồng thời cung cấp các màn hình quản trị cho admin, manager và staff.

## Overview

Ứng dụng gồm frontend React/Vite và backend Spring Boot REST API. Dữ liệu nghiệp vụ được lưu trong MySQL, Redis dùng cho trạng thái token, OTP, giữ ghế và bộ nhớ chat AI. Người dùng có thể duyệt danh sách phim, xem rạp và suất chiếu, chọn ghế, mua combo bắp nước, áp dụng khuyến mãi, thanh toán VNPay và nhận vé điện tử có mã QR.

Hệ thống phục vụ bốn nhóm vai trò chính:

- **USER**: xem phim, đặt vé, thanh toán và quản lý lịch sử đặt vé.
- **STAFF**: đặt vé tại quầy, mở/đóng ca, check-in vé và xử lý các tác vụ vận hành tại chi nhánh được phân công.
- **MANAGER**: quản lý hoạt động của chi nhánh, nhân viên, suất chiếu, booking, đồ ăn, khuyến mãi và báo cáo vận hành.
- **ADMIN**: quản lý toàn bộ hệ thống, bao gồm phim, rạp, suất chiếu, người dùng, khuyến mãi, báo cáo và cấu hình hệ thống.

## Kiến trúc

Dự án được chia thành frontend và backend độc lập:

```text
cinema-ticket/
├── backend/
│   ├── src/main/java/com/ltweb/backend/
│   │   ├── config/       Cấu hình Security, JWT, Redis, WebSocket, VNPay và AI
│   │   ├── controller/   REST API cho client, staff và admin
│   │   ├── dto/          Request/response của API
│   │   ├── entity/       Entity JPA và entity token Redis
│   │   ├── enums/        Enum trạng thái, vai trò và loại dữ liệu
│   │   ├── event/        Domain event, ví dụ sự kiện thanh toán booking
│   │   ├── exception/    Mã lỗi và xử lý exception tập trung
│   │   ├── job/          Tác vụ định kỳ dọn dữ liệu hết hạn
│   │   ├── mapper/       MapStruct mapper
│   │   ├── repository/   Spring Data repository
│   │   ├── service/      Logic nghiệp vụ và phân quyền
│   │   └── util/         Tiện ích dùng chung
│   └── src/main/resources/application.yaml
├── frontend/
│   ├── src/api/          Client gọi backend API
│   ├── src/components/   Component giao diện dùng chung
│   ├── src/context/      Auth và state dùng chung
│   ├── src/pages/        Trang public, admin và staff
│   ├── App.jsx           Root component
│   └── AnimatedRoutes.jsx
├── docker-compose.yml
└── movie.sql
```

## Công nghệ sử dụng

### Backend

- **Java 21**
- **Spring Boot 3.4.0**
- Spring Web MVC và Spring Data JPA/JDBC
- Spring Security OAuth2 Resource Server với JWT HS512
- Spring Data Redis
- Spring WebSocket/STOMP
- Spring Validation
- Spring Mail với SMTP Gmail
- Spring AI OpenAI starter, cấu hình gọi Gemini qua OpenAI-compatible API
- MySQL Connector/J
- Lombok và MapStruct
- ZXing để tạo và đọc mã QR
- Maven Wrapper (`mvnw`, `mvnw.cmd`)

### Frontend

- **React 19**
- **Vite**
- React Router
- Axios
- STOMP.js và SockJS cho cập nhật trạng thái ghế theo thời gian thực
- Framer Motion cho animation
- Recharts cho biểu đồ báo cáo
- QRCode React để hiển thị vé điện tử
- React Toastify, html-to-image và canvas-confetti

### Hạ tầng và tích hợp

- MySQL 9.4 trong Docker Compose
- Redis
- Docker và Docker Compose
- VNPay Sandbox
- Google OAuth2
- Gemini AI

## Chức năng chính

### Khách hàng

- Xem trang chủ, danh sách phim, phim đang chiếu và phim sắp chiếu.
- Xem chi tiết phim, trailer, thể loại, đạo diễn, thông tin độ tuổi và đánh giá.
- Xem danh sách rạp, chi nhánh, phòng chiếu và suất chiếu.
- Chọn ghế theo thời gian thực, nhận cập nhật trạng thái ghế qua WebSocket.
- Chọn combo bắp nước và quản lý số lượng món ăn trong đơn.
- Đặt vé trực tuyến, áp dụng khuyến mãi và thanh toán bằng VNPay.
- Xem lịch sử đặt vé, chi tiết booking và vé điện tử có mã QR.
- Hủy booking hoặc gửi yêu cầu hoàn tiền theo trạng thái đơn.
- Đánh giá phim sau khi xem.
- Đăng ký, đăng nhập, đăng nhập Google, đổi mật khẩu và đặt lại mật khẩu bằng OTP email.
- Xem thông báo cá nhân.
- Sử dụng trợ lý chat AI để tra cứu phim, rạp và thông tin đặt vé.

### Nhân viên

- Xem dashboard và lịch sử ca làm.
- Mở ca, đóng ca và theo dõi lịch trực cá nhân.
- Đặt vé tại quầy với quy trình chọn phim, suất chiếu, ghế và combo.
- In thông tin booking/vé tại quầy.
- Check-in bằng mã vé hoặc ảnh mã QR.
- Xem và xử lý danh sách đơn đặt vé, thông báo vận hành.

### Quản lý chi nhánh

- Xem dashboard và báo cáo hoạt động của chi nhánh được phân công.
- Quản lý lịch làm việc và thông tin nhân viên thuộc chi nhánh.
- Quản lý suất chiếu, booking, hoàn tiền và các nghiệp vụ tại chi nhánh.
- Quản lý đồ ăn, tồn kho, khuyến mãi và báo cáo doanh thu chi tiết trong phạm vi được cấp quyền.
- Theo dõi thông báo và nhật ký hoạt động của chi nhánh.

### Quản trị viên

- Xem dashboard và thống kê booking, doanh thu, phim và hoạt động hệ thống.
- Quản lý phim, đạo diễn, thể loại và đánh giá.
- Quản lý chi nhánh, phòng chiếu, sơ đồ ghế và giá theo loại ghế.
- Quản lý suất chiếu theo phim, phòng và chi nhánh.
- Quản lý người dùng, vai trò, trạng thái tài khoản và nhân viên.
- Quản lý đồ ăn, combo, tồn kho và giao dịch nhập xuất kho.
- Quản lý mã khuyến mãi và kiểm tra điều kiện áp dụng.
- Quản lý booking, yêu cầu hoàn tiền và quyết định hoàn tiền.
- Xem báo cáo doanh thu, báo cáo vận hành và xuất file Excel.
- Quản lý thông báo, nhật ký audit và cấu hình hệ thống.

## Chạy dự án

### Chuẩn bị database và Redis

Tạo database MySQL:

```sql
CREATE DATABASE cinema CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```

Khởi động Redis tại `localhost:6379`. File `backend/movie.sql` hoặc script seed tương ứng có thể được dùng để tạo dữ liệu phim mẫu. Backend cũng có cơ chế seed dữ liệu ban đầu và lịch chiếu theo cấu hình trong `application.yaml`.

### Chạy backend

Trên Windows:

```powershell
cd backend
.\mvnw.cmd spring-boot:run
```

Trên Linux/macOS:

```bash
cd backend
./mvnw spring-boot:run
```

Backend mặc định chạy tại:

```text
http://localhost:8081/api
```

Build backend:

```bash
cd backend
./mvnw clean package
```

Trên Windows có thể thay `./mvnw` bằng `mvnw.cmd`.

### Chạy frontend

```bash
cd frontend
npm install
npm run dev
```

Frontend mặc định chạy tại:

```text
http://localhost:5173
```

Các lệnh frontend khác:

```bash
npm run build
npm run lint
npm run preview
```

### Chạy bằng Docker Compose

```bash
docker compose up -d
```

Docker Compose khởi động các service frontend, backend, MySQL và Redis. File compose hiện sử dụng các image `my-fe` và `my-be`, vì vậy cần build hoặc thay thế bằng image tương ứng trước khi chạy:

```bash
cd frontend
npm install
npm run build
cd ..
docker build -t my-be ./backend
docker build -t my-fe ./frontend
docker compose up -d
```

Frontend container chạy tại `http://localhost:5173`, backend container chạy tại `http://localhost:8081` và MySQL sử dụng database `cinema`.

