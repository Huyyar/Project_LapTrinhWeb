package vn.edu.hcmuaf.fit.project_ltweb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import vn.edu.hcmuaf.fit.project_ltweb.model.Category;

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

    public List<Category> getAllCategories(){
        List<Category> categories = new ArrayList<>();
        String sql = "SELECT id, name, display_order FROM categories ORDER BY display_order ASC";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                Category category = new Category();
                category.setId(rs.getInt("id"));
                category.setName(rs.getString("name"));
                category.setDisplay_order(rs.getInt("display_order"));
                categories.add(category);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return categories;
    }
}
