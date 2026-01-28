package vn.edu.hcmuaf.fit.project_ltweb.services;

import vn.edu.hcmuaf.fit.project_ltweb.dao.OrderDao;
import vn.edu.hcmuaf.fit.project_ltweb.dao.OrderItemDao;
import vn.edu.hcmuaf.fit.project_ltweb.dao.ProductDao;
import vn.edu.hcmuaf.fit.project_ltweb.model.Order;
import vn.edu.hcmuaf.fit.project_ltweb.model.OrderItem;

import java.util.ArrayList;
import java.util.List;

public class OrderService {
    OrderDao  orderDao = new OrderDao();
    OrderItemDao  orderItemDao = new OrderItemDao();
    ProductDao pDao =  new ProductDao();
    public void createOrder(Order order){
        int order_id = orderDao.createOrder(order);
        List<OrderItem> items = order.getOrder_items();
        for(OrderItem orderItem : items){
            orderItem.setOrder_id(order_id);
            orderItemDao.addOrderItem(orderItem);
        }
    }
    public int getTotalUserOrder(String status, int userId){
        int total = 0;
        if(status == null){
            total = orderDao.getTotalUserOrder(userId);
        }else{
            total = orderDao.getTotalUserOrderByStatus(status, userId);
        }
            return total;
    }
    public List<Order> getOrders(String status, int userId, int offset, int pageSize){
        List<Order> orders =   new ArrayList<>();
            if(status == null){
                orders =  orderDao.getUserOrders(userId, offset, pageSize);
            }else{
                orders =  orderDao.getUserOrdersByStatus(status, userId, offset, pageSize);
            }
            for(Order order : orders){
                order.setOrder_items(orderItemDao.getOrderItems(order.getId()));
            }
        return  orders;
    }
    public int getTotalOrder(String status){
        int total = 0;
        if(status.equals("all")){
            total = orderDao.getTotalOrder();
        }else{
            total = orderDao.getTotalOrderByStatus(status);
        }
        return total;
    }
    public List<Order> getAllOrders(int offset, int pageSize, String status){
        List<Order> orders =   new ArrayList<>();
        if(status.equals("all")){
            orders =  orderDao.getAllOrder(offset, pageSize);
        }else{
            orders =  orderDao.getAllOrderByStatus(offset, pageSize, status);
        }
        for(Order order : orders){
            order.setOrder_items(orderItemDao.getOrderItems(order.getId()));
        }
        return  orders;
    }
    public Order getOrder(int id){
        Order order = orderDao.getOrder(id);
        order.setOrder_items(orderItemDao.getOrderItems(id));
        return order;
    }
    public void cancelOrder(int id){
        orderDao.cancelOrder(id);
    }
    public List<OrderItem> getOrderItems(int id){
        return orderItemDao.getOrderItems(id);
    }
    public void updateOrderStatus(int id, String status){
        orderDao.updateOrderStatus(id, status);
    }

}
