package vn.edu.hcmuaf.fit.project_ltweb.dao;

import vn.edu.hcmuaf.fit.project_ltweb.model.User;

import java.sql.*;

public class UserDao {
    public boolean isUserExists(String email) {
        String query = "SELECT* FROM users where email=?";
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public int insertUser(String email, String password,String avatar_url) {

        String query = "INSERT INTO users(email, password, avatar_url,role, is_active, created_at) " +
                "VALUES(?, ?, ?,'user', true, CURRENT_TIMESTAMP())";


        try (Connection conn = DBConnection.getConnection();
             // Thêm tham số RETURN_GENERATED_KEYS để lấy ID tự tăng từ Database
             PreparedStatement ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {


            ps.setString(1, email);
            ps.setString(2, password);
            ps.setString(3,avatar_url);


            int affectedRows = ps.executeUpdate();


            if (affectedRows > 0) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        return rs.getInt(1); // Trả về ID vừa được tạo
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Lỗi : " + e.getMessage());
        }

        return -1;
    }

    public User getUserByEmail(String email) {
        String query = "select * from users where email = ? ";
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1,email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {

                int id = rs.getInt("id");
                String emailU = rs.getString("email");
                String password = rs.getString("password");
                String fullname = rs.getString("fullname");
                String avatar_url =rs.getString("avatar_url");
                String role = rs.getString("role");
                boolean active = rs.getBoolean("is_active");
                Date d = rs.getDate("created_at");
                User u = new User(id, emailU, password, fullname,avatar_url, role, active, new Timestamp(d.getTime()));
                return u;
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return null;
    }


}



