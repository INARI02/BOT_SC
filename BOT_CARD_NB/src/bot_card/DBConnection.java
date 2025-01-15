/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bot_card;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author N ~ N
 */
public class DBConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/botdb";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static Connection connect() {
        try {
            // Kết nối MySQL
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.err.println("Không thể kết nối tới MySQL!");
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) throws SQLException {
        try (Connection conn = connect()) {
            if (conn != null) {
                System.out.println("Kết nối MySQL thành công!");
                System.out.println(conn.getCatalog());
            }
        }
    }

    public static boolean UserInfo(String id, String name, String address, String dob, String licensePlate, String publicKey) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String sql = "INSERT INTO users (id, name, address, dob, license_plate_number, public_key) VALUES (?, ?, ?, ?, ?, ?)";

        try {
            // Lấy kết nối
            conn = DBConnection.connect();
            if (conn == null) {
                System.err.println("Kết nối cơ sở dữ liệu thất bại!");
                return false;
            }
            System.out.println("Connect Db");

            // Chuẩn bị câu lệnh SQL
            pstmt = conn.prepareStatement(sql);
            System.out.println("Prepare sql");

            // Gán giá trị cho các tham số
            pstmt.setString(1, id);
            pstmt.setString(2, name);
            pstmt.setString(3, address);
            pstmt.setString(4, dob);
            pstmt.setString(5, licensePlate);
            pstmt.setString(6, publicKey); // Thêm giá trị public_key
            System.out.println("prepare done");

            // Thực thi câu lệnh
            int rows = pstmt.executeUpdate();
            System.out.println("execute done");

            return rows > 0; // Trả về true nếu có ít nhất một dòng được thêm
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error");
            return false;
        } finally {
            // Đóng kết nối
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
                System.out.println("Close");
            } catch (SQLException ex) {
                ex.printStackTrace();
                System.out.println("Close Failed");
            }
        }
    }

    public static boolean updateUserInfo(String id, String name, String address, String dob, String licensePlate) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String sql = "UPDATE Users SET name = ?, dob = ?, address = ?, license_plate_number = ? WHERE id = ?";

        try {
            conn = DBConnection.connect(); // Hàm lấy kết nối tới DB
            pstmt = conn.prepareStatement(sql);

            // Gán giá trị cho các tham số
            pstmt.setString(1, name);
            pstmt.setString(2, dob);
            pstmt.setString(3, address);
            pstmt.setString(4, licensePlate);
            pstmt.setString(5, id); // Sử dụng id ở cuối để khớp với WHERE

            // Thực thi câu lệnh
            int rowsUpdated = pstmt.executeUpdate();

            System.out.println("Rows updated: " + rowsUpdated);
            return rowsUpdated > 0; // Trả về true nếu có ít nhất một dòng được cập nhật
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Trả về false nếu có lỗi xảy ra
        } finally {
            // Đóng kết nối
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    public static String getUserIDbyName(String name) {
        String userID = null;

        // Sử dụng try-with-resources để tự động đóng tài nguyên
        try (
            Connection conn = DBConnection.connect();
            PreparedStatement pstmt = conn.prepareStatement("SELECT id FROM users WHERE name = ?")
        ){

            pstmt.setString(1, name); // Truyền tham số vào câu truy vấn
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    userID = rs.getString("id"); // Lấy giá trị `id`
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Hoặc ghi log lỗi tùy theo yêu cầu
        }

        return userID; // Trả về `null` nếu không tìm thấy
        
    }
    
    public boolean addTransaction(String userId, double amount) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String sql = "INSERT INTO transactions (user_id, amount) VALUES (?, ?)";
        
        try {
            conn = DBConnection.connect();
            if (conn == null) {
                System.err.println("Kết nối cơ sở dữ liệu thất bại!");
                return false;
            }
            
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userId);
            pstmt.setDouble(2, amount);
            
            int rows = pstmt.executeUpdate();
            
            if (rows <= 0) {
                System.out.println("add success");
                return false;
            }
            
            return true;
        } catch (Exception e) {
            throw  e;
        } finally {
            if (pstmt != null) {
                pstmt.close();;
            }
            
            if (conn != null) {
                conn.close();
            }
        }
        
    }
    
    public static List<Map<String, Object>> loadTransactions(String userID) {
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    String sql = "SELECT transaction_id, amount, transaction_date FROM transactions where user_id=?";
    List<Map<String, Object>> transactions = new ArrayList<>();

    try {
        conn = DBConnection.connect(); // Hàm lấy kết nối tới DB
        pstmt = conn.prepareStatement(sql);
        
        pstmt.setString(1, userID);
        // Thực thi câu lệnh
        rs = pstmt.executeQuery();

        // Duyệt kết quả và đưa vào danh sách
        while (rs.next()) {
            Map<String, Object> transaction = new HashMap<>();
            transaction.put("transaction_id", rs.getInt("transaction_id"));
            transaction.put("amount", rs.getDouble("amount"));
            transaction.put("transaction_date", rs.getDate("transaction_date"));
            transactions.add(transaction);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        // Đóng kết nối
        try {
            if (rs != null) {
                rs.close();
            }
            if (pstmt != null) {
                pstmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    return transactions;
}
    
}
