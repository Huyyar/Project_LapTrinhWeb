package vn.edu.hcmuaf.fit.project_ltweb.dao;

import vn.edu.hcmuaf.fit.project_ltweb.model.OrderItem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
    public List<OrderItem> getOrderItems(int order_id){
        List<OrderItem> items =  new ArrayList<>();
        String sql = "SELECT i.*, p.name, p.image_url " +
                "FROM order_items i " +
                "LEFT JOIN products p ON p.id = i.product_id " +
                "WHERE i.order_id = ?";
        try (Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
        ){
            ps.setInt(1, order_id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                OrderItem item =  new OrderItem();
                item.setId(rs.getInt("id"));
                item.setOrder_id(rs.getInt("order_id"));
                item.setProduct_id(rs.getInt("product_id"));
                item.setProduct_name(rs.getString("name"));
                item.setProduct_image_url(rs.getString("image_url"));
                item.setPrice(rs.getDouble("price"));
                item.setQuantity(rs.getInt("quantity"));
                items.add(item);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return  items;
    }
}
