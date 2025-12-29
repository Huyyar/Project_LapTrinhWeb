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
    public User getUserById(int id ) {
        String query = "select * from users where id = ? ";
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {

                int idU = rs.getInt("id");
                String emailU = rs.getString("email");
                String password = rs.getString("password");
                String fullname = rs.getString("fullname");
                String phone = rs.getString("phone");
                String gender = rs.getString("gender");
                Date birthdate = rs.getDate("birthdate");

                String avatar_url = rs.getString("avatar_url");
                String role = rs.getString("role");
                boolean active = rs.getBoolean("is_active");
                Date createdAt = rs.getDate("created_at");
                User u = new User(idU, emailU, password, fullname,phone,gender,birthdate, avatar_url, role, active, createdAt);
                return u;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
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

                Date createdAt = rs.getDate("created_at");
                User u = new User(id,email, password, fullname, avatar_url, role, active, createdAt);
                return u;
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return null;
    }


    public boolean updateUser(User u){
        String query = "UPDATE users SET fullname = ?, email = ? ,phone = ?,  birthdate = ? , gender = ?   WHERE id = ?";
        try {
            Connection conn =DBConnection.getConnection() ;
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, u.getFullname());
            ps.setString(2, u.getEmail());
            ps.setString(3, u.getPhone());
            ps.setDate(4, u.getBirthdate());
            ps.setString(5, u.getGender());
            ps.setInt(6, u.getId());
            int i = ps.executeUpdate();
            return i > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
}




