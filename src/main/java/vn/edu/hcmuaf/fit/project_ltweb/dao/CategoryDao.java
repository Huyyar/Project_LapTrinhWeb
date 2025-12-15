package vn.edu.hcmuaf.fit.project_ltweb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoryDao {

    public String getCategory(int category_id){
        String sql = "SELECT name FROM categories WHERE id = ?";
        String category = "Không xác định"; // default nếu không tìm thấy

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, category_id); // set parameter
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    category = rs.getString("name"); // lấy tên category
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return category;
    }
}
