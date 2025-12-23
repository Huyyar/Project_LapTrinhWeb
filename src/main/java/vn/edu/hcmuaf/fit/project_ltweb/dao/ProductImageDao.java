package vn.edu.hcmuaf.fit.project_ltweb.dao;

import vn.edu.hcmuaf.fit.project_ltweb.model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProductImageDao {
    public void addProductImage(int product_id, String image_url, boolean is_default) {
        String sql = "INSERT INTO product_images (product_id, image_url, is_default) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, product_id);
            ps.setString(2, image_url);
            ps.setBoolean(3, is_default);

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
