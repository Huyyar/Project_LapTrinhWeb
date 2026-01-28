package vn.edu.hcmuaf.fit.project_ltweb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import vn.edu.hcmuaf.fit.project_ltweb.model.Order;

public class OrderDao {
    public int createOrder(Order order){
        String sql = "INSERT INTO orders (order_code, user_id, full_name, phone, email, address_id, shipping_method, shipping_fee, payment_method, notes, total_amount, status) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        ){
            ps.setString(1, order.getOrder_code());
            ps.setInt(2, order.getUser_id());
            ps.setString(3, order.getFull_name());
            ps.setString(4, order.getPhone());
            ps.setString(5, order.getEmail());
            ps.setInt(6, order.getAddress_id());
            ps.setString(7, order.getShipping_method());
            ps.setDouble(8, order.getShipping_fee());
            ps.setString(9, order.getPayment_method());
            ps.setString(10, order.getNotes());
            ps.setDouble(11, order.getTotal_amount());
            ps.setString(12, order.getStatus());

            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1); // product_id
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return -1;
    }
    public int getTotalUserOrder(int userId){
        String sql = "SELECT COUNT(*) FROM orders WHERE user_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
        ){
            ps.setInt(1, userId);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }
    public int getTotalUserOrderByStatus(String status, int userId){
        String sql = "SELECT COUNT(*) FROM orders WHERE user_id = ? AND status = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
        ){
            ps.setInt(1, userId);
            ps.setString(2, status);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }
    public List<Order> getUserOrders(int userId, int offset, int pageSize){
        List<Order> orders =  new ArrayList<>();
        String sql = "SELECT * FROM orders WHERE user_id = ? LIMIT ? OFFSET ?";
        try (Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
        ){
            ps.setInt(1, userId);
            ps.setInt(2, pageSize);
            ps.setInt(3, offset);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Order order = new Order();
                order.setId(rs.getInt("id"));
                order.setOrder_code(rs.getString("order_code"));
                order.setUser_id(rs.getInt("user_id"));
                order.setFull_name(rs.getString("full_name"));
                order.setPhone(rs.getString("phone"));
                order.setEmail(rs.getString("email"));
                order.setAddress_id(rs.getInt("address_id"));
                order.setShipping_method(rs.getString("shipping_method"));
                order.setShipping_fee(rs.getDouble("shipping_fee"));
                order.setPayment_method(rs.getString("payment_method"));
                order.setNotes(rs.getString("notes"));
                order.setTotal_amount(rs.getDouble("total_amount"));
                order.setStatus(rs.getString("status"));
                order.setCreated_at(rs.getTimestamp("created_at").toLocalDateTime());
                order.setUpdated_at(rs.getTimestamp("updated_at").toLocalDateTime());
                orders.add(order);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return orders;
    }
    public List<Order> getUserOrdersByStatus(String status, int userId, int offset, int pageSize){
        List<Order> orders =  new ArrayList<>();
        String sql = "SELECT * FROM orders WHERE status = ? AND user_id = ? LIMIT ? OFFSET ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
        ){
            ps.setString(1, status);
            ps.setInt(2, userId);
            ps.setInt(4, offset);
            ps.setInt(3, pageSize);

            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Order order = new Order();
                order.setId(rs.getInt("id"));
                order.setOrder_code(rs.getString("order_code"));
                order.setUser_id(rs.getInt("user_id"));
                order.setFull_name(rs.getString("full_name"));
                order.setPhone(rs.getString("phone"));
                order.setEmail(rs.getString("email"));
                order.setAddress_id(rs.getInt("address_id"));
                order.setShipping_method(rs.getString("shipping_method"));
                order.setShipping_fee(rs.getDouble("shipping_fee"));
                order.setPayment_method(rs.getString("payment_method"));
                order.setNotes(rs.getString("notes"));
                order.setTotal_amount(rs.getDouble("total_amount"));
                order.setStatus(rs.getString("status"));
                order.setCreated_at(rs.getTimestamp("created_at").toLocalDateTime());
                order.setUpdated_at(rs.getTimestamp("updated_at").toLocalDateTime());
                orders.add(order);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return orders;
    }
    public int getTotalOrder(){
        String sql = "SELECT COUNT(*) FROM orders";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery();
        ){
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }
    public int getTotalOrderByStatus(String status){
        String sql = "SELECT COUNT(*) FROM orders WHERE status = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);

        ){
            ps.setString(1, status);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }
    public List<Order> getAllOrder(int offset, int pageSize){
        List<Order> orders =  new ArrayList<>();
        OrderItemDao dao  = new OrderItemDao();
        String sql = "SELECT * FROM orders LIMIT ? OFFSET ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
        ){
            ps.setInt(1, pageSize);
            ps.setInt(2, offset);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Order order = new Order();
                order.setId(rs.getInt("id"));
                order.setOrder_code(rs.getString("order_code"));
                order.setUser_id(rs.getInt("user_id"));
                order.setFull_name(rs.getString("full_name"));
                order.setPhone(rs.getString("phone"));
                order.setEmail(rs.getString("email"));
                order.setAddress_id(rs.getInt("address_id"));
                order.setShipping_method(rs.getString("shipping_method"));
                order.setShipping_fee(rs.getDouble("shipping_fee"));
                order.setPayment_method(rs.getString("payment_method"));
                order.setNotes(rs.getString("notes"));
                order.setTotal_amount(rs.getDouble("total_amount"));
                order.setStatus(rs.getString("status"));
                order.setCreated_at(rs.getTimestamp("created_at").toLocalDateTime());
                order.setUpdated_at(rs.getTimestamp("updated_at").toLocalDateTime());
                orders.add(order);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return orders;
    }
    public List<Order> getAllOrderByStatus(int offset, int pageSize, String status){
        List<Order> orders =  new ArrayList<>();
        OrderItemDao dao  = new OrderItemDao();
        String sql = "SELECT * FROM orders WHERE status = ? LIMIT ? OFFSET ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
        ){
            ps.setString(1, status);
            ps.setInt(2, pageSize);
            ps.setInt(3, offset);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Order order = new Order();
                order.setId(rs.getInt("id"));
                order.setOrder_code(rs.getString("order_code"));
                order.setUser_id(rs.getInt("user_id"));
                order.setFull_name(rs.getString("full_name"));
                order.setPhone(rs.getString("phone"));
                order.setEmail(rs.getString("email"));
                order.setAddress_id(rs.getInt("address_id"));
                order.setShipping_method(rs.getString("shipping_method"));
                order.setShipping_fee(rs.getDouble("shipping_fee"));
                order.setPayment_method(rs.getString("payment_method"));
                order.setNotes(rs.getString("notes"));
                order.setTotal_amount(rs.getDouble("total_amount"));
                order.setStatus(rs.getString("status"));
                order.setCreated_at(rs.getTimestamp("created_at").toLocalDateTime());
                order.setUpdated_at(rs.getTimestamp("updated_at").toLocalDateTime());
                orders.add(order);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return orders;
    }
    public Order getOrder(int id){
        Order order = new Order();
        String sql = "SELECT * FROM orders WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
        ){
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                order.setId(rs.getInt("id"));
                order.setOrder_code(rs.getString("order_code"));
                order.setUser_id(rs.getInt("user_id"));
                order.setFull_name(rs.getString("full_name"));
                order.setPhone(rs.getString("phone"));
                order.setEmail(rs.getString("email"));
                order.setAddress_id(rs.getInt("address_id"));
                order.setShipping_method(rs.getString("shipping_method"));
                order.setShipping_fee(rs.getDouble("shipping_fee"));
                order.setPayment_method(rs.getString("payment_method"));
                order.setNotes(rs.getString("notes"));
                order.setTotal_amount(rs.getDouble("total_amount"));
                order.setStatus(rs.getString("status"));
                order.setCreated_at(rs.getTimestamp("created_at").toLocalDateTime());
                order.setUpdated_at(rs.getTimestamp("updated_at").toLocalDateTime());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return order;
    }
    
    /**
     * Get recent orders for dashboard
     */
    public List<Order> getRecentOrders(int limit) {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT * FROM orders ORDER BY created_at DESC LIMIT ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, limit);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Order order = new Order();
                order.setId(rs.getInt("id"));
                order.setOrder_code(rs.getString("order_code"));
                order.setUser_id(rs.getInt("user_id"));
                order.setFull_name(rs.getString("full_name"));
                order.setPhone(rs.getString("phone"));
                order.setEmail(rs.getString("email"));
                order.setAddress_id(rs.getInt("address_id"));
                order.setShipping_method(rs.getString("shipping_method"));
                order.setShipping_fee(rs.getDouble("shipping_fee"));
                order.setPayment_method(rs.getString("payment_method"));
                order.setNotes(rs.getString("notes"));
                order.setTotal_amount(rs.getDouble("total_amount"));
                order.setStatus(rs.getString("status"));
                order.setCreated_at(rs.getTimestamp("created_at").toLocalDateTime());
                order.setUpdated_at(rs.getTimestamp("updated_at").toLocalDateTime());
                orders.add(order);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return orders;
    }
    
    /**
     * Get weekly revenue
     */
    public double getWeeklyRevenue() {
        String sql = "SELECT SUM(total_amount) as revenue FROM orders " +
                     "WHERE created_at >= DATE_SUB(NOW(), INTERVAL 7 DAY) " +
                     "AND status NOT IN ('cancelled', 'pending')";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return rs.getDouble("revenue");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }
    
    /**
     * Get count of items in an order
     */
    public int getOrderItemCount(int orderId) {
        String sql = "SELECT COUNT(*) as count FROM order_items WHERE order_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, orderId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("count");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }
}
