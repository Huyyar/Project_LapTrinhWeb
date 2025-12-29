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

    public int insertUser(User u) {

        String query = "INSERT INTO users(email, password,fullname, avatar_url,role, is_active, created_at) " +
                "VALUES(?, ?, ?,?,'user', true, CURRENT_TIMESTAMP())";


        try (Connection conn = DBConnection.getConnection();
             // Thêm tham số RETURN_GENERATED_KEYS để lấy ID tự tăng từ Database
             PreparedStatement ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {


            ps.setString(1, u.getEmail());
            ps.setString(2, u.getPassword());
            ps.setString(3, u.getFullname());
            ps.setString(4, u.getAvatar_url());

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
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {

                int id = rs.getInt("id");
                String emailU = rs.getString("email");
                String password = rs.getString("password");
                String fullname = rs.getString("fullname");
                String avatar_url = rs.getString("avatar_url");
                String role = rs.getString("role");
                boolean active = rs.getBoolean("is_active");
                Date d = rs.getDate("created_at");
                User u = new User(id, emailU, password, fullname, avatar_url, role, active, new Timestamp(d.getTime()));
                return u;
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return null;
    }

    public boolean updateUser(User u) {
        String query = "UPDATE users SET fullname = ?, email = ? ,phone = ?,  birthdate = ? , gender = ?   WHERE email = ?";
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, u.getFullname());
            ps.setString(2, u.getEmail());
            ps.setString(3, u.getPhone());
            ps.setTimestamp(4, u.getBirthdate());
            ps.setString(5, u.getGender());

            int rowsUpdated = ps.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
}




