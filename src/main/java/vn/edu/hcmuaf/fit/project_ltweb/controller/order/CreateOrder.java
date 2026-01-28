package vn.edu.hcmuaf.fit.project_ltweb.controller.order;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.hcmuaf.fit.project_ltweb.cart.Cart;
import vn.edu.hcmuaf.fit.project_ltweb.cart.CartItem;
import vn.edu.hcmuaf.fit.project_ltweb.model.Order;
import vn.edu.hcmuaf.fit.project_ltweb.model.OrderItem;
import vn.edu.hcmuaf.fit.project_ltweb.model.User;
import vn.edu.hcmuaf.fit.project_ltweb.services.OrderService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@WebServlet(name = "CreateOrder", value = "/create-order")
public class CreateOrder extends HttpServlet {
    OrderService service =  new OrderService();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user =  (User)session.getAttribute("auth");
        if(user == null){
            response.sendRedirect("home");
        }
        Order order =  new Order();
        String orderCode = generateShortUUID();
        order.setOrder_code(orderCode);
        order.setUser_id(user.getId());
        order.setFull_name(request.getParameter("full_name"));
        order.setPhone(request.getParameter("phone"));
        order.setEmail(request.getParameter("email"));
        order.setAddress_id(99);
        String shipping =  request.getParameter("shipping");
        String shipping_method = "";
        Double shipping_fee = 0.0;
        if(shipping!=null){
            if(shipping.equals("standard")){
                shipping_method = "standard";
                shipping_fee =  15000.0;
            }else if(shipping.equals("express")){
                shipping_method = "express";
                shipping_fee =  45000.0;
            }
        }
        order.setShipping_method(shipping_method);
        order.setShipping_fee(shipping_fee);
        order.setPayment_method(request.getParameter("payment"));
        order.setNotes(request.getParameter("notes"));

        Cart cart = (Cart)session.getAttribute("cart");
        order.setTotal_amount(cart.getTotalPrice() + shipping_fee);
        List<OrderItem> orderItems = new ArrayList<>();
        List<CartItem> cartItems = cart.getChosenItems();
        for(CartItem cartItem :  cartItems){
            OrderItem orderItem = new  OrderItem();
            orderItem.setProduct_id(cartItem.getProduct().getId());
            orderItem.setPrice(cartItem.getPrice());
            orderItem.setQuantity(cartItem.getQty());
            orderItems.add(orderItem);
        }
        order.setOrder_items(orderItems);
        order.setStatus("processing");
        service.createOrder(order);
        cart.delChosenItems();
        response.sendRedirect("order-complete?orderCode=" + order.getOrder_code() +
                "&total=" + order.getTotal_amount());

    }

    public String generateShortUUID() {
        String uuid = UUID.randomUUID().toString().replace("-", "").substring(0, 8).toUpperCase();
        return "ORDER_" + uuid;
    }
}