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
    public List<Order> getOrders(String status, int userId){
        List<Order> orders =   new ArrayList<>();
        if(userId != -1) {
            if(status == null){
                orders =  orderDao.getUserOrders(userId);
            }else{
                orders =  orderDao.getUserOrdersByStatus(status, userId);
            }
            for(Order order : orders){
                order.setOrder_items(orderItemDao.getOrderItems(order.getId()));
            }
        }else{
            orders =  orderDao.getAllOrders();
            for(Order order : orders){
                order.setOrder_items(orderItemDao.getOrderItems(order.getId()));
            }
        }
        return  orders;
    }
    public Order getOrder(int id){
        Order order = orderDao.getOrder(id);
        order.setOrder_items(orderItemDao.getOrderItems(id));
        return order;
    }
}
