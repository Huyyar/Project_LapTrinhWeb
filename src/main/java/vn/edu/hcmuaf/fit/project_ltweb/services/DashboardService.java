package vn.edu.hcmuaf.fit.project_ltweb.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import vn.edu.hcmuaf.fit.project_ltweb.dao.OrderDao;
import vn.edu.hcmuaf.fit.project_ltweb.dao.ProductDao;
import vn.edu.hcmuaf.fit.project_ltweb.dao.UserDao;
import vn.edu.hcmuaf.fit.project_ltweb.model.Order;

public class DashboardService {
    private OrderDao orderDao = new OrderDao();
    private ProductDao productDao = new ProductDao();
    private UserDao userDao = new UserDao();
    
    /**
     * Get all dashboard statistics
     */
    public Map<String, Object> getDashboardStats() {
        Map<String, Object> stats = new HashMap<>();
        
        // Weekly revenue
        double weeklyRevenue = orderDao.getWeeklyRevenue();
        stats.put("weeklyRevenue", weeklyRevenue);
        
        // Pending orders count
        int pendingOrders = orderDao.getTotalOrderByStatus("processing");
        stats.put("pendingOrders", pendingOrders);
        
        // Hidden products count and total products
        int hiddenProducts = productDao.getHiddenProductsCount();
        int totalProducts = productDao.getTotalProducts();
        stats.put("hiddenProducts", hiddenProducts);
        stats.put("totalProducts", totalProducts);
        
        // Total users and new users today
        int totalUsers = userDao.getTotalUsers();
        int newUsersToday = userDao.getUsersRegisteredToday();
        stats.put("totalUsers", totalUsers);
        stats.put("newUsersToday", newUsersToday);
        
        return stats;
    }
    
    /**
     * Get recent orders for dashboard
     */
    public List<Order> getRecentOrders(int limit) {
        return orderDao.getRecentOrders(limit);
    }
    
    /**
     * Get order item count
     */
    public int getOrderItemCount(int orderId) {
        return orderDao.getOrderItemCount(orderId);
    }
}
