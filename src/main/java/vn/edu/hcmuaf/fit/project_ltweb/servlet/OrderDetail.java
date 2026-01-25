package vn.edu.hcmuaf.fit.project_ltweb.servlet;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.hcmuaf.fit.project_ltweb.model.Order;
import vn.edu.hcmuaf.fit.project_ltweb.model.UserPageInfo;
import vn.edu.hcmuaf.fit.project_ltweb.services.OrderService;

import java.io.IOException;

@WebServlet(name = "OrderDetail", value = "/order-detail")
public class OrderDetail extends HttpServlet {
    OrderService service = new OrderService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserPageInfo info =  new UserPageInfo();
        info.setTitle("Trang thông tin đơn hàng");
        info.setContent("/WEB-INF/views/userpages/order_detail.jsp");
        info.setCss(new String[]{
                "user/order_detail.css"
        });
        request.setAttribute("info",info);
        int orderId =  Integer.parseInt(request.getParameter("orderId"));
        Order order = service.getOrder(orderId);
        request.setAttribute("order",order);
        request.getRequestDispatcher("/WEB-INF/views/layouts/layout.jsp")
                .forward(request, response);
    }
}