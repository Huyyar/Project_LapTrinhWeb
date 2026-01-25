package vn.edu.hcmuaf.fit.project_ltweb.services;

import vn.edu.hcmuaf.fit.project_ltweb.dao.OrderDao;
import vn.edu.hcmuaf.fit.project_ltweb.dao.OrderItemDao;
import vn.edu.hcmuaf.fit.project_ltweb.model.Order;
import vn.edu.hcmuaf.fit.project_ltweb.model.OrderItem;

import java.util.ArrayList;
import java.util.List;

public class OrderService {
    OrderDao  orderDao = new OrderDao();
    OrderItemDao  orderItemDao = new OrderItemDao();
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
    public int getTotalOrder(){
        return orderDao.getTotalOrder();
    }
    public List<Order> getAllOrders(int offset, int pageSize){
        List<Order> orders =   new ArrayList<>();
        orders =  orderDao.getAllOrders(offset, pageSize);
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
}
