package vn.edu.hcmuaf.fit.project_ltweb.services;

import vn.edu.hcmuaf.fit.project_ltweb.dao.OrderDao;
import vn.edu.hcmuaf.fit.project_ltweb.dao.OrderItemDao;
import vn.edu.hcmuaf.fit.project_ltweb.model.Order;
import vn.edu.hcmuaf.fit.project_ltweb.model.OrderItem;

import java.util.List;

public class OrderService {
    OrderDao  orderDao = new OrderDao();
    OrderItemDao  orderItemDao = new OrderItemDao();
    public void createOrder(Order order){
        int order_id = orderDao.createOrder(order);
        List<OrderItem> items = order.getOrder_items();
        for(OrderItem orderItem : items){
            orderItem.setOrder_id(order_id);
            addOrderItem(orderItem);
        }
    }
    private void addOrderItem(OrderItem orderItem){
        orderItemDao.addOrderItem(orderItem);
    }
}
