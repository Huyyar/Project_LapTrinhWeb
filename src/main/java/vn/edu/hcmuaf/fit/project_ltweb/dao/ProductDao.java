package vn.edu.hcmuaf.fit.project_ltweb.dao;

import vn.edu.hcmuaf.fit.project_ltweb.model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDao {
    public List<Product> getProducts(){
        List<Product> products = new ArrayList<>();
        String sql = "select * from products";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()){
            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setCategory_id(rs.getInt("category_id"));
                product.setName(rs.getString("name"));
                product.setDescription(rs.getString("description"));
                product.setPrice(rs.getDouble("price"));
                product.setImage_url(rs.getString("image_url"));
                product.setInventory_qty(rs.getInt("inventory_qty"));
                CategoryDao  categoryDao = new CategoryDao();
                product.setCategory(categoryDao.getCategory(product.getCategory_id()));
                products.add(product);
            }

        }catch (SQLException e) {
            e.printStackTrace();
        }
        return  products;
    }
    public int addProduct(Product product) {
        String sql = "INSERT INTO products(category_id, name, description, price, image_url, inventory_qty) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, product.getCategory_id());
            ps.setString(2, product.getName());
            ps.setString(3, product.getDescription());
            ps.setDouble(4, product.getPrice());
            ps.setString(5,product.getImage_url());
            ps.setInt(6, product.getInventory_qty());

            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1); // product_id
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
    public Product  getProduct(int id) {
        Product product = new Product();
        String sql = "select * from products where id = ?";
        try (
                Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql);
                ){
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                product.setId(rs.getInt("id"));
                product.setCategory_id(rs.getInt("category_id"));
                product.setName(rs.getString("name"));
                product.setDescription(rs.getString("description"));
                product.setPrice(rs.getDouble("price"));
                product.setImage_url(rs.getString("image_url"));
                product.setInventory_qty(rs.getInt("inventory_qty"));
                CategoryDao  categoryDao = new CategoryDao();
                product.setCategory(categoryDao.getCategory(product.getCategory_id()));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return product;
    }
}
