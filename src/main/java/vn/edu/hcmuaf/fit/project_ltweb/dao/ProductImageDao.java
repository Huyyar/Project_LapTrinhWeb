package vn.edu.hcmuaf.fit.project_ltweb.dao;

import vn.edu.hcmuaf.fit.project_ltweb.model.Product;
import vn.edu.hcmuaf.fit.project_ltweb.model.ProductImage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
    public List<ProductImage> getImagesByProductId(int productId) {
        List<ProductImage> images = new ArrayList<>();
        String sql = "SELECT * FROM product_images WHERE product_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, productId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ProductImage img = new ProductImage();
                img.setId(rs.getInt("id"));
                img.setProduct_id(rs.getInt("product_id"));
                img.setImage_url(rs.getString("image_url"));
                img.setIs_default(rs.getBoolean("is_default"));
                images.add(img);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return images;
    }
    public ProductImage getDefaultImage(int productId) {
        String sql = "SELECT * FROM product_images WHERE product_id = ? AND is_default = 1 LIMIT 1";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, productId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                ProductImage img = new ProductImage();
                img.setId(rs.getInt("id"));
                img.setProduct_id(rs.getInt("product_id"));
                img.setImage_url(rs.getString("image_url"));
                img.setIs_default(true);
                return img;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public void deleteAllImagesByProductId(int product_id) {
        String sql = "DELETE FROM product_images WHERE product_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, product_id);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
