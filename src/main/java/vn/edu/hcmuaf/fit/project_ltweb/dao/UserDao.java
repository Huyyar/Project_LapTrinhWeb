package vn.edu.hcmuaf.fit.project_ltweb.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import vn.edu.hcmuaf.fit.project_ltweb.model.User;

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

        String query = "INSERT INTO users(email, password,fullname, avatar_url,role, is_active,verification_token, created_at) " +
                "VALUES(?, ?, ?,?,'user', true,?, CURRENT_TIMESTAMP())";


        try (Connection conn = DBConnection.getConnection();
             // Thêm tham số RETURN_GENERATED_KEYS để lấy ID tự tăng từ Database
             PreparedStatement ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {


            ps.setString(1, u.getEmail());
            ps.setString(2, u.getPassword());
            ps.setString(3, u.getFullname());
            ps.setString(4, u.getAvatar_url());
            ps.setString(5, u.getVerificationToken());
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

    //  hàm xác thực Token
    public boolean activateUser(String token) {
        String query = "UPDATE users SET is_active = true, verification_token = NULL WHERE verification_token = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, token);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
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
                String phone = rs.getString("phone");
                String gender = rs.getString("gender");
                Date birthdate = rs.getDate("birthdate");
                String avatar_url = rs.getString("avatar_url");
                String role = rs.getString("role");
                boolean active = rs.getBoolean("is_active");
                Date createdAt = rs.getDate("created_at");

                User  u = new User(id, emailU, password, fullname,phone,gender,birthdate, avatar_url, role, active, createdAt);
                return u;
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return null;
    }


    public boolean updatePUser(User u){
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
    public boolean updatePassword(String newPassword,User u) {
        String query = "Update users set password = ? where id = ? ";
        try {
            Connection conn = DBConnection.getConnection() ;
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, newPassword);
            ps.setInt(2, u.getId());
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Get all users with pagination
    public java.util.List<User> getAllUsers(int offset, int limit) {
        String query = "SELECT * FROM users ORDER BY created_at DESC LIMIT ? OFFSET ?";
        java.util.List<User> users = new java.util.ArrayList<>();
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, limit);
            ps.setInt(2, offset);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String email = rs.getString("email");
                String password = rs.getString("password");
                String fullname = rs.getString("fullname");
                String phone = rs.getString("phone");
                String gender = rs.getString("gender");
                Date birthdate = rs.getDate("birthdate");
                String avatar_url = rs.getString("avatar_url");
                String role = rs.getString("role");
                boolean active = rs.getBoolean("is_active");
                Date createdAt = rs.getDate("created_at");
                User u = new User(id, email, password, fullname, phone, gender, birthdate, avatar_url, role, active, createdAt);
                users.add(u);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    // Get total number of users
    public int getTotalUsers() {
        String query = "SELECT COUNT(*) as total FROM users";
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("total");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }
    
   
    public int getUsersRegisteredToday() {
        String query = "SELECT COUNT(*) as total FROM users WHERE DATE(created_at) = CURDATE()";
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("total");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

    // Search users by email or fullname
    public java.util.List<User> searchUsers(String search, int offset, int limit) {
        String query = "SELECT * FROM users WHERE email LIKE ? OR fullname LIKE ? ORDER BY created_at DESC LIMIT ? OFFSET ?";
        java.util.List<User> users = new java.util.ArrayList<>();
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            String searchPattern = "%" + search + "%";
            ps.setString(1, searchPattern);
            ps.setString(2, searchPattern);
            ps.setInt(3, limit);
            ps.setInt(4, offset);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String email = rs.getString("email");
                String password = rs.getString("password");
                String fullname = rs.getString("fullname");
                String phone = rs.getString("phone");
                String gender = rs.getString("gender");
                Date birthdate = rs.getDate("birthdate");
                String avatar_url = rs.getString("avatar_url");
                String role = rs.getString("role");
                boolean active = rs.getBoolean("is_active");
                Date createdAt = rs.getDate("created_at");
                User u = new User(id, email, password, fullname, phone, gender, birthdate, avatar_url, role, active, createdAt);
                users.add(u);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    // Get total search results
    public int getTotalSearchUsers(String search) {
        String query = "SELECT COUNT(*) as total FROM users WHERE email LIKE ? OR fullname LIKE ?";
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            String searchPattern = "%" + search + "%";
            ps.setString(1, searchPattern);
            ps.setString(2, searchPattern);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("total");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

    // Lock/Unlock user
    public boolean lockUser(int userId, boolean isLock) {
        String query = "UPDATE users SET is_active = ? WHERE id = ?";
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setBoolean(1, !isLock); // false = locked, true = active
            ps.setInt(2, userId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Delete user
    public boolean deleteUser(int userId) {
        String query = "DELETE FROM users WHERE id = ?";
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, userId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Update user password by admin
    public boolean updateUserPasswordByAdmin(int userId, String newPassword) {
        String query = "UPDATE users SET password = ? WHERE id = ?";
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, newPassword);
            ps.setInt(2, userId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}




