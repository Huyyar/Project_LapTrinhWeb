package vn.edu.hcmuaf.fit.project_ltweb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import vn.edu.hcmuaf.fit.project_ltweb.model.Product;

public class ProductDao {
    public List<Product> getProducts() {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT p.*, c.name AS category_name FROM products p " +
                "LEFT JOIN categories c ON p.category_id = c.id ";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setCategory_id(rs.getInt("category_id"));
                product.setName(rs.getString("name"));
                product.setDescription(rs.getString("description"));
                product.setPrice(rs.getDouble("price"));
                product.setImage_url(rs.getString("image_url"));
                product.setInventory_qty(rs.getInt("inventory_qty"));
                product.setFeatured(rs.getBoolean("featured"));
                product.setIs_active(rs.getBoolean("is_active"));
                product.setCategory(rs.getString("category_name"));
                products.add(product);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    public int addProduct(Product product) {
        String sql = "INSERT INTO products(category_id, name, description, price, image_url, inventory_qty, featured, is_active) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, product.getCategory_id());
            ps.setString(2, product.getName());
            ps.setString(3, product.getDescription());
            ps.setDouble(4, product.getPrice());
            ps.setString(5, product.getImage_url());
            ps.setInt(6, product.getInventory_qty());
            ps.setBoolean(7, product.getFeatured());
            ps.setBoolean(8, product.getIs_active());

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

    public Product getProduct(int id) {
        Product product = new Product();
        String sql = "SELECT p.*, c.name AS category_name FROM products p " +
                "LEFT JOIN categories c ON p.category_id = c.id " +
                "WHERE p.id = ?";
        try (
                Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql);
        ) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                product.setId(rs.getInt("id"));
                product.setCategory_id(rs.getInt("category_id"));
                product.setName(rs.getString("name"));
                product.setDescription(rs.getString("description"));
                product.setPrice(rs.getDouble("price"));
                product.setImage_url(rs.getString("image_url"));
                product.setInventory_qty(rs.getInt("inventory_qty"));
                product.setFeatured(rs.getBoolean("featured"));
                product.setIs_active(rs.getBoolean("is_active"));
                product.setCategory(rs.getString("category_name"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return product;
    }

    public int getTotalFeaturedProducts(){
        String sql = "SELECT COUNT(*) FROM products WHERE featured = 1";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()
        ) {
            if (rs.next()) return rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    public List<Product> getFeaturedProducts(int offset, int pageSize) {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT p.*, c.name AS category_name FROM products p " +
                "LEFT JOIN categories c ON p.category_id = c.id " +
                "WHERE p.featured = 1 LIMIT ? OFFSET ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, pageSize);
            ps.setInt(2, offset);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Product product = new Product();
                    product.setId(rs.getInt("id"));
                    product.setCategory_id(rs.getInt("category_id"));
                    product.setName(rs.getString("name"));
                    product.setDescription(rs.getString("description"));
                    product.setPrice(rs.getDouble("price"));
                    product.setImage_url(rs.getString("image_url"));
                    product.setInventory_qty(rs.getInt("inventory_qty"));
                    product.setFeatured(rs.getBoolean("featured"));
                    product.setIs_active(rs.getBoolean("is_active"));
                    product.setCategory(rs.getString("category_name"));
                    products.add(product);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    public int getTotalProducts() {
        String sql = "SELECT COUNT(*) FROM products";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()
        ) {
            if (rs.next()) return rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int getTotalSearchProducts(String search) {
        String sql = "SELECT COUNT(*) FROM products WHERE name LIKE ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
        ) {
            ps.setString(1, "%" + search + "%");
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public List<Product> getPagedProducts(int offset, int pageSize) {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT p.*, c.name AS category_name FROM products p " +
                "LEFT JOIN categories c ON p.category_id = c.id " +
                "WHERE LIMIT ? OFFSET ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, pageSize);
            ps.setInt(2, offset);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Product product = new Product();
                    product.setId(rs.getInt("id"));
                    product.setCategory_id(rs.getInt("category_id"));
                    product.setName(rs.getString("name"));
                    product.setDescription(rs.getString("description"));
                    product.setPrice(rs.getDouble("price"));
                    product.setImage_url(rs.getString("image_url"));
                    product.setInventory_qty(rs.getInt("inventory_qty"));
                    product.setFeatured(rs.getBoolean("featured"));
                    product.setIs_active(rs.getBoolean("is_active"));
                    product.setCategory(rs.getString("category_name"));
                    products.add(product);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    public List<Product> getPagedSearchProducts(int offset, int pageSize, String search
    ) {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT p.*, c.name AS category_name FROM products p " +
                "LEFT JOIN categories c ON p.category_id = c.id " +
                "WHERE p.name LIKE ? LIMIT ? OFFSET ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "%" + search + "%");
            ps.setInt(2, pageSize);
            ps.setInt(3, offset);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Product product = new Product();
                    product.setId(rs.getInt("id"));
                    product.setCategory_id(rs.getInt("category_id"));
                    product.setName(rs.getString("name"));
                    product.setDescription(rs.getString("description"));
                    product.setPrice(rs.getDouble("price"));
                    product.setImage_url(rs.getString("image_url"));
                    product.setInventory_qty(rs.getInt("inventory_qty"));
                    product.setFeatured(rs.getBoolean("featured"));
                    product.setIs_active(rs.getBoolean("is_active"));
                    product.setCategory(rs.getString("category_name"));
                    products.add(product);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }
    public void deleteProduct(int id){
        String sql = "DELETE FROM products WHERE id = ?";
        try(Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
        ) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void updateProduct(Product product) {
        String sql = "UPDATE products SET name = ?, description = ?, price = ?, " +
                "image_url = ?, inventory_qty = ?, category_id = ?, " +
                "featured = ?, is_active = ? WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, product.getName());
            ps.setString(2, product.getDescription());
            ps.setDouble(3, product.getPrice());
            ps.setString(4, product.getImage_url());
            ps.setInt(5, product.getInventory_qty());
            ps.setInt(6, product.getCategory_id());
            ps.setBoolean(7, product.getFeatured());
            ps.setBoolean(8, product.getIs_active());

            ps.setInt(9, product.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Product> getProductsWithSortAndSearch(String search, String sortBy) {
        List<Product> products = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT p.*, c.name AS category_name FROM products p ");
        sql.append("LEFT JOIN categories c ON p.category_id = c.id ");
        
        
        if (search != null && !search.trim().isEmpty()) {
            sql.append("WHERE p.name LIKE ? ");
        }
        
       
        switch (sortBy) {
            case "price-asc":
                sql.append("ORDER BY p.price ASC");
                break;
            case "price-desc":
                sql.append("ORDER BY p.price DESC");
                break;
            case "updated-desc":
                sql.append("ORDER BY p.id DESC");
                break;
            case "featured":
            default:
                sql.append("ORDER BY p.featured DESC, p.id DESC");
                break;
        }
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql.toString())) {
            
          
            if (search != null && !search.trim().isEmpty()) {
                ps.setString(1, "%" + search + "%");
            }
            
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Product product = new Product();
                    product.setId(rs.getInt("id"));
                    product.setCategory_id(rs.getInt("category_id"));
                    product.setName(rs.getString("name"));
                    product.setDescription(rs.getString("description"));
                    product.setPrice(rs.getDouble("price"));
                    product.setImage_url(rs.getString("image_url"));
                    product.setInventory_qty(rs.getInt("inventory_qty"));
                    product.setFeatured(rs.getBoolean("featured"));
                    product.setIs_active(rs.getBoolean("is_active"));
                    product.setCategory(rs.getString("category_name"));
                    products.add(product);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }
}
