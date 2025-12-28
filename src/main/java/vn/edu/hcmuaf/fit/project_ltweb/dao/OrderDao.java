package vn.edu.hcmuaf.fit.project_ltweb.dao;

import vn.edu.hcmuaf.fit.project_ltweb.model.Order;
import vn.edu.hcmuaf.fit.project_ltweb.model.OrderItem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
}
