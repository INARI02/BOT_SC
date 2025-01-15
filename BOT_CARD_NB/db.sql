-- Tạo cơ sở dữ liệu
CREATE DATABASE IF NOT EXISTS botdb;
USE botdb;

-- Xóa các bảng theo thứ tự phụ thuộc
DROP TABLE IF EXISTS transactions;
DROP TABLE IF EXISTS users;

-- Tạo lại các bảng

-- Bảng `users`
CREATE TABLE users (
    id VARCHAR(50) PRIMARY KEY,            -- Mã định danh người dùng (thẻ)
    name VARCHAR(100) NOT NULL,            -- Họ và tên
    dob VARCHAR(100) NOT NULL,                              -- Ngày sinh
    license_plate_number VARCHAR(50),      -- Số biển số xe
    address VARCHAR(255),                  -- Địa chỉ
    pin_hash VARCHAR(255) NULL,        -- Băm của mã PIN để xác thực
    public_key TEXT NOT NULL,              -- Khóa công khai dùng để xác thực chữ ký số
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- Ngày tạo
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP -- Ngày cập nhật
);

-- Bảng `transactions`
CREATE TABLE transactions (
    transaction_id INT AUTO_INCREMENT PRIMARY KEY, -- ID giao dịch
    user_id VARCHAR(50) NOT NULL,                 -- ID người dùng liên quan (liên kết với bảng `users`)
    amount DECIMAL(10, 2) NOT NULL,               -- Số tiền giao dịch
    transaction_date DATE DEFAULT CURRENT_DATE,   -- Ngày giao dịch
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE      -- Liên kết với bảng `users`
);

-- Kiểm tra xem các bảng đã tạo thành công
SHOW TABLES;
