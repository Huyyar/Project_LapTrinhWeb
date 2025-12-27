package vn.edu.hcmuaf.fit.project_ltweb.dao;

import vn.edu.hcmuaf.fit.project_ltweb.model.OrderItem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OrderItemDao {
    public  void addOrderItem(OrderItem item){
        String sql = "INSERT INTO order_items (order_id, product_id, price, quantity) " +
                "VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
                ){
            ps.setInt(1, item.getOrder_id());
            ps.setInt(2, item.getProduct_id());
            ps.setDouble(3, item.getPrice());
            ps.setInt(4, item.getQuantity());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
